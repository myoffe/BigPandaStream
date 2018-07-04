import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Stats {
    private Map<String, Integer> eventTypes = new HashMap<>();
    private Map<String, Integer> words = new HashMap<>();

    public void addEvent(Event event) {
        incrementEventTypeOccurrences(event.getEventType());
        Arrays.asList(splitToWords(event.getData())).forEach(this::incrementWordOccurrences);
    }

    private void incrementEventTypeOccurrences(String eventType) {
        incrementOccurrence(eventTypes, eventType);
    }

    private void incrementWordOccurrences(String word) {
        incrementOccurrence(words, word);
    }

    private void incrementOccurrence(Map<String, Integer> map, String word) {
        if (!map.containsKey(word)) {
            map.put(word, 0);
        }
        map.put(word, map.get(word) + 1);
    }

    private String[] splitToWords(String s) {
        return s.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s");
    }

    public Map<String, Integer> getEventTypes() {
        return eventTypes;
    }

    public Map<String, Integer> getWords() {
        return words;
    }
}