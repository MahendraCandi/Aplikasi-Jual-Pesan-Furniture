/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasifurniture.controller;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Fillia
 */
public class LaporanController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public LaporanController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    
    public void cetakBarang(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\report\\reportBarang.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                em.close();
                connect.close();
        }catch(Exception e){e.printStackTrace();}
        
    }
        
        public void cetakPelanggan(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\report\\reportPelanggan.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                em.close();
                connect.close();
            
        }catch(Exception e){e.printStackTrace();
        }
    }   
        
        public void cetakPembayaran(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\report\\reportPembayaran.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                em.close();
                connect.close();
            
        }catch(Exception e){e.printStackTrace();
        }
    }
        
        public void cetakPemesanan(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\report\\reportPemesanan.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                em.close();
                connect.close();
        }catch(Exception e){e.printStackTrace();
        }
    }
        
        public void cetakPengiriman(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\report\\reportPengiriman.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                connect.close();
        }catch(Exception e){e.printStackTrace();
        }
    }
        
        public void cetakUser(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\report\\reportUser.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                connect.close();
        }catch(Exception e){}
    }
}
