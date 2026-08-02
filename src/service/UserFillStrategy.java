package service;

import core.CustomLinkedList;
import core.User;

public interface UserFillStrategy {

  void fill(CustomLinkedList<User> users);
}