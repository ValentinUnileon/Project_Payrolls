package controlador;
// Generated 20-abr-2023 11:11:44 by Hibernate Tools 4.3.1



/**
 * Nomina generated by hbm2java
 */
public class Nomina  implements java.io.Serializable {


     private int idNomina;
     private int mes;
     private int anio;
     private int numeroTrienios;
     private double importeTrienios;
     private double importeSalarioMes;
     private double importeComplementoMes;
     private double valorProrrateo;
     private double brutoAnual;
     private double irpf;
     private double importeIrpf;
     private double baseEmpresario;
     private String seguridadSocialEmpresario;
     private double importeSeguridadSocialEmpresario;
     private double desempleoEmpresario;
     private double importeDesempleoEmpresario;
     private double formacionEmpresario;
     private double importeFormacionEmpresario;
     private double accidentesTrabajoEmpresario;
     private double importeAccidentesTrabajoEmpresario;
     private double fogasaempresario;
     private double importeFogasaempresario;
     private double seguridadSocialTrabajador;
     private double importeSeguridadSocialTrabajador;
     private double desempleoTrabajador;
     private double importeDesempleoTrabajador;
     private double formacionTrabajador;
     private double importeFormacionTrabajador;
     private double brutoNomina;
     private double liquidoNomina;
     private double costeTotalEmpresario;
     private int diasBaja;
     private double importeDescuentoBaja;
     private int idTrabajador;
     private Double meiTrabajador;
     private Double meiEmpresario;
     private Double importeMeiTrabajador;
     private Double importeMeiEmpresario;

    public Nomina() {
    }

	
    public Nomina(int idNomina, int mes, int anio, int numeroTrienios, double importeTrienios, double importeSalarioMes, double importeComplementoMes, double valorProrrateo, double brutoAnual, double irpf, double importeIrpf, double baseEmpresario, String seguridadSocialEmpresario, double importeSeguridadSocialEmpresario, double desempleoEmpresario, double importeDesempleoEmpresario, double formacionEmpresario, double importeFormacionEmpresario, double accidentesTrabajoEmpresario, double importeAccidentesTrabajoEmpresario, double fogasaempresario, double importeFogasaempresario, double seguridadSocialTrabajador, double importeSeguridadSocialTrabajador, double desempleoTrabajador, double importeDesempleoTrabajador, double formacionTrabajador, double importeFormacionTrabajador, double brutoNomina, double liquidoNomina, double costeTotalEmpresario, int diasBaja, double importeDescuentoBaja, int idTrabajador) {
        this.idNomina = idNomina;
        this.mes = mes;
        this.anio = anio;
        this.numeroTrienios = numeroTrienios;
        this.importeTrienios = importeTrienios;
        this.importeSalarioMes = importeSalarioMes;
        this.importeComplementoMes = importeComplementoMes;
        this.valorProrrateo = valorProrrateo;
        this.brutoAnual = brutoAnual;
        this.irpf = irpf;
        this.importeIrpf = importeIrpf;
        this.baseEmpresario = baseEmpresario;
        this.seguridadSocialEmpresario = seguridadSocialEmpresario;
        this.importeSeguridadSocialEmpresario = importeSeguridadSocialEmpresario;
        this.desempleoEmpresario = desempleoEmpresario;
        this.importeDesempleoEmpresario = importeDesempleoEmpresario;
        this.formacionEmpresario = formacionEmpresario;
        this.importeFormacionEmpresario = importeFormacionEmpresario;
        this.accidentesTrabajoEmpresario = accidentesTrabajoEmpresario;
        this.importeAccidentesTrabajoEmpresario = importeAccidentesTrabajoEmpresario;
        this.fogasaempresario = fogasaempresario;
        this.importeFogasaempresario = importeFogasaempresario;
        this.seguridadSocialTrabajador = seguridadSocialTrabajador;
        this.importeSeguridadSocialTrabajador = importeSeguridadSocialTrabajador;
        this.desempleoTrabajador = desempleoTrabajador;
        this.importeDesempleoTrabajador = importeDesempleoTrabajador;
        this.formacionTrabajador = formacionTrabajador;
        this.importeFormacionTrabajador = importeFormacionTrabajador;
        this.brutoNomina = brutoNomina;
        this.liquidoNomina = liquidoNomina;
        this.costeTotalEmpresario = costeTotalEmpresario;
        this.diasBaja = diasBaja;
        this.importeDescuentoBaja = importeDescuentoBaja;
        this.idTrabajador = idTrabajador;
    }
    public Nomina(int idNomina, int mes, int anio, int numeroTrienios, double importeTrienios, double importeSalarioMes, double importeComplementoMes, double valorProrrateo, double brutoAnual, double irpf, double importeIrpf, double baseEmpresario, String seguridadSocialEmpresario, double importeSeguridadSocialEmpresario, double desempleoEmpresario, double importeDesempleoEmpresario, double formacionEmpresario, double importeFormacionEmpresario, double accidentesTrabajoEmpresario, double importeAccidentesTrabajoEmpresario, double fogasaempresario, double importeFogasaempresario, double seguridadSocialTrabajador, double importeSeguridadSocialTrabajador, double desempleoTrabajador, double importeDesempleoTrabajador, double formacionTrabajador, double importeFormacionTrabajador, double brutoNomina, double liquidoNomina, double costeTotalEmpresario, int diasBaja, double importeDescuentoBaja, int idTrabajador, Double meiTrabajador, Double meiEmpresario, Double importeMeiTrabajador, Double importeMeiEmpresario) {
       this.idNomina = idNomina;
       this.mes = mes;
       this.anio = anio;
       this.numeroTrienios = numeroTrienios;
       this.importeTrienios = importeTrienios;
       this.importeSalarioMes = importeSalarioMes;
       this.importeComplementoMes = importeComplementoMes;
       this.valorProrrateo = valorProrrateo;
       this.brutoAnual = brutoAnual;
       this.irpf = irpf;
       this.importeIrpf = importeIrpf;
       this.baseEmpresario = baseEmpresario;
       this.seguridadSocialEmpresario = seguridadSocialEmpresario;
       this.importeSeguridadSocialEmpresario = importeSeguridadSocialEmpresario;
       this.desempleoEmpresario = desempleoEmpresario;
       this.importeDesempleoEmpresario = importeDesempleoEmpresario;
       this.formacionEmpresario = formacionEmpresario;
       this.importeFormacionEmpresario = importeFormacionEmpresario;
       this.accidentesTrabajoEmpresario = accidentesTrabajoEmpresario;
       this.importeAccidentesTrabajoEmpresario = importeAccidentesTrabajoEmpresario;
       this.fogasaempresario = fogasaempresario;
       this.importeFogasaempresario = importeFogasaempresario;
       this.seguridadSocialTrabajador = seguridadSocialTrabajador;
       this.importeSeguridadSocialTrabajador = importeSeguridadSocialTrabajador;
       this.desempleoTrabajador = desempleoTrabajador;
       this.importeDesempleoTrabajador = importeDesempleoTrabajador;
       this.formacionTrabajador = formacionTrabajador;
       this.importeFormacionTrabajador = importeFormacionTrabajador;
       this.brutoNomina = brutoNomina;
       this.liquidoNomina = liquidoNomina;
       this.costeTotalEmpresario = costeTotalEmpresario;
       this.diasBaja = diasBaja;
       this.importeDescuentoBaja = importeDescuentoBaja;
       this.idTrabajador = idTrabajador;
       this.meiTrabajador = meiTrabajador;
       this.meiEmpresario = meiEmpresario;
       this.importeMeiTrabajador = importeMeiTrabajador;
       this.importeMeiEmpresario = importeMeiEmpresario;
    }
   
