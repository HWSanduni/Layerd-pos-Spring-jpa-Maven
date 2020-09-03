package lk.ijse.dep.pos.business.custom.impl;

import lk.ijse.dep.pos.business.custom.CustomerBO;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.db.JPAUtil;
import lk.ijse.dep.pos.entity.Customer;
import lk.ijse.dep.pos.util.CustomerTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerBOImpl implements CustomerBO {
    @Autowired
    private CustomerDAO customerDAO ;

    public String getNewCustomerId() throws Exception {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        customerDAO.setEntityManager(entityManager);

        String lastCustomerId="";

        try{
            entityManager.getTransaction().begin();

            lastCustomerId = customerDAO.getLastCustomerId();

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }
        if (lastCustomerId == null){
            return "C001";
        }else{
            int maxId=  Integer.parseInt(lastCustomerId.replace("C",""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "C00" + maxId;
            } else if (maxId < 100) {
                id = "C0" + maxId;
            } else {
                id = "C" + maxId;
            }
            return id;
        }




    }

    public List<CustomerTM> getAllCustomers() throws Exception {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        customerDAO.setEntityManager(entityManager);

        List<Customer> allCustomers=null;

        try{
            entityManager.getTransaction().begin();
            allCustomers = customerDAO.findAll();
           entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

        List<CustomerTM> customers = new ArrayList<>();
        for (Customer customer : allCustomers) {
            customers.add(new CustomerTM(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customers;


    }

    public void saveCustomer(String id, String name, String address) throws Exception {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        customerDAO.setEntityManager(entityManager);


        try{
            entityManager.getTransaction().begin();

            customerDAO.save(new Customer(id,name,address));

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

    }

    public void deleteCustomer(String customerId)throws Exception {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        customerDAO.setEntityManager(entityManager);
        try{
            entityManager.getTransaction().begin();

            customerDAO.delete(customerId);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }


    }


    public void updateCustomer(String name, String address, String customerId)throws Exception{
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        customerDAO.setEntityManager(entityManager);
        try{
            entityManager.getTransaction().begin();

            customerDAO.update(new Customer(customerId, name, address));

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

    }
}
