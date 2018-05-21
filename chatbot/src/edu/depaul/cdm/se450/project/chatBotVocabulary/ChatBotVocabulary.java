package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Summary: Container class for the chatbots vocabulary
 */
public class ChatBotVocabulary
{
    @SerializedName ("properties")
    private List<Property> Properties;

    @SerializedName ("users")
    private List<User> Users;

    @SerializedName ("openingStatements")
    private List<String> OpeningStatements;

    @SerializedName ("queries")
    private List<Query> Queries;

    @SerializedName ("responses")
    private List<Response> Responses;

    /**
     * Summary:          Set the properties
     * @param properties The properties
     */
    public void setProperties(List<Property> properties)
    {
        this.Properties = properties;
    }

    /**
     * Summary: Get the properties
     * @return The properties
     */
    public List<Property> getProperties()
    {
        return this.Properties;
    }

    /**
     * Summary:     Set the users
     * @param users The users
     */
    public void setUsers(List<User> users)
    {
        this.Users = users;
    }

    /**
     * Summary: Get the users
     * @return  The users
     */
    public List<User> getUsers()
    {
        return this.Users;
    }

    /**
     * Summary: Get the opening statements
     * @return  The opening statements
     */
    public List<String> getOpeningStatements()
    {
        return this.OpeningStatements;
    }

    /**
     * Summary:                 Set the opening statements
     * @param openingStatements The opening statements
     */
    public void setOpeningStatements(List<String> openingStatements)
    {
        this.OpeningStatements = openingStatements;
    }

    /**
     * Summary: Get the queries
     * @return The queries
     */
    public List<Query> getQueries()
    {
        return Queries;
    }

    /**
     * Summary: set the queries
     * @param queries the queries
     */
    public void setQueries(List<Query> queries)
    {
        this.Queries = queries;
    }

    /**
     * Summary: Get the responses
     * @return The responses
     */
    public List<Response> getResponses()
    {
        return this.Responses;
    }

    /**
     * Summary:         Set the responses
     * @param responses The responses
     */
    public void setResponses(List<Response> responses)
    {
        this.Responses = responses;
    }
}
