package com.agencia.rotasdosol.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hospedagens")
public class HospedagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHospedagem;

    @Column(name = "nome_hotel")
    private String nomeHotel;

    @Column(name = "tipo_quarto")
    private String tipoQuarto;

    @Column(name = "data_checkin")
    @Temporal(TemporalType.DATE)
    private Date dataCheckin;

    @Column(name = "data_checkout")
    @Temporal(TemporalType.DATE)
    private Date dataCheckout;

    @Column(name = "valor_pernoite")
    private Double valorPernoite;

    @Column(name = "url_imagem")
    private String imagemUrl;

    @ManyToOne
    @JoinColumn(name = "id_destino", referencedColumnName = "id_destino")
    private DestinoModel destino;


    public Integer getIdHospedagem() {
        return idHospedagem;
    }

    public void setIdHospedagem(Integer idHospedagem) {
        this.idHospedagem = idHospedagem;
    }

    public String getNomeHotel() {
        return nomeHotel;
    }

    public void setNomeHotel(String nomeHotel) {
        this.nomeHotel = nomeHotel;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public Date getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(Date dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public Date getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(Date dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public Double getValorPernoite() {
        return valorPernoite;
    }

    public void setValorPernoite(Double valorPernoite) {
        this.valorPernoite = valorPernoite;
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

