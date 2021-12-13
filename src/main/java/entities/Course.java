package entities;

import java.util.List;
import java.util.Objects;

public class Course {
    private String name;
    private long teacherId;
    private int maxEnrollment;
    private int credits;
    private long courseId;

    public Course(String name, long teacherId, int maxEnrollment, int credits, long courseId) {
        this.name = name;
        this.teacherId = teacherId;
        this.maxEnrollment = maxEnrollment;
        this.credits = credits;
        this.courseId = courseId;
    }

    public Course(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId == course.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", teacherId=" + teacherId +
                ", maxEnrollment=" + maxEnrollment +
                ", credits=" + credits +
                ", courseId=" + courseId +
                '}';
    }
}
