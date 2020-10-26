/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author Jonah Gaudet
 * @version 2020.10.19
 */
public class CommandWords {
    //A constant array containing all the commands words
    private static final String[] validCommands = {
            "attack",
            "end",
            "help",
            "state"
    };

    /**
     * Default constructor for CommandWords
     */
    public CommandWords()
    {
        //
    }

    /**
     * Displays all the possible commands on the terminal
     */
    public void showAllCommands()
    {
        for(int i = 0; i < validCommands.length; i++) {
            System.out.println("> " + validCommands[i]);
        }
    }

    /**
     * Checks if a String qualifies as a command word
     * @param aString the word to check
     * @return true is the input is a command word, false otherwise
     */
    public boolean isCommandWord(String aString)
    {
        for (String validCommand : validCommands) {
            if (validCommand.equals(aString.toLowerCase()))
                return true;
        }
        return false;
    }
}
