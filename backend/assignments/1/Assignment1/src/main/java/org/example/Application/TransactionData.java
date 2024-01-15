package org.example.Application;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionData {
    private String coin;
    private Long quantity;
    private String wallet_address;
    private Double price;
    private Long volume;

    // Constructors, getters, and setters

    public TransactionData() {
        // Default constructor for Jackson
    }

    public TransactionData(String coin, Long quantity, String wallet_address) {
        this.coin = coin;
        this.quantity = quantity;
        this.wallet_address = wallet_address;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getWallet_address() {
        return wallet_address;
    }

    public void setWallet_address(String wallet_address) {
        this.wallet_address = wallet_address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }
}
