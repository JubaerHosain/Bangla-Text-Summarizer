package my.library;

public class MyLibrary {
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
     * This method splits a string by given pattern using
     * KMP string matching algorithm.
     */
    public void splitString(String pattern, String string) {
        char[] charArray = toCharArray(pattern + "也" + string);

        int[] lpsArray = computeLPS(charArray, pattern.length());
        print_Array(lpsArray);

    }



}




























