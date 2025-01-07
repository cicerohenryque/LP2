package documin;

import documento.DocumentoController;
import visao.VisaoController;

/**
 * Classe que irá conter todos os controllers e repassará a requisição
 * do sistema para o especialista da informação, ou seja quem possui
 * os dados necessários para realizar o que se pede.
 */

public class Facade {
    private DocumentoController documentoController;
    private VisaoController visaoController;
    
    /**
     * Construtor de facade, que não recebe nenhum parâmetro pois só
     * irá inicializar o controller de documento
     */

    public Facade() {
        this.documentoController = new DocumentoController();
    }
    
    /**
     * Método que irá repassar a requisição de criação de Documento
     * para o documento controller, passando consigo o titulo que o
     * Documento terá porém sem passar o tamanho máximo de Elementos
     * que esse documento irá possuir
     * @param titulo Título que o Documento terá
     * @return boolean Boolean representando se a criação foi realizada
     * ou não
     */

    public boolean criarDocumento(String titulo) {
    	return this.documentoController.addDocumento(titulo);
    }
    
    /**
     * Método que irá repassar a requisição de criação de Documento
     * para o documento controller, passando consigo o titulo que o
     * Documento terá e o tamanho máximo de Elementos que esse 
     * documento irá possuir
     * @param titulo Titulo que o Documento terá
     * @param tamanhoMaximo Tamanho máximo de Elementos que esse Documento
     * irá possuir
     * @return boolean Boolean representando se a criação foi realizada
     * ou não
     */
    
    public boolean criarDocumento(String titulo, int tamanhoMaximo) {
    	return this.documentoController.addDocumento(titulo, tamanhoMaximo);
    }
    
    /**
     * Método que irá repassar a requisição de remoção de Documento
     * para o documento controller, passando consigo o titulo do Documento
     * que está armazenado no controller e que irá ser excluído
     * @param titulo Titulo do Documento que será excluído
     */
    
    public void removerDocumento(String titulo) {
    	this.documentoController.removerDocumento(titulo);
    }
    
    /**
     * Método que irá repassar a requisição de contagem de Documento
     * para o documento controller, passando consigo o titulo do Documento
     * que terá seus Elementos contados que já está cadastrado no controller
     * @param titulo Título do Documento que irá ter seus Elementos contados
     * @return int A quantidade de elementos que o Documento referenciado
     * possui
     */
    
    public int contarElementos(String titulo) {
    	return this.documentoController.getQtdeElementos(titulo);
    }
    
    /**
     * Método que irá repassar a requisição de exibição de Documento
     * para o documento controller, passando consigo o titulo do Documento
     * que será exibido, o mesmo já cadastrado no controller
     * @param titulo Titulo do Documento que será exibido
     * @return String[] Array de Strings possuindo a representação resumida
     * de cada um dos Elementos presentes no Documento
     */
    
    public String[] exibirDocumento(String titulo) {
    	return this.documentoController.exibirDocumento(titulo);
    }
    
    /**
     * Método que irá repassar a requisição de criação de um Texto
     * para o documento controller, passando consigo o titulo do Documento
     * que terá um Texto adicionado, o valor e a prioridade que o Texto
     * terá 
     * @param tituloDoc Titulo do Documento que terá um texto adicionado
     * @param valor Valor que será passado no Texto criado
     * @param prioridade Prioridade do Texto que será criado
     * @return int O index ou Id do Elemento que foi registrado no Documento
     */
    
    public int criarTexto(String tituloDoc, String valor, int prioridade) {
    	return this.documentoController.addTextoDocumento(tituloDoc, valor, prioridade);
    }
    
    /**
     * Método que irá repassar a requisição de criação de um Titulo
     * para o documento controller, passando consigo o titulo do Documento
     * que terá um Titulo adicionado, o valor, a prioridade, o nível e um booleano
     * indicando se o titulo é linkavel que o Titulo terá 
     * @param tituloDoc Titulo do Documento que terá um Titulo adicionado
     * @param valor Valor que será passado no Titulo criado
     * @param prioridade Prioridade do Titulo que será criado
     * @param nivel Nível do Titulo criado
     * @param linkavel Booleano que indicará se o Titulo é linkavel ou não
     * @return int O index ou Id do Elemento que foi registrado no Documento
     */
    
