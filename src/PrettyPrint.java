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


class EmptyModelError extends Exception
{
    public EmptyModelError()
    { super(); }

    public EmptyModelError(String message)
    { super(message); }

    public EmptyModelError(String message,Throwable cause)
    { super(message,cause); }

}

public class PrettyPrint 
{
    private Alignment alignment;
    private String[] col_model;
    private String[][] col_data;

    /**
     * @param column_model The column model of your table
     * @param column_data  The data of your table for each rows and columns
     */
    public PrettyPrint(String[] column_model,String[][] column_data) throws EmptyModelError
    {
        if (column_model.length <= 0)
            throw new EmptyModelError("Table model cannot be empty!");

        this.alignment  = Alignment.LEFT; // left align is the default
        this.col_model = column_model;
        this.col_data   = column_data;
    }

    /**
     * @param column_model The column model of your table.
     * @param column_data  The data of your table for each rows and columns.
     * @param alignment    Specifies the alignment for each cell in the table except for the model which is always sets to CENTER.
     */
    public PrettyPrint(String[] column_models,String[][] column_data,Alignment alignment) throws EmptyModelError
    {
        if (column_models.length <= 0)
            throw new EmptyModelError("Table model cannot be empty!");

        this.alignment  = alignment; // left align is the default
        this.col_model = column_models;
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

    private String fill_space(String cell_value, int max_size, Alignment alignment)
    {

        String new_cell_data = "";

        int diff = max_size - cell_value.length();

        switch (alignment)
        {
            case CENTER:
            {
                int half = (int) Math.floor(diff / 2);

                half = (half < 0)? 0 : half;

                for (int s = 0; s < half; s ++)
                    new_cell_data += " ";
                
                new_cell_data += cell_value;

                for (int s = 0; s < diff - half; s ++)
                    new_cell_data += " ";

                break;
            }
            case LEFT:
            {
                new_cell_data += cell_value;

                for (int s = 0; s < diff; s ++)
                    new_cell_data += " ";

                break;
            }
            case RIGHT:
            {
                for (int s = 0; s < diff; s ++)
                    new_cell_data += " ";
                
                new_cell_data += cell_value;

                break;
            }
        }

        return new_cell_data;

    }

    /**
     * Returns the table as string.
     */
    public String as_String()
    {

        String[][] table = new String[this.col_data.length + 1][this.col_model.length];

        table[0] = this.col_model;

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
                asrow += this.fill_space(
                    row[idx_j], 
                    row_max[idx_j],
                    (idx_i == 0) ? Alignment.CENTER : this.alignment
                );
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
    /**
     * Prints the table into console.
     */
    public void pprint()
    {
        System.out.println(this.as_String());
    }

    @Override
    public String toString() 
    {
        return this.as_String();
    }

}


