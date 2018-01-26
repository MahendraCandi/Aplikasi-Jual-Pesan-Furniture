/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasifurniture.controller;

import aplikasifurniture.data.Pembayaran;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Candi-PC
 */
public class PembayaranController implements Serializable {
    private EntityManagerFactory emf=null;
    
    public PembayaranController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Pembayaran pembayaran)throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pembayaran);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Pembayaran findPembayaran(String kode){
        EntityManager em = getEntityManager();
        try{
            return em.find(Pembayaran.class, kode);
        }finally{}
    }
    
    public String noPembayaranOto(){
        String kode="Bill-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select b from Pembayaran b order by b.noPembayaran desc");
            q.setMaxResults(1);
            Pembayaran byr=(Pembayaran) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("Bill-000");
                String nomorurut = byr.getNoPembayaran().substring(5);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    //popBayar
    public DefaultTableModel showTablePembayaran(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT no_pembayaran, pembayaran.tanggal, pembayaran.no_pemesanan, pemesanan.jenis_pemesanan, pemesanan.nama_pemesanan, pemesanan.kd_pelanggan, pelanggan.nm_pelanggan, pemesanan.total_harga\n" +
                                         "FROM pembayaran\n" +
                                         "INNER JOIN pemesanan USING (no_pemesanan)\n" +
                                         "INNER JOIN pelanggan USING (kd_pelanggan)");
            while(rs.next()){
                Object[] obj = new Object[8];
                obj[0]=rs.getString("no_pembayaran");
                obj[1]=rs.getString("tanggal");
                obj[2]=rs.getString("no_pemesanan");
                obj[3]=rs.getString("jenis_pemesanan");
                obj[4]=rs.getString("nama_pemesanan");
                obj[5]=rs.getString("kd_pelanggan");
                obj[6]=rs.getString("nm_pelanggan");
                obj[7]=rs.getString("total_harga");
                model.addRow(obj);
            }
            
        }catch(Exception ex){}
        return model;
    }
}
