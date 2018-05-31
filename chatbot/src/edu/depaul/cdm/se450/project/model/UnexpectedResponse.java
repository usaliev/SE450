package edu.depaul.cdm.se450.project.model;

import edu.depaul.cdm.se450.project.chatBotVocabulary.Response;
import java.util.List;
import java.util.Random;

public class UnexpectedResponse implements ChatbotResponse
{

    private List<Response> unexpectedResponses;
    public UnexpectedResponse( List<Response> responses)
    {
        this.unexpectedResponses = responses;
    }

    @Override
    /**
     * Get a random response from the unexpected responses bucket
     */
    public String getResponse()
    {
       Random rand = new Random();
       int i = rand.nextInt(this.unexpectedResponses.size());
       return unexpectedResponses.get(i).getResponse();
    }
}
