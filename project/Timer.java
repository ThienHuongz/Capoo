package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;

public class Timer{
	
	GamePanel gp;
	
	private int countdownTime = 120*60;
	private int miliseconds;
	private int seconds;
	private int minutes;
	
	

	public Timer(GamePanel gp)
	{
		this.gp = gp;
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
	
	public void draw(Graphics2D g2)
	{
		Font font1 = new Font("Arial", Font.PLAIN, 50);
		miliseconds = countdownTime%60;
		seconds = countdownTime/120;
		minutes = countdownTime/3600;
		g2.setFont(font1);
		g2.setColor(Color.white);
		
		String label = String.format("%02d:%02d:%02d", minutes,seconds, miliseconds);
		
		int labelWidth = g2.getFontMetrics().stringWidth(label);
		int x = (gp.getWidth() - labelWidth)/2;
		int y = 50;
		
		if(countdownTime == 0 || countdownTime > 0)
		{
			g2.drawString(label,x,y);
		}
	}
	
//	private void stopTimer()
//	{
//		System.exit(0);
//	}

}
