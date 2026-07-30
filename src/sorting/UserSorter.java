package src.sorting;

import src.CustomLinkedList;
import src.user.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import src.user.User;
import src.CustomLinkedList;

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