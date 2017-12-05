package lt.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test4 {

        static Integer [] arrayInt = {10, 34, 2, 56, 7, 67, 88, 42};
        static List<Integer> arrayList = Arrays.asList(arrayInt);
        static List<Integer> parsedList = new ArrayList<>();
        static Integer maxNumbPossition;

    static List<Integer> arrayList2 = new ArrayList<>();


    public static void main(String[] args) {
            // TODO Auto-generated method stub
//		System.out.println(arrayInt.length);

            // 1 Case:
//            for(int i = 0; i < arrayList.size(); i++){
////                System.out.println("test" + i);
//                maxNumbPossition = i;
//                for(int j = 0; j < arrayList.size(); j++){
//                    if(arrayList.get(maxNumbPossition) < arrayList.get(j)){
//                        maxNumbPossition = j;
//                    }
//                }
//                parsedList.add(arrayList.get(maxNumbPossition));
//                arrayList.set(maxNumbPossition, -1);
//            }
//            System.out.println(parsedList);


            // 2 Case:
            arrayList2.add(10);
            arrayList2.add(34);
            arrayList2.add(2);
            arrayList2.add(56);
            arrayList2.add(7);
            arrayList2.add(67);
            arrayList2.add(88);
            arrayList2.add(42);

            while(arrayList2.size() !=0){
                maxNumbPossition = 0;
                for(int i = 0; i < arrayList2.size(); i++){
                    if(arrayList2.get(maxNumbPossition) < arrayList2.get(i)){
                        maxNumbPossition = i;
                    }
                }
                parsedList.add(arrayList2.get(maxNumbPossition));
                arrayList2.remove(arrayList2.get(maxNumbPossition));
            }
        System.out.println(parsedList);
        }
}
