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
import javafx.application.Application;
import javafx.scene.Scene;
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
    

    @Override
    public void start(Stage primaryStage)
    {

        // initialisation du modèle que l'on souhaite utiliser
        Game game = new Game();
        //texte
        Text affichage;
        // gestion du placement (permet de placer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        int column = 0;
        int row = 0;
        
        Affichage aff = new Affichage(game, gPane);
        game.addObserver(aff);
        
        // création de la grille du jeu, placement des cases
        for(int i = 0; i < game.getState().getCells().length; i++)
        {
            for(int j = 0; j < game.getState().getCells()[i].length; j++)
            {
                Text t = new Text(game.getState().getCells()[i][j].getName());
                t.setWrappingWidth(30);
                t.setFont(Font.font("Verdana", 20));
                t.setTextAlignment(TextAlignment.CENTER);

                gPane.add(t, column++, row);

                if (column >= game.getState().getCells()[i].length)
                {
                    column = 0;
                    row++;
                }
            }
        }
        
        // permet d'afficher les bords des lignes ou non.
        gPane.setGridLinesVisible(true);
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
