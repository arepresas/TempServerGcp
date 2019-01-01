package com.arepresas.TempServerGCP.rest.dao;

import com.arepresas.TempServerGCP.rest.model.TempData;
import com.google.cloud.datastore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class TempDataDao {
    private final Logger log = LoggerFactory.getLogger(TempData.class);

    @Autowired
    Datastore datastore;

    private KeyFactory tempDataKeyFactory;

    @PostConstruct
    public void initializeKeyFactories() {
        log.info("Initializing key factories");
        tempDataKeyFactory = datastore.newKeyFactory().setKind("TempData");
    }

    public Entity putTempData(TempData tempData) {
        return datastore.put(tempDataToEntity(tempData));
    }

    public TempData getTempData(@NotNull Long id) {
        return entityToTempData(
                datastore.get(tempDataKeyFactory.newKey(id))
        );
    }

    public void deleteTempData(@NotNull Long id) {
        datastore.delete(tempDataKeyFactory.newKey(id));
    }

    public List<TempData> getTempDataList() {
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("TempData")
                .setOrderBy(StructuredQuery.OrderBy.desc("dateTime"))
                .build();

        return queryResultsToList(datastore.run(query)); // .stream().limit(nResults).collect(Collectors.toList());
    }

    public List<TempData> getLastNTempData(int nResults) {
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("TempData")
                .setOrderBy(StructuredQuery.OrderBy.desc("dateTime"))
                .setLimit(nResults)
                .build();

        return queryResultsToList(datastore.run(query)); // .stream().limit(nResults).collect(Collectors.toList());
    }

    private List<TempData> queryResultsToList(QueryResults<Entity> queryResults) {
        List<TempData> resultList = new ArrayList<>();
        while (queryResults.hasNext()) {
            Entity entity = queryResults.next();
            resultList.add(entityToTempData(entity));
        }

        return resultList;
    }

    private Entity tempDataToEntity(TempData tempData) {
        Key key;
        if (tempData.getId() != null) {
            key = tempDataKeyFactory.newKey(tempData.getId());
        } else {
            key = datastore.allocateId(tempDataKeyFactory.newKey());
        }

        return Entity.newBuilder(key)
                .set("temperature", tempData.getTemperature())
                .set("humidity", tempData.getHumidity())
                .set("dateTime", tempData.getDateTime())
                .build();
    }

    private TempData entityToTempData(Entity entity) {
        TempData result = new TempData();
        result.setId(entity.getKey().getId());
        result.setDateTime(entity.getTimestamp("dateTime"));
        result.setTemperature(entity.getLong("temperature"));
        result.setHumidity(entity.getLong("humidity"));

        return result;
    }
}
