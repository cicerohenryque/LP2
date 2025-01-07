import java.util.*;

public class Disciplina {

    private String disciplina;

    private int horasEstudo;

    private double[] notas = new double[4];

    private int numNotas;

    private int[] pesoNotas;

    public Disciplina(String disciplina, int numNotas, int[] pesoNotas) {
        this.disciplina = disciplina;
        this.numNotas = numNotas;
        this.pesoNotas = pesoNotas;
        this.notas = new double[numNotas];
        this.pesoNotas = pesoNotas;
    }

    public Disciplina(String disciplina, int numNotas) {
        this.numNotas = numNotas;
        this.disciplina = disciplina;
        this.notas = new double[numNotas];
        this.pesoNotas = new int[] {};
    }

    public Disciplina(String disciplina) {
        this.disciplina = disciplina;

    }

    public double calculaMediaPonderada() {
        int somaDeNotas = 0;
        int somaDePeso = 0;
        if (this.pesoNotas.length == 0) {
            return calculaMedia();
        }
        for(int i = 0; i < this.numNotas; i++) {
            somaDeNotas += this.pesoNotas[i] * this.notas[i];
            somaDePeso += this.pesoNotas[i];
        }
        return (somaDeNotas / somaDePeso);
    }

    public void cadastraHoras(int horasEstudadas) {
        this.horasEstudo = horasEstudadas;
    }

    public void cadastraNota(int posicaoNota, double valorNota) {
        this.notas[posicaoNota-1] = valorNota;
    }

    private double calculaMedia() {
        double media = 0.0;
        for(int i = 0; i <= notas.length-1; i++) {
            media += notas[i];
        }
        return media/this.notas.length;
    }

    public boolean aprovado() {
        if (calculaMedia() >= 7.0) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return this.disciplina + " " + this.horasEstudo + " " + calculaMedia() + " " + Arrays.toString(notas);
    }
}