package edu.depaul.cdm.se450.project.views;

import java.util.ArrayList;
import java.util.ListIterator;

import edu.depaul.cdm.se450.project.model.Model;
import edu.depaul.cdm.se450.project.util.Observer;
import edu.depaul.cdm.se450.project.util.Observable;
import edu.depaul.cdm.se450.project.util.EventCode;


/**
 *  View superclass for the application. Provides basic fields and
 *	functionality of Observer to subclasses.
 *
 */
public abstract class View implements Observer, Observable {

    protected Model model;          	//  Model observed by this View

    /*
     *  List of observers of this View.
     *
     *  Note use of generics to indicate that this is an ArrayList
     *  of Observers.
     */

    protected ArrayList<Observer> observers = new ArrayList<Observer>();

    /**
     *  Set the Model to be observed by this View.
     *
     *  @param m model to be observed
     */
    public void setModel( Model m ) {
        model = m;
        m.addObserver( this );
    }

    public abstract void handleEvent( EventCode eventCode );

    /**
     *  Add an observer to the list of observers for this object. An observer
     *  is entered into the list only once.
     *
     *  @param o observer to be added to list of observers for this object
     */
    public void addObserver( Observer o ) {
        if ( !observers.contains( o ) )
            observers.add( o );
    }

    /**
     *  Remove an observer from the list of observers for this object.
     *
     *  @param o observer to be removed from list of observers for this object
     */
    public void removeObserver( Observer o ) {
        if ( !observers.remove( o ) )
            System.out.println( "Error in Model.removeObserver: " +
                                "Specified Observer not in list." );
    }

    /**
     *  Notify observers of this object that an event in this object has
     *  occurred.
     */
    public void notifyObservers( EventCode eventCode ) {
        ListIterator<Observer> listIterator = observers.listIterator();
        while ( listIterator.hasNext() ) {
            ( listIterator.next() ).handleEvent( eventCode );
        }
    }

}