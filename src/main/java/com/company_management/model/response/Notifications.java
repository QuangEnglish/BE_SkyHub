package com.company_management.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notifications {
    private int count;

    public Notifications(int count) {
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment() {
        this.count++;
    }
}
