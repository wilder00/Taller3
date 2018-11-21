/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miVentana;

import conexionBD.Conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mis.clases.CajaDePago;
import mis.clases.Cliente;
import mis.clases.Tienda;
import mis.clases.cola.ArrayCola;
import mis.clases.listaE.ListaLEG;
import mis.clases.listaE.NodoLEG;

/**
 *
 * @author Wilder
 */
public class MiFormulario extends javax.swing.JFrame {
    ListaLEG<Tienda> Sucursales;
    ArrayCola<Cliente> clientes;
    DefaultTableModel modelo;
    DefaultTableModel modeloTablaCliente;
    int codSucursalDefinido;
    int codCajaDefinido;
    Conectar conectar;
    /**
     * Creates new form MiFormulario
     */
    public MiFormulario() {
        initComponents();
        this.setLocationRelativeTo(this);
        Sucursales = new ListaLEG();
        String[] Titulo = {"Codigo de sucursal","Distrito"};
        String[] tituloTablaCliente = {"Dni","Monto A Pagar"};
        modelo = new DefaultTableModel(null,Titulo);
        this.modeloTablaCliente = new DefaultTableModel(null,tituloTablaCliente);
        jTableListaTienda.setModel(modelo);
        jTableColaDeClientes.setModel(this.modeloTablaCliente);
        conectar = new Conectar();
        
        //desabilitando botones
        /*
        jButtonEncolarCliente.setEnabled(false);
        jButtonclientesEnColaCaja1.setEnabled(false);
        jButtonclientesEnColaCaja2.setEnabled(false);
        jButtonclientesEnColaCaja3.setEnabled(false);
        jButtonclientesEnColaCaja4.setEnabled(false);
        jButtonclientesEnColaCaja5.setEnabled(false);
        jButtonclientesEnColaCaja6.setEnabled(false);
        jButtonclientesEnColaCaja7.setEnabled(false);
        jButtonclientesEnColaCaja8.setEnabled(false);
        jButtonclientesEnColaCaja9.setEnabled(false);
        jButtonclientesEnColaCaja10.setEnabled(false);
        jButtonVerRecaudacionDeCaja.setEnabled(false);*/
        //definir la sucursal en -1 que significa no definido;
        this.codSucursalDefinido = -1;
        this.codCajaDefinido = -1;
        cargar();
        comprobarCajas();
        actualizarListaTiendas();
        
    }
    
    public void cargar(){
        
        String sql="Select * from tienda";       
        Connection con=conectar.conexion();
        try {        
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            Tienda tienda;
            while(rs.next()){
                int cod = rs.getInt("cod_sucursal");
                String distri = rs.getString("distrito");
                tienda = new Tienda(cod , distri);
                this.Sucursales.insertarOrdenado(tienda);
            }       
        } catch (SQLException e) {
            System.out.println("Error ...."+e.toString());       
        }


        sql="Select * from cajadepago";       
        //Connection con=conectar.conexion();
        try {        
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            CajaDePago caja;
            while(rs.next()){
                int cod_suc = rs.getInt("cod_sucursal");
                int num_caja = rs.getInt("num_de_caja");
                float monto_recau = rs.getFloat("monto_recaudado");
                Tienda.montoTotalRecaudado = Tienda.montoTotalRecaudado+monto_recau;
                caja = new CajaDePago();
                caja.setMontoRecaudadoEnCaja(monto_recau);
                this.Sucursales.getTiendabyId(cod_suc).getCajas()[num_caja]=caja;
                
            }       
        } catch (SQLException e) {
            System.out.println("Error ...."+e.toString());       
        }
        
        
        
        sql="Select * from cliente";       
        //Connection con=conectar.conexion();
        try {        
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            Cliente cli;
            while(rs.next()){
                String dni = rs.getString("dni");
                float montoAPagar = rs.getFloat("monto_a_pagar");
                int codSucursal = rs.getInt("cod_sucursal");
                int codCaja = rs.getInt("num_de_caja");
                cli = new Cliente(dni,montoAPagar);
                this.Sucursales.getTiendabyId(codSucursal).getCajas()[codCaja].agregarCliente(cli);
                
            }       
        } catch (SQLException e) {
            System.out.println("Error ...."+e.toString());       
        }
        
        
        
        
    }  

