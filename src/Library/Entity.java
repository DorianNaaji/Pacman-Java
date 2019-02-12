/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

/**
 *
 * @author Dorian
 */
public class Entity
{
    
    private String _entityDescription = " ";
    
    public Entity()
    {
        
    }
    
    public Entity(String str)
    {
        this._entityDescription = str;
    }
    
    @Override
    public String toString()
    {
        return this._entityDescription;
    }
}
