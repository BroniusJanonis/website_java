package lt.web;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Test3 {


    // NEVEIKIA


//        private static int randomNumb = 0;
//        private static int min = 1;
//        private static int max = 100;
//        private static int minTryTimes = 0;
//        private static int[] arrayFilled = IntStream.rangeClosed(min, max).toArray();
//        private static int correctNumber = max/2;
//
//        public static void main(String[] args) {
//            // TODO Auto-generated method stub
//            randomNumb = ThreadLocalRandom.current().nextInt(min, max + 1);
//
//            // minimum times to find generated random
//            while(correctNumber > min -1 && correctNumber < max +1){
//
//                if(randomNumb < arrayFilled[correctNumber] && randomNumb != arrayFilled[correctNumber]){
//                    correctNumber = correctNumber - (correctNumber/2 + correctNumber/2);
//                    System.out.println("Chosen number: " + randomNumb + " checking with correct number: " + correctNumber + ".");
//                    minTryTimes++;
//                } else if(randomNumb > arrayFilled[correctNumber] && randomNumb != arrayFilled[correctNumber]) {
//                    correctNumber = correctNumber + (correctNumber/2 + correctNumber%2);
//                    System.out.println("Chosen number: " + randomNumb + " checking with correct number: " + correctNumber + ".");
//                    minTryTimes++;
//                } else {
//                    correctNumber = arrayFilled[correctNumber];
//                    break;
//                }
//            }
//
//            System.out.println("FINAL Chosen number: " + randomNumb + " was found in: " + minTryTimes + " tries.");
//
//        }

    private static int min = 0;
    private static int max = 100;
    private static int tryTimes = 0;
    private static int randomNumb = ThreadLocalRandom.current().nextInt(min, max + 1);
    private static int[] arrayFilled = IntStream.rangeClosed(min, max).toArray();
    private static int correctNumber = (max - min )/2 + (max -  min)%2;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // minimum times to find generated random
        while(true){
            // Set Max Value
            if(randomNumb < max - correctNumber && randomNumb != arrayFilled[max]){
                max = correctNumber;
                tryTimes++;
                correctNumber = (max - min )/2 + (max -  min)%2;
            } // Set Min Value
            else if(randomNumb > min + correctNumber && randomNumb != arrayFilled[min]) {
                min = min + correctNumber;
                tryTimes++;
                correctNumber = (max - min )/2 + (max -  min)%2;
            } // Else correct answer is:
            else {
                correctNumber = arrayFilled[correctNumber];
                break;
            }
        }
        System.out.println("Chosen number: " + randomNumb + " was found in " + tryTimes + " tries.");

    }



//    void sortM(int[] mas, int maz, int didziausias){
//        if(maz < didziausias){
//            int viduriukas = (maz + didziausias) / 2;
//            sortM(mas, maz, viduriukas);
//            sortM(mas, viduriukas+1, didziausias);
//            keisk(mas, maz, viduriukas, didziausias);
//        }
//    }
//    void keisk(int[] mas, int maz, int viduriukas, int didziausias){
//        int[] laikinas = new int[mas.length];
//        for (int i = maz; i <= didziausias; i++) {
//            laikinas[i] = mas[i];
//        }
//
//        int laikinasKaire = maz;
//        int laikinasDesine = viduriukas+1;
//        int vid = maz;
//
//        while (laikinasKaire <= viduriukas && laikinasDesine <=didziausias) {
//            if(laikinas[laikinasKaire] <= laikinas[laikinasDesine]){
//                mas[vid] = laikinas[laikinasKaire];
//                laikinasKaire++;
//
//            }else{
//                mas[vid] = laikinas[laikinasDesine];
//                laikinasDesine++;
//            }
//            vid ++;
//        }
//
//        int laikinasprisiminti = viduriukas - laikinasKaire;
//        for (int i = 0; i <= laikinasprisiminti; i++) {
//            mas[vid+i] = laikinas[laikinasKaire+ i];
//        }
//    }

}
