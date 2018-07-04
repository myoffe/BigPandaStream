import static spark.Spark.get;

public class Main {
    private static Stats stats = new Stats();

    public static void main(String[] args) throws Exception {
        get("/stats/events", (req, res) -> {
            return stats.getEventStats().toString();
        });

        get("/stats/words", (req, res) -> {
            return stats.getWordStats().toString();
        });

        new Consumer(stats).run();
    }

}