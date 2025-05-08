package filas;

import entidades.Encomenda;
import entidades.Produto;

public class FilaEncomendas {
    public final int N = 10;
    public Encomenda []pedido = new Encomenda[N];
   public int ini, fim, cont;

    public void init() {
        ini = fim = cont = 0;
    }
    public boolean isEmpty() {
        return (cont == 0);
    }
    public boolean isFull() {
        return (cont == N);
    }
    public void enqueue(Encomenda e) {
        if (isFull())
            System.out.println("Fila cheia!");
        else {
            pedido[fim] = e;
            cont++;
            fim = (fim + 1) % N;
        }
    }
    public Encomenda dequeue() {
        Encomenda valor = pedido[ini];
        cont--;
        ini = (ini + 1) % N;
        return valor;
    }
    public Encomenda first() {
        return pedido[ini];
    }

}
