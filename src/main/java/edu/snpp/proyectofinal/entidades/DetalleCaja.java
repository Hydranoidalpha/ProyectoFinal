/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fredybogado
 */
@Entity
@Table(name = "detalle_caja")
@NamedQueries({
    @NamedQuery(name = "DetalleCaja.findAll", query = "SELECT d FROM DetalleCaja d")})
public class DetalleCaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "iddetalle_caja")
    private Integer iddetalleCaja;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "tipo_movimiento")
    private Boolean tipoMovimiento;
    @ManyToMany(mappedBy = "detalleCajaList")
    private List<Alumno> alumnoList;
    @JoinColumn(name = "caja", referencedColumnName = "idcaja")
    @ManyToOne(optional = false)
    private MovimientoCaja caja;
    @JoinColumn(name = "concepto", referencedColumnName = "idconcepto")
    @ManyToOne(optional = false)
    private Concepto concepto;

    public DetalleCaja() {
    }

    public DetalleCaja(Integer iddetalleCaja) {
        this.iddetalleCaja = iddetalleCaja;
    }

    public Integer getIddetalleCaja() {
        return iddetalleCaja;
    }

    public void setIddetalleCaja(Integer iddetalleCaja) {
        this.iddetalleCaja = iddetalleCaja;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Boolean getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Boolean tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    public MovimientoCaja getCaja() {
        return caja;
    }

    public void setCaja(MovimientoCaja caja) {
        this.caja = caja;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleCaja != null ? iddetalleCaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCaja)) {
            return false;
        }
        DetalleCaja other = (DetalleCaja) object;
        if ((this.iddetalleCaja == null && other.iddetalleCaja != null) || (this.iddetalleCaja != null && !this.iddetalleCaja.equals(other.iddetalleCaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.DetalleCaja[ iddetalleCaja=" + iddetalleCaja + " ]";
    }
    
}
