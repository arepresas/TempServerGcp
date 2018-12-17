package com.arepresas.TempServerGCP.rest.service;

import com.arepresas.TempServerGCP.rest.dao.TempDataDao;
import com.arepresas.TempServerGCP.rest.model.TempData;
import com.google.cloud.datastore.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;


@Service
public class TempService {

    private final Logger log = LoggerFactory.getLogger(TempService.class);

    @Autowired
    TempDataDao tempDataDao;

    public Long addTempData(@NotNull TempData tempData) {
        return tempDataDao.putTempData(tempData).getKey().getId();
    }

    public Entity getTempData(@NotNull Long id) {
        return tempDataDao.getTempData(id);
    }

    public void deleteTempData(@NotNull TempData tempData) {
        tempDataDao.deleteTempData(tempData.getId());
    }

    public void deleteTempData(@NotNull Long id) {
        tempDataDao.deleteTempData(id);
    }

    public TempData getLastTempData() {
        return tempDataDao.getLastTempData(1).get(0);
    }
}
