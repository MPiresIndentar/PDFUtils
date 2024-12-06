package br.com.indentar.pdfutils.merge;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class PDFMergerBytesIText {

    public byte[] mergePdfBytes(String base64Pdf1, String base64Pdf2) {
        try {
            // Decodificando os bytes do Base64
            byte[] bytesPdf1 = Base64.getDecoder().decode(base64Pdf1);
            byte[] bytesPdf2 = Base64.getDecoder().decode(base64Pdf2);

            // Lendo os PDFs a partir de bytes
            PdfReader reader1 = new PdfReader(new ByteArrayInputStream(bytesPdf1));
            PdfReader reader2 = new PdfReader(new ByteArrayInputStream(bytesPdf2));

            // Criando um fluxo para o PDF final
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(outputStream);

            PdfDocument mergedPdf = new PdfDocument(writer);
            PdfDocument pdf1 = new PdfDocument(reader1);
            PdfDocument pdf2 = new PdfDocument(reader2);

            // Copiando as páginas de ambos os PDFs
            pdf1.copyPagesTo(1, pdf1.getNumberOfPages(), mergedPdf);
            pdf2.copyPagesTo(1, pdf2.getNumberOfPages(), mergedPdf);

            // Fechando documentos
            pdf1.close();
            pdf2.close();
            mergedPdf.close();

            // Retornando os bytes do PDF mesclado
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
