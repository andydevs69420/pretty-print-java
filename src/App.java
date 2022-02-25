/*
 *   Copyright (c) 2022 
 *   All rights reserved.
 */



/*
 * tests
 */

public class App 
{
    public static void main(String[] args)
    {

        String[] headers = {"Name","Age","Address","Contact no"};

        String[][] data  = {
            {"Philipp1","21","Barangay Iponan"  ,"09455477865"},
            {"Philipp2","22","Igpit Younsville" ,"09455477865"},
            {"Philipp3","23","Igpit Younsville" ,"09455477865"},
            {"Philipp0","20","Igpit Youngsvillezzzzzzzzzzzzzzzzzzz","09455477865"}
        };

        PrettyPrint pp = new PrettyPrint(headers, data);
        
        pp.pprint();

    }
}
