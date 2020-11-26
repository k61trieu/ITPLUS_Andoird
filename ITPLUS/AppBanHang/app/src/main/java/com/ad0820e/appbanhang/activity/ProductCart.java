package com.ad0820e.appbanhang.activity;

public class ProductCart {

        private int id;
        private String name;
        private int price;
        private String avatar;
        private int quantity;
        private int categoryid;

        public ProductCart() {
        }

        public ProductCart(int id, String name, int price, String avatar, int quantity, int categoryid) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.avatar = avatar;
            this.quantity = quantity;
            this.categoryid = categoryid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(int categoryid) {
            this.categoryid = categoryid;
        }
    }

