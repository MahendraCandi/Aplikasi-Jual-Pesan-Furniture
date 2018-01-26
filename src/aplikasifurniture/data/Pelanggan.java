/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasifurniture.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "pelanggan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pelanggan.findAll", query = "SELECT p FROM Pelanggan p"),
    @NamedQuery(name = "Pelanggan.findByKdPelanggan", query = "SELECT p FROM Pelanggan p WHERE p.kdPelanggan = :kdPelanggan"),
    @NamedQuery(name = "Pelanggan.findByNmPelanggan", query = "SELECT p FROM Pelanggan p WHERE p.nmPelanggan = :nmPelanggan"),
    @NamedQuery(name = "Pelanggan.findByTelepon", query = "SELECT p FROM Pelanggan p WHERE p.telepon = :telepon"),
    @NamedQuery(name = "Pelanggan.findByAlamat", query = "SELECT p FROM Pelanggan p WHERE p.alamat = :alamat"),
    @NamedQuery(name = "Pelanggan.findByEmail", query = "SELECT p FROM Pelanggan p WHERE p.email = :email")})
public class Pelanggan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kd_pelanggan")
    private String kdPelanggan;
    @Column(name = "nm_pelanggan")
    private String nmPelanggan;
    @Column(name = "telepon")
    private String telepon;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "email")
    private String email;
    @Lob
    @Column(name = "keterangan")
    private String keterangan;

    public Pelanggan() {
    }

    public Pelanggan(String kdPelanggan) {
        this.kdPelanggan = kdPelanggan;
    }

    public String getKdPelanggan() {
        return kdPelanggan;
    }

    public void setKdPelanggan(String kdPelanggan) {
        this.kdPelanggan = kdPelanggan;
    }

    public String getNmPelanggan() {
        return nmPelanggan;
    }

    public void setNmPelanggan(String nmPelanggan) {
        this.nmPelanggan = nmPelanggan;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kdPelanggan != null ? kdPelanggan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pelanggan)) {
            return false;
        }
        Pelanggan other = (Pelanggan) object;
        if ((this.kdPelanggan == null && other.kdPelanggan != null) || (this.kdPelanggan != null && !this.kdPelanggan.equals(other.kdPelanggan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplikasifurniture.data.Pelanggan[ kdPelanggan=" + kdPelanggan + " ]";
    }
    
}
