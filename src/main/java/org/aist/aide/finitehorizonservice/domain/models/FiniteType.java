package org.aist.aide.finitehorizonservice.domain.models;

import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public class FiniteType {
    @Id
    private String typeId;
    @NotNull
    @NotEmpty
    private Set<String> values;

    public FiniteType(String typeId, @NotNull @NotEmpty Set<String> values) {
        this.typeId = typeId;
        this.values = values;
    }

    public String getTypeId() {
        return typeId;
    }

    public Set<String> getValues() {
        return values;
    }

    public void setValues(Set<String> values) {
        this.values = values;
    }
}
