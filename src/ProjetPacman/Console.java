/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetPacman;

import Library.Direction;
import Library.Entity;
import Library.Grid;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dorian
 */
public class Console implements Observer
{
    
    private Game _game;
    
    public Console(Game g)
    {
        this._game = g;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Entity[][] gridCells = this._game.getState().getCells();
        for(int i = 0; i < gridCells.length; i++)
        {
            for(int j = 0; j < gridCells[i].length; j++)
            {
                System.out.print(" | ");
                System.out.print(gridCells[i][j]);
            }
            System.out.println();
        }
        this._game.getState().getEntities().get(0).move(Direction.BOTTOM);
        // printing some lines to get a more cleaner and easier to analyze output
        System.out.println("\n\n\n\n\n\n\n\n");

    }
    

}
