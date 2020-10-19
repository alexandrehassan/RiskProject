import java.util.Scanner;

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
        String word2 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
                while (tokenizer.hasNext()) {
                    word2 += " " + tokenizer.next();
                }
            }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommandWord(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2);
        }
    }

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
