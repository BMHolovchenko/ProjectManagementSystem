package Dao;

import model.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SkillDao extends AbstractDao <Skill> {
    @Override
    String getTableName() {
        return "skills";
    }

    @Override
    Skill mapToEntity(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();
        skill.setId(resultSet.getLong("id"));
        skill.setName(resultSet.getString("name"));
        skill.setLevel(resultSet.getString("level"));
        return skill;
    }

    @Override
    public Optional<Skill> create(Skill skill) {
        String sql = "insert into skills (name, level) values (?, ?)";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, skill.getName());
            ps.setString(2, skill.getLevel());
        });
        System.out.println("Record was created.");
        return Optional.empty();
    }

    @Override
    public void update(Skill skill) {
        String sql = "update skills set name = ?, level = ? where id = ?";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, skill.getName());
            ps.setString(2, skill.getLevel());
            ps.setLong(3, skill.getId());
        });
        System.out.println("Record was updated.");
    }
}
