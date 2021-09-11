package com.polinayantsen.algorithms.searching;

import com.polinayantsen.algorithms.sorting.QuickSort;
import com.polinayantsen.algorithms.utils.Utils;

import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {
        List<Integer> list = Utils.getListFromFile("average_array.txt");
        QuickSort.quickSort(list);
        System.out.println("[IterativeBinarySearch] Index of element 28 is: " + iterativeBinarySearch(list, 28));
        System.out.println("[RecursiveBinarySearch] Index of element 28 is: " + recursiveBinarySearch(list, 28, 0, list.size() - 1));
    }

    public static int iterativeBinarySearch(List<Integer> input, int target) {
        int first = 0;
        int last = input.size() - 1;
        while (first <= last) {
            int mid = (first + last) / 2;
            if (input.get(mid) == target) {
                return mid;
            } else if (input.get(mid) < target) {
                first = mid + 1;
            } else {
                last = mid - 1;
            }
        }
        return -1;
    }

    public static int recursiveBinarySearch(List<Integer> input, int target, int start, int end) {
        if (start >= end) {
            return -1;
        } else {
            int mid = start + (end - start) / 2;
            if (target < input.get(mid)) {
                return recursiveBinarySearch(input, target, start, mid - 1);
            } else if (target > input.get(mid)) {
                return recursiveBinarySearch(input, target, mid + 1, end);
            } else {
                return mid;
            }
        }
    }
}
