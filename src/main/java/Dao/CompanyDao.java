package Dao;

import model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CompanyDao extends AbstractDao <Company>{
    @Override
    String getTableName() {
        return "companies";
    }

    @Override
    Company mapToEntity(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("id"));
        company.setName(resultSet.getString("name"));
        company.setDescription(resultSet.getString("description"));
        return company;
    }

    @Override
    public Optional<Company> create(Company company) {
        String sql = "insert into companies (name, description) values (?, ?)";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, company.getName());
            ps.setString(2, company.getDescription());
        });
        System.out.println("Record was created.");
        return Optional.empty();
    }

    @Override
    public void update(Company company) {
        String sql = "update companies set name = ?, description = ? where id = ?";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, company.getName());
            ps.setString(2, company.getDescription());
            ps.setLong(3, company.getId());
        });
        System.out.println("Record was updated.");
    }
}
