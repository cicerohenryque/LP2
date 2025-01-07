package documento;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elemento.Lista;
import elemento.Termos;
import elemento.Texto;
import elemento.Titulo;

class DocumentoTest {
	private Documento documento;
	
	@BeforeEach
	void prepareDocumento() {
		documento = new Documento("Titulo", 4);
	}
	
	@Test
	void prepareDocumentoComTamanhoIndefinido() {
		Documento documentoTamanhoIndefinido = new Documento("Titulo1");
		assertEquals(-1, documentoTamanhoIndefinido.getTamanho());
	}
	
	@Test
	void getQtdeElementosTest() {
		assertEquals(0, documento.getQtdeElementos());
		documento.addTexto("Valor", 1);
		assertEquals(1, documento.getQtdeElementos());
	}
	
	@Test
	void getDocumentoTest() {
		documento.addTexto("Valor", 1);
		assertEquals("Valor", documento.getDocumento()[0]);
	}
	
	@Test
	void getDocumentoVazioTest() {
		assertTrue(documento.getDocumento().length == 0);
	}
	
	@Test
	void addTextoTest() {
		assertEquals(1,documento.addTexto("Valor", 1));
	}

	@Test
	void addTextoSemEspacoTest() {
		documento.addTexto("Valor", 1);
		documento.addTitulo("Valor", 1, 1, true);
		documento.addLista("Valor", 1, "/", "-");
		documento.addTermos("Valor", 1, "/", "NENHUM");
		
		try {
			documento.addTexto("Valor2", 2);
			fail("Deveria quebrar ao atingir o limite de elementos possiveis no documento");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Tamanho limite atingido", iobe.getMessage());
		}
	}
	
	@Test
	void addTituloTest() {
		assertEquals(1,documento.addTitulo("Valor", 1, 1, true));
	}

	@Test
	void addTituloNivelInvalidoTest() {
		try {
			assertEquals(1,documento.addTitulo("Valor", 1, 0, true));
			fail("Deveria quebrar ao adicionar um titulo com nível inválido");
		} catch (IllegalArgumentException iae) {
			assertEquals("Nível inválido", iae.getMessage());
		}
		
		try {
			assertEquals(1,documento.addTitulo("Valor", 1, 6, true));
			fail("Deveria quebrar ao adicionar um titulo com nível inválido");
		} catch (IllegalArgumentException iae) {
			assertEquals("Nível inválido", iae.getMessage());
		}
	}
	
	@Test
	void addTituloSemEspacoTest() {
		documento.addTexto("Valor", 1);
		documento.addTitulo("Valor", 1, 1, true);
		documento.addLista("Valor", 1, "/", "-");
		documento.addTermos("Valor", 1, "/", "NENHUM");
		
		try {
			documento.addTitulo("Valor2", 1, 1, true);
			fail("Deveria quebrar ao atingir o limite de elementos possiveis no documento");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Tamanho limite atingido", iobe.getMessage());
		}
	}
	
	@Test
	void addListaTest() {
		assertEquals(1,documento.addLista("Valor", 1, "/", "-"));
	}

	@Test
	void addListaSemEspacoTest() {
		documento.addTexto("Valor", 1);
		documento.addTitulo("Valor", 1, 1, true);
		documento.addLista("Valor", 1, "/", "-");
		documento.addTermos("Valor", 1, "/", "NENHUM");
		
		try {
			documento.addLista("Valor2", 1, "/", "-");
			fail("Deveria quebrar ao atingir o limite de elementos possiveis no documento");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Tamanho limite atingido", iobe.getMessage());
		}
	}
	
	@Test
	void addTermosTest() {
		assertEquals(1,documento.addTermos("Valor", 1, "/", "NENHUM"));
	}
	
	@Test
	void addTermosOrdemInexistenteTest() {
		try {
			documento.addTermos("Valor", 1, "/", "OrdemMassa");
			fail("Não deveria adicionar um termo com ordem inválida");
		} catch (IllegalArgumentException iae) {
			assertEquals("Ordem não existe", iae.getMessage());
		}
	}
	
