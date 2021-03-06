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
import java.util.regex.Pattern;

class ManageSystem {	
    // Handle all the data from CSV File
    public static List<List<String>> handleData(){
        Scanner in  = new Scanner(System.in);

        //Ask user for inputing the file
        System.out.println("Please enter a file or type \"default\" to render the DEFAULT FILE");
        String response = in.nextLine();
        System.out.println(response);
        String fileName = response.equals("default") ? "./data/default.csv" : "./data/"+response+".csv";   
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        catch(Exception e){
            System.out.println("File does not exist !");
            return handleData();
        }
        return records;
    }
    
    // Populate all data into manager class
    public static void allEnrolments(EnrolmentManagement manager, List<List<String>> dataHandled){
        List<Student> studentsList = manager.studentsList;
        List<Course> coursesList = manager.coursesList;
        List<String> semestersList = manager.semestersList;
        
        List<String> checkCourses  = new ArrayList<>();
        List<String> checkStudents = new ArrayList<>();

        for(List<String> al: dataHandled){
            // Student class
            String idStudent = al.get(0);
            String nameStudent = al.get(1);
            String birthdayStudent = al.get(2);
            Student student = new Student(idStudent,nameStudent,birthdayStudent);
            // Add student into manager list
            if(!checkStudents.contains(idStudent)) studentsList.add(student);
            checkStudents.add(idStudent);

            // Course class
            String idCourse = al.get(3);
            String nameCourse = al.get(4);
            int creditsCourse = Integer.parseInt(al.get(5));
            Course course = new Course(idCourse,nameCourse,creditsCourse);
            // Add course into manager list
            if(!checkCourses.contains(idCourse)) coursesList.add(course);
            checkCourses.add(idCourse);
            // StudentEnrolment class
            String semester = al.get(6);
            // Add semester into manager list
            if(!semestersList.contains(semester)) semestersList.add(semester);

            StudentEnrolment enrolment = new StudentEnrolment(student, course, semester);
            // Add an enrolment to the management system list
            manager.add(enrolment);
        }
    }

    // Search for student 
    public static Student searchStudentById(EnrolmentManagement manager, String id){
        List<Student> studentsList = manager.studentsList;
        for(Student student: studentsList){
            if(student.id.equals(id)) return student;
        }
        return null;
    }

    // Search for course 
    public static Course searchCourseById(EnrolmentManagement manager, String id){
        List<Course> coursesList = manager.coursesList;
        for(Course course: coursesList){
            if(course.id.equals(id)) return course;
        }
        return null;
    }

    // Ask for student id function
    public static String studentIDInput(Scanner sc ,EnrolmentManagement manager){
        // Data List from manager
        List<Student> studentsList = manager.studentsList;
//        Scanner sc = new Scanner(System.in);

        // Student
        String idStudent = "";
        boolean studentIncluded = false;
        while(!studentIncluded){
            System.out.println("Input STUDENT ID");
            String str = sc.nextLine().strip();
            for(Student student: studentsList){
                if(student.id.equals(str)) {
                    studentIncluded = true;
                    idStudent = str;
                    break;
                }
            }
        }
        return idStudent;
    }

    // Ask for course id function
    public static String courseIDInput(Scanner sc, EnrolmentManagement manager){
        // Data List from manager
        List<Course> coursesList = manager.coursesList;
//        Scanner sc = new Scanner(System.in);

        // Course
        String idCourse = "";
        boolean courseIncluded = false;
        while(!courseIncluded){
            System.out.println("Input COURSE ID");
            String str = sc.nextLine().strip();
            for(Course course: coursesList){
                if(course.id.equals(str)) {
                    courseIncluded = true;
                    idCourse = str;
                    break;
                }
            }
        }
        System.out.println(idCourse);
        return idCourse;
    }

    // Ask for semester function
    public static String semesterInput(Scanner sc, EnrolmentManagement manager){
//        Scanner sc = new Scanner(System.in);

        // Semester
        String semester = "";
        boolean semesterIncluded = false;
        while(!semesterIncluded){
            System.out.println("Input SEMESTER");
            String str = sc.nextLine().strip().toUpperCase();
            String pattern = "\\d{4}[A-C]";
            if(Pattern.matches(pattern, str)){
                semesterIncluded = true;
                semester = str;
                break;
            }
        }
        return semester;
    }

