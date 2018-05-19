/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

import gameofrobots.navigation.Direction;

/**
 *
 * @author 1
 */
class BigRobotAction {
    enum ActionType{
        move,pontoon,trap;
    }
    ActionType action;
    Direction direction;
    
}
