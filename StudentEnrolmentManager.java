import java.util.ArrayList;
import java.util.List;

interface StudentEnrolmentManager{

    public int add(StudentEnrolment enrolment);
    public void update();
    public int delete(StudentEnrolment enrolment);
    public int getOne(Student student, Course course, String semester);
    public List<StudentEnrolment> getAll();
}
