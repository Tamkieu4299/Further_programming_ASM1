class Course{
	String id;
	String name;
	int credits;

	// Constructor
	Course(String id, String name, int credits){
		this.name=name;
		this.id=id;
		this.credits=credits;
	}

	// Getter and Setter
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

    public int getCredits(){
        return this.credits;
    }

	public void setCredits(int credits){
		this.credits = credits;
	}
}