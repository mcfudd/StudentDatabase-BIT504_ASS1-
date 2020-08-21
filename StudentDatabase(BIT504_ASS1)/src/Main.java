import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main
{
	private static LinkedList<Student> students;
	private static Scanner scanner = new Scanner(System.in);
	private static boolean runtime = true;
	private static final String HEADER = "\n\n\nID\t\tName\t\t\t\tMaths\tA1\tA2\tA3\tAVRG\t\tEnglish\tA1\tA2\tA3\tAVRG";
	private static final int MIN_MARK = 0, MAX_MARK = 100, FULL_NAME_FIELD_WIDTH = 25, DIVIDER_WIDTH = 132;
	private static int nextID = 1;
	
	public static void main(String[] args)
	{
		students = new LinkedList<Student>();						// initiate the linked list
		if (readFile("studentdata.txt"))							// check and load data file, report error if not found
		{
			while (runtime == true)									// loop for runtime
			{
				displayMenu();
				switch(validInput(1, 5))
				{
					case 1 : { displayReportByMarks(); break; }
					case 2 : { displayReportByGrades(); break; }
					case 3 : { addNewStudent(); break; }
					case 4 : { removeStudent(); break; }
					case 5 : { System.out.println("\n\nOK, goodbye!"); runtime = false; break; }
				}
			}
		}
		else
		{
			System.out.println("The file failed to load!");
		}
	}
	
	
	public static boolean readFile(String filename)
	{
		File file = new File(filename);
		try
		{
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				String[] words = scanner.nextLine().split(",");
				addStudent(		Integer.parseInt(words[0]),			// student ID
								words[1],							// student firstName
								words[2],							// student lastName
								Integer.parseInt(words[3]),			// mathsMark1
								Integer.parseInt(words[4]),			// mathsMark2
								Integer.parseInt(words[5]),			// mathsMark3
								Integer.parseInt(words[6]),			// englishMark1
								Integer.parseInt(words[7]),			// englishMark2
								Integer.parseInt(words[8]));		// englishMark3
				nextID++;
			}
			scanner.close();
		}
		catch (FileNotFoundException event)
		{
			System.out.println("Failed to read file");
		}
		return true;
	}
	
	
	// Manually adds the details for a new student and appends it as an object to the linkedList Students
	private static void addStudent(int id, String firstName, String secondName, int mathsMark1, int mathsMark2, int mathsMark3, int englishMark1, int englishMark2, int englishMark3)
	{
		Student aStudent = new Student(id, firstName, secondName);
		AssignmentMarks mathSubject = new AssignmentMarks("Maths", mathsMark1, mathsMark2, mathsMark3);
		AssignmentMarks englishSubject = new AssignmentMarks("English", englishMark1, englishMark2, englishMark3);
		aStudent.mathMarks = mathSubject;
		aStudent.englishMarks = englishSubject;
		students.add(aStudent);
	}
	
	
	// Displays the menu to the user
	private static void displayMenu()
	{
		System.out.println("\n\nStudent Report System");
		System.out.println("---------------------");
		System.out.println("1) Display student marks");
		System.out.println("2) Display student grades");
		System.out.println("3) Add new student");
		System.out.println("4) Remove student");
		System.out.println("5) Exit\n");
		System.out.print("Please select an option and press enter: ");
	}

	
	// Prints a list of the students with marks and average marks for each course
	private static void displayReportByMarks()
	{
		System.out.println(HEADER);
		divider();	
		for(Student student : students)
		{
			System.out.print(		student.getID() + "\t\t" +
									student.getFullName(FULL_NAME_FIELD_WIDTH) + "\t\t" +
									student.mathMarks.getMark(1) + "\t" +
									student.mathMarks.getMark(2) + "\t" +
									student.mathMarks.getMark(3) + "\t" +
									student.mathMarks.getAverageMark() + "\t\t\t" +
									student.englishMarks.getMark(1) + "\t" +
									student.englishMarks.getMark(2) + "\t" +
									student.englishMarks.getMark(3) + "\t" +
									student.englishMarks.getAverageMark()+ "\n");
			divider();
		}
	}
	
	// method prints a list of the students with grades and average grades for each course
	private static void displayReportByGrades()
	{
		System.out.println(HEADER);
		divider();
		
		for(Student student : students)
		{
			System.out.print(	student.getID() + "\t\t" +
								student.getFullName(FULL_NAME_FIELD_WIDTH) + "\t\t" +
								student.mathMarks.getGrade(1) + "\t" +
								student.mathMarks.getGrade(2) + "\t" +
								student.mathMarks.getGrade(3) + "\t" +
								student.mathMarks.getAverageGrade() + "\t\t\t" +
								student.englishMarks.getGrade(1) + "\t" +
								student.englishMarks.getGrade(2) + "\t" +
								student.englishMarks.getGrade(3) + "\t" +
								student.englishMarks.getAverageGrade() + "\n");
			
			divider();
		}
	}
	
	
	private static void addNewStudent()
	{
		System.out.println("\n\nEnter Student Details:");
		System.out.println("----------------------");
		System.out.print("Student ID (next available is " + nextID + "): ");
		int id = validInput(nextID, Integer.MAX_VALUE);
		checkUniqueID(id);		// error checking to ensure student ID is a unique number
		System.out.print("First Name: ");
		String fName = scanner.next();
		System.out.print("Last Name: ");
		String lName = scanner.next();
		System.out.println("Enter mark for Maths Assignment 1: ");
		int mA1 = validInput(MIN_MARK, MAX_MARK);	// currently draws on the global minimum and maximum marks
													// to ensure the grade conversion remains consistent
													// but can be set manually or changed in the class parameters for all courses
													// if future update is required
		System.out.println("Enter mark for Maths Assignment 2: ");
		int mA2 = validInput(MIN_MARK, MAX_MARK);
		System.out.println("Enter mark for Maths Assignment 3: ");
		int mA3 = validInput(MIN_MARK, MAX_MARK);
		System.out.println("Enter mark for English Assignment 1: ");
		int eA1 = validInput(MIN_MARK, MAX_MARK);
		System.out.println("Enter mark for English Assignment 2: ");
		int eA2 = validInput(MIN_MARK, MAX_MARK);
		System.out.println("Enter mark for English Assignment 3: ");
		int eA3 = validInput(MIN_MARK, MAX_MARK);
		
		addStudent(id, fName, lName, mA1, mA2, mA3, eA1, eA2, eA3);
		nextID++;
		
		System.out.println("New student added successfully");
	}
	
	
	private static void removeStudent()
	{
		int studentIndex = 0;
		int i = 0;
		
		System.out.println("\n\nLIST OF STUDENTS");
		divider();
		
		for(Student student : students)
		{
			System.out.println(student.getID() + "\t\t" + student.getFullName());
			divider();
		}
		System.out.println("0\t\tCANCEL ACTION");
		divider();
		System.out.println("\nPlease enter ID for student to remove or enter 0 to cancel: ");
		int toRemove = validInput(0,students.getLast().id);
		
		for (Student student : students)
		{
			if(student.getID() == toRemove)
				studentIndex = i;
			i++;
		}
		if (toRemove == 0)
		{
			System.out.println("\nAction canceled");
		}
		else
		{
			students.remove(studentIndex);
			System.out.println("Student removed successfully");
		}
	}
	
	private static int checkUniqueID(int id)
	{
		boolean duplicate;
		do
		{
			duplicate = false;
			for(Student student : students)
			{
				if (student.id == id)
					duplicate = true;
			}
			if (duplicate == true)
			{
				System.out.print("The student ID is taken, try again (next available is " + (students.getLast().id + 1) + "): ");
				id = validInput(nextID, Integer.MAX_VALUE);
			}
		} while (duplicate == true);
		return id;
	}
	
	
	// originally the menuInput method, it has been expanded (and renamed) to operate as error checker for all integer inputs
	private static int validInput(int minOption, int maxOption)
	{
		String invalidInput = "This input requires an integer value in the range of " + Integer.toString(minOption) + " - " + Integer.toString(maxOption) + ": ";
		int input = -1;
		
		do {
			try
			{
				input = Integer.parseInt(scanner.next());
				if (input < minOption || input > maxOption)
					System.out.print(invalidInput);
			}
			catch (Exception e) { System.out.print(invalidInput); }
		} while (input < minOption || input > maxOption);
		
		return input;
	}
	private static void divider()
	{
		for(int i = 1; i <= DIVIDER_WIDTH; i++)
			System.out.print("-");
		System.out.println();
	}
	
}
