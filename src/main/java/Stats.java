import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Stats {
    private Map<String, LongAdder> eventTypes = new ConcurrentHashMap<>();
    private Map<String, LongAdder> words = new ConcurrentHashMap<>();

    public void addEvent(Event event) {
        if (event == null) return;

        incrementEventTypeOccurrences(event.getEventType());
        Arrays.asList(splitToWords(event.getData())).forEach(this::incrementWordOccurrences);
    }

    private void incrementEventTypeOccurrences(String eventType) {
        incrementOccurrence(eventTypes, eventType);
    }

    private void incrementWordOccurrences(String word) {
        incrementOccurrence(words, word);
    }

    private void incrementOccurrence(Map<String, LongAdder> map, String str) {
        map.computeIfAbsent(str, k -> new LongAdder()).increment();
    }

    private String[] splitToWords(String s) {
        return s.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s");
    }

    public Map<String, LongAdder> getEventTypes() {
        return eventTypes;
    }

    public Map<String, LongAdder> getWords() {
        return words;
    }
}