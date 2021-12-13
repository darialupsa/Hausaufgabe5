package entities;

import java.util.List;
import java.util.Objects;

public class Student extends Person {
    private long StudentId;
    private int totalCredits;


    public Student(String firstName, String lastName, long studentId, int totalCredits) {
        super(firstName, lastName);
        StudentId = studentId;
        this.totalCredits = totalCredits;

    }

    public Student() {
        super();
    }

    public long getStudentId() {
        return StudentId;
    }

    public void setStudentId(long studentId) {
        StudentId = studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }


    @Override
    public String toString() {
        return "Student{" +
                "StudentId=" + StudentId +
                ", totalCredits=" + totalCredits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return StudentId == student.StudentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(StudentId);
    }
}
