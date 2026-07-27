package src.sorting;

import src.user.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserSorter {
    public List<User> sort(List<User> users, UserSortStrategy strategy) {
        if (users == null || users.isEmpty()) {
            return Collections.emptyList();
        }
        return users.stream()
                .sorted(strategy)
                .collect(Collectors.toList());
    }
}
