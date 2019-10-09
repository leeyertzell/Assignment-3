public class Customer 
{
	private String firstName = "", lastName = "" , streetAddress = "" , city = "" , state = "" , phone = "" , emailAddress = "";
	private int zip = 0;
	
	//SETTERS
	public void setName(String name)
	{
		String[] nameArry = new String[2];
		nameArry = name.split(" ");
		if(nameArry.length == 0)
		{
			this.firstName = "(BLANK)";
			this.lastName  = "(BLANK)";
		}
		else if(nameArry.length == 1)
		{
			this.firstName = name;
			this.lastName  = "(BLANK)";
		}
		else
		{
			this.firstName = nameArry[0];
			this.lastName  = nameArry[1];
		}
	}
	public void setAddress(String streetAddress , String city , String state , String zip)
	{
		this.streetAddress = streetAddress;
		this.city          = city;
		this.state         = state;
		this.zip           = Integer.valueOf(zip);
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}
	//GETTERS
	public String getName()
	{
		String name;
		name = lastName + ", " + firstName;
		return name;
	}
	public String getAddress()
	{
		String address;
		address = streetAddress + ", " + city + ", " + state + ", " + zip;
		return address;
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
		//Checks equivalence by last name, then first name.  Returns 1 or -1 depending on which is first alphabetically, ignoring case.  
		//If both names match, checks other parameters.  If all match, return 0.  Else, return -1.
		int i = this.lastName.compareToIgnoreCase(c.lastName);
		if(i == 0)
		{
			i = this.firstName.compareToIgnoreCase(c.firstName);
			if(i == 0)
			{
				if(!(equals(c)))
				{
					return -1;
				}
			}
		}
		return i;
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