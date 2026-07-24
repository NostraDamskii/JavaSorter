import User.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        String filePath = "users.txt";
        int choice = scanner.nextInt();

        scanner.nextLine();

        switch (choice) {
            case 1:
                users = loadUsersFromFile(filePath);
                break;
            case 2:
                users = loadUsersFromConsole();
                break;
            default:
                System.out.println("Такого метода нет. Используем ручной метод ввода");
                users = loadUsersFromConsole();
                break;
        }






    }

    public static List<User> loadUsersFromConsole(){
        List<User> usersFromConsole=new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите пароль: ");
        int password = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите e-mail: ");
        String mail = scanner.nextLine();

        User user = new User(name, password, mail);

        System.out.println(user);

        return usersFromConsole;
    }


    public static List<User> loadUsersFromFile(String file_name){
        List<User> usersFromFile=new ArrayList<>();

        try(BufferedReader reader=new BufferedReader(new FileReader(file_name))){
            String line;
            int lineNumber=0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    User User = parseUser(line);
                    usersFromFile.add(User);
                    System.out.println("Загружен: " + User.getName());
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка в строке " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return usersFromFile;
    }

    private static User parseUser(String line) {

        String[] parts = line.split(";");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Для пользователя нужно 3 поля: 'Имя;пароль;почта");
        }

        String name = parts[0].trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }

        String strpassword=parts[1].trim();
        if (strpassword.isEmpty()) {
            throw new IllegalArgumentException("Пароль пользователя не может быть пустым");
        }
        int password=Integer.parseInt(strpassword);

        String mail=parts[2].trim();
        if( mail.isEmpty()) {
            throw new IllegalArgumentException("Почта пользователя не может быть пустой");
        }


        return new User(name, password, mail);
    }
    

}