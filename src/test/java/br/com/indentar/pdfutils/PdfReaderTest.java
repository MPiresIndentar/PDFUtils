/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.indentar.pdfutils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Marlucio
 */
public class PdfReaderTest {

    public PdfReaderTest() {
    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of iniciar method, of class PdfReader.
     */
    @org.junit.Test
    public void testEncontrarReferenciaEmArquivo() {
        System.out.println("identifica Arvivo");
        String filePdf = "G:\\INDENTAR\\CLIENTES\\SGW\\SANTA-00823189000112\\Anne- verificação de boletos enviados\\pdfs-boleto-20240925T155725Z-001\\pdfs-boleto";
        PdfReader instance = new PdfReader(filePdf);
        List<String> result = instance.encontrarReferenciaEmArquivo("Ref. ");
        result.forEach(inf -> System.out.println(inf));
        System.out.println("Arvivo Identificado");
    }


    /**
     * Test of readLines method, of class PdfReader.
     */
    @org.junit.Test
    public void testReadLines() {
        System.out.println("readLinesTest");
        String filePdf = "C:\\Users\\pcmix\\Downloads\\pdfs-boleto-20240925T155725Z-001\\pdfs-boleto\\boleto-ADELSON_SILVA_MENDONCA_EPP_23_11_2024NF43097B.pdf";
        PdfReader instance = new PdfReader(filePdf);
        List<String> expResult = new ArrayList<>();
        List<String> result = instance.readLines();
        System.out.println("readLines");
    }

    /**
     * Test of findByStartInf method, of class PdfReader.
     */
    @Test
    public void testFindByStartInf() {
        System.out.println("findByStartInf");
        String startInf = "Ref. N";
        String filePdf = "G:\\INDENTAR\\CLIENTES\\SGW\\SANTA-00823189000112\\Anne- verificação de boletos enviados\\pdfs-boleto-20240925T155725Z-001\\pdfs-boleto";
        PdfReader instance = new PdfReader(filePdf);
        String result = instance.findByStartInf(startInf);
        List<String> listInf = ExtractorBoleto.extract(result);
        System.out.println(listInf.get(0) + ";" + listInf.get(1));
        System.out.println(result);
    }

    /**
     * Test of getAsText method, of class PdfReader.
     */
    @Test
    public void testGetAsText() {
        System.out.println("getAsTextTest");
        String filePdf = "C:\\Users\\pcmix\\Downloads\\pdfs-boleto-20240925T155725Z-001\\pdfs-boleto";
        PdfReader instance = new PdfReader(filePdf);
        String result = instance.getAsText();
        System.out.println("getAsText!");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public static class ExtractorBoleto {

        public static List<String> extract(String text) {
            String regex = "Ref\\. (NFse|NFe|NF):([0-9]+)";
            List<String> listInf = new ArrayList<>();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                String type = matcher.group(1);
                String number = matcher.group(2);
                listInf.add(type);
                listInf.add(number);
                listInf.add(number);
                return listInf;
            }
            return listInf;

        }
    }

}
