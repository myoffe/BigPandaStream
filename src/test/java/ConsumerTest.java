import org.junit.jupiter.api.*;
import org.mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsumerTest {
    Stats stats = mock(Stats.class);

    @Test
    void addsEventToStats_whenJsonParsingSucceeds() {
        runConsumer("{\"event_type\": \"foo\", \"data\": \"ipsum\", \"timestamp\": 1531147443}");

        verify(stats).addEvent(new Event("foo", "ipsum", 1531147443L));
    }

    @Test
    void worksWithMultipleLines() {
        runConsumer("{\"event_type\": \"foo\", \"data\": \"ipsum\", \"timestamp\": 1531147443}\n" +
                "{\"event_type\": \"bar\", \"data\": \"lorem\", \"timestamp\": 1531147999}\n" + "" +
                "{\"event_type\": \"baz\", \"data\": \"dolor\", \"timestamp\": 1531147000}");

        verify(stats).addEvent(new Event("foo", "ipsum", 1531147443L));
        verify(stats).addEvent(new Event("bar", "lorem", 1531147999L));
        verify(stats).addEvent(new Event("baz", "dolor", 1531147000L));
    }

    @Test
    void ignoresException_whenJsonParsingFails() {
        runConsumer("{badjson");
    }

    private void runConsumer(String rawEvent) {
        new Consumer(stats, new ByteArrayInputStream(rawEvent.getBytes())).run();
    }
}