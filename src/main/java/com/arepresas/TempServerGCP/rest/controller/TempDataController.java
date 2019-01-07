package com.arepresas.TempServerGCP.rest.controller;

import com.arepresas.TempServerGCP.rest.model.TempData;
import com.arepresas.TempServerGCP.rest.service.TempService;
import com.google.cloud.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tempdata")
public class TempDataController {

    @Autowired
    TempService tempService;

    @RequestMapping(value="", method= RequestMethod.POST, consumes = "application/json")
    public boolean saveTempData(@RequestBody TempData tempData) {
        tempData.setDateTime(Timestamp.now());

        return tempService.addTempData(tempData) != null;
    }

    @RequestMapping(value="", method=RequestMethod.GET, produces = "application/json")
    public TempData getTempData(@RequestParam(value="id") Long id) {
        return tempService.getTempData(id);
    }

    @RequestMapping(value="", method=RequestMethod.DELETE)
    public void deleteTempData(@RequestParam(value="id") Long id) {
        tempService.deleteTempData(id);
    }


    @RequestMapping(value="/getList", method=RequestMethod.GET, produces = "application/json")
    public List<TempData> getTempDataList() {
        return tempService.getTempDataList();
    }

    @RequestMapping(value="/getLast", method= RequestMethod.GET, produces = "application/json")
    public TempData testGetLastTempData() {
        return tempService.getLastTempData();
    }

    // TESTS

    @RequestMapping(value="/test1", method= RequestMethod.GET)
    public Long testAddTempData() {
        Double randomTemp = Math.random() * (40 - 10);
        Double randomHum = Math.random() * (100 - 30);

        TempData tempData = new TempData();
        tempData.setDateTime(Timestamp.now());
        tempData.setTemperature(randomTemp.longValue());
        tempData.setHumidity(randomHum.longValue());

        return tempService.addTempData(tempData);
    }

    @RequestMapping(value="/test2", method= RequestMethod.GET, produces = "application/json")
    public TempData testGetLastTempDataTest() {
        return tempService.getLastTempData();
    }

    @RequestMapping(value="/test3", method= RequestMethod.GET, produces = "application/json")
    public List<TempData> testGetLast10TempData() {
        return tempService.getLastNTempData(10);
    }

}
