/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

import gameofrobots.navigation.CellPosition;
import gameofrobots.navigation.MiddlePosition;
import java.util.ArrayList;

/**
 *
 * @author 1
 */
public class GameField {
    // ------------------------------ Размеры ---------------------------
    private int width;
    private int height;

    GameField (){
        setSize(10, 10);
        Walls = new ArrayList<>();
        Bogs = new ArrayList<>();
        Traps = new ArrayList<>();
        Pontoons = new ArrayList<>();
        
    }
    
    public final void setSize(int width,int height){
        this.width = width;
        this.height = height;
    }
    
    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }
    
    // ---------------------------- Стены ----------------------------
    ArrayList <Wall> Walls;
    public boolean isWall(MiddlePosition pos){
        boolean wall = false;
        for(Wall x:Walls){
            if(x.middlePosition().equals(pos) )
                wall = true;
        }
        return wall;
    }
    
    public boolean addWall(MiddlePosition pos, Wall obj){
        if(pos.cellPosition().isValid()){
            obj.setPosition(pos);
            Walls.add(obj);
            return true;
        }
        return false;
    }
    
    // ---------------------------- Болота ----------------------------
    ArrayList <Bog> Bogs;
    public boolean isBog(CellPosition position) {
        boolean bog = false;
        for(Bog x:Bogs){
            if(x.position().equals(position) )
                bog = true;
        }
        return bog;
    }
    public boolean addBog(CellPosition pos, Bog obj){
        if(pos.isValid()){
            obj.setPosition(pos);
            Bogs.add(obj);
            return true;
        }
        return false;
    }
    
    // ---------------------------- Понтоны ----------------------------
    ArrayList <Pontoon> Pontoons;
    public boolean setPontoon(CellPosition pos,Pontoon obj) {
        if(pos.isValid()){
            obj.setPosition(pos);
            Pontoons.add(obj);
            return true;
        }
        return false;
    }
    boolean isPontoon(CellPosition position) {
        boolean pontoon = false;
        for(Pontoon x:Pontoons){
            if(x.position().equals(position) )
                pontoon = true;
        }
        return pontoon;
    }
    
    // ---------------------------- Капканы ----------------------------
    ArrayList <Trap> Traps;
    public boolean setTrap(CellPosition pos,Trap obj) {
        if(pos.isValid()){
            obj.setPosition(pos);
            Traps.add(obj);
            return true;
        }
        return false;
    }
    boolean isTrap(CellPosition position) {
        boolean trap = false;
        for(Trap x:Traps){
            if(x.position().equals(position) )
                trap = true;
        }
        return trap;
    }
    private SmallRobot smallRobot;
    private BigRobot bigRobot;
    
    public boolean setRobot(Robot rb,CellPosition position){
        if(position.isValid()){
            if(rb instanceof SmallRobot){
                smallRobot = (SmallRobot)rb;
                smallRobot.setPosition(position);
                smallRobot.setField(this);
            }
            
            else if(rb instanceof BigRobot){
                bigRobot = (BigRobot)rb;
                bigRobot.setPosition(position);
                bigRobot.setField(this);
            }
            return true;
        }
        return false;
    }
    public CellPosition smallRobotPosition(){
        return smallRobot.position();
    }
    public CellPosition bigRobotPosition(){
        return bigRobot.position();
    }
    
}
