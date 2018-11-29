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
    @Column(name = "idmoviemiento_aporte")
    private Integer idmoviemientoAporte;
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "pendiente")
    private Boolean pendiente;
    @Column(name = "mes")
    private Integer mes;
    @Column(name = "anho")
    private Integer anho;
    @JoinColumn(name = "alumno", referencedColumnName = "idalumno")
    @ManyToOne(optional = false)
    private Alumno alumno;

    public MovimientoAporte() {
    }

    public MovimientoAporte(Integer idmoviemientoAporte) {
        this.idmoviemientoAporte = idmoviemientoAporte;
    }

    public Integer getIdmoviemientoAporte() {
        return idmoviemientoAporte;
    }

    public void setIdmoviemientoAporte(Integer idmoviemientoAporte) {
        this.idmoviemientoAporte = idmoviemientoAporte;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmoviemientoAporte != null ? idmoviemientoAporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoAporte)) {
            return false;
        }
        MovimientoAporte other = (MovimientoAporte) object;
        if ((this.idmoviemientoAporte == null && other.idmoviemientoAporte != null) || (this.idmoviemientoAporte != null && !this.idmoviemientoAporte.equals(other.idmoviemientoAporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.MovimientoAporte[ idmoviemientoAporte=" + idmoviemientoAporte + " ]";
    }
    
}
