// 
// Decompiled by Procyon v0.5.36
// 

package application;

import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;

public class Comparer
{
    private ArrayList<String> informe;
    private ArrayList<File> archivosMp3;
    private ArrayList<File> archivosMp3Extraviados;
    
    public Comparer(final ArrayList<File> archivosMp3, final ArrayList<File> archivosMp3Extraviados) {
        this.informe = new ArrayList<String>();
        this.archivosMp3 = archivosMp3;
        this.archivosMp3Extraviados = archivosMp3Extraviados;
        this.informe.add("INFORME DE CANCIONES POTENCIALMENTE DUPLICADAS");
        this.informe.add("Formatos: MP3, WAV");
        this.informe.add("Analisis en base al nombre y tama\u00f1o del archivo");
    }
    
    public void compare(final File file1, final File file2) {
        int similitud = 0;
        final String nombre1 = file1.getName().substring(0, file1.getName().lastIndexOf(46));
        final String nombre2 = file2.getName().substring(0, file2.getName().lastIndexOf(46));
        final int longitud1 = nombre1.length();
        final int longitud2 = nombre2.length();
        final long tama\u00f1o1 = file1.length();
        final long tama\u00f1o2 = file2.length();
        if (longitud1 < longitud2) {
            if (longitud1 + 10 >= longitud2) {
                ++similitud;
            }
        }
        else if (longitud2 < longitud1) {
            if (longitud2 + 10 >= longitud1) {
                ++similitud;
            }
        }
        else {
            ++similitud;
        }
        if (similitud == 1) {
            double iteraciones = 0.0;
            double aciertos = 0.0;
            String stringMayor;
            if (file1.getName().lastIndexOf(46) >= 0) {
                stringMayor = file1.getName().substring(0, file1.getName().lastIndexOf(46));
            }
            else {
                stringMayor = file1.getName();
            }
            String stringMenor;
            if (file2.getName().lastIndexOf(46) >= 0) {
                stringMenor = file2.getName().substring(0, file2.getName().lastIndexOf(46));
            }
            else {
                stringMenor = file2.getName();
            }
            if (stringMenor.length() > stringMayor.length()) {
                final String temp = stringMenor;
                stringMenor = stringMayor;
                stringMayor = temp;
            }
            if (stringMayor.equalsIgnoreCase(stringMenor)) {
                ++similitud;
            }
            for (int x = 0; x <= stringMenor.length(); ++x) {
                for (int y = 0; y + x <= stringMenor.length(); ++y) {
                    final String subString = stringMenor.substring(y, y + x);
                    ++iteraciones;
                    if (stringMayor.indexOf(subString) > -1) {
                        ++aciertos;
                    }
                }
            }
            if (aciertos >= iteraciones / 100.0 * 75.0) {
                ++similitud;
            }
        }
        if (similitud == 0) {
            if (tama\u00f1o1 > tama\u00f1o2) {
                if (tama\u00f1o1 - tama\u00f1o2 < 10000L) {
                    ++similitud;
                }
            }
            else if (tama\u00f1o2 > tama\u00f1o1) {
                if (tama\u00f1o2 - tama\u00f1o1 < 10000L) {
                    ++similitud;
                }
            }
            else {
                ++similitud;
            }
        }
        else if (similitud == 1) {
            if (tama\u00f1o1 > tama\u00f1o2) {
                if (tama\u00f1o1 - tama\u00f1o2 < 100000L) {
                    ++similitud;
                }
            }
            else if (tama\u00f1o2 > tama\u00f1o1) {
                if (tama\u00f1o2 - tama\u00f1o1 < 100000L) {
                    ++similitud;
                }
            }
            else {
                ++similitud;
            }
        }
        else if (similitud == 2) {
            if (tama\u00f1o1 > tama\u00f1o2) {
                if (tama\u00f1o1 - tama\u00f1o2 < 1000000L) {
                    ++similitud;
                }
            }
            else if (tama\u00f1o2 > tama\u00f1o1) {
                if (tama\u00f1o2 - tama\u00f1o1 < 1000000L) {
                    ++similitud;
                }
            }
            else {
                ++similitud;
            }
        }
        if (similitud > 2) {
            this.informe.add("Similitud de nivel alto entre:");
            this.informe.add(file1.getAbsolutePath());
            this.informe.add(file2.getAbsolutePath());
        }
    }
    
    public void compareAll() {
        for (int x = 0; x < this.archivosMp3.size(); ++x) {
            for (int y = x + 1; y < this.archivosMp3.size(); ++y) {
                this.compare(this.archivosMp3.get(x), this.archivosMp3.get(y));
            }
        }
    }
    
    public void writeInform() {
        this.informe.add("INFORME DE CANCIONES EXTRAVIADAS");
        for (int x = 0; x < this.archivosMp3Extraviados.size(); ++x) {
            this.informe.add(this.archivosMp3Extraviados.get(x) + "\n");
        }
        try {
            final BufferedWriter bw = new BufferedWriter(new FileWriter("Informe de Canciones.txt"));
            for (int x2 = 0; x2 < this.informe.size(); ++x2) {
                bw.write(this.informe.get(x2));
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