	@Test
	void addTermosSemEspacoTest() {
		documento.addTexto("Valor", 1);
		documento.addTitulo("Valor", 1, 1, true);
		documento.addLista("Valor", 1, "/", "-");
		documento.addTermos("Valor", 1, "/", "NENHUM");
		
		try {
			documento.addTermos("Valor2", 1, "/", "NENHUM");
			fail("Deveria quebrar ao atingir o limite de elementos possiveis no documento");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Tamanho limite atingido", iobe.getMessage());
		}
	}
	
	@Test
	void getRepresentacaoCompletaTextoTest() {
		documento.addTexto("Valor", 1);
	
		assertEquals("Valor",documento.getRepresentacaoCompleta(1));
	}
	
	@Test
	void getRepresentacaoResumidaTextoTest() {
		documento.addTexto("Valor", 1);
		
		assertEquals("Valor", documento.getRepresentacaoResumida(1));
	}
	
	@Test
	void getRepresentacaoCompletaTituloTest() {
		documento.addTitulo("Valor1", 1, 1, true);
		
		assertEquals("1. Valor1 -- VALOR1", documento.getRepresentacaoCompleta(1));
	}
	
	@Test
	void getRepresentacaoResumidaTituloTest() {
		documento.addTitulo("Valor1", 1, 1, true);
		
		assertEquals("1. Valor1", documento.getRepresentacaoResumida(1));
	}
	
	@Test
	void getRepresentacaoCompletaListaTest() {
		documento.addLista("Valor/Separado", 1, "/", "-");

		assertEquals("- Valor\n- Separado", documento.getRepresentacaoCompleta(1));
	}
	
	@Test
	void getRepresentacaoResumidaListaTest() {
		documento.addLista("Valor/Separado", 1, "/", "-");

		assertEquals("Valor/Separado", documento.getRepresentacaoResumida(1));
	}
	
	@Test
	void getRepresentacaoCompletaTermosNenhumaOrdenacaoTest() {
		documento.addTermos("Casa/Bolo/Amor", 1, "/", "NENHUM");
		
		assertEquals("Total de Termos: 3\n- Casa, Bolo, Amor", documento.getRepresentacaoCompleta(1));
	}
	
	@Test
	void getRepresentacaoResumidaTermosNenhumaOrdenacaoTest() {
		documento.addTermos("Casa/Bolo/Amor", 1, "/", "NENHUM");
		
		assertEquals("Casa / Bolo / Amor", documento.getRepresentacaoResumida(1));
	}
	
	@Test
	void getRepresentacaoCompletaTermosOrdemAlfabeticaTest() {
		 documento.addTermos("Casa/Bolo/Amor", 1, "/", "ALFABETICA");
		
		assertEquals("Total de Termos: 3\n- Amor, Bolo, Casa", documento.getRepresentacaoCompleta(1));
	}
	
	@Test
	void getRepresentacaoResumidaTermosOrdemAlfabeticaTest() {
		documento.addTermos("Casa/Bolo/Amor", 1, "/", "ALFABETICA");
		
		assertEquals("Amor / Bolo / Casa", documento.getRepresentacaoResumida(1));
	}
	
	@Test
	void getRepresentacaoCompletaTermosOrdemTamanhoTest() {
		documento.addTermos("Casarao/Bolo Grande/Amor", 1, "/", "TAMANHO");

		assertEquals("Total de Termos: 3\n- Bolo Grande, Casarao, Amor", documento.getRepresentacaoCompleta(1));
	}
	
	@Test
	void getRepresentacaoResumidaTermosOrdemTamanhoTest() {
		documento.addTermos("Casarao/Bolo Grande/Amor", 1, "/", "TAMANHO");
		
		assertEquals("Bolo Grande / Casarao / Amor", documento.getRepresentacaoResumida(1));
	}
	
	@Test
	void elevaElementoTest() {
		documento.addTexto("Valor1", 1);
		documento.addTexto("Valor2", 2);
		
		Texto textoComparado1 = new Texto(1, "Valor1");
		Texto textoComparado2 = new Texto(2, "Valor2");

		assertTrue(textoComparado1.exibirResumido().equals(documento.getElemento(1).exibirResumido()));
		assertTrue(textoComparado2.exibirResumido().equals(documento.getElemento(2).exibirResumido()));
		
		documento.elevaElemento(2);
		
		assertTrue(textoComparado1.exibirResumido().equals(documento.getElemento(2).exibirResumido()));
		assertTrue(textoComparado2.exibirResumido().equals(documento.getElemento(1).exibirResumido()));
	}
	
