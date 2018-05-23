package edu.depaul.cdm.se450.project.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.stream.*;

import com.google.gson.*;

import edu.depaul.cdm.se450.project.chatBotVocabulary.*;
import edu.depaul.cdm.se450.project.util.Observer;
import edu.depaul.cdm.se450.project.util.Observable;
import edu.depaul.cdm.se450.project.util.EventCode;

/**
 * Model for the application. Provides very simple functionality:
 * <p>
 * (1) Notify Observers when started
 * <p>
 * (2) Accept input string from View via Controller
 * <p>
 * (3) Notify Observers when input string has been received
 */
public class Model implements Observable
{

    private StringBuilder banner;            // Start-up banner
    private StringBuilder input;             // Input from user
    private boolean terminateProgram = false; // Boolean determining if we need to terminate
    private ChatBotVocabulary chatBotVocabulary;
    private String doNotUnderstandString = "";
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    /**
     * Set the banner for this application.
     *
     * @param b banner for application
     */
    public void setBanner(String b)
    {
        banner = new StringBuilder(b);
    }

    /**
     * Return the banner for this application.
     *
     * @return banner for application
     */
    public String getBanner()
    {
        return banner.toString();
    }

    /**
     * Execute this object. This method begins the MVC
     * cycle by notifying the attached view that an event has occurred,
     * then notifying it when it is ready to accept user input.
     * <p>
     * NOTE: In a more complex program, a more sophisticated event
     * specification mechanism would be needed.
     */
    public void run()
    {
        // we need to terminate for some reason so don't initialize
        if (terminateProgram)
        {
            return;
        }

        this.notifyObservers(EventCode.INITIALIZE);
        this.notifyObservers(EventCode.READ_CONFIG_FILE);
    }

    /**
     * Set the user input to this application.
     *
     * @param i user input to application
     */
    public void setUserInput(String i)
    {
        this.input = new StringBuilder(i);
    }

    /**
     * Get the user input for this application.
     *
     * @return u ser input for application
     */
    public String getUserInput()
    {
        return input.toString();
    }

    /**
     * Add an observer to the list of observers for this object.
     * An observer is entered into the list only once.
     *
     * @param o observer to be added to list of observers for this object
     */
    public void addObserver(Observer o)
    {
        if (!observers.contains(o))
        {
            observers.add(o);
        }
    }

    /**
     * Remove an observer from the list of observers for this object.
     *
     * @param o observer to be removed from list of observers for this object
     */
    public void removeObserver(Observer o)
    {
        if (!observers.remove(o))
        {
            System.out.println("Error in Model.removeObserver: Specified Observer not in list.");
        }
    }

    /**
     * Notify observers of this object that an event in this object has occurred.
     *
     * @param eventCode value indicating which event occurred
     */
    public void notifyObservers(EventCode eventCode)
    {
        ListIterator<Observer> listIterator = observers.listIterator();
        while (listIterator.hasNext())
        {
            (listIterator.next()).handleEvent(eventCode);
        }
    }

    /**
     * Send the opening statements as provided in the chatbot vocabulary file
     */
    private void sendOpeningStatements()
    {
        for (String statement : this.chatBotVocabulary.getOpeningStatements())
        {
            if (statement.indexOf("${") >= 0 && statement.indexOf("}") >= 0)
            {
                this.input = new StringBuilder(trySetPropertyValues(statement));
            }
            else
            {
                this.input = new StringBuilder(statement);
            }

            this.notifyObservers(EventCode.DISPLAY_USER_INPUT);
        }
    }

