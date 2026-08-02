package base;

import core.CustomLinkedList;
import dataFill.RandomGen;
import service.FileWrite;
import core.User;

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
