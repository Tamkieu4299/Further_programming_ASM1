import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class unitTest {
    public static ManageSystem manager = new ManageSystem();
    public static EnrolmentManagement enrolSystem  = new EnrolmentManagement();

    private ByteArrayOutputStream setStreams(String input){
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream toReturn = new ByteArrayOutputStream();
        System.setOut(new PrintStream(toReturn));
        return toReturn;
    }
    
    @Test
    public void testMethod_HandledData() throws Exception{
        System.out.println("Test handledData()");
        int linesOfDefaultCSV = 15;
        InputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        assertEquals(linesOfDefaultCSV, manager.handleData().size());    
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
        assertEquals("11/15/199", student.birthdate);

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
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        in = new ByteArrayInputStream("S101312".getBytes());
        assertEquals("S101312", manager.studentIDInput(enrolSys));
    }

    @Test
    public void testMethod_courseIdInput() throws Exception{
        System.out.println("Test courseIdInput()");
        EnrolmentManagement enrolSys = new EnrolmentManagement();
        ByteArrayInputStream in = new ByteArrayInputStream("default".getBytes());
        System.setIn(in);
        List<List<String>> dataHandled = manager.handleData();
        manager.allEnrolments(enrolSys, dataHandled);
        in = new ByteArrayInputStream("COSC4030".getBytes());
        assertEquals("COSC4030", manager.courseIDInput(enrolSys));
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
        assertEquals("2022A", manager.semesterInput(enrolSys));
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
        ByteArrayOutputStream output = setStreams("S103821 COSC4030 2022B ");
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
        ByteArrayOutputStream output = setStreams("S103821 2021A 2 PHYS1230 ");
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
        ByteArrayOutputStream output = setStreams("S103821 PHYS1230 2021A 2 COSC3321 ");
        int res = manager.updateAnEnrolment(enrolSys);
        assertEquals(1, res);
    }
}
