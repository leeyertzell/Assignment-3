	/*
 	|Author: n01425511
	*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class Main
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("+--------------------------------------------------+");
		System.out.println("|      WELCOME TO THE JAVA CUSTOMER REGISTRY.      |");
		System.out.println("+--------------------------------------------------+");
		ArrayList<Customer> custArry = new ArrayList<Customer>(0);
		Scanner s = new Scanner(System.in);
		MenuLoop(custArry , s);
		System.out.println("+--------------------------------------------------+");
		System.out.println("| Goodbye.                                         |");
		System.out.println("+--------------------------------------------------+");
		s.close();
	}
	/*
	|MenuLoop 
	|The MenuLoop method is the main menu of this project.  It maintains the ArrayList of Customers by
	|passing it to the switch-statement methods, and also by automatically sorting it every time it is
	|returned to.  The only valid way to exit the program is to enter '7' at the main menu to return 
	|to main().
	*/
	public static void MenuLoop(ArrayList<Customer> custArry , Scanner s) throws IOException
	{ 
		Collections.sort(custArry);
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
				AddCustomer(custArry , s);
				MenuLoop(custArry , s);
				break;
			case "2": //Delete a customer.
				DeleteCustomer(custArry , s); 
				MenuLoop(custArry , s);
				break;
			case "3": //View the list of customers.
				ViewCustomerList(custArry);
				MenuLoop(custArry , s);
				break;
			case "4": //Find a customer on the list and display information fields.
				SearchForCustomer(custArry , s);
				MenuLoop(custArry , s);
				break;
			case "5": //Load Customer List from file.
				LoadCustomerList(custArry , s);
				MenuLoop(custArry , s);
				break;
			case "6": //Save Customer List to file
				SaveCustomerList(custArry , s);
				MenuLoop(custArry , s);
				break;
			case "7": //Quit. This is the only way to exit the program without crashing.
				return;
			default:
				System.out.println("+--------------------------------------------------+");
				System.out.println("| Invalid value, please select from the menu.      |");
				System.out.println("+--------------------------------------------------+");
				MenuLoop(custArry , s);
			}
		return;
	}
	/*
	|Menu Switch Methods
	|These methods are accessible through the main menu, and 1 - 6 will return to the main menu. 
	*/
	public static void AddCustomer(ArrayList<Customer> custArry , Scanner s)
	{
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Add Customer'. Please enter the    |");
		System.out.println("| following information about the customer.  You   |");
		System.out.println("| may leave a field blank if necessary.            |");
		System.out.println("+--------------------------------------------------+");
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
		System.out.println("Array now has size of: " + custArry.size());
		System.out.println("+--------------------------------------------------+");
		System.out.println("| The new customer has been added to the list. Now |");
		System.out.println("| returning to the main menu.                      |");
		System.out.println("+--------------------------------------------------+");
		return;
	}
	public static void DeleteCustomer(ArrayList<Customer> custArry , Scanner s)
	{
		Customer c = new Customer();
		int a , left , right;
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Delte Customer'. Please enter the  |");
		System.out.println("| information below.                               |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("Name: ");
			c.setName(s.nextLine());
		a = BinarySearch(custArry , c);
		if(a == -1) 
		{
			System.out.println("+--------------------------------------------------+");
			System.out.println("| Customer not found. Returning to main menu.      |");
			System.out.println("+--------------------------------------------------+");
		return;
		}
		else 
		{
			//Split 'a' into a window with 'left' and 'right' to encapsulate all possible consecutive matches.
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
				System.out.println("| Do you wish to delete this cutomer? (y/n)        |");
				System.out.println("+--------------------------------------------------+");
				String del = s.nextLine();
				if(del.trim().equalsIgnoreCase("y") || del.equalsIgnoreCase("yes"))
				{
					custArry.remove(a);
					System.out.println("+--------------------------------------------------+");
					System.out.println("| Customer deleted. Returning to main menu.        |");
					System.out.println("+--------------------------------------------------+");
				}
				else
				{
					System.out.println("+--------------------------------------------------+");
					System.out.println("| Deletion cancelled. Returning to main menu.      |");
					System.out.println("+--------------------------------------------------+");
				}
			}
			else//If left does not equal right, multiple results matched.
			{
				System.out.println("+--------------------------------------------------+");
				System.out.println("| Additional Information needed. Please enter the  |");
				System.out.println("| following details.                               |");
				System.out.println("+--------------------------------------------------+");
				System.out.println("Email: ");
					c.setEmailAddress(s.nextLine());
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
					System.out.println("| Customer found.                                  |");
					System.out.println("+--------------------------------------------------+");
					System.out.println(" Name: "    + custArry.get(a).getName()    );
					System.out.println(" Address: " + custArry.get(a).getAddress() );
					System.out.println(" Phone: "   + custArry.get(a).getPhone()   );
					System.out.println(" Email: "   + custArry.get(a).getEmail()   );
					System.out.println("+--------------------------------------------------+");
					System.out.println("| Do you wish to delete this cutomer? (y/n)        |");
					System.out.println("+--------------------------------------------------+");
					String del = s.nextLine();
					if(del.equalsIgnoreCase("y") || del.equalsIgnoreCase("yes"))
					{
						custArry.remove(a);
						System.out.println("+--------------------------------------------------+");
						System.out.println("| Customer deleted. Returning to main menu.        |");
						System.out.println("+--------------------------------------------------+");
					}
					else
					{
						System.out.println("+--------------------------------------------------+");
						System.out.println("| Deletion cancelled. Returning to main menu.      |");
						System.out.println("+--------------------------------------------------+");
					}
				}
			}
		}
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
	public static void SearchForCustomer(ArrayList<Customer> custArry , Scanner s)
	{
		Customer c = new Customer();
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
			System.out.println("| Customer not found. Returning to main menu.      |");
			System.out.println("+--------------------------------------------------+");
		return;
		}
		else 
		{
			//Split 'a' into a window with 'left' and 'right' to encapsulate all possible consecutive matches.
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
				System.out.println("+--------------------------------------------------+");
				System.out.println("| Additional Information needed. Please enter the  |");
				System.out.println("| following details.                               |");
				System.out.println("+--------------------------------------------------+");
				System.out.println("Email: ");
					c.setEmailAddress(s.nextLine());
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
			}
		}
	}
	public static void LoadCustomerList(ArrayList<Customer> custArry , Scanner s) throws IOException
	{
		//C:\Users\Anthony\Desktop\COP2220\Proj2\assignment-3-leeyertzell\assignment-3\test1.txt
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Load Customer List'. Please either |");
		System.out.println("| type or copy & poste the location of the file    |");
		System.out.println("| and then press the 'Enter' key.                  |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("File Path: ");
		String filePath = s.nextLine();
		File file = new File(filePath);
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			{
				String unparsed;
				while((unparsed = br.readLine()) != null)
				{
					String[] parsed = unparsed.split(",");
					if(parsed.length == 8)
					{Customer c = new Customer(parsed[0] , parsed[1] , 
											  parsed[2] , parsed[3] , 
											  parsed[4] , parsed[5] , 
											  parsed[6] , parsed[7]);
					custArry.add(c);
					}
				}
				System.out.println("+--------------------------------------------------+");
				System.out.println("| The customer list has been loaded.  Returning to |");
				System.out.println("| main menu.                                       |");
				System.out.println("+--------------------------------------------------+");

				br.close();
			}
		}
		catch (IndexOutOfBoundsException e1)
		{
			System.out.println("File format errors detected. " + e1);
		}
		catch (FileNotFoundException e2)
		{
	         System.out.println("The file name or path is invalid. " + e2);
		}
	}
	public static void SaveCustomerList(ArrayList<Customer> custArry , Scanner s)
	{
		String fileName = null;
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Save Customer List'. Please name   |");
		System.out.println("| the file and press the 'Enter' key. If the file  |");
		System.out.println("| already exists, it will be overwritten.          |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("File Name: ");
		fileName = s.nextLine();
		fileName = fileName.concat(".txt");
		//Preparing the output as a string
		String customerList = "";
		for(int i = 0 ; i < custArry.size() - 1 ; i++)
		{
			customerList = customerList.concat(custArry.get(i).getFirstName().trim()
									  + ", " + custArry.get(i).getLastName().trim()
					                  + ", " + custArry.get(i).getAddress().trim()
					                  + ", " + custArry.get(i).getPhone().trim()
					                  + ", " + custArry.get(i).getEmail().trim()
					                  + "\n");
		}
			customerList = customerList.concat(custArry.get(custArry.size() - 1).getFirstName().trim()
									  + ", " + custArry.get(custArry.size() - 1).getLastName().trim()
									  + ", " + custArry.get(custArry.size() - 1).getAddress().trim()
									  + ", " + custArry.get(custArry.size() - 1).getPhone().trim()
									  + ", " + custArry.get(custArry.size() - 1).getEmail().trim());
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
	/*
	|Search Methods.
	|These methods are called in both SearchForCustomer and DeleteCustomer. The first two methods are binary searches that take
	|advantage of method overloading to output a search result with slight variation to the arguments passed. The last two methods
	|define the search bounds for the second binary search based off of additional parameters if the first search returns multiple
	|possible results. The additional parameter used in this assignment is 'email'.	
	*/
	public static int BinarySearch(ArrayList<Customer> custArry , Customer c) 
	{
		int left = 0;
		int right = custArry.size() - 1;
		while(left <= right)
		{
			int mid = ((left + right) / 2);
			if(c.compareTo(custArry.get(mid)) == 0)//Possible match found.
			{
				return mid;
			}
			if(c.compareTo(custArry.get(mid)) > 0) //Possibly exists in upper half.
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
	public static int BinarySearch(ArrayList<Customer> custArry , Customer c , int left , int right) 
	{
		while(left <= right)
		{
			int mid = ((left + right) / 2);
			if(c.getEmail().compareTo(custArry.get(mid).getEmail()) == 0)//Possible match found.
			{
				return mid;
			}
			if(c.getEmail().compareTo(custArry.get(mid).getEmail()) > 0) //Possibly exists in upper half.
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
}