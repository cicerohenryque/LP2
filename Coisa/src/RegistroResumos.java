import java.util.ArrayList;
import java.util.Collections;

public class RegistroResumos {

    private int posicao = 0;

    private String[] temas;

    private String[] temasEresumos;

    public RegistroResumos(int quantidadeResumos) {
        this.temas = new String[quantidadeResumos];
        this.temasEresumos = new String[quantidadeResumos];
    }

    private void verificaLimiteArray() {
        if (this.posicao == this.temas.length) {
            this.posicao = 0;
        }
    }

    private void contadorPosicao() {
        this.posicao += 1;
    }

    public void adiciona(String tema, String resumo) {
        verificaLimiteArray();
        this.temas[this.posicao] = tema;
        this.temasEresumos[this.posicao] = tema + ": " + resumo;
        contadorPosicao();
    }

    public String[] pegaResumos() {
        return this.temasEresumos;
    }

    public int conta() {
        int quantidadeValores = 0;
        for(int i = 0; i <= this.temas.length-1; i++) {
            if (temas[i] == null) {
                break;
            } else {
                quantidadeValores++;
            }
        }
        return quantidadeValores;
    }

    public String imprimeResumos() {
        String temas = "";
        for (int i = 0; i <= conta()-1; i++) {
            if(i < conta()-1) {
                temas += this.temas[i] + " | ";
            } else {
                temas += this.temas[i];
            }
        }
        return ("- " + conta() + " resumo(s) cadastrado(s)" + "\n" + "- " + temas);
    }

    public boolean temResumo(String tema) {
        for (int i = 0; i <= conta()-1; i++) {
            if(temas[i].toLowerCase().equals(tema.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> busca(String chave) {
        ArrayList<String> encontrados = new ArrayList<String>();
        for(int i = 0; i <= conta()-1; i++) {
            String resumo = temasEresumos[i];
            String[] separado = resumo.split(" ");

            for(int j = 0; j <= separado.length-1; j++) {
                String palavra = separado[j].toLowerCase();
                if( j > 0 && palavra.equals(chave.toLowerCase())) {
                    encontrados.add(temas[i]);
                }
            }
        }
        Collections.sort(encontrados);
        return encontrados;
    }
}