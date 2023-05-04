package controlador;
// Generated 20-abr-2023 11:11:44 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Trabajador generated by hbm2java
 */
public class Trabajador  implements java.io.Serializable {


     private int idTrabajador;
     private String nombre;
     private String apellido1;
     private String apellido2;
     private String nifnie;
     private String paisOrigen;
     private String email;
     private Date fechaAlta;
     private String codigoCuenta;
     private String iban;
     private Date bajaLaboral;
     private Date altaLaboral;
     private int idEmpresa;
     private int idCategoria;
     
     private Empresas empresa;
     private Categorias categoria;
     
     private boolean prorrata;

    public Trabajador() {
    }

    //CONSTRUCTOR PARA MAPEAR TRABAJADORES DEL EXCEL
    
    public Trabajador (int idTrabajador, String codigoCuenta, String iban, String email, Date fechaAlta,  String apellido1, String apellido2, String nombre, String nifnie, Date bajaLaboral, Date altaLaboral, String paisOrigen, boolean prorrata) {
        this.idTrabajador = idTrabajador;
        this.codigoCuenta = codigoCuenta;
        this.iban = iban;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
        this.nifnie = nifnie;
        this.bajaLaboral = bajaLaboral;
        this.altaLaboral = altaLaboral; 
        
        this.paisOrigen = paisOrigen;
        
        this.prorrata = prorrata;
    }
	
    public Trabajador(int idTrabajador, String nombre, String apellido1, String nifnie, int idEmpresa, int idCategoria) {
        this.idTrabajador = idTrabajador;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.nifnie = nifnie;
        this.idEmpresa = idEmpresa;
        this.idCategoria = idCategoria;
    }
    public Trabajador(int idTrabajador, String nombre, String apellido1, String apellido2, String nifnie, String email, Date fechaAlta, String codigoCuenta, String iban, Date bajaLaboral, Date altaLaboral, int idEmpresa, int idCategoria) {
       this.idTrabajador = idTrabajador;
       this.nombre = nombre;
       this.apellido1 = apellido1;
       this.apellido2 = apellido2;
       this.nifnie = nifnie;
       this.email = email;
       this.fechaAlta = fechaAlta;
       this.codigoCuenta = codigoCuenta;
       this.iban = iban;
       this.bajaLaboral = bajaLaboral;
       this.altaLaboral = altaLaboral;
       this.idEmpresa = idEmpresa;
       this.idCategoria = idCategoria;
    }
   
    public boolean getProrrata() {
        return this.prorrata;
    }
    
    public void setProrrata(boolean prorrata) {
        this.prorrata = prorrata;
    }
    
    public Categorias getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }
    
    public Empresas getEmpresa(){
        return this.empresa;
    }
    
    public void setEmpresa(Empresas empresa){
        this.empresa=empresa;
    }
    
    public String getPaisOrigen() {
        return this.paisOrigen;
    }
    
    public void setPaisOrigen(String paisOrigen){
        this.paisOrigen = paisOrigen;
    }
    
    public int getIdTrabajador() {
        return this.idTrabajador;
    }
    
    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido1() {
        return this.apellido1;
    }
    
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    public String getApellido2() {
        return this.apellido2;
    }
    
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    public String getNifnie() {
        return this.nifnie;
    }
    
    public void setNifnie(String nifnie) {
        this.nifnie = nifnie;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getFechaAlta() {
        return this.fechaAlta;
    }
    
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    public String getCodigoCuenta() {
        return this.codigoCuenta;
    }
    
    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }
    public String getIban() {
        return this.iban;
    }
    
    public void setIban(String iban) {
        this.iban = iban;
    }
    public Date getBajaLaboral() {
        return this.bajaLaboral;
    }
    
    public void setBajaLaboral(Date bajaLaboral) {
        this.bajaLaboral = bajaLaboral;
    }
    public Date getAltaLaboral() {
        return this.altaLaboral;
    }
    
    public void setAltaLaboral(Date altaLaboral) {
        this.altaLaboral = altaLaboral;
    }
    public int getIdEmpresa() {
        return this.idEmpresa;
    }
    
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    public int getIdCategoria() {
        return this.idCategoria;
    }
    
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    


}