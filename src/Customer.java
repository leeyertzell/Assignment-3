public class Customer implements Comparable<Customer>
{
	private String firstName = "", lastName = "" , streetAddress = "" , city = "" , state = "" , phone = "" , emailAddress = "";
	private int zip = 0;
	//CONSTRUCTOR
	public Customer()
	{
	
	}
	public Customer(String firstAndLastName)
	{
		String[] names = firstAndLastName.split(" ");
		this.firstName = names[0];
		this.lastName = names[1];	
	}
	public Customer(String firstName , String lastName)
	{
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
	}
	//SETTERS
	public void setName(String firstName , String lastName)
	{
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
	}
	public void setName(String firstAndLastName)//If only one name is given, it is assumed to be the last name. 
	{
		String[] names = firstAndLastName.split(" ");
		if(names.length == 2)
		{
			this.firstName = names[0].trim();
			this.lastName = names[1].trim();
		}
		else if(names.length == 1)
		{
			this.lastName = names[0].trim();
			this.firstName = " ";
		}
		else
		{
			this.lastName = " ";
			this.firstName = " ";
		}
	}
	public void setAddress(String streetAddress , String city , String state , String zip)
	{
		
		this.streetAddress = streetAddress.trim();
		this.city          = city.trim();
		this.state         = state.trim();
		this.zip           = Integer.valueOf(zip.trim());
	}
	public void setStreetAddress(String streetAddress)
	{
		this.streetAddress = streetAddress.trim();
	}
	public void setCity(String city)
	{
		this.city = city.trim();
	}
	public void setState(String state)
	{
		this.state = state.trim();
	}
	public void setZip(String zip) 
	{
		try 
		{
			this.zip = Integer.valueOf(zip.trim());
		}
		catch (NumberFormatException nfe)
		{
			//Initialization value of 0 is used.
		}
	}
	public void setPhone(String phone)
	{
		this.phone = phone.trim();
	}
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress.trim();
	}
	//GETTERS
	public String getName()
	{
		String name;
		name = lastName + ", " + firstName;
		return name;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getAddress()
	{
		String address;
		address = streetAddress + ", " + city + ", " + state + ", " + zip;
		return address;
	}
	public String getStreetAddress()
	{
		return streetAddress;
	}
	public String getCity()
	{
		return city;
	}
	public String getState()
	{
		return state;
	}
	public int getZip()
	{
		return zip;
	}
	public String getPhone()
	{
		return phone;
	}
	public String getEmail()
	{
		return emailAddress;
	}
	//METHODS

	public int compareTo(Customer c)
	{
		//Checks equivalence by full name.  Returns 1 or -1 depending on which is first alphabetically, ignoring case.  
		//If both names match, checks other parameters.  If all match, return 0.  Else, return -1.
		int i = this.getName().compareToIgnoreCase(c.getName());
		if(i == 0)
			if(this.equals(c))
			{
				return 0;
			}
		return -1;
	}
	public boolean equals(Customer c)
	{
		if(  this.lastName.equalsIgnoreCase(c.lastName)
		  && this.firstName.equalsIgnoreCase(c.firstName)
		  && this.city.equalsIgnoreCase(c.city)
		  && this.state.equalsIgnoreCase(c.state)
		  && this.emailAddress.equalsIgnoreCase(c.emailAddress))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}