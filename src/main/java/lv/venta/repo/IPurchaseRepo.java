package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Purchase;

public interface IPurchaseRepo extends CrudRepository<Purchase, Integer>{

}
