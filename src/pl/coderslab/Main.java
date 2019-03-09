package pl.coderslab;

import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // System.out.println("test");

        //User user = new User("grzegorz.szczerba", "test2@gazeta.pl", "123456789");
        try (Connection connection = DbUtil.getConnection()) {


            User.usersManagment(connection);


        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }


}
