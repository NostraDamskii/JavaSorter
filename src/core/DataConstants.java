package core;

public class DataConstants {
  public static final int MAX_PASSWORD = 999999;
  public static final int MIN_PASSWORD = 100000;

  public static final String PASSWORD_ERROR_TEMPLATE =
      "Ошибка! Пароль должен быть в диапазоне от %d до %d";

  public static boolean isPasswordCorrect(int password){
    return MIN_PASSWORD <= password && password <=MAX_PASSWORD;
  }
}
