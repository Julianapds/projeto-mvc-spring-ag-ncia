package com.agencia.rotasdosol.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "voos")
public class VooModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVoo;

    @Column(name = "companhia_aerea")
    private String companhiaAerea;

    @Column(name = "data_partida")
    @Temporal(TemporalType.DATE)
    private Date dataPartida;

    @Column(name = "data_chegada")
    @Temporal(TemporalType.DATE)
    private Date dataChegada;

    @Column(name = "valor_preco")
    private Double valorPreco;

    @Column(name = "url_imagem")
    private String imagemUrl;

    @ManyToOne
    @JoinColumn(name = "id_destino", referencedColumnName = "id_destino")
    private DestinoModel destino;

    public Integer getIdVoo() {
        return idVoo;
    }

    public void setIdVoo(Integer idVoo) {
        this.idVoo = idVoo;
    }

    public String getCompanhiaAerea() {
        return companhiaAerea;
    }

    public void setCompanhiaAerea(String companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
    }

    public Date getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Double getValorPreco() {
        return valorPreco;
    }

    public void setValorPreco(Double valorPreco) {
        this.valorPreco = valorPreco;
    }

    public DestinoModel getDestino() {
        return destino;
    }

    public void setDestino(DestinoModel destino) {
        this.destino = destino;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}