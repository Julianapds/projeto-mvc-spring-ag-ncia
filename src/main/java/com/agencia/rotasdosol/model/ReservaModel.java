package com.agencia.rotasdosol.model;

import com.agencia.rotasdosol.model.usuario.UsuarioModel;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reservas")
public class ReservaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "idUsuario")
    private UsuarioModel cliente;

    @ManyToOne
    @JoinColumn(name = "id_pacote", referencedColumnName = "id_pacote")
    private PacoteModel pacote;

    @ManyToOne
    @JoinColumn(name = "id_hospedagem", referencedColumnName = "idHospedagem")
    private HospedagemModel hospedagem;

    @ManyToOne
    @JoinColumn(name = "id_voo", referencedColumnName = "idVoo")
    private VooModel voo;

    @Column(name = "data_reserva")
    @Temporal(TemporalType.DATE)
    private Date dataReserva;

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "idStatus")
    private StatusReservaModel status;

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public UsuarioModel getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioModel cliente) {
        this.cliente = cliente;
    }

    public PacoteModel getPacote() {
        return pacote;
    }

    public void setPacote(PacoteModel pacote) {
        this.pacote = pacote;
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

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public StatusReservaModel getStatus() {
        return status;
    }

    public void setStatus(StatusReservaModel status) {
        this.status = status;
    }
}

