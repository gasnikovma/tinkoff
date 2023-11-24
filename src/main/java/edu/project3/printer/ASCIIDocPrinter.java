package edu.project3.printer;

import edu.project3.model.Statistics;
import java.time.OffsetDateTime;
import java.util.Map;

public class ASCIIDocPrinter implements Printer {
    public static final String DIVIDER = "|===";
    public static final String DOUBLE_NEXT_LINE = "\n\n";

    @Override
    public <T> String print(Statistics<T> statistics) {
        StringBuilder sb = new StringBuilder();
        sb.append("==").append(statistics.title()).append("\n");
        sb.append("[cols=\"1,1\"]").append("\n").append(DIVIDER).append("\n");

        if (statistics.result() instanceof Map) {
            sb.append("|").append(statistics.resource()).append(" |").append(statistics.value())
                .append(DOUBLE_NEXT_LINE);
            for (Map.Entry<T, Integer> ent : ((Map<T, Integer>) statistics.result()).entrySet()) {
                sb.append("|").append(ent.getKey()).append("\n").append("|").append(ent.getValue().toString())
                    .append(DOUBLE_NEXT_LINE);
            }
        }
        if (statistics.result() instanceof Long || statistics.result() instanceof Integer
            || statistics.result() instanceof OffsetDateTime) {
            sb.append("|").append("Metric").append(" |").append(statistics.value()).append(DOUBLE_NEXT_LINE);
            sb.append("|").append(statistics.resource()).append("\n").append("|").append(statistics.result())
                .append(DOUBLE_NEXT_LINE);
        }
        sb.append(DIVIDER);
        return sb.toString();
    }
}
