package se450.homework1;

public class Hello implements HelloInterface
{
    String name;

    public Hello( String name )
    {
        this.name = name ;
    }

    public void useHelper()
    {
        HelloHelper helper = new HelloHelper();
        helper.sayHello(this.name);
    }
}
