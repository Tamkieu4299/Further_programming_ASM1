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

    public int update(String idStudent, String idCourse, String semester, StudentEnrolment updatedEnrolment) {
        for(int i=0; i<enrolmentsList.size(); i++){
            if(enrolmentsList.get(i).student.id.equals(idStudent) && enrolmentsList.get(i).course.id.equals(idCourse) && enrolmentsList.get(i).semester.equals(semester)){
               enrolmentsList.set(i, updatedEnrolment);
               System.out.println("Successully modify !");
               return 1;
            }
        }
        System.out.println("This enrolment hasn't been created before !");
        return 0;
    }

    public int delete(String idStudent, String idCourse, String semester) {
        for(int i=0; i<enrolmentsList.size(); i++){
            if(enrolmentsList.get(i).student.id.equals(idStudent) && enrolmentsList.get(i).course.id.equals(idCourse) && enrolmentsList.get(i).semester.equals(semester)) {
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
