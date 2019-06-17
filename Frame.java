/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MSI
 */

package snake;
import java.awt.GridLayout;
import javax.swing.JFrame;
import snake.graphics.Screen;

public class Frame extends JFrame {
    
    public Frame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");
        setResizable(false);
        
        init();
    }
    
    public void init(){
        setLayout(new GridLayout(1, 1, 0, 0));
        Screen s = new Screen(); 
        add(s);
        // make the window fixed size
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        
    }
    
    public static void main(String[] args){
        new Frame(); 
    }
}
