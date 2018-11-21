package mis.clases.listaE;

import conexionBD.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.JOptionPane;
import mis.clases.Tienda;

public class ListaLEG <E extends Tienda>{
    private NodoLEG<E> primero;
    private int talla;        // saber el numero de nodos
    private Conectar conectar;
    
    public ListaLEG() {
        this.primero=null;
        this.talla=0;
        this.conectar = new Conectar();
    }

    public NodoLEG<E> getPrimero() {
        return primero;
    }
    public int getTalla() {
        return talla;
    }
     //reglas de negocio u operaciones
    public void insertarAlInicio(E x){
        NodoLEG<E> nuevo=new NodoLEG<>(x);
        
        nuevo.setSiguiente(primero);
        primero=nuevo;
        talla++;       
    }
    
    public void insertarAlFinal(E x){
        NodoLEG<E> nuevo=new NodoLEG<>(x);
        NodoLEG<E> aux=primero;
        
        if(primero==null){
            primero=nuevo;
        }else{
            while(aux.getSiguiente()!=null){
                aux=aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        talla++;       
    }
    
    public String recorrido(){
        String cad="";
        
        NodoLEG<E> aux=primero;
        while(aux!=null){
            cad+=aux.getDato().toString()+"\n";
            aux=aux.getSiguiente();
        }        
        return cad;
    }
    
    //método que elimina el primer nodo
    public void eliminarAlInicio(){        
        if(primero.getSiguiente()==null){
            primero=null;
        }else{
            primero=primero.getSiguiente();
        }
        talla--;
    }
    
    //método que eliminar al final de la lista
    public void eliminarAlFinal(){        
        if(primero.getSiguiente()==null){
            primero=null;
        }else{
            NodoLEG<E> aux=primero,ant=null;
            while(aux.getSiguiente()!=null){
                ant=aux;
                aux=aux.getSiguiente();
            }
            ant.setSiguiente(null);
        }
        talla--;
    }
    
    //La empresa ha decidido eliminar una tienda con código de sucursal CODS. Si la
    //sucursal no existe muestre un mensaje de error al respecto. Escriba el código para
    //este método.
    public void eliminarDato(int cod){
        NodoLEG<E> aux=primero, ant=null;
        
        while(aux!=null && aux.getDato().getCodSucursal()!=cod){
            ant=aux;
            aux=aux.getSiguiente();
        }
        
        if(aux!=null){
            if(aux==primero){
                eliminarAlInicio();
            }else if(aux.getSiguiente()==null){
                eliminarAlFinal();
            }else{
                ant.setSiguiente(aux.getSiguiente());
                talla--;
            }
            
            
        }else{
            JOptionPane.showMessageDialog(null, "No existe dato a eliminar");
        }       
    }
    
    //método que inserte en forma ordenada de menor
    // mayor o ascendentemente  (o alfabeticamente)
    public void insertarOrdenado(E x){
        NodoLEG<E> nuevo=new NodoLEG<E>(x);
        NodoLEG<E> aux, ant=null;
        
        if(primero==null){
            primero=nuevo;
            talla++;
        }else{
            aux=primero;
            while(aux!=null && aux.getDato().getCodSucursal()<x.getCodSucursal()){
                ant=aux;
                aux=aux.getSiguiente();                
            }
            if(aux!=null){
                if(aux.getDato().getCodSucursal()==x.getCodSucursal()){
                    JOptionPane.showMessageDialog(null, "Objeto ya existe ...");
                }else{
                    if(aux==primero){
                        insertarAlInicio(x);
                    }else{
                        ant.setSiguiente(nuevo);
                        nuevo.setSiguiente(aux);
                        talla++;
                    }
                }
            }else{
                insertarAlFinal(x);
            }
        }
        
        
    }
    
    
    
    //Método que seleccione aleatoriamente una tienda y muestre la información de todas
    //las cajas que se encuentran habilitadas.
    
    public String infoDeTiendaAleatoria(){
        String info = "";
        if(this.talla>0){
            Random ran = new Random();
            int num = ran.nextInt(this.talla);
            int conta = 0;
            
            NodoLEG<E> aux = this.getPrimero();

            
            while(conta != num){
                aux=aux.getSiguiente();
                conta += 1;
            }
            
            info = aux.getDato().toString();
            
        }
        
        
        
        return info;
    }
    
    
    //Método que muestra la recaudación total en cada una de las tiendas del
    //supermercado.
    
    public String mostrarRecaudadoEnSucursales(){
        String mensaje = "";
        int conta = 0;
        NodoLEG<E> aux = this.getPrimero();
        
        while(conta != this.talla){
            mensaje = mensaje+aux.getDato().toString()+"\n";
            aux = aux.getSiguiente();
            conta++;
        }
        return mensaje;
    }
    
    //funcion que retorna boolean si el codifo de tiendao sucursal existe
    
    public boolean existeSucursal(int id){
        NodoLEG<E> aux=primero;
        boolean existe=false;
        
        while(aux!=null && !existe){
            if(aux.getDato().getCodSucursal() == id){
                existe = true;
            }
            aux=aux.getSiguiente();
        }
        return existe;
    }
    
    //obtener una tienda por id
    public Tienda getTiendabyId(int id){
        Tienda t = null;
        NodoLEG<E> aux=primero;
        boolean existe=false;
        
        while(aux!=null && !existe){
            if(aux.getDato().getCodSucursal() == id){
                t = aux.getDato();
            }
            aux=aux.getSiguiente();
        }
        
        return t;
    }
    
    
    
}
