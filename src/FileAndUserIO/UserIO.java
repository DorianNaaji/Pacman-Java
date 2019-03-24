/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileAndUserIO;

import Library.Direction;
import Library.Game;
import MyExceptions.EntityNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    try
                    {
                        if(g.getState().getPacman().isTravellable(Direction.UP))
                        {
                            g.setDirection(Direction.UP);
                        }
                        else
                        {
                            g.setLastTriedDirection(Direction.UP);
                        }
                    } 
                    catch (EntityNotFoundException ex)
                    {
                        Logger.getLogger(UserIO.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                //goes down
                else if(event.getCode() == KeyCode.DOWN)
                {
                    try
                    {
                        if(g.getState().getPacman().isTravellable(Direction.DOWN))
                        {
                            g.setDirection(Direction.DOWN);
                        }
                        else
                        {
                            g.setLastTriedDirection(Direction.DOWN);
                        }
                    } 
                    catch (EntityNotFoundException ex)
                    {
                        Logger.getLogger(UserIO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // goes right
                else if(event.getCode() == KeyCode.RIGHT)
                {
                    try
                    {
                        if(g.getState().getPacman().isTravellable(Direction.RIGHT))
                        {
                            g.setDirection(Direction.RIGHT);
                        }
                        else
                        {
                            g.setLastTriedDirection(Direction.RIGHT);
                        }
                    } 
                    catch (EntityNotFoundException ex)
                    {
                        Logger.getLogger(UserIO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // goes left
                else if(event.getCode() == KeyCode.LEFT)
                {
                    try
                    {
                        if(g.getState().getPacman().isTravellable(Direction.LEFT))
                        {
                            g.setDirection(Direction.LEFT);
                        }
                        else
                        {
                            g.setLastTriedDirection(Direction.LEFT);
                        }
                    } 
                    catch (EntityNotFoundException ex)
                    {
                        Logger.getLogger(UserIO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
}
