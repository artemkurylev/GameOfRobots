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
public class GameField {
    // ------------------------------ Размеры ---------------------------
    private int width;
    private int height;

    GameField (){
        setSize(10, 10);
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
}
