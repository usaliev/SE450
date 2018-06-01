package edu.depaul.cdm.se450.project.model;

public class ExpectedResponse implements ChatbotResponse
{
    String baseString = "I understand that you are";
    String userStatement;

    public ExpectedResponse(String userStatement)
    {
        this.userStatement = userStatement;
    }

    public String getResponse()
    {
        String predicate;
        int beginIndex = 0;

        if (userStatement.toLowerCase().contains("i am"))
        {
            beginIndex = userStatement.toLowerCase().indexOf("i am") + 5;
        }
        if (userStatement.toLowerCase().contains("i'm".toLowerCase()))
        {
            beginIndex = userStatement.toLowerCase().indexOf("i'm") + 4;
        }

        String userStatementNoOpening = userStatement.substring(beginIndex);
        String[] wordsArray = userStatementNoOpening.split(" ");
        predicate = wordsArray[0];

        return baseString + " " + this.cleanUpPredicate(predicate);
    }

    private String cleanUpPredicate(String predicate)
    {
        predicate = predicate.replace(",", "");
        predicate = predicate.replace(".", "");
        predicate = predicate.replace("'", "");
        predicate = predicate.replace("\"", "");
        predicate = predicate.replace("?", "");
        predicate = predicate.replace("!", "");
        predicate = predicate.replace(";", "");

        return predicate;
    }
}
