package com.arepresas.TempServerGCP.rest.model;

import com.google.cloud.Timestamp;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class MaxMinData {
    @NotNull
    private Long minData;

    @NotNull
    private Long maxData;

    @NotNull
    private Timestamp dateTimeMin;

    @NotNull
    private Timestamp dateTimeMax;

    public MaxMinData() {
        super();
    }

    public MaxMinData(@NotNull Long minData, @NotNull Long maxData, @NotNull Timestamp dateTimeMin, @NotNull Timestamp dateTimeMax) {
        this.minData = minData;
        this.maxData = maxData;
        this.dateTimeMin = dateTimeMin;
        this.dateTimeMax = dateTimeMax;
    }
}
