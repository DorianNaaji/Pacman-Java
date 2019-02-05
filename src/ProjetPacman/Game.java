/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetPacman;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dorian
 */
public class Game extends Observable implements Runnable
{
    
    private static int _lines = 30;
    private static int _columns = 15;
    private boolean[][] _matrix;

    
    public Game()
    {
        super();
        this._matrix = new boolean[this._lines][this._columns];
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
                // sleeps for 100ms
                Thread.sleep(100);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    
    public boolean[][] getState()
    {
        return this._matrix;
    }
    
}
