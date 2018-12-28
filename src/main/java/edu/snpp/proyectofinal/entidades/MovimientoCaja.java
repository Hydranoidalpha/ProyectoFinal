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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fredybogado
 */
@Entity
@Table(name = "movimiento_caja")
@NamedQueries({
    @NamedQuery(name = "MovimientoCaja.findAll", query = "SELECT m FROM MovimientoCaja m")})
public class MovimientoCaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcaja")
    private Integer idcaja;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "aperturacaja")
    private Integer aperturacaja;
    @Column(name = "cierrecaja")
    private Integer cierrecaja;
    @Column(name = "totalentrada")
    private Integer totalentrada;
    @Column(name = "totalsalida")
    private Integer totalsalida;
    @Column(name = "habilitado")
    private Boolean habilitado;
    @JoinColumn(name = "empleado", referencedColumnName = "idempleado")
    @ManyToOne(optional = false)
    private Empleado empleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caja")
    private List<DetalleCaja> detalleCajaList;

    public MovimientoCaja() {
    }

    public MovimientoCaja(Integer idcaja) {
        this.idcaja = idcaja;
    }

    public Integer getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(Integer idcaja) {
        this.idcaja = idcaja;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getAperturacaja() {
        return aperturacaja;
    }

    public void setAperturacaja(Integer aperturacaja) {
        this.aperturacaja = aperturacaja;
    }

    public Integer getCierrecaja() {
        return cierrecaja;
    }

    public void setCierrecaja(Integer cierrecaja) {
        this.cierrecaja = cierrecaja;
    }

    public Integer getTotalentrada() {
        return totalentrada;
    }

    public void setTotalentrada(Integer totalentrada) {
        this.totalentrada = totalentrada;
    }

    public Integer getTotalsalida() {
        return totalsalida;
    }

    public void setTotalsalida(Integer totalsalida) {
        this.totalsalida = totalsalida;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
        hash += (idcaja != null ? idcaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoCaja)) {
            return false;
        }
        MovimientoCaja other = (MovimientoCaja) object;
        if ((this.idcaja == null && other.idcaja != null) || (this.idcaja != null && !this.idcaja.equals(other.idcaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.MovimientoCaja[ idcaja=" + idcaja + " ]";
    }
    
}
