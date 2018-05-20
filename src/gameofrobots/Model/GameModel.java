/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

import gameofrobots.Model.events.RobotActionEvent;
import gameofrobots.Model.events.RobotActionListener;
import static gameofrobots.fileRead.FileReader.readField;
import gameofrobots.navigation.CellPosition;
import gameofrobots.navigation.Direction;
import gameofrobots.navigation.MiddlePosition;
import java.io.FileNotFoundException;
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
    public void start() throws FileNotFoundException{
        Field = this.generateField();
    }
    public GameField field(){
        return this.Field;
    }

    private GameField generateField() throws FileNotFoundException {
        FieldDescription F = readField();
        Field = new GameField();
        Field.setSize(F.size.x, F.size.y);
        CellPosition.setHorizontalRange(1, F.size.x);
        CellPosition.setVerticalRange(1, F.size.y);
        Robot Hero = new SmallRobot();
        Hero.setPosition(new CellPosition(F.SmallRobotPos.x,F.SmallRobotPos.y));
        smallRobot = (SmallRobot)Hero;
        smallRobot.addRobotActionListener(new GameEnded());
        this.Field.setRobot(smallRobot, new CellPosition(F.SmallRobotPos.x,F.SmallRobotPos.y));
        BigRobot enemy = new BigRobot();
        CellPosition bigRobotPos = new CellPosition(F.BigRobotPos.x,F.BigRobotPos.y);
        enemy.setPosition(bigRobotPos);
        bigRobot = enemy;
        this.Field.setRobot(bigRobot, bigRobotPos);
        _targetPos = new CellPosition(F.targetPos.x,F.targetPos.y);
        
        
        for(int i = 0; i < F.WallsPos.size(); i ++){
            Wall W = new Wall();
            CellPosition WallPos = new CellPosition(F.WallsPos.get(i).x,F.WallsPos.get(i).x);
            Direction direction;
            switch(F.WallDirections.get(i)){
                case 1:
                {
                    direction = Direction.east();
                    break;
                }
                case 2:
                {
                    direction = Direction.west();
                    break;
                }
                case 3:
                {
                    direction = Direction.north();
                    break;
                }
                default:
                {
                    direction = Direction.south();
                }
            }
            this.Field.addWall(new MiddlePosition(WallPos,direction),W);
        }
        for(int i = 0; i < F.Bogs.size(); ++i){
            Bog B = new Bog();
            this.Field.addBog(new CellPosition(F.Bogs.get(i).x,F.Bogs.get(i).y), B);
        }
        return Field;
    }
    public CellPosition targetpos(){
        return _targetPos;
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
