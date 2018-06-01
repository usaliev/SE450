package edu.depaul.cdm.se450.project.controllers;

import edu.depaul.cdm.se450.project.model.Model;
import edu.depaul.cdm.se450.project.views.OutputView;

public class ProcessingState implements State
{
    Controller controller;
    OutputView outputView;
    Model model;

    public ProcessingState(Controller controller, Model model)
    {
        this.controller = controller;
        this.model = model;
    }

    @Override
    public void readInput()
    {
        this.controller.setState(this.controller.getIOState());
        this.controller.getState().readInput();
    }

    public void readConfig()
    {
        this.controller.setState(this.controller.getIOState());
        this.controller.getState().readConfig();
    }

    @Override
    public void writeOutput(String outputText)
    {
        this.controller.setState(this.controller.getIOState());
        this.controller.getState().writeOutput(outputText);
    }

    public void writeConfig()
    {
        this.controller.setState(this.controller.getIOState());
        this.controller.getState().writeConfig();
    }

    @Override
    public void exitChatbot()
    {
        this.model.exitChatBot();
    }
}
