package cs.cms.app;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

import cs.cms.dao.CustomerDao;
import cs.cms.dto.Customer;
import cs.cms.util.Helper;

public class CmsApp {

	public static void main(String[] args) {

		int option = 0, output = 0;
		Scanner sc = new Scanner(System.in);

		String menuHeader = "     CUSTOMER MODULE     ";
		String line = Helper.generateLine(menuHeader.length());

		while (option != 9) {

			System.out.println(line);
			System.out.println(menuHeader);
			System.out.println(line);
			System.out.println("1. Display All Customer");
			System.out.println("2. Add a new Customer");
			System.out.println("3. Delete a new Customer");
			System.out.println("4. Update  Customer");
			System.out.println("5. Display Customer by Id ");
			System.out.println("9. exit");
			System.out.println(line);
			System.out.println("Enter the option from the menu");
			System.out.println("");
			option = sc.nextInt();

			switch (option) {

			case 1:

				getCustomers();

				break;
			case 2: {

				System.out.println("Enter the customer details ");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				output = addCustomer();

				if (output != 0) {
					System.out.println("Customer added sucessfully");
				} else {
					System.out.println("Customer not added");
				}
			}

				break;
			case 3: {
				int deleteStatus = deleteCustomer();

				if (deleteStatus != 0) {
					System.out.println("Customer deleted successfully");

				}
			}

				break;
			case 4: {
				int updateStatus = updateCustomer();

				if (updateStatus != 0) {
					System.out.println("Customer updated successfully");

				}
			}

				break;

			case 5:

				System.out.println("Enter the customer id to be searched");
				int customerId = sc.nextInt();
				displayCustomerById(customerId);

				break;

			case 9:

				System.out.println("Thank You exiting menu .....");
				System.exit(0);

			default:
				System.out.println("wrong input");

			}

		}

	}

