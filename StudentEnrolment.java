import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

class StudentEnrolment implements StudentEnrolmentManager {
    Student student;
    Course course;
    String semester;
    ArrayList<StudentEnrolment> enrolmentsList = new ArrayList<>();

    StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public void add() {
        enrolmentsList.add(this);
    }

    public void update() {
        HashMap<String, ArrayList<Course>> semCourses = new HashMap<>();
        for (StudentEnrolment enrolment : enrolmentsList) {
            if (enrolment.student == this.student) {
                if (semCourses.containsKey(enrolment.semester))
                    semCourses.get(enrolment.semester).add(enrolment.course);
                else {
                    ArrayList<Course> courses = new ArrayList<>();
                    courses.add(enrolment.course);
                    semCourses.put(enrolment.semester, courses);
                }
            }
        }
    }

    public void delete() {

    }

    public void getOne() {

    }

    public void getAll() {

    }
}

