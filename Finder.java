// 
// Decompiled by Procyon v0.5.36
// 

package application;

import java.io.File;
import java.util.ArrayList;

public class Finder
{
    private ArrayList<File> archivosMp3;
    private ArrayList<File> archivosMp3Extraviados;
    private boolean outsideFlag;
    
    public ArrayList<File> getArchivosMp3() {
        return this.archivosMp3;
    }
    
    public ArrayList<File> getArchivosMp3Extraviados() {
        return this.archivosMp3Extraviados;
    }
    
    public Finder() {
        this.archivosMp3 = new ArrayList<File>();
        this.archivosMp3Extraviados = new ArrayList<File>();
    }
    
    public void listFiles(final File file) {
        final File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (int x = 0; x < files.length; ++x) {
                if (files[x].isDirectory()) {
                    if (files[x].getAbsolutePath().lastIndexOf("Users") < 0) {
                        this.outsideFlag = true;
                    }
                    else {
                        this.outsideFlag = false;
                    }
                    this.listFiles(files[x]);
                }
                else {
                    String extension = null;
                    if (files[x].getName().lastIndexOf(46) >= 0) {
                        extension = files[x].getName().substring(files[x].getName().lastIndexOf(46));
                        if (extension.equalsIgnoreCase(".mp3")) {
                            this.archivosMp3.add(files[x]);
                            if (this.outsideFlag) {
                                this.archivosMp3Extraviados.add(files[x]);
                            }
                        }
                    }
                }
            }
        }
    }
}
