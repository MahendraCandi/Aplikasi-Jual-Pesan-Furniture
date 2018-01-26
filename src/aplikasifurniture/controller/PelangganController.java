/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasifurniture.controller;

import aplikasifurniture.data.Pelanggan;
import java.io.Serializable;
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
public class PelangganController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public PelangganController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Pelanggan pelanggan)throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pelanggan);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
     public void update(Pelanggan pelanggan) throws Exception{
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(pelanggan);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode) throws Exception{
        EntityManager em=getEntityManager();
        Pelanggan pel;
        try{
            pel=em.getReference(Pelanggan.class, kode);
            pel.getKdPelanggan();
            em.getTransaction().begin();
            em.remove(pel);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Pelanggan findPelanggan(String kode){
        EntityManager em = getEntityManager();
        try{
            return em.find(Pelanggan.class, kode);
        }finally{}
    }
    
    public String kodePelangganOto(){
        String kode="Cust-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select a from Pelanggan a order by a.kdPelanggan desc");
            q.setMaxResults(1);
            Pelanggan pel=(Pelanggan) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("Cust-000");
                String nomorurut = pel.getKdPelanggan().substring(5);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    //Form Pelanggan
    public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT a.kdPelanggan, a.nmPelanggan, a.telepon, a.alamat, a.email, a.keterangan from Pelanggan a");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //Pop Pelanggan
    public DefaultTableModel showPopTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT a.kdPelanggan, a.nmPelanggan, a.alamat, a.telepon, a.email, a.keterangan from Pelanggan a where a.keterangan like '%Belum Pesan%'");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    public DefaultTableModel cari(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
                            //SQL = SELECT * FROM `kereta` WHERE `KodeKereta` like '%Cimahi%' or `StasiunTujuan` like '%Cimahi%'
            Query q=em.createQuery("SELECT a.kdPelanggan, a.nmPelanggan, a.alamat, a.telepon, a.email, a.keterangan from Pelanggan a WHERE a.kdPelanggan like '%"+cari+"%'"
                                 + " or a.nmPelanggan like '%"+cari+"%'"
                                 + " or a.alamat like '%"+cari+"%'"
                                 + " or a.telepon like '%"+cari+"%'"
                                 + " or a.keterangan like '%"+cari+"%'"
                                 + " or a.email like '%"+cari+"%'");
            List<Object> hasil=(List<Object>) q.getResultList();
                Iterator itr = hasil.iterator();
                while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    public void updatePelanggan(String kode, String ket){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Query q=em.createQuery("UPDATE Pelanggan k SET k.keterangan = '"+ket+"' where k.kdPelanggan = '"+kode+"'");
            q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex){
            em.close();
        }
    }
    
    
}