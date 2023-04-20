package DAO;



import java.util.List;
import util.HibernateUtil;

import configuracion.Trabajador;
import org.hibernate.*;

/**
 *
 * @author Torre
 */
public class TrabajadorDAO {
    
    Session session;
    public Trabajador encontrarPorCif(String cif) {
        String HQL = "FROM Trabajador WHERE NIFNIE = :cif";
        Query query = session.createQuery(HQL);
        query.setParameter("cif", cif);
        List<Trabajador> result = query.list();
        if(result.size()==1){
            return (Trabajador) result.get(0);
        }
        return (Trabajador) null;
    }

    public void setConector(Session session) {
        this.session = session;
    }
}

