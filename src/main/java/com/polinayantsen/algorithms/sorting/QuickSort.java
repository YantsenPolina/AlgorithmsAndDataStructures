package com.polinayantsen.algorithms.sorting;

import com.polinayantsen.algorithms.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

    public static void main(String[] args) {
        List<Integer> list = Utils.getListFromFile("average_array.txt");
        System.out.println("List is sorted by QuickSort: " + quickSort(list));
    }

    public static List<Integer> quickSort(List<Integer> list) {
        if (list.size() <= 1) {
            return list;
        }
        List<Integer> lessThanPivot = new ArrayList<>();
        List<Integer> greaterThanPivot = new ArrayList<>();
        int pivot = list.get(0);
        int length = list.size();
        for (int i = 1; i < length; i++) {
            int currentValue = list.get(i);
            if (currentValue <= pivot) {
                lessThanPivot.add(currentValue);
            } else {
                greaterThanPivot.add(currentValue);
            }
        }
        ArrayList<Integer> sortedList = new ArrayList<>();
        sortedList.addAll(quickSort(lessThanPivot));
        sortedList.add(pivot);
        sortedList.addAll(quickSort(greaterThanPivot));
        return sortedList;
    }
}
