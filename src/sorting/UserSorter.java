package sorting;


import core.CustomLinkedList;
import core.User;

import java.util.Comparator;

public class UserSorter {
    public core.CustomLinkedList<User> sort(CustomLinkedList<User> users, UserSortStrategy strategy) {
        if (users == null || users.isEmpty()) {
            return new CustomLinkedList<>();
        }

        core.CustomLinkedList<User> sortedList = new CustomLinkedList<>();

        for (User user : users) {
            sortedList.add(user);
        }

        return mergeSort(sortedList, strategy);
    }
    private core.CustomLinkedList<User> mergeSort(CustomLinkedList<User> list, Comparator<User> comparator) {
        if (list.size() <= 1) {
            return list;
        }
        CustomLinkedList<User> left = new core.CustomLinkedList<>();
        core.CustomLinkedList<User> right = new CustomLinkedList<>();

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
        core.CustomLinkedList<User> result = new CustomLinkedList<>();


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