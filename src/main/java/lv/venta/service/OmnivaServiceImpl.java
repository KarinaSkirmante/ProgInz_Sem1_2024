package lv.venta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lv.venta.helpers.model.Location;
import lv.venta.model.GlobalParams;
import lv.venta.model.ParcelMachine;
import lv.venta.repo.IGlobalParamsRepo;
import lv.venta.repo.IParcelMachineRepo;


@Service
public class OmnivaServiceImpl implements IOmnivaService{

	
	@Autowired
	private IGlobalParamsRepo globalParamsRepo;
	@Autowired
	private IParcelMachineRepo parcelMRepo;
	
	
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
	@Scheduled(cron = "0 10 11 * * *") // <- katru dienu 10:30
	//@Scheduled(cron = "0 30 10 * * 6") // <- katru sestdienu 10:30
	public void saveAndUpdateParcelMachines() throws Exception {
		System.out.println("Load data in DB");
		List<Location> dataFromFetch = fetchDataFromOmniva();
		//TODO tikai LV atlasīt
		if (parcelMRepo.count() == 0)//Db ir tukša un ir jāielik visi dati no fetch
		{
			for(Location tempL: dataFromFetch)
			{
				String add = tempL.getRegion() + " " + tempL.getMunicipality() + " " + tempL.getCity()
				+ " " + tempL.getArea4() + " " + tempL.getStreet() + " " + tempL.getArea6()
				+ " " + tempL.getBuilding() + " " + tempL.getArea8();
				
				parcelMRepo.save(new ParcelMachine(tempL.getZip(), tempL.getName(),
						Integer.parseInt(tempL.getType()), tempL.getCountry(), add));
				
			}
		}
		else
		{
			
			
			//ir Location sarakstā, bet nav DB <- tad ir jauns pakomāts
			//nav Location sarakstā, bet ir DB <- tad pakomāts ir jādzēšs
			
			// TODO iet cauri abiem sarakstiem un skatīties adreses. ja adreses atsķirās, tad mainām DB
			
			
			Map<String, Location> pMachinesFromOmnivaAPI = new HashMap<String, Location>();
			
			for(Location tempL : dataFromFetch) {
				pMachinesFromOmnivaAPI.put(tempL.getZip(), tempL);
			}
			
			Map<String, ParcelMachine> pMachinesFromDB = new HashMap<String, ParcelMachine>();
			
			for(ParcelMachine tempP: parcelMRepo.findAll()) {
				pMachinesFromDB.put(tempP.getZip(), tempP);
			}
			
			
			//ir Location sarakstā, bet nav DB <- tad ir jauns pakomāts
			for(Location tempL:pMachinesFromOmnivaAPI.values()) {
				if(!pMachinesFromDB.containsKey(tempL.getZip()))
				{
					String add = tempL.getRegion() + " " + tempL.getMunicipality() + " " + tempL.getCity()
					+ " " + tempL.getArea4() + " " + tempL.getStreet() + " " + tempL.getArea6()
					+ " " + tempL.getBuilding() + " " + tempL.getArea8();
					
					parcelMRepo.save(new ParcelMachine(tempL.getZip(), tempL.getName(),
							Integer.parseInt(tempL.getType()), tempL.getCountry(), add));
				}
			}
			
			//nav Location sarakstā, bet ir DB <- tad pakomāts ir jādzēšs
			for(ParcelMachine tempP: pMachinesFromDB.values()) {
				if(!pMachinesFromOmnivaAPI.containsKey(tempP.getZip())) {
					parcelMRepo.delete(tempP);
				}
			}
			
			// TODO iet cauri abiem sarakstiem un skatīties adreses. ja adreses atsķirās, tad mainām DB
			
			
		}
		
	}

	@Override
	public ArrayList<ParcelMachine> retrieveAllParcelMachines() {
		return (ArrayList<ParcelMachine>) parcelMRepo.findAll();
	}

	
	//TODO mājas pabeigt
	@Override
	public ArrayList<ParcelMachine> findParcelMachinesByAddress(String address) {
		// TODO Auto-generated method stub
		return null;
	}

}
