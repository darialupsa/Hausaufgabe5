package repository;

import entities.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryJDBC implements ICrudRepo<Course>{
    final String DB_URL = "jdbc:mysql://root@localhost:3306/University";
    final String USER = "root";
    final String PASS = "Daria2001";

    Connection connection;

    public CourseRepositoryJDBC() throws SQLException {
       this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @Override
    public Course find(long Id) throws SQLException {
        String Find = "SELECT * FROM Course WHERE courseId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(Find); // transformam Stringul Find in comanda de SQL
        preparedStatement.setLong(1,Id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Course course = new Course();

        if (resultSet != null){

            while (resultSet.next())
            {
                course.setCourseId(resultSet.getLong("courseId"));
                course.setName(resultSet.getString("name"));
                course.setTeacherId(resultSet.getLong("teacherId"));
                course.setMaxEnrollment(resultSet.getInt("maxEnrollment"));
                course.setCredits(resultSet.getInt("credits"));
            }
        }
        return course;
    }

    @Override
    public List<Course> getAll() throws SQLException {
        String findAll = "SELECT * FROM Course";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll); // transformam Stringul findAll in comanda de SQL
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Course> courses = new ArrayList<>();
        if (resultSet != null){
            while (resultSet.next()){
                Course course = new Course();
                course.setCourseId(resultSet.getLong("courseId"));
                course.setName(resultSet.getString("name"));
                course.setTeacherId(resultSet.getLong("teacherId"));
                course.setMaxEnrollment(resultSet.getInt("maxEnrollment"));
                course.setCredits(resultSet.getInt("credits"));
                courses.add(course);
            }
        }
        return courses;

    }

    @Override
    public Course update(Course obj) throws SQLException {
        String update = "UPDATE Course SET maxEnrollment = ?, credits = ? WHERE courseId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(update); // transformam Stringul update in comanda de SQL
        preparedStatement.setInt(1,obj.getMaxEnrollment());
        preparedStatement.setInt(2,obj.getCredits());
        preparedStatement.setLong(3,obj.getCourseId());
        preparedStatement.executeUpdate();

    return obj;
    }

    @Override
    public void delete(long Id) throws SQLException {
        String delete = "DELETE FROM Course WHERE courseId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setLong(1,Id);
        preparedStatement.executeUpdate();
    }
}
