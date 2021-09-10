package estructural;

import java.io.Serializable;

public class CuentasAzure implements Serializable{

    private Number idCuenta;
    private String correo;
    private String contrasenia;
    private String paisCreacion;

    public CuentasAzure(Number idCuenta, String correo, String contrasenia, String paisCreacion) {
        this.idCuenta = idCuenta;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.paisCreacion = paisCreacion;
    }

    public CuentasAzure(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Number getIdCuenta() {
        return idCuenta;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getPaisCreacion() {
        return paisCreacion;
    }

    public void setIdCuenta(Number idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setPaisCreacion(String paisCreacion) {
        this.paisCreacion = paisCreacion;
    }

    @Override
    public String toString() {
        return "CuentasAzure{" + "idCuenta=" + idCuenta + ", correo=" + correo + ", contrasenia=" + contrasenia + ", paisCreacion=" + paisCreacion + '}';
    }



}