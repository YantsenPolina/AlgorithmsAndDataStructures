package com.polinayantsen.algorithms.sorting;

import com.polinayantsen.algorithms.utils.Utils;

import java.util.List;

public class SelectionSort {

    public static void main(String[] args) {
        List<Integer> list = Utils.getListFromFile("small_array.txt");
        selectionSort(list);
        System.out.println("List is sorted by SelectionSort: " + list);
    }

    private static void selectionSort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int indexOfMin = getIndexOfMin(list, i);
            Integer min = list.get(indexOfMin);
            list.set(indexOfMin, list.get(i));
            list.set(i, min);
        }
    }

    private static int getIndexOfMin(List<Integer> list, int indexFrom) {
        int minIndex = indexFrom;
        for (int i = indexFrom + 1; i < list.size(); i++) {
            if (list.get(i) < list.get(minIndex)) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
