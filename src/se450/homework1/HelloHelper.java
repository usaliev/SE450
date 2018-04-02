package se450.homework1;

import java.io.PrintStream;

public class HelloHelper
{
    public void sayHello(String name)
    {
        PrintStream ps = new PrintStream(System.out);
        ps.println("This is your HelloHelper speaking...");
        ps.println("Hello " + name + "!");
    }
}