    public int criarTitulo(String tituloDoc, String valor, int prioridade, int nivel, boolean linkavel) {
    	return this.documentoController.addTituloDocumento(tituloDoc, valor, prioridade, nivel, linkavel);
    }
    
    /**
     * Método que irá repassar a requisição de criação de uma Lista 
     * para o documento controller, passando consigo o titulo do Documento
     * que terá uma Lista adicionada, o valor, a prioridade, o separador que será
     * identificado no valor e o charLista, que será representado na exibição
     * da Lista
     * @param tituloDoc Titulo do Documento que terá um texto adicionado
     * @param valorLista Valor que será passado na Lista criada
     * @param prioridade Prioridade da Lista que será criada
     * @param separador Caracter que será utilizado para separar as partes do
     * valor
     * @param charLista Caracter que será utilizado para a representação de
     * exibição da Lista
     * @return int O index ou Id do Elemento que foi registrado no Documento
     */
    
    public int criarLista(String tituloDoc, String valorLista, int prioridade, String separador, String charLista) {
    	return this.documentoController.addListaDocumento(tituloDoc, valorLista, prioridade, separador, charLista);
    }
    
    /**
     * Método que irá repassar a requisição de criação do Elemento Termos
     * para o documento controller, passando consigo o titulo do Documento
     * que terá um Elemento Termos adicionado, o valor, a prioridade, o 
     * separador que será identificado no valor e a ordem em que esses Termos
     * serão representados, podendo ser ordem alfabética, por tamanho ou sem
     * ordem definida
     * @param tituloDoc Titulo do Documento que terá o Elemento Termos adicionado
     * @param valor Valor que será passado no Elemento Termos criado
     * @param prioridade Prioridade do Elemento Termos que será criado
     * @param separador Caracter que será utilizado para separar as partes do
     * valor
     * @param ordem Ordem em que esses Termos serão representados, podendo ser
     * ordem alfabética, por tamanho ou sem ordem definida
     * @return int O index ou Id do Elemento que foi registrado no Documento
     */
    
    public int criarTermos(String tituloDoc, String valorTermos, int prioridade, String separador, String ordem) {
    	return this.documentoController.addTermosDocumento(tituloDoc, valorTermos, prioridade, separador, ordem);
    }
    
    /**
     * Método que irá repassar a requisição de pegar uma representação completa
     * do Elemento através do Documento Controller, passando consigo o titulo do 
     * Documento que terá seu Elemento representado e a posição do Elemento que
     * será retornado 
     * @param tituloDoc Titulo do Documento que terá seu Elemento referenciado
     * @param elementoPosicao Posição em que o Elemento está guardado no Documento
     * @return String A representação completa do Elemento referenciado
     */
    
    public String pegarRepresentacaoCompleta(String tituloDoc, int elementoPosicao) {
    	return this.documentoController.getRepresentacaoCompletaDocumento(tituloDoc, elementoPosicao);
    }
    
    /**
     * Método que irá repassar a requisição de pegar uma representação resumida
     * do Elemento através do Documento Controller, passando consigo o titulo do 
     * Documento que terá seu Elemento representado e a posição do Elemento que
     * será retornado 
     * @param tituloDoc Titulo do Documento que terá seu Elemento referenciado
     * @param elementoPosicao Posição em que o Elemento está guardado no Documento
     * @return String A representação resumida do Elemento referenciado
     */
    
    public String pegarRepresentacaoResumida(String tituloDoc, int elementoPosicao) {
    	return this.documentoController.getRepresentacaoResumidaDocumento(tituloDoc, elementoPosicao);
    }
    
    /**
     * Método que irá repassar a requisição de remover um elemento de um Documento
     * para o documento controller, passando consigo o titulo do Documento que terá
     * seu Elemento apagado e a posição que o Elemento está guardado no Documento
     * @param tituloDoc Titulo do Documento que terá seu Elemento apagado
     * @param elementoPosicao Posição em que o Elemento está guardado no Documento
     * @return boolean Boolean representando se o Elemento foi apagado ou não
     */
    
    public boolean apagarElemento(String tituloDoc, int elementoPosicao) {
    	return this.documentoController.removeElementoDocumento(tituloDoc, elementoPosicao);
    }
    
