package Conroller;

import entities.Course;
import entities.Student;
import entities.Teacher;
import repository.CourseRepositoryJDBC;
import repository.EnrolledRepositoryJDBC;
import repository.StudentRepositoryJDBC;
import repository.TeacherRepositoryJDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {
    private final TeacherRepositoryJDBC teacherRepository;
    private final StudentRepositoryJDBC studentRepository;
    private final CourseRepositoryJDBC courseRepository;
    private final EnrolledRepositoryJDBC enrolledRepository;

    public RegistrationSystem(TeacherRepositoryJDBC teacherRepository, StudentRepositoryJDBC studentRepository,
                              CourseRepositoryJDBC courseRepository, EnrolledRepositoryJDBC enrolledRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrolledRepository = enrolledRepository;
    }

    public EnrolledRepositoryJDBC getEnrolledRepository() {
        return enrolledRepository;
    }

    public StudentRepositoryJDBC getStudentRepository() {
        return studentRepository;
    }

    public TeacherRepositoryJDBC getTeacherRepository() {
        return teacherRepository;
    }

    public CourseRepositoryJDBC getCourseRepository() {
        return courseRepository;
    }

    /**
     *
     * @return toate elementele din CourseRepository
     */
    public List<Course> getAllCourses() throws SQLException {
        return courseRepository.getAll();
    }

    /**
     *
     * @return toate elementele din TeacherRepository
     */
    public List<Teacher> getAllTeachers() throws SQLException {
        return teacherRepository.getAll();
    }

    /**
     *
     * @return toate elementele din StudentRepository
     */
    public List<Student> getAllStudents() throws SQLException {
        return studentRepository.getAll();
    }

    /**
     * Inscrie un student la un curs
     *
     * @param courseId {@code course} to which the registration is made
     * @param studentId {@code student} for which the registration is made
     * @return TRUE daca studentul a fost inscris la curs
     */
    public boolean register(long courseId, long studentId) throws SQLException {
        if (studentRepository.find(studentId).getStudentId() == 0 || courseRepository.find(courseId).getCourseId() == 0) {
            System.out.println("Nu exista in sistem");
            return false; }
        if ((retrieveStudentsEnrolledForACourse(courseRepository.find(courseId)).size() + 1) > courseRepository.find(courseId).getMaxEnrollment()) {
            System.out.println("Studentul nu poate fi inscris - nu sunt suficiente locuri");
            return false;}
        if (studentRepository.find(studentId).getTotalCredits() + courseRepository.find(courseId).getCredits() > 30){
            System.out.println("Studentul nu poate fi inscris - are prea multe credite");
            return false;}
        if (enrolledRepository.findEnrolled(studentId, courseId)){
            System.out.println("Studentul e deja inregistrat");}
        enrolledRepository.insertIntoEnrolled(courseId, studentId);
        return true;

    }

    /**
     *
     * @return o lista cu toate cursurile unde mai sunt locuri libere
     */
    public List<Course> retrieveCoursesWithFreePlaces() throws SQLException {
        List<Course> courses = new ArrayList<>();
        for (Course course : courseRepository.getAll()){
            if (course.getMaxEnrollment() > enrolledRepository.findStudentsFromCourse(course.getCourseId()).size()){
                courses.add(course);
            }
        }
        return courses;
    }

    /**
     * Retrieve the students enrolled in a course
     *
     * @param course {@code course} in care sunt inscrisi studentii
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Course course) throws SQLException {
        return enrolledRepository.findStudentsFromCourse(course.getCourseId());
    }
}
