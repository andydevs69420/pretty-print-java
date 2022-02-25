/*
 *   Copyright (c) 2022 
 *   All rights reserved.
 */



/*
 * tests
 * TODO: DELETE THIS FILE!!!!
 */

public class Test 
{
    public static void main(String[] args) throws InvalidTableError
    {

        String[] model  = {"Name","Age","Address","Contact no"};

        String[][] data = {
            {"Billy Joe"      , "21"    , "Barangay Iponan"         , "0935XXXXXXX"},
            {"Andy"           , "69420" , "Igpit Youngsville"       , "0926XXXXXXX"},
            {"Marielle"       , "22"    , "Initao Misamis Oriental" , "0967XXXXXXX"},
            {"Philipp Andrew" , "23"    , "Igpit Youngsville"       , "0945XXXXXXX"}
        };

        PrettyPrint pp = new PrettyPrint(model, data, Alignment.LEFT);
        
        System.out.println("left align");
        System.out.println(pp);

    }
}
