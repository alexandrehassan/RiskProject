import java.util.Scanner;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 *
 * This parser reads user input and tries to interpret it as a command or
 * as a number. To return a command, it divides the input into a command word
 * and the command details before returning said command. This command word
 * must come from a set of known command words. If the command words is not
 * a part of this list, it returns an unknown command.
 *
 * To return a number, the Parser checks that the input is indeed a number
 * before returning, and a default value otherwise.
 *
 * @author  Jonah Gaudet
 * @version 2020.10.19
 */

public class Parser
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand()
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String otherWords = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            /*if (tokenizer.hasNext()) {
                otherWords = tokenizer.next();
                while (tokenizer.hasNext()) {
                    otherWords += " " + tokenizer.next();
                }
            }
             */
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommandWord(word1)) {
            return new Command(word1);
        }
        else {
            return new Command(null);
        }
    }

    public String getCountryName()
    {
        String inputLine;   // will hold the full input line
        String countryName = "";

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            countryName = tokenizer.next();      // get first word
            while (tokenizer.hasNext()) {
                countryName += " " + tokenizer.next();
            }
        }

        if (countryName.equals(""))
            throw new IllegalArgumentException("Must provide input");
        return countryName;
    }

    /**
     * @return A number for the user. Returns number if valid, -1 if not
     */
    public int getNumber () {
        String inputLine;
        System.out.print("> ");
        inputLine = reader.nextLine();
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            try {
                return Integer.parseInt(tokenizer.next());
            } catch (Exception e) {
                System.out.println("Must be a number");
                return -1;
            }
        }
        System.out.println("Must provide input");
        return -1;
    }

    /**
     * Print out a list of valid command words.
     */
    public void showCommands()
    {
        commands.showAllCommands();
    }
}
