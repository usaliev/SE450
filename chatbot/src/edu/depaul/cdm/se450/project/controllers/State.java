package edu.depaul.cdm.se450.project.controllers;

import edu.depaul.cdm.se450.project.views.View;

public interface State
{
    public void readInput();

    public void readConfig();

    public void writeOutput(String outputText);

    public void writeConfig();

    public void exitChatbot();
}
