package View;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Model.Clock;
import Model.Observer;

public class AnalogClock extends JPanel implements Observer {
	int h = 0, m = 0, s = 0;
	
	public AnalogClock() {
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);	
		
		g.setColor(Color.BLUE);
		g.drawOval(60, 15, 160, 160);
		
		g.setColor(Color.BLACK);
		g.drawLine(140, 100, calRadian(h, 140, 100, 50, 30).get(0), calRadian(h, 140, 100, 50, 30).get(1));
		g.setColor(Color.BLACK);
		g.drawLine(140, 100, calRadian(m, 140, 100, 70, 6).get(0), calRadian(m, 140, 100, 70, 6).get(1));
		
		g.setColor(Color.RED);
		g.drawLine(140, 100, calRadian(s, 140, 100, 70, 6).get(0), calRadian(s, 140, 100, 70, 6).get(1));
		
	}
	
	public List<Integer> calRadian(int time, int sx, int sy, int lenght, double div) {
		List<Integer> list = new ArrayList<>();
		double radian;
		radian = Math.toRadians(time * div);
		int x = (int) (sx + lenght * Math.sin(radian));
		int y = (int) (sy - lenght * Math.cos(radian));
		
		list.add(x);
		list.add(y);
		
		return list;
	}

	@Override
	public void update(Clock clock) {
		// TODO Auto-generated method stub
		h = clock.getHour();
		m = clock.getMinute();
		s = clock.getSecond();
		repaint();
	}
	
}
