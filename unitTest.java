import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import org.junit.Test;

public class unitTest {
    public static ManageSystem manager = new ManageSystem();
    public static EnrolmentManagement enrolSystem  = new EnrolmentManagement();
    InputStream sysInBackup = System.in;

    
    @Test
    public void testMethod_HandledData() throws Exception{
        System.out.println("Test handledData()");
        int linesOfDefaultCSV = 15;
        InputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        assertEquals(linesOfDefaultCSV, manager.handleData().size());    
        System.setIn(sysInBackup);
    }

    @Test
    public void testMethod_AllEnrolments() throws Exception{
        System.out.println("Test allEnrolments()");
        int linesOfDefaultCSV = 15;
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        assertEquals(linesOfDefaultCSV, enrolSys.getAll().size());
    }

    @Test
    public void testMethod_SearchStudentById() throws Exception{
        System.out.println("Test searchStudentById()");
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        Student student = manager.searchStudentById(enrolSys, "S103821");
        assertEquals("S103821", student.id);
        assertEquals("Son Minh Doan", student.name);
        assertEquals("11/15/1999", student.birthdate);

    }

    @Test
    public void testMethod_SearchCourseById() throws Exception{
        System.out.println("Test searchCourseById()");
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        Course course = manager.searchCourseById(enrolSys, "COSC4030");
        assertEquals("COSC4030", course.id);
        assertEquals("Theory of Computation", course.name);
        assertEquals(5, course.credits);
    }

    @Test
    public void testMethod_studentIdInput() throws Exception{
        System.out.println("Test studentIdInput()");
        Scanner sc = new Scanner(System.in);
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        in = new ByteArrayInputStream("S101312".getBytes());
        System.setIn(in);
        assertEquals("S101312", manager.studentIDInput(new Scanner(System.in), enrolSys));
    }

    @Test
    public void testMethod_courseIdInput() throws Exception{
        System.out.println("Test courseIdInput()");
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        String str = "COSC4030";
        in = new ByteArrayInputStream(str.getBytes());
        System.setIn(in);
        assertEquals("COSC4030", manager.courseIDInput(new Scanner(System.in),enrolSys));
    }

    @Test
    public void testMethod_semesterInput() throws Exception{
        System.out.println("Test semesterInput()");
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        in = new ByteArrayInputStream("2022A".getBytes());
        System.setIn(in);
        assertEquals("2022A", manager.semesterInput(new Scanner(System.in),enrolSys));
    }
    
    @Test
    public void testMethod_CoursesOneStudent() throws Exception{
        System.out.println("Test CourseOneStudent()");
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        File filePath = new File("./data/S1013122020C.csv");
        manager.CoursesOneStudent(enrolSys, "S101312", "2020C");
        assertTrue(filePath.exists());    
    }

    @Test
    public void testMethod_StudentsOneCourse() throws Exception{
        System.out.println("Test studentsOneCourse()");
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        File filePath = new File("./data/COSC40302020C.csv");
        manager.StudentsOneCourse(enrolSys, "COSC4030", "2020C");
        assertTrue(filePath.exists());
    }
    
    @Test
    public void testMethod_CoursesOneSemester() throws Exception{
        System.out.println("Test CourseOneSemester()");
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        File filePath = new File("./data/2020C.csv");
        manager.CoursesOneSemester(enrolSys,"2020C");
        assertTrue(filePath.exists());
    }

    @Test
    public void testMethod_EnrollAStudent() throws Exception{
        System.out.println("Test enrollAStudent()");
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        String str = "S103821" + System.lineSeparator()
        		+ "COSC4030" + System.lineSeparator()
        		+ "2022B";
        in = new ByteArrayInputStream(str.getBytes());
        System.setIn(in);
        int res = manager.enrollAStudent(enrolSys);
        assertEquals(1, res);
    }

    @Test
    public void testMethod_UpdateAnEnrolment() throws Exception{
        System.out.println("Test enrollAStudent()");
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        String str = "S103821" + System.lineSeparator()
				+ "2021A" + System.lineSeparator()
				+ "2" + System.lineSeparator()
				+ "PHYS1230";
        in = new ByteArrayInputStream(str.getBytes());
        System.setIn(in);
        int res = manager.updateAnEnrolment(enrolSys);
        assertEquals(1, res);
    }

    @Test
    public void testMethod_ModifyAnEnrolment() throws Exception{
        System.out.println("Test modifyAnEnrolment()");
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        String str = "S103821" + System.lineSeparator()
				+ "PHYS1230" + System.lineSeparator()
				+ "2021A" + System.lineSeparator()
				+ "3" + System.lineSeparator()
				+ "2022B";
        in = new ByteArrayInputStream(str.getBytes());
        System.setIn(in);
        int res = manager.modifyEnrolment(enrolSys);
        assertEquals(1, res);
    }
}
