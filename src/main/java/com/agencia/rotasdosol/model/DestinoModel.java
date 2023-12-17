package com.agencia.rotasdosol.model;

import jakarta.persistence.*;

@Entity
@Table(name = "destinos")
public class DestinoModel {

    @Id
    @Column(name = "id_destino")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDestino;
    private String descricao;
    private String pais;
    private String cidade;

    public Integer getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
