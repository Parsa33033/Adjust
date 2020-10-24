package com.adjust.api.domain;


import javax.persistence.*;

import java.io.Serializable;

import com.adjust.api.domain.enumeration.ProgramType;

/**
 * A ProgramDescription.
 */
@Entity
@Table(name = "program_description")
public class ProgramDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProgramType type;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public ProgramDescription text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ProgramType getType() {
        return type;
    }

    public ProgramDescription type(ProgramType type) {
        this.type = type;
        return this;
    }

    public void setType(ProgramType type) {
        this.type = type;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProgramDescription)) {
            return false;
        }
        return id != null && id.equals(((ProgramDescription) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProgramDescription{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
