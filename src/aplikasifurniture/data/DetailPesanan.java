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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "detail_pesanan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailPesanan.findAll", query = "SELECT d FROM DetailPesanan d"),
    @NamedQuery(name = "DetailPesanan.findByIdDetail", query = "SELECT d FROM DetailPesanan d WHERE d.idDetail = :idDetail"),
    @NamedQuery(name = "DetailPesanan.findByNoPemesanan", query = "SELECT d FROM DetailPesanan d WHERE d.noPemesanan = :noPemesanan"),
    @NamedQuery(name = "DetailPesanan.findByKdBarang", query = "SELECT d FROM DetailPesanan d WHERE d.kdBarang = :kdBarang"),
    @NamedQuery(name = "DetailPesanan.findByHarga", query = "SELECT d FROM DetailPesanan d WHERE d.harga = :harga"),
    @NamedQuery(name = "DetailPesanan.findByKuantitas", query = "SELECT d FROM DetailPesanan d WHERE d.kuantitas = :kuantitas"),
    @NamedQuery(name = "DetailPesanan.findByDiskon", query = "SELECT d FROM DetailPesanan d WHERE d.diskon = :diskon"),
    @NamedQuery(name = "DetailPesanan.findBySubtotal", query = "SELECT d FROM DetailPesanan d WHERE d.subtotal = :subtotal")})
public class DetailPesanan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detail")
    private Integer idDetail;
    @Column(name = "no_pemesanan")
    private String noPemesanan;
    @Column(name = "kd_barang")
    private String kdBarang;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "harga")
    private Double harga;
    @Column(name = "kuantitas")
    private Integer kuantitas;
    @Column(name = "diskon")
    private Integer diskon;
    @Column(name = "subtotal")
    private Double subtotal;

    public DetailPesanan() {
    }

    public DetailPesanan(Integer idDetail) {
        this.idDetail = idDetail;
    }

    public Integer getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Integer idDetail) {
        this.idDetail = idDetail;
    }

    public String getNoPemesanan() {
        return noPemesanan;
    }

    public void setNoPemesanan(String noPemesanan) {
        this.noPemesanan = noPemesanan;
    }

    public String getKdBarang() {
        return kdBarang;
    }

    public void setKdBarang(String kdBarang) {
        this.kdBarang = kdBarang;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Integer getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(Integer kuantitas) {
        this.kuantitas = kuantitas;
    }

    public Integer getDiskon() {
        return diskon;
    }

    public void setDiskon(Integer diskon) {
        this.diskon = diskon;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetail != null ? idDetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailPesanan)) {
            return false;
        }
        DetailPesanan other = (DetailPesanan) object;
        if ((this.idDetail == null && other.idDetail != null) || (this.idDetail != null && !this.idDetail.equals(other.idDetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplikasifurniture.data.DetailPesanan[ idDetail=" + idDetail + " ]";
    }
    
}
