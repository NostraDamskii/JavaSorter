

import core.CustomLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CustomLinkedListTest {

    
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

    
    @Test
    void testInsert() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(10); 
        list.add(30); 

        
        list.insert(1, 20);

        Assertions.assertEquals(3, list.size(), "размер должен увеличиться до 3");
        Assertions.assertEquals(10, list.get(0));
        Assertions.assertEquals(20, list.get(1), "число 20 должно встать на позицию 1");
        Assertions.assertEquals(30, list.get(2));
    }

    
    @Test
    void testRemove() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("а");
        list.add("б");
        list.add("в");

        
        list.remove(1);

        Assertions.assertEquals(2, list.size(), "после удаления размер должен стать равен 2");
        Assertions.assertEquals("а", list.get(0));
        Assertions.assertEquals("в", list.get(1), "элемент в должен сдвинуться на место удаленного б");
    }

    
    @Test
    void testIndexOutOfBoundsException() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("тест");

        
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        }, "IndexOutOfBoundsException при выходе за границы списка");

        
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        }, "IndexOutOfBoundsException при отрицательном индексе");
    }

    
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
