import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private Calculator calculator;
    private Source source;

    @BeforeEach
    void setUp() {
        source = Mockito.mock(DBConnection.class);
        calculator = new Calculator(source);
    }

    @Test
    void whenFibonacciNotCached() {
        // Given
        int input = 5;
        Mockito.when(source.getData("5")).thenReturn(List.of());

        // When
        List<Integer> result = calculator.fibonachi(input);

        // Then
        Mockito.verify(source).saveData("5", List.of(0, 1, 1, 2, 3));
        assertEquals(List.of(0, 1, 1, 2, 3), result);
    }

    @Test
    void whenFibonacciCached() {
        // Given
        int input = 3;
        Mockito.when(source.getData("3")).thenReturn(List.of(0, 1, 1));

        // When
        List<Integer> result = calculator.fibonachi(input);

        // Then
        Mockito.verify(source, Mockito.never()).saveData(Mockito.anyString(), Mockito.anyList());
        assertEquals(List.of(0, 1, 1), result);
    }

    @Test
    void givenNegativeInput() {
        // Given
        int input = -1;

        // When/Then
        assertThrows(IllegalArgumentException.class, () -> calculator.fibonachi(input));
    }
}
