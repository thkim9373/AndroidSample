package com.hoony.androidsample.main;

class Item {
    private String title;
    private Class target;

    Item(String title, Class target) {
        this.title = title;
        this.target = target;
    }

    String getTitle() {
        return title;
    }

    Class getTarget() {
        return target;
    }
}
