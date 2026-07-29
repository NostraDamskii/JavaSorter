package src.menu;

public abstract class BaseMenuCommand implements MenuCommand {
  private final int actionCode;
  private final String description;

  protected BaseMenuCommand(int actionCode, String description) {
    this.actionCode = actionCode;
    this.description = description;
  }

  @Override
  public int getActionCode() {
    return actionCode;
  }

  @Override
  public String getDescription() {
    return description;
  }
}
