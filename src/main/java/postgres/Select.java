package postgres;

import models.Contact;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Select {
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/somedb", "someuser", "somepass");
            System.out.println("Connection to Postgres has been established");
            conn.createStatement().execute("create table if not exists Users(\n" +
                    " id int generated always as identity,\n" +
                    " name varchar(20) not null,\n" +
                    " primary key(id)\n" +
                    ");\n" +
                    "\n" +
                    "create table if not exists \"Contacts\"(\n" +
                    " id INT generated always as identity,\n" +
                    " \"customerId\" INT, \n" +
                    " \"contactName\" VARCHAR(255) not null, \n" +
                    " phone VARCHAR(15),\n" +
                    " email VARCHAR(100),\n" +
                    " primary key(id), \n" +
                    " constraint \"fk_Users_Contacts\"\n" +
                    " foreign key(\"customerId\") \n" +
                    "\t references \"Users\"(id)\n" +
                    ");");

            System.out.println("Tables created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll() {
        String sql = "select u.id as \"Users->id\", u.name as \"Users ->name\", c.id as \"contactId\", c.\"contactName\", c.phone, c.email from \"Users\" u left join \"Contacts\" c on c.\"customerId\"  = u.id; ";
        List<User> users = new ArrayList<>();
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            User tmpUser = null;
            List<Contact> tmpContacts = new ArrayList<Contact>();

            while (rs.next()) {
                if (tmpUser != null && tmpUser.id != rs.getInt("Users->id")) {
                    tmpUser.contacts = tmpContacts;
                    users.add(tmpUser);
                    tmpUser = null;

                }

                tmpUser = new User(rs.getInt("Users->id"), rs.getString("Users->name"));
                tmpContacts.add(new Contact(rs.getInt("contactId"), rs.getInt("Users->id"), rs.getString("contactName"), rs.getString("phone"), rs.getString("email") ));
            }
            if (tmpUser !=null) {
                tmpUser.contacts = tmpContacts;
                users.add(tmpUser);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(users);

    }

    public static void main(String[] args) {
        Select app = new Select();
        app.selectAll();
    }
}
