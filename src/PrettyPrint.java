/*
 *   Copyright (c) 2022 
 *   All rights reserved.
 */

enum Alignment 
{
    CENTER ,
    LEFT   ,
    RIGHT  ,
}

public class PrettyPrint 
{

    private String[] col_models;
    private String[][] col_data;

    public PrettyPrint(String[] column_models,String[][] column_data)
    {
        this.col_models = column_models;
        this.col_data   = column_data;
    }

    private int[] getColMax(String[][] table)
    {

        int[] col_max = new int[table[0].length];

        for (int idx = 0; idx < col_max.length; idx ++)
            for (int outer = 0; outer < table.length; outer ++)
                    for (int inner = 0; inner < table.length; inner ++)
                        if (table[inner][idx].length() > col_max[idx])
                            col_max[idx] = table[inner][idx].length();

        return col_max;

    }

    private String fill_space(String cell_value, int max_size)
    {

        String new_cell_data = "";

        int diff = max_size - cell_value.length();

        new_cell_data += cell_value;

        for (int s = 0; s < diff; s ++)
            new_cell_data += " ";

        return new_cell_data;

    }

    public String as_String()
    {

        String[][] table = new String[this.col_data.length + 1][this.col_models.length];

        table[0] = this.col_models;

        // populate first
        for (int idx = 0; idx < this.col_data.length; idx++)
            table[idx + 1] = this.col_data[idx];
        
        int[] row_max  = this.getColMax(table);

        String builder = "";

        // build heere

        for (int ccol = 0; ccol < row_max.length; ccol ++)
        {
            if (ccol == 0)
                builder += "+";

            for (int dash = 0; dash < row_max[ccol] + 2/*padding l-r*/; dash ++)
            {

                builder += "-";

                if (dash == (row_max[ccol] + 2) - 1)
                    builder += "+";
                
            }
        }
        
        builder += "\n";

        for (int idx_i = 0; idx_i < table.length; idx_i ++)
        {

            String[] row = table[idx_i];
            String asrow = "";

            for (int idx_j = 0; idx_j < row.length; idx_j ++)
            {
                asrow += "|";
                asrow += " "; // l padding
                asrow += this.fill_space(row[idx_j], row_max[idx_j]);
                asrow += " "; // r padding

                if (idx_j == (row.length - 1))
                    asrow += "|";
            }

            builder += asrow;
            builder += "\n";

            for (int ccol = 0; ccol < row_max.length; ccol ++)
            {
                if (ccol == 0)
                    builder += "+";

                for (int dash = 0; dash < row_max[ccol] + 2/*padding l-r*/; dash ++)
                {

                    builder += "-";

                    if (dash == (row_max[ccol] + 2) - 1)
                        builder += "+";
                    
                }
            }
            
            builder += "\n";

        }
        
        return builder;

    }

    public void pprint()
    {
        System.out.println(this.as_String());
    }

}


