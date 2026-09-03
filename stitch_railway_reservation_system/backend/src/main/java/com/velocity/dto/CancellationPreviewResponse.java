package com.velocity.dto;

public class CancellationPreviewResponse {
    private String pnr;
    private double originalFare;
    private double cancellationFee;
    private double clerkageFee;
    private double nonRefundableConvenienceFee;
    private double finalRefundAmount;
    private String currency; // ₹
    private String refundNotice;
    private String expectedTimeline;

    public CancellationPreviewResponse() {}

    public CancellationPreviewResponse(String pnr, double originalFare, double cancellationFee,
                                       double clerkageFee, double nonRefundableConvenienceFee,
                                       double finalRefundAmount, String currency, String expectedTimeline) {
        this.pnr = pnr;
        this.originalFare = originalFare;
        this.cancellationFee = cancellationFee;
        this.clerkageFee = clerkageFee;
        this.nonRefundableConvenienceFee = nonRefundableConvenienceFee;
        this.finalRefundAmount = finalRefundAmount;
        this.currency = currency != null ? currency : "₹";
        this.expectedTimeline = expectedTimeline;
        this.refundNotice = "You will receive " + this.currency + String.format(java.util.Locale.US, "%.2f", finalRefundAmount) + " refund";
    }

    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }

    public double getOriginalFare() { return originalFare; }
    public void setOriginalFare(double originalFare) { this.originalFare = originalFare; }

    public double getCancellationFee() { return cancellationFee; }
    public void setCancellationFee(double cancellationFee) { this.cancellationFee = cancellationFee; }

    public double getClerkageFee() { return clerkageFee; }
    public void setClerkageFee(double clerkageFee) { this.clerkageFee = clerkageFee; }

    public double getNonRefundableConvenienceFee() { return nonRefundableConvenienceFee; }
    public void setNonRefundableConvenienceFee(double nonRefundableConvenienceFee) { this.nonRefundableConvenienceFee = nonRefundableConvenienceFee; }

    public double getFinalRefundAmount() { return finalRefundAmount; }
    public void setFinalRefundAmount(double finalRefundAmount) { this.finalRefundAmount = finalRefundAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getRefundNotice() { return refundNotice; }
    public void setRefundNotice(String refundNotice) { this.refundNotice = refundNotice; }

    public String getExpectedTimeline() { return expectedTimeline; }
    public void setExpectedTimeline(String expectedTimeline) { this.expectedTimeline = expectedTimeline; }
}
