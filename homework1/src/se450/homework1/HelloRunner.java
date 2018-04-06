package se450.homework1;

public class HelloRunner
{
    public static void main(String[] args)
    {
        if(args.length != 0)
        {
            Hello hello = new Hello(args[0]);
            hello.useHelper();
        }
        else
        {
            System.out.println("Missing argument");
            System.out.println("Usage: HelloRunner [name]");
        }
    }
}


