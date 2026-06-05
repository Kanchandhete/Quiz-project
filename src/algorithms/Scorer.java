package algorithms;

import entity.Option;
import entity.Question;
import java.util.List;
import java.util.Map;

public class Scorer {
    public int calculateScore(List<Question> questions, Map<Integer, Integer> answers) {
        int score = 0;
        for (Question q : questions) {
            Integer selectedOptionId = answers.get(q.getId());
            if (selectedOptionId == null) 
                continue;

            for (Option opt : q.getOptions()) {
                if (opt.getId() == selectedOptionId && opt.isIsCorrect()) {
                    score++;
                    break;
                }
            }
        }
        return score;
    }

    public float calculatePercentage(int score, int total) {
        return (float) score / total * 100;
    }
}
