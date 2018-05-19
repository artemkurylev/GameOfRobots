/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

import gameofrobots.Model.BigRobotAction.ActionType;
import gameofrobots.navigation.Direction;
import gameofrobots.navigation.MiddlePosition;
import static java.lang.Math.abs;
/**
 *
 * @author 1
 */
public class BigRobot extends Robot {
    
    public void setTrap(Direction direction){
        Trap trap = new Trap();
        this.Field().setTrap(this.position().next(direction), trap);
    }
    private BigRobotIntellegence Intellect;
    public void makeAction(){
        if(this.getStun() == 0){
            Intellect = new BigRobotIntellegence(1,1,this);
            BigRobotAction action = Intellect.chooseBigRobotAction(abs(this.position().column() - this.Field().smallRobotPosition().column()),abs(this.position().row()
                    - this.Field().smallRobotPosition().row()));
            if(action.action == ActionType.move){
                this.makeMove(action.direction);
                if(this.Field().isBog(this.position()) && !this.Field().isPontoon(this.position()))
                    this.setStun(3);
            }
            else if(action.action == ActionType.pontoon)
                this.setPontoon(action.direction);
            else
                this.setTrap(action.direction);
        }
        else this.reduceStun();
//        int action = (int)(Math.random() * 10);
//        switch(action){
//            case 0:
//            {
//                this.setTrap(Direction.east());
//                break;
//            }
//            case 1:
//            {
//                this.setPontoon(Direction.east());
//                break;
//            }
//            default:
//            {
//                this.makeMove(calculateDirection());
//            }
//        }
//        
    }
    
    private Direction calculateDirection(){
        int columnSmall = this.Field().smallRobotPosition().column();
        int columnBig = this.Field().bigRobotPosition().column();
        int rowSmall = this.Field().smallRobotPosition().row();
        int rowBig = this.Field().bigRobotPosition().row();
        Direction direction;
        if(abs(columnSmall - columnBig) <= 1 && abs(rowSmall - rowBig) <= 1){
            //"Умное" движение робота
            if(columnSmall > columnBig && (!this.Field().isWall(new MiddlePosition(this.position(),Direction.east())))
                    &&!this.Field().isBog(this.position().next(Direction.east())))
                direction = Direction.east();
            else if (columnSmall > columnBig && (!this.Field().isWall(new MiddlePosition(this.position(),Direction.east())))
                    &&!this.Field().isBog(this.position().next(Direction.east())))
                direction = Direction.west();
            else if (columnSmall > columnBig && (!this.Field().isWall(new MiddlePosition(this.position(),Direction.east())))
                    &&!this.Field().isBog(this.position().next(Direction.east())))
                direction = Direction.north();
            else
                direction = Direction.south();
        }
        
        else
        {
            //глупое движение робота
            if(columnSmall > columnBig && (!this.Field().isWall(new MiddlePosition(this.position(),Direction.east())))){
                direction = Direction.east();
            }
            else if(columnSmall < columnBig && (!this.Field().isWall(new MiddlePosition(this.position(),Direction.west())))){
                direction = Direction.west();
            }
            else if(rowSmall > rowBig && (!this.Field().isWall(new MiddlePosition(this.position(),Direction.south())))){
                direction = Direction.north();
            }
            else {
                direction = Direction.south();
            }
        }
        return direction;
    }
    
    private static Direction chooseCellForTrap(){
        Direction direction;
        int randDirect = (int)(Math.random() * 4);
                    switch(randDirect){
                        case 0:
                        {
                            direction = Direction.east();
                            break;
                        }
                        case 1:
                        {
                            direction = Direction.west();
                            break;
                        }
                        case 2:
                        {
                            direction = Direction.north();
                            break;
                        }
                        default:
                        {
                            direction = Direction.south();
                        }
                    }
        return direction;
    }
}
