package com.electric.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electric.entities.Meter;
import com.electric.service.MeterService;

@RestController
@RequestMapping("/meters")
public class MeterController {
	@Autowired MeterService meterService;
	
	 @PostMapping
	    public Meter createMeter(@Valid @RequestBody Meter meter) {
	        return meterService.saveMeter(meter);
	    }

	   
}
