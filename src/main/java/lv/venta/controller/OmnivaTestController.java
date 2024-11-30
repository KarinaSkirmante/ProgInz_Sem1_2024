package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.service.IOmnivaService;

@RestController
public class OmnivaTestController {
	
	@Autowired
	private IOmnivaService omnivaService;
	
	
	@GetMapping("/start-omniva")
	public ResponseEntity<?> getMethodName() {
		try {
			omnivaService.saveAndUpdateParcelMachines();
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

}
