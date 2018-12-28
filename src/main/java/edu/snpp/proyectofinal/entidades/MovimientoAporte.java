/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author fredybogado
 */
@Entity
@Table(name = "movimiento_aporte")
@NamedQueries({
    @NamedQuery(name = "MovimientoAporte.findAll", query = "SELECT m FROM MovimientoAporte m")})
public class MovimientoAporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmoviemientoaporte")
    private Integer idmoviemientoaporte;
    @Column(name = "fechapago")
    @Temporal(TemporalType.DATE)
    private Date fechapago;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "fechavencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechavencimiento;
    @Column(name = "pendiente")
    private Boolean pendiente;
    @Column(name = "mes")
    private Integer mes;
    @Column(name = "anho")
    private Integer anho;
    @JoinColumn(name = "alumno", referencedColumnName = "idalumno")
    @ManyToOne(optional = false)
    private Alumno alumno;
    @JoinColumn(name = "detallecaja", referencedColumnName = "iddetallecaja")
    @ManyToOne
    private DetalleCaja detallecaja;

    public MovimientoAporte() {
    }

    @Transient
    private Boolean pagar = false;
    public Boolean isPagar() {
        return pagar;
    }

    public void setPagar(Boolean pagar) {
        this.pagar = pagar;
    }
    public MovimientoAporte(Integer idmoviemientoaporte) {
        this.idmoviemientoaporte = idmoviemientoaporte;
    }

    public Integer getIdmoviemientoaporte() {
        return idmoviemientoaporte;
    }

    public void setIdmoviemientoaporte(Integer idmoviemientoaporte) {
        this.idmoviemientoaporte = idmoviemientoaporte;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public Boolean getPendiente() {
        return pendiente;
    }

    public void setPendiente(Boolean pendiente) {
        this.pendiente = pendiente;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnho() {
        return anho;
    }

    public void setAnho(Integer anho) {
        this.anho = anho;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public DetalleCaja getDetallecaja() {
        return detallecaja;
    }

    public void setDetallecaja(DetalleCaja detallecaja) {
        this.detallecaja = detallecaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmoviemientoaporte != null ? idmoviemientoaporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoAporte)) {
            return false;
        }
        MovimientoAporte other = (MovimientoAporte) object;
        if ((this.idmoviemientoaporte == null && other.idmoviemientoaporte != null) || (this.idmoviemientoaporte != null && !this.idmoviemientoaporte.equals(other.idmoviemientoaporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.MovimientoAporte[ idmoviemientoaporte=" + idmoviemientoaporte + " ]";
    }
    
}
