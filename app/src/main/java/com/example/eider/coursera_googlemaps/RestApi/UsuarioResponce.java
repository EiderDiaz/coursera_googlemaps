package com.example.eider.coursera_googlemaps.RestApi;





public  class UsuarioResponce {
    private String id,token;

    public UsuarioResponce(String id, String token) {
        this.id = id;
        this.token = token;
    }


    public UsuarioResponce() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}