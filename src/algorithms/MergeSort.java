package algorithms;

import entity.Submission;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    // Sort Submission list: higher score first, faster time wins ties
    public static void sort(List<Submission> list) {
        if (list.size() <= 1) 
            return;
        mergeSort(list, 0, list.size() - 1);
    }

    private static void mergeSort(List<Submission> list, int left, int right) {
        if (left >= right) 
            return;

        int mid = (left + right) / 2;
        mergeSort(list, left, mid);       // sort left half
        mergeSort(list, mid + 1, right);  // sort right half
        merge(list, left, mid, right);    // merge them
    }

    private static void merge(List<Submission> list, int left, int mid, int right) {
        List<Submission> temp = new ArrayList<>();

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            Submission a = list.get(i);
            Submission b = list.get(j);

            boolean aFirst;
            if (a.getScore() != b.getScore()) {
                aFirst = a.getScore() > b.getScore(); // higher score first
            } else {
                aFirst = a.getTimeSeconds() < b.getTimeSeconds(); // faster time first
            }

            if (aFirst) 
                temp.add(list.get(i++));
            else        
                temp.add(list.get(j++));
        }

        while (i <= mid)  
            temp.add(list.get(i++));
        while (j <= right) 
            temp.add(list.get(j++));

        for (int k = left; k <= right; k++) {
            list.set(k, temp.get(k - left));
        }
    }
}
