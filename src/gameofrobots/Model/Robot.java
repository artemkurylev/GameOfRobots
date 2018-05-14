/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

import gameofrobots.navigation.Direction;
import gameofrobots.navigation.CellPosition;
import gameofrobots.navigation.MiddlePosition;
/**
 *
 * @author 1
 */
public class Robot {
    private CellPosition cellPosition;
    private GameField Field;
    private int stun; 
    
    
    public CellPosition position(){
        return this.cellPosition.clone();
    };
    public boolean setPosition(CellPosition Cell){
        if(Cell.isValid()){
            this.cellPosition = Cell;
            return true;
        }
        return false;
    }
    
    public int getStun(){
        return stun;
    }
    public void setStun(int stn){
        stn = stun;
    }
    public void reduceStun(){
        stun--;
    }
    
    public GameField Field(){
        return this.Field;
    }
    public void makeMove(Direction direction){
        if(!(Field.isWall(new MiddlePosition(this.position(),direction)))){
            this.setPosition(this.position().next(direction));
        }
    }
    
    public void setPontoon(Direction direction){
        if(this.Field.isBog(this.position()) && this.Field.isWall(new MiddlePosition(this.position(),direction))){
            this.Field.setPontoon(this.position().next(direction));
        }
    }
    
    
}
