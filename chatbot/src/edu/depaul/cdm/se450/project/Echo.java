package edu.depaul.cdm.se450.project;

import edu.depaul.cdm.se450.project.controllers.Controller;
import edu.depaul.cdm.se450.project.model.Model;
import edu.depaul.cdm.se450.project.views.InputView;
import edu.depaul.cdm.se450.project.views.OutputView;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This is the driver (start-up) class for a sample implementation of
 * the application. It demonstrates the basic principles of the MVC architecture,
 */
public class Echo
{
    public static void main(String[] args)
    {

        Model eModel;               //  Model architectural component
        InputView inputView;        //  Input view architectural component
        OutputView outputView;        //	Output view architectural component
        Controller eController;     //  Controller architectural component

        //  Banner for program.
        String eBanner = "Welcome to Chatbot v. 0.1";

        /*
         *  Create instances of each of the architectural components
         *  of the application: Model, View, and Controller.
         */
        eModel = new Model();
        eController = new Controller();

        /*
         *  Provide each of the architectural elements with the
         *  appropriate references needed for them to set up
         *  the Observer pattern.
         */
        inputView = new InputView();
        inputView.setModel(eModel);
        eController.setView(inputView);
        outputView = new OutputView();
        outputView.setModel(eModel);

        /*
         *  Provide the Controller with a reference to the Model so
         *  that it can update the Model.
         */
        eController.setModel(eModel);

        /*
         * Initialize Model instance with program banner.
         */
        eModel.setBanner(eBanner);

        /*
         * Get the configuration file at the path specified in the commandline
         */
        eModel.setConfigurationFile(args[0]);

        /*
         *  Start execution of the Model.
         */
        eModel.run();

        /*
         *  Let user know program terminated properly.
         */
        System.out.println("Echo terminated successfully.");
    }
}