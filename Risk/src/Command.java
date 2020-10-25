/**
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and command details
 * that describe the specifics of the general command word
 *
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>. Otherwise, the command is created
 * properly and the remaining words are added as the command details.
 *
 * If the command had only one word, then the second word is <null>. This
 * happens by default for some commands
 *
 * @author Jonah Gaudet
 * @version 2020.10.19
 */

public class Command {
    final String commandWord;
    final String commandDetails;

    /**
     * Constructor for the command
     * @param firstWord commandWord for the command
     * @param commandDetails command details for the command
     */
    public Command(String firstWord, String commandDetails)
    {
        commandWord = firstWord;
        this.commandDetails = commandDetails;
    }

    /**
     * Returns if the command is unknown or not
     * @return true if unknown, false if not
     */
    public boolean isUnknown()
    {
        return (commandWord != null);
    }

    /**
     * Returns if the command has command details
     * @return true if command details exist, false if not
     */
    public boolean hasCommandDetails()
    {
        return (commandDetails != null);
    }

    /**
     * Gets the command's command word
     * @return the command word
     */
    public String getCommandWord () {
        return commandWord;
    }

    /**
     * Gets the command's command details
     * @return the command details
     */
    public String getCommandDetails () {
        return commandDetails;
    }

    /**
     * Prints the command and it's details (if available)
     */
    public void printCommand () {
        if (hasCommandDetails()) {
            System.out.println(commandWord + " " + commandDetails);
            return;
        }
        System.out.println(commandWord);
    }
}
