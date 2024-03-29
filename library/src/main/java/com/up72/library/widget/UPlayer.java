package com.up72.library.widget;

/**
 * Created by cwb on 2017/3/17.
 */
import android.media.MediaPlayer;
import android.util.Log;

public class UPlayer implements IVoiceManager {

    private final String TAG = UPlayer.class.getName();
    private String path;

    private MediaPlayer mPlayer;
    public UPlayer(String path){
        this.path = path;
        mPlayer = new MediaPlayer();
    }

    @Override
    public boolean start() {
        try {
            //设置要播放的文件
            mPlayer.setDataSource(path);
            mPlayer.prepare();
            //播放
            mPlayer.start();
        }catch(Exception e){
            Log.e(TAG, "prepare() failed");
        }

        return false;
    }

    @Override
    public boolean stop() {
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
        return false;
    }

}

