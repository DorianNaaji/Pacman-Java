/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetPacman;

import Library.Cell;
import Library.Grid;
import java.util.Observable;
import java.util.Observer;

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
        System.out.println("---------------------");
        Cell[][] gridCells = this._game.getState().getCells();
        for(int i = 0; i < gridCells.length; i++)
        {
            for(int j = 0; j < gridCells[i].length; j++)
            {
                System.out.print(" | ");
                System.out.print(gridCells[i][j]);
            }
            System.out.println();
        }
    }

}
