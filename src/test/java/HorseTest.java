import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

class HorseTest {

    private static String name;
    private static double speed;
    private static double distance;
    private static Horse horse;

    @BeforeAll
    static void commonVariables() {
        name = "Bella";
        speed = 10.0;
        distance = 100.0;
        horse = new Horse(name, speed, distance);
    }

    @Test
    @DisplayName("Конструктор выбрасывает исключение IllegalArgumentException, если name = null")
    void constructorThrowExeptionIfInFirstArgumentIsNull_Test() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
    }

    @Test
    @DisplayName("Конструктор выбрасывает исключение IllegalArgumentException с корректным сообщением, если name = null")
    void constructorThrowExeptionWithCorrectMessageIfInFirstArgumentIsNull_Test() {
        try {
            Horse horse = new Horse(null, speed, distance);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            assertEquals(message, "Name cannot be null.");
        }
    }

    @ParameterizedTest()
    @ValueSource(strings = {"", " ", "  ", "\t", "\n", "\t \n"})
    @DisplayName("Конструктор выбрасывает исключение IllegalArgumentException, если name = пробельные символы")
    void constructorThrowExeptionIfInFirstArgumentIsBlank_Test(String str) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(str, speed, distance));
    }

    @ParameterizedTest()
    @ValueSource(strings = {"", " ", "  ", "\t", "\n", "\t \n"})
    @DisplayName("Конструктор выбрасывает исключение IllegalArgumentException с корректным сообщением, если name = пробельные символы")
    void constructorThrowExeptionWithCorrectMessageIfInFirstArgumentIsBlanc_Test(String str) {
        try {
            Horse horse = new Horse(str, speed, distance);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            assertEquals(message, "Name cannot be blank.");
        }
    }

    @Test
    @DisplayName("Конструктор выбрасывает исключение IllegalArgumentException, если speed < 0")
    void constructorThrowExeptionIfInSecondArgumentIsNegative_Test() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, -1, distance));
    }

    @Test
    @DisplayName("Конструктор выбрасывает исключение IllegalArgumentException с корректным сообщением, если speed < 0")
    void constructorThrowExeptionWithCorrectMessageIfSecondArgumentIsNegative_Test() {
        try {
            Horse horse = new Horse(name, -1, distance);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            assertEquals(message, "Speed cannot be negative.");
        }
    }

    @Test
    @DisplayName("Конструктор выбрасывает исключение IllegalArgumentException, если distance < 0")
    void constructorThrowExeptionIfInThirdArgumentIsNegative_Test() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Bella", speed, -1));
    }

    @Test
    @DisplayName("Конструктор выбрасывает исключение IllegalArgumentException с корректным сообщением, если distance < 0")
    void constructorThrowExeptionWithCorrectMessageIfThirdArgumentIsNegative_Test() {
        try {
            Horse horse = new Horse(name, speed, -1);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            assertEquals(message, "Distance cannot be negative.");
        }
    }

    @Test
    @DisplayName("Метод getName возвращает корректное имя")
    void getName_Test() {
        assertEquals(name, horse.getName());
    }

    @Test
    @DisplayName("Метод getSpeed возвращает корректную скорость")
    void getSpeed_Test() {
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    @DisplayName("Метод getName возвращает корректную дистанция")
    void getDistance_Test() {
        assertEquals(distance, horse.getDistance());
    }

    @Test
    @DisplayName("Метод getRandomDouble вызывается 1 раз с параметрами 0.2, 0.9")
    void methodMoveShouldCallGetRandomDoubleWithCorrectArgs_Test() {
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.25, 0.5, 0.85})
    @DisplayName("Метод getRandomDouble корректно считает пройденную дистанцию")
    void methodMoveUpdatesDistanceAccordingToFormula(double mockedRandom) {
        double initialDistance = 100.0;
        double initialSpeed = 10.0;
        Horse initialHorse = new Horse("Spirit", initialSpeed, initialDistance);

        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockedRandom);
            initialHorse.move();
            double expected = initialDistance + initialSpeed * mockedRandom;
            assertEquals(expected, initialHorse.getDistance(), 1e-9);
        }
    }
}
