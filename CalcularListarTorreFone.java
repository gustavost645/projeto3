/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Fernando José Schmatz
 */
public class CalcularListarTorreFone
{ 
   public static double calcularDistManhattan(double x1, double x2, double y1,double y2){
       double result =  (double) (Math.abs(x1 - x2) + Math.abs(y1 - y2)); 
       return result;        
   } 
}
