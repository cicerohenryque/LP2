public class RegistroTempoOnline {

    private String disciplina;

    private int horasNecessaria = 120;

    private int horasEstudadas;

    public RegistroTempoOnline(String disciplina, int horas) {
        this.disciplina = disciplina;
        this.horasNecessaria = horas;
    }

    public RegistroTempoOnline(String disciplina) {
        this.disciplina = disciplina;
    }

    public void adicionaTempoOnline(int horas) {
        this.horasEstudadas += horas;
    }

    public boolean atingiuMetaTempoOnline() {
        if(this.horasEstudadas >= this.horasNecessaria) {
            return true;

        } else {
            return false;
        }
    }

    public String toString() {
        return this.disciplina + " " + this.horasEstudadas + "/" + this.horasNecessaria;
    }
}