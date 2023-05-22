                    




//Impuestos
   /*
                    float nominaExtra=0;
                    nominaMensual = brutoAnual / 14; 
                 
                    nomina.setSeguridadSocialTrabajador(seguridadSocialTrabajador);
                    nomina.setImporteSeguridadSocialTrabajador((brutoAnual/12)*seguridadSocialTrabajador);

                    
                    nomina.setFormacionTrabajador(formacionTrabajador);
                    nomina.setImporteFormacionTrabajador((brutoAnual/12)*formacionTrabajador);
             
                    
                    if(fechaActual.getYear()>=123){
                        nomina.setMeiTrabajador(Double.parseDouble(""+meiTrabajador));
                        nomina.setImporteMeiTrabajador(Double.parseDouble(""+((brutoAnual/12)*meiTrabajador)));

                    }else{
                        nomina.setMeiTrabajador(0.0);
                        nomina.setImporteMeiTrabajador(0.0);
                    }
                    
                    
                    nomina.setDesempleoTrabajador(desempleoTrabajador);
                    nomina.setImporteDesempleoTrabajador((brutoAnual/12)*desempleoTrabajador);
                    System.out.println("desempleo t "+ nomina.getDesempleoTrabajador()+ "importe "+ nomina.getImporteDesempleoTrabajador());   

                                      
                    
                    float liquidoMensual = nominaMensual - Float.parseFloat(""+(nomina.getImporteSeguridadSocialTrabajador()
                            +nomina.getImporteFormacionTrabajador()
                            +nomina.getImporteMeiTrabajador()
                            +nomina.getImporteDesempleoTrabajador()

                    ));
                    

                    //Costes empresario
                    
                    nomina.setSeguridadSocialEmpresario(""+seguridadSocialEmpresario);              //CUIDADO ES UNA STRING
                    nomina.setImporteSeguridadSocialTrabajador(nominaMensual*seguridadSocialEmpresario);
                    
                    nomina.setFormacionEmpresario(formacionEmpresario);
                    nomina.setImporteFormacionEmpresario(nominaMensual*formacionEmpresario);
           
                    if(fechaActual.getYear()>=123){
                        nomina.setMeiEmpresario(Double.parseDouble(""+meiEmpresario));
                        nomina.setImporteMeiEmpresario(Double.parseDouble(""+(nominaMensual*meiEmpresario))); 
                    }else{
                        nomina.setMeiEmpresario(0.0);
                        nomina.setImporteMeiEmpresario(0.0);                         
                    }
                    

                    
                    nomina.setDesempleoEmpresario(desempleoEmpresario);
                    nomina.setImporteDesempleoEmpresario(nominaMensual*desempleoEmpresario);
                    
                    nomina.setAccidentesTrabajoEmpresario(accidentesTrabajo);
                    nomina.setImporteAccidentesTrabajoEmpresario(nominaMensual*accidentesTrabajo);
                    
                    nomina.setFogasaempresario(fogasa);
                    nomina.setImporteFogasaempresario(nominaMensual*fogasa);
                            
                    
                    float costeEmpresa = nominaMensual + Float.parseFloat(""+(nomina.getImporteSeguridadSocialEmpresario()
                            +nomina.getImporteFormacionEmpresario()
                            +nomina.getImporteMeiEmpresario()
                            +nomina.getImporteDesempleoEmpresario()
                            +nomina.getImporteAccidentesTrabajoEmpresario()
                            +nomina.getImporteFogasaempresario()
                            
                    ));

                    nomina.setBrutoNomina(nominaMensual);
                    nomina.setLiquidoNomina(liquidoMensual);
                    nomina.setCosteTotalEmpresario(costeEmpresa);
                    nomina.setIdTrabajador(trabajadoresHoja1.get(i).getIdTrabajador());
                    //fin

*/