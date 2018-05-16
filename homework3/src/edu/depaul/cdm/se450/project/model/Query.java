package edu.depaul.cdm.se450.project.model;

public class Query extends Statement
{
    private String tag;
    private String subtag;

    public Query(String tag, String subtag, String statement)
    {
        super(statement);
        this.tag = tag;
        this.subtag = subtag;
    }

    public Query(String fullTag, String statement)
    {
        super(statement);
        this.splitTags(fullTag);
    }

    public String getTag()
    {
        return this.tag;
    }

    public String getSubtag()
    {
        return this.subtag;
    }

    private void splitTags(String fullTag)
    {
        // based on the current documentation a tag will only have 2 parts delimited by a '.'
        String[] splitTag = fullTag.split("\\.");
        this.tag = splitTag[0];
        this.subtag = splitTag[1];
    }
}
