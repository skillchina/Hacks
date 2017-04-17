package club.iandroid.hack50.collection.ui.mediaplayer;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mydemo.R;

public class MediaPlayerActivity extends Activity {

    private Context context;
    private MediaPlayer mediaPlayer  = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        context = this;

        ((Button)findViewById(R.id.btnStart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(context, R.raw.ydalbj);
                mediaPlayer.start();
            }
        });

        ((Button)findViewById(R.id.btnStartAsset)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AssetManager am = getAssets();
                    AssetFileDescriptor afd = am.openFd("ydalbj.mp3");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
                            afd.getLength());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (Exception e) {
                    Toast.makeText(context,e.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

        ((Button)findViewById(R.id.btnStartUri)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse("");
            }
        });

        ((Button)findViewById(R.id.btnPause)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.pause();
            }
        });

        ((Button)findViewById(R.id.btnStop)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.stop();
            }
        });

        ((Button)findViewById(R.id.btnNext)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mediaPlayer.reset();
                    //装载下一首歌曲
                    mediaPlayer.setDataSource("/mnt/sdcard/mrlm.mp3");
                    //准备声音
                    mediaPlayer.prepare();
                    //播放
                    mediaPlayer.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
