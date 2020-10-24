package com.adjust.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;
import com.adjust.api.domain.enumeration.ProgramType;

/**
 * A DTO for the {@link com.adjust.api.domain.ProgramDescription} entity.
 */
public class ProgramDescriptionDTO implements Serializable {
    
    private Long id;

    @Lob
    private String text;

    private ProgramType type;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ProgramType getType() {
        return type;
    }

    public void setType(ProgramType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProgramDescriptionDTO)) {
            return false;
        }

        return id != null && id.equals(((ProgramDescriptionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProgramDescriptionDTO{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
