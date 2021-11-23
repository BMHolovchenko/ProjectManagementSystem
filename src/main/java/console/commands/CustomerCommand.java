package console.commands;

import Dao.CustomerDao;
import console.commands.interfaces.Command;
import model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class CustomerCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CustomerCommand.class);
    private static final CustomerDao customerDao = new CustomerDao();

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
        System.out.println("Customer command is " + params);
    }

    private void delete(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional<Customer> customer = customerDao
                .get(Long.parseLong(paramsArray[0]));
        if (customer.isPresent()){
            customerDao.delete(customer.get());
        } else {
            System.out.println("Customer with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void update(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional <Customer> optionalCustomer = customerDao
                .get(Long.parseLong(paramsArray[0]));
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setName(paramsArray[1]);
            customer.setDescription(paramsArray[2]);
            customerDao.update(customer);
        } else {
            System.out.println("Customer with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void get(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Optional<Customer> customer = customerDao
                .get(Long.parseLong(paramsArray[0]));
        if (customer.isPresent()){
            System.out.println(customer.get());
        } else {
            System.out.println("Customer with parameters " + paramsArray[0] + " not exists.");
        }
    }

    private void getAll() {
        List<Customer> all = customerDao.getAll();
        System.out.println("Returned " + all.size() + " customers.");
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

    private void create(String subParams) {
        String [] paramsArray = subParams.split(" ");
        Customer customer = new Customer();
        customer.setName(paramsArray[0]);
        customer.setDescription(paramsArray[1]);
        customerDao.create(customer);
    }

    @Override
    public void printActiveMenu() {
        LOGGER.info("-------Customer menu-------");
    }

}

