package com.velocity.model;

public class RefundDetails {
    private String refundId;
    private double originalFare;
    private double cancellationCharge;
    private double clerkageCharge;
    private double convenienceFeeDeducted;
    private double refundAmount;
    private String refundStatus; // INITIATED, PROCESSING, COMPLETED, FAILED
    private String initiatedAt;
    private String completedAt;
    private String paymentMode;
    private String expectedTimeline;
    private String refundTransactionRef;

    public RefundDetails() {}

    public RefundDetails(String refundId, double originalFare, double cancellationCharge, double clerkageCharge,
                         double convenienceFeeDeducted, double refundAmount, String refundStatus,
                         String initiatedAt, String paymentMode, String expectedTimeline, String refundTransactionRef) {
        this.refundId = refundId;
        this.originalFare = originalFare;
        this.cancellationCharge = cancellationCharge;
        this.clerkageCharge = clerkageCharge;
        this.convenienceFeeDeducted = convenienceFeeDeducted;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
        this.initiatedAt = initiatedAt;
        this.paymentMode = paymentMode;
        this.expectedTimeline = expectedTimeline;
        this.refundTransactionRef = refundTransactionRef;
    }

    public String getRefundId() { return refundId; }
    public void setRefundId(String refundId) { this.refundId = refundId; }

    public double getOriginalFare() { return originalFare; }
    public void setOriginalFare(double originalFare) { this.originalFare = originalFare; }

    public double getCancellationCharge() { return cancellationCharge; }
    public void setCancellationCharge(double cancellationCharge) { this.cancellationCharge = cancellationCharge; }

    public double getClerkageCharge() { return clerkageCharge; }
    public void setClerkageCharge(double clerkageCharge) { this.clerkageCharge = clerkageCharge; }

    public double getConvenienceFeeDeducted() { return convenienceFeeDeducted; }
    public void setConvenienceFeeDeducted(double convenienceFeeDeducted) { this.convenienceFeeDeducted = convenienceFeeDeducted; }

    public double getRefundAmount() { return refundAmount; }
    public void setRefundAmount(double refundAmount) { this.refundAmount = refundAmount; }

    public String getRefundStatus() { return refundStatus; }
    public void setRefundStatus(String refundStatus) { this.refundStatus = refundStatus; }

    public String getInitiatedAt() { return initiatedAt; }
    public void setInitiatedAt(String initiatedAt) { this.initiatedAt = initiatedAt; }

    public String getCompletedAt() { return completedAt; }
    public void setCompletedAt(String completedAt) { this.completedAt = completedAt; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public String getExpectedTimeline() { return expectedTimeline; }
    public void setExpectedTimeline(String expectedTimeline) { this.expectedTimeline = expectedTimeline; }

    public String getRefundTransactionRef() { return refundTransactionRef; }
    public void setRefundTransactionRef(String refundTransactionRef) { this.refundTransactionRef = refundTransactionRef; }
}
