package src;

import src.fillCollections.RandomGen;
import src.user.User;

public class WriteTest {

    public static void main(String[] args)
    {
        User[] users = new User[5];
        for (int i = 0; i < users.length; i++)
        {
            users[i] = RandomGen.randomGenerateUser();
        }
        //тест на пустые поля
        //users[1] = null;
        //users[3] = null;

        FileWrite.usersToFile(users, "users_db.txt");
        System.out.println("Записано");
    }
}
