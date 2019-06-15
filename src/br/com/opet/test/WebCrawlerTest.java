package br.com.opet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.opet.util.SpiderReader;

class WebCrawlerTest {	
	
	private SpiderReader spider1;
	private SpiderReader spider2;
	private ArrayList<SpiderReader> spiders;
	
	private void assertSpider(String nomeSpider, String dimensoesSpider, String precoSpider, SpiderReader spiderArquivo) {
		assertEquals(nomeSpider, spiderArquivo.getNome());
		assertEquals(dimensoesSpider, spiderArquivo.getDimensoes());
		assertEquals(precoSpider, spiderArquivo.getPreco());
	}
	
	@BeforeEach
	void setUp() {	
		spider1 = new SpiderReader("Colchão Ortobom de Molas Pocket Freedom Pillow Top ViscoelásticoCasal", 
				"1,38x1,88x0,32", "R$ 1.389,85");
		spider2 = new SpiderReader("Colchão Ortobom Ortopédico Light OrtoPillow INMETROCasal", 
				"1,38x1,88x0,24", "R$ 550,05");
		spiders = spider1.lerArquivoSpider("dadosWebCrawler.txt");
	}
	
	@Test
	void testeSpiderSimples() {
		assertSpider(spider1.getNome(), spider1.getDimensoes(), spider1.getPreco(), spiders.get(0));
	}
	
	@Test
	void testeSpiderMultiplo() {
		assertSpider(spider1.getNome(), spider1.getDimensoes(), spider1.getPreco(), spiders.get(0));
		assertSpider(spider2.getNome(), spider2.getDimensoes(), spider2.getPreco(), spiders.get(1));
	}
	
	@Test
	void testTotalDeLinhasArquivo() {
		assertEquals(37, spiders.size());
	}
}
