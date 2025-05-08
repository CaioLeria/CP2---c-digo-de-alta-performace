package filas;

import entidades.Produto;

public class FilaProdutos {

        public final int N = 3;
        public Produto dados[] = new Produto[N];
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
        public void enqueue(Produto produto) {
            if (isFull())
                System.out.println("Fila cheia!");
            else {
                dados[fim] = produto;
                cont++;
                fim = (fim + 1) % N;
            }
        }
        public Produto dequeue() {
            Produto produto = dados[ini];
            cont--;
            ini = (ini + 1) % N;
            return produto;
        }
        public Produto first() {
            return dados[ini];
        }
}
