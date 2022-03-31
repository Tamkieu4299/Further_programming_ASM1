class StudentEnrolment{
    Student student;
    Course course;
    String semester;

    // Constructor
    StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    // Getter and Setter
    public Student getStudent(){
        return this.student;
    }

    public void setStudent(Student student){
        this.student=student;
    }

    public Course getCourse(){
        return this.course;
    }

    public void setCourse(Course course){
        this.course=course;
    }

    public String getSemester(){
        return this.semester;
    }

    public void setSemester(String semester){
        this.semester=semester;
    }
}