	private static int addCustomer() {

		String customerName, mobileNumber, email, address;
		int result = 0;
		Customer customer = null;
		try {

			System.out.println("Enter customer name : ");
			customerName = Helper.getAlphabet();
			System.out.println("Enter mobile number : ");
			mobileNumber = Helper.getS();
			System.out.println("Enter email : ");
			email = Helper.getS();
			System.out.println("Enter address : ");
			address = Helper.getS();

			customer = new Customer(customerName, mobileNumber, email, address);

			result = CustomerDao.addCustomer(customer);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public static void getCustomers() {

		Hashtable<Integer, Customer> htCustomer = null;

		try {

			htCustomer = CustomerDao.getCustomers();

			displayAllCustomer(htCustomer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int deleteCustomer() {

		int result = 0, customerId = 0;
		char choice;
		boolean output;

		try {

			System.out.println("Enter customerId you wish to delete : ");
			customerId = Helper.getI();
			// displayCustomerById(customerId);
			output = displayCustomerById(customerId);
			if (output == true) {
				System.out.println("Please confirm you wish to  delete the above customer :  Y/N ");
				choice = Helper.getC();
				if (choice == 'Y' || choice == 'y' && output == true) {
					result = CustomerDao.deleteCustomer(customerId);
				} else if (choice == 'N' || choice == 'n' && output == true) {
					System.out.println("deletion aborted");
					result = 0;
				} else {
					System.out.println("wrong input ");
					result = 0;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public static int updateCustomer() {

		int result = 0, customerId = 0;
		String customerName, mobileNumber, email, address;

		Customer customer = null;
		Customer existingCustomer = null;

		try {

			System.out.println("Enter customerId you wish to update : ");
			customerId = Helper.getI();

			existingCustomer = CustomerDao.getCustomer(customerId);

			if (existingCustomer != null) {

				displayCustomer(existingCustomer);

				System.out.println(" ");

				System.out.println("Enter customer name : ");
				customerName = Helper.getAlphabet();
				System.out.println("Enter mobile number : ");
				mobileNumber = Helper.getS();
				System.out.println("Enter email : ");
				email = Helper.getS();
				System.out.println("Enter address : ");
				address = Helper.getS();

				customer = new Customer(customerId, customerName, mobileNumber, email, address);

				result = CustomerDao.updateCustomer(customer);

				if (result != 0) {

					displayCustomerById(customerId);
				}

			} else {

				System.out.println("No such customer available");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;

	}

	public static boolean displayCustomerById(int customerId) {

		Customer customer = null;
		boolean flag = true;
		try {

			customer = CustomerDao.getCustomer(customerId);
			if (customer != null) {

				displayCustomer(customer);

			} else {
				System.out.println("Customer is not available");
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;

	}

	/**
	 * @param customer
	 */
	public static void displayCustomer(Customer customer) {

		String header = "          Customer Details          ";
		String line = Helper.generateLine(header.length());
		System.out.println(line);
		System.out.println(header);
		System.out.println(line);

		System.out.println("CustomerId    : " + customer.getCustomerId());
		System.out.println("Customer Name : " + customer.getCustomerName());
		System.out.println("Mobile Number : " + customer.getMobileNumber());
		System.out.println("Email         : " + customer.getEmail());
		System.out.println("Address       : " + customer.getAddress());

		System.out.println(line);
	}

	public static void displayAllCustomer(Hashtable<Integer, Customer> htCustomer) {

		int serialNumber = 1;
		int numberOfSpaces;
		String blankSpace = "";
		String line = "";
		try {

			if (htCustomer != null) {

				String header1 = "|  S.No. ";
				String header2 = "|  Customer Id  ";
				String header3 = "|   Customer Name   ";
				String header4 = "|  Mobile Number  ";
				String header5 = "|   Email         ";
				String header6 = "|        Address         |";

				String tableHeading = header1 + header2 + header3 + header4 + header5 + header6;

				line = Helper.generateLine(tableHeading.length());

				System.out.println(line);
				System.out.println(tableHeading);
				System.out.println(line);

				Enumeration<Integer> customerKeys = htCustomer.keys();

				while (customerKeys.hasMoreElements()) {

					int customerId = customerKeys.nextElement();

					Customer customer = htCustomer.get(customerId);

					numberOfSpaces = header1.length() - Integer.toString(serialNumber).length();
					blankSpace = Helper.generateBlankSpace(numberOfSpaces, "Coloumn");
					System.out.print("| " + serialNumber + blankSpace);

					numberOfSpaces = header2.length() - Integer.toString(customer.getCustomerId()).length();
					blankSpace = Helper.generateBlankSpace(numberOfSpaces, "Coloumn");
					System.out.print("| " + customer.getCustomerId() + blankSpace);

					numberOfSpaces = header3.length() - customer.getCustomerName().length();
					blankSpace = Helper.generateBlankSpace(numberOfSpaces, "Coloumn");
					System.out.print("| " + customer.getCustomerName() + blankSpace);

					numberOfSpaces = header4.length() - customer.getMobileNumber().length();
					blankSpace = Helper.generateBlankSpace(numberOfSpaces, "Coloumn");
					System.out.print("| " + customer.getMobileNumber() + blankSpace);

					numberOfSpaces = header5.length() - customer.getEmail().length();
					blankSpace = Helper.generateBlankSpace(numberOfSpaces, "Coloumn");
					System.out.print("| " + customer.getEmail() + blankSpace);

					numberOfSpaces = header6.length() - customer.getAddress().length();
					blankSpace = Helper.generateBlankSpace(numberOfSpaces, "endColoumn");

					System.out.print("| " + customer.getAddress() + blankSpace + "| \n");

					blankSpace = "";

					serialNumber++;
				}

				System.out.println(line);
			} else {

				System.out.println("No data availble");
			}

		} catch (Exception e) {
			System.out.println("error CmsApp:displayCustomerTable : " + e);
		}
	}

}
