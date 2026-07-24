package User;

import java.util.Objects;

public class User {

    private String name;
    private int password;
    private String mail;

    public User(String name, int password, String mail) {
        this.name = name;
        this.password = password;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public int getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public String toString() {
        return "User.User{" +
                "name='" + name + '\'' +
                ", password=" + password +
                ", mail='" + mail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        return password == user.password
                && Objects.equals(name, user.name)
                && Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, mail);
    }
}