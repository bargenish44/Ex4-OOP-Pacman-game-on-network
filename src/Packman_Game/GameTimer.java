package Packman_Game;

import java.util.Timer;
import java.util.TimerTask;
/**
 * This class represents Game timer.
 * @author Bar Genish
 * @author Elyashiv Deri
 * @author lioz elmalem
 */
public class GameTimer {
	private Timer _timer;
	/**
	 * Function that start the timer.
	 * @param task the task that we want the timer will do when the times come.
	 */
	public void startTimer(TimerTask task) {
		_timer = new Timer();
		_timer.scheduleAtFixedRate(task, 0, 2000);
	}
	/**
	 * Function that end the timer.
	 */
	public void endTimer() {
		_timer.cancel();
	}
}
