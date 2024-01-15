package org.example.Application;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {
    private String type;

    @JsonProperty("data")
    private TransactionData transactionData;

    // Constructors, getters, and setters

    public Transaction() {
        // Default constructor for Jackson
    }

    public Transaction(String type, TransactionData transactionData) {
        this.type = type;
        this.transactionData = transactionData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TransactionData getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(TransactionData transactionData) {
        this.transactionData = transactionData;
    }
}

