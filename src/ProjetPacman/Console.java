/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetPacman;

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
        System.out.println("Hello ! I'm updated");
        boolean[][] tab = this._game.getState();
        for(int i = 0; i < tab.length; i++)
        {
            for(int j = 0; j < tab[i].length; j++)
            {
                System.out.println(tab[i][j]);
            }
        }
    }

}
