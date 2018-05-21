package edu.depaul.cdm.se450.project.model;

/*
 *  Whenever possible, I prefer to import specific classes rather than
 *  simply use a wildcard. This helps make explicit what you are using from
 *  the package and also allows the compiler to generate somewhat more
 *  efficient code.
 */

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.HashMap;

import edu.depaul.cdm.se450.project.util.Observer;
import edu.depaul.cdm.se450.project.util.Observable;
import edu.depaul.cdm.se450.project.util.EventCode;

/**
 *  Model for the application. Provides very simple functionality:
 *  <p>
 *  (1) Notify Observers when started
 *  <p>
 *  (2) Accept input string from View via Controller
 *  <p>
 *  (3) Notify Observers when input string has been received
 *
 */
public class Model implements Observable {

    private StringBuilder banner;            // Start-up banner
    private StringBuilder input;             // Input from user
    private boolean terminateProgram = false; // Boolean determining if we need to terminate
    private boolean readingInput = true;
    private List<String> configurationFile;

    // TODO: These can be encapsulated into one or two new classes to simplify the model.
    private HashMap<String, String> propertyMappings = new HashMap<>();
    private User chatBot = new User();
    private User user = new User();
    private ArrayList<Statement> openingStatements = new ArrayList<>();
    private ArrayList<Query> queries = new ArrayList<>();
    private  ArrayList<ChatBotResponse> chatBotResponses = new ArrayList<>();

    /*
     *  List of observers of this Model.
     *
     *  Note use of generics to indicate that this is an ArrayList
     *  of Observers.
     */
    private ArrayList<Observer> observers = new ArrayList<Observer>();


    /**
     *  Set the banner for this application.
     *
     *  @param b banner for application
     */
    public void setBanner( String b ) {
        banner = new StringBuilder( b );
    }

    /**
     *  Return the banner for this application.
     *
     *  @return banner for application
     */
    public String getBanner() {
        return banner.toString();
    }

    /**
     *  Execute this object. This method begins the MVC
     *  cycle by notifying the attached view that an event has occurred,
     *  then notifying it when it is ready to accept user input.
     *  <p>
     *  NOTE: In a more complex program, a more sophisticated event
     *        specification mechanism would be needed.
     */
    public void run()
    {
        // we need to terminate for some reason so don't initialize
        if (terminateProgram)
        {
            return;
        }

        this.notifyObservers(EventCode.INITIALIZE );	  // Initialization event
        this.notifyObservers(EventCode.READ_CONFIG_FILE); // Ask if the user wants to see the config file
        this.waitForInput();
        this.sendOpeningStatements();
        this.startChatting();
    }

    public void setReadingInput()
    {
        this.readingInput = !this.readingInput;
    }

    private void waitForInput()
    {
        while(this.readingInput)
        {
        }
    }

    /**
     *  Set the user input to this application.
     *
     *  @param i user input to application
     */
    public void setUserInput( String i )
    {
        this.input = new StringBuilder( i );
    }

    /**
     *  Get the user input for this application.
     *
     *  @return user input for application
     */
    public String getUserInput() {
        return input.toString();
    }

    /**
     *  Add an observer to the list of observers for this object. An observer
     *  is entered into the list only once.
     *
     *  @param o observer to be added to list of observers for this object
     */
    public void addObserver( Observer o ) {
        if ( !observers.contains( o ) )
            observers.add( o );
    }

    /**
     *  Remove an observer from the list of observers for this object.
     *
     *  @param o observer to be removed from list of observers for this object
     */
    public void removeObserver( Observer o ) {
        if ( !observers.remove( o ) )
            System.out.println( "Error in Model.removeObserver: " +
                                "Specified Observer not in list." );
    }

    /**
     *  Notify observers of this object that an event in this object has
     *  occurred.
     *
     *	@param eventCode value indicating which event occurred
     */

    public void notifyObservers( EventCode eventCode ) {
        ListIterator<Observer> listIterator = observers.listIterator();
        while ( listIterator.hasNext() ) {
            ( listIterator.next() ).handleEvent( eventCode );
        }
    }

    private void sendOpeningStatements()
    {
        for (Statement statement : openingStatements)
        {
            this.input = new StringBuilder( statement.getStatement() );
            this.notifyObservers( EventCode.DISPLAY_USER_INPUT);	//  User input updated
        }
    }

    private void startChatting()
    {
        for (Query query : this.queries)
        {
            this.input = new StringBuilder(query.getStatement());
            this.notifyObservers(EventCode.DISPLAY_USER_INPUT);
            this.notifyObservers(EventCode.READ_USER_INPUT);

            // if at any point the user says their termination line exit
            if (this.input.equals(this.user.getTerminator()))
            {
                this.input = new StringBuilder(chatBot.getTerminator());
                this.notifyObservers(EventCode.DISPLAY_USER_INPUT);
                return;
            }
        }

        // all of the queries have been run through at this point so let the user ask questions till they say
        // their terminator, this is also a horrible way to do this but it's quick and dirty
        while (true)
        {
            this.notifyObservers(EventCode.READ_USER_INPUT);
            if (this.input.toString().compareToIgnoreCase(this.user.getTerminator()) == 0)
            {
                this.input = new StringBuilder(this.chatBot.getTerminator());
                this.notifyObservers(EventCode.DISPLAY_USER_INPUT);
                return;
            }
            this.respondToUser();
        }
    }

