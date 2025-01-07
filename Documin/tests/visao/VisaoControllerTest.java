package visao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import documento.DocumentoController;

class VisaoControllerTest {
	DocumentoController documentoController;
	VisaoController visaoController;
	
	@BeforeEach
	void prepareDocumentoEVisaoController() {
		this.documentoController = new DocumentoController();
		documentoController.addDocumento("Titulo");
		this.visaoController = new VisaoController();
		documentoController.addTextoDocumento("Titulo", "Valor", 4);
		documentoController.addTituloDocumento("Titulo", "Valor", 3, 1, true);
		documentoController.addListaDocumento("Titulo", "Valor/Separado", 2, "/", "-");
		documentoController.addTermosDocumento("Titulo", "Valor", 1, "/", "NENHUM");
	}
	
	@Test
    void criarVisaoCompletaTest() {
    	assertEquals(1, visaoController.criarVisaoCompleta("Titulo", documentoController));
    }
    
	@Test
    void criaVisaoResumidaTest() {
		assertEquals(1, visaoController.criarVisaoResumida("Titulo", documentoController));  	
    }
    
	@Test
    void criarVisaoPrioritariaTest() {
		assertEquals(1, visaoController.criarVisaoPrioritaria("Titulo", 1, documentoController));
    }
    
	@Test
    void criarVisaoTituloTest() {
		assertEquals(1, visaoController.criarVisaoTitulo("Titulo", documentoController));
    }
	
	//Esse teste funciona para os outros tipos de visão
	@Test
    void exibirVisaoIdInvalidoTest() {
		visaoController.criarVisaoCompleta("Titulo", documentoController);

		try {
			visaoController.exibirVisao(0);
			fail("Deveria quebrar ao passar um Id inválido");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Id inválido",iobe.getMessage());
		}
		
		try {
			visaoController.exibirVisao(2);
			fail("Deveria quebrar ao passar um Id inválido");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("Id inválido",iobe.getMessage());
		}
    }
    
	@Test
	void exibirVisaoCompletaVaziaTest() {
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		
		visaoController.criarVisaoCompleta("Titulo", documentoController);
		
		assertTrue(visaoController.exibirVisao(1).length == 0);
	}
	
	@Test
    void exibirVisaoCompletaTest() {
		visaoController.criarVisaoCompleta("Titulo", documentoController);

		assertEquals("Valor", visaoController.exibirVisao(1)[0]);
		assertEquals("1. Valor -- VALOR", visaoController.exibirVisao(1)[1]);
		assertEquals("- Valor\n- Separado", visaoController.exibirVisao(1)[2]);
		assertEquals("Total de Termos: 1\n- Valor", visaoController.exibirVisao(1)[3]);
    }
	
	@Test
	void exibirVisaoResumidaVaziaTest() {
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		
		visaoController.criarVisaoResumida("Titulo", documentoController);
		
		assertTrue(visaoController.exibirVisao(1).length == 0);
	}
    
	@Test
    void exibirVisaoResumidaTest() {
		visaoController.criarVisaoResumida("Titulo", documentoController);
		
		assertEquals("Valor", visaoController.exibirVisao(1)[0]);
		assertEquals("1. Valor", visaoController.exibirVisao(1)[1]);
		assertEquals("Valor/Separado", visaoController.exibirVisao(1)[2]);
		assertEquals("Valor", visaoController.exibirVisao(1)[3]);
    }
	
	@Test
	void exibirVisaoPrioritariaVaziaTest() {
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		
		visaoController.criarVisaoPrioritaria("Titulo", 1, documentoController);
		
		assertTrue(visaoController.exibirVisao(1).length == 0);
	}
	
	@Test
	void exibirVisaoPrioritariaComPrioridadeAltaTest() {
		visaoController.criarVisaoPrioritaria("Titulo", 5, documentoController);
		
		assertTrue(visaoController.exibirVisao(1).length == 0);
	}
    
	@Test
    void exibirVisaoPrioritariaComPrioridadeMediaTest() {
		visaoController.criarVisaoPrioritaria("Titulo", 3, documentoController);

		assertEquals("Valor", visaoController.exibirVisao(1)[0]);
		assertEquals("1. Valor", visaoController.exibirVisao(1)[1]);
    }

	@Test
    void exibirVisaoPrioritariaComPrioridadeBaixaTest() {
		visaoController.criarVisaoPrioritaria("Titulo", 1, documentoController);

		assertEquals("Valor", visaoController.exibirVisao(1)[0]);
		assertEquals("1. Valor", visaoController.exibirVisao(1)[1]);
		assertEquals("Valor/Separado", visaoController.exibirVisao(1)[2]);
		assertEquals("Valor", visaoController.exibirVisao(1)[3]);
    }
	
	@Test
	void exibirVisaoTituloVaziaTest() {
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		documentoController.removeElementoDocumento("Titulo", 1);
		
		visaoController.criarVisaoTitulo("Titulo", documentoController);
		
		assertTrue(visaoController.exibirVisao(1).length == 0);
	}
	
	@Test
	void exibirVisaoTituloSemTitulosTest() {
		documentoController.removeElementoDocumento("Titulo", 2);
		
		visaoController.criarVisaoTitulo("Titulo", documentoController);
		
		assertTrue(visaoController.exibirVisao(1).length == 0);
	}
	
	@Test
    void exibirVisaoTituloTest() {
		documentoController.addTituloDocumento("Titulo", "Valor2", 3, 2, false);
		visaoController.criarVisaoTitulo("Titulo", documentoController);
		
		assertEquals("1. Valor", visaoController.exibirVisao(1)[0]);
		assertEquals("2. Valor2", visaoController.exibirVisao(1)[1]);
	}
	
}