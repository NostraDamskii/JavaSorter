package src;

import src.fillCollections.RandomGen;
import src.user.User;

import src.user.User;
import src.CustomLinkedList;

public class WriteTest {

    public static void main(String[] args)
    {
        CustomLinkedList<User> users = new CustomLinkedList<>();
        for (int i = 0; i < 5; i++)
        {
            users.add(RandomGen.randomGenerateUser());
        }

        User[] usersArray = new User[users.size()];
        for (int i = 0; i < users.size(); i++) {
            usersArray[i] = users.get(i);
        }

        FileWrite.usersToFile(usersArray, "users_db.txt");
        System.out.println("Записано");
    }
}