    //funciones internas 
    private void comprobarCajas(){
        
        if(this.codSucursalDefinido != -1){
            Tienda tienda = this.Sucursales.getTiendabyId(this.codSucursalDefinido);
            if(tienda.cajaHabilitado(0))
                jButtonclientesEnColaCaja1.setEnabled(true);
            else
                jButtonclientesEnColaCaja1.setEnabled(false);
            if(tienda.cajaHabilitado(1))
                jButtonclientesEnColaCaja2.setEnabled(true);
            else
                jButtonclientesEnColaCaja2.setEnabled(false);
            if(tienda.cajaHabilitado(2))
                jButtonclientesEnColaCaja3.setEnabled(true);
            else
                jButtonclientesEnColaCaja3.setEnabled(false);
            if(tienda.cajaHabilitado(3))
                jButtonclientesEnColaCaja4.setEnabled(true);
            else
                jButtonclientesEnColaCaja4.setEnabled(false);
            if(tienda.cajaHabilitado(4))
                jButtonclientesEnColaCaja5.setEnabled(true);
            else
                jButtonclientesEnColaCaja5.setEnabled(false);
            if(tienda.cajaHabilitado(5))
                jButtonclientesEnColaCaja6.setEnabled(true);
            else
                jButtonclientesEnColaCaja6.setEnabled(false);
            if(tienda.cajaHabilitado(6))
                jButtonclientesEnColaCaja7.setEnabled(true);
            else
                jButtonclientesEnColaCaja7.setEnabled(false);
            if(tienda.cajaHabilitado(7))
                jButtonclientesEnColaCaja8.setEnabled(true);
            else
                jButtonclientesEnColaCaja8.setEnabled(false);
            if(tienda.cajaHabilitado(8))
                jButtonclientesEnColaCaja9.setEnabled(true);
            else
                jButtonclientesEnColaCaja9.setEnabled(false);
            if(tienda.cajaHabilitado(9))
                jButtonclientesEnColaCaja10.setEnabled(true);
            else
                jButtonclientesEnColaCaja10.setEnabled(false);
        }
        
    }
    private void actualizarColaClientes(){
        String[] tituloTablaCliente = {"Dni","Monto A Pagar"};
        this.modeloTablaCliente = new DefaultTableModel(null,tituloTablaCliente);
        CajaDePago caja = this.Sucursales.getTiendabyId(this.codSucursalDefinido).getCajas()[this.codCajaDefinido];
        ArrayCola<Cliente> colaClientes = caja.colaDeClientes();
        String[] fila;
        Cliente cli;
        while(!colaClientes.colaVacia()){
            fila = new String[2];
            cli = colaClientes.desencolar();
            fila[0] = cli.getDni();
            fila[1] = "s/. "+String.valueOf(cli.getMontoAPagar()) ;
            this.modeloTablaCliente.addRow(fila);
        }
        jTableColaDeClientes.setModel(this.modeloTablaCliente);
    }
    private void prepararParaCaja(int numDeCaja){
        this.codCajaDefinido = numDeCaja-1;
        jLabelCajaSeleccionada.setText("caja: "+(this.codCajaDefinido+1));
        jButtonVerRecaudacionDeCaja.setEnabled(true);
        this.actualizarColaClientes();
        jTextAreaRecaudacionDeCaja.setText("");
    }
    