    public int getIdNomina() {
        return this.idNomina;
    }
    
    public void setIdNomina(int idNomina) {
        this.idNomina = idNomina;
    }
    public int getMes() {
        return this.mes;
    }
    
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getAnio() {
        return this.anio;
    }
    
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public int getNumeroTrienios() {
        return this.numeroTrienios;
    }
    
    public void setNumeroTrienios(int numeroTrienios) {
        this.numeroTrienios = numeroTrienios;
    }
    public double getImporteTrienios() {
        return this.importeTrienios;
    }
    
    public void setImporteTrienios(double importeTrienios) {
        this.importeTrienios = importeTrienios;
    }
    public double getImporteSalarioMes() {
        return this.importeSalarioMes;
    }
    
    public void setImporteSalarioMes(double importeSalarioMes) {
        this.importeSalarioMes = importeSalarioMes;
    }
    public double getImporteComplementoMes() {
        return this.importeComplementoMes;
    }
    
    public void setImporteComplementoMes(double importeComplementoMes) {
        this.importeComplementoMes = importeComplementoMes;
    }
    public double getValorProrrateo() {
        return this.valorProrrateo;
    }
    
    public void setValorProrrateo(double valorProrrateo) {
        this.valorProrrateo = valorProrrateo;
    }
    public double getBrutoAnual() {
        return this.brutoAnual;
    }
    
    public void setBrutoAnual(double brutoAnual) {
        this.brutoAnual = brutoAnual;
    }
    public double getIrpf() {
        return this.irpf;
    }
    
    public void setIrpf(double irpf) {
        this.irpf = irpf;
    }
    public double getImporteIrpf() {
        return this.importeIrpf;
    }
    
