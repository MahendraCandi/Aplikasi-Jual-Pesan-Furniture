/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasifurniture.form;

import aplikasifurniture.AplikasiFurniture;
import aplikasifurniture.data.*;
import aplikasifurniture.controller.BarangController;
import aplikasifurniture.controller.DetailPesananController;
import aplikasifurniture.controller.PelangganController;
import aplikasifurniture.controller.PemesananController;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Candi-PC
 */

public class FormPemesanan extends javax.swing.JInternalFrame implements NavigatorFormInt{

    PelangganController pelCont=new PelangganController(AplikasiFurniture.emf);
    BarangController bCont=new BarangController(AplikasiFurniture.emf);
    PemesananController pemCont=new PemesananController(AplikasiFurniture.emf);
    DetailPesananController dpCont=new DetailPesananController(AplikasiFurniture.emf);
    Pelanggan pelanggan=new Pelanggan();
    Barang barang=new Barang();
    Pemesanan pesan=new Pemesanan();
    DetailPesanan detailpesan=new DetailPesanan();
    DefaultTableModel model, model2;
    public String kdPel,nmPel, tlp, alm, eml, kdBar, nmBar, jns, ktg, uku, wrn, stk, dsk;
    public double hrga;
    String kd;
    /**
     * Creates new form FormPemesanan
     */
    
    public FormPemesanan(String kode) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Kuantitas");
        model.addColumn("Diskon");
        model.addColumn("Subtotal");
        
