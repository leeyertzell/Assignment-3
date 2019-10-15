public class Customer implements Comparable<Customer>
{
	private String firstName = "", lastName = "" , streetAddress = "" , city = "" , state = "" , phone = "" , emailAddress = "";
	private int zip = 0;
	//CONSTRUCTORS
	public Customer()
	{
	}
	public Customer(String firstAndLastName)
	{
		String[] names = firstAndLastName.split(" ");
		this.firstName = names[0].trim();
		this.lastName = names[1].trim();	
	}
	public Customer(String firstName , String lastName)
	{
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
	}
	public Customer(String firstName , String lastName , String streetAddress , String city , String state , String zip , String phone , String emailAddress)
	{
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
		this.streetAddress = streetAddress.trim();
		this.city = city.trim();
		this.state = state.trim();
		try 
		{
			this.zip = Integer.valueOf(zip.trim());
		}
		catch (NumberFormatException nfe)
		{
			//Initialization value of 0 is used.
		}
		this.phone = phone.trim();
		this.emailAddress = emailAddress.trim();
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
		return name.trim();
	}
	public String getFirstName()
	{
		return firstName.trim();
	}
	public String getLastName()
	{
		return lastName.trim();
	}
	public String getAddress()
	{
		String address;
		address = streetAddress.trim() + ", " + city.trim() + ", " + state.trim() + ", " + zip;
		return address;
	}
	public String getStreetAddress()
	{
		return streetAddress.trim();
	}
	public String getCity()
	{
		return city.trim();
	}
	public String getState()
	{
		return state.trim();
	}
	public int getZip()
	{
		return zip;
	}
	public String getPhone()
	{
		return phone.trim();
	}
	public String getEmail()
	{
		return emailAddress.trim();
	}
	//METHODS
	@Override
	public String toString()
	{
		return this.getName();
	}
	@Override
	public int compareTo(Customer c)
	{
		return this.getName().compareTo(c.getName());
	}
}