    // Get Courses of one Student and write to CSV
    public static Map<String,List<Course>> CoursesOneStudent(EnrolmentManagement manager, String idStudent, String semester) throws Exception{
        Map<String, List<Course>> records = new HashMap<>();
        List<StudentEnrolment> enrolmentsList = manager.getAll();

        for(StudentEnrolment se: enrolmentsList){
            if(se.student.id.equals(idStudent)){
                if(records.containsKey(se.semester)) records.get(se.semester).add(se.course);
                else {
                    List<Course> courses = new ArrayList<>();
                    courses.add(se.course);
                    records.put(se.semester, courses);
                }
            }
        }

        if(records.get(semester)!=null){
            // Generate data to CSV by creat a list of courses
            List<String> coursesName = new ArrayList<>();
            for(Course course : records.get(semester)) coursesName.add(course.id+" : "+ course.name);
            String filePath = "./data/"+idStudent+semester+".csv";
            File file = new File(filePath);
            FileWriter csvWriter = new FileWriter(file);

            for(String course: coursesName) {
                System.out.println(course);
                csvWriter.append(course+"\n");
            }
            csvWriter.flush();
            csvWriter.close();
        }
        else System.out.println("This student didn't enroll any courses in this semester");
        return records;
    }

    // Get Students of one Course and write to CSV
    public static Map<String, List<Student>> StudentsOneCourse(EnrolmentManagement manager, String idCourse, String semester) throws Exception{
        Map<String, List<Student>> records = new HashMap<>();
        List<StudentEnrolment> enrolmentsList = manager.getAll();

        for(StudentEnrolment se: enrolmentsList){
            if(se.course.id.equals(idCourse)){
                if(records.containsKey(se.semester)) records.get(se.semester).add(se.student);
                else {
                    List<Student> students = new ArrayList<>();
                    students.add(se.student);
                    records.put(se.semester, students);
                }
            }
        }
		
        if(records.get(semester)!=null){
            // Generate data to CSV by creat a list of courses
            List<String> studentsName = new ArrayList<>();
            for(Student student : records.get(semester)) studentsName.add(student.id+" : "+student.name);
          
            String filePath = "./data/"+idCourse+semester+".csv";
            File file = new File(filePath);
            FileWriter csvWriter = new FileWriter(file);

            for(String student: studentsName) {
                csvWriter.append(student+"\n");
                System.out.println(student);
            }
            csvWriter.flush();
            csvWriter.close();
        }
        else System.out.println("This course has no students in this semester !");
        return records;
    }

    // Get Courses of one Semester and write to CSV
    public static Map<String, List<Course>> CoursesOneSemester(EnrolmentManagement manager, String semester) throws Exception{
        Map<String, List<Course>> records = new HashMap<>();
        List<StudentEnrolment> enrolmentsList = manager.getAll();
        List<String> checkCourses = new ArrayList<>();

        for(StudentEnrolment se: enrolmentsList){
            if(se.semester.equals(semester)){
                if(!checkCourses.contains(se.course.id)){
                    if(records.containsKey(se.semester)) {
                        records.get(se.semester).add(se.course);              
                    }
                    else {
                        List<Course> courses = new ArrayList<>();
                        courses.add(se.course);
                        records.put(se.semester, courses);
                    }
                }
                checkCourses.add(se.course.id);  
            }
        }

        if(records.get(semester)!=null){
            List<String> coursesName = new ArrayList<>();
            for(Course course : records.get(semester)) coursesName.add(course.id+" : "+course.name);
        
            String filePath = "./data/"+semester+".csv";
            File file = new File(filePath);
            FileWriter csvWriter = new FileWriter(file);

            for(String course: coursesName) {
                csvWriter.append(course+"\n");
                System.out.println(course);
            }
            csvWriter.flush();
            csvWriter.close();
        }
        else System.out.println("This semester has no enrolment !");
        return records;
    }

    // Enroll a student function
    public static int enrollAStudent(EnrolmentManagement manager){
    	Scanner sc = new Scanner(System.in);
        // Student
        String idStudent = studentIDInput(sc, manager);
        // Course
        String idCourse = courseIDInput(sc, manager);
        // Semester
        String semester =semesterInput(sc, manager);

        // Create an enrolment
        Student student = searchStudentById(manager, idStudent);
        Course course = searchCourseById(manager, idCourse);
        StudentEnrolment newEnrolment = new StudentEnrolment(student, course, semester);
        return manager.add(newEnrolment);
    }

