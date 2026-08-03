package sorting;

import core.CustomLinkedList;
import core.User;

public class EvenSorting
{
    
    public static void sortByEvenPassword(CustomLinkedList<User> list)
    {
        
        if (list == null || list.size() < 2)
        {
            return;
        }

        
        int evenCount = 0;
        
        for (User user : list)
        {
            
            if (user != null && user.getPassword() % 2 == 0)
            {
                evenCount++;
            }
        }

        
        if (evenCount < 2)
        {
            return;
        }

        
        User[] evenArray = new User[evenCount];
        int index = 0;
        
        for (User user : list)
        {
            if (user != null && user.getPassword() % 2 == 0)
            {
                evenArray[index] = user;
                index++;
            }
        }

        
        
        manualQuickSort(evenArray, 0, evenArray.length - 1);

        
        CustomLinkedList<User> resultList = new CustomLinkedList<>();
        int evenCounter = 0;

        
        for (User currentUser : list)
        {
            if (currentUser != null && currentUser.getPassword() % 2 == 0)
            {
                
                resultList.add(evenArray[evenCounter]);
                evenCounter++;
            }
            else
            {
                
                resultList.add(currentUser);
            }
        }

        
        list.clear();
        
        list.addAll(resultList);
    }

    
    private static void manualQuickSort(User[] array, int low, int high)
    {
        
        if (low >= high)
        {
            return;
        }

        
        int middle = low + (high - low) / 2;
        int pivot = array[middle].getPassword();

        
        int i = low;
        int j = high;

        
        while (i <= j)
        {
            
            while (array[i].getPassword() < pivot)
            {
                i++;
            }
            
            while (array[j].getPassword() > pivot)
            {
                j--;
            }
            
            if (i <= j)
            {
                
                User temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                
                i++;
                j--;
            }
        }

        
        
        if (low < j)
        {
            manualQuickSort(array, low, j);
        }
        
        
        if (high > i)
        {
            manualQuickSort(array, i, high);
        }
    }
}
