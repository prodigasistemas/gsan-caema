package gcom.util;

import java.util.AbstractList;
import java.util.List;


/**
 * Classe com métodos úteis para manipular uma Collection
 *
 *
 *
 * @author Hugo Amorim
 * @data 14/10/2010
 */
public class CollectionUtil {

	/*
	 * Utilizar como exemplos:
	 *
	 * - atualizarParmsOS no RepositorioCobrancaHBM
	 *
	 * - pesquisarServicoTipoPorRA no RepositorioOrdemServicoHBM
	 */
	public static <T> List<List<T>> particao(List<T> list, int size) {

		if (list == null)
			throw new NullPointerException("Lista precisa esta preenchida");
		if (!(size > 0))
			throw new IllegalArgumentException("Tamanho retorno precisa ser maior que 0");

		return new Particao<T>(list, size);
	}

	/**
	 *
	 * Método aonde quebra a colecao para incluir na
	 * clausula IN com mais de 1000 registros
	 *
	 * Exemplo: select coluna from table1 where ID in (1,2,3,4,...,1000) or
	 *	ID in (1001,1002,...,2000)
	 *
	 * @author Rafael Pinto
	 * @since 08/10/2012
	 *
	 * @param List<Integer> colecao  colecao a ser quebrada
	 * @param String id o nome da coluna que será recebido o IN
	 * @return String
	 */
	public static String quebrarColecaoOR(List<Integer> colecao,String id){

		StringBuffer retorno = new StringBuffer("");

		List<List<Integer>> particoes = particao( (List<Integer>) colecao, 999);

		int qtdQuebras = 999;
		int indice = colecao.size() / qtdQuebras;
		if (colecao.size() % qtdQuebras != 0) {
			indice++;
		}

		for (int i = 0; i < indice; i++) {

			if(i == 0){
				retorno.append(id+" IN( ");
			}else{
				retorno.append("OR "+id+" IN( ");
			}


			List<Integer> valores = particoes.get(i);

			for (int j = 0; j < (Integer) valores.toArray().length; j++) {
				Integer valor = (Integer) valores.toArray()[j];
				if(j != 0){
					retorno.append(",");
				}
				retorno.append(valor);
			}

			retorno.append(" ) ");

		}

		return retorno.toString();
	}

	private static class Particao<T> extends AbstractList<List<T>> {

		final List<T> list;

		final int size;

		Particao(List<T> list, int size) {
			this.list = list;
			this.size = size;
		}

		@Override
		public List<T> get(int index) {
			int listSize = size();
			if (listSize < 0)
				throw new IllegalArgumentException("Tamanho negativo: " + listSize);
			if (index < 0)
				throw new IndexOutOfBoundsException("Index " + index
						+ " precisa ser não negativo");
			if (index >= listSize)
				throw new IndexOutOfBoundsException("Index " + index
						+ " precisa ser menor que " + listSize);
			int start = index * size;
			int end = Math.min(start + size, list.size());
			return list.subList(start, end);
		}

		@Override
		public int size() {
			return (list.size() + size - 1) / size;
		}

		@Override
		public boolean isEmpty() {
			return list.isEmpty();
		}
	}

}
