public class Command {
    String commandWord;
    String secondWord;

    public Command(String firstWord)
    {
        commandWord = firstWord;
        secondWord = null;
    }

    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    public boolean isUnknown()
    {
        return (commandWord != null);
    }

    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }

    public String getCommandWord () {
        return commandWord;
    }

    public String getSecondWord () {
        return secondWord;
    }

    public void printCommand () {
        if (hasSecondWord()) {
            System.out.println(commandWord + " " + secondWord);
            return;
        }
        System.out.println(commandWord);
    }
}
