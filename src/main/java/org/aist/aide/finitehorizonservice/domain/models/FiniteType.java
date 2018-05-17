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
    private Set<String> cases;

    public FiniteType(String typeId, @NotNull @NotEmpty Set<String> cases) {
        this.typeId = typeId;
        this.cases = cases;
    }

    public String getTypeId() {
        return typeId;
    }

    public Set<String> getCases() {
        return cases;
    }

    public void setCases(Set<String> cases) {
        this.cases = cases;
    }
}
