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
import MyExceptions.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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

    /**
     * Checks if the game is lost or not
     * @return true if the game is lost
     */
    private boolean checkLose()
    {
        // IF THE GAME IS OVER
        if (this._game.getState().getLost())
        {
            // END OF THE GAME
            this._game.stop();
            Platform.runLater(() -> this._pane.getChildren().clear());
            Text label = new Text("Game over !");
            label.setStyle("-fx-font: 90 arial;");
            Platform.runLater(() -> this._pane.add(label, 0, 0));

            return true;
        }
        return false;
    }

    /**
     * Checks if the game is won or not yet
     * @return true if the game is won
     */
    private boolean checkWin()
    {
        if (this._game.getState().getWin_NoMoreGums())
        {
            // END OF THE GAME
            this._game.stop();
            Platform.runLater(() -> this._pane.getChildren().clear());
            Text label = new Text("You won !");
            label.setStyle("-fx-font: 90 arial;");
            Platform.runLater(() -> this._pane.add(label, 0, 0));

            return true;
        }
        return false;
    }
    /**
     * Updates the view
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg)
    {
        if(this.checkLose() || this.checkWin())
        {
            // we step out from the update function to cause no conflicts between the UI reloads if the game is either won or lost
            return;
        }
        this.moveGhosts();
        this.movePacman();
        this.updateImageViews();
    }
    
    private void moveGhosts()
    {
        try
        {
            ArrayList<Entity> ghosts = this._game.getState().getGhosts();
            for(int i = 0; i < ghosts.size(); i++)
            {
                ghosts.get(i).moveAI();
            }
        } 
        catch (EntityNotFoundException ex)
        {
            Logger.getLogger(Affichage.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    /**
     * Moves the pacman
     */
    private void movePacman()
    {
        // for the gameplay to be more fluid, we stock the last tried direction of the player
        // and we make the pacman go to this tried direction as soon as it is possible.
        try
        {
            if (this._game.getLastTriedDirection() != null)
            {
                if (this._game.getState().getPacman().move(this._game.getLastTriedDirection()))
                {
                    this._game.setDirection(this._game.getLastTriedDirection());
                    this._game.setLastTriedDirection(null);
                } else
                {
                    this._game.getState().getPacman().move(this._game.getDirection());
                }
            } else
            {
                this._game.getState().getPacman().move(this._game.getDirection());
            }

        } catch (EntityNotFoundException ex)
        {
            System.out.println(ex + "||| Error occured in Affichage.update()");
            Logger.getLogger(Affichage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Updates the imageView according to the controller (library grid)
     */
    private void updateImageViews()
    {
        for (int i = 0; i < this._game.getState().getCells().length; i++)
        {
            for (int j = 0; j < this._game.getState().getCells()[i].length; j++)
            {
                ImageView img = new ImageView(this._game.getState().getCells()[i][j].getImage());
                Node imgAsNode = this.getNodeByRowColumnIndex(i, j, _pane);
                ImageView oldImage = (ImageView) imgAsNode;
                oldImage.setImage(this._game.getState().getCells()[i][j].getImage());
            }
        }
    }

    /**
     *
     * @param row
     * @param column
     * @param gridPane
     * @see
     * https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column
     * (Sedrick°
     * @return
     */
    private Node getNodeByRowColumnIndex(int row, int column, GridPane gridPane)
    {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens)
        {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column)
            {
                result = node;
                break;
            }
        }
        return result;
    }

}
