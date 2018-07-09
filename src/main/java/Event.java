import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Event {
    @SerializedName("event_type")
    private String eventType;
    private String data;
    private Long timestamp;

    public Event(String eventType, String data, Long timestamp) {
        this.eventType = eventType;
        this.data = data;
        this.timestamp = timestamp;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventType, event.eventType) &&
                Objects.equals(data, event.data) &&
                Objects.equals(timestamp, event.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, data, timestamp);
    }
}
