package Packman_Game;
import java.io.*;
import sun.audio.*;

public class PlaySound {
	/**
	 * This Department is responsible for the music.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	/**
	 * Play specific sound.
	 * @param path - the path of the files that we want to play.
	 * @throws IOException - if he didn't play the sound.
	 */
	public PlaySound(String path) throws IOException {// open the sound file as a Java input stream
		String gongFile = path;
		InputStream in = new FileInputStream(gongFile);

		// create an audiostream from the inputstream
		AudioStream audioStream = new AudioStream(in);

		// play the audio clip with the audioplayer class
		AudioPlayer.player.start(audioStream);
	}
}