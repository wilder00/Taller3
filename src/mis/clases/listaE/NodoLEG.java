package mis.clases.listaE;

import mis.clases.Tienda;

public class NodoLEG <E>{
    private E dato;
    private NodoLEG<E> siguiente;

    public NodoLEG(E dato) {
        this.dato = dato;
        this.siguiente=null;
    }

    public E getDato() {
        return dato;
    }
    public void setDato(E dato) {
        this.dato = dato;
    }
    public NodoLEG<E> getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(NodoLEG<E> siguiente) {
        this.siguiente = siguiente;
    }   
}
