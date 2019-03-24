/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Library.Direction;
import Library.Entity;
import Library.EntityType;
import Library.Game;
import MyExceptions.EntitiesException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
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
        try
        {
            this._game.getState().getPacman().move(this._game.getDirection());
        } 
        catch (EntitiesException ex)
        {
            System.out.println(ex + "||| Error occured in Affichage.update()");
            Logger.getLogger(Affichage.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i < this._game.getState().getCells().length; i++)
        {
            for(int j = 0; j < this._game.getState().getCells()[i].length; j++)
            {
                ImageView img = new ImageView(this._game.getState().getCells()[i][j].getImage());
                Node imgAsNode = this.getNodeByRowColumnIndex(i, j, _pane);
                ImageView oldImage = (ImageView) imgAsNode;
                oldImage.setImage(this._game.getState().getCells()[i][j].getImage());
            }
        }

            //tests
//        for(int i = 0; i < this._game.getState().getEntities().size(); i++)
//        {
//            if(this._game.getState().getEntities().get(i).getType() == EntityType.PACMAN)
//            {
//                this._game.getState().getEntities().get(i).move(Direction.RIGHT);
//            }
//         //   System.out.println("pas trouvé");
//        }



    }
    
    /**
     * 
     * @param row
     * @param column
     * @param gridPane
     * @see https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column (Sedrick°
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
