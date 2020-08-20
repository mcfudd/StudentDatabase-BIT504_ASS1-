import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main
{
	private static LinkedList<Student> students;
	private static Scanner scanner = new Scanner(System.in);
	private static boolean runtime = true;
	private static final String HEADER = "\n\n\nID\tName\t\tMaths\tA1\tA2\tA3\tAVRG\t\tEnglish\tA1\tA2\tA3\tAVRG";
	private static final String DIVIDER = "---------------------------------------------------------------------------------------------------------------";
	
	public static void main(String[] args)
	{
		students = new LinkedList<Student>();						// initiate the linked list
		if (readFile("studentdata.txt"))							// check and load data file, report error if not found
		{
			while (runtime == true)									// loop for runtime
			{
				displayMenu();
				switch(selectMenuOption())
				{
					case 1 : { displayReportByMarks(); break; }
					case 2 : { displayReportByGrades(); break; }
					case 3 : { addNewStudent(); break; }
					case 4 : { removeStudent(); break; }
					case 5 : { System.out.println("\n\nOK, goodbye!"); runtime = false; break; }
					default : { System.out.print("\nSelction is invalid, please try again: "); break; }
				}
//				if(runtime == true)
//					pause();
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
			}
			scanner.close();
		}
		catch (FileNotFoundException event)
		{
			System.out.println("Failed to read file");
		}
		return true;
	}
	
	private static void addStudent(int id, String firstName, String secondName, int mathsMark1, int mathsMark2, int mathsMark3, int englishMark1, int englishMark2, int englishMark3)
	{
		Student aStudent = new Student(id, firstName, secondName);
		AssignmentMarks mathSubject = new AssignmentMarks("Maths", mathsMark1, mathsMark2, mathsMark3);
		AssignmentMarks englishSubject = new AssignmentMarks("English", englishMark1, englishMark2, englishMark3);
		aStudent.mathMarks = mathSubject;
		aStudent.englishMarks = englishSubject;
		students.add(aStudent);
	}
	
	private static void removeStudent()
	{
		int studentIndex = -1;
		int i = 0;
		
		System.out.println("\n\nLIST OF STUDENTS");
		System.out.println(DIVIDER);
		
		for(Student student : students)
		{
			System.out.println(student.getID() + "\t" + student.getFullName());
		}
		
		System.out.println(DIVIDER + "\nPlease enter ID for student to remove: ");
		int toRemove = scanner.nextInt();
		
		for (Student student : students)
		{
			if(student.getID() == toRemove)
					studentIndex = i;
			i++;
		}
		if (studentIndex != -1)
		{
			students.remove(studentIndex);
			System.out.println("Student removed successfully");
		}
		else
		{
			System.out.println("Invalid selection");
		}
}
	
	private static void displayReportByMarks()
	{
		System.out.println(HEADER);
		System.out.println(DIVIDER);		
		for(Student student : students)
		{
			System.out.print(		student.getID() + "\t" +
									student.getFullName() + "\t\t" +
									student.mathMarks.getMark(1) + "\t" +
									student.mathMarks.getMark(2) + "\t" +
									student.mathMarks.getMark(3) + "\t" +
									student.mathMarks.getAverageMark() + "\t\t\t" +
									student.englishMarks.getMark(1) + "\t" +
									student.englishMarks.getMark(2) + "\t" +
									student.englishMarks.getMark(3) + "\t" +
									student.englishMarks.getAverageMark()+ "\n");
			System.out.println(DIVIDER);
		}
	}
	
	private static void displayReportByGrades()
	{
		// 		Displays a list of all the students with the student letter grades
		System.out.println(HEADER);
		System.out.println(DIVIDER);
		
		for(Student student : students)
		{
			System.out.print(		student.getID() + "\t" +
									student.getFullName() + "\t\t" +
									student.mathMarks.getGrade(1) + "\t" +
									student.mathMarks.getGrade(2) + "\t" +
									student.mathMarks.getGrade(3) + "\t" +
									student.mathMarks.getAverageGrade() + "\t\t\t" +
									student.englishMarks.getGrade(1) + "\t" +
									student.englishMarks.getGrade(2) + "\t" +
									student.englishMarks.getGrade(3) + "\t" +
									student.englishMarks.getAverageGrade() + "\n");
			System.out.println(DIVIDER);
		}
	}
	
	// Displays the menu to the user.
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
	
	// method for collecting the menu selection
	private static int selectMenuOption()
	{
		try
		{
			int menuItem = Integer.parseInt(scanner.next());
			return menuItem;
		}
		catch (Exception e)
		{
			return 6;
		}
	}
	
	private static void addNewStudent()
	{
		System.out.println("\n\nEnter Student Details:");
		System.out.println("----------------------");
		System.out.print("Student ID (next available is " + (students.getLast().id + 1) + "): ");
		int id = scanner.nextInt();
		checkUniqueID(id);		// error checking to ensure student ID is a unique number
		System.out.print("First Name: ");
		String fName = scanner.next();
		System.out.print("Last Name: ");
		String lName = scanner.next();
		System.out.println("Enter mark for Maths Assignment 1: ");
		int mA1 = scanner.nextInt();
		System.out.println("Enter mark for Maths Assignment 2: ");
		int mA2 = scanner.nextInt();
		System.out.println("Enter mark for Maths Assignment 3: ");
		int mA3 = scanner.nextInt();
		System.out.println("Enter mark for English Assignment 1: ");
		int eA1 = scanner.nextInt();
		System.out.println("Enter mark for English Assignment 2: ");
		int eA2 = scanner.nextInt();
		System.out.println("Enter mark for English Assignment 3: ");
		int eA3 = scanner.nextInt();
		
		addStudent(id, fName, lName, mA1, mA2, mA3, eA1, eA2, eA3);
		
		System.out.println("New student added successfully");
	}
	
//	// adds interface delay before continuing program loop
//	private static void pause()
//	{
//		System.out.print("\n\nPress ENTER key to continue . . .");
//		try
//		{
//			System.in.read();
//		}
//		catch(Exception e)
//		{}
//	}
	
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
				System.out.print("Student ID must be unique (next available is " + (students.getLast().id + 1) + "): ");
				id = scanner.nextInt();
			}
		} while (duplicate == true);
		return id;
	}
}
