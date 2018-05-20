/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

import java.util.ArrayList;

/**
 *
 * @author 1
 */
public class FieldDescription {
    
    public pair size,targetPos,BigRobotPos,SmallRobotPos;
    public ArrayList<pair> WallsPos;
    public ArrayList <Integer> WallDirections;
    public ArrayList<pair> Bogs;
    
}

