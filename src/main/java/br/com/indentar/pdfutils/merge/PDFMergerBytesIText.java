package br.com.indentar.pdfutils.merge;

import br.com.indentar.pdfutils.merge.exceptions.PdfMergeException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PDFMergerBytesIText {

    public static byte[] mergePdfStringBase64(List<String> listPdfByteBase64) throws PdfMergeException {
        // Decodificando os bytes do Base64
        List<byte[]> listPdfBytesDecode = new ArrayList<>();
        for (String stringPdfBase64 : listPdfByteBase64) {
            listPdfBytesDecode.add(Base64.getDecoder().decode(stringPdfBase64));
        }
        return mergePdfBytes(listPdfBytesDecode);
    }
    public static byte[] mergePdfBytesBase64(List<byte[]> listPdfByteBase64) throws PdfMergeException {
        // Decodificando os bytes do Base64
        List<byte[]> listPdfBytesDecode = new ArrayList<>();
        for (byte[] bytesPdfBase64 : listPdfByteBase64) {
            listPdfBytesDecode.add(Base64.getDecoder().decode(bytesPdfBase64));
        }
        return mergePdfBytes(listPdfBytesDecode);
    }

    public static byte[] mergePdfBytes(List<byte[]> listPdfByte) throws PdfMergeException {
        try {
            // Criando um fluxo para o PDF final
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(outputStream);

            try (PdfDocument mergedPdf = new PdfDocument(writer)) {
                for (byte[] pdfBytes : listPdfByte) {
                    // Lendo os PDFs a partir dos bytes
                    PdfReader reader = new PdfReader(new ByteArrayInputStream(pdfBytes));
                    PdfDocument pdfDocument = new PdfDocument(reader);

                    // Copiando todas as páginas para o PDF mesclado
                    pdfDocument.copyPagesTo(1, pdfDocument.getNumberOfPages(), mergedPdf);

                    // Fechando o documento individual
                    pdfDocument.close();
                }
            }

            // Retornando os bytes do PDF mesclado
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new PdfMergeException(
                    listPdfByte.toArray(),
                    "Erro ao mesclar PDFs",
                    600,
                    null,
                    null,
                    null,
                    null,
                    e
            );
        }
    }

}
