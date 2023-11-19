package edu.project3.printer;

import edu.project3.model.Statistics;
import java.time.OffsetDateTime;
import java.util.Map;

public class MarkdownPrinter implements Printer {
    @Override
    public <T> String print(Statistics<T> statistics) {
        StringBuilder sb = new StringBuilder();
        sb.append("#### ").append(statistics.title()).append("\n");
        int widthResourceCol = statistics.resource().length();
        int widthValueCol = statistics.value().length();

        if (statistics.result() instanceof Map) {
            for (Map.Entry<T, Integer> ent : ((Map<T, Integer>) statistics.result()).entrySet()) {
                if (widthResourceCol < ent.getKey().toString().length()) {
                    widthResourceCol = ent.getKey().toString().length();
                }
                if (widthValueCol < ent.getValue().toString().length()) {
                    widthValueCol = ent.getValue().toString().length();
                }
            }
            sb.append("|").append(setTitleOrDivider(statistics.resource(), " ", widthResourceCol));
            sb.append("|").append(setTitleOrDivider(statistics.value(), " ", widthValueCol));
            sb.append("|").append("\n");
            sb.append("|:").append(setTitleOrDivider("", "-", widthResourceCol - 2)).append(":|")
                .append("-".repeat(widthValueCol - 1)).append(":").append("|").append("\n");

            for (Map.Entry<T, Integer> ent : ((Map<T, Integer>) statistics.result()).entrySet()) {
                sb.append("|").append(setTitleOrDivider(ent.getKey(), " ", widthResourceCol))
                    .append("|").append(setTitleOrDivider(ent.getValue().toString(), " ", widthValueCol))
                    .append("|").append("\n");
            }
        }
        if (statistics.result() instanceof Double || statistics.result() instanceof Integer ||
            statistics.result() instanceof OffsetDateTime) {
            String statisticString = statistics.result().toString();
            widthValueCol = Math.max(widthValueCol, statisticString.length());
            widthResourceCol = Math.max("Metric".length(), widthResourceCol);

            sb.append("|").append(setTitleOrDivider("Metric", " ", widthResourceCol)).append("|")
                .append(setTitleOrDivider(statistics.value(), " ", widthValueCol)).append("|").append("\n");
            sb.append("|:").append(setTitleOrDivider("", "-", widthResourceCol - 2)).append(":|")
                .append("-".repeat(widthValueCol - 1)).append(":").append("|").append("\n");
            sb.append("|").append(setTitleOrDivider(statistics.resource(), " ", widthResourceCol)).append("|")
                .append(setTitleOrDivider(statisticString, " ", widthValueCol)).append("|").append("\n\n");
        }
        return String.valueOf(sb);
    }

    private <T> String setTitleOrDivider(T data, String addSymbol, int expected) {
        int len = data.toString().length();
        StringBuilder sb = new StringBuilder(expected);
        int diff = expected - len;
        int left = (diff + 1) / 2;
        int right = diff - left;

        for (int i = 0; i < left; i++) {
            sb.append(addSymbol);
        }
        sb.append(data);
        for (int i = 0; i < right; i++) {
            sb.append(addSymbol);
        }
        return sb.toString();
    }

}
