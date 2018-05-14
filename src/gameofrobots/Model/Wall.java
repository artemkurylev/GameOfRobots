/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

/**
 *
 * @author 1
 */
import gameofrobots.navigation.MiddlePosition;
public class Wall {
    
    private MiddlePosition middlePosition;
    
    public MiddlePosition middlePosition(){
        return middlePosition;
    }
    
    public void setPosition(MiddlePosition position){
        middlePosition = position;
    }
    
}
