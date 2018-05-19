/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

import gameofrobots.Model.BigRobotAction.ActionType;
import gameofrobots.navigation.CellPosition;
import gameofrobots.navigation.Direction;
import gameofrobots.navigation.MiddlePosition;

/**
 *
 * @author 1
 */
public class BigRobotIntellegence {
    private final int criticalXDistance;
    private final int criticalYDistance;
    BigRobot robot;
    public BigRobotIntellegence(int Xdistance,int Ydistance){
        criticalXDistance = Xdistance;
        criticalYDistance = Ydistance;
        action = new BigRobotAction();
    }
    
    BigRobotAction action;
    /**
     *
     * @param Xdistance
     * @param Ydistance
     * @return
     */
    public BigRobotAction chooseBigRobotAction(int Xdistance,int Ydistance){
        
        if(Xdistance < criticalXDistance && Ydistance < criticalYDistance)
            action = smartRobotAction();
        else
            action = stupidRobotAction();
        return action;
    }
    
    private BigRobotAction smartRobotAction(){
        
        return action;
    }

    private BigRobotAction stupidRobotAction() {
        int randInt = (int)(Math.random()*3);
        switch(randInt){
            case 2:
            {
                action.action = ActionType.pontoon;
                action.direction = defineRandomDirection();
                break;
            }
            case 1:
            {
                action.action = ActionType.trap;
                action.direction = defineRandomDirection();
                break;
            }
            
        }
        action.action = ActionType.move;
        return action;
    }

    private Direction defineRandomDirection() {
        Direction direction;
        CellPosition Pos = this.robot.position();
        GameField Field = this.robot.Field();
        if(action.action == ActionType.pontoon){
            if(Pos.next(Direction.west()).isValid() &&
                    !Field.isWall(new MiddlePosition(Pos,Direction.west())))
                direction = Direction.west();
            
            else if(Pos.next(Direction.east()).isValid() &&
                    !Field.isWall(new MiddlePosition(Pos,Direction.east())))
                direction = Direction.east();
            
            else if(Pos.next(Direction.north()).isValid() &&
                    !Field.isWall(new MiddlePosition(Pos,Direction.north())))
                direction = Direction.north();
            
            else 
                direction = Direction.south();
        }
        else {
            if(Pos.next(Direction.west()).isValid() &&
                    !robot.Field().isBog(Pos.next(Direction.west()))
                    && !Field.isWall(new MiddlePosition(Pos,Direction.west())))
                
                direction = Direction.west();
            
            else if(Pos.next(Direction.east()).isValid() && 
                    !robot.Field().isBog(Pos.next(Direction.east())) &&
                    !Field.isWall(new MiddlePosition(Pos,Direction.east())))
                
                direction = Direction.east();
            
            else if(Pos.next(Direction.north()).isValid() && 
                    !robot.Field().isBog(Pos.next(Direction.north())) &&
                    !Field.isWall(new MiddlePosition(Pos,Direction.north())))
                
                direction = Direction.north();
            
            else
                direction = Direction.south();
                }
        
        
        
        return direction;
    }
    
    private Direction defineStupidMoveDirection(){
        Direction direction;
        CellPosition Pos = this.robot.position();
        int columnSmall = this.robot.Field().smallRobotPosition().column();
        int columnBig = Pos.column();
        int rowSmall = this.robot.Field().smallRobotPosition().row();
        int rowBig = Pos.row();
        GameField Field = this.robot.Field();
        
        if(columnSmall < columnBig && (!Field.isWall(new MiddlePosition(Pos,Direction.west())))){
            direction = Direction.west();
        }
        
        else if(columnSmall > columnBig && (!Field.isWall(new MiddlePosition(Pos,Direction.east())))){
            direction = Direction.east();
        }
            
        else if(rowSmall > rowBig && (!Field.isWall(new MiddlePosition(Pos,Direction.north())))){
            direction = Direction.north();
        }
        
        else if(rowSmall < rowBig && (!Field.isWall(new MiddlePosition(Pos,Direction.south())))){
            direction = Direction.south();
        }
        else{
            
            if(!Field.isWall(new MiddlePosition(Pos,Direction.west())))
                direction = Direction.west();
            
            else if(!Field.isWall(new MiddlePosition(Pos,Direction.east())))
                direction = Direction.east();
            
            else if(!Field.isWall(new MiddlePosition(Pos,Direction.north())))
                direction = Direction.north();
            
            else 
                direction = Direction.south();
        }
        return direction;
    }
}
    
