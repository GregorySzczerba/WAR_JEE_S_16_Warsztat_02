package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class Solution {
    private int id;
    private String created;
    private String updated;
    private String description;
    private int exercise_id;
    private int users_id;

    public Solution() {
    }

    public Solution(String created, String description, int exercise_id, int users_id) {
        this.created = created;
        this.description = description;
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO solution(created, description, exercise_id, users_id) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, this.created);
            preparedStatement.setString(2, this.description);
            preparedStatement.setInt(3, this.exercise_id);
            preparedStatement.setInt(4, this.users_id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE solution SET updated = ?, description = ?, exercise_id = ?, users_id = ?  where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, this.updated);
            preparedStatement.setString(2, this.description);
            preparedStatement.setInt(3, this.exercise_id);
            preparedStatement.setInt(4, this.users_id);
            preparedStatement.setInt(5, this.id);
            preparedStatement.executeUpdate();
        }
    }

    static public Solution loadSolutionById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM solution where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getString("created");
            loadedSolution.updated = resultSet.getString("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise_id = resultSet.getInt("exercise_id");
            loadedSolution.users_id = resultSet.getInt("users_id");
            return loadedSolution;}
        return null;
    }

    static public Solution[] loadAllSolutions(Connection conn) throws SQLException {
        ArrayList<Solution> solutions = new ArrayList<Solution>();
        String sql = "SELECT * FROM solution";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getString("created");
            loadedSolution.updated = resultSet.getString("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise_id = resultSet.getInt("exercise_id");
            loadedSolution.users_id = resultSet.getInt("users_id");

            solutions.add(loadedSolution);}
        Solution[] uArray = new Solution[solutions.size()];
        uArray = solutions.toArray(uArray);
        return uArray;
    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM solution WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", exercise_id=" + exercise_id +
                ", users_id=" + users_id +
                '}';
    }
}
