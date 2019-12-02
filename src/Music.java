import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;

public class Music {
    static MediaPlayer mediaPlayer;

    public static MediaPlayer PlayMusic(String path){
        final Media media = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(1);
        return mediaPlayer;
    }

    public static void StopMusic(MediaPlayer mediaPlayer){
        mediaPlayer.stop();
    }

    public static void ExplosionSoundEffect(String path){
        Media player = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(player);
        mediaPlayer.setVolume(0.08);
        mediaPlayer.play();
    }
    public static void PlaySoundEffect(String path){
        Media player = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(player);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();
    }
}


