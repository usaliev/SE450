package edu.depaul.cdm.se450.project.model;

public class User
{
    private String firstName;
    private String terminator;

    public String getFirstName()
    {
        return  this.firstName;
    }

    public String getTerminator()
    {
        return this.terminator;
    }


    public void setTerminator(String terminator)
    {
        this.terminator = terminator;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
}
