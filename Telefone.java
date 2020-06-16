/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 
/**
 *
 * @author Fernando José Schmatz, Gustavo Steinhoefel
 */
public class Telefone
{
    public String nome;
    public double x;
    public double y;

    public Telefone (String n, double x, double y){
        this.nome = n;
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "[ Nome = "+ this.nome + 
                "  <--> Posição Eixo X = "+this.x+" Eixo Y = "+this.y+" ]";
    }

    public String getNome()
    {
        return nome;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }  


    public static String retornaNome(String str) {
        String n = str.substring(0,6);        
        return n;
    }
    
    public static double retornaPosicaoX(String str) {
        String n = str.substring(8,14);
        return Double.parseDouble(n);
    }
    
    public static double retornaPosicaoY(String str) {
        String n = str.substring(16,22);
        return Double.parseDouble(n);
    }  
}
