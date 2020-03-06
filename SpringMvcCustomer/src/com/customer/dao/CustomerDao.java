package com.customer.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import com.customer.bean.Customer;

public class CustomerDao {
	// create object of jdbc template
	private JdbcTemplate jdbcTemplate;
	// query for insert
	private String INSERT_CUSTOMER = "INSERT INTO CUSTOMER (CUSTNAME,CITY,CONTACTNUMBER,ORDERDATE,TOTALAMOUNT,ISACTIVE)values(?,?,?,curDate(),?,'Y')";
	// query for display
	private String DISPLAY_CUSTOMER = "SELECT * FROM CUSTOMER";

	// query for search using id
	private String SEARCH_CUSTOMER_ID = "SELECT * FROM CUSTOMER WHERE CUSTID=?";

	// query for search using DATE
	private String SEARCH_CUSTOMER_NAME = "SELECT * FROM CUSTOMER WHERE CUSTNAME=?";
	// query for update
	private String UPDATE_CUSTOMER = "UPDATE CUSTOMER SET CUSTNAME=?,CITY=?,CONTACTNUMBER=?,TOTALAMOUNT=? WHERE CUSTID=?";

	//delte query
	private String DELETE_CUSTOMER = "UPDATE CUSTOMER SET ISACTIVE='N' WHERE CUSTID=?";
	// getters and setters of jdbc template
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// method for add details
	public void addDetails(final Customer customer) {
		// call the prepared statement query
		jdbcTemplate.execute(INSERT_CUSTOMER,
				new PreparedStatementCallback<Boolean>() {

					@Override
					public Boolean doInPreparedStatement(
							PreparedStatement statement) throws SQLException,
							DataAccessException {
						// set the values
						statement.setString(1, customer.getCustName());
						statement.setString(2, customer.getCity());
						statement.setString(3, customer.getContactNumber());
						statement.setInt(4, customer.getTotalAmount());

						return statement.execute();
					}

				});

	}

	// list details
	public List<Customer> getDetails() {

		// create object to get the deatils
		List<Customer> customers = jdbcTemplate.query(DISPLAY_CUSTOMER,
				new CustomerMapper());

		return customers;

	}

	public class CustomerMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet resultSet, int rowNumber)
				throws SQLException {

			// create object of customer
			Customer customer = new Customer();
			// get values
			customer.setCustId(resultSet.getInt("custId"));
			customer.setCustName(resultSet.getString("custName"));
			customer.setCity(resultSet.getString("city"));
			customer.setContactNumber(resultSet.getString("contactNumber"));
			customer.setOrderDate(resultSet.getDate("orderDate"));
			customer.setTotalAmount(resultSet.getInt("totalAmount"));
			customer.setIsActive(resultSet.getString("isActive"));

			return customer;
		}

	}

	// method for search using id
	public Customer searchById(int custId) {

		return jdbcTemplate.queryForObject(SEARCH_CUSTOMER_ID,
				new Object[] { custId }, new CustomerMapper());

	}

	// method for search using id
	public Customer searchByName(String custName) {

		return jdbcTemplate.queryForObject(SEARCH_CUSTOMER_NAME,
				new Object[] { custName }, new CustomerMapper());

	}

	// method for update
	public void UpdateCustomer(final Customer customer, final int id) {

		jdbcTemplate.execute(UPDATE_CUSTOMER,
				new PreparedStatementCallback<Boolean>() {

					public Boolean doInPreparedStatement(
							PreparedStatement statement) throws SQLException,
							DataAccessException {
						statement.setString(1, customer.getCustName());
						statement.setString(2, customer.getCity());
						statement.setString(3, customer.getContactNumber());
						statement.setInt(4, customer.getTotalAmount());
						statement.setInt(5, id);

						return statement.execute();

					}
				});
	}

	// method for delete
	public void deleteCustomer(final int id) {

		jdbcTemplate.execute(DELETE_CUSTOMER, new PreparedStatementCallback<Boolean>() {

			public Boolean doInPreparedStatement(PreparedStatement statement)
					throws SQLException, DataAccessException {
				statement.setInt(1, id);

				return statement.execute();

			}
		});

	}

}
