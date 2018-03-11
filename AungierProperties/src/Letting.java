/*
 * Lucy Gormley
 */
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Letting
{
	private ArrayList<Tenant>tenants;
	private ArrayList<Apartment>apartments;
	private ArrayList<Lease>leases;
	private Scanner scan;
	private File tenantsFile;
	private File apartmentsFile;
	private File leasesFile;
	private Tenant tenant;
	private static double COMMISSION = 0.10;


	public Letting()
	{
		tenants = new ArrayList<Tenant>(); 
		apartments = new ArrayList<Apartment>();
		leases = new ArrayList<Lease>();
		scan = new Scanner(System.in);
		tenantsFile = new File("tenants.dat");
		apartmentsFile = new File("apartments.dat");
		leasesFile = new File("leases.dat");
		readFromTenantsFile();
		readFromApartmentsFile();
		readFromLeasesFile();
	}

	public void readFromTenantsFile()
	{
		try
		{
			FileInputStream fis = new FileInputStream(tenantsFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			tenants = (ArrayList<Tenant>) ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());

		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void writeToTenantsFile()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(tenantsFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tenants);
			oos.close();

		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void readFromApartmentsFile()
	{
		try
		{
			FileInputStream fis = new FileInputStream(apartmentsFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			apartments = (ArrayList<Apartment>) ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());

		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void writeToApartmentsFile()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(apartmentsFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(apartments);
			oos.close();

		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void readFromLeasesFile()
	{
		try
		{
			FileInputStream fis = new FileInputStream(leasesFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			leases = (ArrayList<Lease>) ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());

		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void writeToLeasesFile()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(leasesFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(leases);
			oos.close();

		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public boolean validInteger(String input)// checks if input can be converted to an integer value - no decimal point
	{
		if(input.length() < 1)
		{
			return false;
		}
		for(int i = 0; i < input.length(); i++)
		{
			char c = input.charAt(i);
			if(!Character.isDigit(c))
			{
				return false;

			}
		}
		return true;
	}

	public boolean validDouble(String input)//checks if input can be converted to double - will only allow 0 or 1 decimal point 
	{
		if(input.length() < 1)
		{
			return false;
		}
		else if(!input.matches("[0-9]*\\.?[0-9]+") && !input.matches("[0-9]+\\.?[0-9]*"))
		{
			return false;
		}
		return true;
	}

	public void enterApartment() //creates and stores apartment
	{
		System.out.print("Enter apartment number: ");
		String aptNumber = scan.nextLine();
		System.out.print("Enter apartment address: ");
		String address = scan.nextLine();
		System.out.print("Enter monthly rent: ");
		String inputRent = scan.nextLine();
		while(!validDouble(inputRent))
		{
			System.out.print("Error - please enter the monthly rent for this apartment(numerically): ");
			inputRent = scan.nextLine();
		}
		double rent = Double.parseDouble(inputRent);
		System.out.print("Number of bedrooms in apartment: ");
		String inputBedrooms = scan.nextLine();
		while(!validInteger(inputBedrooms))
		{
			System.out.print("Error - please enter the number of bedrooms in this apartment(numerically): ");
			inputBedrooms = scan.nextLine();
		}
		int bedrooms = Integer.parseInt(inputBedrooms);
		System.out.print("Number of bathrooms in apartment: ");
		String inputBathrooms = scan.nextLine();
		while(!validInteger(inputBathrooms))
		{
			System.out.print("Error - please enter the number of bathrooms in this apartment(numerically): ");
			inputBathrooms = scan.nextLine();
		}
		int bathrooms = Integer.parseInt(inputBathrooms);		
		System.out.print("Does this apartment have a balcony?(Y/N)> ");
		String balconyResponse = scan.nextLine();
		boolean balcony = true;
		while(!balconyResponse.equalsIgnoreCase("y") && !balconyResponse.equalsIgnoreCase("n"))
		{
			System.out.print("Please enter Y/N - Does this apartment have a balcony? ");
			balconyResponse = scan.nextLine();
		}
		if(balconyResponse.equalsIgnoreCase("y"))
		{
			balcony = true;
		}
		else if(balconyResponse.equalsIgnoreCase("n"))
		{
			balcony = false;
		}
		Apartment apartment = new Apartment(aptNumber, address, rent, bedrooms, bathrooms, balcony);
		apartments.add(apartment);
		System.out.println();
		System.out.println(apartment.toString());
	}

	public Apartment searchAptNumber(String aptNumber)//returns apartment if input aptNumber finds a match in arrayList
	{
		for(Apartment apartment: apartments)
		{
			if(apartment.getAptNumber().equalsIgnoreCase(aptNumber))
			{
				return apartment;
			}
		}
		return null;
	}

	public void showApartments() // displays all apartments
	{
		System.out.println("DETAILS FOR ALL APARTMENTS");
		System.out.println();
		if(!apartments.isEmpty())
		{
			for(Apartment apartment : apartments)
			{
				System.out.println(apartment.toString());
				System.out.println();
			}
		}
		else
		{
			System.out.println("There are currently no apartments registered.");
		}
	}

	public boolean validEmail(String email)//validates email - email must have any characters followed by @ followed by any characters followed by 1 '.' followed by any amount of letters only
	{
		if(email.matches(".+@.+\\.[a-z]+"))
		{
			System.out.println("Valid email");
			return true;
		}
		else
		{
			System.out.println("Invalid email");
			return false;
		}	
	}

	public void enterTenant()//creates and stores tenant
	{
		System.out.print("Enter name: ");
		String name = scan.nextLine();
		System.out.print("Enter phone number: ");
		String phoneNumber = scan.nextLine();
		System.out.print("Enter name of tenant's referee: ");
		String referee = scan.nextLine();
		System.out.print("Enter tenant's email address: ");
		String email = scan.nextLine();
		while(!validEmail(email))
		{
			System.out.print("Error - please enter a valid email address: ");
			email = scan.nextLine();
		}
		System.out.print("Maximum rent payable per month: ");
		String inputRent = scan.nextLine();
		while(!validDouble(inputRent))
		{
			System.out.print("Error - please enter the maximum monthly rent the tenant can pay(numerically): ");
			inputRent = scan.nextLine();
		}
		double maxRent = Double.parseDouble(inputRent);

		Tenant tenant = new Tenant(name, phoneNumber, referee, email, maxRent);
		tenants.add(tenant);
		System.out.println();
		System.out.println(tenant.toString());
	}

	public Tenant searchTenantName(String name) // returns tenant if input name matches name in arrayList
	{
		for(Tenant tenant : tenants)
		{
			if (tenant.getName().equalsIgnoreCase(name))
			{
				return tenant;
			}
		}
		return null;
	}

	public void showTenants() // displays all tenants
	{
		System.out.println("DETAILS FOR ALL TENANTS:");
		System.out.println();
		if(!tenants.isEmpty())
		{
			for(Tenant tenant : tenants)
			{
				System.out.println(tenant.toString());
				System.out.println();
			}
		}
		else
		{
			System.out.println("There are currently no tenants registered.");
		}
	}

	public void suitableApartment()// displays suitable apartments below the maxRent of tenant taking the 10% commission into account
	{
		if(!tenants.isEmpty())
		{
			System.out.print("Enter tenant name: ");
			String name = scan.nextLine();
			Tenant tenant = searchTenantName(name);
			if(tenant != null)
			{
				System.out.println("Tenant Found");
				System.out.print("Number of bedrooms in apartment: ");
				String inputBedrooms = scan.nextLine();
				while(!validInteger(inputBedrooms))
				{
					System.out.print("Error - please enter the number of bedrooms in this apartment(numerically): ");
					inputBedrooms = scan.nextLine();
				}
				int bedrooms = Integer.parseInt(inputBedrooms);
				System.out.print("Number of bathrooms in apartment: ");
				String inputBathrooms = scan.nextLine();
				while(!validInteger(inputBathrooms))
				{
					System.out.print("Error - please enter the number of bathrooms in this apartment(numerically): ");
					inputBathrooms = scan.nextLine();
				}
				int bathrooms = Integer.parseInt(inputBathrooms);
				System.out.print("Do you want a balcony? (Y/N - or enter 0 if it is not essential): ");
				String balconyResponse = scan.nextLine();
				while(!balconyResponse.equalsIgnoreCase("y") && !balconyResponse.equalsIgnoreCase("n") && !balconyResponse.equals("0"))
				{
					System.out.print("Error - Do you want a balcony? (Y/N - or enter 0 if it is not essential):  ");
					balconyResponse = scan.nextLine();
				}
				System.out.println();
				System.out.println("SUITABLE APARTMENTS:");
				System.out.println();
				int index = 0;
				for(Apartment apartment : apartments)
				{
					if(balconyResponse.equalsIgnoreCase("y"))
					{
						if(apartment.getBedrooms() >= bedrooms && apartment.getBathrooms() >= bathrooms && apartment.getRent() <= (tenant.getMaxRent() - (apartment.getRent() * COMMISSION)) && apartment.isAvailable() && apartment.hasBalcony())
						{
							System.out.println(apartment.toString());
							System.out.println();
							index++;
						}
					}
					else if(balconyResponse.equalsIgnoreCase("n"))
					{
						if(apartment.getBedrooms() >= bedrooms && apartment.getBathrooms() >= bathrooms && apartment.getRent() <= (tenant.getMaxRent() - (apartment.getRent() * COMMISSION)) && apartment.isAvailable() && apartment.hasBalcony() == false)
						{
							System.out.println(apartment.toString());
							System.out.println();
							index++;
						}
					}
					else
					{
						if(apartment.getBedrooms() >= bedrooms && apartment.getBathrooms() >= bathrooms && apartment.getRent() <= (tenant.getMaxRent() - (apartment.getRent() * COMMISSION)) && apartment.isAvailable())
						{
							System.out.println(apartment.toString());
							System.out.println();
							index++;
						}
					}
				}
				System.out.println(index + " apartment(s) match your specification.");
			}
			else
			{
				System.out.println("Tenant not found.");
			}
		}
		else
		{
			System.out.println("There are currently no tenants registered.");
		}
	}

	public boolean validateLeaseId(String leaseId)//validates leaseId - must have capital l followed by one letter followed by 3 digits
	{
		if(leaseId.matches("L[a-z][0-9]{3}"))
		{
			System.out.println("LeaseId is valid.");
			return true;
		}
		else
		{
			return false;
		}
	}

	public Lease searchLeaseId(String leaseId) // returns lease if matching leaseId is found
	{
		for(Lease lease : leases)
		{
			if (lease.getLeaseId().equals(leaseId))
			{
				return lease;
			}
		}
		return null;
	}

	public void enterLease() // creates and stores leases
	{
		if(!tenants.isEmpty() && !apartments.isEmpty())
		{
			System.out.print("Please enter 1 if you know which apartment to rent, or enter 2 to view a list of suitable apartments -> ");
			String response = scan.nextLine();
			while(!response.equals("1") && !response.equals("2"))
			{
				System.out.print("Unavailable option, please enter 1 or 2 -> ");
				response = scan.nextLine();
			}
			if(response.equals("1"))
			{
				System.out.print("Enter tenant name: ");
				String name = scan.nextLine();
				Tenant tenant = searchTenantName(name);
				if(tenant != null)
				{
					System.out.println("Tenant found.");
					System.out.print("Enter apartment number: ");
					String aptNumber = scan.nextLine();
					Apartment apartment = searchAptNumber(aptNumber);
					if(apartment != null)
					{
						System.out.println("Apartment found");
						if(apartment.isAvailable())
						{
							if(apartment.getRent() <= (tenant.getMaxRent() - (apartment.getRent() * COMMISSION)))// to allow for 10% commission
							{
								System.out.print("Enter Lease ID: ");
								String leaseId = scan.nextLine();
								boolean valid = validateLeaseId(leaseId);
								while(valid == false )
								{
									System.out.println("LeaseId is invalid, Lease ID must start with L, followed by any character, followed by 3 digits.");
									System.out.print("Please re-enter Lease ID: ");
									leaseId = scan.nextLine();
									valid = validateLeaseId(leaseId);
								}
								Lease leaseSearch = searchLeaseId(leaseId);
								if(leaseSearch == null)
								{
									System.out.print("Enter the lease start date: ");
									String inputDay = scan.nextLine();
									while(!validInteger(inputDay))
									{
										System.out.print("Error - please enter the date* the lease starts(numerically): ");
										inputDay = scan.nextLine();
									}

									int startDay = Integer.parseInt(inputDay);
									System.out.print("Enter the month the lease starts in: ");
									String inputMonth = scan.nextLine();
									while(!validInteger(inputMonth))
									{
										System.out.print("Error - please enter the month* the lease starts in(numerically): ");
										inputMonth = scan.nextLine();
									}
									int startMonth = Integer.parseInt(inputMonth);
									System.out.print("Enter the year the lease starts in: ");
									String inputYear = scan.nextLine();
									while(!validInteger(inputYear))
									{
										System.out.print("Error - please enter the year* the lease starts in(numerically): ");
										inputYear = scan.nextLine();
									}
									int startYear = Integer.parseInt(inputYear);
									System.out.print("Enter the lease end date: ");
									String inputEndDay = scan.nextLine();
									while(!validInteger(inputEndDay))
									{
										System.out.print("Error - please enter the date* the lease ends(numerically): ");
										inputEndDay = scan.nextLine();
									}

									int endDay = Integer.parseInt(inputEndDay);
									System.out.print("Enter the month the lease ends in: ");
									String inputEndMonth = scan.nextLine();
									while(!validInteger(inputEndMonth))
									{
										System.out.print("Error - please enter the month* the lease ends in(numerically): ");
										inputEndMonth = scan.nextLine();
									}
									int endMonth = Integer.parseInt(inputEndMonth);
									System.out.print("Enter the year the lease ends in: ");
									String inputEndYear = scan.nextLine();
									while(!validInteger(inputEndYear))
									{
										System.out.print("Error - please enter the year* the lease ends in(numerically): ");
										inputEndYear = scan.nextLine();
									}
									int endYear = Integer.parseInt(inputEndYear);
									Lease lease = new Lease(tenant, leaseId, apartment, startDay, startMonth, startYear, endDay, endMonth, endYear);
									leases.add(lease);
									lease.getApartment().notAvailable();
									System.out.println();
									System.out.println(lease.toString());
								}
								else
								{
									System.out.println("Lease ID has already been used.");
								}
							}
							else
							{
								System.out.println("This apartment's monthly rent of " + apartment.getRent() + " is greater than this tenant's \nmaximum rent payable of " + tenant.getMaxRent() + ", making this apartment unsuitable for this tenant.");
							}
						}
						else
						{
							System.out.println("Apartment is currently unavailable to rent.");
						}
					}
					else
					{
						System.out.println("Apartment not found.");
					}
				}
				else
				{
					System.out.println("Tenant not found.");
				}
			}
			else
			{
				System.out.println("Please view suitable apartments through option 5 on the menu, then create a lease through option 6.");
			}
		}
		else
		{
			System.out.println("Both apartments and tenants must be registered to create a lease.");
		}
	}

	public void showLeases() // displays all leases
	{
		System.out.println("DETAILS FOR ALL LEASES:");
		System.out.println();
		int index = 1;
		if(!leases.isEmpty())
		{
			for(Lease lease : leases)
			{
				System.out.println("[" + index + "]");
				System.out.println(lease.toString());
				System.out.println();
			}
		}
		else
		{
			System.out.println("There are currently no leases to display.");
		}
	}

	public void rentedApt() // displays number of rented apartments
	{
		if(!apartments.isEmpty())
		{
			int index = 0;
			for(Apartment apartment : apartments)
			{
				if(apartment.isAvailable() == false)
				{
					index++;
				}
			}
			System.out.println(index + " apartment(s) are rented.");
		}
		else
		{
			System.out.println("There are currently no apartments registered.");
		}
	}

	public void availableApt() // displays number of available apartments
	{
		if(!apartments.isEmpty())
		{
			int index = 0;
			for(Apartment apartment : apartments)
			{
				if(apartment.isAvailable() == true)
				{
					index++;
				}
			}
			System.out.println(index + " apartment(s) are available to rent.");
		}
		else
		{
			System.out.println("There are currently no apartments registered.");
		}
	}

	public void showRentedApt() // displays rented apartments
	{
		if(!apartments.isEmpty())
		{
			int index = 0;
			System.out.println("UNAVAILABLE APARTMENTS:");
			for(Apartment apartment : apartments)
			{
				if(apartment.isAvailable() == false)
				{
					System.out.println(apartment.toString());
					index++;
				}
			}
			if(index < 1)
			{
				System.out.println("There are currently no apartments being rented.");
			}
		}
		else
		{
			System.out.println("There are currently no apartments registered.");
		}
	}

	public void showAvailableApt() // displays available apartments
	{
		if(!apartments.isEmpty())
		{
			int index = 0;
			System.out.println("AVAILABLE APARTMENTS:");
			for(Apartment apartment : apartments)
			{
				if(apartment.isAvailable() == true)
				{
					System.out.println(apartment.toString());
					index++;
				}
			}
			if(index < 1)
			{
				System.out.println("There are currently no apartments available to rent.");
			}
		}
		else
		{
			System.out.println("There are currently no apartments registered.");
		}
	}

	public void endLease() // leaseId and end date is entered, a message is shown to display arranged end date, apartment is changed to available and lease is deleted from arrayList
	{
		if(!leases.isEmpty())
		{
			System.out.print("Enter lease ID: ");
			String leaseId = scan.nextLine();
			Lease lease = searchLeaseId(leaseId);
			if(lease != null)
			{
				System.out.print("Enter the lease end date: ");
				String inputEndDay = scan.nextLine();
				while(!validInteger(inputEndDay))
				{
					System.out.print("Error - please enter the date* the lease ends(numerically): ");
					inputEndDay = scan.nextLine();
				}

				int endDay = Integer.parseInt(inputEndDay);
				System.out.print("Enter the month the lease ends in: ");
				String inputEndMonth = scan.nextLine();
				while(!validInteger(inputEndMonth))
				{
					System.out.print("Error - please enter the month* the lease ends in(numerically): ");
					inputEndMonth = scan.nextLine();
				}
				int endMonth = Integer.parseInt(inputEndMonth);
				System.out.print("Enter the year the lease ends in: ");
				String inputEndYear = scan.nextLine();
				while(!validInteger(inputEndYear))
				{
					System.out.print("Error - please enter the year* the lease ends in(numerically): ");
					inputEndYear = scan.nextLine();
				}
				int endYear = Integer.parseInt(inputEndYear);
				LocalDate endDateLease = LocalDate.of(endYear, endMonth, endDay);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/yyyy E");
				dtf.format(endDateLease);
				if(endDateLease != lease.getEndDate())
				{
					System.out.println("This lease was due to finish on " + dtf.format(lease.getEndDate()) + ", and has instead finished on " + dtf.format(endDateLease));
				}
				else
				{
					System.out.println("Lease has finished on correct date.");
				}
				lease.getApartment().nowAvailable();
				leases.remove(lease);
				System.out.println();
				System.out.println("-->This apartment is now available to rent.");
				System.out.println(lease.getApartment().toString());
			}

			else
			{
				System.out.println("Lease ID entered does not match with any leases registered.");
			}
		}

		else
		{
			System.out.println("There are currently no leases registered");
		}
	}

	public void displayMenu()
	{
		System.out.println();
		System.out.println("1 = Enter apartment details.");
		System.out.println("2 = Enter tenant details.");
		System.out.println("3 = Display all registered apartments.");
		System.out.println("4 = Display all registered tenants.");
		System.out.println("5 = Display suitable apartments.");
		System.out.println("6 = Enter Lease details.");
		System.out.println("7 = Display all leases.");
		System.out.println("8 = Display rented apartments.");
		System.out.println("9 = Number of rented apartments.");
		System.out.println("10 = Display available apartments.");
		System.out.println("11 = Number of available apartments.");
		System.out.println("12 = End lease.");
		System.out.println("13 = Exit.");
		System.out.print("WHAT DO YOU WANT TO DO?(1-13) > ");
	}

	public void menu()
	{
		int response;
		do{
			displayMenu();
			String inputResponse = scan.nextLine();
			while(!validInteger(inputResponse))
			{
				System.out.print("Error - please enter one option(numerically): ");
				inputResponse = scan.nextLine();
			}
			response = Integer.parseInt(inputResponse);
			System.out.println();
			if(response == 1)
			{
				enterApartment();
			}

			else if(response == 2)
			{
				enterTenant();
			}

			else if(response == 3)
			{
				showApartments();
			}

			else if(response == 4)
			{
				showTenants();
			}

			else if(response == 5)
			{
				suitableApartment();
			}

			else if(response == 6)
			{
				enterLease();
			}
			else if(response == 7)
			{
				showLeases();
			}
			else if(response == 8)
			{
				showRentedApt();
			}

			else if(response == 9)
			{
				rentedApt();
			}

			else if(response == 10)
			{
				showAvailableApt();
			}
			else if(response == 11)
			{
				availableApt();
			}
			else if(response == 12)
			{
				endLease();
			}
			else if(response < 1 || response > 13)
			{
				System.out.println("Please check menu, figure entered does not correspond to available options.");
			}
		}while(response != 13);
		writeToApartmentsFile();
		writeToTenantsFile();
		writeToLeasesFile();
		System.out.println("The letting data has been saved - Goodbye!");
	}

	public static void main(String[] args) 
	{
		Letting letting = new Letting();
		System.out.println("AUNGIER PROPERTIES LTD.");
		letting.menu();		
	}
}