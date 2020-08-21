
public class AssignmentMarks
{
	// AssignmentMarks class variables	
	private String courseName;
	private int assignment1, assignment2, assignment3;
	
	
	// AssignmentMarks course constructor
	public AssignmentMarks(String name, int mark1, int mark2, int mark3)
	{
		this.courseName = name;
		this.assignment1 = mark1;
		this.assignment2 = mark2;
		this.assignment3 = mark3;
	}
	
	
	// method for assignment marks, takes an assignment number and mark, then selects the correct assignment by case and sets the value
	public void setMark(int assignmentNumber, int mark)
	{
		switch(assignmentNumber)
		{
			case 1:	{ this.assignment1 = mark; break;}
			case 2:	{ this.assignment2 = mark; break;}
			case 3:	{ this.assignment3 = mark; break;}
		}
	}

	
	// method for retrieving marks, same as setMark it uses switch to determine the correct value to return
	public int getMark(int assignmentNumber)
	{
		switch(assignmentNumber)
		{
			case 1:	{ return this.assignment1; }
			case 2:	{ return this.assignment2; }
			case 3:	{ return this.assignment3; }
			
			// a value of -1 is returned in the event of an error to assist with troubleshooting
			default: { return -1; }
		}
	}
	
	// method calculates and returns the average of the three course marks (condensed for readability)
	public int getAverageMark()	{ return (this.assignment1 + this.assignment2 + this.assignment3)/3; }
	
	
	// course name setter and getter (condensed for readability)
	public void setCourseName(String name) { this.courseName = name; }
	public String getCourseName() { return this.courseName; }
	
	
	// returns a grade for a mark using a switch to determine the correct assignment value
	public String getGrade(int assignmentNumber)
	{
		switch(assignmentNumber)
		{
			case 1:	{ return markToGrade(this.assignment1); }
			case 2:	{ return markToGrade(this.assignment2); }
			case 3:	{ return markToGrade(this.assignment3); }

			// error is returned to assist with troubleshooting in the event that the switch does not execute correctly
			default: { return "error"; }
		}
	}

	
	// method return the grade equivalent of the average course mark by utilises existing methods for simple conversion 
	public String getAverageGrade()	{ return markToGrade(getAverageMark());	} // condensed for readability
	
	
	// simple mark to grade conversion method utilising else if chain (condensed to single line if-then for readability)
	public String markToGrade(int mark)
	{
		if (mark >= 95) return "A+";
		else if (mark >= 90) return "A";
		else if (mark >= 85) return "A-";
		else if (mark >= 80) return "B+";
		else if (mark >= 75) return "B";
		else if (mark >= 70) return "B-";
		else if (mark >= 60) return "C+";
		else if (mark >= 50) return "C";
		else if (mark >= 40) return "C-";
		else if (mark >= 0)  return "D";
		else return "error";
	}
}
