package main.java.dtos;

import main.java.models.*;

public class IssueTicketResponseDTO {
    private Ticket ticket; // assuming there's no sensitive data in the ticket object

    private ResponseStatus responseStatus;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}