package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;

/**
 * Summary: Container class for responses
 */
public class Response
{
    @SerializedName ("tag")
    private String tag;

    @SerializedName ("subTag")
    private String subtag;

    @SerializedName ("response")
    private String response;

    /**
     * Summary:   Set the tag
     * @param tag The tag
     */
    public void setTag(String tag)
    {
        this.tag = tag;
    }

    /**
     * Summary: Get the tag
     * @return  The tag
     */
    public String getTag()
    {
        return this.tag;
    }

    /**
     * Summary:      Set the subtag
     * @param subtag The subtag
     */
    public void setSubtag(String subtag)
    {
        this.subtag = tag;
    }

    /**
     * Summary: Get the subtag
     * @return  The subtag
     */
    public String getSubtag()
    {
        return this.subtag;
    }

    /**
     * Summary:        Set the response string
     * @param response The response string
     */
    public void setResponse(String response)
    {
        this.response = response;
    }

    /**
     * Summary: Get the response string
     * @return  The response string
     */
    public String getResponse()
    {
        return this.response;
    }
}
