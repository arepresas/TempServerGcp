package com.arepresas.TempServerGCP.rest.model;

import com.google.cloud.Timestamp;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TempData {

    private Long id;

    @NotNull
    private Long temperature;

    @NotNull
    private Long humidity;

    @NotNull
    private Timestamp dateTime;

    public TempData() {
        super();
    }
}
