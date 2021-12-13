package entities;

import java.util.List;
import java.util.Objects;

public class Teacher extends Person {
    private long teacherId;


    public Teacher(String firstName, String lastName, long teacherId) {
        super(firstName, lastName);
        this.teacherId = teacherId;
    }

    public Teacher() {

    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return teacherId == teacher.teacherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teacherId);
    }
}
