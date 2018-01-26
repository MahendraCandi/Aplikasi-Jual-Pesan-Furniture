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
@Table(name = "pengiriman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pengiriman.findAll", query = "SELECT p FROM Pengiriman p"),
    @NamedQuery(name = "Pengiriman.findByKdPengiriman", query = "SELECT p FROM Pengiriman p WHERE p.kdPengiriman = :kdPengiriman"),
    @NamedQuery(name = "Pengiriman.findByNoPembayaran", query = "SELECT p FROM Pengiriman p WHERE p.noPembayaran = :noPembayaran"),
    @NamedQuery(name = "Pengiriman.findByTglKirim", query = "SELECT p FROM Pengiriman p WHERE p.tglKirim = :tglKirim"),
    @NamedQuery(name = "Pengiriman.findByNmPenerima", query = "SELECT p FROM Pengiriman p WHERE p.nmPenerima = :nmPenerima"),
    @NamedQuery(name = "Pengiriman.findByTelpPenerima", query = "SELECT p FROM Pengiriman p WHERE p.telpPenerima = :telpPenerima"),
    @NamedQuery(name = "Pengiriman.findByAlamat", query = "SELECT p FROM Pengiriman p WHERE p.alamat = :alamat"),
    @NamedQuery(name = "Pengiriman.findByStatus", query = "SELECT p FROM Pengiriman p WHERE p.status = :status")})
public class Pengiriman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kd_pengiriman")
    private String kdPengiriman;
    @Column(name = "no_pembayaran")
    private String noPembayaran;
    @Column(name = "tgl_kirim")
    @Temporal(TemporalType.DATE)
    private Date tglKirim;
    @Column(name = "nm_penerima")
    private String nmPenerima;
    @Column(name = "telp_penerima")
    private String telpPenerima;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "status")
    private String status;

    public Pengiriman() {
    }

    public Pengiriman(String kdPengiriman) {
        this.kdPengiriman = kdPengiriman;
    }

    public String getKdPengiriman() {
        return kdPengiriman;
    }

    public void setKdPengiriman(String kdPengiriman) {
        this.kdPengiriman = kdPengiriman;
    }

    public String getNoPembayaran() {
        return noPembayaran;
    }

    public void setNoPembayaran(String noPembayaran) {
        this.noPembayaran = noPembayaran;
    }

    public Date getTglKirim() {
        return tglKirim;
    }

    public void setTglKirim(Date tglKirim) {
        this.tglKirim = tglKirim;
    }

    public String getNmPenerima() {
        return nmPenerima;
    }

    public void setNmPenerima(String nmPenerima) {
        this.nmPenerima = nmPenerima;
    }

    public String getTelpPenerima() {
        return telpPenerima;
    }

    public void setTelpPenerima(String telpPenerima) {
        this.telpPenerima = telpPenerima;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kdPengiriman != null ? kdPengiriman.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pengiriman)) {
            return false;
        }
        Pengiriman other = (Pengiriman) object;
        if ((this.kdPengiriman == null && other.kdPengiriman != null) || (this.kdPengiriman != null && !this.kdPengiriman.equals(other.kdPengiriman))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplikasifurniture.data.Pengiriman[ kdPengiriman=" + kdPengiriman + " ]";
    }
    
}
