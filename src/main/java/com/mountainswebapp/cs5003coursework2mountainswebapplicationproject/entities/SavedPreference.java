package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "saved_preferences")
public class SavedPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "savedItemID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "itemType", nullable = false)
    private String itemType;

    @NotNull
    @Column(name = "itemID", nullable = false)
    private Integer itemID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private Users usersID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public Users getUserID() {
        return usersID;
    }

    public void setUserID(Users usersID) {
        this.usersID = usersID;
    }

}