/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import static Library.Direction.DOWN;
import static Library.Direction.LEFT;
import static Library.Direction.RIGHT;
import static Library.Direction.UP;
import Utilities.Consts;
import java.util.ArrayList;
import java.util.Random;    

/**
 *
 * @author Dorian
 */
public class Entity
{

    private String _imagePath;
    private int _x;
    private int _y;
    private Grid _grid;
    private EntityType _type;

    /**
     * 
     * @param x : x pos of the entity
     * @param y : y pos of the entity
     * @param g : the grid
     * @param imgPath : the path of the entity img texture
     * @param type : the type of the entity
     */
    public Entity(int x, int y, Grid g, String imgPath, EntityType type)
    {
        this._imagePath = imgPath;
        this._x = x;
        this._y = y;
        this._grid = g;
        this._type = type;
    }


    /**
     * Getter
     * @return the X position of the current entity
     */
    public int getX()
    {
        return this._x;
    }

    /**
     * Getter
     * @return The Y position of the current entity 
     */
    public int getY()
    {
        return this._y;
    }

    /**
     * Getter
     * @return the type of the entity
     */
    public EntityType getType()
    {
        return this._type;
    }

    /**
     * Sets a new Image & new ImagePath to the current entity
     * @param imgPath : The path of the img
     */
    public void setImgPath(String imgPath)
    {
        this._imagePath = imgPath;
    }
    
    public String getImgPath()
    {
        return this._imagePath;
    }

    /**
     * Checks if  a direction is travellable
     * @param dir the direction 
     * @return true if travellable. False otherwise
     */
    public boolean isTravellable(Direction dir)
    {
        switch (dir)
        {
            case RIGHT:
                return this._grid.isTravellable(_x, _y + 1);
            case LEFT:
                return this._grid.isTravellable(_x, _y - 1);
            case UP:
                return this._grid.isTravellable(_x - 1, _y);
            case DOWN:
                return this._grid.isTravellable(_x + 1, _y);
        }
        return false;
    }

    /**
     * AI for ghosts (totally random, can be changed for a better one)
     */
    public void moveAI()
    {
        boolean flag = false;
        ArrayList<Direction> directions = new ArrayList<Direction>();
        directions.add(Direction.DOWN);
        directions.add(Direction.LEFT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.UP);
        Random r = new Random();
        int cpt = 0;
        while (!flag)
        {
            int i = r.nextInt(4);
            if (this.move(directions.get(i)))
            {
                flag = true;
            }
            if (cpt > 20)
            {
                flag = true;
            }
            cpt++;
        }
    }

    /**
     * Moves an entity
     * @param dir the direction in which will move the entity
     * @return true if the entity has moved. False otherwise.
     */
    public boolean move(Direction dir)
    {
        boolean ret = false;
        switch (dir)
        {
            case RIGHT:
                ret = this.move(0, 1);
                if(this._type == EntityType.PACMAN)
                {
                    this._imagePath = Consts.getPacmanRightImgPath();
                }
                break;
            case LEFT:
                ret = this.move(0, - 1);
                if(this._type == EntityType.PACMAN)
                {
                    this._imagePath = Consts.getPacmanLeftImgPath();
                }
                break;
            case UP:
                ret = this.move(- 1, 0);
                if(this._type == EntityType.PACMAN)
                {
                    this._imagePath = Consts.getPacmanUpImgPath();
                }
                break;
            case DOWN:
                ret = this.move(1, 0);
                if(this._type == EntityType.PACMAN)
                {
                    this._imagePath = Consts.getPacmanDownImgPath();
                }
                break;
        }
        return ret;
    }

    /**
     * Moves the current entity according to given offsets
     * @param xOffset vertical offset
     * @param yOffset horizontal offset
     * @return 
     */
    private boolean move(int xOffset, int yOffset)
    {
        boolean res = false;
        boolean lost = false;
        res = this._grid.isTravellable(_x + xOffset, _y + yOffset);
        if (this._type == EntityType.PACMAN /*&& !Game.isPacmanBoosted()*/)
        {
            lost = this._grid.contains(_x + xOffset, _y + yOffset, EntityType.GHOST);
        }
        else if(this._type == EntityType.GHOST && !Game.isPacmanBoosted())
        {
            lost = this._grid.contains(_x + xOffset, _y + yOffset, EntityType.PACMAN);
        }
        
        boolean gum = this._grid.contains(_x + xOffset, _y + yOffset, EntityType.GUM);
        boolean bigGum = this._grid.contains(_x + xOffset, _y + yOffset, EntityType.BIG_GUM);
        if (res & !lost)
        {
            if (this._type == EntityType.PACMAN)
            {
                // the entity has moved to the right
                this._grid.getCells()[this._x + xOffset][this._y + yOffset] = this;
                this._grid.getCells()[this._x][this._y] = new Entity(this._x, this._y, this._grid, Consts.getDefaultImgPath(), EntityType.DEFAULT);
                if(gum || bigGum)
                {
                    this._grid.eatenGum();
                    if(bigGum)
                    {
                        Game.setPacmanBoosted();
                    }
                }
            }
            if (this._type == EntityType.GHOST)
            {

                boolean ghost = this._grid.contains(_x + xOffset, _y + yOffset, EntityType.GHOST);
                //if the ghost were about to "meet", the function returns false.
                if (ghost)
                {
                    return false;
                } // the previous cell was a gum
                else if (gum)
                {
                    this._grid.getCells()[this._x][this._y] = new Entity(this._x, this._y, this._grid, Consts.getSmallGumImgPath(), EntityType.GUM);
                } // the previous cell was a big gum
                else if (bigGum)
                {
                    this._grid.getCells()[this._x][this._y] = new Entity(this._x, this._y, this._grid, Consts.getBigGumImgPath(), EntityType.BIG_GUM);
                } // the previous cell was default
                else
                {
                    this._grid.getCells()[this._x][this._y] = new Entity(this._x, this._y, this._grid, Consts.getDefaultImgPath(), EntityType.DEFAULT);
                }
                this._grid.getCells()[this._x + xOffset][this._y + yOffset] = this;
            }
            this._y += yOffset;
            this._x += xOffset;
        }
        if (lost)
        {
            this._grid.setLost(true);
        }
        return res;
    }
}
