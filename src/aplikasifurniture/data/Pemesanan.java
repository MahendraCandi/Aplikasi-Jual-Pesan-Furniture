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
import javax.persistence.Lob;
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
@Table(name = "pemesanan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pemesanan.findAll", query = "SELECT p FROM Pemesanan p"),
    @NamedQuery(name = "Pemesanan.findByNoPemesanan", query = "SELECT p FROM Pemesanan p WHERE p.noPemesanan = :noPemesanan"),
    @NamedQuery(name = "Pemesanan.findByKdPelanggan", query = "SELECT p FROM Pemesanan p WHERE p.kdPelanggan = :kdPelanggan"),
    @NamedQuery(name = "Pemesanan.findByJenisPemesanan", query = "SELECT p FROM Pemesanan p WHERE p.jenisPemesanan = :jenisPemesanan"),
    @NamedQuery(name = "Pemesanan.findByWaktu", query = "SELECT p FROM Pemesanan p WHERE p.waktu = :waktu"),
    @NamedQuery(name = "Pemesanan.findByTotalHarga", query = "SELECT p FROM Pemesanan p WHERE p.totalHarga = :totalHarga"),
    @NamedQuery(name = "Pemesanan.findByUangMuka", query = "SELECT p FROM Pemesanan p WHERE p.uangMuka = :uangMuka"),
    @NamedQuery(name = "Pemesanan.findByNamaPemesanan", query = "SELECT p FROM Pemesanan p WHERE p.namaPemesanan = :namaPemesanan"),
    @NamedQuery(name = "Pemesanan.findByTanggal", query = "SELECT p FROM Pemesanan p WHERE p.tanggal = :tanggal"),
    @NamedQuery(name = "Pemesanan.findByKodeUser", query = "SELECT p FROM Pemesanan p WHERE p.kodeUser = :kodeUser")})
public class Pemesanan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "no_pemesanan")
    private String noPemesanan;
    @Column(name = "kd_pelanggan")
    private String kdPelanggan;
    @Column(name = "jenis_pemesanan")
    private String jenisPemesanan;
    @Column(name = "waktu")
    @Temporal(TemporalType.DATE)
    private Date waktu;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_harga")
    private Double totalHarga;
    @Column(name = "uang_muka")
    private Double uangMuka;
    @Column(name = "nama_pemesanan")
    private String namaPemesanan;
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @Column(name = "kode_user")
    private String kodeUser;
    @Lob
    @Column(name = "keterangan")
    private String keterangan;

    public Pemesanan() {
    }

    public Pemesanan(String noPemesanan) {
        this.noPemesanan = noPemesanan;
    }

    public String getNoPemesanan() {
        return noPemesanan;
    }

    public void setNoPemesanan(String noPemesanan) {
        this.noPemesanan = noPemesanan;
    }

    public String getKdPelanggan() {
        return kdPelanggan;
    }

    public void setKdPelanggan(String kdPelanggan) {
        this.kdPelanggan = kdPelanggan;
    }

    public String getJenisPemesanan() {
        return jenisPemesanan;
    }

    public void setJenisPemesanan(String jenisPemesanan) {
        this.jenisPemesanan = jenisPemesanan;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

    public Double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public Double getUangMuka() {
        return uangMuka;
    }

    public void setUangMuka(Double uangMuka) {
        this.uangMuka = uangMuka;
    }

    public String getNamaPemesanan() {
        return namaPemesanan;
    }

    public void setNamaPemesanan(String namaPemesanan) {
        this.namaPemesanan = namaPemesanan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKodeUser() {
        return kodeUser;
    }

    public void setKodeUser(String kodeUser) {
        this.kodeUser = kodeUser;
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
        hash += (noPemesanan != null ? noPemesanan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pemesanan)) {
            return false;
        }
        Pemesanan other = (Pemesanan) object;
        if ((this.noPemesanan == null && other.noPemesanan != null) || (this.noPemesanan != null && !this.noPemesanan.equals(other.noPemesanan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplikasifurniture.data.Pemesanan[ noPemesanan=" + noPemesanan + " ]";
    }
    
}
