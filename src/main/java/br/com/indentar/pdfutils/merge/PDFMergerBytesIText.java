package br.com.indentar.pdfutils.merge;

import br.com.indentar.pdfutils.merge.exceptions.PdfMergeException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class PDFMergerBytesIText {

    public static byte[] mergePdfBytesBase64(String base64Pdf1, String base64Pdf2) throws PdfMergeException {
        // Decodificando os bytes do Base64
        byte[] bytesPdf1 = Base64.getDecoder().decode(base64Pdf1);
        byte[] bytesPdf2 = Base64.getDecoder().decode(base64Pdf2);
        return mergePdfBytes(bytesPdf1, bytesPdf2);
    }

    public static byte [] mergePdfBytesBase64(byte[] base64Pdf1, byte[] base64Pdf2) throws PdfMergeException {
        // Decodificando os bytes do Base64
        byte[] bytesPdf1 = Base64.getDecoder().decode(base64Pdf1);
        byte[] bytesPdf2 = Base64.getDecoder().decode(base64Pdf2);
        return mergePdfBytes(bytesPdf1, bytesPdf2);
    }

    public static byte[] mergePdfBytes(byte[] bytesPdf1, byte[] bytesPdf2) throws PdfMergeException {
        try {
            // Lendo os PDFs a partir de bytes
            PdfReader reader1 = new PdfReader(new ByteArrayInputStream(bytesPdf1));
            PdfReader reader2 = new PdfReader(new ByteArrayInputStream(bytesPdf2));

            // Criando um fluxo para o PDF final
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(outputStream);

            try (PdfDocument mergedPdf = new PdfDocument(writer)) {
                PdfDocument pdf1 = new PdfDocument(reader1);
                PdfDocument pdf2 = new PdfDocument(reader2);

                // Copiando as páginas de ambos os PDFs
                pdf1.copyPagesTo(1, pdf1.getNumberOfPages(), mergedPdf);
                pdf2.copyPagesTo(1, pdf2.getNumberOfPages(), mergedPdf);

                // Fechando documentos
                pdf1.close();
                pdf2.close();
            }

            // Retornando os bytes do PDF mesclado
            return outputStream.toByteArray();
        } catch (Exception e) {
            Object[] request = {bytesPdf1, bytesPdf2};
            throw new PdfMergeException(request, "Erro ao mesclar pdf", 600, null, null, null, null, e);
        }
    }

}
