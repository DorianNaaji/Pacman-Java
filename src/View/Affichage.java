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
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
            for(int j = 0; j < this._game.getState().getCells().length; j++)
            {
                for(int k = 0; k < this._game.getState().getCells()[j].length; k++)
                {
                    Text t = new Text(this._game.getState().getCells()[j][k].getName());
                    t.setWrappingWidth(30);
                    t.setFont(Font.font("Verdana", 20));
                    t.setTextAlignment(TextAlignment.LEFT);
                    
                    Node textAsNode = this.getNodeByRowColumnIndex(j, k, _pane);
                    Text oldText = (Text) textAsNode;
                    oldText.setText(this._game.getState().getCells()[j][k].getName());
                }
            }
        }
        
        this._game.getState().getEntities().get(0).move(Direction.LEFT);
    }
    
    /**
     * 
     * @param row
     * @param column
     * @param gridPane
     * @see https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column (Sedrick)
     * @return 
     */
    private Node getNodeByRowColumnIndex(int row,  int column, GridPane gridPane) 
    {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }
    
}
