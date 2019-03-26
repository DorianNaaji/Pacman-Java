/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import Library.Grid;
import MyExceptions.EntityNotFoundException;
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
    private static int _boostDuration = 0;
    private static boolean _boosted = false;
    
    /**
     * default constr.
     */
    public Game()
    {
        super();
        this._grid = new Grid(this._lines, this._columns);
    }
    
    /**
     * Runs the process
     */
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
                this.manageBoostInteraction();
                
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (EntityNotFoundException ex)
            {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void manageBoostInteraction() throws EntityNotFoundException
    {
        if(this._boosted)
        {
            this._boostDuration--;
            this.getState().changeGhostTexture(true);
            if(this._boostDuration == 0)
            {
                this._boosted = false;
                this.getState().changeGhostTexture(false);
            }
        }
    }
    
    /**
     * Boosts the pacman
     */
    public static void setPacmanBoosted()
    {
        Game._boosted = true;
        Game._boostDuration = 15;
    }
    
    /**
     * 
     * @return true if the pacman is boosted 
     */
    public static boolean isPacmanBoosted()
    {
        return Game._boostDuration > 0;
    }
    
    /**
     * Tells the process to stop
     */
    public void stop()
    {
        this._exit = true;
    }
    
    /**
     * 
     * @return the current state of the grid 
     */
    public Grid getState()
    {
        return this._grid;
    }
    
    /**
     * 
     * @return the direction of the pacman 
     */
    public Direction getDirection()
    {
        return this._dir;
    }
    
    /**
     * Sets the pacman direction
     * @param dir a direction
     */
    public void setDirection(Direction dir)
    {
        this._dir = dir;
    }
    
    /**
     * Returns the last tried direction
     * @return tried direction
     */
    public Direction getLastTriedDirection()
    {
        return this._triedDir;
    }
    
    /**
     * sets the last tried direction
     * @param dir last tried direction
     */
    public void setLastTriedDirection(Direction dir)
    {
        this._triedDir = dir;
    }
    
}
