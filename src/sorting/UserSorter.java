package src.sorting;

import src.CustomLinkedList;
import src.user.User;

import java.util.Collections;
import java.util.Comparator;
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

        for (User user : users) {
            sortedList.add(user);
        }

        return mergeSort(sortedList, strategy);
    }
    private CustomLinkedList<User> mergeSort(CustomLinkedList<User> list, Comparator<User> comparator) {
        if (list.size() <= 1) {
            return list;
        }
        CustomLinkedList<User> left = new CustomLinkedList<>();
        CustomLinkedList<User> right = new CustomLinkedList<>();

        int mid = list.size() / 2;
        int index = 0;

        for (User user : list) {
            if (index < mid) {
                left.add(user);
            } else {
                right.add(user);
            }
            index++;
        }


        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);

        return merge(left, right, comparator);
    }


    private CustomLinkedList<User> merge(CustomLinkedList<User> left, CustomLinkedList<User> right,
                                         Comparator<User> comparator) {
        CustomLinkedList<User> result = new CustomLinkedList<>();


        java.util.Iterator<User> leftIter = left.iterator();
        java.util.Iterator<User> rightIter = right.iterator();

        User leftVal = leftIter.hasNext() ? leftIter.next() : null;
        User rightVal = rightIter.hasNext() ? rightIter.next() : null;

        while (leftVal != null && rightVal != null) {
            if (comparator.compare(leftVal, rightVal) <= 0) {
                result.add(leftVal);
                leftVal = leftIter.hasNext() ? leftIter.next() : null;
            } else {
                result.add(rightVal);
                rightVal = rightIter.hasNext() ? rightIter.next() : null;
            }
        }

        while (leftVal != null) {
            result.add(leftVal);
            leftVal = leftIter.hasNext() ? leftIter.next() : null;
        }

        while (rightVal != null) {
            result.add(rightVal);
            rightVal = rightIter.hasNext() ? rightIter.next() : null;
        }

        return result;

    }
}