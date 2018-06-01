package edu.depaul.cdm.se450.project.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import com.google.gson.*;

import edu.depaul.cdm.se450.project.chatBotVocabulary.*;
import edu.depaul.cdm.se450.project.util.Observer;
import edu.depaul.cdm.se450.project.util.Observable;
import edu.depaul.cdm.se450.project.util.EventCode;

public class Model implements Observable
{

    private StringBuilder banner;
    private StringBuilder input;
    private UserResponse userResponse = new UserResponse();
    private ChatbotVocabulary chatbotVocabulary;
    private String doNotUnderstandString = "";
    private ArrayList<Observer> observers = new ArrayList<>();

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
     * Set the banner for this application.
     *
     * @param b banner for application
     */
    public void setBanner(String b)
    {
        banner = new StringBuilder(b);
    }

    /**
     * Get the user input for this application.
     *
     * @return u ser input for application
     */
    public String getUserInput()
    {
        if (this.input == null)
        {
            return null;
        }
        return this.input.toString();
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
     * Get the chatbotVocabulary object
     *
     * @return The chatbotVocabulary object
     */
    public ChatbotVocabulary getConfigurationFile()
    {
        return this.chatbotVocabulary;
    }

    /**
     * Set the chatbotVocabulary from the filepath
     *
     * @param filePath a JSON file that matches the format expected for a ChatbotVocabulary object
     */
    public void setConfigurationFile(String filePath)
    {
        //TODO implement error handler
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            Gson gson = new Gson();
            this.chatbotVocabulary = gson.fromJson(br, ChatbotVocabulary.class);
        }
        catch (IOException ex)
        {
            System.out.println("Error " + ex);
            notifyObservers(EventCode.EXIT_CHATBOT);
        }
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
        this.notifyObservers(EventCode.INITIALIZE);
        this.notifyObservers(EventCode.READ_CONFIG_FILE);
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
        for (String statement : this.chatbotVocabulary.getOpeningStatements())
        {
            if (statement.indexOf("${") >= 0 && statement.indexOf("}") >= 0)
            {
                this.input = new StringBuilder(trySetPropertyValues(statement));
            }
            else
            {
                this.input = new StringBuilder(statement);
            }

            this.notifyObservers(EventCode.DISPLAY_OUTPUT);
        }
    }

    /**
     * Start chatting but allowing the user to ask a question and responding if the
     * query is understood and a response is available
     */
    public void startChatting()
    {
        this.sendOpeningStatements();

        for (Query query : this.chatbotVocabulary.getQueries())
        {
            this.queryUser(query);
            String response = this.getChatbotResponse(this.input.toString(), query);
            this.input = new StringBuilder(response);
            notifyObservers(EventCode.DISPLAY_OUTPUT);
        }

        // all of the queries have been run through at this point so let the user ask questions till they say
        // their terminator, this is also a horrible way to do this but it's quick and dirty
        while (true)
        {
            this.notifyObservers(EventCode.READ_USER_INPUT);
            if (this.input.toString().compareToIgnoreCase(this.chatbotVocabulary.getUser().getTerminator()) == 0)
            {
                this.input = new StringBuilder(this.chatbotVocabulary.getUser().getTerminator());
                this.notifyObservers(EventCode.DISPLAY_OUTPUT);
                this.exitChatBot();
            }

            if (this.chatbotVocabulary.inQueries(this.input.toString()))
            {

                this.respondToUser(this.chatbotVocabulary.getQueryByQuestion(this.input.toString()));
            }
            else
            {
                this.userResponse.setResponseType(new UnexpectedResponse(this.chatbotVocabulary.getUnexpectedResponses()));
                this.input = new StringBuilder(this.userResponse.getResponse());
                this.notifyObservers(EventCode.DISPLAY_OUTPUT);
            }
        }
    }

    /**
     * Ask the user a question
     * @param query the query to ask
     */
    private void queryUser(Query query)
    {
        this.input = new StringBuilder(query.getQuestion());
        this.notifyObservers(EventCode.DISPLAY_OUTPUT);
        this.notifyObservers(EventCode.READ_USER_INPUT);

        // if at any point the user says their termination line exit
        if (this.input.equals(this.chatbotVocabulary.getUser().getTerminator()))
        {
            this.input = new StringBuilder(this.chatbotVocabulary.getChatBot().getTerminator());
            this.notifyObservers(EventCode.DISPLAY_OUTPUT);
            this.exitChatBot();
        }
    }

