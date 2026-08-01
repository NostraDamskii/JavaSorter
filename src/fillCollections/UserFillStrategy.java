package fillCollections;

import base.CustomLinkedList;
import user.User;

import java.util.List;

public interface UserFillStrategy {

  void fill(CustomLinkedList<User> users);
}