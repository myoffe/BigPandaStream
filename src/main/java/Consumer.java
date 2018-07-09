import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.String.format;

public class Consumer implements Runnable {

    private final Gson gson = new Gson();
    private Stats stats;

    public Consumer(Stats stats) {
        this.stats = stats;
    }


    @Override
    public void run() {
        log("Starting consumer");
        try {
            Process generatorProcess = Runtime.getRuntime().exec("./generator-macosx-amd64");
            BufferedReader reader = new BufferedReader(new InputStreamReader(generatorProcess.getInputStream()));
            reader.lines().forEach(this::consume);
        } catch (IOException e) {
            log("Error in consumer: " + e);
        }

    }
    private void consume(String line) {
        log("consuming line: " + line);
        try {
            Event event = gson.fromJson(line, Event.class);
            stats.addEvent(event);
        } catch (JsonParseException e) {
            // Ignore bad data
        }
    }

    private void log(String msg) {
        System.out.println(format("%s %s", Thread.currentThread(), msg));
    }

}
