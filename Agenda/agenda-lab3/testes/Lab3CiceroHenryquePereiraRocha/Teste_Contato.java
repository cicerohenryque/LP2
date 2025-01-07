package Lab3CiceroHenryquePereiraRocha;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
import agenda.Contato;


public class Teste_Contato {
	@Test
	public void test_contato() {
		Contato contato = new Contato( "Cicero", "Henryque", "(83) 99946-9605");
		assertEquals("Cicero Henryque\n(83) 99946-9605", contato.toString());
	}
}
