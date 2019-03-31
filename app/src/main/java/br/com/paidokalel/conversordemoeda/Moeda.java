package br.com.paidokalel.conversordemoeda;

import java.util.Objects;

public class Moeda {
    private Integer id;
    private String nome;
    private String simbolo;
    private String codigoISO;
    private double valorEmUSD;

    public double getValorEmUSD() {
        return valorEmUSD;
    }

    public Moeda(){
    }

    public Moeda(Integer id, String nome, String simbolo, String codigoISO, double valorEmUSD) {
        this.id = id;
        this.nome = nome;
        this.simbolo = simbolo;
        this.codigoISO = codigoISO;
        this.valorEmUSD = valorEmUSD;
    }

    public void setValorEmUSD(double valorEmUSD) {
        this.valorEmUSD = valorEmUSD;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getCodigoISO() {
        return codigoISO;
    }

    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moeda moeda = (Moeda) o;
        return Objects.equals(id, moeda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return codigoISO + " (" + nome + " - " + simbolo + ")";
    }
}
