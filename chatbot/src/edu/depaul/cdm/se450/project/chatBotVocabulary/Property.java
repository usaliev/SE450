package edu.depaul.cdm.se450.project.chatBotVocabulary;

import com.google.gson.annotations.SerializedName;

public class Property
{
    @SerializedName ("name")
    private String name;

    @SerializedName ("nickName")
    private String nickName;

    /**
     * Summary: Get the name
     * @return  The name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Summary:    Set the name
     * @param name The name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Summary: Get the nickname
     * @return  The nickname
     */
    public String getNickName()
    {
        return nickName;
    }

    /**
     * Summary:        Set the nickname
     * @param nickName The nickname
     */
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }


}
