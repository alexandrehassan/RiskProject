public class CommandWords {
    private static final String[] validCommands = {
            "attack", "move", "end", "help", "state"
    };

    private static final String[] commandMethod = {
            "Attack _____ with/from ____",
            "Move __ troops from _____ to _____",
            "End turn",
            "Help",
            "State",
    };

    public CommandWords()
    {
        //
    }

    public void showAllCommands()
    {
        for(int i = 0; i < validCommands.length; i++) {
            System.out.println(validCommands[i] + ":  '" + commandMethod[i] + "'");
        }
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
