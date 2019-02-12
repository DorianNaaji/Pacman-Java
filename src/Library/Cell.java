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
public class Cell
{
    private Entity _content;
    
    public Cell(Entity e)
    {
        this._content = e;
    }
    
    public Cell()
    {
        this._content = new Entity();
    }
    
    @Override
    public String toString()
    {
        return this._content.toString();
    }
    
    public Entity getContent()
    {
        return this._content;
    }
    
    public void setContent(Entity e)
    {
        this._content = e;
    }
    
}