    private void actualizarListaTiendas(){
        
         String[]Titulo = {"Codigo de sucursal","Distrito"};
         modelo = new DefaultTableModel(null,Titulo);
         
         
         NodoLEG<Tienda> aux = Sucursales.getPrimero();
         String[]Registro;
         while(aux!=null){
             Registro = new String[2];
             Registro[0] = String.valueOf(aux.getDato().getCodSucursal());
             Registro[1] = aux.getDato().getDistrito();
             
             modelo.addRow(Registro);
             aux = aux.getSiguiente();
         }
         
         jTableListaTienda.setModel(modelo);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldDistritoT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCodigoT = new javax.swing.JTextField();
        jButtonListarT = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaInfoDeTienda = new javax.swing.JTextArea();
        jButtonRecaudacionT = new javax.swing.JButton();
        jButtonVerLista = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaTienda = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jTextFieldCodigoEliminar = new javax.swing.JTextField();
        jButtonDeleteT = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabelSucursalDefinido = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCodigoSucursalDefinido = new javax.swing.JTextField();
        jButtonIngresarSucursalDefinido = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDNICliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldMontoAPagarCliente = new javax.swing.JTextField();
        jButtonEncolarCliente = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaRecaudacionDeCaja = new javax.swing.JTextArea();
        jButtonVerRecaudacionDeCaja = new javax.swing.JButton();
        jButtonclientesEnColaCaja1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableColaDeClientes = new javax.swing.JTable();
        jButtonclientesEnColaCaja2 = new javax.swing.JButton();
        jButtonclientesEnColaCaja3 = new javax.swing.JButton();
        jButtonclientesEnColaCaja4 = new javax.swing.JButton();
        jButtonclientesEnColaCaja5 = new javax.swing.JButton();
        jButtonclientesEnColaCaja10 = new javax.swing.JButton();
        jButtonclientesEnColaCaja9 = new javax.swing.JButton();
        jButtonclientesEnColaCaja8 = new javax.swing.JButton();
        jButtonclientesEnColaCaja7 = new javax.swing.JButton();
        jButtonclientesEnColaCaja6 = new javax.swing.JButton();
        jLabelCajaSeleccionada = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recaudacion En El Supermercado");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jTabbedPane1.setBackground(new java.awt.Color(153, 255, 153));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Insertar Sucursal"));

        jLabel1.setText("Código de sucursal:");

        jLabel2.setText("Distrito de sucursal:");

        jButtonListarT.setText("Listar Tienda");
        jButtonListarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldCodigoT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDistritoT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jButtonListarT)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldCodigoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldDistritoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonListarT)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Lista de Sucursales"));

        jTextAreaInfoDeTienda.setColumns(20);
        jTextAreaInfoDeTienda.setRows(5);
        jScrollPane5.setViewportView(jTextAreaInfoDeTienda);

        jButtonRecaudacionT.setText("Ver Recaudacion Total");
        jButtonRecaudacionT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecaudacionTActionPerformed(evt);
            }
        });

        jButtonVerLista.setText("Ver Tienda aleatoria");
        jButtonVerLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerListaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButtonVerLista, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jButtonRecaudacionT, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(125, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVerLista)
                    .addComponent(jButtonRecaudacionT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jTableListaTienda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo de sucursal", "Distrito"
            }
        ));
        jScrollPane1.setViewportView(jTableListaTienda);

        jPanel10.setBackground(new java.awt.Color(255, 102, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Eliminar Sucursal"));

        jButtonDeleteT.setText("Eliminar Tienda");
        jButtonDeleteT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteTActionPerformed(evt);
            }
        });

        jLabel8.setText("Código de sucursal:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel8)
                        .addGap(44, 44, 44)
                        .addComponent(jTextFieldCodigoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jButtonDeleteT, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCodigoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonDeleteT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("Insertar Tienda", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 102));

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelSucursalDefinido.setText("No Definió la sucursal");

        jLabel4.setText("Codigo sucursal:");

        jTextFieldCodigoSucursalDefinido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoSucursalDefinidoActionPerformed(evt);
            }
        });

        jButtonIngresarSucursalDefinido.setText("Ingresar");
        jButtonIngresarSucursalDefinido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIngresarSucursalDefinidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldCodigoSucursalDefinido, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(jButtonIngresarSucursalDefinido)
                .addGap(24, 24, 24))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabelSucursalDefinido, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSucursalDefinido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCodigoSucursalDefinido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonIngresarSucursalDefinido)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Encolar a cliente"));

        jLabel5.setText("D.N.I. :");

        jLabel6.setText("Monto a pagar:");

        jButtonEncolarCliente.setText("Enconlar Cliente");
        jButtonEncolarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEncolarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldMontoAPagarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(jTextFieldDNICliente)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jButtonEncolarCliente)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldDNICliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldMontoAPagarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jButtonEncolarCliente)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(153, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Clientes en cola"));

        jTextAreaRecaudacionDeCaja.setColumns(20);
        jTextAreaRecaudacionDeCaja.setRows(5);
        jScrollPane3.setViewportView(jTextAreaRecaudacionDeCaja);

        jButtonVerRecaudacionDeCaja.setText("Ver Recaudacion de Caja");
        jButtonVerRecaudacionDeCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerRecaudacionDeCajaActionPerformed(evt);
            }
        });

        jButtonclientesEnColaCaja1.setText("Caja 1");
        jButtonclientesEnColaCaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonclientesEnColaCaja1ActionPerformed(evt);
            }
        });

        jTableColaDeClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTableColaDeClientes);

        jButtonclientesEnColaCaja2.setText("Caja 2");
        jButtonclientesEnColaCaja2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonclientesEnColaCaja2ActionPerformed(evt);
            }
        });

        jButtonclientesEnColaCaja3.setText("Caja 3");
        jButtonclientesEnColaCaja3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonclientesEnColaCaja3ActionPerformed(evt);
            }
        });

        jButtonclientesEnColaCaja4.setText("Caja 4");
        jButtonclientesEnColaCaja4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonclientesEnColaCaja4ActionPerformed(evt);
            }
        });

        jButtonclientesEnColaCaja5.setText("Caja 5");
        jButtonclientesEnColaCaja5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonclientesEnColaCaja5ActionPerformed(evt);
            }
        });

        jButtonclientesEnColaCaja10.setText("Caja 10");
        jButtonclientesEnColaCaja10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonclientesEnColaCaja10ActionPerformed(evt);
            }
        });

        jButtonclientesEnColaCaja9.setText("Caja 9");
        jButtonclientesEnColaCaja9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonclientesEnColaCaja9ActionPerformed(evt);
            }
        });

        jButtonclientesEnColaCaja8.setText("Caja 8");
        jButtonclientesEnColaCaja8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonclientesEnColaCaja8ActionPerformed(evt);
            }
        });

        jButtonclientesEnColaCaja7.setText("Caja 7");
        jButtonclientesEnColaCaja7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonclientesEnColaCaja7ActionPerformed(evt);
            }
        });

        jButtonclientesEnColaCaja6.setText("Caja 6");
        jButtonclientesEnColaCaja6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonclientesEnColaCaja6ActionPerformed(evt);
            }
        });

        jLabelCajaSeleccionada.setText("Caja no seleccionada");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonclientesEnColaCaja1)
                                    .addComponent(jButtonclientesEnColaCaja2)
                                    .addComponent(jButtonclientesEnColaCaja3)
                                    .addComponent(jButtonclientesEnColaCaja4)
                                    .addComponent(jButtonclientesEnColaCaja5))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonclientesEnColaCaja6)
                                    .addComponent(jButtonclientesEnColaCaja7)
                                    .addComponent(jButtonclientesEnColaCaja8)
                                    .addComponent(jButtonclientesEnColaCaja9)
                                    .addComponent(jButtonclientesEnColaCaja10))
                                .addGap(50, 50, 50)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabelCajaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonVerRecaudacionDeCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 50, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jButtonclientesEnColaCaja6)
                                .addGap(35, 35, 35)
                                .addComponent(jButtonclientesEnColaCaja7)
                                .addGap(35, 35, 35)
                                .addComponent(jButtonclientesEnColaCaja8)
                                .addGap(35, 35, 35)
                                .addComponent(jButtonclientesEnColaCaja9)
                                .addGap(35, 35, 35)
                                .addComponent(jButtonclientesEnColaCaja10))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jButtonclientesEnColaCaja1)
                                .addGap(35, 35, 35)
                                .addComponent(jButtonclientesEnColaCaja2)
                                .addGap(35, 35, 35)
                                .addComponent(jButtonclientesEnColaCaja3)
                                .addGap(35, 35, 35)
                                .addComponent(jButtonclientesEnColaCaja4)
                                .addGap(35, 35, 35)
                                .addComponent(jButtonclientesEnColaCaja5))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVerRecaudacionDeCaja)
                    .addComponent(jLabelCajaSeleccionada))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Clientes de Sucursal", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCodigoSucursalDefinidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoSucursalDefinidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigoSucursalDefinidoActionPerformed

    private void jButtonclientesEnColaCaja5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonclientesEnColaCaja5ActionPerformed
        this.prepararParaCaja(5);
    }//GEN-LAST:event_jButtonclientesEnColaCaja5ActionPerformed

    private void jButtonclientesEnColaCaja6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonclientesEnColaCaja6ActionPerformed
        this.prepararParaCaja(6);
    }//GEN-LAST:event_jButtonclientesEnColaCaja6ActionPerformed

    
    private void jButtonListarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarTActionPerformed
         int codigo = Integer.parseInt(jTextFieldCodigoT.getText());
         String distrito = jTextFieldDistritoT.getText();
         Tienda S = new Tienda(codigo, distrito);
         Sucursales.insertarOrdenado(S);
         jTextFieldCodigoT.setText("");
         jTextFieldDistritoT.setText("");
         
         this.actualizarListaTiendas();

         
    }//GEN-LAST:event_jButtonListarTActionPerformed

    private void jButtonDeleteTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteTActionPerformed
        String cod = jTextFieldCodigoEliminar.getText();
        if(!cod.equalsIgnoreCase("")){
            int codigo = Integer.parseInt(cod);
            this.Sucursales.eliminarDato(codigo);
            
            jTextFieldCodigoEliminar.setText("");
            this.actualizarListaTiendas();
        }else{
            JOptionPane.showMessageDialog(this, "necesita ingresar un codigo");
        }
        
          
          
          
    }//GEN-LAST:event_jButtonDeleteTActionPerformed

    private void jButtonIngresarSucursalDefinidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIngresarSucursalDefinidoActionPerformed
        String cod = jTextFieldCodigoSucursalDefinido.getText();
        if(!cod.equalsIgnoreCase("")){
            int id = Integer.parseInt(cod);
            if(this.Sucursales.existeSucursal(id)){
                this.codSucursalDefinido = id;
                jLabelSucursalDefinido.setText("Esta en la Sucursal : "+this.codSucursalDefinido);
                this.comprobarCajas();
                jButtonEncolarCliente.setEnabled(true);

                String[] tituloTablaCliente = {"Dni","Monto A Pagar"};
                this.modeloTablaCliente = new DefaultTableModel(null,tituloTablaCliente);
                jTableColaDeClientes.setModel(this.modeloTablaCliente);
                   
            }else{
                JOptionPane.showMessageDialog(this, "no existe la sucursal de id: "+(id));
            }
            jTextFieldCodigoSucursalDefinido.setText("");
        }else{
            JOptionPane.showMessageDialog(this, "necesita agregar un id");
        }
        
        
        
        
    }//GEN-LAST:event_jButtonIngresarSucursalDefinidoActionPerformed

    private void jButtonEncolarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEncolarClienteActionPerformed
        String dni = jTextFieldDNICliente.getText();
        String monto = jTextFieldMontoAPagarCliente.getText();
        if(!dni.equalsIgnoreCase("") && !monto.equalsIgnoreCase("")){
            
            float montoAPagar = Float.parseFloat(monto);
            Cliente cli= new Cliente(dni, montoAPagar);
            int cajaInsertada = this.Sucursales.getTiendabyId(this.codSucursalDefinido).insertarClienteACaja(cli);
            
            this.comprobarCajas();
        
        }else{
            JOptionPane.showMessageDialog(this, "tiene que insertar informacion de cliente");
        }
        
        
        
        jTextFieldDNICliente.setText("");
        jTextFieldMontoAPagarCliente.setText("");
        
    }//GEN-LAST:event_jButtonEncolarClienteActionPerformed

    private void jButtonclientesEnColaCaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonclientesEnColaCaja1ActionPerformed
        this.prepararParaCaja(1);
        
        
    }//GEN-LAST:event_jButtonclientesEnColaCaja1ActionPerformed

    private void jButtonclientesEnColaCaja2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonclientesEnColaCaja2ActionPerformed
        this.prepararParaCaja(2);
    }//GEN-LAST:event_jButtonclientesEnColaCaja2ActionPerformed

    private void jButtonclientesEnColaCaja3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonclientesEnColaCaja3ActionPerformed
        this.prepararParaCaja(3);
    }//GEN-LAST:event_jButtonclientesEnColaCaja3ActionPerformed

    private void jButtonclientesEnColaCaja4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonclientesEnColaCaja4ActionPerformed
        this.prepararParaCaja(4);
    }//GEN-LAST:event_jButtonclientesEnColaCaja4ActionPerformed

    private void jButtonclientesEnColaCaja7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonclientesEnColaCaja7ActionPerformed
        this.prepararParaCaja(7);
    }//GEN-LAST:event_jButtonclientesEnColaCaja7ActionPerformed

    private void jButtonclientesEnColaCaja8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonclientesEnColaCaja8ActionPerformed
        this.prepararParaCaja(8);
    }//GEN-LAST:event_jButtonclientesEnColaCaja8ActionPerformed

    private void jButtonclientesEnColaCaja9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonclientesEnColaCaja9ActionPerformed
        this.prepararParaCaja(9);
    }//GEN-LAST:event_jButtonclientesEnColaCaja9ActionPerformed

    private void jButtonclientesEnColaCaja10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonclientesEnColaCaja10ActionPerformed
        this.prepararParaCaja(10);
    }//GEN-LAST:event_jButtonclientesEnColaCaja10ActionPerformed

    private void jButtonVerRecaudacionDeCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerRecaudacionDeCajaActionPerformed
        CajaDePago c = this.Sucursales.getTiendabyId(this.codSucursalDefinido).getCajas()[this.codCajaDefinido];
        c.calcularMontoRecaudado();
        String info = String.valueOf(c.getMontoRecaudadoEnCaja());
        jTextAreaRecaudacionDeCaja.setText("El monto recaudado en caja es: s/. "+ info);
        this.actualizarColaClientes();
    }//GEN-LAST:event_jButtonVerRecaudacionDeCajaActionPerformed

    private void jButtonVerListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerListaActionPerformed
        String info = this.Sucursales.infoDeTiendaAleatoria();
        jTextAreaInfoDeTienda.setText(info);
    }//GEN-LAST:event_jButtonVerListaActionPerformed

    private void jButtonRecaudacionTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecaudacionTActionPerformed
        String info = "monto recaudadado total es de: s/. "+ Tienda.montoTotalRecaudado;
        jTextAreaInfoDeTienda.setText(info);
    }//GEN-LAST:event_jButtonRecaudacionTActionPerformed

    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MiFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MiFormulario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDeleteT;
    private javax.swing.JButton jButtonEncolarCliente;
    private javax.swing.JButton jButtonIngresarSucursalDefinido;
    private javax.swing.JButton jButtonListarT;
    private javax.swing.JButton jButtonRecaudacionT;
    private javax.swing.JButton jButtonVerLista;
    private javax.swing.JButton jButtonVerRecaudacionDeCaja;
    private javax.swing.JButton jButtonclientesEnColaCaja1;
    private javax.swing.JButton jButtonclientesEnColaCaja10;
    private javax.swing.JButton jButtonclientesEnColaCaja2;
    private javax.swing.JButton jButtonclientesEnColaCaja3;
    private javax.swing.JButton jButtonclientesEnColaCaja4;
    private javax.swing.JButton jButtonclientesEnColaCaja5;
    private javax.swing.JButton jButtonclientesEnColaCaja6;
    private javax.swing.JButton jButtonclientesEnColaCaja7;
    private javax.swing.JButton jButtonclientesEnColaCaja8;
    private javax.swing.JButton jButtonclientesEnColaCaja9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelCajaSeleccionada;
    private javax.swing.JLabel jLabelSucursalDefinido;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableColaDeClientes;
    private javax.swing.JTable jTableListaTienda;
    private javax.swing.JTextArea jTextAreaInfoDeTienda;
    private javax.swing.JTextArea jTextAreaRecaudacionDeCaja;
    private javax.swing.JTextField jTextFieldCodigoEliminar;
    private javax.swing.JTextField jTextFieldCodigoSucursalDefinido;
    private javax.swing.JTextField jTextFieldCodigoT;
    private javax.swing.JTextField jTextFieldDNICliente;
    private javax.swing.JTextField jTextFieldDistritoT;
    private javax.swing.JTextField jTextFieldMontoAPagarCliente;
    // End of variables declaration//GEN-END:variables


}
