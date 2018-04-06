package edu.cdm.se450.assign_02.hello;

import java.util.ArrayList;
import edu.cdm.se450.assign_02.util.HelloHelper;
import edu.cdm.se450.assign_02.intf.HelloInterface;

public class Hello implements HelloInterface
{
    private ArrayList<String> nameList;

    public Hello(ArrayList<String> nameList)
    {
        this.nameList = nameList ;
    }

    public void useHelper()
    {
        HelloHelper helper = new HelloHelper();

        // iterate the nameList and say hello to everyone
        for(String name : this.nameList)
        {
            helper.sayHello(name);
        }
    }
}