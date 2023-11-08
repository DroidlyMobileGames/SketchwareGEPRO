package com.besome.sketch;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import mod.agus.jcoderz.lib.FileUtil;

public class AssetManager {
    Context context;
    public String javaDir = "";
    private String sc_id;

    public AssetManager (Context context,String sc_id){
        this.context = context;
        this.sc_id = sc_id;

    }

    public void copyFile(String filename,String filepath) {
        //Copys items from assets to whatever location you'd like
        android.content.res.AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
        }
        for (String filenames : files) {
            InputStream in = null;
            OutputStream out = null;
            File outFile = null;
            if (filenames.equals(filename)) {
                try {
                    in = assetManager.open(filename);
                    outFile = new File(filepath, filename);
                    out = new FileOutputStream(outFile);
                    _copyFile(in, out);
                    in.close();
                    in = null;
                    out.flush();
                    out.close();
                    out = null;
                } catch (IOException e) {

                }
            }
        }
    }

    private void _copyFile(InputStream in, OutputStream out) {
        byte[] buffer = new byte[1024];
        int read;
        while (true){
            try {
                if (!((read = in.read(buffer)) !=-1)) break;
                out.write(buffer,0,read);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
