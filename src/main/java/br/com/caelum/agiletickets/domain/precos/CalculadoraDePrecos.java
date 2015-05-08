package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		BigDecimal valorAcrescentado = new BigDecimal(0.0);
		if (sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA)
				|| sessao.getEspetaculo().getTipo()
						.equals(TipoDeEspetaculo.SHOW)) {
			// quando estiver acabando os ingressos...
			if (sessao.getPorcentualIngressosDisponiveis() <= 0.05) {
				valorAcrescentado = sessao.valorAcrescentado(0.10);
			}
		} else if (sessao.getEspetaculo().getTipo()
				.equals(TipoDeEspetaculo.BALLET)) {
			if (sessao.getPorcentualIngressosDisponiveis() <= 0.50) {
				valorAcrescentado = sessao.valorAcrescentado(0.20);
			}

			if (sessao.getDuracaoEmMinutos() > 60) {
				valorAcrescentado = valorAcrescentado.add(sessao
						.valorAcrescentado(0.10));
			}
		} else if (sessao.getEspetaculo().getTipo()
				.equals(TipoDeEspetaculo.ORQUESTRA)) {
			if (sessao.getPorcentualIngressosDisponiveis() <= 0.50) {
				valorAcrescentado = sessao.valorAcrescentado(0.20);
			}
			if (sessao.getDuracaoEmMinutos() > 60) {
				valorAcrescentado = valorAcrescentado.add(sessao
						.valorAcrescentado(0.10));
			}
		}
		preco = sessao.getPreco().add(valorAcrescentado);
		return preco.multiply(BigDecimal.valueOf(quantidade));

	}

}