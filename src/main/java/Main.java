import repository.CourseRepositoryJDBC;
import repository.StudentRepositoryJDBC;
import repository.TeacherRepositoryJDBC;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
       /* CourseRepositoryJDBC courseRepositoryJDBC = new CourseRepositoryJDBC();
        System.out.println(courseRepositoryJDBC.find(1));
        System.out.println(courseRepositoryJDBC.getAll());
        System.out.println(courseRepositoryJDBC.update(courseRepositoryJDBC.find(1)));
        courseRepositoryJDBC.delete(2); */

        StudentRepositoryJDBC studentRepositoryJDBC = new StudentRepositoryJDBC();
        System.out.println(studentRepositoryJDBC.getAll());
        System.out.println(studentRepositoryJDBC.find(1));

        TeacherRepositoryJDBC teacherRepositoryJDBC = new TeacherRepositoryJDBC();
        System.out.println(teacherRepositoryJDBC.find(1));
        System.out.println(teacherRepositoryJDBC.getAll());
    }
}
