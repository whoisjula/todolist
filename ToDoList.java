import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {	

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Main menu
        System.out.println("----- Main Menu -----");
        System.out.println(" ");
        System.out.println("1. Create a new task");
        System.out.println("2. List all tasks");
        System.out.println("3. Remove a task");
        System.out.println("4. Exit");
        System.out.println(" ");
        
        // ArrayList for tasks
        ArrayList<String> tasks = new ArrayList<String>();
        
        // Load file
        loadTasks(tasks);
        
        while (true) {
            // Get the user choice
            System.out.print("What would you like to do? Enter 1-4: ");
            Scanner userChoice = new Scanner(System.in);
            int userNum = userChoice.nextInt();
            userChoice.nextLine();
            
            // Respond to user choice
            if (userNum == 1) {
            	addTasks(tasks, userChoice);
            }
            
            else if (userNum == 2) {
            	showTasklist(tasks);           	
            }
            
            else if (userNum == 3) {
            	removeTask(tasks, userChoice);
            }
            
            else if (userNum == 4) {
                System.out.print("Exiting Progam...");
                break;
            }
            
            else {
            	System.out.println("Input was invalid. Enter 1-4.");
            }
		}
	}
    
	// Function to save tasks to a file
    public static void saveTasks(ArrayList<String> tasklist) {
    	try {
    		File arList = new File("tasklist.txt");
    		FileWriter myWriter = new FileWriter("tasklist.txt");    		
    		for (int i = 0; i < tasklist.size(); i++) {
    			myWriter.write(tasklist.get(i) + System.lineSeparator());
    		}
    		myWriter.close();
    	}
    	catch (IOException e) {
    		System.out.println("Error occurred.");
    		e.printStackTrace();
    	}
    	
    }	
    
    // Function to load task list from a file
    public static void loadTasks(ArrayList<String> tasklist) {
    	try {
    		File myObj = new File("tasklist.txt");
    		Scanner myReader = new Scanner(myObj);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			tasklist.add(data);
    		}
    	}
    	catch (FileNotFoundException e){
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
    }
    
    // Function to add tasks to the list.
    public static void addTasks(ArrayList<String> tasklist, Scanner choice) {
        System.out.print("Enter name of the task: ");
        String addTask = choice.nextLine();
    	if (addTask.length() < 15) {
            tasklist.add(addTask);
            saveTasks(tasklist);
        }
        else {
            System.out.println("Task should be between 1 and 15 characters. Please try again.");
        }
    }

	// Function to show all the tasks in the list
	public static void showTasklist(ArrayList<String> tasklist) {
		if (tasklist.isEmpty()) {
            System.out.println("There are no tasks in the list.");
        }
        else {
        	System.out.println("Here are all the current tasks: ");

        	for (int i = 0; i < tasklist.size(); i++) {
        		System.out.println((i + 1) + ". "+ tasklist.get(i));
        	}
        }
	}
	
	// Function used to remove task from the list
	public static void removeTask(ArrayList<String> tasklist, Scanner choice) {
        System.out.print("Which task would you like to remove? Enter index: ");
        int removeTask = choice.nextInt();
        tasklist.remove(removeTask - 1);
        saveTasks(tasklist);
	}
}
