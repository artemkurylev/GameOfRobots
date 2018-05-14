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
abstract public class StandableObject {
    
    private CellPosition position;
    
    public void SetPosition(CellPosition cellPosition){
        position = cellPosition;
    }
    
    public CellPosition position(){
        return position;
    }
    
}
