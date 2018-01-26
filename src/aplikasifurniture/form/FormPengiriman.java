/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasifurniture.form;

import aplikasifurniture.AplikasiFurniture;
import aplikasifurniture.data.Pengiriman;
import aplikasifurniture.controller.PengirimanController;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Candi-PC
 */
public class FormPengiriman extends javax.swing.JInternalFrame implements NavigatorFormInt{

    PengirimanController pCont=new PengirimanController(AplikasiFurniture.emf);
    Pengiriman kirim=new Pengiriman();
    DefaultTableModel model;
    public String byr;
    /**
     * Creates new form FormPengiriman
     */
    public FormPengiriman() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Kode Pengiriman");
        model.addColumn("No Bayar");
        model.addColumn("Tanggal Pengiriman");
        model.addColumn("Nama Penerima");
        model.addColumn("No Telepon");
        model.addColumn("Alamat");
        model.addColumn("Status");
        tableKirim.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        setJDC(jdcTanggal);
        seleksiTable();
        
        
    }
    
    public void pilihPembayaran(){
        PopBayar PP=new PopBayar();
        PP.FP=this;
        txtNoBayar.setText(byr);
        txtNama.setText("");
        txtTelepon.setText("");
        txtAlamat.setText("");
        txtNama.requestFocus();
        txtStatus.setText("Belum Terkirim");
        
    }
    
    private void showTableKirim(){
        tableKirim.setModel(pCont.showTable(model));
    }

    private void seleksiTable(){
        tableKirim.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableKirim.getSelectedRow();       //dapatkan baris yang terseleksi
                if(baris != -1){                        //jika baris bukan sama dengan -1 atau tidak ada
                    txtKodeKirim.setText(tableKirim.getValueAt(baris, 0).toString());
                    txtNoBayar.setText(tableKirim.getValueAt(baris, 1).toString());
                    jdcTanggal.setDate((Date) tableKirim.getValueAt(baris, 2));
                    txtNama.setText(tableKirim.getValueAt(baris, 3).toString());
                    txtTelepon.setText(tableKirim.getValueAt(baris, 4).toString());
                    txtAlamat.setText(tableKirim.getValueAt(baris, 5).toString());
                    txtStatus.setText(tableKirim.getValueAt(baris, 6).toString());
                }
            }
        });
    }
    
    private void setJDC(JDateChooser JDC){
        JDC.setLocale(Locale.forLanguageTag("in-ID")); //membuat locale indonesia
        JDC.setDateFormatString("EEEE, dd MMMM yyyy");
        JDC.setMinSelectableDate(new Date());
        JDC.setDate(new Date());
    }
    
    public void renderTable(){
        TableCellRenderer tbr = new DefaultTableCellRenderer(){
            SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column){
                if(value instanceof Date){
                    value = sdf.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tableKirim.getColumnModel().getColumn(2).setCellRenderer(tbr);
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=pCont.cari(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ada!");
        }else{
            tableKirim.setModel(pCont.cari(model, cari));
        }
    }
    
    private void updateStatus(){
        kirim=pCont.findPengiriman(txtKodeKirim.getText());
        if(kirim==null){
            JOptionPane.showMessageDialog(null, "Pilih kode pengiriman dahulu pada table untuk update status terkirim!");
        }else{
            if("Terkirim".equals(txtStatus.getText())){
                JOptionPane.showMessageDialog(null, "Kode pengiriman ini telah terkirim ke tujuan!");
            }else{
                if(JOptionPane.showConfirmDialog(null, "Apakah barang telah sampai tujuan?", "Konfirmasi", JOptionPane.YES_OPTION )==JOptionPane.YES_OPTION){
                    kirim.setStatus("Terkirim");
                    try{
                        pCont.update(kirim);
                    }catch(Exception ex){}
                    bersih();
                }
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtKodeKirim = new javax.swing.JTextField();
        txtNoBayar = new javax.swing.JTextField();
        jdcTanggal = new com.toedter.calendar.JDateChooser();
        txtNama = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        btCariBayar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKirim = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btStatus = new javax.swing.JButton();

        setBackground(new java.awt.Color(50, 77, 92));
        setTitle("Form Pengiriman");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(50, 77, 92));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Pengiriman", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Kode Pengiriman");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No Pembayaran");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tanggal Pengiriman");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama Penerima");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("No Telepon");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Alamat Penerima");

        txtKodeKirim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNoBayar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jdcTanggal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTelepon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtAlamat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btCariBayar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btCariBayar.setText("Cari");
        btCariBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariBayarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Status");

        txtStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25)
                        .addComponent(txtKodeKirim, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNoBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btCariBayar))
                            .addComponent(jdcTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtNoBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btCariBayar)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtKodeKirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jdcTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableKirim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableKirim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Pengiriman", "No Bayar", "Tanggal Pengiriman", "Nama Penerima", "No Telepon", "Alamat", "Status"
            }
        ));
        jScrollPane1.setViewportView(tableKirim);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cari data pengiriman");

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btCari.setText("Cari");
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Ubah status diterima");

        btStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btStatus.setText("OK");
        btStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCari)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCari)
                    .addComponent(jLabel9)
                    .addComponent(btStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCariBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariBayarActionPerformed
        PopBayar PB=new PopBayar();
        PB.FP=this;
        PB.setVisible(true);
    }//GEN-LAST:event_btCariBayarActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtKodeKirim.setEnabled(false);
        txtNoBayar.setEnabled(false);
        btCariBayar.setEnabled(false);
        jdcTanggal.setEnabled(false);
        txtNama.setEnabled(false);
        txtTelepon.setEnabled(false);
        txtAlamat.setEnabled(false);
        txtStatus.setEnabled(false);
        txtCari.setEnabled(false);
        btCari.setEnabled(false);
        btStatus.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btCariActionPerformed

    private void btStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStatusActionPerformed
        updateStatus();
    }//GEN-LAST:event_btStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JButton btCariBayar;
    private javax.swing.JButton btStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcTanggal;
    private javax.swing.JTable tableKirim;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKodeKirim;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoBayar;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTelepon;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        btCariBayar.setEnabled(true);
        jdcTanggal.setEnabled(true);
        txtNama.setEnabled(true);
        txtTelepon.setEnabled(true);
        txtAlamat.setEnabled(true);
        txtCari.setEnabled(true);
        btCari.setEnabled(true);
        btStatus.setEnabled(true);
        showTableKirim();
        renderTable();
        bersih();
    }

    @Override
    public void bersih() {
        txtNoBayar.setText("");
        txtNama.setText("");
        txtTelepon.setText("");
        txtAlamat.setText("");
        txtStatus.setText("");
        txtCari.setText("");
        txtKodeKirim.setText(pCont.kodePengirimanOto());
        showTableKirim();
        renderTable();
        setJDC(jdcTanggal);
    }
    
    @Override
    public void simpan() {
        boolean eksis=false;
        String data="";
        int baris=tableKirim.getRowCount();
        if("".equals(txtNoBayar.getText())||"".equals(txtNama.getText())
                ||"".equals(txtTelepon.getText())||"".equals(txtAlamat.getText())){
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
        }else{
            for(int i=0; i<baris;i++){
                data=tableKirim.getValueAt(i, 1).toString().trim();
                if(txtNoBayar.getText().equals(data)){
                    eksis=true;
                    break;
                }
            }
            
            if(!eksis){
                kirim=pCont.findPengiriman(txtKodeKirim.getText());
                Pengiriman krm=new Pengiriman();
                if(kirim==null){
                    krm.setKdPengiriman(txtKodeKirim.getText());
                    krm.setNoPembayaran(txtNoBayar.getText());
                    krm.setTglKirim(jdcTanggal.getDate());
                    krm.setKdPengiriman(txtKodeKirim.getText());
                    krm.setNmPenerima(txtNama.getText());
                    krm.setTelpPenerima(txtTelepon.getText());
                    krm.setAlamat(txtAlamat.getText());
                    krm.setStatus(txtStatus.getText());
                    try{
                        pCont.save(krm);
                        JOptionPane.showMessageDialog(null, "Data telah tersimpan! Status Pengiriman : "+kirim.getStatus());
                    }catch(Exception ex){}
                }else{
                    krm.setKdPengiriman(txtKodeKirim.getText());
                    krm.setNoPembayaran(txtNoBayar.getText());
                    krm.setTglKirim(jdcTanggal.getDate());
                    krm.setKdPengiriman(txtKodeKirim.getText());
                    krm.setNmPenerima(txtNama.getText());
                    krm.setTelpPenerima(txtTelepon.getText());
                    krm.setAlamat(txtAlamat.getText());
                    krm.setStatus(txtStatus.getText());
                    try{
                        pCont.update(krm);
                        JOptionPane.showMessageDialog(null, "Data telah tersimpan! Status Pengiriman : "+kirim.getStatus());
                    }catch(Exception ex){}
                }
                JOptionPane.showMessageDialog(null, "Penyimpanan berhasil! Barang siap dikirim!");
                bersih();
            }else{
                JOptionPane.showMessageDialog(null, "Oops, no pembayaran ini telah ada di table pengiriman!");
            }
        }
    }

    @Override
    public void hapus() {
        try{
            pCont.delete(txtKodeKirim.getText());
            JOptionPane.showMessageDialog(null, "Data telah di hapus!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Data tidak bisa di hapus karena "+ex.getMessage());
        }
        bersih();
    }

    @Override
    public void cari() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tampil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
