package console.commands;

import Dao.ProjectDao;
import console.commands.interfaces.Command;
import model.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ProjectCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ProjectCommand.class);
    private static final ProjectDao projectDao = new ProjectDao();

    @Override
    public void handle(String params, Consumer<Command> setActive) {
        String [] paramsArray = params.split(" ");
        String subParams = String.join(" ", params.replace(paramsArray[0] + " ", ""));
        switch (paramsArray[0]) {
            case "create" -> create(subParams);
            case "getAll" -> getAll();
            case "get" -> get(subParams);
            case "update" -> update(subParams);
            case "delete" -> delete(subParams);
        }
        System.out.println("Project command is " + params);
    }

    private void delete(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional<Project> project = projectDao
                .get(Long.parseLong(paramsArray[0]));
        if (project.isPresent()){
            projectDao.delete(project.get());
        } else {
            System.out.println("Project with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void update(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional <Project> optionalProject = projectDao
                .get(Long.parseLong(paramsArray[0]));
        if (optionalProject.isPresent()){
            Project project = optionalProject.get();
            project.setName(paramsArray[1]);
            project.setDescription(paramsArray[2]);
            project.setDate(Date.valueOf(paramsArray[3]));
            projectDao.update(project);
        } else {
            System.out.println("Project with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void get(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional<Project> project = projectDao
                .get(Long.parseLong(paramsArray[0]));
        if (project.isPresent()){
            System.out.println(project.get());
        } else {
            System.out.println("Project with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void getAll() {
        List<Project> all = projectDao.getAll();
        System.out.println("Returned " + all.size() + " projects.");
        for (Project project : all) {
            System.out.println(project);
        }
    }

    private void create(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Project project = new Project();
        project.setName(paramsArray[0]);
        project.setDescription(paramsArray[1]);
        project.setDate(Date.valueOf(paramsArray[2]));
        projectDao.create(project);
    }

    @Override
    public void printActiveMenu() {
        LOGGER.info("-------Project menu-------");
    }

}

