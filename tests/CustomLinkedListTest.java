

import core.CustomLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CustomLinkedListTest {

    // тестируем простое добавление add и размер списка size
    @Test
    void testAddAndSize() {
        CustomLinkedList<String> list = new CustomLinkedList<>();

        Assertions.assertTrue(list.isEmpty(), "новый список должен быть пустым");

        list.add("первый");
        list.add("второй");

        Assertions.assertEquals(2, list.size(), "размер списка должен быть равен 2");
        Assertions.assertEquals("первый", list.get(0));
        Assertions.assertEquals("второй", list.get(1));
    }

    // тестируем вставку по конкретному индексу (insert)
    @Test
    void testInsert() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(10); // индекс 0
        list.add(30); // индекс 1

        // вставляем число 20 между ними в индекс 1
        list.insert(1, 20);

        Assertions.assertEquals(3, list.size(), "размер должен увеличиться до 3");
        Assertions.assertEquals(10, list.get(0));
        Assertions.assertEquals(20, list.get(1), "число 20 должно встать на позицию 1");
        Assertions.assertEquals(30, list.get(2));
    }

    // тестируем удаление элемента
    @Test
    void testRemove() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("а");
        list.add("б");
        list.add("в");

        // удаляем центральный элемент б индекс 1
        list.remove(1);

        Assertions.assertEquals(2, list.size(), "после удаления размер должен стать равен 2");
        Assertions.assertEquals("а", list.get(0));
        Assertions.assertEquals("в", list.get(1), "элемент в должен сдвинуться на место удаленного б");
    }

    // тестируем защиту от некорректных индексов
    @Test
    void testIndexOutOfBoundsException() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("тест");

        // пытаемся взять элемент с несуществующим индексом
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        }, "IndexOutOfBoundsException при выходе за границы списка");

        // пытаемся удалить элемент с отрицательным
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        }, "IndexOutOfBoundsException при отрицательном индексе");
    }

    // 5. тестируем работу цикла for
    @Test
    void testIterator() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.add(2);

        int sum = 0;

        for (Integer num : list) {
            sum += num;
        }

        Assertions.assertEquals(3, sum, "цикл должен был корректно обойти все элементы и посчитать сумму");
    }
}
