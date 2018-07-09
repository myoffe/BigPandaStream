import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.String.format;

public class Consumer implements Runnable {

    private final Gson gson = new Gson();

    private Stats stats;
    private InputStream inputStream;

    public Consumer(Stats stats, InputStream inputStream) {
        this.stats = stats;
        this.inputStream = inputStream;
    }


    @Override
    public void run() {
        log("Starting consumer");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        reader.lines().forEach(this::consume);

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
