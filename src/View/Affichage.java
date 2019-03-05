/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Library.Direction;
import Library.Entity;
import PacmanRessources.Game;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Dorian
 */
public class Affichage implements Observer
{
    
    private Game _game;
    private GridPane _pane;
    
    public Affichage(Game game, GridPane pane)
    {
        this._game = game;
        this._pane = pane;
    }

    @Override
    public void update(Observable o, Object arg)
    {
    
        ObservableList<Node> elements = this._pane.getChildren();
        for(int i = 0; i < elements.size(); i++)
        {
            System.out.println(elements.get(i));
        }
        System.out.println("Update");
    }
    
}
