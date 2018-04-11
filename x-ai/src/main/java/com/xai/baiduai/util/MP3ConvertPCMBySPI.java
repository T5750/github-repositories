package com.xai.baiduai.util;
import java.io.File;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import com.xai.common.utils.R;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
/**
 * MP3ConvertPCMBySPI
 * MP3转PCM的方法
 * @author 小帅丶
 * @create 2017-12-21 17:53
 **/
public class MP3ConvertPCMBySPI {
    /**
     * MP3转换PCM文件方法
     * @param mp3FilePath  原始文件路径
     * @param pcmFilePath  转换文件的保存路径
     * @throws Exception
     */
    public static void convertMP32PCM(String mp3FilePath, String pcmFilePath){
        //转换PCM audioInputStream 数据
        AudioInputStream audioInputStream = getPcmAudioInputStream(mp3FilePath);
        //写入PCM预给定的文件
        try {
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(pcmFilePath));
        } catch (Exception e) {
            System.out.print("MP3转换PCM错误。"+e.getMessage());
        }
    }
    /**
     * 获取PCM AudioInputStream 数据
     * @param mp3filepath
     * @return AudioInputStream
     */
    private static AudioInputStream getPcmAudioInputStream(String mp3filepath) {
        File mp3File = new File(mp3filepath);
        AudioInputStream audioInputStream = null;
        AudioFormat targetFormat = null;
        try {
            AudioInputStream in = null;
            MpegAudioFileReader mp = new MpegAudioFileReader();
            in = mp.getAudioInputStream(mp3File);
            AudioFormat baseFormat = in.getFormat();
            targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
                    baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, in);
        } catch (Exception e) {
            System.out.print("获取PCM-AudioInputStream 数据错误。"+e.getMessage());
        }
        return audioInputStream;
    }
}
