package edu.depaul.cdm.se450.project.views;

import edu.depaul.cdm.se450.project.util.Observer;
import edu.depaul.cdm.se450.project.util.EventCode;
import java.util.List;

/**
 *  OutputView for the application. Provides very simple functionality
 *	of displaying start-up banner and echoing user input.
 *
 */

public class OutputView extends View implements Observer {

    /**
     *  Receive notification of an event in an Observable object.
     */

    public void handleEvent( EventCode eventCode ) {

        /*
         *  Handle the two event types for an OutputView:
         *	<p>
         *	(1) Print start-up banner to console.
         *	<p>
         *	(2) Echo user input to the console.
         */

        //  Read banner from model and display.

        switch (eventCode)
        {
            case INITIALIZE:
            {
                System.out.println( this.model.getBanner() );
                break;
            }
            case DISPLAY_USER_INPUT:
            {
                String userInput = this.model.getUserInput();
                System.out.println(userInput);
                break;
            }
            case DISPLAY_CONFIG_FILE:
            {
                List<String> configFile = this.model.getConfigurationFile();
                for (String line : configFile)
                {
                    System.out.println(line);
                }
                notifyObservers( EventCode.DONE_DISPLAYING);
            }
        }
    }
}