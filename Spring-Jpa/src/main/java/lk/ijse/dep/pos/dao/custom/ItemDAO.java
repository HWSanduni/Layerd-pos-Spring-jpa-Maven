package lk.ijse.dep.pos.dao.custom;

import lk.ijse.dep.pos.entity.Item;

public interface ItemDAO extends CrudDAO<Item,String> {
    public String getLastitemCode()throws Exception;
/*    public List<Item> findAllItems();
    public Item findItem(String id);
    public boolean saveItem(Item item);
    public boolean updateItem(Item item);
    public boolean deleteItem(String id);*/
}
