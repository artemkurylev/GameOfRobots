/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.view;
/*import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;*/
import gameofrobots.Model.GameModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author 1
 */
public class GameView extends JPanel{
    
// ------------------------------ Модель игры ------------------------------
    private GameModel _model;
    
    
    
    // ------------------------------ Размеры ---------------------------------
    
    private static final int CELL_SIZE = 30;
    private static final int GAP = 2;
    private static final int FONT_HEIGHT = 15;

    // ------------------------- Цветовое оформление ---------------------------
    
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color GRID_COLOR = Color.BLACK;

    
    
    public GameView(GameModel model){
        _model = model;
        // Инициализация графики
        int width = 2*GAP + CELL_SIZE * _model.field().width();
        int height = 2*GAP + CELL_SIZE * _model.field().height();
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.CYAN);
    }
    
    /** Рисуем поле */
    @Override
    public void paintComponent(Graphics g) {
        
        // Отрисовка фона
        int width  = getWidth();
        int height = getHeight();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);   // восстнанваливаем цвет пера
              
        // Отрисовка сетки
        drawGrid(g);
    }
    private void drawGrid(Graphics g) {
        int width  = getWidth();
        int height = getHeight();

        g.setColor(GRID_COLOR);
        
        for(int i = 1; i <= _model.field().width()+1; i++)  // вертикальные линии
        {
            int x = GAP + CELL_SIZE*(i-1);
            g.drawLine(x, 0, x, height);
        }

        for(int i = 1; i <= _model.field().width()+1; i++)  // горизотнальные линии
        {
            int y = GAP + CELL_SIZE*(i-1);
            g.drawLine(0, y, width, y);
        }

    }
    
}
