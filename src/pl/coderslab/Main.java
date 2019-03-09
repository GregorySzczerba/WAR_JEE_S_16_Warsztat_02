package pl.coderslab;

import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
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


            //Exercise exercise = new Exercise();

            /*User user = new User("test", "tedsfd@fdgg.pl", "dsfdgg");
            User.loadUserById(connection, 1);
            user.toString();*/

            /*Solution solution = new Solution("2019-03-09", "jakis nowy description", 1, 1);
            System.out.println(solution);
            solution.saveToDB(connection);
            System.out.println(Solution.loadSolutionById(connection, 1));*/

            /*Solution[] solutions = Solution.loadAllSolutions(connection);
            for (Solution elementSolution : solutions ) {
                System.out.println(elementSolution);
            }*/

            ;
           /* Solution solution = Solution.loadSolutionById(connection, 1);
            solution.setDescription("calkiem nowy description");
            solution.saveToDB(connection);
            solution.delete(connection);

*/          Solution[] solutions = Solution.loadAllByUserId(connection, 3);
            for (Solution elementsSolution : solutions) {
                System.out.println(elementsSolution);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
