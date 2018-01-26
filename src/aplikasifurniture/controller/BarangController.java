/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasifurniture.controller;

import aplikasifurniture.data.Barang;
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
public class BarangController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public BarangController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Barang barang)throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(barang);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
     public void update(Barang barang) throws Exception{
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(barang);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode) throws Exception{
        EntityManager em=getEntityManager();
        Barang brg;
        try{
            brg=em.getReference(Barang.class, kode);
            brg.getKdBarang();
            em.getTransaction().begin();
            em.remove(brg);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Barang findBarang(String kode){
        EntityManager em = getEntityManager();
        try{
            return em.find(Barang.class, kode);
        }finally{}
    }
    
    public String kodeBarangOto(){
        String kode="BR-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select a from Barang a order by a.kdBarang desc");
            q.setMaxResults(1);
            Barang brg=(Barang) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("BR-000");
                String nomorurut = brg.getKdBarang().substring(3);
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
            Query q=em.createQuery("SELECT a.kdBarang, a.namaBarang, a.jenisBarang, a.kategori, a.ukuran, a.harga, a.warna, a.stok, a.diskon from Barang a");
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
            Query q=em.createQuery("SELECT a.kdBarang, a.namaBarang, a.jenisBarang, a.kategori, a.ukuran, a.harga, a.warna, a.stok, a.diskon from Barang a WHERE a.kdBarang like '%"+cari+"%'"
                                 + " or a.namaBarang like '%"+cari+"%'"
                                 + " or a.jenisBarang like '%"+cari+"%'"
                                 + " or a.kategori like '%"+cari+"%'"
                                 + " or a.ukuran like '%"+cari+"%'"
                                 + " or a.harga like '%"+cari+"%'"
                                 + " or a.warna like '%"+cari+"%'"
                                 + " or a.stok like '%"+cari+"%'"
                                 + " or a.diskon like '%"+cari+"%'");
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
