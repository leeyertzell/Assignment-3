import java.io.File; // import File class
import java.io.FileNotFoundException; // file input stream is at runtime
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
@SuppressWarnings("resource") //System.in cannot be reopened once it is closed, silly compiler.

public class MainUsingAL
{
	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("       WELCOME TO THE JAVA CUSTOMER REGISTRY.       ");
		ArrayList<Customer> custArry = new ArrayList<Customer>(0);
		MenuLoop(custArry);
	}
	public static void MenuLoop(ArrayList<Customer> custArry) throws FileNotFoundException
	{ 
		//Customer name set as Comparator, the array is re-sorted at the beginning of every loop.
		Collections.sort(custArry); 
		Scanner s = new Scanner(System.in);
		System.out.println("+--------------------------------------------------+");
		System.out.println("| Please select an option from the following menu. |");
		System.out.println("|                                                  |");
		System.out.println("| 1. Add a Customer          2. Delete a Customer  |");
		System.out.println("| 3. View Customer List      4. Find a Customer    |");
		System.out.println("| 5. Load Customer List      6. Save Customer List |");
		System.out.println("| 7. Quit                                          |");
		System.out.println("+--------------------------------------------------+");
		System.out.print("Selection: ");
		String n = s.nextLine();
		//valid input options
		switch (n) 
			{
			case "1": //Add a customer.
				AddCustomer(custArry);
				break;
			case "2": //Delete a customer.
				DeleteCustomer(custArry);
				break;
			case "3": //View the list of customers.
				ViewCustomerList(custArry);
				break;
			case "4": //Find a customer on the list and display information fields.
				SearchForCustomer(custArry);
				break;
			case "5": //Load Customer List from file.
//				LoadCustomerList(custArry);
				break;
			case "6": //Save Customer List to file
				SaveCustomerList(custArry);
				break;
			case "7": //Quit. This is the only way to exit the program without crashing.
				Quit();
				break;
			default:
				System.out.println("+--------------------------------------------------+");
				System.out.println("| Invalid value, please select from the menu.      |");
				System.out.println("+--------------------------------------------------+");
			}
			MenuLoop(custArry);	
		s.close();
		return;
	}
	//Menu Switch Methods
	public static void AddCustomer(ArrayList<Customer> custArry)
	{
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Add Customer'. Please enter the    |");
		System.out.println("| following information about the customer.  You   |");
		System.out.println("| may leave a field blank if necessary.            |");
		System.out.println("+--------------------------------------------------+");
		Scanner s = new Scanner(System.in);
		Customer c = new Customer();
		System.out.print("Name (First Last): ");
			c.setName(s.nextLine());
		System.out.print("Street Address: ");
			c.setStreetAddress(s.nextLine());
		System.out.print("City: ");
			c.setCity(s.nextLine());
		System.out.print("State: ");
			c.setState(s.nextLine());
		System.out.print("Zip Code: ");
			c.setZip(s.nextLine());
		System.out.print("Phone Number (###-###-####): ");
			c.setPhone(s.nextLine());
		System.out.print("Email Address: ");
			c.setEmailAddress(s.nextLine());
		custArry.add(c);
		System.out.println("+--------------------------------------------------+");
		System.out.println("| The new customer has been added to the list. Now |");
		System.out.println("| returning to the main menu.                      |");
		System.out.println("+--------------------------------------------------+");
		return;
	}
	public static void DeleteCustomer(ArrayList<Customer> custArry)
	{
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Delete Customer'. Please Enter the |");
		System.out.println("| following information about the customer.        |");
		System.out.println("+--------------------------------------------------+");
		for(int i = 0 ; i < custArry.size() ; i++)
		{
			custArry.get(i).getName();
		}
		return;
	}
	public static void ViewCustomerList(ArrayList<Customer> custArry)
	{
		System.out.println("+--------------------------------------------------+");				
		System.out.println("| You selected 'View Customer List'. The following |");
		System.out.println("| is a list of our valued customers.               |");
		System.out.println("+--------------------------------------------------+");
			System.out.println(String.format("%-12s%-12s%-15s%-15s\n","Last","First","City","State"));
		for (int i = 0 ; i < custArry.size() ; i++)
		{
			System.out.println(String.format("%-12s%-12s%-15s%-15s\n",
											 custArry.get(i).getLastName(),
											 custArry.get(i).getFirstName(),
											 custArry.get(i).getCity(),
											 custArry.get(i).getState()));
		}
		return;
	}
	public static void SearchForCustomer(ArrayList<Customer> custArry)
	{
		Customer c = new Customer();
		Scanner s = new Scanner(System.in);
		int a , left , right;
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Find a Customer'. Please enter the |");
		System.out.println("| information below.                               |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("Name: ");
			c.setName(s.nextLine());
		a = BinarySearch(custArry , c);
		if(a == -1) 
		{
			System.out.println("+--------------------------------------------------+");
			System.out.println("| Customer not found. Returning to main menu. First search failed.|");
			System.out.println("+--------------------------------------------------+");
		return;
		}
		//Split 'a' into a window with 'left' and 'right' to represent the multiple possible consecutive matches.
		left = SearchWindowMin(custArry , c , a);
		right = SearchWindowMax(custArry , c , a);
		if(right == left)//If left equals right, there is only one match.
		{
			System.out.println("+--------------------------------------------------+");
			System.out.println("| Customer found.                                  |");
			System.out.println("+--------------------------------------------------+");
			System.out.println(" Name: "    + custArry.get(a).getName()    );
			System.out.println(" Address: " + custArry.get(a).getAddress() );
			System.out.println(" Phone: "   + custArry.get(a).getPhone()   );
			System.out.println(" Email: "   + custArry.get(a).getEmail()   );
			System.out.println("+--------------------------------------------------+");
			System.out.println("| Returning to main menu.                          |");
			System.out.println("+--------------------------------------------------+");
		}
		else//If left does not equal right, multiple results matched.
		{
			a = BinarySearch(custArry , c , left, right);
			if(a == -1)
			{
				System.out.println("+--------------------------------------------------+");
				System.out.println("| Customer not found. Returning to main menu.      |");
				System.out.println("+--------------------------------------------------+");
			return;
			}
			else
			{
				System.out.println("+--------------------------------------------------+");
				System.out.println("| Customer found. Returning to main menu.          |");
				System.out.println("+--------------------------------------------------+");
				System.out.println(" Name: "    + custArry.get(a).getName()    );
				System.out.println(" Address: " + custArry.get(a).getAddress() );
				System.out.println(" Phone: "   + custArry.get(a).getPhone()   );
				System.out.println(" Email: "   + custArry.get(a).getEmail()   );
				System.out.println("+--------------------------------------------------+");
				System.out.println("| Returning to main menu.                          |");
				System.out.println("+--------------------------------------------------+");
			}
		
		}
	}
	public static void LoadCustomerList(ArrayList<Customer> custArry) throws FileNotFoundException
	{
		/*
		//Scanner filePathInput = new Scanner(System.in);
		//String filePath = null;
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Load Customer List'. Please copy & |");
		System.out.println("| paste the file location for the list and press   |");
		System.out.println("| the 'Enter' key.                                 |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("File Path: ");
		//filePath = filePathInput.nextLine();
		
		//File streamIn = new File(filePath);
		//Scanner fileReader = new Scanner(streamIn); 
		
		//not finished
		*/
	}
	public static void SaveCustomerList(ArrayList<Customer> custArry)
	{
		Scanner fileNameInput = new Scanner(System.in);
		String fileName = null;
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Save Customer List'. Please name   |");
		System.out.println("| the file and press the 'Enter' key. If the file  |");
		System.out.println("| already exists, it will be overwritten.          |");
		System.out.println("| be overwritten.                                  |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("File Name: ");
		fileName = fileNameInput.nextLine();
		fileName = fileName.concat(".txt");
		//Preparing the output as a string
		String customerList = "";
		for(int i = 0 ; i < custArry.size() ; i++)
		{
			customerList = customerList.concat(custArry.get(i).getName()
					                  + ", " + custArry.get(i).getAddress()
					                  + ", " + custArry.get(i).getPhone()
					                  + ", " + custArry.get(i).getEmail()
					                  + "\n");
		}
		//Setting up the PrintWriter class. Surrounded with 'try' to catch the IOException for a null file name.
		try
		{
			PrintWriter customerListOut = new PrintWriter(fileName);
			customerListOut.println(customerList);
			customerListOut.close();
			System.out.println("+--------------------------------------------------+");
			System.out.println("| The customer list has been saved.  Returning to  |");
			System.out.println("| main menu.                                       |");
			System.out.println("+--------------------------------------------------+");
		}
		catch (IOException e)
		{
			System.out.println(" #ERROR: " + e );
		}
	}
	public static void Quit()
	{
		System.out.println("+--------------------------------------------------+");
		System.out.println("| Goodbye.                                         |");
		System.out.println("+--------------------------------------------------+");
		System.exit(0);
	}
	//Main Methods
	public static int BinarySearch(ArrayList<Customer> custArry , Customer c)
	{
		int left = 0 , right = custArry.size() , mid = ((left + right) / 2);
		while(left < right)
		{
			if(c.compareTo(custArry.get(mid)) == 0)//Possible match found.
			{
				return mid;
			}
			else if(c.compareTo(custArry.get(mid)) == 1)//Possibly exists in upper half.
			{
				left = mid + 1;
						System.out.println("not yet");
			}
			else//Possibly exists in lower half.
			{
				right = mid - 1;
						System.out.println("yet not");
			}
		}
		return -1;
	}
	public static int SearchWindowMin(ArrayList<Customer> custArry , Customer c , int a)
	{
		while((a != 0) && (c.compareTo(custArry.get(a - 1)) == 0)) 
		{
			a -= 1;
		}
		return a;
	}
	public static int SearchWindowMax(ArrayList<Customer> custArry , Customer c , int a)
	{
		while((a != custArry.size() - 1) && (c.compareTo(custArry.get(a + 1)) == 0)) 
		{
			a += 1;
		}
		return a;
	}
	public static int BinarySearch(ArrayList<Customer> custArry , Customer c , int left , int right)
	{
		int mid = ((left + right) / 2);
		while(left < right)
		{
			if(c.getEmail().compareTo(custArry.get(mid).getEmail()) == 0)//Match found.
			{
				return mid;
			}
			else if(c.getEmail().compareTo(custArry.get(mid).getEmail()) == 1)//Possibly exists in upper half.
			{
				left = mid + 1;
			}
			else//Possibly exists in lower half.
			{
				right = mid - 1;
			}
		}
		return -1;
	}
}	