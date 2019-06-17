package br.com.opet.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitorArquivo {
	
	private ArrayList<String> lerArquivoSpider(String arquivo) {
		InputStream inputStream = getClass().getResourceAsStream(arquivo);
		ArrayList<String> linhasLidas = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String novaLinha = "";
			while ((novaLinha = reader.readLine()) != null) {
				linhasLidas.add(novaLinha);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linhasLidas;
	}
	
	public ArrayList<SpiderReader> formatarArquivoSpider(String arquivo) {
		ArrayList<SpiderReader> spiders = new ArrayList<SpiderReader>();
		ArrayList<String> linhasLidas = lerArquivoSpider(arquivo);
		
		for (String string : linhasLidas) {
			spiders.add(formatarLinhasArquivo(string));
		}
		return spiders;
	}
	
	private SpiderReader formatarLinhasArquivo(String linha) {
		if (linha == null || linha.length() <= 0) {
			return null;
		} else {
			List<String> linhas = splitLinhas(linha.trim());
			return formatarLinhas(linhas);
		}
	}
	
	private List<String> splitLinhas(String linha) {
		return Arrays.asList(linha.split("\\s*#\\s*"));
	}

	private SpiderReader formatarLinhas(List<String> linhas) {
		SpiderReader spider = new SpiderReader(linhas.get(0), linhas.get(1), linhas.get(2));
		return spider;
	}
}
