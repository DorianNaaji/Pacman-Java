/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Library.Direction;
import Library.Entity;
import Library.Game;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author Dorian
 */
public class JPacman extends Application
{
    private Game _game;
    private GridPane _pane;
    

    @Override
    public void start(Stage primaryStage)
    {
        //empêche de resize la window
        primaryStage.resizableProperty().set(false);
        // initialisation du modèle que l'on souhaite utiliser
        Game game = new Game();
        this._game = game;
        BorderPane border = new BorderPane();

        // permet de placer les diffrents éléments dans une grille
        GridPane gPane = new GridPane();
        this._pane = gPane;
        Affichage aff = new Affichage(game, gPane);
        game.addObserver(aff);
        
        int column = 0;
        int row = 0;
        
        // création de la grille du jeu, placement des cases
        for(int i = 0; i < game.getState().getCells().length; i++)
        {
            for(int j = 0; j < game.getState().getCells()[i].length; j++)
            {
                ImageView img = new ImageView();
                img.setImage(this._game.getState().getCells()[i][j].getImage());

                gPane.add(img, column++, row);

                if (column >= game.getState().getCells()[i].length)
                {
                    column = 0;
                    row++;
                }
            }
        }
        
        // permet d'afficher les bords des lignes ou non.
        //gPane.setGridLinesVisible(true);
        border.setCenter(gPane);
        Scene scene = new Scene(border, Color.WHITESMOKE);
        primaryStage.setTitle("PACMAN FX");
        primaryStage.setScene(scene);
        // on affiche la scène
        primaryStage.show();
        
        // on lance le thread de la couche métier
        new Thread(game).start();

    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
