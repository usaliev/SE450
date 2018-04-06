package edu.cdm.se450.assign_02;

import static java.lang.Integer.parseInt;
import edu.cdm.se450.assign_02.hello.Hello;
import edu.cdm.se450.assign_02.util.HelloReader;

public class HelloRunner
{
    public static void main(String[] args)
    {
        if(args.length != 0)
        {
            Hello hello = new Hello(HelloReader.readNames(parseInt(args[0])));
            hello.useHelper();
        }
        else
        {
            System.out.println("Missing argument");
            System.out.println("Usage: HelloRunner [num of names]");
        }
    }
}