    /**
     * Método que irá repassar a requisição de mover o Elemento para uma posição
     * acima em seu armazenamento para o Documento Controller, passando consigo 
     * o titulo do Documento que terá seus Elementos alterados e a posição do
     * Elemento que será promovido na ordem de Elementos
     * @param tituloDoc Titulo do Documento que terá seu Elemento ascendido
     * @param elementoPosicao Posição em que o Elemento está guardado no Documento
     */
    
    public void moverParaCima(String tituloDoc, int elementoPosicao) {
    	this.documentoController.elevaElementoDocumento(tituloDoc, elementoPosicao);
    }
    
    /**
     * Método que irá repassar a requisição de mover o Elemento para uma posição
     * abaixo em seu armazenamento para o Documento Controller, passando consigo 
     * o titulo do Documento que terá seus Elementos alterados e a posição do
     * Elemento que será promovido na ordem de Elementos
     * @param tituloDoc Titulo do Documento que terá seu Elemento regredido na sua
     * posição no armazenamento de Elementos do Documento 
     * @param elementoPosicao Posição em que o Elemento está guardado no Documento
     */
    
    public void moverParaBaixo(String tituloDoc, int elementoPosicao) {
    	this.documentoController.cedeElementoDocumento(tituloDoc, elementoPosicao);
    }
    
    /**
     * Método que irá repassar a requisição de mover criar um Atalho em um Documento
     * para o Documento Controller, passando consigo o titulo do Documento que terá
     * um atalho adicionado e o titulo do Documento que será transformado em Atalho
     * @param tituloDoc Titulo do Documento que terá um Atalho adicionado
     * @param tituloDocReferenciado Titulo do Documento que será transformado em Atalho
     * @return int A posição do Elemento Atalho no armazenamento de Elemento do Documento
     */
    
    public int criarAtalho(String tituloDoc, String tituloDocReferenciado) {
    	return this.documentoController.addAtalho(tituloDoc, tituloDocReferenciado);
    }
    
    /**
     * Método que irá repassar a requisição de criar uma visão completa de um Documento
     * para o Visao Controller, passando consigo o titulo do Documento que será 
     * representado na visão e o controller do Documento onde o Documento será recebido
     * @param tituloDoc Titulo do Documento que será representado na visão
     * @return int A posição da Visão no armazenamento de visões no VisaoController
     */
    
    public int criarVisaoCompleta(String tituloDoc) {
    	return this.visaoController.criarVisaoCompleta(tituloDoc, documentoController);
    }
    
    /**
     * Método que irá repassar a requisição de criar uma visão resumida de um Documento
     * para o Visao Controller, passando consigo o titulo do Documento que será 
     * representado na visão
     * @param tituloDoc Titulo do Documento que será representado na visão
     * @return int A posição da Visão no armazenamento de visões no VisaoController
     */
    
    public int criarVisaoResumida(String tituloDoc) {
    	return this.visaoController.criarVisaoResumida(tituloDoc, documentoController);	
    }
    
    /**
     * Método que irá repassar a requisição de criar uma visão prioritária de um Documento
     * para o Visao Controller, passando consigo o titulo do Documento que será 
     * representado na visão e a prioridade base que os Elementos terão que ter para aparecer
     * na visão
     * @param tituloDoc Titulo do Documento que será representado na visão
     * @param prioridade Prioridade Prioridade base que os Elementos terão que ter para aparecer
     * na visão
     * @return int A posição da Visão no armazenamento de visões no VisaoController
     */
    
    public int criarVisaoPrioritaria(String tituloDoc, int prioridade) {
    	return this.visaoController.criarVisaoPrioritaria(tituloDoc, prioridade, documentoController);
    }
    
    /**
     * Método que irá repassar a requisição de criar uma visão Titulo de um Documento
     * para o Visao Controller, passando consigo o titulo do Documento que será 
     * representado na visão
     * @param tituloDoc Titulo do Documento que será representado na visão
     * @return int A posição da Visão no armazenamento de visões no VisaoController
     */

    public int criarVisaoTitulo(String tituloDoc) {
    	return this.visaoController.criarVisaoTitulo(tituloDoc, documentoController);
    }
   
    /**
     * Método que irá repassar a requisição de exibir uma Visão de um Documento
     * para o Visão Controller, passando consigo o Id da visão que será representada
     * @param visaoId Id da visão que será representada
     * @return String[] Array de String que representa a exibição da Visão
     */
    
    public String[] exibirVisao(int visaoId) {
    	return this.visaoController.exibirVisao(visaoId);
    }
}