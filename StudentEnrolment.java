import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

class StudentEnrolment implements StudentEnrolmentManager {
    Student student;
    Course course;
    String semester;
    List<StudentEnrolment> enrolmentsList = new ArrayList<>();

    StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
        enrolmentsList.add(this);
    }

    public void add(StudentEnrolment enrolment) {
        enrolmentsList.add(enrolment);
    }

    public void update() {

    }

    public void delete(StudentEnrolment enrolment) {
        for(int i=0; i<enrolmentsList.size(); i++){
            if(enrolmentsList.get(i)==enrolment) enrolmentsList.remove(i);
        }
    }

    public void getOne() {

    }

    public void getAll() {

    }
}

