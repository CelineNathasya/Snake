/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import snake.graphics.entities.Apple;
import snake.graphics.entities.BodyPart;

/**
 *
 * @author MSI
 */
public class Screen extends JPanel implements Runnable{
    
    public static final int WIDTH = 800, HEIGHT = 800;
    private Thread thread;
    private boolean running = false;
    
    private BodyPart b;
    private ArrayList<BodyPart> snake;
    
    private Apple apple;
    private ArrayList<Apple> apples;
    
    private Random rand;
    
    private int xCoor = 10, yCoor =10;
    private int size = 5;
            
    //biar directionnya ke kanan dulu
    private boolean right = true, left = false, up = false, down = false;
    private int ticks = 0;
    
    private Key key;
    
    public Screen() {
        setFocusable(true);
        key = new Key();
        addKeyListener(key);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        rand = new Random();
        
        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();
        
        start();
    }
    
    public void tick(){
//        System.out.println("Running...");
        if (snake.size() == 0){
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
        }
        
        if (apples.size() == 0){
            //tilesnya ada 80, yg terakhir jadi 79
            int xCoor = rand.nextInt(79);
            int yCoor = rand.nextInt(79);
            
            apple = new Apple(xCoor, yCoor, 10);
            apples.add(apple);           
        }
        
        //loop supaya bisa muncul terus apelnya
        for(int i = 0; i<apples.size(); i++){
            if(xCoor == apples.get(i).getxCoor()&& yCoor == apples.get(i).getyCoor()){
                size++;
                //biar apelnya ilang
                apples.remove(i);
                i--;
            }
        }
        
        for(int i = 0; i<snake.size(); i++){
            if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()){
                if(i != snake.size() - 1){
                    stop();
                }
            }
        }
        
        if (xCoor < 0 || xCoor > 79 || yCoor < 0 || yCoor > 79){
            stop();
        }
        
        
        // biar jalan
        ticks++;
        
        //set kecepatan waktu nya
        if(ticks>500000){
            if(right){
                xCoor++;
            }
            if(left){
                xCoor--;
            }
            if(up){
                yCoor--;
            }
            if(down){
                yCoor++;
            }
            ticks = 0;
            
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
            
            //delete array plg belakangnya
            if(snake.size()>size){
                snake.remove(0);
            }
        }
        
        
    }
    
    public void paint(Graphics g){
        //clear screen so it looked like moving
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //bikin warna line nya jadi hitam
//        g.setColor(Color.BLACK);
        //bikin garis horizontal
//        for(int i = 0; i<WIDTH/10; i++){
//            g.drawLine(i*10, 0, i*10, HEIGHT);
//        }
        //bikin garis vertikal
//        for(int i = 0; i<HEIGHT/10; i++){
//            g.drawLine(0, i*10, WIDTH, i*10);
//        }
        
        for(int i = 0; i < snake.size();i++){
           snake.get(i).draw(g);
        }
        
        for(int i = 0; i<apples.size(); i++){
            apples.get(i).draw(g);
        }
    }
    
    public void start(){
        running = true;
        thread = new Thread(this,"Game Loop");
        thread.start();
    }
    
    public void stop(){
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        while(running){
            //biar tau lagi jalan
            tick();
            repaint(); 
        }
    }
    
    private class Key implements KeyListener{

        @Override
        public void keyPressed(KeyEvent ke) {
            int key = ke.getKeyCode();
            
            // !left biar snake ga bisa kembali ke body nya
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                left = false;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_LEFT && !right){
                right = false;
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && !down){
                right = false;
                left = false;
                up = true;
                down = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                left = false;
                up = false;
                down = true;
            }
            
        }

        @Override
        public void keyReleased(KeyEvent ke) {
       
        }

        @Override
        public void keyTyped(KeyEvent ke) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        
    }
}
 