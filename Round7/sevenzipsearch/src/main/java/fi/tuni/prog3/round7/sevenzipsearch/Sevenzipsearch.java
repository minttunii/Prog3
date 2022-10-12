
package fi.tuni.prog3.round7.sevenzipsearch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

public class Sevenzipsearch {

    public static void main(String[] args) throws IOException {
        try(SevenZFile sevenZFile = new SevenZFile(new File(args[0]))){
            SevenZArchiveEntry entry;
            while((entry = sevenZFile.getNextEntry())!= null){
                String name = entry.getName();
                //Entry is a file it ends with ".txt"
                if(!name.substring(name.length()-4).equals(".txt") || name.length()< 4){
                return;
                }
                else{
                    System.out.println(name);
                    // Now the file is read and given word is searched
                    // If the word is found, the row is printed
                    InputStream input = sevenZFile.getInputStream(entry);
                    try (Scanner scanner = new Scanner(input)) {
                        int rownum = 1;
                        String wordtosearch = args[1].toLowerCase();
                        while(scanner.hasNext()){
                            String line = scanner.nextLine();
                            String[] words = line.split(" "); 
                            
                            if(line.toLowerCase().contains(wordtosearch)){
                               int x1 = 0;
                                while (x1 < words.length) {
                                if (words[x1].toLowerCase().equals(wordtosearch)) {
                                    words[x1] = words[x1].toUpperCase();
                                } else if (words[x1].toLowerCase().contains(wordtosearch)) {
                                    int pos = words[x1].toLowerCase().indexOf(wordtosearch);
                                    String temp = words[x1].substring(pos, pos + wordtosearch.length()).toUpperCase();
                                    words[x1] = words[x1].substring(0, pos) + temp + words[x1].substring(pos + wordtosearch.length());
                                }
                                x1++;
                            }
                                line = String.join(" ", words);
                                System.out.println(rownum + ": " + line);                               
                            } 
                            rownum++;
                            }
                            System.out.println();                         
                        }
                    }
                }
                sevenZFile.close();
        } 
}
}
    

