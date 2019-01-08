package Packman_Game;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
	private Timer _timer;

	public void startTimer(TimerTask task) {
		_timer = new Timer();
		_timer.scheduleAtFixedRate(task, 0, 2000);
	}
	public void endTimer() {
		_timer.cancel();
	}
}
