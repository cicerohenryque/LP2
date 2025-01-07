package Testes;

import MrBet.SistemaMrBet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class TestesSistemaMrBet {
    @BeforeEach
    public void limpaDados() {
        SistemaMrBet.limparClubes();
        SistemaMrBet.limpaCampeonatos();
        SistemaMrBet.limpaApostas();
    }

    @Test
    void criarTimeComSucesso() {
        String criou = SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        assertEquals("INCLUSÃO REALIZADA!", criou);
    }

    @Test
    void criarTimeQueJaExiste() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        String criou = SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        assertEquals("TIME JÁ EXISTE!", criou);
    }

    @Test
    void criarTimeRepetido() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        String criou = SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");

        assertEquals("TIME JÁ EXISTE!", criou);
    }

    @Test
    void recuperaTimeExistente() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        String timeRecuperado = SistemaMrBet.recuperaTime("FLA_123");

        assertEquals("[FLA_123] Flamengo / Urubu", timeRecuperado);
    }

    @Test
    void recuperaTimeInexistente() {
        String timeRecuperado = SistemaMrBet.recuperaTime("codigoInexistente");

        assertEquals("TIME NÃO EXISTE!", timeRecuperado);
    }

    @Test
    void recuperaTimeNulo() {
        String timeRecuperado = SistemaMrBet.recuperaTime(null);

        assertEquals("TIME NÃO EXISTE!", timeRecuperado);
    }

    @Test
    void cadastraTimeExistenteEmCampeonatoExistente() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        SistemaMrBet.criarCampeonato("Brasileirao", 10);
        String time = SistemaMrBet.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");

        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", time);
    }

    @Test
    void cadastraTimeInexistenteEmCampeonatoExistente() {
        SistemaMrBet.criarCampeonato("Brasileirao", 10);
        String time = SistemaMrBet.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");

        assertEquals("TIME NÃO EXISTE!", time);
    }

    @Test
    void cadastraTimeExistenteEmCampeonatoInexistente() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        String time = SistemaMrBet.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");

        assertEquals("CAMPEONATO NÃO EXISTE!", time);
    }

    @Test
    void cadastraTimeRepetidoEmCampeonatoExistente() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        SistemaMrBet.criarCampeonato("Brasileirao", 10);
        SistemaMrBet.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");
        String time = SistemaMrBet.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");


        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", time);
    }

    @Test
    void cadastraTimeEmCampeonatoCheio() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        SistemaMrBet.criarTime("COR_456", "Corinthians", "Gavião");
        SistemaMrBet.criarTime("PAL_789", "Palmeiras", "Porco");
        SistemaMrBet.criarCampeonato("Brasileirao", 2);
        SistemaMrBet.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");
        SistemaMrBet.cadastraTimeEmCampeonato("Brasileirao", "COR_456");
        String cadastroFalhou = SistemaMrBet.cadastraTimeEmCampeonato("Brasileirao", "PAL_789");


        assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", cadastroFalhou);
    }

    @Test
    void criarCampeonatoInexistente() {
        String campeonatoCriado = SistemaMrBet.criarCampeonato("Brasileirão", 10);

        assertEquals("CAMPEONATO ADICIONADO!", campeonatoCriado);
    }

    @Test
    void criarCampeonatoDuplicado() {
        SistemaMrBet.criarCampeonato("Brasileirão", 10);
        String campeonatoCriado = SistemaMrBet.criarCampeonato("Brasileirão", 10);

        assertEquals("CAMPEONATO JÁ EXISTE!", campeonatoCriado);
    }

    @Test
    void exibeCampeonatosDeTimeExistente() {
        SistemaMrBet.criarCampeonato("Brasileirão", 10);
        SistemaMrBet.criarCampeonato("Libertadores", 10);
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        SistemaMrBet.cadastraTimeEmCampeonato("Brasileirão", "FLA_123");
        SistemaMrBet.cadastraTimeEmCampeonato("Libertadores", "FLA_123");

        String campeonatosInscritos = SistemaMrBet.exibeCampeonatos("FLA_123");
        assertEquals("Campeonatos do Flamengo:\n" + "* Brasileirão - 1/10\n" + "* Libertadores - 1/10"
                , campeonatosInscritos);
    }

    @Test
    void exibeCampeonatosDeTimeInexistente() {
        SistemaMrBet.criarCampeonato("Brasileirão", 10);
        SistemaMrBet.criarCampeonato("Libertadores", 10);

        String campeonatosInscritos = SistemaMrBet.exibeCampeonatos("FLA_123");
        assertEquals("O TIME NÃO EXISTE!", campeonatosInscritos);

    }

    @Test
    void verificaTimeInexistenteEmCampeonatoExistente() {
        SistemaMrBet.criarCampeonato("Brasileirão", 10);
        SistemaMrBet.cadastraTimeEmCampeonato("Brasileirão", "FLA_123");
        String verificaTime = SistemaMrBet.verificaTimeEmCampeonato("Brasileirão", "FLA_123");

        assertEquals("O TIME NÃO EXISTE!", verificaTime);
    }

    @Test
    void verificaTimeCadastradoEmCampeonato() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        SistemaMrBet.criarCampeonato("Brasileirão", 10);
        SistemaMrBet.cadastraTimeEmCampeonato("Brasileirão", "FLA_123");
        String verificaTime = SistemaMrBet.verificaTimeEmCampeonato("Brasileirão", "FLA_123");

        assertEquals("O TIME ESTÁ NO CAMPEONATO!", verificaTime);
    }

    @Test
    void verificaTimeNaoCadastradoEmCampeonato() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        SistemaMrBet.criarCampeonato("Brasileirão", 10);
        String verificaTime = SistemaMrBet.verificaTimeEmCampeonato("Brasileirão", "FLA_123");

        assertEquals("O TIME NÃO ESTÁ NO CAMPEONATO", verificaTime);
    }

    @Test
    void registraApostaTimeInexistente() {
        SistemaMrBet.criarCampeonato("Brasileirão", 10);
        String registraAposta = SistemaMrBet.registraAposta("FLA_123", "Brasileirão",
                1, "22,34");

        assertEquals("TIME NÃO EXISTE!", registraAposta);
    }

    @Test
    void registraApostaCampeonatoInexistente() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        String registraAposta = SistemaMrBet.registraAposta("FLA_123", "Brasileirão",
                1, "22,34");

        assertEquals("CAMPEONATO NÃO EXISTE!", registraAposta);
    }

    @Test
    void registraApostaEmPosicaoInexistente() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        SistemaMrBet.criarCampeonato("Brasileirão", 10);
        String registraAposta = SistemaMrBet.registraAposta("FLA_123", "Brasileirão",
                11, "22,34");

        assertEquals("APOSTA NÃO REGISTRADA!", registraAposta);
    }

    @Test
    void registraApostaComSucesso() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        SistemaMrBet.criarCampeonato("Brasileirão", 10);
        String registraAposta = SistemaMrBet.registraAposta("FLA_123", "Brasileirão",
                1, "22,34");

        assertEquals("APOSTA REGISTRADA", registraAposta);
    }

    @Test
    void imprimeApostas() {
        SistemaMrBet.criarTime("FLA_123", "Flamengo", "Urubu");
        SistemaMrBet.criarTime("COR_123", "Corinthians", "Gavião");
        SistemaMrBet.criarTime("PAL_123", "Palmeiras", "Porco");
        SistemaMrBet.criarCampeonato("Brasileirão", 10);
        SistemaMrBet.registraAposta("FLA_123", "Brasileirão",
                1, "22,34");
        SistemaMrBet.registraAposta("COR_123", "Brasileirão",
                2, "123");
        SistemaMrBet.registraAposta("PAL_123", "Brasileirão",
                3, "15.32");

        String apostas = SistemaMrBet.imprimeApostas();

        assertEquals("1. [FLA_123] Flamengo / Urubu\n" + "Brasileirão 1 / 10\n" +  "R$ 22.34\n" +
                "2. [COR_123] Corinthians / Gavião\n" + "Brasileirão 2 / 10\n" + "R$ 123.0\n" +
                "3. [PAL_123] Palmeiras / Porco\n" + "Brasileirão 3 / 10\n" +  "R$ 15.32\n", apostas);
    }
}
