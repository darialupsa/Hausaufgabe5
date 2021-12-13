package repository;

import entities.Course;
import entities.Student;

import javax.management.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrolledRepositoryJDBC {
    final String DB_URL = "jdbc:mysql://root@localhost:3306/University";
    final String USER = "root";
    final String PASS = "Daria2001";

    StudentRepositoryJDBC studentRepositoryJDBC;
    CourseRepositoryJDBC courseRepositoryJDBC;

    Connection connection;

    public EnrolledRepositoryJDBC(StudentRepositoryJDBC studentRepositoryJDBC, CourseRepositoryJDBC courseRepositoryJDBC) throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
        this.courseRepositoryJDBC = courseRepositoryJDBC;
        this.studentRepositoryJDBC = studentRepositoryJDBC;
    }

    public boolean findEnrolled(long studentId, long courseId) throws SQLException {
        String find = "Select * FROM Enrolled WHERE courseId = ? AND studentId = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(find); // transformam Stringul Find in comanda de SQL
        preparedStatement.setLong(1,courseId);
        preparedStatement.setLong(2,studentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();

    }

    public void insertIntoEnrolled(long courseId, long studentId) throws SQLException {
        String Query = "INSERT INTO Enrolled VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(Query);
        preparedStatement.setLong(1, courseId);
        preparedStatement.setLong(2, studentId);
        preparedStatement.executeUpdate();

    }

    public List<Student> findStudentsFromCourse(long courseId) throws SQLException {
        String Query = "Select studentId FROM Enrolled WHERE courdeID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(Query); // transformam Stringul Find in comanda de SQL
        preparedStatement.setLong(1,courseId);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Student> students = new ArrayList<>();
        while (resultSet.next()){
            students.add(studentRepositoryJDBC.find(resultSet.getLong("studentId")));
        }
        return students;

    }
}
