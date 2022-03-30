import java.util.List;

interface StudentEnrolmentManager{

    public int add(StudentEnrolment enrolment);
    public int update(String idStudent, String idCourse, String semester, StudentEnrolment updatedEnrolment);
    public int delete(String idStudent, String idCourse, String semester);
    public int getOne(Student student, Course course, String semester);
    public List<StudentEnrolment> getAll();
}
