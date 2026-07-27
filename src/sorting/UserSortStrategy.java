package src.sorting;

import src.user.User;
import java.util.Comparator;

public enum UserSortStrategy implements Comparator<User> {
    BY_NAME_ASC {
        @Override
        public int compare(User u1, User u2) {
            return u1.getName().compareToIgnoreCase(u2.getName());
        }
    },
    BY_NAME_DESC {
        @Override
        public int compare(User u1, User u2) {
            return u2.getName().compareToIgnoreCase(u1.getName());
        }
    },

    BY_EMAIL_ASC {
        @Override
        public int compare(User u1, User u2) {
            return u1.getEmail().compareToIgnoreCase(u2.getEmail());
        }
    },
    BY_EMAIL_DESC {
        @Override
        public int compare(User u1, User u2) {
            return u2.getEmail().compareToIgnoreCase(u1.getEmail());
        }
    },

    BY_PASSWORD_ASC {
        @Override
        public int compare(User u1, User u2) {
            return Integer.compare(u1.getPassword(), u2.getPassword());
        }
    },
    BY_PASSWORD_DESC {
        @Override
        public int compare(User u1, User u2) {
            return Integer.compare(u2.getPassword(), u1.getPassword());
        }
    },

}
