/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

import gameofrobots.Model.events.RobotActionEvent;
import gameofrobots.Model.events.RobotActionListener;
import gameofrobots.navigation.Direction;
import gameofrobots.navigation.CellPosition;
import gameofrobots.navigation.MiddlePosition;
import java.util.ArrayList;
/**
 *
 * @author 1
 */
public class Robot {
    
    //поле на котором стоит робот
    private GameField Field;
    public GameField Field(){
        return this.Field;
    }
    public void setField(GameField F){
        Field = F;
    }
    
    //позиция робота
    private CellPosition cellPosition;
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
    
  
    //количество ходов, в течении которых робот не может двигаться.
    private int stun; 
    public int getStun(){
        return stun;
    }
    public void setStun(int stn){
        stun = stn;
    }
    public void reduceStun(){
        stun--;
    }
    
    
    public void makeMove(Direction direction){
        if(this.stun  == 0){
            if(!(Field.isWall(new MiddlePosition(this.position(),direction)))){
                this.setPosition(this.position().next(direction));
            }
        }
        else
            this.reduceStun();   
    }
    
    public void setPontoon(Direction direction){
        if(this.Field.isBog(this.position().next(direction)) && !this.Field.isWall(new MiddlePosition(this.position(),direction))){
            this.Field.setPontoon(this.position().next(direction),new Pontoon());
        }
    }
    
    
}
