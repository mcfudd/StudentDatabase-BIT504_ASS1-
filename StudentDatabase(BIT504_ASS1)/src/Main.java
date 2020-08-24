import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main
{
	// Create objects
	private static LinkedList<Student> students = new LinkedList<Student>();
	private static Scanner scanner = new Scanner(System.in);
	
	// Initialising global parameters
	private static boolean RUNTIME = true; // Sets the program run-state (true=on and false=off)
	private static final int MIN_MARK = 0, MAX_MARK = 100; // Sets the global max and min valid assignment results to assist error checking
	private static final int FULL_NAME_FIELD_WIDTH = 25, DIVIDER_WIDTH = 125; // Formatting parameters to ensure tables function correctly
	private static int NEXT_ID = 1; // Tracks where unique identifier student ID is up
	
	public static void main(String[] args)
	{
		//students = new LinkedList<Student>();					// Initiate the linked list
		if (readFile("studentdata.txt"))						// Check and load data file, report error if not found
		{
			while (RUNTIME == true)								// Loop for runtime
			{
				displayMenu();
				switch(validInput(1, 5))						// Replaces the selectMenuOption() method and is used for
																// validating all program inputs and in this case gathers menu input
				{
					case 1 : { displayReportByMarks(); break; }
					case 2 : { displayReportByGrades(); break; }
					case 3 : { addNewStudent(); break; }
					case 4 : { removeStudent(); break; }
					case 5 : { System.out.println("\n\nOK, goodbye!"); RUNTIME = false; break; }
				}
			}
		}
		else { System.out.println("The file failed to load!"); }
	}
	
	
	// readFile() method is used to open and parse the data file and create the program objects
	public static boolean readFile(String filename)
	{
		File file = new File(filename);
		try
		{
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				// Parses the input file for commas to determine elements and creates an object per line
				// Code line has been split into a line per parameter in this case to make identification easier
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
				NEXT_ID++;
			}
			scanner.close();
		}
		catch (FileNotFoundException event)
		{
			System.out.println("Failed to read file");
		}
		return true;
	}
	
	
	// Adds the details for a new student and appends it as an object to the linkedList Students
	private static void addStudent(int id, String firstName, String secondName, int mathsMark1, int mathsMark2, int mathsMark3, int englishMark1, int englishMark2, int englishMark3)
	{
		// Declare and initialise a new student object
		Student aStudent = new Student(id, firstName, secondName); 
		
		// Declare and initialise two new asignementMarks objects
		AssignmentMarks mathSubject = new AssignmentMarks("Maths", mathsMark1, mathsMark2, mathsMark3); 
		AssignmentMarks englishSubject = new AssignmentMarks("English", englishMark1, englishMark2, englishMark3);
		
		// Assign the assignmentMarks objects to the new student
		aStudent.mathMarks = mathSubject;
		aStudent.englishMarks = englishSubject;
		
		// Append the new student object to the linkedList
		students.add(aStudent);
	}
	
	
	// Displays the menu to the user
	private static void displayMenu()
	{
		// Println() line has been split into separate lines to represent formatting
		System.out.println(		"\n\nStudent Report System\n" +
								"---------------------\n" +
								"1) Display student marks\n" +
								"2) Display student grades\n" +
								"3) Add new student\n" +
								"4) Remove student\n" +
								"5) Exit\n");
		
		System.out.print("Please select an option and press enter: "); // input request
	}

	
	// Prints a list of the students with marks and average marks for each course
	private static void displayReportByMarks()
	{
		header();
		divider();	
		for(Student student : students)
		{
			// Println code has been split in this case to assist readability and make editing easier
			System.out.print(		student.getID() + "\t\t" +
									student.getFullName(FULL_NAME_FIELD_WIDTH) + "\t" +
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
		header();
		divider();
		
		for(Student student : students)
		{
			// Println code has been split in this case to assist readability and make editing easier
			System.out.print(	student.getID() + "\t\t" +
								student.getFullName(FULL_NAME_FIELD_WIDTH) + "\t" +
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
	
	
	// Manually collects a new set of student data and appends it to the linkedList
	private static void addNewStudent()
	{
		System.out.println("\n\nEnter Student Details:");
		System.out.println("----------------------");
		System.out.print("Student ID (next available is " + NEXT_ID + ")");
		int id = validInput(NEXT_ID, Integer.MAX_VALUE);
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
		
		// Confirm user input or gives opportunity to cancel the operation
		System.out.print("Are the student details correct and do you wish to proceed? (y): ");
		String proceed = scanner.next();
		if (proceed.contains("y") || proceed.contains("Y"))
		{
			addStudent(id, fName, lName, mA1, mA2, mA3, eA1, eA2, eA3);
			System.out.println("\n\nStudent added sucesfully");
			NEXT_ID++;
		}
		else
			System.out.println("\n\nAction canceled");
	}
	
	// Presents the current list of students and allows removal by selecting the appropriate ID
	private static void removeStudent()
	{
		// Parameters used in this method, student index refers to the linkedList index, i is loop counter, toRemove is the user input
		int studentIndex = -1, i = 0, toRemove;
		
		// Presents the list of students for the user to choose from 
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
		toRemove = validInput(0,students.getLast().id);
		
		// Runs the operation but first checks if the user want to cancel the operation in case they accidentally ended up here	
		if (toRemove == 0)
		{
			System.out.println("\nAction canceled");
		}
		else
		{
			// Searches for the correct linkList index according to student ID
			for (Student student : students)
			{
				if(student.getID() == toRemove)
					studentIndex = i;
				i++;
			}
			
			// Check that the student record exists, i initialised to -1 to create a catch (doesn't increment) if no matching record is found
			if (studentIndex == -1)
				System.out.println("No such student number exists, action canceled");
			else
			{
				students.remove(studentIndex);
				System.out.println("Student removed successfully");
			}
		}
	}
	
	
	// Dedicated method to confirm that manual student ID entry is always unique
	private static int checkUniqueID(int id)
	{
		boolean duplicate;
		
		do // do loop forces user to keep entering an id until a valid one is selected
		{
			duplicate = false;
			for(Student student : students) // searches through the linked list for a duplicate student ID
			{
				if (student.id == id)
				{
					duplicate = true;
					System.out.print("The student ID is taken, try again (next available is " + (students.getLast().id + 1) + "): "); // error message and provides the user with guidance
					id = validInput(NEXT_ID, Integer.MAX_VALUE); // collects new input
				}
			}
		} while (duplicate == true); // once input passes unique test, terminates loop
		return id;
	}
	
	
	// Originally this was the selectMenuOption() method, it has been expanded (and renamed) to operate as error checker for all integer inputs
	// Requires a minimum and maximum valid input from the caller
	private static int validInput(int minOption, int maxOption)
	{
		//Error message string is declared and initialised here to keep the code within the loop cleaner, error message also includes input guidance
		String invalidInput = "This input requires an integer value in the range of " + Integer.toString(minOption) + " - " + Integer.toString(maxOption) + ": ";

		int input = 0; // declared and initialised outside loop to ensure return statement works
		do // do loop tests that input is within the required range
		{
			try // try-catch used to test that input is an integer
			{
				input = Integer.parseInt(scanner.next());
				if (input < minOption || input > maxOption)
					System.out.print(invalidInput);
			}
			catch (Exception e) { System.out.print(invalidInput); }
		} while (input < minOption || input > maxOption);
		return input;
	}
	
	
	// Format setup for the table header (displayMarks and displayGrades)
	private static void header()
	{
		// Stringbuilder is used to set the width of the name field to ensure table lines up
		// since tabs result in errors for very short and very long student names FULL_NAME_FIELD_WIDTH is used here
		// to keep consistency between all methods. "Name" is initialised and the spaces appended until the string length
		// is equal to the specified width 
		StringBuilder nameHeader = new StringBuilder("Name");
		for (int i = nameHeader.length(); i < FULL_NAME_FIELD_WIDTH; i++)
			nameHeader.append(" ");
		
		
		// Prints the header
		System.out.println("\n\n\nID\t\t" + nameHeader + "Maths\tA1\tA2\tA3\tAVRG\t\tEnglish\tA1\tA2\tA3\tAVRG");
	}

	// Creates a horizontal divider used in table formatting, width is adjustable in main parameters
	private static void divider()
	{
		for(int i = 1; i <= DIVIDER_WIDTH; i++)
			System.out.print("-");
		System.out.println();
	}
	
}
