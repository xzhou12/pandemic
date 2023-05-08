package backend;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class sonido {
	public static int sonidoSFX = 50;

	public static void pulsarBoton() {
		try {
			// Nombre del archivo (.wav)
			File soundFile = new File("select.wav");
			// cosas de sonido
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			// abre el clip
			clip.open(audioInputStream);
			float decibels = (float) (Math.log(sonidoSFX / 100.0) / Math.log(10.0) * 20.0);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// establece el volumen
			gainControl.setValue(decibels);
			// reproduce el clip
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pulsarCiudad() {
		try {
			// Nombre del archivo (.wav)
			File soundFile = new File("select.wav");
			// cosas de sonido
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			// abre el clip
			clip.open(audioInputStream);
			// conviete el volumen de config en decibelios
			float decibels = (float) (Math.log(sonidoSFX / 100.0) / Math.log(10.0) * 20.0);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// establece el volumen
			gainControl.setValue(decibels);
			// reproduce el clip
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pulsarInvestigar() {
		try {
			// Nombre del archivo (.wav)
			File soundFile = new File("select.wav");
			// cosas de sonido
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			// abre el clip
			clip.open(audioInputStream);
			// conviete el volumen de config en decibelios
			float decibels = (float) (Math.log(sonidoSFX / 100.0) / Math.log(10.0) * 20.0);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// establece el volumen
			gainControl.setValue(decibels);
			// reproduce el clip
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pulsarCurar() {
		try {
			// Nombre del archivo (.wav)
			File soundFile = new File("select.wav");
			// cosas de sonido
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			// abre el clip
			clip.open(audioInputStream);
			// conviete el volumen de config en decibelios
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float decibels = (float) (Math.log(sonidoSFX / 100.0) / Math.log(10.0) * 20.0);
			// establece el volumen
			gainControl.setValue(decibels);
			// reproduce el clip
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sonidaVictoria() {
		try {
			// Nombre del archivo (.wav)
			File soundFile = new File("victoria.wav");
			// cosas de sonido
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			// abre el clip
			clip.open(audioInputStream);
			// conviete el volumen de config en decibelios
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float decibels = (float) (Math.log(sonidoSFX / 100.0) / Math.log(10.0) * 20.0);
			// establece el volumen
			gainControl.setValue(decibels);
			// reproduce el clip
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
