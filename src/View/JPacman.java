/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import FileAndUserIO.UserIO;
import Library.Game;
import Utilities.Consts;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
        primaryStage.getIcons().add(new Image(Consts.getPolytechImgPath()));
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
                ImageView img = new ImageView(this._game.getState().getCells()[i][j].getImgPath());

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

        // on ajoute les events
        UserIO.addEvents(primaryStage, this._game);
        
        // on affiche la scène
        primaryStage.show();
        
        
        // on lance le thread de la couche métier
        new Thread(game).start();
    }
    
    @Override
    public void stop()
    {
        this._game.stop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
