/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetPacman;

/**
 *
 * @author Dorian
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       Game game = new Game();
       Console c = new Console(game);
       game.addObserver(c);
       new Thread(game).start();
    }
    
}
