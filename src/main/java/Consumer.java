import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Consumer {

    private final Gson gson = new Gson();
    private Stats stats;

    public Consumer(Stats stats) {
        this.stats = stats;
    }

    void run() throws IOException {
        Process generatorProcess = Runtime.getRuntime().exec("./generator-macosx-amd64");
        BufferedReader reader = new BufferedReader(new InputStreamReader(generatorProcess.getInputStream()));
        reader.lines().forEach(this::consume);
    }

    private void consume(String line) {
        try {
            Event event = gson.fromJson(line, Event.class);
            stats.addEvent(event);
        } catch (JsonParseException e) {
            // Ignore bad data
        }
    }
}
