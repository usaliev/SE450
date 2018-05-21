package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;

public class User
{
    @SerializedName ("name")
    private String Name;

    @SerializedName ("terminator")
    private String terminator;

    @SerializedName ("isChatbot")
    private boolean isChatbot;

    /**
     * Summary: Get the name
     * @return  The name
     */
    public String getName()
    {
        return Name;
    }

    /**
     * Sumamry:    Set the name
     * @param name The name
     */
    public void setName(String name)
    {
        Name = name;
    }

    /**
     * Summary: Get the terminator
     * @return  The terminator
     */
    public String getTerminator()
    {
        return terminator;
    }

    /**
     * Summary:          Get the terminator
     * @param terminator The terminator
     */
    public void setTerminator(String terminator)
    {
        this.terminator = terminator;
    }

    /**
     * Summary: Get a value indicating if this is a chatbot user
     * @return  A value indicating if this is a chatbot user
     */
    public boolean isChatbot()
    {
        return isChatbot;
    }

    /**
     * Summary:       Set a value indicating if this is a chatbot user
     * @param chatbot A value indicating if this is a chatbot user
     */
    public void setChatbot(boolean chatbot)
    {
        isChatbot = chatbot;
    }


}
