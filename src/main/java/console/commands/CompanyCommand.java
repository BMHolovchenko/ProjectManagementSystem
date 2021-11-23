package console.commands;

import Dao.CompanyDao;
import console.commands.interfaces.Command;
import model.Company;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class CompanyCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CompanyCommand.class);
    private static final CompanyDao companyDao = new CompanyDao();

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
        System.out.println("Company command is " + params);
    }

    private void delete(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional<Company> company = companyDao
                .get(Long.parseLong(paramsArray[0]));
        if (company.isPresent()){
            companyDao.delete(company.get());
        } else {
            System.out.println("Company with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void update(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional <Company> optionalCompany = companyDao
                .get(Long.parseLong(paramsArray[0]));
        if (optionalCompany.isPresent()){
            Company company = optionalCompany.get();
            company.setName(paramsArray[1]);
            company.setDescription(paramsArray[2]);
            companyDao.update(company);
        } else {
            System.out.println("Company with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void get(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional<Company> company = companyDao
                .get(Long.parseLong(paramsArray[0]));
        if (company.isPresent()){
            System.out.println(company.get());
        } else {
            System.out.println("Company with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void getAll() {
        List<Company> all = companyDao.getAll();
        System.out.println("Returned " + all.size() + " companies.");
        for (Company company : all) {
            System.out.println(company);
        }
    }

    private void create(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Company company = new Company();
        company.setName(paramsArray[0]);
        company.setDescription(paramsArray[1]);
        companyDao.create(company);
    }

    @Override
    public void printActiveMenu() {
        LOGGER.info("-------Company menu-------");
    }
}