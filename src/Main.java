import java.io.File; // import File class
import java.io.FileNotFoundException; // file input stream is at runtime
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
@SuppressWarnings("resource") //System.in cannot be reopened once closed, silly compiler.

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Customer[] custArry = new Customer[0];
		System.out.println("       WELCOME TO THE JAVA CUSTOMER REGISTRY.       ");
		MenuLoop(custArry);
	}
	public static void MenuLoop(Customer[] custArry) throws FileNotFoundException
	{
		//Since Menu is looped until exit, sorting occurs automatically at the top of the menu method every time it is called. 
		CustomerSorter(custArry);
		Scanner input = new Scanner(System.in);
		//Menu prints in a compact fashion, with integers corresponding to methods.
		System.out.println("+--------------------------------------------------+");
		System.out.println("| Please select an option from the following menu. |");
		System.out.println("|                                                  |");
		System.out.println("| 1. Add a Customer          2. Delete a Customer  |");
		System.out.println("| 3. View Customer List      4. Find a Customer    |");
		System.out.println("| 5. Load Customer List      6. Save Customer List |");
		System.out.println("| 7. Quit                                          |");
		System.out.println("+--------------------------------------------------+");
		System.out.print("Selection: ");
		String n = input.nextLine();
		//valid input options
		switch (n) 
		{
			case "1": //Add a customer
				custArry = AddCustomer(custArry);
				break;
			case "2": //Delete a customer
				custArry = DeleteCustomer(custArry);
				break;
			case "3": //View the list of customers
				ViewCustomerList(custArry);
				break;
			case "4": //Find a customer on the list
				CustomerSearch(custArry);
				break;
			case "5": //Load Customer List from file
				LoadCustomerList(custArry);
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
		input.close();
		return;
	}
	//Menu Switch Methods
	public static Customer[] AddCustomer(Customer[] custArry)
	{
		//Method immediately lengthens current Array by 1.
		custArry = ExtendArry(custArry);
		custArry[custArry.length - 1] = new Customer();
		Scanner addCust = new Scanner(System.in);
		addCust.useDelimiter(",");
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Add Customer'. Please enter the    |");
		System.out.println("| following information about the customer.  You   |");
		System.out.println("| may leave a field blank if necessary. Please end |");
		System.out.println("| all fields with a comma (,).                     |");
		System.out.println("+--------------------------------------------------+");
		System.out.print(" 1) Name (First, Last,): ");
			custArry[custArry.length - 1].setName(addCust.next() , addCust.next());
		System.out.print(" 3) Address (Street, City, State, Zip,): ");
			custArry[custArry.length - 1].setAddress(addCust.next() , addCust.next() , addCust.next() , addCust.next());
		System.out.print(" 7) Phone Number (###-###-####,): ");
			custArry[custArry.length - 1].setPhone(addCust.next());
		System.out.print(" 8) Email Address (example@you.who,): ");
			custArry[custArry.length - 1].setEmailAddress(addCust.next());
		System.out.println("+--------------------------------------------------+");
		System.out.println("| The new customer has been added to the list. Now |");
		System.out.println("| returning to the main menu.                      |");
		System.out.println("+--------------------------------------------------+");
		return custArry;
	}
/**/public static Customer[] DeleteCustomer(Customer[] custArry)
	{
		Scanner delCust = new Scanner(System.in);
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Delete Customer'. Please Enter the |");
		System.out.println("| following information about the customer.  More  |");
		System.out.println("| information may be needed for customers with     |");
		System.out.println("| similar information.                             |");
		System.out.println("+--------------------------------------------------+");
		System.out.print("Name (Last, First): ");
		String delTemp = delCust.nextLine();
		Boolean delCustExists = false;
		for (int i = 0 ; i < custArry.length; i++)
		{
			String nameInArry = custArry[i].getName();
				if (delTemp.equalsIgnoreCase(nameInArry)) 
				{
					for (int j = i ; j < custArry.length - 1 ; j++)
					{
					custArry[j] = custArry[j + 1];
					}
				custArry = ShrinkArry(custArry);
				delCustExists = true;
				break;
				}
		}
		if (delCustExists) 
		{
			System.out.println("+--------------------------------------------------+");
			System.out.println("|Customer deleted. Now returning to main menu.     |");
			System.out.println("+--------------------------------------------------+");
		}
		else
		{
			System.out.println("+--------------------------------------------------+");
			System.out.println("| Customer not found. Now returning to main menu.  |");
			System.out.println("+--------------------------------------------------+");
		}
		return custArry;
	}
	public static void ViewCustomerList(Customer[] custArry)
	{
		System.out.println("+--------------------------------------------------+");				
		System.out.println("| You selected 'View Customer List'. The following |");
		System.out.println("| is a list of our valued customers.               |");
		System.out.println("+--------------------------------------------------+");
		for (int i = 0 ; i < custArry.length ; i++)
		{
			System.out.println("Name:     " + custArry[i].getName()    );
			System.out.println("Address:  " + custArry[i].getAddress() );
			System.out.println("Phone:    " + custArry[i].getPhone()   );
			System.out.println("Email:    " + custArry[i].getEmail()   );
			System.out.println();
		}
		return;
	}
/**/public static void CustomerSearch(Customer[] custArry)
	{
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You selected 'Find Customer'. Please enter the   |");
		System.out.println("| following information about the customer.  More  |");
		System.out.println("| information may be needed for customers with     |");
		System.out.println("| similar information.                             |");
		System.out.println("+--------------------------------------------------+");
		System.out.print("Name (Last, First): ");

		Scanner findName = new Scanner(System.in);
		String nameTemp = findName.nextLine();
		Customer[] tempArry = new Customer[0];
		
		//First check for name.  This is a binary search that will locate one instance of a name.
		int indexLowB = 0;
		int indexHighB = custArry.length - 1;
		int indexMidB = 0;
		while(indexLowB <= indexHighB)
		{
			indexMidB = ((indexLowB + indexHighB) / 2);
			if(custArry[indexMidB].getName().compareToIgnoreCase(nameTemp) < 0)
			{
				//Query is not in the lower half.
				indexLowB = indexMidB + 1;
			}
			else if(custArry[indexMidB].getName().compareToIgnoreCase(nameTemp) > 0)
			{
				//Query is not in the upper half.
				indexHighB = indexMidB - 1;
			}
			else 
			{
				System.out.println("possible match found");
				break;
			}
		}
		//The array is already sorted, so the next search locates all adjacent instances of the same name.
		int indexLowL = indexMidB;
		int indexHighL = indexMidB;
		//If the name of the customer at [indexLowL] is the same as the one before it, indexLowL shifts down.
		while((indexLowL != 0) && custArry[indexLowL].getName().compareToIgnoreCase(custArry[indexLowL - 1].getName()) == 0)
		{
			indexLowL = indexLowL - 1;
		}
		//If the name of the customer at [indexHighL] is the same as the one after it, indexHighL shifts up.
		while((indexHighL != custArry.length - 1) && custArry[indexHighL].getName().compareToIgnoreCase(custArry[indexHighL + 1].getName()) == 0)
		{
			indexHighL = indexHighL + 1;
		}
		//We now have a range from indexLowL to indexHighL for all names matching query.  Move them to a sub array.
		int i = 0; 
		int j = 0;
		for(i = indexLowL ; i <= indexHighL ; i++)
		{
			tempArry = ExtendArry(tempArry);
			tempArry[j] = custArry[i];
			j++;
		}
		//Check if Array is empty
		if(tempArry.length > 1)
		{
			System.out.println("+--------------------------------------------------+");
			System.out.println("| Multiple potential matches to search criteria.   |");
			System.out.println("| Please provide additional information.           |");
			System.out.println("+--------------------------------------------------+");
			System.out.println("Email: ");
			
			
			
			
		}
		if(tempArry.length == 1)
		{
			System.out.println("+--------------------------------------------------+");
			System.out.println("| One customer matching search criteria. Returning |");
			System.out.println("| to main menu.                                    |");
			System.out.println("+--------------------------------------------------+");
		}
		else
		{
			System.out.println("+--------------------------------------------------+");
			System.out.println("| No customers matching search criteria. Returning |");
			System.out.println("| to main menu.                                    |");
			System.out.println("+--------------------------------------------------+");
			
			
			
			
		}

		return;
	}
/**/public static void LoadCustomerList(Customer[] custArry) throws FileNotFoundException
	{
		//Scanner filePathInput = new Scanner(System.in);
		//String filePath = null;
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You seleted 'Load Customer Lise'. Please copy &  |");
		System.out.println("| paste the file location for the list and press   |");
		System.out.println("| the 'Enter' key.                                 |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("File Path: ");
		//filePath = filePathInput.nextLine();
		
		//File streamIn = new File(filePath);
		//Scanner fileReader = new Scanner(streamIn); 
		
		//not finished
		
	}
	public static void SaveCustomerList(Customer[] custArry)
	{
		Scanner fileNameInput = new Scanner(System.in);
		String fileName = null;
		System.out.println("+--------------------------------------------------+");
		System.out.println("| You seleted 'Save Customer List'. Please name    |");
		System.out.println("| the file  and press the 'Enter' key. If the file |");
		System.out.println("| already exists, it will be overwritten.          |");
		System.out.println("| be overwritten.                                  |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("File Name: ");
		fileName = fileNameInput.nextLine();
		fileName = fileName.concat(".txt");
		//Preparing the output as a string
		String customerList = "";
		for(int i = 0 ; i < custArry.length ; i++)
		{
			customerList = customerList.concat(custArry[i].getName()
					                  + ", " + custArry[i].getAddress()
					                  + ", " + custArry[i].getPhone()
					                  + ", " + custArry[i].getEmail()
					                  + "\n");
		}
		//setting up the PrintWriter class.  surrounded with 'try' to catch the ioe for a null file name.
		try
		{
			PrintWriter customerListOut = new PrintWriter(fileName);
			customerListOut.println(customerList);
			customerListOut.close();
		}
		catch (IOException e)
		{
			System.out.println("ERROR: " + e);
		}
		System.out.println("+--------------------------------------------------+");
		System.out.println("| Customer List file has been written.  Returning to ");
		System.out.println("+--------------------------------------------------+");
	}
	public static void Quit()
	{
		System.out.println("+--------------------------------------------------+");
		System.out.println("| Goodbye.                                         |");
		System.out.println("+--------------------------------------------------+");
		System.exit(0);
	}
	//Array Management Methods
	public static Customer[] CustomerSorter(Customer[] custArry)//I implemented my own sorter which runs after every menu item. 
	{
		Customer[] sortedArry = new Customer[custArry.length];
		for (int i = 0 ; i < custArry.length ; i++) 
		{
			int min = i;
			for (int j = i + 1 ; j < custArry.length ; j ++)
			{
				//comparison between strings, negative means the new value is the new minimum
				if (custArry[j].getName().compareTo(custArry[min].getName()) < 0)
				{
					min = j;
				}
				//three-point swap for the values being moved
				sortedArry[i] = custArry[i];
				custArry[i] = custArry[min];
				custArry[min] = sortedArry[i];
			}
		}
		return sortedArry;
	}
 	public static Customer[] ExtendArry(Customer[] custArry)//I still used your code from the announcement to extend the array. 
 	{
		Customer[] biggerCustArry = new Customer[custArry.length + 1]; 
		for (int i = 0 ; i < custArry.length ; i++)
		{
			biggerCustArry[i] = custArry[i];
		}
		return biggerCustArry;
	}
	public static Customer[] ShrinkArry(Customer[] custArry)//I modified your extender code for deletions. 
	{
		Customer[] smallerCustArry = new Customer[custArry.length - 1];
		for (int i = 0  ; i < custArry.length - 1 ; i++)
		{
			smallerCustArry[i] = custArry[i];
		}
		return smallerCustArry;
	}
}