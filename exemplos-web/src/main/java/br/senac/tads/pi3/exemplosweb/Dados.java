/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemplosweb;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

public class Dados {

    private Integer id;
    
    private String nome;
    
    private LocalDate dataNascimento;
    
    private BigDecimal peso;
    
    private BigDecimal altura;
    
    private String[] interesses;

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public String[] getInteresses() {
        return interesses;
    }

    public void setInteresses(String[] interesses) {
        this.interesses = interesses;
    }
    
    
    public BigDecimal getImc() {
        if (peso != null && altura != null) {
            BigDecimal altura2 = (this.altura.multiply(this.altura));
            BigDecimal imc = this.peso.divide(altura2, 2, RoundingMode.HALF_UP);
            return imc;
        }
        return null;
    }
    
    public int getImcResult() {
        BigDecimal imc = getImc();
        if (imc.compareTo(new BigDecimal("18.5")) < 0) {
            return 1; // MAGREZA
        } else if (imc.compareTo(new BigDecimal("18.5")) >= 0 && imc.compareTo(new BigDecimal("25")) < 0) {
            return 2; // NORMAL
        } else if (imc.compareTo(new BigDecimal("25")) >= 0 && imc.compareTo(new BigDecimal("30")) < 0) {
            return 3; // SOBREPESO
        } else if (imc.compareTo(new BigDecimal("30")) >= 0 && imc.compareTo(new BigDecimal("40")) < 0) {
            return 4; // OBESIDADE
        } else if (imc.compareTo(new BigDecimal("40")) > 0) {
            return 5; // OBESIDADE GRAVE
        }
        return 0;
    }
    
    public Integer getIdade() {
        if (dataNascimento != null) {
            Period periodo = Period.between(dataNascimento, LocalDate.now());
            int idade = periodo.getYears();
            return idade;
        }
        return null;
    }
    
}
