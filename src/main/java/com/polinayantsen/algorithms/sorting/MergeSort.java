package com.polinayantsen.algorithms.sorting;

import com.polinayantsen.algorithms.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public static void main(String[] args) {
        List<Integer> list = Utils.getListFromFile("average_array.txt");
        System.out.println("List is sorted by MergeSort: " + mergeSort(list));
    }

    private static List<Integer> mergeSort(List<Integer> list) {
        if (list.size() <= 1) {
            return list;
        }
        int middleIndex = list.size() / 2;
        List<Integer> leftList = mergeSort(list.subList(0, middleIndex));
        List<Integer> rightList = mergeSort(list.subList(middleIndex, list.size()));
        List<Integer> sortedList = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;
        int leftLength = leftList.size();
        int rightLength = rightList.size();
        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (leftList.get(leftIndex) < rightList.get(rightIndex)) {
                sortedList.add(leftList.get(leftIndex));
                leftIndex++;
            } else {
                sortedList.add(rightList.get(rightIndex));
                rightIndex++;
            }
        }
        sortedList.addAll(leftList.subList(leftIndex, leftLength));
        sortedList.addAll(rightList.subList(rightIndex, rightLength));
        return sortedList;
    }
}
