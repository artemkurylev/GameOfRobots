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
import gameofrobots.Model.BigRobot;
import gameofrobots.Model.GameModel;
import gameofrobots.Model.SmallRobot;
import gameofrobots.Model.events.RobotActionEvent;
import gameofrobots.Model.events.RobotActionListener;
import gameofrobots.navigation.CellPosition;
import gameofrobots.navigation.Direction;
import gameofrobots.navigation.MiddlePosition;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
//import java.awt.Color;
//import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author 1
 */
public class GameView extends JPanel implements KeyListener{
    
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
        
        // Отрисовка робота
        Point lefTop = leftTopCell(_model.bigRobot().position());
        drawBigRobot(g, _model.bigRobot(), lefTop);
        // Отрисовка робота
        lefTop = leftTopCell(_model.smallRobot().position());
        drawSmallRobot(g, _model.smallRobot(), lefTop);
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
    private void drawBigRobot(Graphics g, BigRobot robot, Point lefTop) {
        g.setColor(Color.RED);   

        String str = "РБ";
        g.drawString(str, lefTop.x+CELL_SIZE/8, lefTop.y+CELL_SIZE/4+FONT_HEIGHT);

        g.setColor(Color.BLACK);   // восстанваливаем цвет пера
    }
    private void drawSmallRobot(Graphics g, SmallRobot robot, Point lefTop) {
        g.setColor(Color.GREEN);   

        String str = "РУ";
        g.drawString(str, lefTop.x+CELL_SIZE/8, lefTop.y+CELL_SIZE/4+FONT_HEIGHT);

        g.setColor(Color.BLACK);   // восстанваливаем цвет пера
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent ke) {
            
        if(ke.isControlDown())
        {
            if(ke.getKeyCode() == KeyEvent.VK_UP) {         // откр/закр дверь сверху
                _model.smallRobot().setPontoon(Direction.north());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {  // откр/закр дверь снизу
                _model.smallRobot().setPontoon(Direction.south());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {  // откр/закр дверь слева
                _model.smallRobot().setPontoon(Direction.west());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_RIGHT) { // откр/закр дверь справа
                _model.smallRobot().setPontoon(Direction.east());
            }
        }
        else
        {
            if(ke.getKeyCode() == KeyEvent.VK_UP) {         // перемещаемся вверх
                _model.smallRobot().makeMove(Direction.north());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {  // перемещаемся вниз
                _model.smallRobot().makeMove(Direction.south());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {  // перемещаемся влево
                _model.smallRobot().makeMove(Direction.west());
            }
            else if(ke.getKeyCode() == KeyEvent.VK_RIGHT) { // перемещаемся вправо
                _model.smallRobot().makeMove(Direction.east());
            }
        }
    }
     private Point leftTopCell(CellPosition pos) {
        
        int left = GAP + CELL_SIZE * (pos.column()-1);
        int top = GAP + CELL_SIZE * (pos.row()-1);
        
        return new Point(left, top);
    }

    private Point leftTopCell(MiddlePosition mpos) {
        
        Point p = leftTopCell(mpos.cellPosition());
        
        if(mpos.direction().equals(Direction.south()))
        {
            p.y += CELL_SIZE;
            //p.x += CELL_SIZE;
        }
        else if(mpos.direction().equals(Direction.east()))
        {
            p.x += CELL_SIZE;
        }
        
        return p;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
        
    private class RepaintByAction implements RobotActionListener{

        @Override
        public void robotMakedMove(RobotActionEvent e) {
            repaint();
        }
    }
}
