package main.java.controllers;

import main.java.dtos.IssueTicketRequestDTO;
import main.java.dtos.IssueTicketResponseDTO;
import main.java.dtos.ResponseStatus;
import main.java.models.Ticket;
import main.java.services.TicketServices;

public class TicketController {
    private TicketServices ticketServices;

    public TicketController(TicketServices ticketServices) {
        this.ticketServices = ticketServices;
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO issueTicketRequestDTO) {
        IssueTicketResponseDTO issueTicketResponseDTO = new IssueTicketResponseDTO();

        try {
            Ticket ticket = this.ticketServices.issueTicket(issueTicketRequestDTO.getGateId(),
                    issueTicketRequestDTO.getVehicleNumber(),
                    issueTicketRequestDTO.getVehicleOwnerName(),
                    issueTicketRequestDTO.getVehicleType(),
                    issueTicketRequestDTO.getParkingLotId());
            issueTicketResponseDTO.setTicket(ticket);
            issueTicketResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception ex) {
            issueTicketResponseDTO.setResponseStatus(ResponseStatus.FAILED);
        }

        return issueTicketResponseDTO;
    }
}
