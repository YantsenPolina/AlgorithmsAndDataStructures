package com.polinayantsen.algorithms.sorting;

import com.polinayantsen.algorithms.utils.Utils;

import java.util.Collections;
import java.util.List;

public class BogoSort {

    public static void main(String[] args) {
        List<Integer> list = Utils.getListFromFile("small_array.txt");
        bogoSort(list);
        System.out.println("List is sorted by BogoSort: " + list);
    }

    private static void bogoSort(List<Integer> list) {
        while (!isSorted(list)) {
            Collections.shuffle(list);
        }
    }

    private static boolean isSorted(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
