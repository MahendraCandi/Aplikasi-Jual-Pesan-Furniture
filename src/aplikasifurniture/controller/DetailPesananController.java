/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasifurniture.controller;

import aplikasifurniture.data.DetailPesanan;
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
public class DetailPesananController {
    private EntityManagerFactory emf=null;
    
    public DetailPesananController (EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void saveDetail(DetailPesanan detailpesanan) throws Exception{
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(detailpesanan);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
}
