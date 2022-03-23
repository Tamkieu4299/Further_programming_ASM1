import java.util.List;
import java.util.ArrayList;

class EnrolmentManagement implements StudentEnrolmentManager{
    public List<StudentEnrolment> enrolmentsList = new ArrayList<>();
    public List<Student> studentsList = new ArrayList<>();
    public List<Course> coursesList = new ArrayList<>();
    public List<String> semestersList = new ArrayList<>();

    EnrolmentManagement(){
        super();
    }

    public int add(StudentEnrolment enrolment) {
        if(!enrolmentsList.contains(enrolment)) {
            enrolmentsList.add(enrolment);
            System.out.println("Successfully added !");
            return 1;
        }
        System.out.println("This enrolment has been created before !");
        return 0;
    }

    public void update() {

    }

    public int delete(StudentEnrolment enrolment) {
        for(int i=0; i<enrolmentsList.size(); i++){
            if(enrolmentsList.get(i)==enrolment) {
                enrolmentsList.remove(i);
                System.out.println("Successfully deleted !");
                return 1;
            }
        }
        System.out.println("This enrolment hasn't been created before !");
        return 0;
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
    
    public List<Student> getStudents(){
        return studentsList;
    }

    public List<Course> getCourses(){
        return coursesList;
    }

    public List<String> getSemesters(){
        return semestersList;
    }
}
