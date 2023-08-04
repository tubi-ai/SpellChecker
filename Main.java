package com.company;

import java.io.*;
import java.util.*;



public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter("typos.txt");
        Set<String> dictionaryWords = readWords("dictionary.txt");
        Set<String> documentWords = readWords("text.txt");
        Set<String> spell;
        for (String word : documentWords) {
            if (!dictionaryWords.contains(word)) {
                spell= SpellChecker(dictionaryWords,word);
                writer.println("Line: "+ lineNum("text.txt", word)+
                        "\tColumn: " +colNum("text.txt", word) +
                        "\t" + word);
                writer.println(spell);
                writer.println("");
            }
        }
        writer.close();

    }
    public static Set<String> readWords(String filename)
            throws FileNotFoundException {
        // Open file:

        Scanner fileScan = new Scanner (new File(filename));
        Set<String> words = new HashSet<String>();

        //System.out.printf ("Contents of file %s:\n", filename);
        // Until there is something in the file, read it.

        while (fileScan.hasNext()){

            String word = fileScan.next();
            words.add(word.toLowerCase());

            //System.out.println( " the word: " + word + "\n");
        }
        fileScan.close();

        return words;

    }
    public static <s> Set<String> SpellChecker(Set<String> st, String s){
        Set<String> possible = new HashSet<String>();

        for (int i=0;i<s.length(); i++){
            for (char v = 'a'; v<='z'; v++){
                String s1=s.substring(0,i)+v+s.substring(i+1,s.length());
                //System.out.println("s1: "+s1);
                if (st.contains(s1)){
                    //System.out.println("1"+s1);
                    possible.add(s1);
                } if(i==s.length()-1){
                    String s6 = s + v;
                    if (st.contains(s6)){

                        possible.add(s6);
                    }
                }
                String s2 = s.substring(0, i) + v + s.substring(i,s.length());
                //System.out.println("s2: "+s2);
                if (st.contains(s2)){
                    //System.out.println("2"+s2);
                    possible.add(s2);
                }
                String s3= s.substring(0, i) + s.substring(i+1,s.length());
                //System.out.println("s3: "+s3.length());
                if (st.contains(s3)){
                    //System.out.println("3"+s3);
                    possible.add(s3);
                }
            } char[] ch = s.toCharArray();
            for (int k=0; k<ch.length-1; k++){
                String s4=s.substring(0, k) + s.charAt(k+1)
                        + s.charAt(k) + s.substring(k+2, s.length()) ;

                if (st.contains(s4)){
                    //System.out.println("4"+s4);
                    possible.add(s4);
                }

            }

        } return possible;
    }
    public static int lineNum(String file, String st)
            throws FileNotFoundException {
        int line=0;
        Scanner fileScan = new Scanner (new File(file));

                while (fileScan.hasNextLine()) {
                    line++;
                    String word = fileScan.next();
                    String w = (word.toLowerCase());
                    String s = (st.toLowerCase());
                    if (s.equals(w))
                        return line;
                }
        fileScan.close();
        return line;
    }
    public static int colNum(String file, String st)
            throws FileNotFoundException {
        int col=0;
        Scanner fileScan = new Scanner (new File(file));
        String lines = fileScan.nextLine();

            char[] chars = lines.toCharArray();

            while(fileScan.hasNextLine()){
                col=0;

                String word = fileScan.next();
                String w = (word.toLowerCase());
                col+=st.length();
                char[] chw=w.toCharArray();
                String s = (st.toLowerCase());
                char[] chs=s.toCharArray();
                col++;
                if(equals(chs,chw))
                    return (col-st.length());



            }
         fileScan.close();
        return col;
    }
    public static boolean isAlpha(char c) {
            if( (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                return true;
            return false;
    }
    public static boolean equals(char[] c1, char[] c2){
        for (int j=0; j < c2.length; j++){
            if (c1[j]!=c2[j])
                return false;
        } return true;
    }
}
