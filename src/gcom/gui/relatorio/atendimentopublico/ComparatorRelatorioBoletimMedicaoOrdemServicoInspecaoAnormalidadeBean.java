package gcom.gui.relatorio.atendimentopublico;

import java.util.Comparator;

public class ComparatorRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean
		implements Comparator<GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean> {

	public int compare(
			GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean o1,
			GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean o2) {
		
		if(o1.getNomeGerencia().compareTo(o2.getNomeGerencia()) > 0){
			return +1;
		}else if(o1.getNomeGerencia().compareTo(o2.getNomeGerencia()) < 0){
			return -1;
		}else{
			return 0;
		}
	}

}
