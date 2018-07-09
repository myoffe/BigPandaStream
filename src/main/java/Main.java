import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

import static spark.Spark.get;

public class Main {
    private static final int NUM_CONSUMERS = 100;

    public static void main(String[] args) throws Exception {
        Stats stats = new Stats();

        get("/events", (req, res) -> formattedStats(stats.getEventTypes()));
        get("/words", (req, res) -> formattedStats(stats.getWords()));

        for (int i = 0; i < NUM_CONSUMERS; i++) {
            (new Thread(new Consumer(stats, createInputStreamFromProcess()))).start();
        }

    }

    private static String formattedStats(Map<String, LongAdder> counter) {
        List<String> lines = counter.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());
        return String.join("<br>", lines);
    }

    private static InputStream createInputStreamFromProcess() throws IOException {
        Process generatorProcess = Runtime.getRuntime().exec("./generator-macosx-amd64");
        return generatorProcess.getInputStream();
    }
}