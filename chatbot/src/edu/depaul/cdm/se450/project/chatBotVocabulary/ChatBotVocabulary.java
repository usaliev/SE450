package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Summary: Container class for the chatbots vocabulary
 */
public class ChatbotVocabulary
{
    @SerializedName (value = "properties", alternate = {"Properties"})
    private List<Property> properties;

    @SerializedName (value = "users", alternate = {"Users"})
    private User user;

    @SerializedName (value = "chatBot", alternate = {"chatbot", "Chatbot"})
    private User chatBot;

    @SerializedName (value = "openingStatements", alternate = {"OpeningStatements"})
    private List<String> openingStatements;

    @SerializedName (value = "queries", alternate = {"Queries"})
    private List<Query> queries;

    @SerializedName (value = "responses", alternate = {"Response"})
    private List<Response> responses;

    @SerializedName( value = "unexpectedResponses", alternate = {"UnexpectedResponses", "unexpectedresponses", "Unexpectedresponses"})
    private List<Response> unexpectedResponses;

    @SerializedName(value = "actors", alternate = { "Actors"})
    private List<Person> actors;

    @SerializedName(value = "actresses", alternate = { "Actresses"})
    private List<Person> actresses;

    @SerializedName(value = "directors", alternate = { "Directors"})
    private List<Person> directors;

    @SerializedName(value = "movies", alternate = {"Movies"})
    private List<String> movies;

    /**
     * Get the properties
     *
     * @return The properties
     */
    public List<Property> getProperties()
    {
        return this.properties;
    }

    /**
     * Get the users
     *
     * @return The users
     */
    public User getChatBot()
    {
        return this.chatBot;
    }

    /**
     * Get the users
     *
     * @return The users
     */
    public User getUser()
    {
        return this.user;
    }

    /**
     * Get the opening statements
     *
     * @return The opening statements
     */
    public List<String> getOpeningStatements()
    {
        return this.openingStatements;
    }

    /**
     * Get the queries
     *
     * @return The queries
     */
    public List<Query> getQueries()
    {
        return queries;
    }

    /**
     * Get the responses
     *
     * @return The responses
     */
    public List<Response> getResponses()
    {
        return this.responses;
    }

    /**
     * Get the list of actors
     * @return the actors
     */
    public List<Person> getActors()
    {
        return actors;
    }

    /**
     * Get the list of actresses
     * @return the actresses
     */
    public List<Person> getActresses()
    {
        return actresses;
    }

    /**
     * Get the list of directors
     * @return the directors
     */
    public List<Person> getDirectors()
    {
        return directors;
    }

    /**
     * Get the list of movies
     * @return the movies
     */
    public List<String> getMovies()
    {
        return movies;
    }

    /**
     * Get the list of unexpected responses
     * @return a list of unexpected responses
     */
    public List<Response> getUnexpectedResponses()
    {
        return unexpectedResponses;
    }

    /**
     * Determine if the given response is a known response
     * @param userResponse a response from the user to check
     * @return a value indicating if the response is known
     */
    public boolean inResponses(String userResponse)
    {
        for(Response knownResponse : this.responses)
        {
            if( knownResponse.equals(userResponse))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Determin if the given query is a known query
     * @param userQuery a query from the user to check
     * @return a value indicating if the query is known
     */
    public boolean inQueries(String userQuery)
    {
        for(Query knownQuery : this.queries)
        {
            if( knownQuery.equals(userQuery))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a query by the question
     * @param userQuery the question the user is asking
     * @return a query assocaited with the question asked.
     */
    public Query getQueryByQuestion(String userQuery)
    {
        for(Query knownQuery : this.queries)
        {
            if( knownQuery.equals(userQuery))
            {
                return knownQuery;
            }
        }
        return null;
    }
}
