package com.example.vacinacao;

import java.util.Date;

public class CadastroCao {
    /*
     * Declaração das variáveis
     */
    private String nome;
    private String raca;
    private Date dataNascimento;
    private float peso;
    private String sexo;
    /*
     * Getters
     */
    public String getNome() { return nome; }
    public String getRaca() { return raca; }
    public Date getDataNascimento() { return dataNascimento; }
    public float getPeso() { return peso; }
    public String getSexo() { return sexo; }
    /*
     * Setters
     */
    public void setNome(String nome) { this.nome = nome; }
    public void setRaca(String raca) { this.raca = raca; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setPeso(float peso) { this.peso = peso; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    /*
     * Imprime os dados da pessoa no terminal para teste
     */
    public void imprimeCao(CadastroCao c){
        System.out.println("Nome: " + c.getNome() +
                "Raça: " + c.getRaca() +
                "Data de nascimento: " + c.getDataNascimento() +
                "Sexo: " + c.getSexo() +
                "Número: " + c.getPeso());
    }
}
