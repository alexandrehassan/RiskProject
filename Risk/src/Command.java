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
    String commandWord;


    /**
     * Constructor for the command
     * @param firstWord commandWord for the command
     */
    public Command(String firstWord)
    {
        commandWord = firstWord;
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
     * Gets the command's command word
     * @return the command word
     */
    public String getCommandWord () {
        return commandWord;
    }

    /**
     * Prints the command and it's details (if available)
     */
    public void printCommand () {
        System.out.println(commandWord);
    }
}
