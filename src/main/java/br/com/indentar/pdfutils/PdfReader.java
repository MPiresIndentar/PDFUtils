/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.indentar.pdfutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
 *
 * @author pcmix
 */
public class PdfReader {

    private File fileOrDirectory;

    public PdfReader(File fileOrDirectory) {
        this.fileOrDirectory = fileOrDirectory;
    }

    public PdfReader(String fileOrDirectory) {
        this.fileOrDirectory = new File(fileOrDirectory);
    }

    public void iniciar() {
        String pastaBoletos = "G:\\INDENTAR\\CLIENTES\\SGW\\SANTA-00823189000112\\Anne- verificação de boletos enviados";
        File FilePastaBoletos = new File(pastaBoletos);

        String[] extensions = {"pdf"};
        Iterator<File> listFilesExtension = FileUtils.iterateFiles(fileOrDirectory, extensions, true);
        while (listFilesExtension.hasNext()) {
            File next = listFilesExtension.next();
        }

    }

    public String findByStartInf(String startInf) {
        List<String> readLines = readLines();
        for (String line : readLines) {
            if (line.startsWith(startInf)) {
                return line;
            }
        }
        return null;
    }

    public List<String> readLines() {
        String pdfAsText = getAsText();
        String lines[] = pdfAsText.split("\\r?\\n");
        List<String> lineList = new ArrayList<>(Arrays.asList(lines));
        return lineList;
    }

    public String getAsText() {

        List<String> lineList = new ArrayList<>();
        try (PDDocument document = PDDocument.load(fileOrDirectory)) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);

                //System.out.println("Text:" + st);
                return pdfFileInText;

            }

        } catch (IOException ex) {
            Logger.getLogger(PdfReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

}
