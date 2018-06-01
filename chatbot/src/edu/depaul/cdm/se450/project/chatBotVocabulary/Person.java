package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;

public class Person
{
    @SerializedName (value = "name", alternate = {"Name"})
    private String name;

    @SerializedName (value = "eval", alternate = {"Eval"})
    private String eval;

    public String getName()
    {
        return this.name;
    }

    public boolean getEval()
    {
        return this.eval.compareToIgnoreCase("like") == 0;
    }
}
