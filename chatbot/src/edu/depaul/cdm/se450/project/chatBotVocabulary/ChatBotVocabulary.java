package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Summary: Container class for the chatbots vocabulary
 */
public class ChatBotVocabulary
{
    @SerializedName (value = "properties", alternate = {"Properties"})
    private List<Property> properties;

    @SerializedName (value = "users", alternate = {"Users"})
    private User user;

    @SerializedName (value = "chatBot", alternate = {"chatbot", "ChatBot"})
    private User chatBot;

    @SerializedName (value = "openingStatements", alternate = {"OpeningStatements"})
    private List<String> openingStatements;

    @SerializedName (value = "queries", alternate = {"Queries"})
    private List<Query> queries;

    @SerializedName (value = "responses", alternate = {"Responses"})
    private List<Response> responses;

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

}
