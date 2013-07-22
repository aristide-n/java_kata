package algorithms;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 7/22/13
 */
public class SumPairFinder {
    public static void main (String[] args){
        // TESTS
        SumPairFinder finder = new SumPairFinder();

        int[] firstInput = {};
        int firstSum = 10;

        System.out.println("first test");
        finder.findSorted(firstInput, firstSum);
        System.out.println("first test unsorted");
        finder.findUnsorted(firstInput, firstSum);


        int[] secondInput = {4};
        int secondSum = 5;

        System.out.println("second test");
        finder.findSorted(secondInput, secondSum);
        System.out.println("second test unsorted");
        finder.findUnsorted(secondInput, secondSum);


        int[] thirdInput = {2,4,6,5,1,3};
        int thirdSum = 6;

        System.out.println("third test");
        finder.findSorted(thirdInput, thirdSum);
        System.out.println("third test unsorted");
        finder.findUnsorted(thirdInput, thirdSum);


        int[] fourthInput = {8, 7, 6, 6, 5, 4, 3, 2, 1};
        int fourthSum = 10;

        System.out.println("fourth test");
        finder.findSorted(fourthInput, fourthSum);
        System.out.println("fourth test unsorted");
        finder.findUnsorted(fourthInput, fourthSum);
    }

    /**
     *
     * @param inputArr
     * @param n
     *
     * T(n) = runtime of Arrays.sort = O(nlogn)
     *
     * Algorithm:
     *  1. Sort the input
     *  2. Set two pointers one at the beginning the other at the end of the array
     *  3. Move the end pointer to the first element that is less than the sum
     *  4. compare the sum to the addition of the elements at the two locations of the pointers
     *  5. print the pairs and move the pointers to the next location towards each other if the previous comparison
     *     is true
     *  6. move one of the pointers towards the other to increase or decrease the value of the addition  if the previous
     *     comparison is false
     *  7. terminate when the pointers point at the same location
     *
     */
    public void findSorted(int[] inputArr, int n){
        if (inputArr.length >= 2){
            int iLeft = 0;
            int iRight = inputArr.length - 1;

            Arrays.sort(inputArr);

            while (inputArr[iRight] > n)
            {
                iRight--;
            }


            while (iRight > iLeft ){
                if (n == (inputArr[iLeft] + inputArr[iRight])){
                    System.out.println("Pair: " + inputArr[iLeft] + " " + inputArr[iRight]);
                    iLeft++;
                    iRight--;
                }
                else if (n < inputArr[iLeft] + inputArr[iRight]){
                    iRight--;
                }
                else{
                    iLeft++;
                }
            }
        }
    }

    /**
     *
     * @param inputArr
     * @param n
     *
     * T(n) = O(n)
     *
     * Algorithm:
     *  1. put all array element in a HashMap where the array index is the key
     *  2. remove the map entries in order of array index, retaining the value which is the element of the input array
     *     at that index
     *  3. substract the retained value from the sum to find the value that forms a pair with it
     *  4. Check if the map contains the value obtained in the previous step, and print the pair if that is the case.
     *  4. Terminate after attempting to find the pair including the second to last element of the input array
     *
     */
    public void findUnsorted(int[] inputArr, int n){
        if (inputArr.length >= 2){
            HashMap<Integer, Integer> inputMap = new HashMap<Integer, Integer>();

            int i = 0;
            while(i < inputArr.length){
                inputMap.put(i, inputArr[i]);
                i++;
            }

            int j = 0;
            while(j < inputArr.length-1){
                int pairEl1 = inputMap.remove(j);
                int pairEl2 = n - pairEl1;

                if (inputMap.containsValue(pairEl2)){
                    System.out.println("Pair: " + pairEl1 + " " + pairEl2);
                }

                j++;
            }
        }
    }
}
