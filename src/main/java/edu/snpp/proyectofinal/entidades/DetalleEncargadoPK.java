/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author fredybogado
 */
@Embeddable
public class DetalleEncargadoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "alumno")
    private int alumno;
    @Basic(optional = false)
    @Column(name = "parentesco")
    private int parentesco;
    @Basic(optional = false)
    @Column(name = "encargado")
    private int encargado;

    public DetalleEncargadoPK() {
    }

    public DetalleEncargadoPK(int alumno, int parentesco, int encargado) {
        this.alumno = alumno;
        this.parentesco = parentesco;
        this.encargado = encargado;
    }

    public int getAlumno() {
        return alumno;
    }

    public void setAlumno(int alumno) {
        this.alumno = alumno;
    }

    public int getParentesco() {
        return parentesco;
    }

    public void setParentesco(int parentesco) {
        this.parentesco = parentesco;
    }

    public int getEncargado() {
        return encargado;
    }

    public void setEncargado(int encargado) {
        this.encargado = encargado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) alumno;
        hash += (int) parentesco;
        hash += (int) encargado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleEncargadoPK)) {
            return false;
        }
        DetalleEncargadoPK other = (DetalleEncargadoPK) object;
        if (this.alumno != other.alumno) {
            return false;
        }
        if (this.parentesco != other.parentesco) {
            return false;
        }
        if (this.encargado != other.encargado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.DetalleEncargadoPK[ alumno=" + alumno + ", parentesco=" + parentesco + ", encargado=" + encargado + " ]";
    }
    
}
