package documento;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DocumentoControllerTest {
	private DocumentoController controller;
	
	@BeforeEach
	void prepareDocumentoControllerTest() {
		this.controller = new DocumentoController();
	}
	
	@Test
	void addDocumentoTest() {
		assertTrue(controller.addDocumento("Titulo"));
	}
	
	@Test
	void addDocumentoJaExistenteTest() {
		assertTrue(controller.addDocumento("Titulo"));
		assertFalse(controller.addDocumento("Titulo"));
	}
	
	@Test
	void addDocumentoVazioTest() {
		try {
			controller.addDocumento("");
			fail("Deveria quebrar ao passar um titulo vazio");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void addDocumentoCompostoPorEspacoTest() {
		try {
			controller.addDocumento("     ");
			fail("Deveria quebrar ao passar um titulo composto por espaços");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	public void addDocumentoComTamanhoMaximoTest() {
		assertTrue(controller.addDocumento("Titulo", 2));
	}
	
	@Test
	void addDocumentoComTamanhoMaximoVazioTest() {
		try {
			controller.addDocumento("", 2);
			fail("Deveria quebrar ao passar um titulo vazio");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void addDocumentoComTamanhoMaximoCompostoPorEspacoTest() {
		try {
			controller.addDocumento("     ", 2);
			fail("Deveria quebrar ao passar um titulo composto por espaços");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	public void addDocumentoComTamanhoMaximoInvalidoTest() {
		try {
			controller.addDocumento("Titulo", 0);
			fail("Deveria quebrar ao passar um tamanho maximo inválido");
		} catch (IllegalArgumentException iae) {
			assertEquals("Tamanho inválido", iae.getMessage());
		}
		
		try {
			controller.addDocumento("Titulo", -10);
			fail("Deveria quebrar ao passar um tamanho maximo inválido");
		} catch (IllegalArgumentException iae) {
			assertEquals("Tamanho inválido", iae.getMessage());
		}
	}
	
	@Test
	public void removerDocumentoTest() {
		controller.addDocumento("Titulo1", 1);
		
		controller.removerDocumento("Titulo1");
		try {
			controller.acessaDocumento("Titulo1");
			fail("Não deveria encontrar um documento que foi apagado");
		} catch (NoSuchElementException nsee) {
			assertEquals("Elemento não encontrado", nsee.getMessage());
		}
	}
	
	@Test
	public void removerDocumentoInexistenteTest() {
		try {
			controller.removerDocumento("Titulo1");
			fail("Não deveria conseguir excluir um documento que não existe");
		} catch (NoSuchElementException nsee) {
			assertEquals("Elemento não encontrado", nsee.getMessage());
		}
	}
	
	@Test
	void removerDocumentoVazioTest() {
		try {
			controller.removerDocumento("");
			fail("Deveria quebrar ao passar um titulo vazio");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void removerDocumentoCompostoPorEspacoTest() {
		try {
			controller.removerDocumento("     ");
			fail("Deveria quebrar ao passar um titulo composto por espaços");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void getQtdeElementosTest() {
		controller.addDocumento("Titulo1");
		assertEquals(0, controller.getQtdeElementos("Titulo1"));
		controller.addTextoDocumento("Titulo1", "Valor", 1);
		assertEquals(1, controller.getQtdeElementos("Titulo1"));
	}
	
	@Test
	void getQtdeElementosTituloVazioTest() {
		try {
			controller.getQtdeElementos("");
			fail("Deveria quebrar ao passar um titulo vazio");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void getQtdeElementosTituloCompostoPorEspacoTest() {
		try {
			controller.getQtdeElementos("     ");
			fail("Deveria quebrar ao passar um titulo composto por espaços");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void getQtdeElementosTituloInexistenteTest() {
		try {
			controller.getQtdeElementos("Titulo1");
			fail("Deveria quebrar ao passar um titulo de um documento inexistente");
		} catch (NoSuchElementException nsee) {
			assertEquals("Elemento não encontrado", nsee.getMessage());
		}
	}
	
	@Test
	void exibirDocumentoTest() {
		controller.addDocumento("Titulo1");
		assertEquals(0, controller.exibirDocumento("Titulo1").length);
		
		controller.addTextoDocumento("Titulo1", "Valor", 1);
		assertEquals("Valor", controller.exibirDocumento("Titulo1")[0]);
	}
	
	@Test
	void exibirDocumentoVazioTest() {
		try {
			controller.exibirDocumento("");
			fail("Deveria quebrar ao passar um titulo vazio");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void exibirDocumentoCompostoPorEspacoTest() {
		try {
			controller.exibirDocumento("     ");
			fail("Deveria quebrar ao passar um titulo composto por espaços");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void exibirDocumentoInexistenteTest() {
		try {
			controller.exibirDocumento("Titulo1");
			fail("Deveria quebrar ao passar um titulo de um documento inexistente");
		} catch (NoSuchElementException nsee) {
			assertEquals("Elemento não encontrado", nsee.getMessage());
		}
	}
	
	@Test
	void acessaDocumentoTest() {
		controller.addDocumento("Titulo1");
		
		Documento documentoComparado = new Documento("Titulo1");
		
		assertTrue(documentoComparado.equals(controller.acessaDocumento("Titulo1")));
	}
	
	@Test
	void acessaDocumentoVazioTest() {
		try {
			controller.acessaDocumento("");
			fail("Deveria quebrar ao passar um titulo vazio");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void getDocumentoCompostoPorEspacoTest() {
		try {
			controller.acessaDocumento("     ");
			fail("Deveria quebrar ao passar um titulo composto por espaços");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void getDocumentoInexistenteTest() {
		try {
			controller.acessaDocumento("Titulo1");
			fail("Deveria quebrar ao passar um titulo de um documento inexistente");
		} catch (NoSuchElementException nsee) {
			assertEquals("Elemento não encontrado", nsee.getMessage());
		}
	}
	
	@Test
	void addAtalhoTest() {
		controller.addDocumento("Titulo1", 1);
		controller.addDocumento("Titulo2", 2);
		
		assertEquals(1, controller.addAtalho("Titulo1", "Titulo2"));
	}
	
	@Test
	void addAtalhoTamanhoLimiteAtingidoTest() {
		controller.addDocumento("Titulo1", 1);
		
		controller.addTextoDocumento("Titulo1", "Valor", 1);
		controller.addDocumento("Titulo2", 2);
		
		try {
			controller.addAtalho("Titulo1", "Titulo2");
			fail("Deveria quebrar pois não há espaço para o atalho nos elementos do documento");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Tamanho limite atingido", iobe.getMessage());
		}
	}
	
	@Test
	void addAtalhoDocumentoPossuiAtalhoTest() {
		controller.addDocumento("Titulo1", 1);
		controller.addDocumento("Titulo2", 2);
		controller.addDocumento("Titulo3", 2);
		
		assertEquals(1, controller.addAtalho("Titulo1", "Titulo2"));
		
		try {
			controller.addAtalho("Titulo1", "Titulo3");
			fail("Deveria quebrar ao tentar adicionar um atalho a um documento que ja possui atalho");
		} catch (IllegalStateException ise) {
			assertEquals("Documento possui atalho", ise.getMessage());
		}
	}
	
	@Test
	void addAtalhoDocumentoVazioTest() {
		try {
			controller.addAtalho("", "Titulo");
			fail("Deveria quebrar ao passar um titulo vazio");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void addAtalhoDocumentoCompostoPorEspacoTest() {
		try {
			controller.addAtalho("     ", "Titulo");
			fail("Deveria quebrar ao passar um titulo composto por espaços");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void addAtalhoDocumentoInexistenteTest() {
		controller.addDocumento("Titulo2");
		try {
			controller.addAtalho("Titulo1", "Titulo2");
			fail("Deveria quebrar ao passar um titulo de um documento inexistente");
		} catch (NoSuchElementException nsee) {
			assertEquals("Elemento não encontrado", nsee.getMessage());
		}
	}
	
	@Test
	void addAtalhoDocumentoReferenciadoVazioTest() {
		try {
			controller.addAtalho("Titulo", "");
			fail("Deveria quebrar ao passar um titulo vazio");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void addAtalhoDocumentoReferenciadoCompostoPorEspacoTest() {
		try {
			controller.addAtalho("Titulo", "     ");
			fail("Deveria quebrar ao passar um titulo composto por espaços");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
	
	@Test
	void addAtalhoDocumentoReferenciadoInexistenteTest() {
		controller.addDocumento("Titulo1");
		try {
			controller.addAtalho("Titulo1", "Titulo2");
			fail("Deveria quebrar ao passar um titulo de um documento inexistente");
		} catch (NoSuchElementException nsee) {
			assertEquals("Elemento não encontrado", nsee.getMessage());
		}
	}
	
	@Test
	void acessaDocumentoTituloVazioTest() {
		try {
			controller.acessaDocumento("");
			fail("Deveria quebrar ao passar um titulo vazio");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}

	@Test
	void acessaDocumentoTituloCompostPorEspacosTest() {
		try {
			controller.acessaDocumento("     ");
			fail("Deveria quebrar ao passar um titulo vazio");
		} catch (IllegalArgumentException iae) {
			assertEquals("Título vazio", iae.getMessage());
		}
	}
}