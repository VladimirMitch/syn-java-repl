package models;

public class Contact {
   public int id;
  public   int customerId;
  public   String name;
   public String phone;
   public String email;

    public Contact(int id, int customerId, String name, String phone, String email) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
