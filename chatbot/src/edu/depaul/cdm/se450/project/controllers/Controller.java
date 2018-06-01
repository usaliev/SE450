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
    State state;
    private InputView inputView;
    private OutputView outputView;
    private Model model;
    private State processingState;
    private State ioState;

    /**
     * Constructor
     */
    public Controller(Model model, InputView inputView, OutputView outputView)
    {
        this.model = model;
        this.inputView = inputView;
        this.outputView = outputView;

        this.model.addObserver(this);
        this.inputView.addObserver(this);
        this.outputView.addObserver(this);

        this.ioState = new IOState(this, inputView, outputView, model);
        this.processingState = new ProcessingState(this, model);

        state = this.processingState;
    }

    /**
     * Get the current state
     *
     * @return the state of the controller
     */
    public State getState()
    {
        return this.state;
    }

    /**
     * Set the state
     *
     * @param state the state to set
     */
    public void setState(State state)
    {
        this.state = state;
    }

    /**
     * Gets the ioState
     *
     * @return the ioState
     */
    public State getIOState()
    {
        return this.ioState;
    }

    /**
     * Gets the processingState
     *
     * @return the processingState
     */
    public State getProcessingState()
    {
        return processingState;
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
                this.state.writeOutput(this.model.getBanner());
                break;
            }
            case DISPLAY_CONFIG_FILE:
            {
                this.state.writeConfig();
                break;
            }
            case START_CHATBOT:
            {
                //Already in the processing state when this event is triggered so we dont need to switch
                this.model.startChatting();
                break;
            }
            case EXIT_CHATBOT:
            {
                this.state.exitChatbot();
                break;
            }
            case READ_CONFIG_FILE:
            {
                this.state.readConfig();

                //Check if an error occurred during displaying the config, if one has write it to output
                if (this.model.getUserInput() != null)
                {
                    this.state.writeOutput(this.model.getUserInput());
                    this.state.exitChatbot();
                }
                break;
            }
            case READ_USER_INPUT:
            {
                this.state.readInput();
                break;
            }
            case DISPLAY_OUTPUT:
            {
                this.state.writeOutput(this.model.getUserInput());
                break;
            }
        }
    }
}