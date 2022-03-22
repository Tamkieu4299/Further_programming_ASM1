import java.util.List;
import java.util.ArrayList;

class EnrolmentManagement implements StudentEnrolmentManager{
    public List<StudentEnrolment> enrolmentsList = new ArrayList<>();

    EnrolmentManagement(){
        super();
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

    public int getOne(Student student, Course course, String semester) {
        for(StudentEnrolment se: enrolmentsList){
            if(se.student == student && se.course == course && se.semester == semester) return 1;
        }
        return 0;
    }

    public List<StudentEnrolment> getAll() {
        return enrolmentsList;
    }
}
