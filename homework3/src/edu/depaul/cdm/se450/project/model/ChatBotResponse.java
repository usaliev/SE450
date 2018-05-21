package edu.depaul.cdm.se450.project.model;

import java.util.ArrayList;

public class ChatBotResponse
{
    private ArrayList<Response> responses = new ArrayList<>();
    private String query;

    public ChatBotResponse(String query)
    {
        this.query = query;
    }

    public ChatBotResponse(String query, ArrayList<Response> responses)
    {
        this.query = query;
        this.responses = responses;
    }

    public String getQuery()
    {
        return this.query;
    }

    public ArrayList<Response> getResponses()
    {
        return this.responses;
    }

    public void addResponse(Response response)
    {
        // currently I allow responses with duplicate tags and subtags
        this.responses.add(response);
    }
}
