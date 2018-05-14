/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

import gameofrobots.navigation.CellPosition;

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
        Field = new GameField();
        bigRobot = new BigRobot();
        smallRobot = new SmallRobot();
    }
    public void start(){
        Field = this.loadField();
    }
    public GameField field(){
        return this.Field;
    }

    private GameField loadField() {
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
    
   
}
