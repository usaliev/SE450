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
     * Nullary Constructor
     */
    public Query()
    {
    }

    /**
     * Constructor to build a new query
     * @param question the question to ask
     * @param tag a tag associated with the query
     * @param subTag a subtag associated with the query
     */
    public Query(String question, String tag, String subTag)
    {
        this.question = question;
        this.tag = tag;
        this.subTag = subTag;
    }

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

    public boolean equals(Object obj)
    {
        String query = (String)obj;
        return this.question.compareToIgnoreCase(query) == 0;
    }
}
