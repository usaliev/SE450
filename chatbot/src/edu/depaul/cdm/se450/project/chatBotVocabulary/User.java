package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;

public class User
{
    @SerializedName (value = "name", alternate = {"Name"})
    private String name = "";

    @SerializedName (value = "terminator", alternate = {"Terminator"})
    private String terminator = "";

    /**
     * Get the name
     *
     * @return The name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the terminator
     *
     * @return The terminator
     */
    public String getTerminator()
    {
        return terminator;
    }

}
