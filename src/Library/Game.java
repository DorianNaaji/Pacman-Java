/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import Library.Grid;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dorian
 */
public class Game extends Observable implements Runnable
{
    // 25 et 25 default
    private static int _lines = 24;
    private static int _columns = 24;
    private Grid _grid;
    private Direction _dir = Direction.RIGHT;

    
    public Game()
    {
        super();
        this._grid = new Grid(this._lines, this._columns);
    }
    
    @Override
    public synchronized void run()
    {
        while(true)
        {
            try
            {
                this.setChanged();
                this.notifyObservers();
                // sleeps for 300ms
                // pretty okay amount of time so the player can react easily : not too fast, not too slow.
                Thread.sleep(300);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Grid getState()
    {
        return this._grid;
    }
    
    public Direction getDirection()
    {
        return this._dir;
    }
    
    public void setDirection(Direction dir)
    {
        this._dir = dir;
    }
    
}
