class Course{
	String id;
	String name;
	int credits;

	Course(String id, String name, int credits){
		this.name=name;
		this.id=id;
		this.credits=credits;
	}

	public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getCredits(){
        return this.credits;
    }
}