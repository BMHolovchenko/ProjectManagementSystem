package Dao;

import model.Company;
import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerDao extends AbstractDao <Customer>{
    @Override
    String getTableName() {
        return "customers";
    }

    @Override
    Customer mapToEntity(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setName(resultSet.getString("name"));
        customer.setDescription(resultSet.getString("description"));
        return customer;
    }

    @Override
    public Optional<Customer> create(Customer customer) {
        String sql = "insert into customers (name, description) values (?, ?)";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getDescription());
        });
        System.out.println("Record was created.");
        return Optional.empty();
    }

    @Override
    public void update(Customer customer) {
        String sql = "update customers set name = ?, description = ? where id = ?";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getDescription());
            ps.setLong(3, customer.getId());
        });
        System.out.println("Record was updated.");
    }
}
