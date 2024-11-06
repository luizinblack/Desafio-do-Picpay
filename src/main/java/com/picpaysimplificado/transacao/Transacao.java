package com.picpaysimplificado.transacao;

import com.picpaysimplificado.usuario.Usuario;
import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal quantia;

    @ManyToOne
    @JoinColumn(name = "enviar_id")
    private Usuario enviar;

    @ManyToOne
    @JoinColumn(name = "receber_id")
    private Usuario beneficiario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantia() {
        return quantia;
    }

    public void setQuantia(BigDecimal quantia) {
        this.quantia = quantia;
    }

    public Usuario getEnviar() {
        return enviar;
    }

    public void setEnviar(Usuario enviar) {
        this.enviar = enviar;
    }

    public Usuario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Usuario beneficiario) {
        this.beneficiario = beneficiario;
    }
}
