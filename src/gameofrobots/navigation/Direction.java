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
public class Direction {
    // определяем направление как угол в градусах от 0 до 360
    private int _angle = 90;

    private Direction(int angle) {
        // Приводим заданный угол к допустимому диапазону 
        angle = angle%360;
        if(angle < 0)    angle += 360;
        
        this._angle = angle;
    }
    
    // ------------------ Возможные направления ---------------------
    
    public static Direction north()
    { return new Direction(90); }
    
    public static Direction south()
    { return new Direction(270); }

    public static Direction east()
    { return new Direction(0); }

    public static Direction west()
    { return new Direction(180); }
    
  
    // ------------------ Новые направления ---------------------
    
    @Override
    public Direction clone(){ 
        return new Direction(this._angle); 
    }
  
    public Direction clockwise() { 
        return new Direction(this._angle-90); 
    }
    
    public Direction anticlockwise() { 
        return new Direction(this._angle+90); 
    }
    
    public Direction opposite() { 
        return new Direction(this._angle+180); 
    }
    
    public Direction rightword()  { 
        return clockwise(); 
    }
    
    public Direction leftword()  { 
        return anticlockwise(); 
    }
    
    // ------------------ Сравнить направления ---------------------
    
    @Override
    public boolean equals(Object other) {

        if(other instanceof Direction) {
            // Типы совместимы, можно провести преобразование
            Direction otherDirect = (Direction)other;
            // Возвращаем результат сравнения углов
            return  _angle == otherDirect._angle;
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        return this._angle;
    }
    
    public boolean isOpposite(Direction other) {
        return this.opposite().equals(other);
    }
}
