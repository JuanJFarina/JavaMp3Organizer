// 
// Decompiled by Procyon v0.5.36
// 

package application;

import java.awt.Component;
import javax.swing.JProgressBar;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.io.File;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Window
{
    private JFrame frmMp;
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final Window window = new Window();
                    window.frmMp.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        final Finder finder = new Finder();
        final File[] unidades = File.listRoots();
        finder.listFiles(unidades[0]);
        final Comparer comparer = new Comparer(finder.getArchivosMp3(), finder.getArchivosMp3Extraviados());
        comparer.compareAll();
        comparer.writeInform();
        System.exit(0);
    }
    
    public Window() {
        this.initialize();
    }
    
    private void initialize() {
        (this.frmMp = new JFrame()).setResizable(false);
        this.frmMp.setUndecorated(true);
        this.frmMp.setCursor(Cursor.getPredefinedCursor(3));
        this.frmMp.getContentPane().setCursor(Cursor.getPredefinedCursor(0));
        this.frmMp.getContentPane().setLayout(new BorderLayout(0, 0));
        final JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setStringPainted(true);
        progressBar.setString("TRABAJANDO");
        this.frmMp.getContentPane().add(progressBar, "Center");
        this.frmMp.setBounds(100, 100, 200, 50);
        this.frmMp.setDefaultCloseOperation(3);
    }
}
