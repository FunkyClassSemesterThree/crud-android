package com.dele.my.project.crudandroid.operations.utils;

public enum AppColors {

    RGB;

    enum BLACK {
        R(0),
        G(0),
        B(0);

        int value;
        BLACK(int value) {
            this.value = value;
        }

        public int getValue () {
            return this.value;
        }
    }

}
