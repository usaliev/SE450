package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;

public class Query
{
    @SerializedName ("question")
    private String question;

    @SerializedName ("tag")
    private String tag;

    @SerializedName ("subtag")
    private String subtag;

    /**
     * Summary:        Set the question
     * @param question The question
     */
    public void setQuestion(String question)
    {
        this.question = question;
    }

    /**
     * Summary: Get the question
     * @return  The question
     */
    public String getQuestion()
    {
        return this.question;
    }

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
        this.subtag = subtag;
    }

    /**
     * Summary: Get the subtag
     * @return  The subtag
     */
    public String getSubtag()
    {
        return this.subtag;
    }
}
