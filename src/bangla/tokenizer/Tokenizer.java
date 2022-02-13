package bangla.tokenizer;

import my.library.List;

public class Tokenizer {
    private char DARI1 = '।';
    private char DARI2 = '৷';

    public List<String> getSentences(String text) {
        List<String> sentences = new List<>();
        // split sentences by dari
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == DARI1 || text.charAt(i) == DARI2) {
                String sentence = sb.toString().trim();
                sentences.add(sentence);
                sb = new StringBuffer();
            } else {
                sb.append(text.charAt(i));
            }
        }

        return sentences;
    }

    public static void main(String[] args) {

//        System.out.println(url.getPath());
//        File f = new File(url.getFile());
//        FileWriter fw = new FileWriter(url.getPath());
////        for(int i = 0; i < 1e8; i++)
////            fw.write('A');
//        fw.write("sadf;laskdfj;lasfd");
//        fw.close();
        Tokenizer t = new Tokenizer();
        System.out.println(t.DARI2 == '।');
    }
}
