public class CommandWords {
    private static final String[] validCommands = {
            "attack", "move", "end", "help"
    };

    public CommandWords()
    {
        //
    }

    public void showAllCommands()
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }

    public boolean isCommandWord(String aString)
    {
        for (String validCommand : validCommands) {
            if (validCommand.equals(aString.toLowerCase()))
                return true;
        }
        return false;
    }
}
