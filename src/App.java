/*
 *   Copyright (c) 2022 
 *   All rights reserved.
 */



/*
 * tests
 */

public class App 
{
    public static void main(String[] args) throws EmptyModelError
    {

        String[] headers = {"Name","Age","Address","Contact no"};

        String[][] data  = {
            {"Billy Joe"      , "21"    , "Barangay Iponan"         , "0945547786500000000000"},
            {"Andy"           , "69420" , "Igpit Younsville"        , "09455477865"},
            {"Marielle"       , "22"    , "Initao Misamis Oriental" , "09455477865"},
            {"Philipp Andrew" , "23"    , "Igpit Youngsville"       , "09455477865"}
        };

        PrettyPrint pp = new PrettyPrint(headers, data, Alignment.CENTER);
        
        System.out.println(pp);

    }
}
