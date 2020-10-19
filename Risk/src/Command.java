public class Command {
    String commandWord;
    String commandDetails;

    public Command(String firstWord)
    {
        commandWord = firstWord;
        commandDetails = null;
    }

    public Command(String firstWord, String commandDetails)
    {
        commandWord = firstWord;
        this.commandDetails = commandDetails;
    }

    public boolean isUnknown()
    {
        return (commandWord != null);
    }

    public boolean hasSecondWord()
    {
        return (commandDetails != null);
    }

    public String getCommandWord () {
        return commandWord;
    }

    public String getCommandDetails () {
        return commandDetails;
    }

    public void printCommand () {
        if (hasSecondWord()) {
            System.out.println(commandWord + " " + commandDetails);
            return;
        }
        System.out.println(commandWord);
    }
}
