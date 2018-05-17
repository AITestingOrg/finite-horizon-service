package org.aist.aide.finitehorizonservice.domain.models;

import javax.validation.constraints.NotBlank;

public class NewCase {
    @NotBlank
    private String newCase;

    public NewCase(@NotBlank String newCase) {

        this.newCase = newCase;
    }

    public String getNewCase() {
        return newCase;
    }
}
