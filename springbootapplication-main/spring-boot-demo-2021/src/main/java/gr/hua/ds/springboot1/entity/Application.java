package gr.hua.ds.springboot1.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="aid")
    private int aid;

    @Column(name = "applicationstatus")
    private int applicationstatus;

    @Column(name = "buyer_afm_number", nullable = false)
    private long buyer_afm_number;

    @Column(name = "trans_dept_address")
    private String trans_dept_address;

    @Column(name = "vehicle_id")
    private Integer vehicle_id;

    @Column(name = "license_plt_numb")
    private String license_plt_numb;

    @Column(name = "car_maker")
    private String car_maker;

    @Column(name = "car_mk_yr")
    private Integer car_mk_yr;

    public Application() {

    }

    public Application(int applicationstatus, long buyer_afm_number, String trans_dept_address, Integer vehicle_id, String license_plt_numb, String car_maker, Integer car_mk_yr) {
        this.applicationstatus = applicationstatus;
        this.buyer_afm_number = buyer_afm_number;
        this.trans_dept_address = trans_dept_address;
        this.vehicle_id = vehicle_id;
        this.license_plt_numb = license_plt_numb;
        this.car_maker = car_maker;
        this.car_mk_yr = car_mk_yr;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn (name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getApplicationstatus() {
        return applicationstatus;
    }

    public void setApplicationstatus(int applicationstatus) {
        this.applicationstatus = applicationstatus;
    }

    public long getBuyer_afm_number() {
        return buyer_afm_number;
    }

    public void setBuyer_afm_number(long buyer_afm_number) {
        this.buyer_afm_number = buyer_afm_number;
    }

    public String getTrans_dept_address() {
        return trans_dept_address;
    }

    public void setTrans_dept_address(String trans_dept_address) {
        this.trans_dept_address = trans_dept_address;
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getLicense_plt_numb() {
        return license_plt_numb;
    }

    public void setLicense_plt_numb(String license_plt_numb) {
        this.license_plt_numb = license_plt_numb;
    }

    public String getCar_maker() {
        return car_maker;
    }

    public void setCar_maker(String car_maker) {
        this.car_maker = car_maker;
    }

    public Integer getCar_mk_yr() {
        return car_mk_yr;
    }

    public void setCar_mk_yr(Integer car_mk_yr) {
        this.car_mk_yr = car_mk_yr;
    }

    @Override
    public String toString() {
        return "Application{" +
                "aid=" + aid +
                ", applicationstatus=" + applicationstatus +
                ", buyer_afm_number=" + buyer_afm_number +
                ", trans_dept_address='" + trans_dept_address + '\'' +
                ", vehicle_id='" + vehicle_id + '\'' +
                ", license_plt_numb='" + license_plt_numb + '\'' +
                ", car_maker='" + car_maker + '\'' +
                ", car_mk_yr=" + car_mk_yr +
                '}';
    }
}
