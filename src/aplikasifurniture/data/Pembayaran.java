/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasifurniture.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "pembayaran")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pembayaran.findAll", query = "SELECT p FROM Pembayaran p"),
    @NamedQuery(name = "Pembayaran.findByNoPembayaran", query = "SELECT p FROM Pembayaran p WHERE p.noPembayaran = :noPembayaran"),
    @NamedQuery(name = "Pembayaran.findByNoPemesanan", query = "SELECT p FROM Pembayaran p WHERE p.noPemesanan = :noPemesanan"),
    @NamedQuery(name = "Pembayaran.findByTanggal", query = "SELECT p FROM Pembayaran p WHERE p.tanggal = :tanggal"),
    @NamedQuery(name = "Pembayaran.findByUangBayar", query = "SELECT p FROM Pembayaran p WHERE p.uangBayar = :uangBayar"),
    @NamedQuery(name = "Pembayaran.findByUangKembali", query = "SELECT p FROM Pembayaran p WHERE p.uangKembali = :uangKembali")})
public class Pembayaran implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "no_pembayaran")
    private String noPembayaran;
    @Column(name = "no_pemesanan")
    private String noPemesanan;
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "uang_bayar")
    private Double uangBayar;
    @Column(name = "uang_kembali")
    private Double uangKembali;

    public Pembayaran() {
    }

    public Pembayaran(String noPembayaran) {
        this.noPembayaran = noPembayaran;
    }

    public String getNoPembayaran() {
        return noPembayaran;
    }

    public void setNoPembayaran(String noPembayaran) {
        this.noPembayaran = noPembayaran;
    }

    public String getNoPemesanan() {
        return noPemesanan;
    }

    public void setNoPemesanan(String noPemesanan) {
        this.noPemesanan = noPemesanan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Double getUangBayar() {
        return uangBayar;
    }

    public void setUangBayar(Double uangBayar) {
        this.uangBayar = uangBayar;
    }

    public Double getUangKembali() {
        return uangKembali;
    }

    public void setUangKembali(Double uangKembali) {
        this.uangKembali = uangKembali;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noPembayaran != null ? noPembayaran.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pembayaran)) {
            return false;
        }
        Pembayaran other = (Pembayaran) object;
        if ((this.noPembayaran == null && other.noPembayaran != null) || (this.noPembayaran != null && !this.noPembayaran.equals(other.noPembayaran))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplikasifurniture.data.Pembayaran[ noPembayaran=" + noPembayaran + " ]";
    }
    
}
