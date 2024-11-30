package lv.venta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lv.venta.helpers.model.Location;
import lv.venta.model.GlobalParams;
import lv.venta.model.ParcelMachine;
import lv.venta.repo.IGlobalParamsRepo;

public class OmnivaServiceImpl implements IOmnivaService{

	
	@Autowired
	private IGlobalParamsRepo globalParamsRepo;
	
	
	@Override
	public List<Location> fetchDataFromOmniva() throws Exception{
		RestTemplate rest = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		
		GlobalParams param = globalParamsRepo.findByParamTitle("omniva_api_link");
		String jsonResponse = rest.getForObject(param.getParamValue(), String.class);
		
		try
		{
			List<Location> locations = mapper.readValue(jsonResponse, new TypeReference<List<Location>>() {});
			return locations;
		}
		catch (Exception e) {
			throw new Exception();
		}
		
		
	}

	@Override
	
	//@Scheduled(cron = "0 0 4 * * *") <- katru dienu četros no rīta
	@Scheduled(cron = "0 30 10 * * *") // <- katru dienu 10:30
	//@Scheduled(cron = "0 30 10 * * 6") // <- katru sestdienu 10:30
	public void saveAndUpdateParcelMachines() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ParcelMachine> retrieveAllParcelMachines() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ParcelMachine> findParcelMachinesByAddress(String address) {
		// TODO Auto-generated method stub
		return null;
	}

}
