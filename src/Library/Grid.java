/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.util.ArrayList;

/**
 *
 * @author Dorian
 */
public class Grid
{

    private int _lines;
    private int _columns;
    private Entity[][] _cells;
    private ArrayList<Entity> _entities = new ArrayList<Entity>();

    /**
     *
     * @param nbLines
     * @param nbCols
     */
    public Grid(int nbLines, int nbCols)
    {
        this._lines = nbLines;
        this._columns = nbCols;
        this._cells = new Entity[nbLines][nbCols];
        for (int i = 0; i < nbLines; i++)
        {
            for (int j = 0; j < nbCols; j++)
            {
                this._cells[i][j] = new Entity(" ", i, j, this);
            }
            // putting a PACMAN in line 3, col 6
            Entity e = new Entity("P", 2, 5, this);
            this._cells[2][5] = e;
            this._entities.add(e);
        }
    }

    public Entity[][] getCells()
    {
        return this._cells;
    }

    /**
     * Checks if a cell is travellable or not , ie if it's empty or not out of the grid
     * @param x the line to check
     * @param y the col to check
     * @return 
     */
    public boolean isTravellable(int x, int y)
    {
        if ((x < this._lines) && (y < this._columns) && (x >= 0) && (y >= 0))
        {
            return ((this._cells[x][y] == null) || (this._cells[x][y].getName() == " "));
        } 
        else
        {
            return false;
        }
    }

    public ArrayList<Entity> getEntities()
    {
        return this._entities;
    }
}
