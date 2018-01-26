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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "barang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barang.findAll", query = "SELECT b FROM Barang b"),
    @NamedQuery(name = "Barang.findByKdBarang", query = "SELECT b FROM Barang b WHERE b.kdBarang = :kdBarang"),
    @NamedQuery(name = "Barang.findByJenisBarang", query = "SELECT b FROM Barang b WHERE b.jenisBarang = :jenisBarang"),
    @NamedQuery(name = "Barang.findByKategori", query = "SELECT b FROM Barang b WHERE b.kategori = :kategori"),
    @NamedQuery(name = "Barang.findByNamaBarang", query = "SELECT b FROM Barang b WHERE b.namaBarang = :namaBarang"),
    @NamedQuery(name = "Barang.findByUkuran", query = "SELECT b FROM Barang b WHERE b.ukuran = :ukuran"),
    @NamedQuery(name = "Barang.findByHarga", query = "SELECT b FROM Barang b WHERE b.harga = :harga"),
    @NamedQuery(name = "Barang.findByWarna", query = "SELECT b FROM Barang b WHERE b.warna = :warna"),
    @NamedQuery(name = "Barang.findByStok", query = "SELECT b FROM Barang b WHERE b.stok = :stok"),
    @NamedQuery(name = "Barang.findByDiskon", query = "SELECT b FROM Barang b WHERE b.diskon = :diskon")})
public class Barang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kd_barang")
    private String kdBarang;
    @Column(name = "jenis_barang")
    private String jenisBarang;
    @Column(name = "kategori")
    private String kategori;
    @Column(name = "nama_barang")
    private String namaBarang;
    @Column(name = "ukuran")
    private String ukuran;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "harga")
    private Double harga;
    @Column(name = "warna")
    private String warna;
    @Column(name = "stok")
    private Integer stok;
    @Column(name = "diskon")
    private Integer diskon;

    public Barang() {
    }

    public Barang(String kdBarang) {
        this.kdBarang = kdBarang;
    }

    public String getKdBarang() {
        return kdBarang;
    }

    public void setKdBarang(String kdBarang) {
        this.kdBarang = kdBarang;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Integer getDiskon() {
        return diskon;
    }

    public void setDiskon(Integer diskon) {
        this.diskon = diskon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kdBarang != null ? kdBarang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barang)) {
            return false;
        }
        Barang other = (Barang) object;
        if ((this.kdBarang == null && other.kdBarang != null) || (this.kdBarang != null && !this.kdBarang.equals(other.kdBarang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplikasifurniture.data.Barang[ kdBarang=" + kdBarang + " ]";
    }
    
}
