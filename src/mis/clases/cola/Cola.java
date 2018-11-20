package mis.clases.cola;

public interface Cola<E> {
    void encolar(E x);
    E desencolar();
    E frenteC(); // método consultar
    boolean colaVacia();
    boolean colaLlena();
}
