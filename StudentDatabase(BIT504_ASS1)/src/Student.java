
public class Student
{
	// Student class variables
	public int id;
	public String firstName, lastName;
	public AssignmentMarks mathMarks, englishMarks;
	
	
	// Student constructor
	public Student(int id, String firstName, String lastName)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	// getter concatenates first name, last name and returns the string
	public String getFullName() { return firstName + " " + lastName; }
	
	
	// an alternate Full Name getter which is used for formatting to ensure reports display correctly by guaranteeing a field width
	public StringBuilder getFullName(int fieldWidth)
	{
		StringBuilder fullNameFormatted = new StringBuilder();
		fullNameFormatted.append(firstName + " " + lastName);
		int i = fieldWidth - fullNameFormatted.length();
		while (i > 0)
		{
			fullNameFormatted.append(" ");
			i--;
		}
		return fullNameFormatted;
	}
	
	// Student ID getter to assist the student removal method in main by linking ID to list index
	public int getID() { return id;	}
}
