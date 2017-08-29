/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouthpiecemaker;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stas
 */
public class StartedClass extends WorkingClass implements Runnable
{
    private Random random;
    private int tryCount = 0;
    private boolean emptyHorns = true;
    private boolean emptyWhistle = true;
    private boolean itemInMain = true;
    private boolean emptyDest = false;
    
    public StartedClass()
    {
        random = new Random();
        
    }
    
    @Override
    public void run()
    {
        startWorking();
    }
    
    private void startWorking()
    {
        rollToEnd();
        
        while (tryCount < 12)
        {
            emptyDest = checkDest();
            emptyHorns = checkHorn();
            emptyWhistle = checkWhistle();
            itemInMain = checkMain();
            
            if (emptyDest==true & emptyHorns==true & emptyWhistle==true & itemInMain==true)
            {
                makeStep();
                tryCount = 0;
            }
            else
            {
                if (emptyDest == false)
                {
                    tryCount++;
                    try
                    {
                        openNewDest();
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(StartedClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (emptyHorns == false)
                {
                    tryCount++;
                    try
                    {
                        openNewHorn();
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(StartedClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                if (emptyWhistle == false)
                {
                    tryCount++;
                    try
                    {
                        openNewWhistle();
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(StartedClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                if (itemInMain == false)
                {
                    tryCount++;
                    try
                    {
                        moveToDest();
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(StartedClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    private void makeStep()
    {
        try
        {
            createItem();
            Thread.sleep(100);
            moveToDest();
            Thread.sleep(random.nextInt(574) + 1152);

        } catch (InterruptedException ex)
        {
            Logger.getLogger(WorkingClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resetCounter()
    {
        tryCount = 0;
    }

}
