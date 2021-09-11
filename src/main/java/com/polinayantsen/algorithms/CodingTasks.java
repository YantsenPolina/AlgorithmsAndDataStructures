package com.polinayantsen.algorithms;

import com.polinayantsen.algorithms.utils.Utils;

import java.util.Collections;
import java.util.List;

public class CodingTasks {

    public static void main(String[] args) {
        List<Integer> list = Utils.getListFromFile("average_array.txt");
        System.out.println("Number of pairs: " + getNumberOfPairs(list)); // 4
    }

    /*
    Calculates number of pairs in array.
    Pair is when two numbers added together, result is zero.
     */
    public static int getNumberOfPairs(List<Integer> array) {
        int numberOfPairs = 0;
        Collections.sort(array);
        int leftBound = 0;
        int rightBound = array.size() - 1;
        while (leftBound < rightBound) {
            if (array.get(leftBound) + array.get(rightBound) > 0) {
                rightBound--;
            } else if (array.get(leftBound) + array.get(rightBound) < 0) {
                leftBound++;
            } else {
                numberOfPairs++;
                leftBound++;
                rightBound--;
            }
        }
        return numberOfPairs;
    }

    /*
    Finds some element in binary search tree.
     */

    /*
    Reverses elements in one directional LinkedList.
     */

    /*
    Having two separate sorted arrays, merges these arrays in single sorted array without sorting it.
     */

    /*
    An array contains numbers.
    Exactly one number is duplicated in the array.
    Finds this duplicate.
     */

    /*
    In a 2D matrix, every row is increasingly sorted from left to right.
    Last number in each row is not greater than first number in next row.
    Checks if there is a number in the matrix.
     */

    /*
    Finds number of occurrences of some number in given array of numbers.
     */
}
