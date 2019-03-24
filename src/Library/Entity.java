/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import Utilities.Consts;
import javafx.scene.image.Image;

/**
 *
 * @author Dorian
 */
public class Entity
{
    private Image _image;
    private String _imagePath;
    private int _x;
    private int _y;
    private Grid _grid;
    private EntityType _type;
    
    public Entity(int x, int y, Grid g, String imgPath, EntityType type)
    {
        this._imagePath = imgPath;
        this._image = new Image(this._imagePath);
        this._x = x;
        this._y = y;
        this._grid = g;
        this._type = type;
    }
    
    public Image getImage()
    {
        return this._image;
    }
    
    public void setImage(Image image)
    {
        this._image = image;
    }
    
    public int getX()
    {
        return this._x;
    }
    
    public int getY()
    {
        return this._y;
    }
    
    public EntityType getType()
    {
        return this._type;
    }
    
    public void setNewImage(String imgPath)
    {
        this._imagePath = imgPath;
        this._image = new Image(imgPath);
    }
    
    public boolean isTravellable(Direction dir)
    {
        switch(dir)
        {
            case RIGHT:
                return this._grid.isTravellable(_x, _y+1);
            case LEFT:
                return this._grid.isTravellable(_x, _y-1);
            case UP:
                return this._grid.isTravellable(_x-1, _y);
            case DOWN:
                return this._grid.isTravellable(_x+1, _y);
        }
        return false;
    }
    
    public boolean move(Direction dir)
    {
        boolean ret = false;
        switch(dir)
        {
            case RIGHT:
                ret = this._grid.isTravellable(_x, _y+1);
                if(ret)
                {
                    // the entity has moved to the right
                    this._grid.getCells()[this._x][this._y+1] = this;
                    // if the entity was a pacman, the previous cell becomes a default cell
                    if(this._type == EntityType.PACMAN)
                    {
                        this._grid.getCells()[this._x][this._y] = new Entity(this._x, this._y, this._grid, Consts.getDefaultImgPath(), EntityType.DEFAULT);
                        this.setNewImage(Consts.getPacmanRightImgPath());
                    }
                    this._y++;
                }
                break;
            case LEFT:
                ret = this._grid.isTravellable(_x, _y-1);
                if(ret)
                {
                    this._grid.getCells()[this._x][this._y-1] = this;
                    // if the entity was a pacman, the previous cell becomes a default cell
                    if(this._type == EntityType.PACMAN)
                    {
                        this._grid.getCells()[this._x][this._y] = new Entity(this._x, this._y, this._grid, Consts.getDefaultImgPath(), EntityType.DEFAULT);
                        this.setNewImage(Consts.getPacmanLeftImgPath());
                    }
                    this._y--;
                }
                break;
            case UP:
                ret = this._grid.isTravellable(_x-1, _y);
                if(ret)
                {
                    this._grid.getCells()[this._x-1][this._y] = this;
                    // if the entity was a pacman, the previous cell becomes a default cell
                    if(this._type == EntityType.PACMAN)
                    {
                        this._grid.getCells()[this._x][this._y] = new Entity(this._x, this._y, this._grid, Consts.getDefaultImgPath(), EntityType.DEFAULT);
                        this.setNewImage(Consts.getPacmanUpImgPath());
                    }
                    this._x--;
                }
                break;
            case DOWN:
                ret = this._grid.isTravellable(_x+1, _y);
                if(ret)
                {
                    this._grid.getCells()[this._x+1][this._y] = this;
                    // if the entity was a pacman, the previous cell becomes a default cell
                    if(this._type == EntityType.PACMAN)
                    {
                        this._grid.getCells()[this._x][this._y] = new Entity(this._x, this._y, this._grid, Consts.getDefaultImgPath(), EntityType.DEFAULT);
                        this.setNewImage(Consts.getPacmanDownImgPath());
                    }
                    this._x++;
                }
                break;
        }
        return ret;
    }
}
