import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NextDayCalculatorTest {

    @Test
    void testNextDay1() {
        String result = NextDayCalculator.getNextDay(1, 1, 2018); // đầu vào
        assertEquals("2/1/2018", result);                                 // ngày tiếp theo
    }

    @Test
    void testNextDay2() {
        String result = NextDayCalculator.getNextDay(31, 1, 2018);
        assertEquals("1/2/2018", result);
    }

    @Test
    void testNextDay3() {
        String result = NextDayCalculator.getNextDay(30, 4, 2018);
        assertEquals("1/5/2018", result);
    }

    @Test
    void testNextDay4() {
        String result = NextDayCalculator.getNextDay(28, 2, 2018);
        assertEquals("1/3/2018", result);
    }

    @Test
    void testNextDay5() {
        String result = NextDayCalculator.getNextDay(29, 2, 2020);
        assertEquals("1/3/2020", result);
    }

    @Test
    void testNextDay6() {
        String result = NextDayCalculator.getNextDay(31, 12, 2018);
        assertEquals("1/1/2019", result);
    }
}