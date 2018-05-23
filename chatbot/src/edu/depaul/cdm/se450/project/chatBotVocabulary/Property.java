package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;

public class Property
{
    @SerializedName (value = "tag", alternate = {"Tag"})
    private String tag = "";

    @SerializedName (value = "subTag", alternate = {"subtag", "SubTag", "Subtag"})
    private String subTag = "";

    @SerializedName (value = "value", alternate = {"Value"})
    private String value = "";

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
     * Get the nickname
     *
     * @return The nickname
     */
    public String getValue()
    {
        return this.value;
    }

}
