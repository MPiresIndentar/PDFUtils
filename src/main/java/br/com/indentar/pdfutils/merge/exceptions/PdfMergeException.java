/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.indentar.pdfutils.merge.exceptions;

import br.com.indentar.pdfutils.exception.ExceptionBaseApiAbstract;

/**
 *
 * @author indentar
 */
public  class PdfMergeException extends ExceptionBaseApiAbstract {

    public PdfMergeException(Object request, Object response, int codeErro, Object modeloRequest, Object modeloResponse, String linkDocumentacao, String string, Throwable thrwbl) {
        super(request, response, codeErro, modeloRequest, modeloResponse, linkDocumentacao, string, thrwbl);
    }

    public Object getRequest() {
        return request;
    }

    public Object getResponse() {
        return response;
    }

    public int getCodeErro() {
        return codeErro;
    }

    public Object getModeloRequest() {
        return modeloRequest;
    }

    public Object getModeloResponse() {
        return modeloResponse;
    }

    public String getLinkDocumentacao() {
        return linkDocumentacao;
    }


}
