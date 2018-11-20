package mis.clases.pila;

import javax.swing.JOptionPane;

public class ArrayPila <E> implements Pila<E>{
    private static final int TAMAÑO=10;
    private E[]arP;
    private int tope;

    public ArrayPila() {
        arP=(E[])new Object[TAMAÑO];
        tope=-1;
    }
    
    public boolean pilaVacia(){
        return (tope==-1);
    }
    
    public boolean pilaLlena(){
        return (tope==TAMAÑO-1);
    }
    public void apilar(E x ){
        if(pilaLlena()==false){
            tope++;
            arP[tope]=x;
        }else{
            JOptionPane.showMessageDialog(null, "Pila llena...");
        }
    }
    public E desapilar(){
        E elemSacado=arP[tope];
        tope--;
        return elemSacado;
    }
    
    public E tope(){
        return arP[tope];
    }    
    
    // mostrar el contenido de la Pila
    // sin modificarla
    public String verPilaDeDatos(){
        String cad="";
        
        ArrayPila<E> pAux=new ArrayPila<E>();        
        while(pilaVacia()==false){
            E x= desapilar();
            cad=cad+x.toString()+"\n";
            pAux.apilar(x);
        }
        while(pAux.pilaVacia()==false){
            apilar(pAux.desapilar());
        }               
        return cad;
    }
    
}
