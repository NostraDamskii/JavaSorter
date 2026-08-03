import core.CustomLinkedList;
import core.User;
import sorting.UserSorter;
import sorting.UserSortStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortTest
{
    
    @Test
    void testSortByPasswordAscending()
    {
        CustomLinkedList<User> list = new CustomLinkedList<>();
        list.add(new User.Builder().name("Олег").password(999999).build());
        list.add(new User.Builder().name("Анна").password(111111).build());
        list.add(new User.Builder().name("Иван").password(555555).build());

        UserSorter sorter = new UserSorter();

        CustomLinkedList<User> sorted = sorter.sort(list, UserSortStrategy.BY_PASSWORD_ASC);

        Assertions.assertEquals(3, sorted.size(), "размер списка должен остаться равным 3");
        Assertions.assertEquals(111111, sorted.get(0).getPassword(), "первым должен быть самый маленький пароль");
        Assertions.assertEquals(555555, sorted.get(1).getPassword());
        Assertions.assertEquals(999999, sorted.get(2).getPassword(), "последним должен быть самый большой пароль");
    }

    
    @Test
    void testSortByNameDescending()
    {
        CustomLinkedList<User> list = new CustomLinkedList<>();
        list.add(new User.Builder().name("Иван").password(123456).build());
        list.add(new User.Builder().name("Анна").password(123456).build());
        list.add(new User.Builder().name("Олег").password(123456).build());

        UserSorter sorter = new UserSorter();

        
        CustomLinkedList<User> sorted = sorter.sort(list, UserSortStrategy.BY_NAME_DESC);

        
        Assertions.assertEquals("Олег", sorted.get(0).getName());
        Assertions.assertEquals("Иван", sorted.get(1).getName());
        Assertions.assertEquals("Анна", sorted.get(2).getName());
    }

    
    @Test
    void testSortWithEmptyAndNull()
    {
        UserSorter sorter = new UserSorter();

        
        CustomLinkedList<User> nullResult = sorter.sort(null, UserSortStrategy.BY_NAME_ASC);
        Assertions.assertNotNull(nullResult, "метод должен вернуть пустой список, а не null");
        Assertions.assertTrue(nullResult.isEmpty(), "возвращенный список должен быть пустым");

        
        CustomLinkedList<User> emptyList = new CustomLinkedList<>();
        CustomLinkedList<User> emptyResult = sorter.sort(emptyList, UserSortStrategy.BY_NAME_ASC);
        Assertions.assertTrue(emptyResult.isEmpty(), "список должен остаться пустым");
    }
}
