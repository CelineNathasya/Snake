/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.graphics.entities;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author MSI
 */
public class BodyPart {
    
    private int xCoor, yCoor, width, height;
    
    public BodyPart(int xCoor, int yCoor, int tileSize){
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }
    
     public int getxCoor(){
        return xCoor;
    }
    
    public void setxCoor(int xCoor){
        this.xCoor = xCoor;
    }
    
    public int getyCoor(){
        return yCoor;
    }
    
    public void setyCoor(int yCoor){
        this.yCoor = yCoor;
    }
    
    //bikin snake nya
    public void draw(Graphics g){
        //jadi fill kotaknya pakai warna hitam, lalu timpah pake warna lain
        //bikin border
//        g.setColor(Color.BLACK);
//        g.fillRect(xCoor*width, yCoor*height, width, height);
        //bikin fill
//        g.setColor(Color.GREEN);
//        g.fillRect(xCoor*width+2, yCoor*height+2, width-4, height-4);

        //more modern look
        g.setColor(Color.WHITE);
        g.fillOval(xCoor*width, yCoor*height, width, height);
        
    }

    // untuk .contains() nya
    // buat containnya tau apa yang dicompare karena yang dibuat adalah custom object
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        BodyPart bodyPart = (BodyPart) o;
        return (bodyPart.getxCoor() == this.xCoor && bodyPart.getyCoor() == this.yCoor);
    }
}
