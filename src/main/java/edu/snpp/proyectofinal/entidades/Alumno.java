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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "alumno")
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a")})
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idalumno")
    private Integer idalumno;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "ci")
    private Integer ci;
    @Column(name = "fecha_nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    @Column(name = "monto_aporte")
    private Integer montoAporte;
    @JoinTable(name = "alumno_detalle_caja", joinColumns = {
        @JoinColumn(name = "idalumno", referencedColumnName = "idalumno")}, inverseJoinColumns = {
        @JoinColumn(name = "detalle_caja", referencedColumnName = "iddetalle_caja")})
    @ManyToMany
    private List<DetalleCaja> detalleCajaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<Inscripcion> inscripcionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<MoviemientoAporte> moviemientoAporteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno1")
    private List<DetalleEncargado> detalleEncargadoList;

    public Alumno() {
    }

    public Alumno(Integer idalumno) {
        this.idalumno = idalumno;
    }

    public Integer getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(Integer idalumno) {
        this.idalumno = idalumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Integer getMontoAporte() {
        return montoAporte;
    }

    public void setMontoAporte(Integer montoAporte) {
        this.montoAporte = montoAporte;
    }

    public List<DetalleCaja> getDetalleCajaList() {
        return detalleCajaList;
    }

    public void setDetalleCajaList(List<DetalleCaja> detalleCajaList) {
        this.detalleCajaList = detalleCajaList;
    }

    public List<Inscripcion> getInscripcionList() {
        return inscripcionList;
    }

    public void setInscripcionList(List<Inscripcion> inscripcionList) {
        this.inscripcionList = inscripcionList;
    }

    public List<MoviemientoAporte> getMoviemientoAporteList() {
        return moviemientoAporteList;
    }

    public void setMoviemientoAporteList(List<MoviemientoAporte> moviemientoAporteList) {
        this.moviemientoAporteList = moviemientoAporteList;
    }

    public List<DetalleEncargado> getDetalleEncargadoList() {
        return detalleEncargadoList;
    }

    public void setDetalleEncargadoList(List<DetalleEncargado> detalleEncargadoList) {
        this.detalleEncargadoList = detalleEncargadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalumno != null ? idalumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.idalumno == null && other.idalumno != null) || (this.idalumno != null && !this.idalumno.equals(other.idalumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.Alumno[ idalumno=" + idalumno + " ]";
    }
    
}
