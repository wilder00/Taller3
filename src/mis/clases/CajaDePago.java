/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis.clases;

import javax.swing.JOptionPane;
import mis.clases.cola.ArrayCola;

/**
 *
 * @author Wilder
 */
public class CajaDePago {
    private ArrayCola<Cliente> clientes;
    private float montoRecaudadoEnCaja;

    //Constructor sin parámetro (aquí la cola está vacía y el monto recaudado en cero), y
    //los métodos getters.
    public CajaDePago() {
        this.clientes = new ArrayCola<Cliente>();
        this.montoRecaudadoEnCaja = 0f;
    }

    public ArrayCola<Cliente> getClientes() {
        return clientes;
    }

    public float getMontoRecaudadoEnCaja() {
        return montoRecaudadoEnCaja;
    }
    public void setMontoRecaudadoEnCaja(float f){
        this.montoRecaudadoEnCaja = f;
    }
    public boolean seRepiteDni(String dni){
        boolean repite = false;
        ArrayCola<Cliente> auxC = new ArrayCola<Cliente>();
        Cliente temp = null;
        while(!this.clientes.colaVacia()){//se tiene que desencolar todo para volver a encolarlo despues
            temp = this.clientes.desencolar();
            if(temp.getDni().equalsIgnoreCase(dni)){
                repite = true; //una vez que se sepa que repite no cambiará
            }
            auxC.encolar(temp);
        }
        
        while(!auxC.colaVacia()){
            this.clientes.encolar(auxC.desencolar());
        }
        
        return repite;
        
    }
    
    
    //Método que adicione un cliente a la cola, verifique que el dni no se repita.
    public void agregarCliente(Cliente c){
        
        
        boolean repite = this.seRepiteDni(c.getDni());
        
        if(!repite){
            this.clientes.encolar(c);
        }else{
            JOptionPane.showMessageDialog(null, "No se ha registrado cliente porque el dni se Repite");
        }
        
        
    }
    
        //Método que retorne la cola de clientes, sin modificar la información original.
        public ArrayCola<Cliente> colaDeClientes(){
            ArrayCola<Cliente> nuevo = new ArrayCola<>();
            ArrayCola<Cliente> auxC = new ArrayCola<>();
            Cliente temp;

            while(!this.clientes.colaVacia()){
                auxC.encolar(this.clientes.desencolar());
            }

            while(!auxC.colaVacia()){
                temp = auxC.desencolar();
                this.clientes.encolar(temp);
                nuevo.encolar(temp);
            }
            
            return nuevo;
        }
        
        
        //Método que calcule el monto total recaudado por la caja, aquí la cola queda vacía
        
        public void calcularMontoRecaudado(){
            float monto=0f;
            Cliente temp;
            
            while(!this.clientes.colaVacia()){
                monto = this.clientes.desencolar().getMontoAPagar() + monto;
                
            }
            this.montoRecaudadoEnCaja = this.montoRecaudadoEnCaja+monto;
            Tienda.montoTotalRecaudado = Tienda.montoTotalRecaudado+this.montoRecaudadoEnCaja;
        }
    
    
}
