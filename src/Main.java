

import dao.QuestionDAO;
import dao.StudentDAO;
import dao.SubmissionDAO;
import entity.Option;
import entity.Question;
import entity.Submission;
import algorithms.FisherYates;
import algorithms.MergeSort;
import algorithms.Scorer;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // 1. Get student name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        StudentDAO studentDAO = new StudentDAO();
        int studentId = studentDAO.insertStudent(name);

        // 2. Load questions from DB
        QuestionDAO questionDAO = new QuestionDAO();
        List<Question> allQuestions = questionDAO.getAllQuestions();

        // 3. Fisher-Yates shuffle → pick 10
        List<Question> quiz = FisherYates.pickNRandom(allQuestions, 10);

        // 4. Start timer
        long startTime = System.currentTimeMillis();

        // 5. Present questions one at a time
        Map<Integer, Integer> answers = new HashMap<>();
        for (int i = 0; i < quiz.size(); i++) {
            Question q = quiz.get(i);
            System.out.println("\nQ" + (i+1) + ": " + q.getText());

            List<Option> opts = q.getOptions();
            for (int k = 0; k < opts.size(); k++) {
                System.out.println("  " + (char)('A'+k) + ") " + opts.get(k).getText());
            }
            System.out.print("Your answer (A/B/C/D): ");
            String ans = scanner.nextLine().trim().toUpperCase();
            int idx = ans.charAt(0) - 'A';
            if (idx >= 0 && idx < opts.size()) {
                answers.put(q.getId(), opts.get(idx).getId());
            }
        }

        // 6. Calculate score
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        Scorer scorer = new Scorer();
        int score = scorer.calculateScore(quiz, answers);
        float pct = scorer.calculatePercentage(score, quiz.size());

        System.out.println("\n--- RESULT ---");
        System.out.println("Score: " + score + "/" + quiz.size());
        System.out.printf("Percentage: %.1f%%%n", pct);
        System.out.println("Time: " + elapsed + " seconds");

        // 7. Save submission
        SubmissionDAO subDAO = new SubmissionDAO();
        subDAO.saveSubmission(studentId, score, pct, (int) elapsed);

        // 8. Fetch + sort leaderboard
        List<Submission> leaderboard = subDAO.getLeaderboard();
        MergeSort.sort(leaderboard);   // No Arrays.sort!

        // 9. Write HTML
        HTMLWriter.write(leaderboard, "output/leaderboard.html");
        System.out.println("\nLeaderboard updated: output/leaderboard.html");
    }
}
