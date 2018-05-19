/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.Model;

/**
 *
 * @author 1
 */
public class BigRobotIntellegence {
    private int criticalDistance;
    
    public BigRobotIntellegence(int distance){
        criticalDistance = distance;
    }
    public Action chooseAction(int distance){
        Action action;
        if(distance > criticalDistance)
            action = stupidRobotAction();
        else
            action = smartRobotAction();
        return action;
    }
    private Action smartRobotAction(){
        Action action = new Action();
        return action;
    }

    private Action stupidRobotAction() {
        Action action = new Action();
        return action;
    }
    
    
}
    
