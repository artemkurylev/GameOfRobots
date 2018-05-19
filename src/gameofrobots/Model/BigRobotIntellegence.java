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
    public BigRobotIntellegence(int Xdistance,int Ydistance,BigRobot R){
        criticalXDistance = Xdistance;
        criticalYDistance = Ydistance;
        action = new BigRobotAction();
        robot = R;
    }
    
    BigRobotAction action;
    /**
     *
     * @param Xdistance
     * @param Ydistance
     * @return
     */
    public BigRobotAction chooseBigRobotAction(int Xdistance,int Ydistance){
        
        if(Xdistance <= criticalXDistance && Ydistance <= criticalYDistance)
            action = smartRobotAction();
        else
            action = stupidRobotAction();
        return action;
    }
    
    private BigRobotAction smartRobotAction(){
        Direction direction;
        CellPosition Pos = this.robot.position();
        int columnSmall = this.robot.Field().smallRobotPosition().column();
        int columnBig = Pos.column();
        int rowSmall = this.robot.Field().smallRobotPosition().row();
        int rowBig = Pos.row();
        GameField Field = this.robot.Field();
        
        if(columnSmall > columnBig){ 
            if(!Field.isWall(new MiddlePosition(Pos,Direction.east()))){
                    if (!Field.isBog(Pos.next(Direction.east()))||Field.isPontoon(Pos.next(Direction.east()))){
                        action.action = ActionType.move;
                        action.direction = Direction.east();
                    }
                    else{
                        action.action = ActionType.pontoon;
                        action.direction = Direction.east();
                    }
            }
        }
        else if (columnSmall < columnBig){
            if(!Field.isWall(new MiddlePosition(Pos,Direction.west()))){
                    if (!Field.isBog(Pos.next(Direction.west()))||Field.isPontoon(Pos.next(Direction.west()))){
                        action.action = ActionType.move;
                        action.direction = Direction.west();
                    }
                    else{
                        action.action = ActionType.pontoon;
                        action.direction = Direction.west();
                    }
            }
        }
        else if (rowSmall < rowBig){
            if(!Field.isWall(new MiddlePosition(Pos,Direction.north()))){
                    if (!Field.isBog(Pos.next(Direction.north()))||Field.isPontoon(Pos.next(Direction.north()))){
                        action.action = ActionType.move;
                        action.direction = Direction.north();
                    }
                    else{
                        action.action = ActionType.pontoon;
                        action.direction = Direction.north();
                    }
            }
        }
        else {
            if(!Field.isWall(new MiddlePosition(Pos,Direction.south()))){
                    if (!Field.isBog(Pos.next(Direction.north()))||Field.isPontoon(Pos.next(Direction.south()))){
                        action.action = ActionType.move;
                        action.direction = Direction.south();
                    }
                    else{
                        action.action = ActionType.pontoon;
                        action.direction = Direction.south();
                    }
            }   
        }
        
        return action;
    }

    private BigRobotAction stupidRobotAction() {
        int randInt = (int)(Math.random()*5);
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
            default:
            {
                action.action = ActionType.move;
                action.direction = defineStupidMoveDirection();
            }
        }
        
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
        
        if(columnSmall < columnBig && (!Field.isWall(new MiddlePosition(Pos,Direction.west()))) 
                && Pos.hasNext(Direction.west())){
            direction = Direction.west();
        }
        
        else if(columnSmall > columnBig && (!Field.isWall(new MiddlePosition(Pos,Direction.east())))
                && Pos.hasNext(Direction.east())){
            direction = Direction.east();
        }
            
        else if(rowSmall < rowBig && (!Field.isWall(new MiddlePosition(Pos,Direction.north())))
                && Pos.hasNext(Direction.north())){
            direction = Direction.north();
        }
        
        else if(rowSmall > rowBig && (!Field.isWall(new MiddlePosition(Pos,Direction.south())))
                && Pos.hasNext(Direction.south())){
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
    
