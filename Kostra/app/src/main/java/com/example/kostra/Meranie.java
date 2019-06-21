package com.example.kostra;


class Meranie {
    int id;
    String date1;
    String date2;
    int speed;
    int volume;


    public Meranie(int i, String date, String s, int j, int k) {
        id = i;
        date1 = date;
        date2 = s;
        speed = j;
        volume = k;
    }


    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date1) {
        this.date2 = date1;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}



