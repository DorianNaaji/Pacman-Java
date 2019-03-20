/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

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
    
    public Entity(int x, int y, Grid g, String imgPath)
    {
        this._imagePath = imgPath;
        this._image = new Image(this._imagePath);
        this._x = x;
        this._y = y;
        this._grid = g;
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
    
    public boolean move(Direction dir)
    {
        boolean ret = false;
        switch(dir)
        {
            case RIGHT:
                ret = this._grid.isTravellable(_x, _y+1);
                if(ret)
                {
                    this._grid.getCells()[this._x][this._y+1] = this;
                    this._grid.getCells()[this._x][this._y] = null;
                    this._y++;
                }
                break;
            case LEFT:
                ret = this._grid.isTravellable(_x, _y-1);
                if(ret)
                {
                    this._grid.getCells()[this._x][this._y-1] = this;
                    this._grid.getCells()[this._x][this._y]  = null;
                    this._y--;
                }
                break;
            case TOP:
                ret = this._grid.isTravellable(_x-1, _y);
                if(ret)
                {
                    this._grid.getCells()[this._x-1][this._y] = this;
                    this._grid.getCells()[this._x][this._y] = null;
                    this._x--;
                }
                break;
            case BOTTOM:
                ret = this._grid.isTravellable(_x+1, _y);
                if(ret)
                {
                    this._grid.getCells()[this._x+1][this._y] = this;
                    this._grid.getCells()[this._x][this._y] = null;
                    this._x++;
                }
                break;
        }
        return ret;
    }
}
