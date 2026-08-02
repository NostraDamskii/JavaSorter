package core;

import java.util.Objects;

public class User {
  private final String name;
  private final String email;
  private final int password;

  public User(Builder builder) {
    this.name = builder.name;
    this.email = builder.email;
    this.password = builder.password;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public int getPassword() {
    return password;
  }

  public static class Builder {
    private String name;
    private String email;
    private int password;

    public Builder() {
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder password(int password) {
      this.password = password;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }

  @Override
  public String toString() {
    return String.format("Имя: %-12s | Email: %-22s | Пароль: %d", name, email, password);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (password != user.password) return false;
    if (!Objects.equals(name, user.name)) return false;
    return Objects.equals(email, user.email);
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + password;
    return result;
  }
}