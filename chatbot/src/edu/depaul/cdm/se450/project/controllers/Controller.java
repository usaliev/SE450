package edu.depaul.cdm.se450.project.controllers;

import edu.depaul.cdm.se450.project.model.Model;
import edu.depaul.cdm.se450.project.views.InputView;
import edu.depaul.cdm.se450.project.views.OutputView;
import edu.depaul.cdm.se450.project.util.Observer;
import edu.depaul.cdm.se450.project.util.EventCode;

/**
 * Controller for the application.
 */
public class Controller implements Observer
{

    private InputView inputView;
    private OutputView outputView;
    private Model model;

    /**
     * Set the Model to be controlled by this Controller.
     *
     * @param m model to be controlled
     */
    public void setModel(Model m)
    {
        this.model = m;
        m.addObserver(this);
    }

    /**
     * Set the Input View to be observed by this Controller.
     *
     * @param inputView input view to be observed
     */
    public void setInputView(InputView inputView)
    {
        this.inputView = inputView;
        inputView.addObserver(this);
    }

    public void setOutputView(OutputView outputView)
    {
        this.outputView = outputView;
        outputView.addObserver(this);
    }

    /**
     * Receive notification of an event in the View.
     * This triggers the Controller to retrieve information from
     * the View and update the Model.
     *
     * @param eventCode value indicating which event occurred
     */
    public void handleEvent(EventCode eventCode)
    {
        switch (eventCode)
        {
            case INITIALIZE:
            {
                this.outputView.initialize(this.model.getBanner());
                break;
            }
            case SET_MODEL_VALUE:
            {
                this.model.setUserInput(this.inputView.getUserInput());
                break;
            }
            case DISPLAY_CONFIG_FILE:
            {
                this.outputView.displayConfigFile(this.model.getConfigurationFile());
                break;
            }
            case START_CHATBOT:
            {
                this.model.startChatting();
                break;
            }
            case EXIT_CHATBOT:
            {
                this.model.exitChatBot();
                break;
            }
            case READ_CONFIG_FILE:
            {
                this.inputView.readConfigurationFile();
                break;
            }
            case READ_USER_INPUT:
            {
                this.inputView.readUserInput();
                break;
            }
            case DISPLAY_USER_INPUT:
            {
                this.outputView.displayOutput(this.model.getUserInput());
                break;
            }
        }

    }
}