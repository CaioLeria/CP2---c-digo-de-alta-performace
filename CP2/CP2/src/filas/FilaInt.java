package filas;

public class FilaInt {

	public final int N = 10;
	int dados[] = new int[N];
	int ini, fim, cont;

	public void init() {
		ini = fim = cont = 0;
	}
	public boolean isEmpty() {
		return (cont == 0);
	}
	public boolean isFull() {
		return (cont == N);
	}
	public void enqueue(int valor) {
		if (isFull())
			System.out.println("Fila cheia!");
		else {
			dados[fim] = valor;
			cont++;
			fim = (fim + 1) % N;
		}
	}
	public int dequeue() {
		int valor = dados[ini];
		cont--;
		ini = (ini + 1) % N;
		return valor;
	}
	public int first() {
		return dados[ini];
	}

}
