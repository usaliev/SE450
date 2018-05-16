package edu.depaul.cdm.se450.project.model;

import javax.swing.plaf.nimbus.State;

public class Response
{
    private String tag;
    private String subtag;
    private Statement response;

    public Response(String tag, String subtag, Statement response)
    {
        this.tag = tag;
        this.subtag = subtag;
        this.response = response;
    }

    public Response(String fullTag, String statement)
    {
        this.splitTags(fullTag);
        this.response = new Statement(statement);
    }

    public String getTag()
    {
        return this.tag;
    }

    public String getSubtag()
    {
        return this.subtag;
    }

    public Statement getResponse()
    {
        return this.response;
    }
    private void splitTags(String fullTag)
    {
        // based on the current documentation a tag will only have 2 parts delimited by a '.'
        String[] splitTag = fullTag.split("\\.");
        this.tag = splitTag[0];
        this.subtag = splitTag[1];
    }

}
