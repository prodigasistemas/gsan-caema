package gcom.arrecadacao.pagamento;

import gcom.util.Util;

import java.util.Comparator;

public class ComparatorPagamentos implements Comparator<TipoPagamentoPortalHelper> {

	public int compare(TipoPagamentoPortalHelper p1, TipoPagamentoPortalHelper p2) {
		String anoMes1 = String.valueOf(Util.formatarMesAnoComBarraParaAnoMes(p1.getReferenciaConta()));
		String anoMes2 = String.valueOf(Util.formatarMesAnoComBarraParaAnoMes(p2.getReferenciaConta()));
		
		return anoMes2.compareTo(anoMes1);
	}
}
