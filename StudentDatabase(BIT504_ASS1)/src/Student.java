
public class Student
{
	// Student variables
	public int id;
	public String firstName;
	public String lastName;
	public AssignmentMarks mathMarks;
	public AssignmentMarks englishMarks;
	
	// Student constructor
	public Student(int id, String firstName, String lastName)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// Student setters
	private void setID(int studentID) { id = studentID; }
	private void setFirstName(String fName) { firstName = fName; }
	private void setLastName(String lName) { lastName = lName; }
	
	// return the full name of the student
	public String getFullName()
	{
		String fullName = firstName + " " + lastName;
		return fullName;		
	}
	public int getID()
	{
		return id;
	}
}
