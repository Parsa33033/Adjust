package com.adjust.api.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.adjust.api.domain.enumeration.Gender;

/**
 * A AdjustClient.
 */
@Entity
@Table(name = "adjust_client")
public class AdjustClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "token")
    private Double token;

    @Column(name = "score")
    private Double score;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @OneToOne
    @JoinColumn(unique = true)
    private MedicalProfile medicalProfile;

    @OneToMany(mappedBy = "adjustClient", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "adjustClient", fetch = FetchType.EAGER)
    private Set<AdjustProgram> adjustPrograms = new HashSet<>();

    @OneToMany(mappedBy = "adjustClient", fetch = FetchType.EAGER)
    private Set<Conversation> conversations = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "adjust_client_tutorials",
               joinColumns = @JoinColumn(name = "adjust_client_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tutorials_id", referencedColumnName = "id"))
    private Set<Tutorial> tutorials = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public AdjustClient username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public AdjustClient firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AdjustClient lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public AdjustClient birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public AdjustClient age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public AdjustClient gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getToken() {
        return token;
    }

    public AdjustClient token(Double token) {
        this.token = token;
        return this;
    }

    public void setToken(Double token) {
        this.token = token;
    }

    public Double getScore() {
        return score;
    }

    public AdjustClient score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public byte[] getImage() {
        return image;
    }

    public AdjustClient image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public AdjustClient imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public MedicalProfile getMedicalProfile() {
        return medicalProfile;
    }

    public AdjustClient medicalProfile(MedicalProfile medicalProfile) {
        this.medicalProfile = medicalProfile;
        return this;
    }

    public void setMedicalProfile(MedicalProfile medicalProfile) {
        this.medicalProfile = medicalProfile;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public AdjustClient orders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    public AdjustClient addOrders(Order order) {
        this.orders.add(order);
        order.setAdjustClient(this);
        return this;
    }

    public AdjustClient removeOrders(Order order) {
        this.orders.remove(order);
        order.setAdjustClient(null);
        return this;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<AdjustProgram> getAdjustPrograms() {
        return adjustPrograms;
    }

    public AdjustClient adjustPrograms(Set<AdjustProgram> adjustPrograms) {
        this.adjustPrograms = adjustPrograms;
        return this;
    }

    public AdjustClient addAdjustPrograms(AdjustProgram adjustProgram) {
        this.adjustPrograms.add(adjustProgram);
        adjustProgram.setAdjustClient(this);
        return this;
    }

    public AdjustClient removeAdjustPrograms(AdjustProgram adjustProgram) {
        this.adjustPrograms.remove(adjustProgram);
        adjustProgram.setAdjustClient(null);
        return this;
    }

    public void setAdjustPrograms(Set<AdjustProgram> adjustPrograms) {
        this.adjustPrograms = adjustPrograms;
    }

    public Set<Conversation> getConversations() {
        return conversations;
    }

    public AdjustClient conversations(Set<Conversation> conversations) {
        this.conversations = conversations;
        return this;
    }

    public AdjustClient addConversations(Conversation conversation) {
        this.conversations.add(conversation);
        conversation.setAdjustClient(this);
        return this;
    }

    public AdjustClient removeConversations(Conversation conversation) {
        this.conversations.remove(conversation);
        conversation.setAdjustClient(null);
        return this;
    }

    public void setConversations(Set<Conversation> conversations) {
        this.conversations = conversations;
    }

    public Set<Tutorial> getTutorials() {
        return tutorials;
    }

    public AdjustClient tutorials(Set<Tutorial> tutorials) {
        this.tutorials = tutorials;
        return this;
    }

    public AdjustClient addTutorials(Tutorial tutorial) {
        this.tutorials.add(tutorial);
        tutorial.getClients().add(this);
        return this;
    }

    public AdjustClient removeTutorials(Tutorial tutorial) {
        this.tutorials.remove(tutorial);
        tutorial.getClients().remove(this);
        return this;
    }

    public void setTutorials(Set<Tutorial> tutorials) {
        this.tutorials = tutorials;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustClient)) {
            return false;
        }
        return id != null && id.equals(((AdjustClient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustClient{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", age=" + getAge() +
            ", gender='" + getGender() + "'" +
            ", token=" + getToken() +
            ", score=" + getScore() +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}
