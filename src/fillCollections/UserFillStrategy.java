package src.fillCollections;

import src.CustomLinkedList;
import src.user.User;

import java.util.List;

public interface UserFillStrategy {

  void fill(CustomLinkedList<User> users);
}