/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.indentar.pdfutils;

import java.io.File;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author pcmix
 */
public class PdfReader {

    private File fileOrDirectory;
    
    public void iniciar() {
        String pastaBoletos = "G:\\INDENTAR\\CLIENTES\\SGW\\SANTA-00823189000112\\Anne- verificação de boletos enviados";
        File FilePastaBoletos = new File(pastaBoletos);
        
        
        String[] extensions = {"pdf"};
        Iterator<File> listFilesExtension = FileUtils.iterateFiles(fileOrDirectory, extensions, true);
        while (listFilesExtension.hasNext()) {
            File next = listFilesExtension.next();
        }

    }
}
