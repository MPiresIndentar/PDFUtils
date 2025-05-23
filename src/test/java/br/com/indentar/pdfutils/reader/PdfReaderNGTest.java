/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package br.com.indentar.pdfutils.reader;

import java.io.File;
import java.util.List;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author pcmix
 */
public class PdfReaderNGTest {
    
    public PdfReaderNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of encontrarReferenciaEmArquivo method, of class PdfReader.
     */
    @Test
    public void testEncontrarReferenciaEmArquivo() {
        System.out.println("encontrarReferenciaEmArquivo");
        String startWith = "Gerais";
        File filePdf=new File("C:\\teste.pdf");
        PdfReader instance = new PdfReader(filePdf);
        List result = instance.encontrarReferenciaEmArquivo(startWith);
        
         System.out.println(result);
    }

    /**
     * Test of findByStartInf method, of class PdfReader.
     */
    @Test
    public void testGetLinhaQueComecaCom() {
        System.out.println("findByStartInf");
        String startInf = "02.1 Webservice";
        File filePdf=new File("C:\\teste.pdf");
        PdfReader instance = new PdfReader(filePdf);

        String result = instance.getLinhaQueComecaCom(startInf);
        System.out.println(result);
    }
    
        @Test
    public void testGetLinhasQueComecaCom() {
        
        String text = "SMH SHS SUPERRR C3L 60S LOTE:1MS12B2649 TERMO:TC 0";
         
         String pdf = "C:\\Users\\pcmix\\Documents\\DELETAVEIS\\PDF_GERADOS0.pdf";
         PdfReader pdfreader = new PdfReader(pdf);
         String linhaRecuperada = pdfreader.linhaUnica();
         
        assertNotNull(linhaRecuperada);
    }
    
     @Test
     void capturaTextoQuebrado(){
         String text = "SMH SHS SUPERRR C3L 60S LOTE:1MS12B2649 TERMO:TC 0";
         
         String pdf = "C:\\Users\\pcmix\\Documents\\DELETAVEIS\\PDF_GERADOS0.pdf";
         PdfReader pdfreader = new PdfReader(pdf);
         
        String linhaRecuperada = pdfreader.linhaUnica();
         
        String textLowerTrimmed = text.toLowerCase().replace(" ","");
        String linhaRecuperadaLowerTrimmed = linhaRecuperada.toLowerCase().replace(" ", "");
        
        assertTrue(linhaRecuperadaLowerTrimmed.contains(textLowerTrimmed));
     }


    /**
     * Test of getAsText method, of class PdfReader.
     */
    @Test
    public void testGetConteudoDoArquivoComoTexto() {
        System.out.println("getAsText");
         File filePdf=new File("C:\\teste.pdf");
        PdfReader instance = new PdfReader(filePdf);
     
        String result = instance.getConteudoDoArquivoComoTexto();
        System.out.println(result);
    }
    
    @Test
    public void testExtractAfter(){
        System.out.println("test extract after");
        File filePdf=new File("C:\\teste.pdf");
        PdfReader pdfReader=new PdfReader(filePdf);
        String extractAfter2 = pdfReader.extrairTextoEntre("01. Resumo","02. ");
        System.out.println(extractAfter2);
    }

}
