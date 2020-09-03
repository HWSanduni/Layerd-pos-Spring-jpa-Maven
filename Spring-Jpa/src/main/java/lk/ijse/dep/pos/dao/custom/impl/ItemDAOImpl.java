package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.ItemDAO;
import lk.ijse.dep.pos.entity.Item;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ItemDAOImpl extends CrudDAOImpl<Item,String> implements ItemDAO {


    public String getLastitemCode() throws Exception{
       List list = entityManager.createQuery("SELECT i.itemCode FROM entity.Item i ORDER BY i.itemCode DESC").setMaxResults(1).getResultList();
        return (list.size()>0) ? (String) list.get(0):null;
    }


}
