
import entity.Submission;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HTMLWriter {
    public static void write(List<Submission> leaderboard, String path) throws IOException {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><meta charset='UTF-8'>");
        html.append("<title>Quiz Leaderboard</title>");
        html.append("<style>");
        html.append("body{font-family:sans-serif;max-width:700px;margin:40px auto;background:#f9f9f9}");
        html.append("h1{color:#333}");
        html.append("table{width:100%;border-collapse:collapse;background:#fff;border-radius:8px;overflow:hidden;box-shadow:0 1px 4px rgba(0,0,0,.1)}");
        html.append("th{background:#534AB7;color:#fff;padding:12px 16px;text-align:left}");
        html.append("td{padding:10px 16px;border-bottom:1px solid #eee}");
        html.append("tr:nth-child(even) td{background:#f5f5f5}");
        html.append(".rank-1{font-weight:bold;color:#534AB7}");
        html.append("</style></head><body>");
        html.append("<h1>🏆 Quiz Leaderboard</h1>");
        html.append("<table><tr><th>Rank</th><th>Name</th><th>Score</th><th>%</th><th>Time (s)</th></tr>");

        for (int i = 0; i < leaderboard.size(); i++) {
            Submission s = leaderboard.get(i);
            String cls = (i == 0) ? " class='rank-1'" : "";
            html.append("<tr><td" + cls + ">" + (i+1) + "</td>");
            html.append("<td>" + s.getStudentName() + "</td>");
            html.append("<td>" + s.getScore() + "</td>");
            html.append(String.format("<td>%.1f%%</td>", s.getPercentage()));
            html.append("<td>" + s.getTimeSeconds() + "</td></tr>");
        }

        html.append("</table></body></html>");

        try (FileWriter fw = new FileWriter(path)) {
            fw.write(html.toString());
        }
    }
}