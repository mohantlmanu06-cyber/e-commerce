package com.velocity.dto;

import java.util.ArrayList;
import java.util.List;

public class PriceCalculationResponse {
    private double baseFare;
    private double taxesAndFees;
    private double discounts;
    private double totalAmount;
    private String currency; // ₹
    private List<PriceLineItem> breakdown = new ArrayList<>();

    public static class PriceLineItem {
        private String label;
        private double amount;
        private String formattedAmount;

        public PriceLineItem() {}

        public PriceLineItem(String label, double amount, String formattedAmount) {
            this.label = label;
            this.amount = amount;
            this.formattedAmount = formattedAmount;
        }

        public String getLabel() { return label; }
        public void setLabel(String label) { this.label = label; }

        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }

        public String getFormattedAmount() { return formattedAmount; }
        public void setFormattedAmount(String formattedAmount) { this.formattedAmount = formattedAmount; }
    }

    public PriceCalculationResponse() {}

    public PriceCalculationResponse(double baseFare, double taxesAndFees, double discounts,
                                    double totalAmount, String currency, List<PriceLineItem> breakdown) {
        this.baseFare = baseFare;
        this.taxesAndFees = taxesAndFees;
        this.discounts = discounts;
        this.totalAmount = totalAmount;
        this.currency = currency != null ? currency : "₹";
        this.breakdown = breakdown;
    }

    public double getBaseFare() { return baseFare; }
    public void setBaseFare(double baseFare) { this.baseFare = baseFare; }

    public double getTaxesAndFees() { return taxesAndFees; }
    public void setTaxesAndFees(double taxesAndFees) { this.taxesAndFees = taxesAndFees; }

    public double getDiscounts() { return discounts; }
    public void setDiscounts(double discounts) { this.discounts = discounts; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public List<PriceLineItem> getBreakdown() { return breakdown; }
    public void setBreakdown(List<PriceLineItem> breakdown) { this.breakdown = breakdown; }
}
