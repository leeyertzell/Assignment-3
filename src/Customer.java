public class Customer 
{
	private String firstName = "", lastName = "" , streetAddress = "" , city = "" , state = "" , phone = "" , emailAddress = "";
	private int zip = 0;
	//CONSTRUCTOR
	public Customer()
	{
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
	public void setAddress(String streetAddress , String city , String state , String zip)
	{
		
		this.streetAddress = streetAddress.trim();
		this.city          = city.trim();
		this.state         = state.trim();
		this.zip           = Integer.valueOf(zip.trim());
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