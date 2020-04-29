package com.example.vacinacao;

public class CadastroPessoa {
    /*
     * Variable declaration
     */
    //TODO-Vinícius: 27/04/2020: Declarar os outros atributos
    private String nome;
    private String email;

    //TODO-Vinícius: 27/04/2020: Ajustar construtor
    /*
     * Construtor using fields
     */
    public CadastroPessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    /*
     * Construtor without fields
     */
    public CadastroPessoa(){
    }
    //TODO-Vinícius: 27/04/2020: Gerar outros gets
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    //TODO-Vinícius: 27/04/2020: Gerar outros sets
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
