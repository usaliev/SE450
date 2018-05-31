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
                this.initialize();
                break;
            }
            case SET_MODEL_VALUE:
            {
                this.setUserInput();
                break;
            }
            case DISPLAY_CONFIG_FILE:
            {
                this.displayConfig();
                break;
            }
            case START_CHATBOT:
            {
                this.startChatbot();
                break;
            }
            case EXIT_CHATBOT:
            {
                this.exitChatbot();
                break;
            }
            case READ_CONFIG_FILE:
            {
                this.readConfigFile();
                break;
            }
            case READ_USER_INPUT:
            {
                this.readInput();
                break;
            }
            case DISPLAY_OUTPUT:
            {
                this.displayOutput();
                break;
            }
        }
    }

    private void initialize()
    {
        this.outputView.displayOutput(this.model.getBanner());
    }

    private void setUserInput()
    {
        this.model.setUserInput(this.inputView.getUserInput());
    }

    private void displayConfig()
    {
        this.outputView.displayConfigFile(this.model.getConfigurationFile());
    }

    private void displayOutput()
    {
        this.outputView.displayOutput(this.model.getUserInput());
    }

    private void startChatbot()
    {
        this.model.startChatting();
    }

    private void exitChatbot()
    {
        this.model.exitChatBot();
    }

    private void readConfigFile()
    {
        this.inputView.readConfigurationFile();
    }

    private void readInput()
    {
        this.inputView.readUserInput();
    }
}