    /**
     * Start chatting but allowing the user to ask a question and responding if the
     * query is understood and a response is available
     */
    public void startChatting()
    {
        this.sendOpeningStatements();

        for (Query query : this.chatBotVocabulary.getQueries())
        {
            this.input = new StringBuilder(query.getQuestion());
            this.notifyObservers(EventCode.DISPLAY_USER_INPUT);
            this.notifyObservers(EventCode.READ_USER_INPUT);

            // if at any point the user says their termination line exit
            if (this.input.equals(this.chatBotVocabulary.getUser().getTerminator()))
            {
                this.input = new StringBuilder(this.chatBotVocabulary.getChatBot().getTerminator());
                this.notifyObservers(EventCode.DISPLAY_USER_INPUT);
                this.exitChatBot();
            }
        }

        // all of the queries have been run through at this point so let the user ask questions till they say
        // their terminator, this is also a horrible way to do this but it's quick and dirty
        while (true)
        {
            this.notifyObservers(EventCode.READ_USER_INPUT);
            if (this.input.toString().compareToIgnoreCase(this.chatBotVocabulary.getUser().getTerminator()) == 0)
            {
                this.input = new StringBuilder(this.chatBotVocabulary.getUser().getTerminator());
                this.notifyObservers(EventCode.DISPLAY_USER_INPUT);
                this.exitChatBot();
            }
            this.respondToUser();
        }
    }

    /**
     * The user has asked a question so check if we have a response that matches the tags associated with it
     */
    private void respondToUser()
    {
        String tag = "";
        String subTag = "";

        for (Query query : this.chatBotVocabulary.getQueries())
        {
            if (query.getQuestion().compareToIgnoreCase(this.input.toString().toLowerCase()) == 0)
            {
                tag = query.getTag();
                subTag = query.getSubTag();
            }
        }

        for (Response response : this.chatBotVocabulary.getResponses())
        {
            if (response.getTag().compareToIgnoreCase(tag) == 0)
            {
                this.input = new StringBuilder(response.getResponse());
                this.notifyObservers(EventCode.DISPLAY_USER_INPUT);

                //once you find the first match just return
                return;
            }

            // need a better way to handle this
            if (doNotUnderstandString == "" && response.getTag().compareToIgnoreCase("General") == 0
                    && response.getSubTag().compareToIgnoreCase("do not understand") == 0)
            {
                doNotUnderstandString = response.getResponse();
            }
        }

        this.input = new StringBuilder(doNotUnderstandString);
        this.notifyObservers(EventCode.DISPLAY_USER_INPUT);
    }

    /**
     * Set the chatBotVocabulary from the filepath
     *
     * @param filePath a JSON file that matches the format expected for a ChatBotVocabulary object
     */
    public void setConfigurationFile(String filePath)
    {
        //TODO implement error handler
        try
        {

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            Gson gson = new Gson();
            this.chatBotVocabulary = gson.fromJson(br, ChatBotVocabulary.class);

        }
        catch (IOException ex)
        {
            System.out.println("Error " + ex);
            this.terminateProgram = true;
        }
    }

    /**
     * Get the chatBotVocabulary object
     *
     * @return The chatBotVocabulary object
     */
    public ChatBotVocabulary getConfigurationFile()
    {
        return this.chatBotVocabulary;
    }

    /**
     * Given the line determine if any of the properties supplied in the chatbot vocabulary file are a match
     * for the tags in the line and if they do match replace the tags with the value from the file
     *
     * @param line The line to be checked for properties
     * @return A line that either has the properties exchanged for a value in it or if no properties match the
     * original line
     */
    private String trySetPropertyValues(String line)
    {
        int startIndex = line.indexOf("${") + 2;
        int endIndex = line.indexOf("}");

        if (startIndex > 0 && endIndex > 0)
        {
            String oldValue = line.substring(startIndex, endIndex);
            String[] tags = oldValue.split("\\.");
            String tag = tags[0].toLowerCase();
            String subTag = tags[1].toLowerCase();

            for (Property property : this.chatBotVocabulary.getProperties())
            {
                //it's better to keep the formatting when outputting the string but we need to ignore case problems
                if (property.getTag().compareToIgnoreCase(tag) == 0 && property.getSubTag().compareToIgnoreCase(subTag) == 0)
                {
                    return line.replace("${" + oldValue + "}", property.getValue());
                }
            }
        }
        return line;
    }

    /**
     * Exit
     */
    public void exitChatBot()
    {
        return;
    }
}