import console.CommandHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) throws SQLException, IOException {
        LOGGER.info("Start application.");
        CommandHandler commandHandler = new CommandHandler();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Application started.");
        while (scanner.hasNext()){
            commandHandler.handleCommand(scanner.nextLine());
        }
        scanner.close();
        LOGGER.info("End application.");
    }
}