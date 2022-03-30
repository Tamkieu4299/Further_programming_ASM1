class Student {
    public String id;
    public String name;
    public String birthdate;

    Student(String id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getBirthDate(){
        return this.birthdate;
    }
}
