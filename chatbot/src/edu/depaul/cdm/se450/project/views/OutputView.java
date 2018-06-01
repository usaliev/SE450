package edu.depaul.cdm.se450.project.views;

import edu.depaul.cdm.se450.project.chatBotVocabulary.ChatbotVocabulary;
import com.google.gson.*;

/**
 * OutputView for the application. Provides very simple functionality
 * of displaying start-up banner and echoing user input.
 */
public class OutputView extends View
{

    public void displayOutput(String output)
    {
        System.out.println(output);
    }

    public void displayConfigFile(ChatbotVocabulary configFile)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        System.out.println(gson.toJson(configFile));
    }

}