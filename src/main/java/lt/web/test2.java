package lt.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class test2 {


    public static void main(String[] args) {

        System.out.println(new Timestamp(System.currentTimeMillis()));

        int m = 5;
        int n = 1000;

        List<Integer> integerList = new ArrayList<>();
        List<Integer> finalList = new ArrayList<>();

        for(int i = m; i <= n; i++){
            int generateRandomMN = new Random().nextInt((n - m) + 1) + m;
            integerList.add(generateRandomMN);
        }
        List<Integer> modifList = integerList;

        for(int i = 0; i < integerList.size(); i++){
            int temp = i;
            for(int j = 0; j < modifList.size(); j++){
               if(integerList.get(temp) > modifList.get(j)){
                   temp = j;
               }
            }
            finalList.add(integerList.get(temp));
            modifList.set(temp, n+1);
        }
        System.out.println(finalList.toString());
        System.out.println(finalList.size());
        System.out.println(new Timestamp(System.currentTimeMillis()));



    }

}
