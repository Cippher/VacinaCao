package com.example.vacinacao;

import java.util.Date;

public class CadastroPessoa {
    /*
     * Variable declaration
     */
    private String nome;
    private String email;
    private String sexo;
    private Date dataNascimento;
    private String senha;
    private String rua;
    private int numero;
    private String cidade;
    /*
     * Construtor using fields
     */
    public CadastroPessoa(String nome, String email, String sexo, Date dataNascimento, String senha, String rua, int numero, String cidade) {
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
    }
    /*
     * Construtor without fields
     */
    public CadastroPessoa(){
    }
    /*
     * Getters
     */
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSexo() { return sexo; }
    public Date getDataNascimento() { return dataNascimento; }
    public String getSenha() { return senha; }
    public String getRua() { return rua; }
    public int getNumero() { return numero; }
    public String getCidade() { return cidade; }
    /*
     * Setters
     */
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setRua(String rua) { this.rua = rua; }
    public void setNumero(int numero) { this.numero = numero; }
    public void setCidade(String cidade) { this.cidade = cidade; }
}
