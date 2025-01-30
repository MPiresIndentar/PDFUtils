
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

    private File fileOrDirectory;

    
     /**
     * Construtor que recebe um objeto File.
     * @param fileOrDirectory Arquivo ou diretório contendo arquivos PDF.
     */
    public PdfReader(File fileOrDirectory) {
        this.fileOrDirectory = fileOrDirectory;
    }

     /**
     * Construtor que recebe um caminho de arquivo em String.
     * @param fileOrDirectory Caminho do arquivo ou diretório contendo arquivos PDF.
     */
    public PdfReader(String fileOrDirectory) {
        this.fileOrDirectory = new File(fileOrDirectory);
    }

    /**
     * Encontra linhas em arquivos PDF que começam com um determinado prefixo.
     * @param startWith Prefixo a ser buscado.
     * @return Lista de strings contendo as linhas encontradas.
     */
    public List<String> encontrarReferenciaEmArquivo(String startWith) {
        try {
            List<String> lista = new ArrayList<>();
            if (fileOrDirectory.isDirectory()) {
                String[] extensions = {"pdf"};
                Iterator<File> fileIterator = FileUtils.iterateFiles(fileOrDirectory, extensions, true);
                while (fileIterator.hasNext()) {
                    fileOrDirectory = fileIterator.next();
                    String findByStartInf = getLinhaQueComecaCom(startWith);
                    lista.add(findByStartInf);
                }
            } else {
                String findByStartInf = getLinhaQueComecaCom(startWith);
                lista.add(findByStartInf);
            }
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(PdfReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

      /**
     * Encontra a primeira linha em um arquivo PDF que começa com um determinado prefixo.
     * @param busca Prefixo a ser buscado.
     * @return A primeira linha encontrada ou null se nenhuma linha for encontrada.
     */
    public String getLinhaQueComecaCom(String busca) {
       return getLinhas().stream() .filter(it -> it.startsWith(busca))
                .findFirst()
                .orElse(null);
    }

       /**
     * Lê todas as linhas de um arquivo PDF.
     * @return Lista de linhas do arquivo.
     */
    public List<String> getLinhas() {
        String pdfAsText = getConteudoDoArquivoComoTexto();
        String lines[] = pdfAsText.split("\\r?\\n");
        List<String> lineList = new ArrayList<>(Arrays.asList(lines));
        return lineList;
    }

       /**
     * Extrai o texto de um arquivo PDF.
     * @return Conteúdo textual do arquivo PDF.
     */
    public String getConteudoDoArquivoComoTexto() {
        try (PDDocument document = PDDocument.load(fileOrDirectory)) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);

                return pdfFileInText;

            }

        } catch (IOException ex) {
            Logger.getLogger(PdfReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
      /**
     * Extrai o texto de um PDF a partir de um título específico até um ponto de interrupção.
     * @param textoInicial Título inicial da extração.
     * @param textoDeParada Ponto onde a extração deve parar.
     * @return O conteúdo extraído após o título especificado.
     */
        public String extrairTextoEntre(String textoInicial, String textoDeParada) {
        List<String> listLines = getLinhas();
        StringBuilder content = new StringBuilder();
        boolean foundTitle = false;

        for (String line : listLines) {
            if (foundTitle) {
                if (line.trim().startsWith(textoDeParada)) {
                    break;
                }
                content.append(line).append("\n");
            }
            if (line.trim().equalsIgnoreCase(textoInicial)) {
                foundTitle = true;
            }
        }

        return content.toString().trim();
    }

}
