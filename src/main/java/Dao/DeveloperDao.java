package Dao;

import model.Developer;
import model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DeveloperDao extends AbstractDao <Developer> {

    @Override
    String getTableName() {
        return "developers";
    }

    @Override
    Developer mapToEntity(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        developer.setId(resultSet.getLong("id"));
        developer.setName(resultSet.getString("name"));
        developer.setAge(resultSet.getInt("age"));
        developer.setGender(resultSet.getString("gender"));
        developer.setSalary(resultSet.getInt("salary"));
        return developer;
    }

    @Override
    public Optional<Developer> create(Developer developer) {
        String sql = "insert into developers (name, age, gender, salary) values (?, ?, ?, ?)";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, developer.getName());
            ps.setInt(2, developer.getAge());
            ps.setString(3, developer.getGender());
            ps.setInt(4, developer.getSalary());
        });
        System.out.println("Record was created.");
        return Optional.empty();
    }

    @Override
    public void update(Developer developer) {
        String sql = "update developers set name = ?, age = ?, gender = ?, salary = ? where id = ?";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, developer.getName());
            ps.setInt(2, developer.getAge());
            ps.setString(3, developer.getGender());
            ps.setInt(4, developer.getSalary());
            ps.setLong(5, developer.getId());
        });
        System.out.println("Record was updated.");
    }
}
