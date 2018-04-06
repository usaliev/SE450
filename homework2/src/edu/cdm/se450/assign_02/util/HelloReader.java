package edu.cdm.se450.assign_02.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HelloReader
{
    public static ArrayList<String> readNames(int numNames)
    {
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        ArrayList<String> nameList = new ArrayList<>();

        try
        {
            System.out.println();

            // No validation done to ensure it's actually a name
            for( int i = 1; i <= numNames; ++i)
            {
                System.out.print("Enter name " + i + " of " + numNames + ": ");
                nameList.add(reader.readLine());
                System.out.println();
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error reading names" + ex);
        }

        return nameList;
    }
}
