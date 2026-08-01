package dataFill;

import base.CustomLinkedList;
import core.User;

public interface UserFillStrategy {

  void fill(CustomLinkedList<User> users);
}