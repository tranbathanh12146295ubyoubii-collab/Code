package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Clock implements IClock {
	private int hour, minute, second;
	private int year, month, day;
	
	List<Observer> observers = new ArrayList<>();
	
	public Clock() {
		this.hour = 0;
		this.minute = 0;
		this.second = 0;
		this.year = 0;
		this.month = 0;
		this.day = 0;
		execute();
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	public void execute() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Clock.this.day = LocalDateTime.now().getDayOfMonth();
				Clock.this.month = LocalDateTime.now().getMonthValue();
				Clock.this.year = LocalDateTime.now().getYear();
				Clock.this.hour = LocalDateTime.now().getHour();
				Clock.this.minute = LocalDateTime.now().getMinute();
				Clock.this.second = LocalDateTime.now().getSecond();
				
				Clock.this.notifyObserver();
			}
		}, 0, 1000);
	}
	
	public void notifyObserver() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
	
	
}
