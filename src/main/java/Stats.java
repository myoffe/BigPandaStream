import java.util.HashMap;
import java.util.Map;

public class Stats {


    public Map<String, Integer> getEventStats() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("foo", 5);
        map.put("bar", 7);
        return map;
    }

    public Map<String, Integer> getWordStats() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("hi", 5);
        map.put("bye", 7);
        return map;
    }
}