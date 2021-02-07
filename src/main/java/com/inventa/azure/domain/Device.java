package com.inventa.azure.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inventa.azure.enums.DeviceTypeEnum;
import com.inventa.azure.enums.ParentCategoryEnum;
import com.inventa.azure.valueobject.Common;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Document("devices")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device {

    @Id
    private ObjectId _id;
    private DeviceTypeEnum type;

    private Common common;

    private Map<String,Object> adapters;

    private Set<String> adapterProperties;

    private List<String> tags;
    private String note;
    private Boolean isRemoved = false;

    private Date firstFetchTime = new Date();
    private Date fetchTime;


    private Set<String> instanceAssociationSet;

    private ParentCategoryEnum parentType;


    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public DeviceTypeEnum getType() {
        return type;
    }

    public void setType(DeviceTypeEnum type) {
        this.type = type;
    }

    public Map<String, Object> getAdapters() {
        return adapters;
    }

    public void setAdapters(Map<String, Object> adapters) {
        this.adapters = adapters;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getFirstFetchTime() {
        return firstFetchTime;
    }

    public void setFirstFetchTime(Date firstFetchTime) {
        this.firstFetchTime = firstFetchTime;
    }

    public Date getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Date fetchTime) {
        this.fetchTime = fetchTime;
    }

    public Set<String> getAdapterProperties() {
        return adapterProperties;
    }

    public void setAdapterProperties(Set<String> adapterProperties) {
        this.adapterProperties = adapterProperties;
    }

    public Boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(Boolean removed) {
        isRemoved = removed;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Set<String> getInstanceAssociationSet() {
        return instanceAssociationSet;
    }

    public void setInstanceAssociationSet(Set<String> instanceAssociationSet) {
        this.instanceAssociationSet = instanceAssociationSet;
    }

    public ParentCategoryEnum getParentType() {
        return parentType;
    }

    public void setParentType(ParentCategoryEnum parentType) {
        this.parentType = parentType;
    }
}
