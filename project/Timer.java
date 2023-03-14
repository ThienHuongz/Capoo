package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Timer{
	
	private int countdownTime = 90*60;
	private int seconds;
	private int minutes;
	
	final int tileTimeSize = 10;
	

	public Timer()
	{
	}
	
	public void update()
	{
		if(countdownTime != 0)
		{
			countdownTime = countdownTime-1;
		}
		else
		{
			
		}
	}
	
	public void draw(Graphics g2)
	{
		Font font1 = new Font("Arial", Font.PLAIN, 50);
		seconds = countdownTime/60;
		minutes = countdownTime/3600;
		g2.setFont(font1);
		g2.setColor(Color.white);
		
		String label;
		
		if( seconds > 60)
		{
			label = String.format("%02d:%02d", minutes,seconds-60);
		}
		else if(seconds == 60)
		{
			label = String.format("%02d:%02d", minutes-1,seconds-1);
		}
		else
		{
			label = String.format("%02d:%02d", minutes,seconds);
		}
		
		int labelWidth = g2.getFontMetrics().stringWidth(label);
		int x = (game.getScreenWidth() - labelWidth)/2;
		int y = 50;
		
		if(countdownTime == 0 || countdownTime > 0)
		{
			g2.drawRect(x-20, 0, labelWidth*3/2-20, labelWidth/2);
			g2.drawString(label,x,y);
		}
	}
	
//	private void stopTimer()
//	{
//		System.exit(0);
//	}

}
