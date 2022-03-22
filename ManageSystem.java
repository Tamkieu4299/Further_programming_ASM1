import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;

class ManageSystem {
    public static List<List<String>> handleData() throws Exception{
        Scanner in  = new Scanner(System.in);

        //Ask user for inputing the file
        System.out.println("Please enter a file or the system will render the DEFAULT FILE");
        String response = in.nextLine();
        in.close();
        String fileName = response == "" ? "./data/default.csv" : "./data/"+response+".csv";   

        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }

    public static void allEnrolments(EnrolmentManagement manager, List<List<String>> dataHandled){
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

            // Add an enrolment to the management system list
            manager.add(enrolment);
        }
    }

    public static Map<String,List<Course>> CoursesOneStudent(EnrolmentManagement manager, String idStudent, String semester) throws Exception{
        Map<String, List<Course>> records = new HashMap<>();
        List<StudentEnrolment> enrolmentsList = manager.getAll();

        for(StudentEnrolment se: enrolmentsList){
            if(se.student.id == idStudent){
                if(records.containsKey(se.semester)) records.get(se.semester).add(se.course);
                else {
                    List<Course> courses = new ArrayList<>();
                    courses.add(se.course);
                    records.put(se.semester, courses);
                }
            }
        }

        // Generate data to CSV by creat a list of courses
        List<String> coursesName = new ArrayList<>();
        for(Course course : records.get(semester)) coursesName.add(course.id+" : "+ course.name);

        String filePath = "./data/"+idStudent+semester+".csv";
        File file = new File(filePath);
        FileWriter csvWriter = new FileWriter(file);

        for(String course: coursesName) csvWriter.append(course+"\n");
        csvWriter.flush();
        csvWriter.close();
        return records;
    }

    public static Map<String, List<Student>> StudentsOneCourse(EnrolmentManagement manager, String idCourse, String semester) throws Exception{
        Map<String, List<Student>> records = new HashMap<>();
        List<StudentEnrolment> enrolmentsList = manager.getAll();

        for(StudentEnrolment se: enrolmentsList){
            if(se.course.id == idCourse){
                if(records.containsKey(se.semester)) records.get(se.semester).add(se.student);
                else {
                    List<Student> students = new ArrayList<>();
                    students.add(se.student);
                    records.put(se.semester, students);
                }
            }
        }

        // Generate data to CSV by creat a list of courses
        List<String> studentsName = new ArrayList<>();
        for(Student student : records.get(semester)) studentsName.add(student.id+" : "+student.name);

        String filePath = "./data/"+idCourse+semester+".csv";
        File file = new File(filePath);
        FileWriter csvWriter = new FileWriter(file);

        for(String student: studentsName) csvWriter.append(student+"\n");
        csvWriter.flush();
        csvWriter.close();
        return records;
    }

    public static Map<String, List<Course>> CoursesOneSemester(EnrolmentManagement manager, String semester) throws Exception{
        Map<String, List<Course>> records = new HashMap<>();
        List<StudentEnrolment> enrolmentsList = manager.getAll();

        for(StudentEnrolment se: enrolmentsList){
            if(se.semester == semester){
                if(records.containsKey(se.semester)) records.get(se.semester).add(se.course);
                else {
                    List<Course> courses = new ArrayList<>();
                    courses.add(se.course);
                    records.put(se.semester, courses);
                }
            }
        }

        List<String> coursesName = new ArrayList<>();
        for(Course course : records.get(semester)) coursesName.add(course.id+" : "+course.name);

        String filePath = "./data/"+semester+".csv";
        File file = new File(filePath);
        FileWriter csvWriter = new FileWriter(file);

        for(String course: coursesName) csvWriter.append(course+"\n");
        csvWriter.flush();
        csvWriter.close();
        return records;
    }

    // Main program starts here
    public static void main(String[] args) throws Exception {

        // Handle data from csv file from user
        List<List<String>> dataHandled = new ArrayList<>();
        dataHandled = handleData();

        // Initialize Class EnrolmentManagement
        EnrolmentManagement manager = new EnrolmentManagement();

        // Populate data into enrolmentsList of manager
        allEnrolments(manager, dataHandled);

        // Menu functions
        System.out.println("Welcome to the Enrolment Management Application");
        System.out.println("Enter a number to go ahead: "
                +"\n1 => Enroll a student"
                +"\n2 => Update an enrolment"
                +"\n3 => View ALL courses of ONE student in ONE semester"
                +"\n4 => View ALL students of ONE course in ONE semester"
                +"\n5 => View ALL courses offered in ONE semester");
    }
}
