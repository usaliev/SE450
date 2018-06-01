package edu.depaul.cdm.se450.project.views;

import java.io.*;

import edu.depaul.cdm.se450.project.util.EventCode;

/**
 * Input view for the application. Provides very simple functionality
 * for accepting input string from user
 */
public class InputView extends View
{

    private StringBuilder inputString;

    /**
     * Return the last user input to this InputView.
     *
     * @return user input
     */
    public String getUserInput()
    {
        if (this.inputString == null)
        {
            return null;
        }
        return inputString.toString();
    }

    public void readUserInput()
    {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String inString;

        try
        {
            inString = console.readLine();
        }
        catch (IOException e)
        {
            inString = "<" + e + ">";
        }

        inputString = new StringBuilder(inString);
    }

    public void readConfigurationFile()
    {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String inString;

        try
        {
            System.out.println("Do you wish to display the configuration file (y or n) and then exit?");
            inString = console.readLine().toLowerCase();
            if (inString.compareToIgnoreCase("y") == 0 || inString.compareToIgnoreCase("yes") == 0)
            {
                notifyObservers(EventCode.DISPLAY_CONFIG_FILE);
            }
            else
            {
                notifyObservers(EventCode.START_CHATBOT);
            }
        }
        catch (IOException e)
        {
            inputString = new StringBuilder("<" + e + ">");
        }
    }
}