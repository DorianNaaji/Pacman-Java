/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

/**
 *
 * @author Dorian
 */
public class Grid
{
    private int _lines;
    private int _columns;
    private Cell[][] _cells;
    
    /**
     * 
     * @param nbLines
     * @param nbCols 
     */
    public Grid(int nbLines, int nbCols)
    {
        this._lines = nbLines;
        this._columns = nbCols;
        this._cells = new Cell[nbLines][nbCols];
        for(int i = 0; i < nbLines; i++)
        {
            for(int j = 0; j < nbCols; j++)
            {
                this._cells[i][j] = new Cell();
            }
        }
        this._cells[5][7].setContent(new Entity("P"));
    }
    
    public Cell[][] getCells()
    {
        return this._cells;
    }
    
}
