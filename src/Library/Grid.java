/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import FileAndUserIO.FileIO;
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
    private int _nbGums;

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
                switch(this._map[i][j])
                {
                    case '0':
                        this._cells[i][j] = new Entity(i, j, this, Consts.getSmallGumImgPath(), EntityType.GUM);
                        this._entities.add(this._cells[i][j]);
//                        this._cells[i][j] = new Entity(i, j, this, Consts.getDefaultImgPath(), EntityType.DEFAULT);
//                        this._entities.add(this._cells[i][j]);
                        break;
                    case '1':
                        this._cells[i][j] = new Entity(i, j, this, Consts.getWallImgPath(), EntityType.WALL);
                        this._entities.add(this._cells[i][j]);
                        break;
                    case '2':
                        this._cells[i][j] = new Entity(i, j, this, Consts.getBigGumImgPath(),EntityType.BIG_GUM);
                        this._entities.add(this._cells[i][j]);
                        break;
                    case '3':
                        this._cells[i][j] = new Entity(i, j, this, Consts.getPacmanRightImgPath(), EntityType.PACMAN);
                        this._entities.add(this._cells[i][j]);
                        break;
                    case '4':
                        this._cells[i][j] = new Entity(i, j, this, Consts.getPinkGhostImgPath(), EntityType.GHOST);
                        this._entities.add(this._cells[i][j]);
                        break;                    
                    case '5':
                        this._cells[i][j] = new Entity(i, j, this, Consts.getRedGhostImgPath(),EntityType.GHOST);
                        this._entities.add(this._cells[i][j]);
                        break;
                    case '6':
                        this._cells[i][j] = new Entity(i, j, this, Consts.getOrangeGhostImgPath(), EntityType.GHOST);
                        this._entities.add(this._cells[i][j]);
                        break;
                }
            }
        }
        this._nbGums = this.countGums();
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
    
    /**
     * Checks if a cell contains a give entity
     * @param x the x pos of the entity
     * @param y the y pos of the entity
     * @param type the type of the entity
     * @return 
     */
    public boolean contains(int x, int y, EntityType type)
    {
        if ((x < this._rows) && (y < this._columns) && (x >= 0) && (y >= 0))
        {
            return ((this._cells[x][y].getType() == type));
        } 
        else
        {
            return false;
        }
    }

    private int countGums()
    {
        int gums = 0;
        for(int i = 0; i < this._entities.size(); i++)
        {
            if(this._entities.get(i).getType() == EntityType.GUM || this._entities.get(i).getType() == EntityType.BIG_GUM)
            {
                gums++;
            }
        }
        return gums;
    }
    
    /**
     * Reduces the amount of gum by one if one has been eaten
     */
    public void eatenGum()
    {
        this._nbGums--;
    }
    
    /**
     * 
     * @return true if the grid does not contain any gum. False otherwise 
     */
    public boolean noMoreGums()
    {
        return this._nbGums == 0;
    }

    public ArrayList<Entity> getEntities()
    {
        return this._entities;
    }
    
    /**
     * Gets the pacman
     * @return the pacman if found
     * @throws EntityNotFoundException an exception if not
     */
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
    
       public void ChangeGhostState(boolean afraid) throws EntityNotFoundException
    {
        for(int i = 0; i < this.getGhosts().size(); i++)
        {
            if(afraid)
            {
                this.getGhosts().get(i).setImgPath(Consts.getAfraidGhostImgPath());
            }
            else
            {
                switch(i)
                {
                    case 0:
                        this.getGhosts().get(i).setImgPath(Consts.getPinkGhostImgPath());
                        break;
                    case 1:
                        this.getGhosts().get(i).setImgPath(Consts.getRedGhostImgPath());
                        break;
                    case 2:
                        this.getGhosts().get(i).setImgPath(Consts.getOrangeGhostImgPath());
                        break;
                }
            }
        }
    }
    
    /**
     * 
     * @return the ghosts if found
     * @throws EntityNotFoundException if not found
     */
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
