package edu.depaul.cdm.se450.project.views;

import java.io.*;

import edu.depaul.cdm.se450.project.util.Observer;
import edu.depaul.cdm.se450.project.util.EventCode;

/**
 *  Input view for the application. Provides very simple functionality
 *	for accepting input string from user
 *
 */
public class InputView extends View implements Observer
{

    private StringBuilder inputString;      //  User input string

    /**
     * Return the last user input to this InputView.
     *
     * @return user input
     */
    public String getUserInput()
    {
        return inputString.toString();
    }

    /**
     * Receive notification of an event in an Observable object.
     */

    public void handleEvent(EventCode eventCode)
    {

        /*
         *  Handle the single event type for an InputView:
         *	<p>
         *	(1) Print prompt to console.
         *	<p>
         *	(2) Read a line from the console.
         *	<p>
         *	(3) Notify Obervers (in this case, the Controller)
         *		that the InputView has input.
         */

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String inString;

        //  Read input from user and notify observers.
        switch (eventCode)
        {
            case READ_USER_INPUT:
            {
                notifyObservers(EventCode.READ_USER_INPUT);

                try
                {
                    inString = console.readLine();
                }
                catch (IOException e)
                {
                    inString = "<" + e + ">";
                }

                inputString = new StringBuilder(inString);
                notifyObservers(EventCode.SET_MODEL_VALUE);
                break;
            }
            case READ_CONFIG_FILE:
            {
                notifyObservers(EventCode.READ_USER_INPUT);
                System.out.println("Do you wish to display the configuration file (y or n)?");
                try
                {
                    inString = console.readLine().toLowerCase();
                    if (inString.compareTo("y") == 0 || inString.compareTo("yes") == 0)
                    {
                        notifyObservers(EventCode.DISPLAY_CONFIG_FILE);
                    }
                }
                catch (IOException e)
                {
                    inputString = new StringBuilder("<" + e + ">");
                    notifyObservers(EventCode.SET_MODEL_VALUE);
                }
                break;
            }
        }
    }
}