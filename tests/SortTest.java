

import org.junit.jupiter.api.Test;
import CustomLinkedList;
import sorting.UserSortStrategy;
import sorting.UserSorter;
import user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTest {


    @Test
    public void unsortedCollectionSortTest(){
        CustomLinkedList<User> unsortedList =new CustomLinkedList<>();


        User user1=new User.Builder().name("Diba").email("Diba@emil.com").password(333333).build();
        User user2=new User.Builder().name("Boba").email("Boba@emil.com").password(2222222).build();
        User user3=new User.Builder().name("Achopa").email("Achopa@emil.com").password(111111).build();

        unsortedList.add(user1);
        unsortedList.add(user2);
        unsortedList.add(user3);

        CustomLinkedList<User> expected=new CustomLinkedList<>();
        expected.add(user3);
        expected.add(user2);
        expected.add(user1);

        UserSorter sorter=new UserSorter();
        CustomLinkedList<User> actual=sorter.sort(unsortedList, UserSortStrategy.BY_NAME_ASC);

        assertEquals(expected, actual);
        
    }
}
