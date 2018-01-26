/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasifurniture.controller;

import aplikasifurniture.data.Pemesanan;
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
public class PemesananController {
    private EntityManagerFactory emf=null;
    
    public PemesananController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Pemesanan pemesanan)throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pemesanan);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Pemesanan findPemesanan(String kode){
        EntityManager em = getEntityManager();
        try{
            return em.find(Pemesanan.class, kode);
        }finally{}
    }
    
    public String noPemesananOto(){
        String kode="No-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select b from Pemesanan b order by b.noPemesanan desc");
            q.setMaxResults(1);
            Pemesanan bk=(Pemesanan) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("No-000");
                String nomorurut = bk.getNoPemesanan().substring(3);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    //formPemesanan
    public DefaultTableModel showTableFormPemesanan(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
                                 //       "b.noPemesanan, b.kdPenyewa, b.kdApartemen, b.tglSewa, b.lamaSewa, b.sewaPer, b.totalFasilitas, b.totalBayar
            Query q=em.createQuery("SELECT p.noPemesanan, p.kdPelanggan, p.jenisPemesanan, p.namaPemesanan, p.tanggal, p.waktu, p.totalHarga, p.uangMuka, p.keterangan FROM Pemesanan p");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }  
    
    //popPesan
    public DefaultTableModel showTablePemesanan(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
                                 //       "b.noPemesanan, b.kdPenyewa, b.kdApartemen, b.tglSewa, b.lamaSewa, b.sewaPer, b.totalFasilitas, b.totalBayar
            Query q=em.createQuery("SELECT p.noPemesanan, p.kdPelanggan, p.jenisPemesanan, p.namaPemesanan, p.tanggal, p.waktu, p.totalHarga, p.uangMuka, p.keterangan FROM Pemesanan p WHERE p.keterangan like '%Belum Lunas%'");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }   
    
    public void updatePemesanan(String kode, String ket){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Query q=em.createQuery("UPDATE Pemesanan k SET k.keterangan = '"+ket+"' where k.noPemesanan = '"+kode+"'");
            q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex){
            em.close();
        }
    }
}