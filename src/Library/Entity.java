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
public class Entity
{
    
    private String _entityDescription = " ";
    private int _x;
    private int _y;
    private Grid _grid;
    
    public Entity(String entityDescr, int x, int y, Grid g)
    {
        this._entityDescription = entityDescr;
        this._x = x;
        this._y = y;
        this._grid = g;
    }
    
    @Override
    public String toString()
    {
        return this._entityDescription;
    }
    
    public String getName()
    {
        return this._entityDescription;
    }
    
    public void setContent(String content)
    {
        this._entityDescription = content;
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
                    this._grid.getCells()[this._x][this._y] = new Entity(" ", this._x, this._y, this._grid);
                    this._y++;
                }
                break;
            case LEFT:
                ret = this._grid.isTravellable(_x, _y-1);
                if(ret)
                {
                    System.out.println(this._y);
                    this._grid.getCells()[this._x][this._y-1] = this;
                    this._grid.getCells()[this._x][this._y]  = new Entity(" ", this._x, this._y, this._grid);
                    this._y--;
                }
                break;
            case TOP:
                ret = this._grid.isTravellable(_x-1, _y);
                if(ret)
                {
                    this._grid.getCells()[this._x-1][this._y] = this;
                    this._grid.getCells()[this._x][this._y] = new Entity(" ", this._x, this._y, this._grid);
                    this._x--;
                }
                break;
            case BOTTOM:
                ret = this._grid.isTravellable(_x+1, _y);
                if(ret)
                {
                    this._grid.getCells()[this._x+1][this._y] = this;
                    this._grid.getCells()[this._x][this._y] = new Entity(" ", this._x, this._y, this._grid);
                    this._x++;
                }
                break;
        }
        return ret;
    }
}
