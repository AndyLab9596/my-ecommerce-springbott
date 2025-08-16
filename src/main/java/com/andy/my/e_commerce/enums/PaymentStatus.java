package com.andy.my.e_commerce.enums;

public enum PaymentStatus {
    PENDING,        // payment created, waiting for processing
    PROCESSING,     // being verified/processed
    COMPLETED,      // successfully paid
    FAILED,         // payment attempt failed (e.g. insufficient funds)
    CANCELLED,      // user or system cancelled before completion
    REFUNDED,       // money refunded after completion
    CHARGEBACK      // reversed by bank/payment provider
}
