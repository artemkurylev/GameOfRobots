/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import gameofrobots.Model.GameModel;
import gameofrobots.view.GameView;
/**
 *
 * @author 1
 */
public class GameOfRobots extends JFrame{
    private GameModel _model;
    
    private GameView _gamePanel;
    
    //===================================================================== main
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameOfRobots();
            }
        });
    }
    
    //============================================================== constructor
    public GameOfRobots() {
        _model = new GameModel();
        //_model.start();
        _gamePanel = new GameView(_model);
        
//        //... Create button and check box.
       // JButton newGameBtn = new JButton("Новая игра");
       // newGameBtn.addActionListener(new ActionNewGame());
        
        //... Do layout
        //JPanel controlPanel = new JPanel(new FlowLayout());
        //controlPanel.add(newGameBtn);
        
        //... Create content pane with graphics area in center (so it expands)
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        //content.add(controlPanel, BorderLayout.NORTH);
        content.add(_gamePanel, BorderLayout.CENTER);
        
        //... Set this window's characteristics.
        setContentPane(content);
        setTitle("Битва роботов");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        _gamePanel.setFocusable(true);
        _gamePanel.setVisible(true);        
}
}
