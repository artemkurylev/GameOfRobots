/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

import gameofrobots.Model.events.RobotActionEvent;
import gameofrobots.Model.events.RobotActionListener;
import gameofrobots.navigation.CellPosition;
import gameofrobots.navigation.Direction;
import gameofrobots.navigation.MiddlePosition;
import java.util.Random;

/**
 *
 * @author 1
 */
public class GameModel {
    private GameField Field;
    private BigRobot bigRobot;
    private SmallRobot smallRobot;
    private CellPosition _targetPos;
    public GameModel(){
    }
    public void start(){
        Field = this.generateField();
    }
    public GameField field(){
        return this.Field;
    }

    private GameField generateField() {
        Field = new GameField();
        Random Rnd = new Random();
        int height = 10;
        int width = 10;
        CellPosition smallRobotPos = new CellPosition(1,1);
        Robot Hero = new SmallRobot();
        Hero.setPosition(smallRobotPos);
        smallRobot = (SmallRobot)Hero;
        smallRobot.addRobotActionListener(new GameEnded());
        this.Field.setRobot(smallRobot, smallRobotPos);
        BigRobot enemy = new BigRobot();
        CellPosition bigRobotPos = new CellPosition(3,3);
        enemy.setPosition(bigRobotPos);
        bigRobot = enemy;
        this.Field.setRobot(bigRobot, bigRobotPos);
        
        
        
        for(int i = 1; i <= height*4; i ++){
            int genr = Rnd.nextInt() + 1;
            CellPosition CellPos = new CellPosition(new Random().nextInt(height),new Random().nextInt(width));
            int direct = new Random().nextInt(4) + 1;
            Direction dir;
            switch(direct){
                case 1:{  
                    dir = Direction.north();
                    break;
                }
                case 2:{
                    dir = Direction.east();
                    break;
                }
                case 3:{
                    dir = Direction.west();
                    break;
                }
                default:{
                    dir = Direction.south();
                }
            }
            if(genr % 2 == 1){
                Field.addWall(new MiddlePosition(CellPos,dir),new Wall());
            }
            if(genr %3 == 0){
                Field.addBog(CellPos, new Bog());
            }
        } 
        return Field;
    }
    //---------------Слушатель для определения конца игры и хода ИИ------------
    private class GameEnded implements RobotActionListener{
        @Override
        public void robotMakedMove(RobotActionEvent e) {
            identifyGameOver();
            bigRobot.makeAction();
            identifyGameOver();
        }
    }
    private void identifyGameOver(){
        
        if(smallRobot.position().equals(_targetPos))
        {
            System.out.println("Ты выйграл, красавчие");
        }
        if(bigRobot.position().equals(smallRobot.position()))
        {
            System.out.println("Ха ха, ты проиграл :DDDDDD");
        }

    }

    public SmallRobot smallRobot() {
        return this.smallRobot;
    }
    public BigRobot bigRobot() {
        return this.bigRobot;
    }
    
   
}
