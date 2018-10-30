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
@Table(name = "encargado")
@NamedQueries({
    @NamedQuery(name = "Encargado.findAll", query = "SELECT e FROM Encargado e")})
public class Encargado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idencargado")
    private Integer idencargado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "ci")
    private Integer ci;
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encargado1")
    private List<DetalleEncargado> detalleEncargadoList;

    public Encargado() {
    }

    public Encargado(Integer idencargado) {
        this.idencargado = idencargado;
    }

    public Integer getIdencargado() {
        return idencargado;
    }

    public void setIdencargado(Integer idencargado) {
        this.idencargado = idencargado;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        hash += (idencargado != null ? idencargado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encargado)) {
            return false;
        }
        Encargado other = (Encargado) object;
        if ((this.idencargado == null && other.idencargado != null) || (this.idencargado != null && !this.idencargado.equals(other.idencargado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.Encargado[ idencargado=" + idencargado + " ]";
    }
    
}