    public void setImporteIrpf(double importeIrpf) {
        this.importeIrpf = importeIrpf;
    }
    public double getBaseEmpresario() {
        return this.baseEmpresario;
    }
    
    public void setBaseEmpresario(double baseEmpresario) {
        this.baseEmpresario = baseEmpresario;
    }
    public String getSeguridadSocialEmpresario() {
        return this.seguridadSocialEmpresario;
    }
    
    public void setSeguridadSocialEmpresario(String seguridadSocialEmpresario) {
        this.seguridadSocialEmpresario = seguridadSocialEmpresario;
    }
    public double getImporteSeguridadSocialEmpresario() {
        return this.importeSeguridadSocialEmpresario;
    }
    
    public void setImporteSeguridadSocialEmpresario(double importeSeguridadSocialEmpresario) {
        this.importeSeguridadSocialEmpresario = importeSeguridadSocialEmpresario;
    }
    public double getDesempleoEmpresario() {
        return this.desempleoEmpresario;
    }
    
    public void setDesempleoEmpresario(double desempleoEmpresario) {
        this.desempleoEmpresario = desempleoEmpresario;
    }
    public double getImporteDesempleoEmpresario() {
        return this.importeDesempleoEmpresario;
    }
    
    public void setImporteDesempleoEmpresario(double importeDesempleoEmpresario) {
        this.importeDesempleoEmpresario = importeDesempleoEmpresario;
    }
    public double getFormacionEmpresario() {
        return this.formacionEmpresario;
    }
    
    public void setFormacionEmpresario(double formacionEmpresario) {
        this.formacionEmpresario = formacionEmpresario;
    }
    public double getImporteFormacionEmpresario() {
        return this.importeFormacionEmpresario;
    }
    
    public void setImporteFormacionEmpresario(double importeFormacionEmpresario) {
        this.importeFormacionEmpresario = importeFormacionEmpresario;
    }
    public double getAccidentesTrabajoEmpresario() {
        return this.accidentesTrabajoEmpresario;
    }
    
    public void setAccidentesTrabajoEmpresario(double accidentesTrabajoEmpresario) {
        this.accidentesTrabajoEmpresario = accidentesTrabajoEmpresario;
    }
    public double getImporteAccidentesTrabajoEmpresario() {
        return this.importeAccidentesTrabajoEmpresario;
    }
    
    public void setImporteAccidentesTrabajoEmpresario(double importeAccidentesTrabajoEmpresario) {
        this.importeAccidentesTrabajoEmpresario = importeAccidentesTrabajoEmpresario;
    }
    public double getFogasaempresario() {
        return this.fogasaempresario;
    }
    
    public void setFogasaempresario(double fogasaempresario) {
        this.fogasaempresario = fogasaempresario;
    }
    public double getImporteFogasaempresario() {
        return this.importeFogasaempresario;
    }
    
    public void setImporteFogasaempresario(double importeFogasaempresario) {
        this.importeFogasaempresario = importeFogasaempresario;
    }
    public double getSeguridadSocialTrabajador() {
        return this.seguridadSocialTrabajador;
    }
    
    public void setSeguridadSocialTrabajador(double seguridadSocialTrabajador) {
        this.seguridadSocialTrabajador = seguridadSocialTrabajador;
    }
    public double getImporteSeguridadSocialTrabajador() {
        return this.importeSeguridadSocialTrabajador;
    }
    
    public void setImporteSeguridadSocialTrabajador(double importeSeguridadSocialTrabajador) {
        this.importeSeguridadSocialTrabajador = importeSeguridadSocialTrabajador;
    }
    public double getDesempleoTrabajador() {
        return this.desempleoTrabajador;
    }
    
    public void setDesempleoTrabajador(double desempleoTrabajador) {
        this.desempleoTrabajador = desempleoTrabajador;
    }
    public double getImporteDesempleoTrabajador() {
        return this.importeDesempleoTrabajador;
    }
    
    public void setImporteDesempleoTrabajador(double importeDesempleoTrabajador) {
        this.importeDesempleoTrabajador = importeDesempleoTrabajador;
    }
    public double getFormacionTrabajador() {
        return this.formacionTrabajador;
    }
    
    public void setFormacionTrabajador(double formacionTrabajador) {
        this.formacionTrabajador = formacionTrabajador;
    }
    public double getImporteFormacionTrabajador() {
        return this.importeFormacionTrabajador;
    }
    
    public void setImporteFormacionTrabajador(double importeFormacionTrabajador) {
        this.importeFormacionTrabajador = importeFormacionTrabajador;
    }
    public double getBrutoNomina() {
        return this.brutoNomina;
    }
    
