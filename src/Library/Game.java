/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import MyExceptions.EntityNotFoundException;
import java.util.ArrayList;
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
    private static int _defaultSleepTime = 50;
    
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
                // sleeps for 100ms
                // pretty okay amount of time so the player can react easily : not too fast, not too slow.
                Thread.sleep(_defaultSleepTime);
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
            this.getState().ChangeGhostState(true);
            if(this._boostDuration == 0)
            {
                this._boosted = false;
                this.getState().ChangeGhostState(false);
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
    
    public void movePacman()
    {
        // for the gameplay to be more fluid, we stock the last tried direction of the player
        // and we make the pacman go to this tried direction as soon as it is possible.
        try
        {
            if (this.getLastTriedDirection() != null)
            {
                if (this.getState().getPacman().move(this.getLastTriedDirection()))
                {
                    this.setDirection(this.getLastTriedDirection());
                    this.setLastTriedDirection(null);
                } else
                {
                    this.getState().getPacman().move(this.getDirection());
                }
            } else
            {
                this.getState().getPacman().move(this.getDirection());
            }

        } 
        catch (EntityNotFoundException ex)
        {
            System.out.println(ex + "||| Error occured in Game.movePacman()");
           
        }
    }
    
    public void moveGhosts()
    {
        try
        {
            ArrayList<Entity> ghosts = this.getState().getGhosts();
            for(int i = 0; i < ghosts.size(); i++)
            {
                ghosts.get(i).moveAI();
            }
        } 
        catch (EntityNotFoundException ex)
        {
            System.out.println(ex);
        }
    }
    
}
