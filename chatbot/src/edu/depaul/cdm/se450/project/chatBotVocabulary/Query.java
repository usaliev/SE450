package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;

public class Query
{
    @SerializedName (value = "question", alternate = {"Question"})
    private String question = "";

    @SerializedName (value = "tag", alternate = {"Tag"})
    private String tag = "";

    @SerializedName (value = "subTag", alternate = {"subtag", "SubTag", "Subtag"})
    private String subTag = "";

    /**
     * Get the question
     *
     * @return The question
     */
    public String getQuestion()
    {
        return this.question;
    }

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
}
