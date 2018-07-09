import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {
        Stats stats = new Stats();

        get("/events", (req, res) -> formattedStats(stats.getEventTypes()));
        get("/words", (req, res) -> formattedStats(stats.getWords()));

        for (int i = 0; i < 10; i++) {
            (new Thread(new Consumer(stats))).start();
        }

    }

    private static String formattedStats(Map<String, LongAdder> counter) {
        List<String> lines = counter.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());
        return String.join("<br>", lines);
    }

}