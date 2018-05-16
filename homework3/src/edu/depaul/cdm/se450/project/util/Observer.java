package edu.depaul.cdm.se450.project.util;

/**
 *  A class implements the Observerable interface in order
 *	to be able to notify Observer objects of events and provide
 *	specific information about what event occurred.
 *  <p>
 *  This is a basic specific messaging Observer interface, really useful
 *  only if the Obeservable is being observed by a single Observer.
 *
 */

public interface Observer {

    /**
     *  Receive notification of an event in an Observable object.
     *
     *	@param eventCode value indicating which event occurred
     */

    public void handleEvent( EventCode eventCode );
}