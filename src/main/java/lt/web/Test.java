package lt.web;

import java.util.*;

public class Test {

    static String word1 = "Basa3  ";
    static String word2 = "Bas aa3";
    boolean matches = false;
    boolean isTwin(String word1, String word2){
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();

        if(word1 != null && word2 != null && chars1.length == chars2.length){
            for(int i = 0; i < chars1.length; i++){
                for(int j = 0; j < chars2.length; j++){
                    if( chars1[i] == chars2[j]){
                        chars2[j] = 0;
                        break;                    }
                }
            }
        }
        for(Character c : chars2){
            if(c != 0){
                return false;
            }else if(c == 0){
                matches = true;
            }
        }
        return matches;
    }
    public static void main(String[] args) {
        boolean twin = new Test().isTwin(word1, word2);
        System.out.println(twin);
    }
}