        model2 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model2.addColumn("No Pemesanan");
        model2.addColumn("Kode Pelanggan");
        model2.addColumn("Jenis Pesanan");
        model2.addColumn("Nama Pesanan");
        model2.addColumn("Tanggal Buat");
        model2.addColumn("Waktu Jadi");
        model2.addColumn("Total Harga");
        model2.addColumn("Uang Muka");
        model2.addColumn("Keterangan");
        kd=kode;
        tableBarang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablePemesanan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        showTableSewa();
        renderTable();
    }
    
    private void showTableSewa(){
        tablePemesanan.setModel(pemCont.showTableFormPemesanan(model2));
    }
    
    public void renderTable(){
        TableCellRenderer tbr = new DefaultTableCellRenderer(){
            SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("In-ID"));
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column){
                if(value instanceof Date){
                    value = sdf.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tablePemesanan.getColumnModel().getColumn(4).setCellRenderer(tbr);
        tablePemesanan.getColumnModel().getColumn(5).setCellRenderer(tbr);
    }
    
    private void setTanggal(){
        //membuat format waktu jam, hari bulan dan tahun dengan locale indonesia
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        Calendar cal=Calendar.getInstance();
        txtTanggal.setText(sdf.format(cal.getTime()));
    }
    
    private void jenisPemesanan(){
        if("Barang Jadi".equals(cmbJenisPesan.getSelectedItem().toString())){
            txtNamaPesanan.setEnabled(false);
            btCari.setEnabled(true);
            txtKodeBarang.setEnabled(false);
            txtNamaBarang.setEnabled(false);
            cmbJenisBarang.setEnabled(false);
            txtKategori.setEnabled(false);
            txtUkuran.setEnabled(false);
            txtHarga.setEnabled(false);
            txtWarna.setEnabled(false);
            txtStok.setEnabled(false);
            txtDiskon.setEnabled(false);
            jdcWaktu.setEnabled(false);
            setJDC(jdcWaktu);
            txtJumlahBarang.setEnabled(true);
            txtJumlahBarang.setValue(1);
            btTambah.setEnabled(true);
            btTambah.setText("Tambah");
            btHapus.setEnabled(true);
            btBersih.setEnabled(true);
            txtUangMuka.setEnabled(false);
            txtNamaPesanan.setText("Barang Jadi");
            txtKodeBarang.setText("");
            txtNamaBarang.setText("");
            cmbJenisBarang.setSelectedIndex(0);
            txtKategori.setText("");
            txtUkuran.setText("");
            txtHarga.setText("");
            txtWarna.setText("");
            txtStok.setText("");
            txtDiskon.setText("");
            txtGrandTotal.setText("");
            txtUangMuka.setText("0");
            pengembalianStok();
            bersihBarang();
        }else if("Barang Custom".equals(cmbJenisPesan.getSelectedItem().toString())){
            btCari.setEnabled(false);
            txtNamaPesanan.setEnabled(true);
            cmbJenisBarang.setEnabled(true);
            txtKategori.setEnabled(false);
            txtUkuran.setEnabled(false);
            txtHarga.setEnabled(true);
            txtWarna.setEnabled(false);
            setJDC(jdcWaktu);
            jdcWaktu.setEnabled(true);
            txtJumlahBarang.setEnabled(true);
            txtJumlahBarang.setValue(1);
            txtUangMuka.setEnabled(true);
            btTambah.setEnabled(true);
            btTambah.setText("Hitung");
            btHapus.setEnabled(false);
            btBersih.setEnabled(false);
            txtKodeBarang.setText(cmbJenisPesan.getSelectedItem().toString());
            txtNamaBarang.setText(cmbJenisPesan.getSelectedItem().toString());
            txtKategori.setText(cmbJenisPesan.getSelectedItem().toString());
            txtUkuran.setText(cmbJenisPesan.getSelectedItem().toString());
            txtWarna.setText(cmbJenisPesan.getSelectedItem().toString());
            txtHarga.setText("");
            txtStok.setText("0");
            txtDiskon.setText("0");
            txtNamaPesanan.setText("");
            txtNamaPesanan.requestFocus();
            pengembalianStok();
            bersihBarang();
        }
    }
    
    public void pilihPelanggan(){
        PopPelanggan PP=new PopPelanggan();
        PP.FP=this;
        txtKodePelanggan.setText(kdPel);
        txtNamaPelanggan.setText(nmPel);
        txtTelepon.setText(tlp);
        txtAlamat.setText(alm);
        txtEmail.setText(eml);
    }
    
    public void pilihBarang(){
        PopBarang PP=new PopBarang();
        PP.FP=this;
        txtKodeBarang.setText(kdBar);
        txtNamaBarang.setText(nmBar);
        cmbJenisBarang.setSelectedItem(jns);
        txtKategori.setText(ktg);
        txtUkuran.setText(uku);
        txtWarna.setText(wrn);
        txtHarga.setText(String.valueOf(hrga));
        txtStok.setText(stk);
        txtDiskon.setText(dsk);
    }

    private void setJDC(JDateChooser JDC){
        JDC.setLocale(Locale.forLanguageTag("in-ID")); //membuat locale indonesia
        JDC.setDateFormatString("EEEE, dd MMMM yyyy");
        JDC.setMinSelectableDate(new Date());
        JDC.setDate(new Date());
    }
    
    public void penguranganStok(){
        try{
        barang=bCont.findBarang(txtKodeBarang.getText());
        barang.setStok(barang.getStok()-(Integer.parseInt(txtJumlahBarang.getValue().toString())));
        
            bCont.update(barang);
        }catch(Exception ex){}
    }
    
    public void pengembalianStok(){
        int row=tableBarang.getRowCount();
            for(int i=0; i<row; i++){
                try{
                    barang=bCont.findBarang(tableBarang.getValueAt(i, 0).toString());
                    barang.setStok(barang.getStok()+(Integer.parseInt(tableBarang.getValueAt(i,3).toString())));
                    bCont.update(barang);
                }catch(Exception e){}
            }
    }
    
    private double subtotal(){
        long subtotal;
        float diskon=(float) (Float.parseFloat(txtDiskon.getText()))/100;
        float potongan=(Float.parseFloat(txtHarga.getText()))*diskon;
        long harga=(long)(Double.parseDouble(txtHarga.getText()))- (long)potongan;
        int jumlah=Integer.parseInt(txtJumlahBarang.getValue().toString());
        subtotal=(long)harga*jumlah;
        return subtotal;
    }
    
    private void tambahBarang(){
        if("Barang Custom".equals(cmbJenisPesan.getSelectedItem().toString())){
            
            if(txtHarga.getText().equalsIgnoreCase("")||txtNamaPesanan.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "Data belum lengkap!");
            }else{
                Object[] obj=new Object[6];
                obj[0]=txtKodeBarang.getText();
                obj[1]=txtNamaBarang.getText();
                obj[2]=txtHarga.getText();
                obj[3]=txtJumlahBarang.getValue().toString();
                obj[4]=txtDiskon.getText();
                obj[5]=String.valueOf(subtotal());
                model.setRowCount(0);
                model.addRow(obj);
                
                model.fireTableRowsUpdated(0, 0);
                tableBarang.setModel(model);
                hitungCustom();
                txtUangMuka.requestFocus();
            }
        }else{
            if(txtKodeBarang.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "Data Barang belum lengkap!");
            }else{
                Object[] obj=new Object[6];
                obj[0]=txtKodeBarang.getText();
                obj[1]=txtNamaBarang.getText();
                obj[2]=txtHarga.getText();
                obj[3]=txtJumlahBarang.getValue().toString();
                obj[4]=txtDiskon.getText();
                obj[5]=String.valueOf(subtotal());
                model.addRow(obj);
                tableBarang.setModel(model);
                hitungBarang();
                penguranganStok();
                txtUangMuka.setText("0");
            }
        }
    }
    
    private void hapusBarang(){
         int pilih=tableBarang.getSelectedRow();
            if(pilih==-1){
                 JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
            }else{
                try{
                    barang=bCont.findBarang(tableBarang.getValueAt(pilih, 0).toString());
                    barang.setStok(barang.getStok()+(Integer.parseInt(tableBarang.getValueAt(pilih, 3).toString())));
                    bCont.update(barang);
                }catch(Exception ex){}
                model.removeRow(pilih);
                hitungBarang();
            }
    }
    
     private void bersihBarang(){
        int baris=tableBarang.getRowCount();
        while(baris>0){        
            baris--;
            model.removeRow(baris);
        }
         hitungBarang();
    }
     
     private void hitungBarang(){
        int baris=tableBarang.getRowCount();
        long hitung=0,ttl=0;
        if(baris==0){
            txtGrandTotal.setText("");
        }else{
            for(int a=0;a<baris;a++){
                hitung=(long)(Double.parseDouble(tableBarang.getValueAt(a, 5).toString()));
                ttl=ttl+hitung;
                txtGrandTotal.setText(String.valueOf(ttl));
            }
        }
    }
      
    private void hitungCustom(){
        double harga=Double.parseDouble(txtHarga.getText());
        int jumlah=Integer.parseInt(txtJumlahBarang.getValue().toString());
        long total=(long)harga*jumlah;
        txtGrandTotal.setText(String.valueOf(total));
    }  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtNoPesan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNamaPelanggan = new javax.swing.JTextField();
        txtKodePelanggan = new javax.swing.JTextField();
        btCariPelanggan = new javax.swing.JButton();
        txtTelepon = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbJenisPesan = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNamaBarang = new javax.swing.JTextField();
        txtKategori = new javax.swing.JTextField();
        txtUkuran = new javax.swing.JTextField();
        txtKodeBarang = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDiskon = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        txtWarna = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        cmbJenisBarang = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txtTanggal = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtJumlahBarang = new javax.swing.JSpinner();
        btTambah = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btBersih = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtGrandTotal = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jdcWaktu = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        txtUangMuka = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtNamaPesanan = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePemesanan = new javax.swing.JTable();

        setTitle("Form Pemesanan");
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
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        txtNoPesan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No Pemesanan");

        jPanel2.setBackground(new java.awt.Color(50, 77, 92));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informasi Pelanggan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kode Pelanggan");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama Pelanggan");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Telepon");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Alamat");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email");

        txtNamaPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNamaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPelangganActionPerformed(evt);
            }
        });

        txtKodePelanggan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btCariPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btCariPelanggan.setText("Cari");
        btCariPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariPelangganActionPerformed(evt);
            }
        });

        txtTelepon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtAlamat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtKodePelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCariPelanggan))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtKodePelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCariPelanggan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Jenis pemesanan");

        cmbJenisPesan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbJenisPesan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Barang Jadi", "Barang Custom" }));
        cmbJenisPesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenisPesanActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(50, 77, 92));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informasi Barang", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Kode Barang");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nama Barang");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jenis Barang");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Kategori");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Ukuran");

        txtNamaBarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtKategori.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtUkuran.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtKodeBarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btCari.setText("Cari");
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Harga");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Warna");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Stok");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Diskon");

        txtDiskon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtStok.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtWarna.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHarga.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cmbJenisBarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbJenisBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Barang baku", "Barang mentah" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtKodeBarang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCari))
                    .addComponent(txtNamaBarang)
                    .addComponent(txtKategori)
                    .addComponent(txtUkuran)
                    .addComponent(cmbJenisBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtWarna, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCari)
                    .addComponent(jLabel13)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtWarna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbJenisBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableBarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga", "Kuantitas", "Diskon Barang", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tableBarang);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tanggal");

        txtTanggal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Jumlah Barang ");

        txtJumlahBarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtJumlahBarang.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));
        txtJumlahBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJumlahBarangKeyPressed(evt);
            }
        });

        btTambah.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btTambah.setText("Tambah");
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

        btHapus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btHapus.setText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });

        btBersih.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btBersih.setText("Bersih");
        btBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBersihActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Grand Total");

        txtGrandTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Waktu Selesai");

        jdcWaktu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Uang Muka");

        txtUangMuka.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Nama Pesanan");

        txtNamaPesanan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNamaPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPesananActionPerformed(evt);
            }
        });
        txtNamaPesanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamaPesananKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNoPesan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbJenisPesan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(693, 693, 693)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUangMuka)
                            .addComponent(txtGrandTotal))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBersih)
                        .addGap(347, 347, 347))))
            .addComponent(jScrollPane1)
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btBersih, btHapus, btTambah});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoPesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cmbJenisPesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtNamaPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcWaktu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(txtJumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btTambah)
                        .addComponent(btHapus)
                        .addComponent(btBersih)
                        .addComponent(jLabel22)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtUangMuka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Transaksi Pemesanan", jPanel1);

        tablePemesanan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablePemesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablePemesanan);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Data Pemesanan", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPesananActionPerformed

    private void txtNamaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPelangganActionPerformed

    private void btCariPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariPelangganActionPerformed
        PopPelanggan PP=new PopPelanggan();
        PP.FP=this;
        PP.setVisible(true);
    }//GEN-LAST:event_btCariPelangganActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        PopBarang PB=new PopBarang();
        PB.FP=this;
        PB.setVisible(true);
    }//GEN-LAST:event_btCariActionPerformed

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        String data="";
        boolean eksis=false;
        int baris=tableBarang.getRowCount();
        int stok=Integer.parseInt(txtStok.getText());
        int jumlah=Integer.parseInt(txtJumlahBarang.getValue().toString());
        if("Hitung".equals(btTambah.getText())){
            tambahBarang();
        }else{
            if(jumlah>stok){
                JOptionPane.showMessageDialog(null, "Jumlah barang lebih dari stok!");
            }else{
                for(int i=0; i<baris; i++){
                data=tableBarang.getValueAt(i, 0).toString().trim();
                    if(txtKodeBarang.getText().equals(data)){
                        eksis=true;
                        break;
                    }
                }
                
                if(!eksis){
                    tambahBarang();
                }else{
                    JOptionPane.showMessageDialog(null, "Barang sudah ada!");
                }
            }
        }
    }//GEN-LAST:event_btTambahActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        hapusBarang();
    }//GEN-LAST:event_btHapusActionPerformed

    private void btBersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBersihActionPerformed
        pengembalianStok();
        bersihBarang();
        txtKodeBarang.setText("");
         txtNamaBarang.setText("");
         cmbJenisBarang.setSelectedIndex(0);
         txtKategori.setText("");
         txtUkuran.setText("");
         txtHarga.setText("");
         txtWarna.setText("");
         txtStok.setText("");
         txtDiskon.setText("");
    }//GEN-LAST:event_btBersihActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtNoPesan.setEnabled(false);
        txtTanggal.setEnabled(false);
        cmbJenisPesan.setEnabled(false);
        txtNamaPesanan.setEnabled(false);
        txtKodePelanggan.setEnabled(false);
        txtNamaPelanggan.setEnabled(false);
        txtTelepon.setEnabled(false);
        txtAlamat.setEnabled(false);
        txtEmail.setEnabled(false);
        btCariPelanggan.setEnabled(false);
        btCari.setEnabled(false);
        txtKodeBarang.setEnabled(false);
        txtNamaBarang.setEnabled(false);
        cmbJenisBarang.setEnabled(false);
        txtKategori.setEnabled(false);
        txtUkuran.setEnabled(false);
        txtHarga.setEnabled(false);
        txtWarna.setEnabled(false);
        txtStok.setEnabled(false);
        txtDiskon.setEnabled(false);
        txtJumlahBarang.setEnabled(false);
        jdcWaktu.setEnabled(false);
        btTambah.setEnabled(false);
        btHapus.setEnabled(false);
        btBersih.setEnabled(false);
        txtGrandTotal.setEnabled(false);
        txtUangMuka.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void cmbJenisPesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisPesanActionPerformed
        jenisPemesanan();
    }//GEN-LAST:event_cmbJenisPesanActionPerformed

    private void txtNamaPesananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPesananKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER || evt.getKeyCode()==KeyEvent.VK_TAB ){
            txtHarga.requestFocus();
        }
    }//GEN-LAST:event_txtNamaPesananKeyPressed

    private void txtJumlahBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahBarangKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btTambahActionPerformed(null);
        }
    }//GEN-LAST:event_txtJumlahBarangKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBersih;
    private javax.swing.JButton btCari;
    private javax.swing.JButton btCariPelanggan;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btTambah;
    private javax.swing.JComboBox<String> cmbJenisBarang;
    private javax.swing.JComboBox<String> cmbJenisPesan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcWaktu;
    private javax.swing.JTable tableBarang;
    private javax.swing.JTable tablePemesanan;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGrandTotal;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JSpinner txtJumlahBarang;
    private javax.swing.JTextField txtKategori;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtKodePelanggan;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JTextField txtNamaPesanan;
    private javax.swing.JTextField txtNoPesan;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JTextField txtUangMuka;
    private javax.swing.JTextField txtUkuran;
    private javax.swing.JTextField txtWarna;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        cmbJenisPesan.setEnabled(true);
        btCariPelanggan.setEnabled(true);
        setTanggal();
        txtNoPesan.setText(pemCont.noPemesananOto());
        
    }

    @Override
    public void bersih() {
        cmbJenisPesan.setSelectedIndex(0);
        txtNamaPesanan.setText("Barang Jadi");
        txtKodePelanggan.setText("");
        txtNamaPelanggan.setText("");
        txtTelepon.setText("");
        txtAlamat.setText("");
        txtEmail.setText("");
        txtKodeBarang.setText("");
        txtNamaBarang.setText("");
        cmbJenisBarang.setSelectedIndex(0);
        txtKategori.setText("");
        txtUkuran.setText("");
        txtHarga.setText("");
        txtWarna.setText("");
        txtStok.setText("");
        txtDiskon.setText("");
        txtJumlahBarang.setValue(1);
        txtGrandTotal.setText("");
        txtUangMuka.setText("");
        txtNoPesan.setText(pemCont.noPemesananOto());
        setJDC(jdcWaktu);
        
    }

    @Override
    public void simpan() {
        if(txtKodePelanggan.getText().equalsIgnoreCase("")||txtKodeBarang.getText().equalsIgnoreCase("")||txtKategori.getText().equalsIgnoreCase("")||
                txtUkuran.getText().equalsIgnoreCase("")||txtHarga.getText().equalsIgnoreCase("")||txtWarna.getText().equalsIgnoreCase("")||
                txtUangMuka.getText().equalsIgnoreCase("")||txtGrandTotal.getText().equalsIgnoreCase("")||txtNamaPesanan.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
        }else{
            pesan.setNoPemesanan(txtNoPesan.getText());
            pesan.setKdPelanggan(txtKodePelanggan.getText());
            pesan.setJenisPemesanan(cmbJenisPesan.getSelectedItem().toString());
            pesan.setWaktu(jdcWaktu.getDate());
            pesan.setTotalHarga(Double.parseDouble(txtGrandTotal.getText()));
            pesan.setUangMuka(Double.parseDouble(txtUangMuka.getText()));
            pesan.setNamaPemesanan(txtNamaPesanan.getText());
            pesan.setTanggal(new Date());
            pesan.setKodeUser(kd);
            pesan.setKeterangan("Belum Lunas");
            try{
                pemCont.save(pesan);
            }catch(Exception e){}
            
            int row=tableBarang.getRowCount();
            for(int i=0; i<row; i++){
                detailpesan.setKdBarang(tableBarang.getValueAt(i, 0).toString());
                detailpesan.setHarga(Double.parseDouble(tableBarang.getValueAt(i, 2).toString()));
                detailpesan.setKuantitas(Integer.parseInt(tableBarang.getValueAt(i, 3).toString()));
                detailpesan.setDiskon(Integer.parseInt(tableBarang.getValueAt(i, 4).toString()));
                detailpesan.setSubtotal(Double.parseDouble(tableBarang.getValueAt(i, 5).toString()));
                detailpesan.setNoPemesanan(txtNoPesan.getText());
                try{
                    dpCont.saveDetail(detailpesan);
                    pelCont.updatePelanggan(txtKodePelanggan.getText(), "Sudah Pesan");
                }catch(Exception ex){}
            }
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            bersih();
        }
    }

    @Override
    public void hapus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
