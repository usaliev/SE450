package edu.depaul.cdm.se450.project.controllers;

import edu.depaul.cdm.se450.project.model.Model;
import edu.depaul.cdm.se450.project.views.InputView;
import edu.depaul.cdm.se450.project.views.OutputView;

public class IOState implements State
{
    Controller controller;
    OutputView outputView;
    InputView inputView;
    Model model;

    public IOState(Controller controller, InputView inputView, OutputView outputView, Model model)
    {
        this.controller = controller;
        this.outputView = outputView;
        this.inputView = inputView;
        this.model = model;
    }

    @Override
    public void readInput()
    {
        this.inputView.readUserInput();
        this.model.setUserInput(this.inputView.getUserInput());
        this.controller.setState(this.controller.getProcessingState());
    }

    @Override
    public void readConfig()
    {
        this.inputView.readConfigurationFile();
        if (inputView.getUserInput() != null)
        {
            this.model.setUserInput(this.inputView.getUserInput());
        }
        this.controller.setState(this.controller.getProcessingState());
    }

    @Override
    public void writeOutput(String text)
    {
        this.outputView.displayOutput(text);
        this.controller.setState(this.controller.getProcessingState());
    }

    public void writeConfig()
    {
        this.outputView.displayConfigFile(this.model.getConfigurationFile());
    }

    @Override
    public void exitChatbot()
    {
        this.model.exitChatBot();
    }
}
