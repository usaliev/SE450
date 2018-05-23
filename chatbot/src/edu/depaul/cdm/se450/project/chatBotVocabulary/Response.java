package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;

/**
 * Container class for responses
 */
public class Response
{
    @SerializedName (value = "tag", alternate = {"Tag"})
    private String tag = "";

    @SerializedName (value = "subTag", alternate = {"subtag", "SubTag", "Subtag"})
    private String subTag = "";

    @SerializedName (value = "response", alternate = {"Response"})
    private String response = "";

    /**
     * Get the tag
     *
     * @return The tag
     */
    public String getTag()
    {
        return this.tag.toLowerCase();
    }

    /**
     * Get the subTag
     *
     * @return The subTag
     */
    public String getSubTag()
    {
        return this.subTag.toLowerCase();
    }

    /**
     * Get the response string
     *
     * @return The response string
     */
    public String getResponse()
    {
        return this.response;
    }
}
