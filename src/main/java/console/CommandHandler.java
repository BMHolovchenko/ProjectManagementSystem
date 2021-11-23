package console;

import console.commands.*;
import console.commands.interfaces.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.regex.Matcher;

import static console.commands.interfaces.Command.pattern;


public class CommandHandler {

    private static final Logger LOGGER = LogManager.getLogger(CommandHandler.class);

    Command mainMenu = new MainMenuCommand();
    Command activeMenu = mainMenu;

    public CommandHandler() {
        this.activeMenu.printActiveMenu();
    }


    public void handleCommand (String params) throws SQLException {
        Matcher matcher = pattern.matcher(params);
        if (matcher.find()){
            String command = matcher.group();
            if ("exit".equalsIgnoreCase(command)){
                System.exit(0);
            } else if ("active".equalsIgnoreCase(command)){
                this.activeMenu.printActiveMenu();
            } else if ("main".equalsIgnoreCase(command)){
                this.activeMenu = mainMenu;
                this.activeMenu.printActiveMenu();
            } else {
                this.activeMenu.handle(params, cm -> {
                    this.activeMenu = cm;
                    this.activeMenu.printActiveMenu();
                });
            }
        } else {
            LOGGER.warn("No command.");
        }
    }

}
