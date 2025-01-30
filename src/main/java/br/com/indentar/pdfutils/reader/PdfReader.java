/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.indentar.pdfutils.reader;

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

    private String pdfPath;

    private File fileOrDirectory;

    public PdfReader(File fileOrDirectory) {
        this.fileOrDirectory = fileOrDirectory;
    }

    public PdfReader(String fileOrDirectory) {
        this.fileOrDirectory = new File(fileOrDirectory);
    }

    public List<String> encontrarReferenciaEmArquivo(String startWith) {
        try {
            List<String> listInfByStartInf = new ArrayList<>();
            if (fileOrDirectory.isDirectory()) {
                String[] extensions = {"pdf"};
                Iterator<File> fileIterator = FileUtils.iterateFiles(fileOrDirectory, extensions, true);
                while (fileIterator.hasNext()) {
                    fileOrDirectory = fileIterator.next();
                    String findByStartInf = findByStartInf(startWith);
                    listInfByStartInf.add(findByStartInf);
                }
            } else {
                String findByStartInf = findByStartInf(startWith);
                listInfByStartInf.add(findByStartInf);
            }
            return listInfByStartInf;
        } catch (Exception ex) {
            Logger.getLogger(PdfReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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

    public void definePdfPath(String pdfPath) throws Exception {
        if(pdfPath.isEmpty()) throw new Exception("pdfPath is empty");
        this.pdfPath = pdfPath;
    }

    /**
     * Lé o Resumo do pdf, O Resumo inicia com "01. Resumo"
     * @return String, devolve o conteúdo encontrado
     * @throws Exception, lanã excessão se não foi possivel concluir a operação.
     */
    public String lerResumo() throws Exception {

        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String text = pdfTextStripper.getText(document);

            return extractAfterTitle(text, "01. Resumo");

        } catch (IOException e) {
            throw new Exception("Falha ao ler pdf: " + e.getMessage());
        }
    }

    private String extractAfterTitle(String text, String title) {
        String[] lines = text.split("\n");
        StringBuilder content = new StringBuilder();
        boolean foundTitle = false;

        for (String line : lines) {
            if (foundTitle) {
                if (line.trim().startsWith("02. ")) break;

                content.append(line).append("\n");
            }
            if (line.trim().equalsIgnoreCase(title)) {
                foundTitle = true;
            }
        }

        return content.toString().trim();
    }

}
