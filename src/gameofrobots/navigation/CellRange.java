/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.navigation;

/**
 *
 * @author 1
 */
public class CellRange {
    // ------------------ Возможные значения ------------------
    private  int _min = 0;
    private  int _max = 0;

    public CellRange(int min, int max){
        if(min < 0)     min = 0;
        if(max < min)   max = min;
        
        _min = min;
        _max = max;
    }
    
    public int min(){
        return _min;
    }

    public int max(){
        return _max;
    }

    public int length(){
        return _max - _min + 1;
    }
    
    public static boolean isValidRange(int min, int max) {
      return min > 0 && max >= min;  
    }

    // ------------------ Принадлежность диапазону ------------------
    
    public boolean contains(int val){
       return val >= _min && val <= _max;
    }
}
