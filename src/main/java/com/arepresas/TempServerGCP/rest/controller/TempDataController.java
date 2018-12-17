package com.arepresas.TempServerGCP.rest.controller;

import com.arepresas.TempServerGCP.rest.model.TempData;
import com.arepresas.TempServerGCP.rest.service.TempService;
import com.google.cloud.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tempdata")
public class TempDataController {

    @Autowired
    TempService tempService;

    @RequestMapping(value="/save", method= RequestMethod.POST, consumes = "application/json")
    public boolean saveTempData(@RequestBody TempData tempData) {
        tempData.setDateTime(Timestamp.now());

        return tempService.addTempData(tempData) != null;
    }

    @RequestMapping(value="/tempdata", method=RequestMethod.GET, produces = "application/json")
    public String getTempData(@RequestParam(value="id") Long id) {
        tempService.getTempData(id);

        return null;
    }

    // TESTS

    @RequestMapping(value="/test1", method= RequestMethod.GET)
    public Long testAddTempData() {
        TempData tempData = new TempData();
        tempData.setDateTime(Timestamp.now());
        tempData.setTemperature(10L);
        tempData.setHumidity(100L);

        return tempService.addTempData(tempData);
    }

    @RequestMapping(value="/test2", method= RequestMethod.GET, produces = "application/json")
    public TempData testGetLastTempData() {
        return tempService.getLastTempData();
    }
}
