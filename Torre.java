/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 
/**
 *
 * @author Fernando José Schmatz, Gustavo Steinhoefel
 */
public class Torre
{
    public int id;
    public double x;
    public double y;

    public Torre (int id, double x, double y){
        this.id = id;
        this.x = x;
        this.y = y;
    }
    
    public String toString(){
        return "[ Torre = "+ this.id + 
                "  <--> Posição Eixo X = "+this.x+" Eixo Y = "+this.y+" ]";
    }

    public int getId()
    {
        return id;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

}