    public void setBrutoNomina(double brutoNomina) {
        this.brutoNomina = brutoNomina;
    }
    public double getLiquidoNomina() {
        return this.liquidoNomina;
    }
    
    public void setLiquidoNomina(double liquidoNomina) {
        this.liquidoNomina = liquidoNomina;
    }
    public double getCosteTotalEmpresario() {
        return this.costeTotalEmpresario;
    }
    
    public void setCosteTotalEmpresario(double costeTotalEmpresario) {
        this.costeTotalEmpresario = costeTotalEmpresario;
    }
    public int getDiasBaja() {
        return this.diasBaja;
    }
    
    public void setDiasBaja(int diasBaja) {
        this.diasBaja = diasBaja;
    }
    public double getImporteDescuentoBaja() {
        return this.importeDescuentoBaja;
    }
    
    public void setImporteDescuentoBaja(double importeDescuentoBaja) {
        this.importeDescuentoBaja = importeDescuentoBaja;
    }
    public int getIdTrabajador() {
        return this.idTrabajador;
    }
    
    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }
    public Double getMeiTrabajador() {
        return this.meiTrabajador;
    }
    
    public void setMeiTrabajador(Double meiTrabajador) {
        this.meiTrabajador = meiTrabajador;
    }
    public Double getMeiEmpresario() {
        return this.meiEmpresario;
    }
    
    public void setMeiEmpresario(Double meiEmpresario) {
        this.meiEmpresario = meiEmpresario;
    }
    public Double getImporteMeiTrabajador() {
        return this.importeMeiTrabajador;
    }
    
    public void setImporteMeiTrabajador(Double importeMeiTrabajador) {
        this.importeMeiTrabajador = importeMeiTrabajador;
    }
    public Double getImporteMeiEmpresario() {
        return this.importeMeiEmpresario;
    }
    
    public void setImporteMeiEmpresario(Double importeMeiEmpresario) {
        this.importeMeiEmpresario = importeMeiEmpresario;
    }

    
    public String toString() {
        return "Nomina: " + 
                "\nId Nomina: " + idNomina + 
                "\nMes: " + mes + 
                "\nAnio: " + anio + 
                "\nNúmero Trienios: " + numeroTrienios + 
                "\nImporte Trienios: " + importeTrienios + 
                "\nImporte Salario Mes: " + importeSalarioMes + 
                "\nImporte Complemento Mes: " + importeComplementoMes + 
                "\nValor Prorrateo: " + valorProrrateo + 
                "\nBruto Anual: " + brutoAnual + 
                "\nIRPF: " + irpf + 
                "\nImporte IRPF: " + importeIrpf + 
                "\nBase Empresario: " + baseEmpresario + 
                "\nSeguridad Social Empresario: " + seguridadSocialEmpresario + 
                "\nImporte Seguridad Social Empresario: " + importeSeguridadSocialEmpresario + 
                "\nDesempleo Empresario: " + desempleoEmpresario + 
                "\nImporte Desempleo Empresario: " + importeDesempleoEmpresario + 
                "\nFormación Empresario: " + formacionEmpresario + 
                "\nImporte Formación Empresario: " + importeFormacionEmpresario + 
                "\nAccidentes Trabajo Empresario: " + accidentesTrabajoEmpresario + 
                "\nImporte Accidentes Trabajo Empresario: " + importeAccidentesTrabajoEmpresario + 
                "\nFOGASA Empresario: " + fogasaempresario + 
                "\nImporte FOGASA Empresario: " + importeFogasaempresario + 
                "\nSeguridad Social Trabajador: " + seguridadSocialTrabajador + 
                "\nImporte Seguridad Social Trabajador: " + importeSeguridadSocialTrabajador + 
                "\nDesempleo Trabajador: " + desempleoTrabajador + 
                "\nImporte Desempleo Trabajador: " + importeDesempleoTrabajador + 
                "\nFormación Trabajador: " + formacionTrabajador + 
                "\nImporte Formación Trabajador: " + importeFormacionTrabajador + 
                "\nBruto Nómina: " + brutoNomina + 
                "\nLíquido Nómina: " + liquidoNomina + 
                "\nCoste Total Empresario: " + costeTotalEmpresario + 
                "\nDías Baja: " + diasBaja + 
                "\nImporte Descuento Baja: " + importeDescuentoBaja + 
                "\nId Trabajador: " + idTrabajador + 
                "\nMEI Trabajador: " + meiTrabajador + 
                "\nMEI Empresario: " + meiEmpresario + 
                "\nImporte MEI Trabajador: " + importeMeiTrabajador + 
                "\nImporte MEI Empresario: " + importeMeiEmpresario;
    }



}