    /**
     * Respond to the users responses to questions asked by the chatbot
     * @param userResponse the users response
     * @param questionAsked the question that was asked
     * @return a reply statement from the chatbot
     */
    private String getChatbotResponse(String userResponse, Query questionAsked)
    {
        String tag = questionAsked.getTag();
        String subTag = questionAsked.getSubTag();

        if (tag.compareToIgnoreCase("movies") == 0)
        {
            if (subTag.compareToIgnoreCase("title") == 0)
            {
                return this.checkMovie();
            }
            if (subTag.compareToIgnoreCase("actor") == 0)
            {
                return this.checkActor();
            }
            if (subTag.compareToIgnoreCase("actress") == 0)
            {
                return this.checkActress();
            }
            if (subTag.compareToIgnoreCase("director") == 0)
            {
                return this.checkDirector();
            }
        }

        if (this.chatbotVocabulary.inResponses(userResponse))
        {
            this.userResponse.setResponseType(new ExpectedResponse(userResponse));
            return this.userResponse.getResponse();
        }
        else
        {
            this.userResponse.setResponseType(new UnexpectedResponse(this.chatbotVocabulary.getUnexpectedResponses()));
            return this.userResponse.getResponse();
        }
    }

    /**
     * The user has asked a question so check if we have a response that matches the tags associated with it
     */
    private void respondToUser(Query query)
    {
        String tag = query.getTag();
        String subTag = query.getSubTag();

        for (Response response : this.chatbotVocabulary.getResponses())
        {
            if (response.getTag().compareToIgnoreCase(tag) == 0)
            {
                this.input = new StringBuilder(response.getResponse());
                this.notifyObservers(EventCode.DISPLAY_OUTPUT);

                //once you find the first match just return
                return;
            }
        }

        this.userResponse.setResponseType(new UnexpectedResponse(this.chatbotVocabulary.getUnexpectedResponses()));
        this.input = new StringBuilder(this.userResponse.getResponse());
        this.notifyObservers(EventCode.DISPLAY_OUTPUT);
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

            for (Property property : this.chatbotVocabulary.getProperties())
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
     * Check if the movie the user specified is known
     * @return A statement about whether the movie is known
     */
    private String checkMovie()
    {
        if (this.chatbotVocabulary.getMovies().contains(this.input.toString()))
        {
            return "I’ve seen that movie";
        }
        return "I haven't seen that movie";
    }

    /**
     * Check if the actor the user specified is known and or liked
     * @return A statement about whether the actor is known or liked
     */
    private String checkActor()
    {
        for (Person actor : this.chatbotVocabulary.getActors())
        {
            if (actor.getName().compareToIgnoreCase(this.input.toString()) == 0)
            {
                if (actor.getEval())
                {
                    return "I like that actor";
                }
                return "I don't like that actor";
            }
        }
        return "I don't know that actor";
    }

    /**
     * Check if the actress the user specified is known and or liked
     * @return A statement about whether the actress is known or liked
     */
    private String checkActress()
    {
        for (Person actress : this.chatbotVocabulary.getActresses())
        {
            if (actress.getName().compareToIgnoreCase(this.input.toString()) == 0)
            {
                if (actress.getEval())
                {
                    return "I like that actress";
                }
                return "I don't like that actress";
            }
        }
        return "I don't know that actress";
    }

    /**
     * Check if the chatbot knows the director the user specified, if it doesnt ask for a movie they directed
     * @return a chatbot response either stating the director is liked/not liked or a a statement about a movie the
     * director directed
     */
    private String checkDirector()
    {
        for (Person director : this.chatbotVocabulary.getDirectors())
        {
            if (director.getName().compareToIgnoreCase(this.input.toString()) == 0)
            {
                if (director.getEval())
                {
                    return "I like that director";
                }
                return "I don't like that director";
            }
        }
        this.input = new StringBuilder("I don't know that director");
        notifyObservers(EventCode.DISPLAY_OUTPUT);

        Query nameAMovie = new Query("Tell me one film the director made", "movies", "title");
        this.queryUser(nameAMovie);
        return this.getChatbotResponse(this.input.toString(), nameAMovie);
    }

    /**
     * Exit
     */
    public void exitChatBot()
    {
        System.exit(0);
    }
}