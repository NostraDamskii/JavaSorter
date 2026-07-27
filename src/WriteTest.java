package src;

public class WriteTest {

    public static void main(String[] args)
    {
        User[] users = new User[5];
        for (int i = 0; i < users.length; i++)
        {
            users[i] = RandomGen.randomGenerateUser();
        }
        FileWrite.UsersToFile(users, "users_db.txt");
        System.out.println("Записано");
    }
}
