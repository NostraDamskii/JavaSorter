package service;

import core.User;

import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
public class FileWrite
{
    
    public static void usersToFile(User[] array, String fileName)
    {
        
        if (array == null)
        {
            return;
        }
        
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             PrintWriter printWriter = new PrintWriter(fileWriter))
        {
            
          for (User user : array) {
            
            if (user == null) {
              continue;
            }
            
            
            String textLine = user.getName() + ";" + user.getPassword() + ";" + user.getEmail();
            
            printWriter.println(textLine);
          }
            System.out.println("сохранено в файл: " + fileName);
        }
        catch (IOException e)
        {
            System.out.println("ошибка не удалось записать: " + e.getMessage());
        }
    }
}