    // Update an enrolment function
    public static int updateAnEnrolment(EnrolmentManagement manager) throws Exception{

        Scanner sc = new Scanner(System.in);

        // Student
        String idStudent = studentIDInput(sc,manager);

        // Semester
        String semester = semesterInput(sc,manager);

        // Print all the courses ENROLED to this student in a semester
        Map<String, List<Course>> coursesOneStudent = CoursesOneStudent(manager,idStudent, semester);

        // Ask the user to ADD or DELETE course
        System.out.println("Press 1: ADD course"+ "\nPress 2: DELETE course");
        int response = sc.nextInt();

        // Add course
        if(response==1){
            String idCourse = courseIDInput(sc,manager);
            
            // Create an enrolment
            Student student = searchStudentById(manager, idStudent);
            Course course = searchCourseById(manager, idCourse);
            StudentEnrolment newEnrolment = new StudentEnrolment(student, course, semester);
            return manager.add(newEnrolment);
        }

        // Delete course
        else if(response==2) {
            if(coursesOneStudent.get(semester)==null) return 0;
            String idCourse = courseIDInput(sc, manager);

            // Delete an enrolment
            return manager.delete(idStudent, idCourse, semester);
        }
        System.out.println("Invalid Options !");
        return 0;
    }

    // Modify an enrolment function
    public static int modifyEnrolment(EnrolmentManagement manager) throws Exception{

        Scanner sc = new Scanner(System.in);

        // Ask user for idStudent, semester
        // Student
        String idStudent = studentIDInput(sc, manager);

        // Course
        String idCourse = courseIDInput(sc,manager);
        
        // Semester
        String semester = semesterInput(sc,manager);

        // Ask user what type of information he wants to modify
        System.out.println("Which information you want to change: "
                +"\n1 => Student"
                +"\n2 => Course"
                +"\n3 => Semester");
        int response = sc.nextInt();
        if(response==1){
            // Student
            String newidStudent = studentIDInput(sc,manager);

            // Create an enrolment
            Student student = searchStudentById(manager, newidStudent);
            Course course = searchCourseById(manager, idCourse);
            StudentEnrolment newEnrolment = new StudentEnrolment(student, course, semester);

            return manager.update(idStudent, idCourse, semester, newEnrolment);
        }

        else if(response==2){
            // Course
            String newidCourse = courseIDInput(sc,manager);

           // Create an enrolment
           Student student = searchStudentById(manager, idStudent);
           Course course = searchCourseById(manager, newidCourse);
           StudentEnrolment newEnrolment = new StudentEnrolment(student, course, semester);

           return manager.update(idStudent, idCourse, semester, newEnrolment);
        }

        else if(response==3){
            // Semester
            String newsemester = semesterInput(sc,manager);

            // Create an enrolment
            Student student = searchStudentById(manager, idStudent);
            Course course = searchCourseById(manager, idCourse);
            StudentEnrolment newEnrolment = new StudentEnrolment(student, course, newsemester);

            return manager.update(idStudent, idCourse, semester, newEnrolment);
        }
        System.out.println("Invalid Options !");
        return 0;
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
        System.out.println("======== Welcome to the Enrolment Management Application ========");
        // Start scanner
        Scanner in = new Scanner(System.in);
        // User need to enroll a student
        while(true){
            System.out.println("Enter a number to go ahead: "
                +"\n0 => Enroll a student"
                +"\n1 => Add/ Delete an enrolment"
                +"\n2 => Modify an enrolment"
                +"\n3 => CSV Output ALL courses of ONE student in ONE semester"
                +"\n4 => CSV Output ALL students of ONE course in ONE semester"
                +"\n5 => CSV Output ALL courses offered in ONE semester"
                +"\n6 => Quit");
            int response = in.nextInt();
			
			// User need to add Enrolment
            if(response==0){
                int process = enrollAStudent(manager);
                while(process!=1){
                    process=enrollAStudent(manager);
                }
            }
            
            // User need to add/delete an enrolment
            else if(response==1){
                int process = updateAnEnrolment(manager);
                while(process!=1){
                    process = updateAnEnrolment(manager);
                }
            }
            
            // User need to update an enrolment
            else if(response==2){
                int process = modifyEnrolment(manager);
                while(process!=1){
                    process = updateAnEnrolment(manager);
                }
            }

            // User need to CSV all Courses of a Student
            else if(response==3){
                // Student
                String idStudent = studentIDInput(in,manager);

                // Semester
                String semester = semesterInput(in,manager);

                Map<String, List<Course>> coursesOneStudent = CoursesOneStudent(manager, idStudent, semester );
            }

            // User need to CSV all Students of one Course
            else if(response==4){
                // Ask user for idCourse, semester
                // Course
                String idCourse = courseIDInput(in, manager);

                // Semester
                String semester = semesterInput(in, manager);
                
                Map<String, List<Student>> studentsOneStudent = StudentsOneCourse(manager, idCourse, semester);
            }

            // User need to CSV all Courses offered in one semester
            else if(response==5){
                // Semester
                String semester = semesterInput(in, manager);

                Map<String, List<Course>> coursesOneSemester = CoursesOneSemester(manager,semester);
            }
            else if(response==6) {
                System.out.println("Stay Safe !!! Good Bye");
                break;
            }
        }
		in.close();
    }
}
        