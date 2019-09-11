package com.innomalist.taxi.common.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.innomalist.taxi.common.BR;
import com.innomalist.taxi.common.utils.LatLngDeserializer;
import com.stfalcon.chatkit.commons.models.IUser;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Driver extends BaseObservable implements Serializable, IUser {
	@SerializedName("registration_timestamp")
	private Timestamp registrationTimestamp;

	@SerializedName("account_number")
	private String accountNumber;

	@SerializedName("media")
    private Media media;

	@SerializedName("car_media")
    private Media carMedia;

	@SerializedName("car_plate")
	private String carPlate;

	private String address;

	private Gender gender;

	private Integer rating;

	@SerializedName("info_changed")
	private int infoChanged;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("review_count")
	private int reviewCount;

	@SerializedName("car_color")
	private String carColor;

	@SerializedName("certificate_number")
	private String certificateNumber;

	private String password;

	private Double balance;

	@SerializedName("car_production_year")
	private Integer carProductionYear;

	private int id;

	@SerializedName("mobile_number")
	private long mobileNumber;

	@SerializedName("first_name")
	private String firstName;

	private Car car;

	private String email;

	private Status status;

	@SerializedName("documents_note")
	private String documentsNote;

	private List<Media> documents;

    private List<Service> services;

    @Bindable
    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
        notifyPropertyChanged(BR.media);
    }

    @Bindable
    public Media getCarMedia() {
        return carMedia;
    }

    public void setCarMedia(Media carMedia) {
        this.carMedia = carMedia;
        notifyPropertyChanged(BR.carMedia);
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Bindable
    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
        if(gender != null)
            notifyPropertyChanged(com.innomalist.taxi.common.BR.gender);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Double getBalance() {
        return balance;
    }

    public String getId() {
        return String.valueOf(this.id);
    }

    @Override
    public String getName() {
        return lastName;
    }

    @Override
    public String getAvatar() {
        return media == null ? null : media.getAddress();
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Media> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Media> documents) {
        this.documents = documents;
    }

    public String getDocumentsNote() {
        return documentsNote;
    }

    public void setDocumentsNote(String documentsNote) {
        this.documentsNote = documentsNote;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public static Driver fromJson(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson customGson = gsonBuilder.create();
        return customGson.fromJson(json,Driver.class);
    }

    public String toJson(){
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.create();
        return gson.toJson(this);
    }
    public enum Status {
        @SerializedName("offline")
        OFFLINE("offline"),

        @SerializedName("online")
        ONLINE("online"),

        @SerializedName("in service")
        IN_SERVICE("in service"),

        @SerializedName("blocked")
        BLOCKED("blocked"),

        @SerializedName("pending approval")
        PENDING_APPROVAL("pending approval"),

        @SerializedName("waiting documents")
        WAITING_DOCUMENTS("waiting documents"),

        @SerializedName("soft reject")
        SOFT_REJECT("soft reject"),

        @SerializedName("hard reject")
        HARD_REJECT("hard reject");


        private String value;

        Status(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

        public static Status get(String code) {
            for(Status s : values()) {
                if(s.value.equals(code)) return s;
            }
            return OFFLINE;
        }
    }
}