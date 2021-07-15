package com.yapue.appan.models;

/**
 * Created by hemant on 21/3/18.
 */

public class MercadoObject {
    String id = "";
    String client_id = "";
    String collector_id = "";
    String init_point = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getCollector_id() {
        return collector_id;
    }

    public void setCollector_id(String collector_id) {
        this.collector_id = collector_id;
    }

    public String getInit_point() {
        return init_point;
    }

    public void setInit_point(String init_point) {
        this.init_point = init_point;
    }
}
