package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	@Test
	public void criaSessaoSeComecaeTerminaHoje() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2015,5,11), new LocalDate(2015,5,11), new LocalTime(20,0,0), Periodicidade.DIARIA);
		Assert.assertEquals(1, sessoes.size());
	}
	@Test
	public void cria3SessoesSeTerminaDepoisdeAmanha() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2015,5,11), new LocalDate(2015,5,13), new LocalTime(20,0,0), Periodicidade.DIARIA);
		Assert.assertEquals(3, sessoes.size());
	}
	@Test
	public void criaSessaoSemanalSeComecaHojeETerminaAmanha() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2015,5,11), new LocalDate(2015,5,12), new LocalTime(20,0,0), Periodicidade.SEMANAL);
		Assert.assertEquals(1, sessoes.size());
	}
	@Test
	public void criaSessaoSemanalSeComecaHojeEmSeteDias() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2015,5,11), new LocalDate(2015,5,20), new LocalTime(20,0,0), Periodicidade.SEMANAL);
		Assert.assertEquals(2, sessoes.size());
	}
	@Test
	public void cria3SessoesSemanaisSeComecaHojeEm14Dias() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2015,5,11), new LocalDate(2015,5,30), new LocalTime(20,0,0), Periodicidade.SEMANAL);
		Assert.assertEquals(3, sessoes.size());
	}
}
