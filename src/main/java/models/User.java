package models;

import java.util.List;

public class User {
   public int id;
    public String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

   public List<Contact> contacts;

    public User(List<Contact> contacts) {
        this.contacts = contacts;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
