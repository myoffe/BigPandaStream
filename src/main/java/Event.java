import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("event_type")
    private String eventType;
    private String data;
    private Long timestamp;

    @Override
    public String toString() {
        return "Event{" +
                "eventType='" + eventType + '\'' +
                ", data='" + data + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getEventType() {
        return eventType;
    }

    public String getData() {
        return data;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
