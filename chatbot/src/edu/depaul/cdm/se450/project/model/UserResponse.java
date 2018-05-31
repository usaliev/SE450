package edu.depaul.cdm.se450.project.model;

public class UserResponse
{
    private ChatbotResponse chatbotResponse;

    public String getResponse()
    {
        return chatbotResponse.getResponse();
    }

    public void setResponseType(ChatbotResponse chatbotResponse)
    {
        this.chatbotResponse = chatbotResponse;
    }
}
