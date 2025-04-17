import java.io.File;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

abstract class Audio {
	public abstract void play();
	
	public void loop(int n) {
		for(int i = 0; i < n; i++) {
			play();
		}
	}
}

public class SoundPlayer extends Audio{
	private static final int BUFFER_SIZE = 128000;
	static int loopNumber = 1;
	String soundFileName;
	
	public SoundPlayer(String soundFileName) {
		this.soundFileName = soundFileName;
	}
	
	public void play() {
		try {
			int nBytesRead = 0;
			byte[] dat = new byte[BUFFER_SIZE];
			File soundFile = new File(soundFileName);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
			line.open(audioFormat);
			line.start();
			while(nBytesRead != -1) {
				nBytesRead = audioInputStream.read(dat,0,dat.length);
				if(nBytesRead >= 0) {
					int nBytesWritten = line.write(dat, 0, nBytesRead);
				}
			}
			
			line.drain();
			line.close();
		}catch(FileNotFoundException e){
			System.out.println("音声ファイルが見つかりません");		
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}