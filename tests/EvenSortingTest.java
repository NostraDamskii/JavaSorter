
import core.CustomLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sorting.EvenSorting;
import core.User;


class EvenSortingTest
{
    @Test
    void testSortByEvenPassword()
    {
        
        CustomLinkedList<User> list = new CustomLinkedList<>();

        
        User u1 = new User.Builder().name("Анна").password(555555).email("anna@mail.ru").build();  
        User u2 = new User.Builder().name("Иван").password(444444).email("ivan@mail.ru").build();  
        User u3 = new User.Builder().name("Олег").password(222222).email("oleg@mail.ru").build();  
        User u4 = new User.Builder().name("Яна").password(111111).email("yana@mail.ru").build();   

        
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);

        
        EvenSorting.sortByEvenPassword(list);

        
        Assertions.assertEquals(555555, list.get(0).getPassword(),
                "нечётный пароль на индексе 0 не должен был сдвинуться");

        Assertions.assertEquals(111111, list.get(3).getPassword(),
                "нечётный пароль на индексе 3 должен остаться на месте");

        
        
        Assertions.assertEquals(222222, list.get(1).getPassword(),
                "чётный пароль 222222 должен был сдвинуться вперёд на индекс 1");

        Assertions.assertEquals(444444, list.get(2).getPassword(),
                "чётный пароль 444444 должен был сместиться назад на индекс 2");
    }

    @Test
    void testSortByEvenPasswordWithNullAndEmpty()
    {
        
        
        Assertions.assertDoesNotThrow(() -> {
            EvenSorting.sortByEvenPassword(null);
        }, "метод упал при передаче null вместо списка");

        CustomLinkedList<User> emptyList = new CustomLinkedList<>();
        Assertions.assertDoesNotThrow(() -> {
            EvenSorting.sortByEvenPassword(emptyList);
        }, "метод упал при передаче пустого списка");
    }
}
