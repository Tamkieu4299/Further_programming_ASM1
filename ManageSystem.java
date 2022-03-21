import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

class ManageSystem{
    public static List<List<String>> handleData() throws Exception{
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/default.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }
    
    public static Map<Student, StudentEnrolment> allEnrolments(List<List<String>> dataHandled){
        Map<Student, StudentEnrolment> records = new HashMap<>();
        for(List<String> al: dataHandled){
            // Student class
            String idStudent = al.get(0);
            String nameStudent = al.get(1);
            String birthdayStudent = al.get(2);
            Student student = new Student(idStudent,nameStudent,birthdayStudent);

            // Course class
            String idCourse = al.get(3);
            String nameCourse = al.get(4);
            int creditsCourse = Integer.parseInt(al.get(5));
            Course course = new Course(idCourse,nameCourse,creditsCourse);

            //StudentEnrolment class
            String semester = al.get(6);
            StudentEnrolment enrolment = new StudentEnrolment(student, course, semester);

            if(records.containsKey(student)) records.get(student).enrolmentsList.add(enrolment);
            records.put(student, enrolment);
        }
        return records;
    }

    public static Map<String,List<Course>> CoursesOneStudent(Map<Student, StudentEnrolment> totalEnrolments , String idStudent){
        Map<String, List<Course>> records = new HashMap<>();
        for(Student student : totalEnrolments.keySet()){
            if(idStudent==student.id) {
                StudentEnrolment se = totalEnrolments.get(student);
                if(!records.containsKey(se.semester)) {
                    List<Course> al = new ArrayList<>();
                    al.add(se.course);
                    records.put(se.semester, al);
                }
                else records.get(se.semester).add(se.course);
            } 
        }
        return records;
    }

    public static Map<String, List<Student>> StudentsOneCourse(Map<Student, StudentEnrolment> totalEnrolments, String idCourse){
        Map<String, List<Student>> records = new HashMap<>();
        for(Map.Entry<Student, StudentEnrolment> pair : totalEnrolments.entrySet()){
            if()
        }
    }

    public boolean checkExistEnrolment(){

    }

	public static void main(String[] args) {
		
	}
}
