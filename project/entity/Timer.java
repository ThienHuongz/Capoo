package project.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import project.game;

public class Timer {

	private BufferedImage timePanel[] = new BufferedImage[2];
	public int countdownTime = 60 * 60;
	private int seconds;
	private int minutes;
	public int plusSecond = 10 * 60;
	final int tileTimeSize = 10;

	public Timer() {
		init();
	}

	public void init() {
		try {
			timePanel[0] = ImageIO.read(getClass().getResourceAsStream("/assets/gameTimePanel2.png"));
			timePanel[1] = ImageIO.read(getClass().getResourceAsStream("/assets/framButton1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (countdownTime != 0) {
			countdownTime = countdownTime - 1;
		}

	}
	public int getCountDownTime(){
		return countdownTime;
	}
	public void setCountDownTime(int time){
		this.countdownTime=time;
	}
	public void draw(Graphics g2) {
		Font font1 = new Font("futura", Font.PLAIN, 50);
		seconds = countdownTime / 60;
		minutes = countdownTime / 3600;
		g2.setFont(font1);
		g2.setColor(Color.WHITE);

		String label;

		if (seconds > 60) {
			label = String.format("%02d:%02d", minutes, seconds - 60);
		} else if (seconds == 60) {
			label = String.format("%02d:%02d", minutes - 1, seconds - 1);
		} else {
			label = String.format("%02d:%02d", minutes, seconds);
		}

		int labelWidth = g2.getFontMetrics().stringWidth(label);
		int x = (game.getScreenWidth() - labelWidth) / 2;
		int y = 50;

		if (countdownTime == 0 || countdownTime > 0) {
			// g2.drawRect(x-20, 0, labelWidth*3/2-20, labelWidth/2);
			g2.drawImage(timePanel[0], x - 25, -30, null);
			g2.drawString(label, x-8, y);
		}
	}

	// private void stopTimer()
	// {
	// System.exit(0);
	// }

}
