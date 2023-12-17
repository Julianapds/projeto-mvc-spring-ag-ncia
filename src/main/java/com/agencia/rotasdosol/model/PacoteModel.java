package com.agencia.rotasdosol.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pacotes")
public class PacoteModel {

    @Id
    @Column(name = "id_pacote")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPacote;

    @Column(name = "valor_preco")
    private Double valorPreco;

    @ManyToOne
    @JoinColumn(name = "id_hospedagem", referencedColumnName = "idHospedagem")
    private HospedagemModel hospedagem;

    @ManyToOne
    @JoinColumn(name = "id_voo", referencedColumnName = "idVoo")
    private VooModel voo;

    @Column(name = "url_imagem")
    private String imagemUrl;

    public Integer getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Integer idPacote) {
        this.idPacote = idPacote;
    }

    public Double getValorPreco() {
        return valorPreco;
    }

    public void setValorPreco(Double valorPreco) {
        this.valorPreco = valorPreco;
    }

    public HospedagemModel getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(HospedagemModel hospedagem) {
        this.hospedagem = hospedagem;
    }

    public VooModel getVoo() {
        return voo;
    }

    public void setVoo(VooModel voo) {
        this.voo = voo;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
