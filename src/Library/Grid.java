/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import FileAndUserIO.FileIO;
import java.util.ArrayList;

/**
 *
 * @author Dorian
 */
public class Grid
{

    private int _rows;
    private int _columns;
    private Entity[][] _cells;
    private ArrayList<Entity> _entities = new ArrayList<Entity>();
    private char[][] _map;

    /**
     *
     * @param rows
     * @param cols
     */
    public Grid(int rows, int cols)
    {
        this._rows = rows;
        this._columns = cols;
        this._cells = new Entity[rows][cols];
        this._map = Utilities.StrUtilities.StringTo2D_CharArray(FileIO.readTextFile("src/resources/map.txt"), rows, cols);
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if(this._map[i][j] == '0')
                {
                    this._cells[i][j] = new Entity(i, j, this, "resources/default_small_gum.png");
                    this._entities.add(this._cells[i][j]);
                }
                else if(this._map[i][j] == '1')
                {
                    this._cells[i][j] = new Entity(i, j, this, "resources/wall.png");
                    this._entities.add(this._cells[i][j]);
                }
                else if(this._map[i][j] == '2')
                {
                    this._cells[i][j] = new Entity(i, j, this, "resources/default_big_gum.png");
                    this._entities.add(this._cells[i][j]);
                }
                else if(this._map[i][j] == '3')
                {
                    this._cells[i][j] = new Entity(i, j, this, "resources/pacman_right.png");
                    this._entities.add(this._cells[i][j]);
                }
                //this._cells[i][j] = new Entity(i, j, this, "resources/pacman_top.png");
            }
            System.out.println();
            // putting a PACMAN in line 5, col 7
            //Entity e = new Entity("P", 5, 7, this);
            //this._cells[5][7] = e;
            //this._entities.add(e);
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
        if ((x < this._rows) && (y < this._columns) && (x >= 0) && (y >= 0))
        {
            return ((this._cells[x][y] == null));
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
