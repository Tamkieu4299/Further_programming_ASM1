class Student {
    public String id;
    public String name;
    public String birthdate;

    // Constructor
    Student(String id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    //Getter and Setter
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getBirthDate(){
        return this.birthdate;
    }

    public void setBirthDate(String birthdate){
        this.birthdate=birthdate;
    }
}
