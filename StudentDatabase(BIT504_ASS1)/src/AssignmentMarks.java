
public class AssignmentMarks
{
	private String courseName;
	private int assignment1;
	private int assignment2;
	private int assignment3;
	
	public AssignmentMarks(String name, int mark1, int mark2, int mark3)
	{
		this.courseName = name;
		this.assignment1 = mark1;
		this.assignment2 = mark2;
		this.assignment3 = mark3;
	}
	
	public void setMark(int assignmentNumber, int mark)
	{
		switch(assignmentNumber)
		{
			case 1:	{ this.assignment1 = mark; break;}
			case 2:	{ this.assignment2 = mark; break;}
			case 3:	{ this.assignment3 = mark; break;}
		}
	}
	
	public int getMark(int assignmentNumber)
	{
		int mark = 0;
		switch(assignmentNumber)
		{
			case 1:	{ mark = this.assignment1; break; }
			case 2:	{ mark = this.assignment2; break; }
			case 3:	{ mark = this.assignment3; break; }
		}
		return mark;
	}
	
	public int getAverageMark()
	{
		return (this.assignment1 + this.assignment2 + this.assignment3)/3;
	}
	
	public String getGrade(int assignmentNumber)
	{
		String grade = "error";
		switch(assignmentNumber)
		{
			case 1:	{ grade = markToGrade(this.assignment1); break; }
			case 2:	{ grade = markToGrade(this.assignment2); break; }
			case 3:	{ grade = markToGrade(this.assignment3); break; }
		}
		return grade;
	}
	
	public String getAverageGrade()
	{
		return markToGrade(getAverageMark());
	}
	
	public String markToGrade(int mark)
	{
		String grade;
		if (mark >= 95)
			grade = "A+";
		else if (mark >= 90)
			grade = "A";
		else if (mark >= 85)
			grade = "A-";
		else if (mark >= 80)
			grade = "B+";
		else if (mark >= 75)
			grade = "B";
		else if (mark >= 70)
			grade = "B-";
		else if (mark >= 60)
			grade = "C+";
		else if (mark >= 50)
			grade = "C";
		else if (mark >= 40)
			grade = "C-";
		else if (mark >= 0)
			grade = "D";
		else
			grade = "error";
		return grade;
	}
	
	public void setCourseName(String name)
	{
		this.courseName = name;
	}
	
	public String getCourseName()
	{
		return this.courseName;
	}
}
