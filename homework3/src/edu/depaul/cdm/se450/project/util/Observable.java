package edu.depaul.cdm.se450.project.util;

/**
 *  A class implements the Observerable interface in order
 *	to be able to notify Observer objects of events and provide
 *	specific information about what event occurred.
 *
 */

public interface Observable {

    /**
     *  Add an observer to the list of observers for this object. An observer
     *  is entered into the list only once.
     *
     *  @param o observer to be added to list of observers for this object
     */

    public void addObserver( Observer o );

    /**
     *  Remove an observer from the list of observers for this object.
     *
     *  @param o observer to be removed from list of observers for this object
     */

    public void removeObserver( Observer o );

    /**
     *  Notify observers of this object that an event in this object has
     *  occurred.
     *
     *	@param eventCode value indicating which event occurred
     */

    public void notifyObservers( EventCode eventCode );
}