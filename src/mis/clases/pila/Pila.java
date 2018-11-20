package mis.clases.pila;

public interface Pila<E> {
    void apilar(E x);
    E desapilar();
    E tope();
    boolean pilaVacia();
    boolean pilaLlena();
}
