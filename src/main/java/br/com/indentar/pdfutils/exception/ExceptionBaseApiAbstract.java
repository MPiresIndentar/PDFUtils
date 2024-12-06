/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.indentar.pdfutils.exception;

/**
 *
 * @author indentar
 */
public abstract class ExceptionBaseApiAbstract extends Exception {

    protected Object request;
    protected Object response;
    protected int codeErro;
    protected Object modeloRequest;
    protected Object modeloResponse;
    protected String linkDocumentacao;

    public ExceptionBaseApiAbstract(Object request, Object response, int codeErro, Object modeloRequest, Object modeloResponse, String linkDocumentacao, String string, Throwable thrwbl) {
        super(string, thrwbl);
        this.request = request;
        this.response = response;
        this.codeErro = codeErro;
        this.modeloRequest = modeloRequest;
        this.modeloResponse = modeloResponse;
        this.linkDocumentacao = linkDocumentacao;
    }
    
}
