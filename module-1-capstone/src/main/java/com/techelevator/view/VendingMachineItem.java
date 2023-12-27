package com.techelevator.view;

public class VendingMachineItem {

        private String productName;
        private double price;
        private int quantity;
        private String type;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPurchaseMessage() {
            return switch (type) {
                case "Chip" -> "Crunch, Crunch, Yum!";
                case "Drink" -> "Glug, Glug, Yum!";
                case "Candy" -> "Munch, Munch, Yum!";
                case "Gum" -> "Chew, Chew, Yum!";
                default -> "Yum Yum!";
            };
        }

        public VendingMachineItem(String name, double price, String type) {
            this.productName = name;
            this.price = price;
            this.type = type;
            this.quantity = 5;
        }
    }