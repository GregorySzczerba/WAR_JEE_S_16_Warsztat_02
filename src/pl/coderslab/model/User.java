package pl.coderslab.model;

import pl.coderslab.util.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public int getId() {
        return id;
    }

   /* public void setId(int id) {
        this.id = id;
    } to jest chyba niepotrzebne*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }

        } else {
            String sql = "UPDATE users SET username=?, email=?, password=? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.setInt(4, this.id);
            preparedStatement.executeUpdate();
        }
    }

    static public User loadUserById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM users where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            return loadedUser;
        }
        return null;
    }

    static public User[] loadAllUsers(Connection conn) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            users.add(loadedUser);}
        User[] uArray = new User[users.size()]; uArray = users.toArray(uArray);
        return uArray;}

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }


    public static void usersManagment(Connection connection) throws SQLException {
        boolean quit = false;
        User[] users = User.loadAllUsers(connection);
        for (User userElement : users) {
            System.out.println(userElement);
        }
        System.out.println("Wybierz jedną z opcji: ");
        System.out.println("add - dodanie nowego użytkownika");
        System.out.println("edit - edycja użytkownika");
        System.out.println("delete - skasowanie użytkownika");
        System.out.println("quit - wyjście z programu");


        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        while (!quit) {


            switch (option) {

                case "quit":
                    quit = true;
                    System.out.println("Do widzenia");
                    break;

                case "delete":
                    System.out.println("Podaj id użytkownika, którego chcesz usunąć");
                    int id = scanner.nextInt();
                    User user = User.loadUserById(connection, id);
                    user.delete(connection);

                    break;

                case "add":
                    System.out.println("Podaj nazwę użytkownika: ");
                    String username = scanner.nextLine();
                    System.out.println("Podaj email użytkownika : ");
                    String email = scanner.nextLine();
                    System.out.println("Podaj hasło uzytkownika: ");
                    String password = scanner.nextLine();

                    User user1 = new User(username, email, password);
                    user1.saveToDB(connection);
                    break;

                case "edit":
                    System.out.println("Podaj nową nazwę użytkownika: ");
                    username = scanner.nextLine();
                    System.out.println("Podaj nowy email użytkownika : ");
                    email = scanner.nextLine();
                    System.out.println("Podaj nowe hasło uzytkownika: ");
                    password = scanner.nextLine();
                    System.out.println("Podaj id użytkownika do edycji");
                    id = scanner.nextInt();
                    user = User.loadUserById(connection, id);
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.saveToDB(connection);
                    break;

                default:
                    System.out.println("Podaj poprawną opcję");
                    break;

            }
            if (!"quit".equals(option)) {
                users = User.loadAllUsers(connection);
                for (User userElement : users) {
                    System.out.println(userElement);
                }
                System.out.println("Wybierz jedną z opcji: ");
                System.out.println("add - dodanie nowego użytkownika");
                System.out.println("edit - edycja użytkownika");
                System.out.println("delete - skasowanie użytkownika");
                System.out.println("quit - wyjście z programu");
                option = scanner.nextLine();

            }
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
