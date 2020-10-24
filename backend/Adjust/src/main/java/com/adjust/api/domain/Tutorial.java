package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Tutorial.
 */
@Entity
@Table(name = "tutorial")
public class Tutorial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "text")
    private String text;

    @Lob
    @Column(name = "thumbnail")
    private byte[] thumbnail;

    @Column(name = "thumbnail_content_type")
    private String thumbnailContentType;

    @Column(name = "token_price")
    private Double tokenPrice;

    @OneToOne
    @JoinColumn(unique = true)
    private TutorialVideo video;

    @ManyToOne
    @JsonIgnoreProperties(value = "tutorials", allowSetters = true)
    private Course course;

    @ManyToMany(mappedBy = "tutorials", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<AdjustClient> clients = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Tutorial title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Tutorial description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public Tutorial text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public Tutorial thumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailContentType() {
        return thumbnailContentType;
    }

    public Tutorial thumbnailContentType(String thumbnailContentType) {
        this.thumbnailContentType = thumbnailContentType;
        return this;
    }

    public void setThumbnailContentType(String thumbnailContentType) {
        this.thumbnailContentType = thumbnailContentType;
    }

    public Double getTokenPrice() {
        return tokenPrice;
    }

    public Tutorial tokenPrice(Double tokenPrice) {
        this.tokenPrice = tokenPrice;
        return this;
    }

    public void setTokenPrice(Double tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    public TutorialVideo getVideo() {
        return video;
    }

    public Tutorial video(TutorialVideo tutorialVideo) {
        this.video = tutorialVideo;
        return this;
    }

    public void setVideo(TutorialVideo tutorialVideo) {
        this.video = tutorialVideo;
    }

    public Course getCourse() {
        return course;
    }

    public Tutorial course(Course course) {
        this.course = course;
        return this;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<AdjustClient> getClients() {
        return clients;
    }

    public Tutorial clients(Set<AdjustClient> adjustClients) {
        this.clients = adjustClients;
        return this;
    }

    public Tutorial addClient(AdjustClient adjustClient) {
        this.clients.add(adjustClient);
        adjustClient.getTutorials().add(this);
        return this;
    }

    public Tutorial removeClient(AdjustClient adjustClient) {
        this.clients.remove(adjustClient);
        adjustClient.getTutorials().remove(this);
        return this;
    }

    public void setClients(Set<AdjustClient> adjustClients) {
        this.clients = adjustClients;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tutorial)) {
            return false;
        }
        return id != null && id.equals(((Tutorial) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tutorial{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", text='" + getText() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", thumbnailContentType='" + getThumbnailContentType() + "'" +
            ", tokenPrice=" + getTokenPrice() +
            "}";
    }
}