	@Test
	void elevaElementoPosicaoInvalidaTest() {
		try {
			documento.elevaElemento(0);
			fail("Deveria quebrar ao passar uma posicao inválida");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Posição inválida", iobe.getMessage());
		}
		
		documento.addTexto("Valor1", 1);
		documento.addTexto("Valor2", 2);
		
		try {
			documento.elevaElemento(3);
			fail("Deveria quebrar ao passar uma posicao inválida");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Posição inválida", iobe.getMessage());
		}
	}
	
	@Test
	void elevaElementoNaPosicaoMaisAltaTest() {
		documento.addTexto("Valor1", 1);
		documento.addTexto("Valor2", 2);
		
		Texto textoComparado1 = new Texto(1, "Valor1");
		Texto textoComparado2 = new Texto(2, "Valor2");

		assertTrue(textoComparado1.exibirResumido().equals(documento.getElemento(1).exibirResumido()));
		assertTrue(textoComparado2.exibirResumido().equals(documento.getElemento(2).exibirResumido()));
		
		documento.elevaElemento(1);
		
		assertTrue(textoComparado1.exibirResumido().equals(documento.getElemento(1).exibirResumido()));
		assertTrue(textoComparado2.exibirResumido().equals(documento.getElemento(2).exibirResumido()));
	}
	
	@Test
	void cedeElementoTest() {
		documento.addTexto("Valor1", 1);
		documento.addTexto("Valor2", 2);
		
		Texto textoComparado1 = new Texto(1, "Valor1");
		Texto textoComparado2 = new Texto(2, "Valor2");

		assertTrue(textoComparado1.exibirResumido().equals(documento.getElemento(1).exibirResumido()));
		assertTrue(textoComparado2.exibirResumido().equals(documento.getElemento(2).exibirResumido()));
		
		documento.cedeElemento(1);
		
		assertTrue(textoComparado1.exibirResumido().equals(documento.getElemento(2).exibirResumido()));
		assertTrue(textoComparado2.exibirResumido().equals(documento.getElemento(1).exibirResumido()));
	}
	
	@Test
	void cedeElementoPosicaoInvalidaTest() {
		try {
			documento.cedeElemento(0);
			fail("Deveria quebrar ao passar uma posicao inválida");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Posição inválida", iobe.getMessage());
		}
		
		documento.addTexto("Valor1", 1);
		documento.addTexto("Valor2", 2);
		
		try {
			documento.cedeElemento(3);
			fail("Deveria quebrar ao passar uma posicao inválida");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Posição inválida", iobe.getMessage());
		}
	}
	
	@Test
	void cedeElementoNaPosicaoMaisBaixaTest() {
		documento.addTexto("Valor1", 1);
		documento.addTexto("Valor2", 2);
		
		Texto textoComparado1 = new Texto(1, "Valor1");
		Texto textoComparado2 = new Texto(2, "Valor2");

		assertTrue(textoComparado1.exibirResumido().equals(documento.getElemento(1).exibirResumido()));
		assertTrue(textoComparado2.exibirResumido().equals(documento.getElemento(2).exibirResumido()));
		
		documento.cedeElemento(2);
		
		assertTrue(textoComparado1.exibirResumido().equals(documento.getElemento(1).exibirResumido()));
		assertTrue(textoComparado2.exibirResumido().equals(documento.getElemento(2).exibirResumido()));
	}
	
