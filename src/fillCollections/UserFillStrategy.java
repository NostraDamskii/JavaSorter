package fillCollections;

import user.User;

public interface UserFillStrategy {

  void fill(customCollection.CustomLinkedList<User> users);
}