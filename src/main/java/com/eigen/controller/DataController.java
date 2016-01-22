package com.eigen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eigen.iface.finder.HistDataFinder;

@RestController
public class DataController {
	
	@Autowired
	private HistDataFinder histDataFinder;

    @RequestMapping("/get_hist_data")
    public String getHistData(@RequestParam(value="sb", defaultValue="") String sSymbol,
    		@RequestParam(value="fr", defaultValue="") String sFrDate,
    		@RequestParam(value="to", defaultValue="") String sToDate) {
    	return histDataFinder.getData(sSymbol, sFrDate, sToDate);
    }
    
}