	@Test
	void getElementoTest() {
		documento.addTexto("Valor", 1);
		documento.addTitulo("Valor", 1, 1, true);
		documento.addLista("Valor", 1, "/", "-");
		documento.addTermos("Valor", 1, "/", "NENHUM");
		
		Texto textoComparado = new Texto(1, "Valor");
		
		HashMap<String, String> propriedadesTitulo = new HashMap<>();
		propriedadesTitulo.put("nivel", "1");
		propriedadesTitulo.put("linkável", "true");
		
		Titulo tituloComparado = new Titulo(1, "Valor", propriedadesTitulo);
		
		HashMap<String, String> propriedadesLista = new HashMap<>();
		propriedadesLista.put("separador", "/");
		propriedadesLista.put("charLista", "-");
		
		Lista listaComparado = new Lista(1, "Valor", propriedadesLista);
		
		HashMap<String, String> propriedadesTermo = new HashMap<>();
		propriedadesTermo.put("separador", "/");
		propriedadesTermo.put("ordem", "NENHUM");
		
		Termos termoComparado = new Termos(1, "Valor", propriedadesTermo);
		
		assertTrue(textoComparado.exibirResumido().equals(documento.getElemento(1).exibirResumido()));
		assertTrue(tituloComparado.exibirResumido().equals(documento.getElemento(2).exibirResumido()));
		assertTrue(listaComparado.exibirResumido().equals(documento.getElemento(3).exibirResumido()));
		assertTrue(termoComparado.exibirResumido().equals(documento.getElemento(4).exibirResumido()));
	}
	
	
	@Test
	void getElementoPosicaoInvalidaTest() {
		try {
			documento.getElemento(0);
			fail("Deveria quebrar ao passar uma posição inválida");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Posição inválida", iobe.getMessage());
		}
		
		documento.addTexto("Valor", 1);
		
		try {
			documento.getElemento(2);
			fail("Deveria quebrar ao passar uma posição inválida");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Posição inválida", iobe.getMessage());
		}
	}
	
	@Test
	void getElementosTest() {
		documento.addTexto("Valor", 1);
		documento.addTitulo("Valor", 1, 1, true);
		documento.addLista("Valor", 1, "/", "-");
		documento.addTermos("Valor", 1, "/", "NENHUM");
		
		Texto textoComparado = new Texto(1, "Valor");
		
		HashMap<String, String> propriedadesTitulo = new HashMap<>();
		propriedadesTitulo.put("nivel", "1");
		propriedadesTitulo.put("linkável", "true");
		
		Titulo tituloComparado = new Titulo(1, "Valor", propriedadesTitulo);
		
		HashMap<String, String> propriedadesLista = new HashMap<>();
		propriedadesLista.put("separador", "/");
		propriedadesLista.put("charLista", "-");
		
		Lista listaComparado = new Lista(1, "Valor", propriedadesLista);
		
		HashMap<String, String> propriedadesTermo = new HashMap<>();
		propriedadesTermo.put("separador", "/");
		propriedadesTermo.put("ordem", "NENHUM");
		
		Termos termoComparado = new Termos(1, "Valor", propriedadesTermo);
		
		assertTrue(textoComparado.exibirResumido().equals(documento.getElementos().get(0).exibirResumido()));
		assertTrue(tituloComparado.exibirResumido().equals(documento.getElementos().get(1).exibirResumido()));
		assertTrue(listaComparado.exibirResumido().equals(documento.getElementos().get(2).exibirResumido()));
		assertTrue(termoComparado.exibirResumido().equals(documento.getElementos().get(3).exibirResumido()));
	}
	
	@Test
	void getElementosVazioTest() {
		assertTrue(documento.getElementos().isEmpty());
	}
	
	@Test
	void removeElementoTest() {
		documento.addTexto("Valor", 1);
		
		documento.removeElemento(1);
		try {
			documento.getElemento(1);
			fail("Não deveria encontrar um documento que foi apagado");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Posição inválida", iobe.getMessage());
		}
	}
	
	@Test
	void removeElementoPosicaoInvalidaTest() {
		assertFalse(documento.removeElemento(0));
		assertFalse(documento.removeElemento(1));
	}
	
	@Test
	void removeElementoDandoShiftNasPosicoesTest() {
		documento.addTexto("Valor1", 1);
		documento.addTexto("Valor2", 2);
		
		Texto textoComparado = new Texto(2, "Valor2");
		
		documento.removeElemento(1);
		assertTrue(textoComparado.exibirResumido().equals(documento.getElemento(1).exibirResumido()));
	}
	
	//outros testes do add atalho estão especificados no DocumentoControllerTest
	@Test
	void addAtalhoDocumentoSemEspacoTest() {
		documento.addTexto("Valor1", 1);
		documento.addTexto("Valor2", 1);
		documento.addTexto("Valor3", 1);
		documento.addTexto("Valor4", 1);
		
		try {
			documento.addAtalho(1, "Valor", new HashMap<String, String>());
			fail("Deveria quebrar se passar mais elementos do que o documento suporta");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Tamanho limite atingido", iobe.getMessage());
		}
	}
}
