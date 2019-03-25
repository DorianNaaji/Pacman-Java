/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import FileAndUserIO.FileIO;
import static Library.EntityType.GUM;
import MyExceptions.EntityNotFoundException;
import Utilities.Consts;
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
    private boolean _lost = false;
    private boolean _win = false;

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
        this._map = Utilities.StrUtilities.StringTo2D_CharArray(FileIO.readTextFile(Consts.getMapTxtPath()), rows, cols);
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if(this._map[i][j] == '0')
                {
                    this._cells[i][j] = new Entity(i, j, this, Consts.getSmallGumImgPath(), EntityType.GUM);
                    this._entities.add(this._cells[i][j]);
                }
                else if(this._map[i][j] == '1')
                {
                    this._cells[i][j] = new Entity(i, j, this, Consts.getWallImgPath(), EntityType.WALL);
                    this._entities.add(this._cells[i][j]);
                }
                else if(this._map[i][j] == '2')
                {
                    this._cells[i][j] = new Entity(i, j, this, Consts.getBigGumImgPath(), EntityType.BIG_GUM);
                    this._entities.add(this._cells[i][j]);
                }
                else if(this._map[i][j] == '3')
                {
                    this._cells[i][j] = new Entity(i, j, this, Consts.getPacmanRightImgPath(), EntityType.PACMAN);
                    this._entities.add(this._cells[i][j]);
                }
                else if(this._map[i][j] == '4')
                {
                    this._cells[i][j] = new Entity(i, j, this, Consts.getRedGhostImgPath(), EntityType.GHOST);
                    this._entities.add(this._cells[i][j]);
                }
                else if(this._map[i][j] == '5')
                {
                    this._cells[i][j] = new Entity(i, j, this, Consts.getPinkGhostImgPath(), EntityType.GHOST);
                    this._entities.add(this._cells[i][j]);
                }
                else if(this._map[i][j] == '6')
                {
                    this._cells[i][j] = new Entity(i, j, this, Consts.getOrangeGhostImgPath(), EntityType.GHOST);
                    this._entities.add(this._cells[i][j]);
                }
            }
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
            return ((this._cells[x][y].getType() != EntityType.WALL));
        } 
        else
        {
            return false;
        }
    }
    
    public boolean containsGhost(int x, int y)
    {
        if ((x < this._rows) && (y < this._columns) && (x >= 0) && (y >= 0))
        {
            return ((this._cells[x][y].getType() == EntityType.GHOST));
        } 
        else
        {
            return false;
        }
    }
    
    public boolean containsGum(int x, int y)
    {
        if ((x < this._rows) && (y < this._columns) && (x >= 0) && (y >= 0))
        {
            return ((this._cells[x][y].getType() == EntityType.GUM));
        } 
        else
        {
            return false;
        }
    }
    
        public boolean containsBigGum(int x, int y)
    {
        if ((x < this._rows) && (y < this._columns) && (x >= 0) && (y >= 0))
        {
            return ((this._cells[x][y].getType() == EntityType.BIG_GUM));
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
    
    public Entity getPacman() throws EntityNotFoundException
    {
        for(Entity entity : this._entities)
        {
            if(entity.getType() == EntityType.PACMAN)
            {
                return entity;
            }
        }
        throw new EntityNotFoundException("Pacman not found");
    }
    
    public ArrayList<Entity> getGhosts() throws EntityNotFoundException
    {
        ArrayList<Entity> ghosts = new ArrayList<Entity>();
        for(Entity entity : this._entities)
        {
            if(entity.getType() == EntityType.GHOST)
            {
                ghosts.add(entity);
            }
        }
        if(!ghosts.isEmpty())
        {
            return ghosts;
        }
        else
        {
            throw new EntityNotFoundException("Ghosts not found");
        }
    }
    
    public void setLost(boolean lost)
    {
        this._lost = lost;
    }
    
    public boolean getLost()
    {
        return this._lost;
    }
}
