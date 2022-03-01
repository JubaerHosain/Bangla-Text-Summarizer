package bangla.tokenizer;

import my.library.MyList;
import my.library.Pair;

public class MyLibrary {

    public MyLibrary() {

    }


    /**
     * This method prints an integer array
     */
    public void print_Array(int[] array) {
        for(int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }


    /**
     * This method converts a String to array of character.
     */
    public char[] toCharArray(String string) {
        int length = string.length();
        char[] charArray = new char[length];
        for(int i = 0; i < length; i++)
            charArray[i] = string.charAt(i);
        return charArray;
    }


    /**
     * This method calculates LPS(Longest Prefix which is Suffix)
     * array which is needed for finding pattern match
     */
    private int[] computeLPS(char[] charArray, int patternLength) {
        int length = charArray.length;
        int[] lpsArray = new int[length];

        lpsArray[0] = 0;
        int currentLength = 0, iterator = 1;
        while(iterator < length) {
            if(charArray[iterator] == charArray[currentLength]) {
                lpsArray[iterator++] = ++currentLength;
            } else {
                if(currentLength == 0) {
                    lpsArray[iterator++] = 0;
                } else {
                    currentLength = lpsArray[currentLength-1];
                }
            }
        }

        // bcz lpsArray includes pattern itself also and
        // an extra character that isn't present in pattern nor in string
        // finalLPSArray only contains the values of string part, not pattern or extra character
        int[] finalLPSArray = new int[length-patternLength-1];
        iterator = 0;
        for(int i = patternLength+1; i < length; i++)
            finalLPSArray[iterator++] = lpsArray[i];

        return finalLPSArray;
    }


    /**
     * This method finds the ranges(pattern range) which will be removed from the string
     */
    private MyList<Pair<Integer, Integer>> findRemovableRanges(int[] lpsArray, int patternLength) {
        MyList<Pair<Integer, Integer>> removableRanges = new MyList<>();

        int start = 0;
        for(int i = 0; i < lpsArray.length; i++) {
            if(lpsArray[i] != patternLength) {
                continue;
            } else if(i-patternLength+1 >= start) {
                removableRanges.add(new Pair<>(i-patternLength+1,i));
                start = i + 1;
            }
        }

        return removableRanges;
    }

    /**
     * This method extracts the tokens removing patterns
     */
    private MyList<String> extractTokens(MyList<Pair<Integer, Integer>> ranges, String string) {
        MyList<String> tokens = new MyList<>();

        // First interval (0 to firstValue of first range)
        // Append in string takes n^2, that's why I used StringBuffer
        StringBuffer temp = new StringBuffer();
        for(int i = 0; i <= ranges.get(0).getFirst()-1; i++) {
            temp.append(string.charAt(i));
        }

        if(temp.length() != 0) {
            tokens.add(temp.toString());
        }

        // Intermediary intervals
        for(int i = 0; i < ranges.size()-1; i++) {
            temp = new StringBuffer();
            int left = ranges.get(i).getSecond()+1;
            int right = ranges.get(i+1).getFirst()-1;
            for(int j = left; j <= right; j++)
                temp.append(string.charAt(j));
            if(temp.length() != 0) {
                tokens.add(temp.toString());
            }
        }

        // Last interval
        temp = new StringBuffer();
        for(int i = ranges.get(ranges.size()-1).getSecond()+1; i < string.length(); i++) {
            temp.append(string.charAt(i));
        }

        if(temp.length() != 0) {
            tokens.add(temp.toString());
        }

        return tokens;
    }


    /**
     * This method splits a string by given pattern using
     * KMP string matching algorithm.
     */
    public void splitString(String pattern, String string) {
        char[] charArray = toCharArray(pattern + "也" + string);
        int[] lpsArray = computeLPS(charArray, pattern.length());

        // find ranges that would be removed from the string
        MyList<Pair<Integer, Integer>> removableRanges = findRemovableRanges(lpsArray, pattern.length());

        MyList<String> tokens = extractTokens(removableRanges, string);
        for(int i = 0; i < tokens.size(); i++)
            System.out.println(tokens.get(i));
    }
}