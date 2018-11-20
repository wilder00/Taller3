package mis.clases.cola;

import javax.swing.JOptionPane;

public class ArrayCola<E> implements Cola<E>{
    private static final int TAMAÑO=5;
    private E []arC;
    private int frentec,finalc;

    public ArrayCola() {
        arC=(E[])new Object[TAMAÑO];
        frentec=-1;
        finalc=-1;
    }
    //reglas de negocio
    public boolean colaVacia(){
        return (frentec==-1 && finalc==-1);
    }
    public boolean colaLlena(){
        return ((frentec==0 && finalc==TAMAÑO-1) || frentec==finalc+1);
    }
    public void encolar(E x){        
        if(colaLlena()==false){
            if(colaVacia()==true){
                frentec=0;
            }
            if(finalc==TAMAÑO-1){
                    finalc=0;
             }else{
                    finalc++;
             }
            arC[finalc]=x;
          }else{
            JOptionPane.showMessageDialog(null, "Cola llena ...");        
        }    
    }
    
    public E desencolar(){
        E elemSacado=arC[frentec];
        
        if(frentec==finalc){
            frentec=-1;
            finalc=-1;
        }else{
            if(frentec==TAMAÑO-1){
                frentec=0;
            }else{
                frentec++;
            }
        }       
        return elemSacado;
    }
    
    public E frenteC(){
        return arC[frentec];
    }
    
    //ver contenido de la cola sin modificarla
    public String verColaDeDatos(){
        ArrayCola<E> cAux=new ArrayCola<E>();
        String cad="";
        
        while(colaVacia()==false){
            E x=desencolar();
            cad=cad+x.toString()+"\t";
            cAux.encolar(x);
        }        
        while(cAux.colaVacia()==false){
            encolar(cAux.desencolar());
        }        
        return cad;
    }
    
}
