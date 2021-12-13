package repository;

import entities.Course;
import entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryJDBC implements ICrudRepo<Student> {
    final String DB_URL = "jdbc:mysql://root@localhost:3306/University";
    final String USER = "root";
    final String PASS = "Daria2001";

    Connection connection;

    public StudentRepositoryJDBC() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL, USER, PASS);}

    @Override
    public Student find(long Id) throws SQLException {
        String Find = "SELECT * FROM Student WHERE studentId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(Find); // transformam Stringul Find in comanda de SQL
        preparedStatement.setLong(1,Id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Student student = new Student();

        if (resultSet != null){

            while (resultSet.next())
            {
                student.setStudentId(resultSet.getLong("studentId"));
                student.setFirstName(resultSet.getString("firstName"));
                student.setLastName(resultSet.getString("lastName"));
                student.setTotalCredits(resultSet.getInt("totalCredits"));
            }
        }
        return student;
    }

    @Override
    public List<Student> getAll() throws SQLException {
        String findAll = "SELECT * FROM Student";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll); // transformam Stringul findAll in comanda de SQL
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Student> students = new ArrayList<>();
        if (resultSet != null){
            while (resultSet.next()){
                Student student = new Student();
                student.setStudentId(resultSet.getLong("studentId"));
                student.setFirstName(resultSet.getString("firstName"));
                student.setLastName(resultSet.getString("lastName"));
                student.setTotalCredits(resultSet.getInt("totalCredits"));
                students.add(student);
            }
        }
        return students;

    }



    @Override
    public Student update(Student obj) throws SQLException {
        String update = "UPDATE Student SET totalCredits = ? WHERE studentId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(update); // transformam Stringul update in comanda de SQL
        preparedStatement.setInt(1,obj.getTotalCredits());
        preparedStatement.setLong(2,obj.getStudentId());

        preparedStatement.executeUpdate();

        return obj;
    }

    @Override
    public void delete(long Id) throws SQLException {
        String delete = "DELETE FROM Student WHERE studentId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setLong(1,Id);
        preparedStatement.executeUpdate();
    }
}
