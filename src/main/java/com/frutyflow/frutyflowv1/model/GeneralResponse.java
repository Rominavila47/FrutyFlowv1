package com.frutyflow.frutyflowv1.model;

public class GeneralResponse {
    private int codigo;
    private String mensaje;

    public int getCodigo (){
        return codigo;
    }

    public void setCodigo (int codigo){
        this.codigo = codigo;
    }

    public String getMensaje (){
        return mensaje;
    }

    public void setMensaje (String mensaje){
        this.mensaje = mensaje;
    }
}
