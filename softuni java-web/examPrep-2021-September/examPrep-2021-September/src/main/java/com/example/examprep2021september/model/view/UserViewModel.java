package com.example.examprep2021september.model.view;

public class UserViewModel {
    private String userName;
    private Integer countOfOrders;

    public UserViewModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCountOfOrders() {
        return countOfOrders;
    }

    public void setCountOfOrders(Integer countOfOrders) {
        this.countOfOrders = countOfOrders;
    }
}
