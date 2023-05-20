package com.electric.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electric.entities.Meter;
import com.electric.repository.MeterRepository;
import com.electric.service.MeterService;

@Service
public class MeterServiceImpl implements MeterService{
	@Autowired MeterRepository meterRepository;

	 public Meter saveMeter(@Valid Meter meter) {
	        return meterRepository.save(meter);
	    }

	    public List<Meter> getAllMeters() {
	        return meterRepository.findAll();
	    }

	    public Meter getMeterById(Long meterId) {
	        return meterRepository.findById(meterId)
	                .orElseThrow(() -> new NoSuchElementException("Meter not found with ID: " + meterId));
	    }


	    public void deleteMeterById(Long meterId) {
	        meterRepository.deleteById(meterId);
	    }

		

}
