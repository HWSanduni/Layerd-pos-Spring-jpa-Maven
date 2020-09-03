package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.entity.Customer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

@Component
public class CustomerDAOImpl extends CrudDAOImpl<Customer,String>implements CustomerDAO {

    public String getLastCustomerId()throws Exception{

        try {
            return (String) entityManager.createNativeQuery("SELECT id FROM Customer ORDER BY id DESC  LIMIT 1").getSingleResult();
        }catch (NoResultException n){
            return null;
        }


    }

}


