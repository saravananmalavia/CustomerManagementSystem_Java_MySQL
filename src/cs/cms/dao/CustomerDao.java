package cs.cms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import com.mysql.cj.protocol.Resultset;

import cs.cms.dto.Customer;

public class CustomerDao {

	public static int addCustomer(Customer customer) {

		Connection connection = null;
		PreparedStatement psmt = null;
		int result = 0;
		String sql = "";

		try {

			connection = DbHelper.getConnection();

			sql = "INSERT INTO tbl_customer (customerName, mobileNumber, email, address) VALUES (?, ?, ?, ?)";

			psmt = connection.prepareStatement(sql);

			psmt.setString(1, customer.getCustomerName());
			psmt.setString(2, customer.getMobileNumber());
			psmt.setString(3, customer.getEmail());
			psmt.setString(4, customer.getAddress());

			result = psmt.executeUpdate();
			psmt.close();
			connection.close();

		} catch (Exception e) {
			System.out.println("Error  CustomerDao : addCustomer " + e);
		}

		return result;
	}

	public static int updateCustomer(Customer customer) {

		int status = 0;

		Connection connection = null;
		PreparedStatement psmt = null;

		int result = 0;
		String sql = "";
		try {

			connection = DbHelper.getConnection();
			sql = "UPDATE cms.tbl_customer SET customerName = ? , mobileNumber = ? , email = ? , address = ? WHERE  pk_customerId = ? ";
			psmt = connection.prepareStatement(sql);

			psmt.setString(1, customer.getCustomerName());
			psmt.setString(2, customer.getMobileNumber());
			psmt.setString(3, customer.getEmail());
			psmt.setString(4, customer.getAddress());
			psmt.setInt(5, customer.getCustomerId());

			result = psmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(" error  CustomerDao : UpdateCustomer " + e);
		}

		return result;
	}

	public static int deleteCustomer(int customerId) {

		Connection connection = null;
		PreparedStatement psmt = null;

		String sql, sql1 = "";

		int status = 0;
		try {

			connection = DbHelper.getConnection();
			sql1 = "DELETE FROM cms.tbl_customer WHERE pk_customerId IN (?)";
			psmt = connection.prepareStatement(sql1);

			psmt.setInt(1, customerId);

			status = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(" error  CustomerDao : DeleteCustomer " + e);
		}

		return status;
	}

	public static Customer getCustomer(int customerId) {

		int result = 0;

		Connection connection = null;
		PreparedStatement psmt = null;
		String sql = "";
		ResultSet rs = null;

		Customer customer = null;
		String customerName, mobileNumber, email, address;
		int dbCustomerId;

		try {
			connection = DbHelper.getConnection();

			sql = "SELECT pk_customerId, customerName, mobileNumber, email, address FROM cms.tbl_customer WHERE pk_customerId = ? ";
			psmt = connection.prepareStatement(sql);

			psmt.setInt(1, customerId);

			rs = psmt.executeQuery();

			if (rs != null) {
				if (rs.next()) {

					dbCustomerId = rs.getInt("pk_CustomerId");
					customerName = rs.getString("customerName");

					mobileNumber = rs.getString("mobileNumber");
					email = rs.getString("email");
					address = rs.getString("address");

					customer = new Customer(dbCustomerId, customerName, mobileNumber, email, address);

				}

			}

		} catch (SQLException e) {

			System.out.println("CustomerDao : getCustomer " + e);
		}

		return customer;
	}

	public static Hashtable<Integer, Customer> getCustomers() {

		Hashtable<Integer, Customer> htCustomer = null;

		Connection connection = null;
		Customer customer = null;
		Statement stmt = null;

		String sql = "";
		ResultSet rs = null;
		String customerName, mobileNumber, email, address;
		int dbCustomerId;
		try {

			connection = DbHelper.getConnection();

			sql = "SELECT pk_customerId, customerName, mobileNumber, email, address FROM cms.tbl_customer";
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs != null) {

				htCustomer = new Hashtable<Integer, Customer>();

				while (rs.next()) {

					dbCustomerId = rs.getInt("pk_CustomerId");
					customerName = rs.getString("customerName");

					mobileNumber = rs.getString("mobileNumber");
					email = rs.getString("email");
					address = rs.getString("address");

					customer = new Customer(dbCustomerId, customerName, mobileNumber, email, address);

					htCustomer.put(dbCustomerId, customer);
				}

			}

		} catch (Exception e) {
			System.out.println(" error  CustomerDao : getCustomers " + e);
		}

		return htCustomer;
	}
}
