package se.miun.taja1900.dt031g.dailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SoundPlayer {
    private SoundPool soundPool;
    private final int soundZero ,soundOne, soundTwo, soundThree, soundFour, soundFive, soundSix, soundSeven,
            soundEight, soundNine, soundStar, soundPound;
    Util util = new Util();
    String voiceName;
    File file;
    private static SoundPlayer instance;
    private SoundPlayer(Context context){


        soundPool = new SoundPool.Builder()
                .setMaxStreams(3)
                .setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()).build();

        file = util.getDirForVoice(context, voiceName);

        if (file.length() == 0)
            Util.copyDefaultVoiceToInternalStorage(context);

        String sPath =  file.getAbsolutePath() + File.separator;

        soundZero = soundPool.load(sPath + "zero.mp3", 1);
        soundOne = soundPool.load(sPath + "one.mp3", 1);
        soundTwo = soundPool.load(sPath + "two.mp3", 1);
        soundThree = soundPool.load(sPath + "three.mp3", 1);
        soundFour = soundPool.load(sPath + "four.mp3", 1);
        soundFive = soundPool.load(sPath + "five.mp3", 1);
        soundSix = soundPool.load(sPath + "six.mp3", 1);
        soundSeven = soundPool.load(sPath + "seven.mp3", 1);
        soundEight = soundPool.load(sPath + "eight.mp3", 1);
        soundNine = soundPool.load(sPath + "nine.mp3", 1);
        soundStar = soundPool.load(sPath + "star.mp3", 1);
        soundPound = soundPool.load(sPath + "pound.mp3", 1);
/**/
    }


    public static SoundPlayer getInstance(Context context){
        if (instance == null){
            instance = new SoundPlayer(context);
        }
        return instance;
    }


    @SuppressLint("NonConstantResourceId")
    public void playSound(DialpadButton dial){
        switch (dial.getTitle()){
            case 0:
                soundPool.play(soundZero, 1, 1, 1, 0, 1);
                break;
            case 1:
                soundPool.play(soundOne, 1, 1, 1, 0, 1);
                break;
            case 2:
                soundPool.play(soundTwo, 1, 1, 1, 0, 1);
                break;
            case 3:
                soundPool.play(soundThree, 1, 1, 1, 0, 1);
                break;
            case 4:
                soundPool.play(soundFour, 1, 1, 1, 0, 1);
                break;
            case 5:
                soundPool.play(soundFive, 1, 1, 1, 0, 1);
                break;
            case 6:
                soundPool.play(soundSix, 1, 1, 1, 0, 1);
                break;
            case 7:
                soundPool.play(soundSeven, 1, 1, 1, 0, 1);
                break;
            case 8:
                soundPool.play(soundEight, 1, 1, 1, 0, 1);
                break;
            case 9:
                soundPool.play(soundNine, 1, 1, 1, 0, 1);
                break;
            case 10:
                soundPool.play(soundStar, 1, 1, 1, 0, 1);
                break;
            case 11:
                soundPool.play(soundPound, 1, 1, 1, 0, 1);
                break;
        }
    }

    public void destroy(){
        if (soundPool != null){
            soundPool.release();
            soundPool.setOnLoadCompleteListener(null);
            soundPool = null;
            instance = null;
        }
    }
}
