package console.commands;

import config.DataSourceHolder;
import console.commands.interfaces.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

public class ExtendedCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ExtendedCommand.class);

    @Override
    public void handle(String params, Consumer<Command> setActive) throws SQLException {
        String [] paramsArray = params.split(" ");
        String subParams = String.join(" ", params.replace(paramsArray[0] + " ", ""));
        switch (paramsArray[0]) {
            case "getProjectSalaries" -> getProjectSalaries(subParams);
            case "getDevelopersByProject" -> getDevelopersByProject(subParams);
            case "getJavaDevelopers" -> getJavaDevelopers();
            case "getMiddleDevelopers" -> getMiddleDevelopers();
            case "getProjectsInfo" -> getProjectsInfo();
        }
        System.out.println("Extended command is " + params);
    }

    private void getProjectSalaries(String params) throws SQLException {
        Connection connection = DataSourceHolder.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("select sum(salary) as salaries from developers \n"
                        + "inner join developers_projects on developers.id = developers_projects.developer_id \n"
                        + "inner join projects on developers_projects.project_id = projects.id \n"
                        + "where projects.name = '"
                        + params + "';");

        while (resultSet.next()) {
            LOGGER.info("Salaries sum: " + resultSet.getString(1));
        }
        connection.close();
    }

    private void getDevelopersByProject(String params) throws SQLException {
        Connection connection = DataSourceHolder.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("select developers.name from developers \n"
                        + "inner join developers_projects on developers_projects.developer_id = developers.id \n"
                        + "inner join projects on developers_projects.project_id = projects.id \n"
                        + "where projects.name = '" + params + "';");

        while (resultSet.next()) {
            LOGGER.info("Developer: " + resultSet.getString(1));
        }
        connection.close();
    }

    public static void getJavaDevelopers() throws SQLException {
        Connection connection = DataSourceHolder.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("select developers.name from developers \n"
                        + "inner join developers_skills on developers_skills.developer_id = developers.id \n"
                        + "inner join skills on developers_skills.skill_id = skills.id \n"
                        + "where skills.name = 'Java'");
        while (resultSet.next()) {
            LOGGER.info("Java developer: " + resultSet.getString(1));
        }
        connection.close();
    }

    public static void getMiddleDevelopers() throws SQLException {
        Connection connection = DataSourceHolder.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("select developers.name from developers \n"
                        + "inner join developers_skills on developers_skills.developer_id = developers.id \n"
                        + "inner join skills on developers_skills.skill_id = skills.id \n"
                        + "where skills.level = 'Middle'");
        while (resultSet.next()) {
            LOGGER.info("Middle developer: " + resultSet.getString(1));
        }
        connection.close();
    }

    public static void getProjectsInfo () throws SQLException {
        Connection connection = DataSourceHolder.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("select projects.date, projects.name, count(developers_projects.developer_id) from projects \n"
                        + "inner join developers_projects on developers_projects.project_id = projects.id \n"
                        + "group by projects.date, projects.name");
        while (resultSet.next()) {
            LOGGER.info("Project : " + resultSet.getString(1) + " "
                    + resultSet.getString(2) + " "
                    + resultSet.getString(3));
        }
        connection.close();
    }

    @Override
    public void printActiveMenu() {
        LOGGER.info("-------Extended commands menu-------");
        LOGGER.info("-------Command list-------");
        LOGGER.info(" [getProjectSalaries, getDevelopersByProject, getJavaDevelopers, getMiddleDevelopers, getProjectsInfo]");
        LOGGER.info(" While using [getProjectSalaries, getDevelopersByProject] you have to enter project name after space");
    }
}
