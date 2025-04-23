package main.java.models;

import java.util.Date;
import java.util.List;

import main.java.models.enums.BillStatus;

public class Bill extends BaseModel {
    private String billNo;
    private Ticket ticket;
    private Gate gate;
    private Operator operatedBy;
    private List<Payment> payments;
    private double amount;
    private Date exitTime;
    private BillStatus billStatus;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public Operator getOperatedBy() {
        return operatedBy;
    }

    public void setOperatedBy(Operator operatedBy) {
        this.operatedBy = operatedBy;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }
}
