package sorting;

import base.CustomLinkedList;
import user.User;

public class UserSorter {
    public CustomLinkedList<User> sort(CustomLinkedList<User> users, UserSortStrategy strategy) {
        if (users == null || users.isEmpty()) {
            return new CustomLinkedList<>();
        }

        CustomLinkedList<User> sortedList = new CustomLinkedList<>();

        users.stream()
            .sorted(strategy)
            .forEach(sortedList::add);

        return sortedList;
    }
}