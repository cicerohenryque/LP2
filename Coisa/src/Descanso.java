public class Descanso {

    private int horas;

    private String emoji = "";

    private int semanas;

    private String condicao = "cansado";

    public Descanso() {
    }

    public void defineHorasDescanso(int horas) {
        this.horas = horas;
        String condicaoAntes = this.condicao;
        verificaEstado();
        if (!this.condicao.equals(condicaoAntes)) {
            this.emoji = "";
        }
    }

    public void defineNumeroSemanas(int semanas) {
        this.semanas = semanas;
        String condicaoAntes = this.condicao;
        verificaEstado();

        if(!(this.condicao.equals(condicaoAntes))) {
            this.emoji = "";
        }
    }

    private void verificaEstado() {
        if(horas >= 26 && semanas == 1) {
            this.condicao = "descansado";

        } else {
            this.condicao = "cansado";
        }
    }

    public void definirEmoji(String emoji) {
        this.emoji = " - ";
        this.emoji += emoji;
    }

    public String getStatusGeral() {
        return this.condicao + this.emoji;
    }
}