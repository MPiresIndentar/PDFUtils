/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.indentar.pdfutils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
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
    public void testIniciar() {
        System.out.println("iniciar");
        PdfReader instance = null;
        instance.iniciar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readLines method, of class PdfReader.
     */
    @org.junit.Test
    public void testReadLines() {
        System.out.println("readLines");
        String filePdf = "G:\\INDENTAR\\CLIENTES\\SGW\\SANTA-00823189000112\\Anne- verificação de boletos enviados\\boleto-ELETROCAMP_CONST.ELETRICAS_E_CIVIS_LTDA_19_11_2024NFS21986B.pdf";
        PdfReader instance = new PdfReader(filePdf);
        List<String> expResult = new ArrayList<>();
        List<String> result = instance.readLines();
        assertEquals(expResult, result);
    }

    /**
     * Test of findByStartInf method, of class PdfReader.
     */
    @Test
    public void testFindByStartInf() {
        System.out.println("findByStartInf");
        String startInf = "Ref. N";
        String filePdf = "G:\\INDENTAR\\CLIENTES\\SGW\\SANTA-00823189000112\\Anne- verificação de boletos enviados\\boleto-ELETROCAMP_CONST.ELETRICAS_E_CIVIS_LTDA_19_11_2024NFS21986B.pdf";
        PdfReader instance = new PdfReader(filePdf);
        String expResult = "";
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
        System.out.println("getAsText");
        PdfReader instance = null;
        String expResult = "";
        String result = instance.getAsText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    public static class ExtractorBoleto {
        
        public static List<String> extract(String text) {
            String regex = "Ref\\. (NFse|NFe):([0-9]+)";
            List<String> listInf = new ArrayList<>();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                String type = matcher.group(1);
                String number = matcher.group(2);
                listInf.add(type);
                listInf.add(number);
                return listInf;
            }
            return listInf;
            
        }
    }
    
}
