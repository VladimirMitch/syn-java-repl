package mysql;

import java.sql.*;

public class Select {
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/somedb","someuser","somepass");
            System.out.println("Connection to Maria has been established");
            conn.createStatement().execute("create table if not exists Users(\n" +
                    " id int primary key auto_increment,\n" +
                    " name varchar(20) not null,\n" +
                    " phone varchar(20) default null\n" +
                    ");");
            System.out.println("Tables created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll() {
        String sql = "select id,phone from Users where name like '%Petya%'";

        try (Connection conn = this.connect();

//             String sqlCreate = "\n" +
//                    "insert into Users (name, phone) values\n" +
//                     "('Petya', '65132'),\n" +
//                     "('Vasya', '48651'),\n" +
//                     "('Katya', null);";
//             Statement stmtCreate = conn.createStatement();
//             stmtCreate.execute(sqlCreate);

        Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        Select app = new Select();
        app.selectAll();
    }
}
