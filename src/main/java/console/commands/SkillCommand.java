package console.commands;

import Dao.SkillDao;
import console.commands.interfaces.Command;
import model.Skill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class SkillCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SkillCommand.class);
    SkillDao skillDao = new SkillDao();

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
        System.out.println("Skill command is " + params);
    }

    private void delete(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional <Skill> skill = skillDao
                .get(Long.parseLong(paramsArray[0]));
        if (skill.isPresent()){
            skillDao.delete(skill.get());
        } else {
            System.out.println("Skill with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void update(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional <Skill> optionalSkill = skillDao
                .get(Long.parseLong(paramsArray[0]));
        if (optionalSkill.isPresent()){
            Skill skill = optionalSkill.get();
            skill.setName(paramsArray[1]);
            skill.setLevel(paramsArray[2]);
            skillDao.update(skill);
        } else {
            System.out.println("Skill with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void get(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional<Skill> skill = skillDao
                .get(Long.parseLong(paramsArray[0]));
        if (skill.isPresent()){
            System.out.println(skill.get());
        } else {
            System.out.println("Skill with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void getAll() {
        List<Skill> all = skillDao.getAll();
        System.out.println("Returned " + all.size() + " skills.");
        for (Skill skill : all) {
            System.out.println(skill);
        }
    }

    private void create(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Skill skill = new Skill();
        skill.setName(paramsArray[0]);
        skill.setLevel(paramsArray[1]);
        skillDao.create(skill);
    }

    @Override
    public void printActiveMenu() {
        LOGGER.info("-------Skill menu-------");
    }

}
