package agenda;
/**
 * 
 * Classe que cria o contato.
 *
 */

public class Contato {
    
    /**
     * Nome do contato.
     */
    private String nome;

    /**
     * Sobrenome do contato
     */
    private String sobrenome;

    /**
     * Numero do telefone.
     */
    private String telefone;

    /**
     * Boolean que controla se o contato é favorito ou não.
     */
    private boolean favorito;

    /**
     * Construtor da classe contato
     * @param nome Nome do contato.
     * @param sobrenome Sobrenome do contato.
     * @param telefone Telefone do contato.
     */
    public Contato(String nome, String sobrenome, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.favorito = false;
    }

    /**
     * Booleano para favoritar um contato.
     */
    public void favoritar() {
        this.favorito = true;
    }

    /**
     * Booleano para desfavoritar um contato.
     */
    public void desfavoritar(){
        this.favorito = false;
    }

    /**
     * Retorna o nome do contato.
     * @return String com o nome do contato.
     */
    public String getNome(){
        return nome;
    }

    /**
     * Retorna o sobrenome do contato.
     * @return String com o sobrenome.
     */
    public String getSobrenome(){
        return sobrenome;
    }

    /**
     * Retorna o telefone do contato.
     * @return String com o telefone.
     */
    public String getTelefone(){
        return telefone;
    }

    /**
     * Retorna o estado atual do favorito.
     * @return Booleano para confirmar o estado do favorito.
     */
    public boolean getFavorito(){
        return favorito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contato)) return false;
        Contato contato = (Contato) o;
        return nome.equals(contato.nome) && sobrenome.equals(contato.sobrenome);
    }

    /**
     * Cria a representação em String do contato.
     * @return Retorna uma string contendo o nome, sobrenome e telefone. se for
     *         favorito retorna tambem um coração antes do nome do contato.
     */
    public String toString() {
        if (favorito){
            return "❤️ " + this.nome + " " + this.sobrenome + "\n" + this.telefone;
        }
        return this.nome + " " + this.sobrenome + "\n" + this.telefone;
    }
}