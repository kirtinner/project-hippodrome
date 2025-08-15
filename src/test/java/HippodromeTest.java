import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {

    @Test
    @DisplayName("Конструктор Hippodrome выбрасывает исключение IllegalArgumentException, если horses = null")
    void constructorThrowExeptionIfArgumentIsNull_Test() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("Конструктор Hippodrome выбрасывает исключение IllegalArgumentException с корректным сообщением, если horses = null")
    void constructorThrowExeptionWithCorrectMessageIfArgumentIsNull_Test() {
        try {
            Hippodrome hippodrome = new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            assertEquals(message, "Horses cannot be null.");
        }
    }

    @Test
    @DisplayName("Конструктор Hippodrome выбрасывает исключение IllegalArgumentException, если лист horses пустой")
    void constructorThrowExeptionIfArgumentIsBlanc_Test() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    @DisplayName("Конструктор Hippodrome выбрасывает исключение IllegalArgumentException с корректным сообщением, если лист horses пустой")
    void constructorThrowExeptionWithCorrectMessageIfArgumentIsBlanc_Test() {
        try {
            List<Horse> horses = new ArrayList<>();
            Hippodrome hippodrome = new Hippodrome(horses);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            assertEquals(message, "Horses cannot be empty.");
        }
    }

    @Test
    @DisplayName("Метод getHorses возвращает тот же список, который был передан при сознании иподрома")
    void getHorses_Test() {
        int countHorses = 10;
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < countHorses; i++) {
            horses.add(new Horse("Horse" + i, 2.0 + i + 0.1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> result = hippodrome.getHorses();
        assertEquals(countHorses, result.size());
        for (int i = 0; i < countHorses; i++) {
            assertEquals(horses.get(i), result.get(i));
        }

    }

    @Test
    @DisplayName("Метод move вызывается у всех лошадей")
    void move_Test() {
        int countHorses = 50;
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < countHorses; i++) {
            Horse horse = mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            verify(horse, times(1)).move();
        }
        verifyNoMoreInteractions(horses.toArray());
    }

    @Test
    @DisplayName("Метод getWinner возвращает лошадь с самым большим значением distance")
    void getWinner_returnsHorseWithMaxDistance() {
        Horse h1 = new Horse("Alpha", 2.0, 50.0);
        Horse h2 = new Horse("Bravo", 3.0, 150.0); // ожидаемый победитель
        Horse h3 = new Horse("Charlie", 2.5, 120.0);
        Hippodrome hippodrome = new Hippodrome(List.of(h1, h2, h3));
        Horse winner = hippodrome.getWinner();
        assertSame(h2, winner);
    }
}


