/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author fredybogado
 */
@Entity
@Table(name = "detalle_encargado")
@NamedQueries({
    @NamedQuery(name = "DetalleEncargado.findAll", query = "SELECT d FROM DetalleEncargado d")})
public class DetalleEncargado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleEncargadoPK detalleEncargadoPK;
    @JoinColumn(name = "alumno", referencedColumnName = "idalumno", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno1;
    @JoinColumn(name = "encargado", referencedColumnName = "idencargado", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Encargado encargado1;
    @JoinColumn(name = "parentesco", referencedColumnName = "idparentesco_familiar", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ParentescoFamiliar parentescoFamiliar;

    public DetalleEncargado() {
    }

    public DetalleEncargado(DetalleEncargadoPK detalleEncargadoPK) {
        this.detalleEncargadoPK = detalleEncargadoPK;
    }

    public DetalleEncargado(int alumno, int parentesco, int encargado) {
        this.detalleEncargadoPK = new DetalleEncargadoPK(alumno, parentesco, encargado);
    }

    public DetalleEncargadoPK getDetalleEncargadoPK() {
        return detalleEncargadoPK;
    }

    public void setDetalleEncargadoPK(DetalleEncargadoPK detalleEncargadoPK) {
        this.detalleEncargadoPK = detalleEncargadoPK;
    }

    public Alumno getAlumno1() {
        return alumno1;
    }

    public void setAlumno1(Alumno alumno1) {
        this.alumno1 = alumno1;
    }

    public Encargado getEncargado1() {
        return encargado1;
    }

    public void setEncargado1(Encargado encargado1) {
        this.encargado1 = encargado1;
    }

    public ParentescoFamiliar getParentescoFamiliar() {
        return parentescoFamiliar;
    }

    public void setParentescoFamiliar(ParentescoFamiliar parentescoFamiliar) {
        this.parentescoFamiliar = parentescoFamiliar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleEncargadoPK != null ? detalleEncargadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleEncargado)) {
            return false;
        }
        DetalleEncargado other = (DetalleEncargado) object;
        if ((this.detalleEncargadoPK == null && other.detalleEncargadoPK != null) || (this.detalleEncargadoPK != null && !this.detalleEncargadoPK.equals(other.detalleEncargadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.DetalleEncargado[ detalleEncargadoPK=" + detalleEncargadoPK + " ]";
    }
    
}