    private void respondToUser()
    {
        for (ChatBotResponse response : this.chatBotResponses)
        {
            // using toLowerCase().contains() here because i still want to evaluate if punctuation is missing.
            if ( response.getQuery() != null && response.getQuery().toLowerCase().contains(this.input.toString().toLowerCase()))
            {
                String responseStatement = response.getResponses().get(0).getResponse().getStatement();
                //TODO: definitely need to do something about this to simplify it.
                this.input = new StringBuilder(responseStatement);
                this.notifyObservers(EventCode.DISPLAY_USER_INPUT);

                //once you find the first match just return
                return;
            }
        }
    }

    public void setConfigurationFile( String filePath )
    {
        try
        {
            this.configurationFile = Files.readAllLines(Paths.get(filePath));
            this.generateMapping(this.configurationFile);
        }
        catch (FileNotFoundException ex)
        {
            //TODO implement error handler
            System.out.println("Configuration File not found, please provide a valid configuration file path");
            this.terminateProgram = true;
        }
        catch (Exception ex)
        {
            System.out.println("Error " + ex);
            this.terminateProgram = true;
        }
    }

    public List<String> getConfigurationFile()
    {
        return this.configurationFile;
    }

    private void generateMapping( List<String> conversationConfig)
    {
        int openingIndex = 0;
        int openingClosingIndex = 0;

        // There is probably a more efficient way to deserialize this but on the fly this works
        for (String line : conversationConfig)
        {
            // Get the properties from the config
            if (line.toLowerCase().contains("property name=") && line.toLowerCase().contains("value="))
            {
                String key = line.substring(line.indexOf("name=\"") + 6, line.indexOf("value") - 2);
                String value = line.substring(line.indexOf("value=\"") + 7, line.indexOf(" />") - 1);
                this.propertyMappings.put(key, value);
            }

            // based on the requirements it is assumed that a query will always be a single line element
            if (line.toLowerCase().contains("query"))
            {
                String queryTag = line.substring(line.indexOf("name=\"") + 6, line.indexOf(">") - 1);
                String response = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
                this.queries.add( new Query(queryTag, response));
            }

            // this assumes order of the config, although could be fixed later, for now assume query was resolved first
            if (line.toLowerCase().contains("response"))
            {
                String queryTag = line.substring(line.indexOf("name=\"") + 6, line.indexOf(">") - 1);
                String responseStatement = line.substring(line.indexOf(">") + 1, line.indexOf("</"));

                // TODO: clean this up in the chatbot response/response classes
                ArrayList<Response> response = new ArrayList<>();
                response.add( new Response (queryTag, responseStatement));

                String queryStatement = null;
                for( Query query : this.queries)
                {
                    if (query.getTag().compareToIgnoreCase(response.get(0).getTag()) == 0)
                    {
                        queryStatement = query.getStatement();
                    }

                }

                this.chatBotResponses.add( new ChatBotResponse(queryStatement, response));

            }

            // because opening tag spans multiple lines and the name can change it's faster to collect its start and end
            // index here
            if (line.toLowerCase().contains("<opening"))
            {
                openingIndex = conversationConfig.indexOf(line);
            }

            if (line.toLowerCase().contains("</opening>"))
            {
                openingClosingIndex = conversationConfig.indexOf(line);
            }
        }

        // Create the user object
        for( int i = conversationConfig.indexOf("<user>"); i <= conversationConfig.indexOf("</user>"); ++i)
        {
            String line = this.trySetPropertyValues(conversationConfig.get(i));
            if(line.toLowerCase().contains("terminator"))
            {
                this.user.setTerminator(line.substring(line.indexOf(">") + 1, line.indexOf("</")));
            }
            if(line.toLowerCase().contains("name"))
            {
                this.user.setFirstName(line.substring(line.indexOf(">") + 1, line.indexOf("</")));
            }
        }

        // Create the chatBot object
        for( int i = conversationConfig.indexOf("<chatbot>"); i <= conversationConfig.indexOf("</chatbot>"); ++i)
        {
            String line = this.trySetPropertyValues(conversationConfig.get(i));
            if (line.toLowerCase().contains("terminator"))
            {
                this.chatBot.setTerminator(line.substring(line.indexOf(">") + 1, line.indexOf("</")));
            }
            if (line.toLowerCase().contains("name"))
            {
                this.chatBot.setFirstName(line.substring(line.indexOf(">") + 1, line.indexOf("</")));
            }
        }

        // Create the opening statement object
        for( int i = openingIndex; i <= openingClosingIndex; ++i)
        {
            String line = this.trySetPropertyValues(conversationConfig.get(i));
            if (line.toLowerCase().contains("statement"))
            {
                this.openingStatements.add(new Statement(line.substring(line.indexOf(">") + 1, line.indexOf("</"))));
            }
        }
    }

    private String trySetPropertyValues(String line)
    {
        int startIndex = line.indexOf("${") + 2;
        int endIndex = line.indexOf("}");

        if (startIndex > 0 && endIndex > 0)
        {
            String oldValue = line.substring(startIndex, endIndex);
            if (this.propertyMappings.containsKey(oldValue))
            {
                return line.replace( "${" + oldValue + "}", this.propertyMappings.get(oldValue));
            }
        }
        return line;
    }
}