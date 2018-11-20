/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis.clases;

/**
 *
 * @author Wilder
 */
public class Cliente {
    private String dni;
    private float montoAPagar;

    public Cliente(String dni, float montoAPagar) {
        this.dni = dni;
        this.montoAPagar = montoAPagar;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public float getMontoAPagar() {
        return montoAPagar;
    }

    public void setMontoAPagar(float montoAPagar) {
        this.montoAPagar = montoAPagar;
    }
    
    public String toString(){
        return "\t\tD.N.I. : "+ this.dni+"\n"
                + "\t\tMonto a pagar: "+ this.montoAPagar+"\n";
    }
    
}
