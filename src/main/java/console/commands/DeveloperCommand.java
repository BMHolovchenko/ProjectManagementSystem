package console.commands;

import Dao.DeveloperDao;
import console.commands.interfaces.Command;
import model.Developer;
import model.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class DeveloperCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeveloperCommand.class);
    private static final DeveloperDao developerDao = new DeveloperDao();

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
        System.out.println("Developer command is " + params);
    }

    private void create(String params){
        String [] paramsArray = params.split(" ");
        Developer developer = new Developer();
        developer.setName(paramsArray[0]);
        developer.setGender(paramsArray[1]);
        developer.setAge(Integer.valueOf(paramsArray[2]));
        developer.setSalary(Integer.valueOf(paramsArray[3]));
        developerDao.create(developer);
    }

    private void get(String params){
        String [] paramsArray = params.split(" ");
        Optional <Developer> developer = developerDao
                .get(Long.parseLong(paramsArray[0]));
        if (developer.isPresent()){
            System.out.println(developer.get());
        } else {
            System.out.println("Developer with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void delete(String params){
        String [] paramsArray = params.split(" ");
        Optional <Developer> developer = developerDao
                .get(Long.parseLong(paramsArray[0]));
        if (developer.isPresent()){
            developerDao.delete(developer.get());
        } else {
            System.out.println("Developer with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void getAll(){
        List <Developer> all = developerDao.getAll();
        System.out.println("Returned " + all.size() + " developers.");
        for (Developer developer : all) {
            System.out.println(developer);
        }
    }

    private void update (String params){
        String [] paramsArray = params.split(" ");
        Optional <Developer> optionalDeveloper = developerDao
                .get(Long.parseLong(paramsArray[0]));
        if (optionalDeveloper.isPresent()){
            Developer developer = optionalDeveloper.get();
            developer.setName(paramsArray[1]);
            developer.setAge(Integer.valueOf(paramsArray[2]));
            developer.setGender(paramsArray[3]);
            developer.setSalary(Integer.valueOf(paramsArray[4]));
            developerDao.update(developer);
        } else {
            System.out.println("Developer with parameters " + paramsArray[0] + " not exists.");
        }
    }

    @Override
    public void printActiveMenu() {
        LOGGER.info("-------Developer menu-------");
    }

}
