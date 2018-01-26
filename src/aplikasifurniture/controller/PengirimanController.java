/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasifurniture.controller;

import aplikasifurniture.data.Pengiriman;
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
public class PengirimanController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public PengirimanController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Pengiriman pengiriman)throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pengiriman);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
     public void update(Pengiriman pengiriman) throws Exception{
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(pengiriman);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode) throws Exception{
        EntityManager em=getEntityManager();
        Pengiriman brg;
        try{
            brg=em.getReference(Pengiriman.class, kode);
            brg.getKdPengiriman();
            em.getTransaction().begin();
            em.remove(brg);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Pengiriman findPengiriman(String kode){
        EntityManager em = getEntityManager();
        try{
            return em.find(Pengiriman.class, kode);
        }finally{}
    }
    
    public String kodePengirimanOto(){
        String kode="SENT-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select a from Pengiriman a order by a.kdPengiriman desc");
            q.setMaxResults(1);
            Pengiriman brg=(Pengiriman) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("SENT-000");
                String nomorurut = brg.getKdPengiriman().substring(5);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT a.kdPengiriman, a.noPembayaran, a.tglKirim, a.nmPenerima, a.telpPenerima, a.alamat, a.status from Pengiriman a");
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
            Query q=em.createQuery("SELECT a.kdPengiriman, a.noPembayaran, a.tglKirim, a.nmPenerima, a.telpPenerima, a.alamat, a.status from Pengiriman a WHERE a.kdPengiriman like '%"+cari+"%'"
                                 + " or a.noPembayaran like '%"+cari+"%'"
                                 + " or a.tglKirim like '%"+cari+"%'"
                                 + " or a.nmPenerima like '%"+cari+"%'"
                                 + " or a.telpPenerima like '%"+cari+"%'"
                                 + " or a.alamat like '%"+cari+"%'"
                                 + " or a.status like '%"+cari+"%'");
            List<Object> hasil=(List<Object>) q.getResultList();
                Iterator itr = hasil.iterator();
                while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
}
