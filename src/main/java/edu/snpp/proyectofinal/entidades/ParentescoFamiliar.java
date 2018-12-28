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
@Table(name = "parentesco_familiar")
@NamedQueries({
    @NamedQuery(name = "ParentescoFamiliar.findAll", query = "SELECT p FROM ParentescoFamiliar p")})
public class ParentescoFamiliar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idparentescofamiliar")
    private Integer idparentescofamiliar;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentescoFamiliar")
    private List<DetalleEncargado> detalleEncargadoList;

    public ParentescoFamiliar() {
    }

    public ParentescoFamiliar(Integer idparentescofamiliar) {
        this.idparentescofamiliar = idparentescofamiliar;
    }

    public Integer getIdparentescofamiliar() {
        return idparentescofamiliar;
    }

    public void setIdparentescofamiliar(Integer idparentescofamiliar) {
        this.idparentescofamiliar = idparentescofamiliar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idparentescofamiliar != null ? idparentescofamiliar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParentescoFamiliar)) {
            return false;
        }
        ParentescoFamiliar other = (ParentescoFamiliar) object;
        if ((this.idparentescofamiliar == null && other.idparentescofamiliar != null) || (this.idparentescofamiliar != null && !this.idparentescofamiliar.equals(other.idparentescofamiliar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.ParentescoFamiliar[ idparentescofamiliar=" + idparentescofamiliar + " ]";
    }
    
}
