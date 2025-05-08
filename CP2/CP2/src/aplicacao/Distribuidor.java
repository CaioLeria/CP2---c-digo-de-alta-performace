package aplicacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import entidades.Encomenda;
import entidades.Produto;
import filas.*;


public class Distribuidor {
	public static Scanner le = new Scanner(System.in);

	public static void main(String[] args) {

		// Declara e inicia a filaEncomendas da classe FilaEncomenda
		//com a fila de encomendas armazenadas no arquivo ListaEncomendas.txt
		//usa a funcao ja implemetada a seguir para ler o arquivo e enfileirar as encomendas
		FilaEncomendas filaEncomendas = new FilaEncomendas();

		filaEncomendas.init();
		geraFilaEncomendas(filaEncomendas);


		 int opcao;
		String novoProduto = "sim";
		do {
			System.out.println("0 - Encerrar atendimento");
			System.out.println("1 - Inserir encomenda na fila para aguarda atendimento");
			System.out.println("2 - Atender uma encomenda");
			System.out.println("Opcao: ");
			opcao = le.nextInt();
			switch (opcao) {
			case 0:
				break;
			case 1:
				/*
				 * Le do teclado uma encomenda: apenas ID do cliente e nome do arquivo com lista de produtos encomendados.
				 * A encomenda lida deve ser enfileirada na filaEncomendas.
				 */
				Encomenda encomenda = new Encomenda();
				System.out.println("Digite o id do cliente: ");
				encomenda.clienteID = le.next();
				System.out.println("Digite o nome do arquivo com a lista de produtos: ");
				encomenda.nomeArquivo = "CP2/src/arquivos/"+le.next()+".txt";
				filaEncomendas.enqueue(encomenda);




				break;
			case 2:
				/*
				 * Retira da filaEncomendas uma encomenda.
				 * Atendimento e� iniciado lendo o arquivo txt que contem a lista de produtos da encomenda.
				 * Cada produto lido do teclado deve ser armazenado na filaProdutos para que o robo possa busca-los na 
				 * ordem.
				 * Caso o produto nao esteja disponivel na prateleira o produto volta para o final da filaProdutos.
				 * 
				 */
				if(!filaEncomendas.isEmpty()) {
					FilaProdutos filaProdutos = new FilaProdutos();
					Encomenda e = filaEncomendas.dequeue();
					double totalCompra = 0;

					System.out.println("Atendimento do pedido do cliente: " + e.clienteID + " esta iniciando.");
					geraFilaProdutos(filaProdutos, e.nomeArquivo);
					while (!filaProdutos.isEmpty()) {
						for (int i = 0; i < filaProdutos.cont; i++) {

							Produto p = filaProdutos.dequeue();
							System.out.println("Produto: [ codigo = " + p.codigo + ", nome = " + p.descricao
									+ ", preco" + p.preco + ", localização = " + p.localizacao + " ]");
							System.out.println("O produto foi encontrado na prateleira? ");
							int encontrado = le.nextInt();
							if (encontrado == 1) {
								totalCompra += filaProdutos.dados[i].preco;

							} else {
								System.out.println("Voltar depois para colocar no carrinho");
								filaProdutos.enqueue(p);
							}
						}
					}
					if(totalCompra!=0){
						System.out.println("Atendimento da encomenda foi finalizada com sucesso" + "\n\t Valor total da compra: R$" + totalCompra);
					}
				} else {
					System.out.println("Não há encomendas na fila");
				}
				break;
			default:
				System.out.println("Opcao Invalida");
			}

		} while (opcao != 0);

		le.close();

	}

	public static void geraFilaEncomendas(FilaEncomendas fila) {

		String caminhoDoArquivo = "CP2/CP2/src/arquivos/ListaEncomendas.txt";
		
		try {
			// Criar um objeto File com o caminho do arquivo
			File arquivo = new File(caminhoDoArquivo);

			// Criar um Scanner para ler o arquivo
			Scanner leArq = new Scanner(arquivo);

			// Loop para ler linha por linha at� o final do arquivo
			while (leArq.hasNextLine()) {
				// Ler a pr�xima linha
				String linha = leArq.nextLine();
				String[] partes = linha.split(",");
				Encomenda obj = new Encomenda();
				obj.clienteID = partes[0];
				obj.nomeArquivo = partes[1];
				//System.out.println(obj);
				//System.out.println();
				fila.enqueue(obj);
			}
			// Fechar o objeto da classe Scanner le
			leArq.close();
		} catch (FileNotFoundException e) {
			// Caso o arquivo n�o seja encontrado
			System.out.println("Arquivo n�o encontrado: " + e.getMessage());
		}

	}

	public static void geraFilaProdutos(FilaProdutos filaProd, String nomeArquivo) {
		/*Implementar esse metodo*/
		String caminhoDoArquivo = "CP2/CP2/src/arquivos/"+nomeArquivo;

		try {
			// Criar um objeto File com o caminho do arquivo
			File arquivo = new File(caminhoDoArquivo);

			// Criar um Scanner para ler o arquivo
			Scanner leArq = new Scanner(arquivo);

			// Loop para ler linha por linha at� o final do arquivo
			while (leArq.hasNextLine()) {

				// Ler a pr�xima linha
				String linha = leArq.nextLine();
				String[] partes = linha.split(",");
				Produto obj = new Produto();
				obj.codigo = partes[0];
				obj.descricao = partes[1];
				obj.preco = Double.parseDouble(partes[2]);
				//String preco = Double.toString(obj.preco);
				//preco = partes[2];
				obj.localizacao = partes[3];
				filaProd.enqueue(obj);
			}
			// Fechar o objeto da classe Scanner le
			leArq.close();
		} catch (FileNotFoundException e) {
			// Caso o arquivo n�o seja encontrado
			System.out.println("Arquivo n�o encontrado: " + e.getMessage());
		}
	}
}
