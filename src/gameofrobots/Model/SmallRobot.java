/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

import gameofrobots.Model.events.RobotActionEvent;
import gameofrobots.Model.events.RobotActionListener;
import gameofrobots.navigation.Direction;
import gameofrobots.navigation.MiddlePosition;
import java.util.ArrayList;

/**
 *
 * @author 1
 */
public class SmallRobot extends Robot {
    
    
    @Override
    public void makeMove(Direction direction){
        //Если нет стен и болота либо стоит понтон на болоте и робот может сделать ход
       if(this.getStun() == 0 && (!this.Field().isWall(new MiddlePosition(this.position(),direction))) 
               && ((!this.Field().isBog(this.position().next(direction))))
               ||(this.Field().isPontoon(this.position().next(direction)))){
           this.setPosition(this.position().next(direction));
                     
           if(this.Field().isTrap(this.position())){
            this.setStun(1);
           }
       }
       else if(this.getStun() > 0)
           this.reduceStun();
       this.RobotMovePerformed();
       
    }
    //---------------------Слушатель--------------------------------------------
    private ArrayList<RobotActionListener> Listeners = new ArrayList<RobotActionListener>();
    public void addRobotActionListener(RobotActionListener ActionListener){
        Listeners.add(ActionListener);
    }
    //---------------------Событие----------------------------------------------
    
    protected void RobotMovePerformed(){
    RobotActionEvent rEvent = new RobotActionEvent(this);

    for(RobotActionListener rListener : Listeners)
    rListener.robotMakedMove(rEvent);
    }
    
    
}
