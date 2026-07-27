package src.fillCollections;

import src.user.User;

import java.util.List;

public interface UserFillStrategy {

  void fill(List<User> users);
}