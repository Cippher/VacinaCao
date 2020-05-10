package com.example.vacinacao;

import java.util.Date;

public class CadastroPessoa {
    /*
     * Declaração das variáveis
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
    /*
     * Imprime os dados da pessoa no terminal para teste
     */
    public void imprimePessoa(CadastroPessoa p){
        System.out.println("Nome: " + p.getNome() +
                         "E-mail: " + p.getEmail() +
                           "Sexo: " + p.getSexo() +
             "Data de nascimento: " + p.getDataNascimento() +
                          "Senha: " + p.getSenha() +
                            "Rua: " + p.getRua() +
                         "Número: " + p.getNumero() +
                         "Cidade: " + p.getCidade());
    }
}
