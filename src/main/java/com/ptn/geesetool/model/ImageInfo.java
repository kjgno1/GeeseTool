package com.ptn.geesetool.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_IMAGE_INFO")
public class ImageInfo {
    @Id
    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "TAGS")
    private String tags;

    @Column(name = "DESCRIPTIONS")
    private String descriptions;

    @Column(name = "IS_UPLOADED")
    private int isUploaded = 0;

    @CreationTimestamp
    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    public ImageInfo( String name, String url, String tags, String descriptions) {

        this.name = name;
        this.url = url;
        this.tags = tags;
        this.descriptions = descriptions;
    }

    public ImageInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getIsUploaded() {
        return isUploaded;
    }

    public void setIsUploaded(int isUploaded) {
        this.isUploaded = isUploaded;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
