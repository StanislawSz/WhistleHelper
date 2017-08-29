/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouthpiecemaker;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas
 */
public class WorkingClass
{
    private Point hornBpPos;
    private Point whistleBpPos;
    private Point mainBpPos;
    private Point destBpPos;
    private Color emptySqm;
    private Robot robot;
    private Random random;
    private int nextVertical = 60;
    private int nextHorizontal = 37;
    
    public WorkingClass()
    {
        mainBpPos = new Point(1773, 451);
        whistleBpPos = new Point(1773 + nextHorizontal, 451 + nextVertical);
        hornBpPos = new Point(1773 + nextHorizontal, 451 + (2*nextVertical));
        destBpPos = new Point(1880, 625);
        emptySqm = new Color(45, 46, 46);
        random = new Random();
        
        try
        {
            robot = new Robot();
        } catch (AWTException ex)
        {
            Logger.getLogger(WorkingClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    protected void rollToEnd()
    {
        try
        {
            Thread.sleep(600);
            robot.mouseMove(destBpPos.x, destBpPos.y);
            for (int i=0; i< 10; i++)
            {
                Thread.sleep(25 + random.nextInt(10));
                robot.mouseWheel(1);
            }
        } catch (InterruptedException ex)
        {
            Logger.getLogger(WorkingClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected boolean checkDest()
    {
        Color sqmColor = robot.getPixelColor(destBpPos.x, destBpPos.y);
        
        return sqmColor.equals(emptySqm);
        
    }
    
    protected boolean checkWhistle()
    {
        Color sqmColor = robot.getPixelColor(whistleBpPos.x, whistleBpPos.y);
        
        return sqmColor.equals(emptySqm);
        
    }
    
    protected boolean checkHorn()
    {
        Color sqmColor = robot.getPixelColor(hornBpPos.x, hornBpPos.y);
        
        return sqmColor.equals(emptySqm);
        
    }
    
    protected boolean checkMain()
    {
        Color sqmColor = robot.getPixelColor(mainBpPos.x, mainBpPos.y);
        
        return sqmColor.equals(emptySqm);
        
    }
    
    
    
    protected void openNewDest() throws InterruptedException
    {
        robot.mouseMove(destBpPos.x + random.nextInt(8)-4, destBpPos.y + random.nextInt(8)-4);
        robot.mouseWheel(3);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON3_MASK);
        Thread.sleep(10+random.nextInt(20));
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        rollToEnd();
        Thread.sleep(300);
    }
    
    protected void openNewHorn() throws InterruptedException
    {
        robot.mouseMove(hornBpPos.x - nextHorizontal + random.nextInt(8)-4, hornBpPos.y + random.nextInt(8)-4);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON3_MASK);
        Thread.sleep(10+random.nextInt(20));
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        Thread.sleep(300);
    }
    
    protected void openNewWhistle() throws InterruptedException
    {
        robot.mouseMove(whistleBpPos.x - nextHorizontal + random.nextInt(8)-4, whistleBpPos.y + random.nextInt(8)-4);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON3_MASK);
        Thread.sleep(10+random.nextInt(20));
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        Thread.sleep(300);
    }
    
    protected void createItem() throws InterruptedException
    {
        robot.mouseMove(hornBpPos.x + random.nextInt(8)-4, hornBpPos.y + random.nextInt(8)-4);
        Thread.sleep(10+random.nextInt(20));
        robot.mousePress(InputEvent.BUTTON3_MASK);
        Thread.sleep(10+random.nextInt(20));
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        Thread.sleep(10+random.nextInt(20));
        robot.mouseMove(whistleBpPos.x + random.nextInt(8)-4, whistleBpPos.y + random.nextInt(8)-4);
        Thread.sleep(10+random.nextInt(20));
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(10+random.nextInt(20));
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        
    }
    
    protected void moveToDest() throws InterruptedException
    {
        robot.mouseMove(mainBpPos.x + random.nextInt(8)-4, mainBpPos.y + random.nextInt(8)-4);
        Thread.sleep(10+random.nextInt(20));
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(10+random.nextInt(20));
        robot.mouseMove(destBpPos.x + random.nextInt(8)-4, destBpPos.y + random.nextInt(8)-4);
        Thread.sleep(10+random.nextInt(20));
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
