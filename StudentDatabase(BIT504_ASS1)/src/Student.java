
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
	
	
	// an alternate Full Name getter which is used for formatting
	// to ensure reports display correctly by guaranteeing a field width
	// and error checks excessive name lengths as not to break the tables.
	public StringBuilder getFullName(int fieldWidth)
	{
		StringBuilder fullNameFormatted = new StringBuilder();
		
		// Checks if first name and second name will fit in the field width, if not, displays first initial and last name instead
		if (firstName.length() + lastName.length() < fieldWidth)
			fullNameFormatted.append(firstName + " " + lastName);
		else
			fullNameFormatted.append(firstName.charAt(0) + ". " + lastName);
		
		// Adds whitespace until return string length equals the specified field width (protects table formatting)
		while (fieldWidth - fullNameFormatted.length() > 0)
		{
			fullNameFormatted.append(" ");
		}
		
		// If the full name is still longer than the specified field width then we truncate the stringbuilder
		// to match field width and protect table formatting
		if (fullNameFormatted.length() > fieldWidth)
		{
			fullNameFormatted = fullNameFormatted.delete(fieldWidth - 3, fullNameFormatted.length());
			fullNameFormatted.append("..."); // When displayed, shows that truncation has occurred
		}
		return fullNameFormatted;
	}
	
	// Student ID getter to assist the student removal method in main by linking ID to list index
	public int getID() { return id;	}
}
