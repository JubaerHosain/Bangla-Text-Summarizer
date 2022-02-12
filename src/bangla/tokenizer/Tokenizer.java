package bangla.tokenizer;

import java.io.*;
import java.net.URL;

public class Tokenizer {
    public void method() throws IOException {
        // reads first found file in this root directory
//        URL url = Tokenizer.class.getResource("input.txt");
       URL url = this.getClass().getResource("input.txt");
       System.out.println(url);
       File f = new File(url.getFile());
       System.out.println(f == null);
       BufferedReader bf = new BufferedReader(new FileReader(f));

       int ch;
       while((ch = bf.read()) != -1) {
           System.out.println((char)ch);
       }
    }

    public static void main(String[] args) throws IOException {

//        System.out.println(url.getPath());
//        File f = new File(url.getFile());
//        FileWriter fw = new FileWriter(url.getPath());
////        for(int i = 0; i < 1e8; i++)
////            fw.write('A');
//        fw.write("sadf;laskdfj;lasfd");
//        fw.close();

        Tokenizer tokenizer = new Tokenizer();
        tokenizer.method();
    }
}
