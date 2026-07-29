package src.menu;

public enum MenuConfig {
  FILL_RANDOM("Заполнить массив случайными пользователями"),
  FILL_CONSOLE("Заполнить массив вручную (через консоль)"),
  FILL_FILE("Заполнить массив из файла"),
  DISPLAY("Вывести полученный массив на экран"),
  EXPORT("Сохранить текущий массив в файл"),
  SORT("Меню сортировки"),
  CLEAR("Очистить текущий массив"),
  PARALLEL_COUNT("Многопоточный подсчет вхождений пользователя (Parallel Stream)");

  private final String description;

  MenuConfig(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
