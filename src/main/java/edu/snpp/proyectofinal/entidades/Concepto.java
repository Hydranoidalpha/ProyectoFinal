/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author fredybogado
 */
@Entity
@Table(name = "concepto")
@NamedQueries({
    @NamedQuery(name = "Concepto.findAll", query = "SELECT c FROM Concepto c")})
public class Concepto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconcepto")
    private Integer idconcepto;
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "tipo")
    private Boolean tipo;
    @Column(name = "montoingreso")
    private Integer montoingreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "concepto")
    private List<DetalleCaja> detalleCajaList;

    public Concepto() {
    }

    public Concepto(Integer idconcepto) {
        this.idconcepto = idconcepto;
    }

    public Integer getIdconcepto() {
        return idconcepto;
    }

    public void setIdconcepto(Integer idconcepto) {
        this.idconcepto = idconcepto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Boolean getTipo() {
        return tipo;
    }

    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
    }

    public Integer getMontoingreso() {
        return montoingreso;
    }

    public void setMontoingreso(Integer montoingreso) {
        this.montoingreso = montoingreso;
    }

    public List<DetalleCaja> getDetalleCajaList() {
        return detalleCajaList;
    }

    public void setDetalleCajaList(List<DetalleCaja> detalleCajaList) {
        this.detalleCajaList = detalleCajaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconcepto != null ? idconcepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concepto)) {
            return false;
        }
        Concepto other = (Concepto) object;
        if ((this.idconcepto == null && other.idconcepto != null) || (this.idconcepto != null && !this.idconcepto.equals(other.idconcepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.Concepto[ idconcepto=" + idconcepto + " ]";
    }
    
}
