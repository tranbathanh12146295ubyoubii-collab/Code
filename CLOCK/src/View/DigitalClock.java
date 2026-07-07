package View;

import Model.*;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DigitalClock extends JPanel implements Observer {
	List<JLabel> listLable = new ArrayList<>();
	JLabel hour, minute, second;
	Font font = new Font("Inter", Font.BOLD, 30);
	public DigitalClock() {
		hour = new JLabel("00:", JLabel.CENTER);
		minute = new JLabel("00:", JLabel.CENTER);
		second = new JLabel("00", JLabel.CENTER);
		
		listLable.add(hour);
		listLable.add(minute);
		listLable.add(second);
		
		for (JLabel l : listLable) {
			l.setFont(font);
			add(l);
		}
	}
	public List<JLabel> getListLable() {
		return listLable;
	}
	public void setListLable(List<JLabel> listLable) {
		this.listLable = listLable;
	}
	public JLabel getHour() {
		return hour;
	}
	public void setHour(JLabel hour) {
		this.hour = hour;
	}
	public JLabel getMinute() {
		return minute;
	}
	public void setMinute(JLabel minute) {
		this.minute = minute;
	}
	public JLabel getSecond() {
		return second;
	}
	public void setSecond(JLabel second) {
		this.second = second;
	}
	
	public void setH(int h) {
		hour.setText(h + ":");
	}
	
	public void setM(int m) {
		minute.setText(m + ":");
	}
	
	public void setS(int s) {
		second.setText(s + "");
	}
	
	@Override
	public void update(Clock clock) {
		// TODO Auto-generated method stub
		setH(clock.getHour());
		setM(clock.getMinute());
		setS(clock.getSecond());
	}
	
}
