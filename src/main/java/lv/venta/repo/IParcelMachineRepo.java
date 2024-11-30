package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.ParcelMachine;

public interface IParcelMachineRepo extends CrudRepository<ParcelMachine, Integer> {

}
