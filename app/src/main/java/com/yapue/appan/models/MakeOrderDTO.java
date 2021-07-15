package com.yapue.appan.models;

import java.io.Serializable;

public class MakeOrderDTO implements Serializable {

    String id = "";
    String user_id = "";
    String order_id = "";
    float total_price = 0f;
    float final_price = 0f;
    String status = "";
    String discount = "";
    float cod_charges = 0f;
    String order_date = "";
    String place_date = "";
    String address = "";
    String created_at = "";
    String updated_at = "";
    String currency_type = "";
    String payment_status = "";
    String email = "";
    String city = "";
    String zip = "";
    String country = "";
    String landMark = "";
    String name = "";
    String country_code = "";
    String mobile_no = "";
    String special_note = "";
    String promo_code_id = "";
    String promo_code_type = "";
    String figure = "";
    String promo_code = "";
    String payment_mode = "";


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public float getFinal_price() {
        return final_price;
    }

    public void setFinal_price(float final_price) {
        this.final_price = final_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   /* public float getDiscount() {

        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }*/

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public float getCod_charges() {
        return cod_charges;
    }

    public void setCod_charges(float cod_charges) {
        this.cod_charges = cod_charges;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getPlace_date() {
        return place_date;
    }

    public void setPlace_date(String place_date) {
        this.place_date = place_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getSpecial_note() {
        return special_note;
    }

    public void setSpecial_note(String special_note) {
        this.special_note = special_note;
    }

    public String getPromo_code_id() {
        return promo_code_id;
    }

    public void setPromo_code_id(String promo_code_id) {
        this.promo_code_id = promo_code_id;
    }

    public String getPromo_code_type() {
        return promo_code_type;
    }

    public void setPromo_code_type(String promo_code_type) {
        this.promo_code_type = promo_code_type;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getPromo_code() {
        return promo_code;
    }

    public void setPromo_code(String promo_code) {
        this.promo_code = promo_code;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }
}
