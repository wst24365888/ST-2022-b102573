import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[]{3, 2, 1, 5, 4}, new int[]{1, 2, 3, 4, 5}),
                Arguments.of(new int[]{-1, -2, -3, -5, -4}, new int[]{-5, -4, -3, -2, -1}),
                Arguments.of(new int[]{0}, new int[]{0}),
                Arguments.of(new int[]{1, -1, 0}, new int[]{-1, 0, 1}),
                Arguments.of(new int[]{4, 8, 7, 6, 3}, new int[]{3, 4, 6, 7, 8})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument=(0),(1)")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> testQueue = new PriorityQueue<Integer>();
        int[] result = new int[random_array.length];

        for (int el : random_array) {
            testQueue.add(el);
        }

        for (int i = 0; testQueue.size() != 0; i++) {
            result[i] = testQueue.poll();
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void whenInitialCapacityLessThan1_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue testQueue = new PriorityQueue(0);
        });
    }

    @Test
    public void whenSpecifiedCollectionIsNull_thenThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            Collection c = null;
            PriorityQueue testQueue = new PriorityQueue(c);
        });
    }

    @Test
    public void whenElementCannotBeComparedToTheNextOne_thenThrowClassCastException() {
        assertThrows(ClassCastException.class, () -> {
            PriorityQueue testQueue = new PriorityQueue();
            testQueue.add(8+9);
            testQueue.add("8+9");
        });
    }
}
