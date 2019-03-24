/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileAndUserIO;

import Library.Direction;
import Library.Game;
import MyExceptions.EntitiesException;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Dorian
 */
public class UserIO
{
    public static void addEvents(Stage currentStage, Game g)
    {
        currentStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                //goes up
                if(event.getCode() == KeyCode.UP)
                {
                    g.setDirection(Direction.UP);

                }
                //goes down
                else if(event.getCode() == KeyCode.DOWN)
                {
                    g.setDirection(Direction.DOWN);
                }
                // goes right
                else if(event.getCode() == KeyCode.RIGHT)
                {
                    g.setDirection(Direction.RIGHT);
                }
                // goes left
                else if(event.getCode() == KeyCode.LEFT)
                {
                    g.setDirection(Direction.LEFT);
                }
            }
        });
    }
    
}
