package Packman_Game;

public class Time {
	/**
	 * This class represents time on TimeStamp format.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	private int year;
	private int mounth;
	private int day;
	private int hour;
	private int minute;
	private int second;

	public Time() {
		setYear(2000);
		setMounth(1);
		setDay(1);
		setHour(1);
		setMinute(1);
		setSecond(1);
	}
	public Time(Time ot) {
		setYear(ot.year);
		setMounth(ot.mounth);
		setDay(ot.day);
		setHour(ot.hour);
		setMinute(ot.minute);
		setSecond(ot.second);
	}
	public int getYear() {//getters and setters
		return year;
	}
	public void setYear(int year) {
		this.year+=year;
	}
	public int getMounth() {
		return mounth;
	}
	public void setMounth(int mounth) {
		mounth+=this.mounth;
		while(mounth>=12) {
			year++;
			mounth-=12;
		}	
		this.mounth=mounth;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		if(mounth==1||mounth==3||mounth==5||mounth==7||mounth==8||mounth==10||mounth==12) {
			day+=this.day;
			while(day>=31) {
				mounth++;
				day-=31;
			}
		}
		if(mounth==2) {
			day+=this.day;
			while(day>=28) {
				mounth++;
				day-=28;
			}
		}
		if(mounth==4||mounth==6||mounth==9||mounth==11) {
			day+=this.day;
			while(day>=30) {
				mounth++;
				day-=30;
			}
		}
		this.day=day;
	}

	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		hour+=this.hour;
		while(hour>=24) {
			day++;
			hour-=24;
		}
		this.hour=hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		minute+=this.minute;
		while(minute>=60) {
			hour++;
			minute-=60;
		}
		this.minute=minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		second+=this.second;
		while(second>=60) {
			minute++;
			second-=60;
		}
		this.second=second;
	}
	/**
	 * write the time as string on kml timestamp format.
	 * @return time.
	 */
	public String toString() {
		String s="";
		int dig=countdigits(year);
		if(dig==1)s+="000"+year+"-";
		else if(dig==2)s+="00"+year+"-";
		else if(dig==3)s+="0"+year+"-";
		else s+=year+"-";
		dig=countdigits(mounth);
		if(dig==1) s+="0"+mounth+"-";
		else s+=mounth+"-";
		dig=countdigits(day);
		if(dig==1) s+="0"+day+"T";
		else s+=day+"T";
		dig=countdigits(hour);
		if(dig==1) s+="0"+hour+":";
		else s+=hour+":";
		dig=countdigits(minute);
		if(dig==1) s+="0"+minute+":";
		else s+=minute+":";
		dig=countdigits(second);
		if(dig==1) s+="0"+second;
		else s+=second;
		return s;
	}
	/**
	 * help func that count how many digits the number have.
	 * @param a the number that we want to count his digits.
	 * @return int how many digits the number have.
	 */
	private int countdigits(int a) {
		int i=1;
		if(a<10)return 1;
		while(a>10) {
			a=a/10;
			i++;
		}
		return i;
	}
}