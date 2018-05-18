package org.aist.aide.finitehorizonservice.domain.models;

import javax.validation.constraints.NotBlank;

public class AdditionalValue {
    @NotBlank
    private String additionalValue;

    public AdditionalValue(@NotBlank String additionalValue) {

        this.additionalValue = additionalValue;
    }

    public String getAdditionalValue() {
        return additionalValue;
    }
}
