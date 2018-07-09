import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.concurrent.atomic.LongAdder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatsTest {
    Stats stats;

    @BeforeEach
    void init() {
        stats = new Stats();
    }

    @Test
    void addEvent_ignoresNullEvents() {
        stats.addEvent(null);

        assertThat(stats.getWords()).isEmpty();
        assertThat(stats.getEventTypes()).isEmpty();
    }

    @Test
    void addEvent_incrementsEventTypeOccurrences() {
        assertThat(stats.getEventTypes()).doesNotContainKey("foo");

        stats.addEvent(new Event("foo", "data", 1531147443L));

        assertThat(stats.getEventTypes().get("foo").intValue()).isEqualTo(1);

        stats.addEvent(new Event("foo", "other", 1531147443L));
        stats.addEvent(new Event("foo", "stuff", 1531147443L));

        assertThat(stats.getEventTypes().get("foo").intValue()).isEqualTo(3);
    }

    @Test
    void addEvent_incrementsWordOccurrences() {
        assertThat(stats.getWords()).doesNotContainKey("hello");

        stats.addEvent(new Event("foo", "hello", 1531147443L));

        assertThat(stats.getWords().get("hello").intValue()).isEqualTo(1);

        stats.addEvent(new Event("bar", "hello", 1531147443L));
        stats.addEvent(new Event("foo", "hello", 1531147443L));

        assertThat(stats.getWords().get("hello").intValue()).isEqualTo(3);
    }

    @Test
    void addEvent_incrementsWordOccurrences_withMultipleWordEvents() {
        stats.addEvent(new Event("foo", "hello,foo hello(hello) -foo", 1531147443L));

        assertThat(stats.getWords().get("foo").intValue()).isEqualTo(2);
        assertThat(stats.getWords().get("hello").intValue()).isEqualTo(3);
    }
}