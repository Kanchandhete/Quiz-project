package algorithms;

import entity.Question;
import java.util.*;
import java.util.Random;

public class FisherYates {
    public static List<Question> pickNRandom(List<Question> all, int n) {
        Random random = new Random();
        int m = all.size();

        // Step 1: shuffle in-place using Fisher-Yates
        for (int i = m - 1; i >= 1; i--) {
            // Pick random j from 0 to i (inclusive)
            int j = random.nextInt(i + 1);    

            // Swap all[i] and all[j]
            Question temp = all.get(i);
            all.set(i, all.get(j));
            all.set(j, temp);
        }

        // Step 2: return first N elements
        return new ArrayList<>(all.subList(0, n));
    }
}
