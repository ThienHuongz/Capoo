package project;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
    private Clip clip;
    private URL soundURL[] = new URL[10];

    public SoundEffect() {
        soundURL[0] = getClass().getResource("../assets/sound/mario_jumping-mike_koenig-989896458.wav");
        soundURL[1] = getClass().getResource("../assets/sound/birdsong-140428.wav");
        soundURL[2] = getClass().getResource("../assets/sound/sound_hover2.wav");
        soundURL[3] = getClass().getResource("../assets/sound/clickStartGame.wav");
        soundURL[4] = getClass().getResource("../assets/sound/MenuBGM.wav");

        soundURL[5] = getClass().getResource("../assets/sound/fish_collect.wav");

    }

    public void SetClip(int i) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {

        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pause() {
        // if (clip != null && clip.isRunning()) {
        clip.stop();
        // }
    }
}
