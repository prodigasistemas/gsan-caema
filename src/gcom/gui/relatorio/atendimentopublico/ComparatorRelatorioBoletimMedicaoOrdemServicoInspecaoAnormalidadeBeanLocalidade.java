package gcom.gui.relatorio.atendimentopublico;

import java.util.Comparator;

public class ComparatorRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBeanLocalidade
		implements Comparator<GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean> {

	public int compare(
			GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean o1,
			GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean o2) {
		
		if(o1.getNomeGerencia().equals(o2.getNomeGerencia())){
			if(o1.getNomeLocalidade().compareTo(o2.getNomeLocalidade()) > 0){
				return +1;
			}else if(o1.getNomeLocalidade().compareTo(o2.getNomeLocalidade()) < 0){
				return -1;
			}else{
				return 0;
			}
		} else {
			return 0;
		}
	}

}
