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
    private Direction _triedDir = null;
    private boolean _exit = false;

    
    public Game()
    {
        super();
        this._grid = new Grid(this._lines, this._columns);
    }
    
    @Override
    public synchronized void run()
    {
        while(!this._exit)
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
    
    public void stop()
    {
        this._exit = true;
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
    
    public Direction getLastTriedDirection()
    {
        return this._triedDir;
    }
    
    public void setLastTriedDirection(Direction dir)
    {
        this._triedDir = dir;
    }
    
}
