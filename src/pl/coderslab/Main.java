package pl.coderslab;

import pl.coderslab.model.Exercise;
import pl.coderslab.model.User;
import pl.coderslab.util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
       // System.out.println("test");

        //User user = new User("grzegorz.szczerba", "test2@gazeta.pl", "123456789");
        try (Connection connection = DbUtil.getConnection()) {
            //user.saveToDB(connection);

          //  user = User.loadUserById(connection, 6);
           // user.delete(connection);
            /*user.setUsername("Pankracy2");
            user.setEmail("dssdfd@fsdfd.pl");
            user.saveToDB(connection);

            User[] users = User.loadAllUsers(connection);
            for (User userElement : users) {
                System.out.println(userElement);
            }*/


            //exercise.saveToDB(connection);
            //Exercise.loadExerciseById(connection, 1);
            //exercise.toString();
            //System.out.println(exercise);
            //exercise.saveToDB(connection);
            //exercise.setTitle("nowy testowy title");
            //exercise = pl.coderslab.Exercise.loadExerciseById(connection, 5);
            //exercise.delete(connection);



            //pl.coderslab.Exercise[] exercises = pl.coderslab.Exercise.loadAllExercise(connection);
            //for (pl.coderslab.Exercise exerciseElement : exercises) {
            //    System.out.println(exerciseElement);
            //}


            Exercise exercise = new Exercise();

            /*User user = new User("test", "tedsfd@fdgg.pl", "dsfdgg");
            User.loadUserById(connection, 1);
            user.toString();*/


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
