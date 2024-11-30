package lv.venta.service;

import java.util.ArrayList;
import java.util.List;

import lv.venta.helpers.model.Location;
import lv.venta.model.ParcelMachine;

public interface IOmnivaService {
	
	List<Location> fetchDataFromOmniva() throws Exception;
	void saveAndUpdateParcelMachines();
	
	
	ArrayList<ParcelMachine> retrieveAllParcelMachines();  
	ArrayList<ParcelMachine> findParcelMachinesByAddress(String address);
	
	

}
