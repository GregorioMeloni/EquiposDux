
package com.dux.pruebatecnica.exceptions;

public class ErrorResponse {
    private int codigo;
    private String mensaje;

    public ErrorResponse(String mensaje, int codigo) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
