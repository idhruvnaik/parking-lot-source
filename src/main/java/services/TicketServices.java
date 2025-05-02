package main.java.services;

import java.util.Optional;

import main.java.exceptions.GateNotFoundException;
import main.java.factory.SpotAssignmentFactory;
import main.java.models.Gate;
import main.java.models.ParkingLot;
import main.java.models.ParkingSpot;
import main.java.models.Ticket;
import main.java.models.Vehicle;
import main.java.models.enums.SpotAssignmentStrategyType;
import main.java.models.enums.TicketStatus;
import main.java.models.enums.VehicleType;
import main.java.repository.GateRepository;
import main.java.repository.ParkingLotRepository;
import main.java.repository.TicketRepository;
import main.java.repository.VehicleRepository;
import main.java.strategies.SpotAssignmentStrategy;

public class TicketServices {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketServices(GateRepository gateRepository, VehicleRepository vehicleRepository,
            ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(Long gateId, String vehicleNumber, String ownerName, VehicleType vehicleType,
            Long parkingLotId) throws GateNotFoundException {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(System.currentTimeMillis());

        Optional<Gate> optionalGate = gateRepository.findGateById(gateId);

        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException("Gate with gateId " + gateId + " is not present in the parking lot");
        }

        Gate gate = optionalGate.get();
        ticket.setGeneratedAt(gate);
        ticket.setGeneratedBy(gate.getOperator());

        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicleByVehicleNo(vehicleNumber);

        Vehicle savedVehicle;

        if (optionalVehicle.isEmpty()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setNumber(vehicleNumber);
            vehicle.setOwner(ownerName);
            vehicle.setVehicleType(vehicleType);

            vehicleRepository.save(vehicle);
            savedVehicle = vehicle;
        } else {
            savedVehicle = optionalVehicle.get();
        }

        ticket.setVehicle(savedVehicle);
        ticket.setTicketStatus(TicketStatus.VALID);

        ticket.setTicketNo(
                "TICKET_" + gateId + "_" + vehicleNumber + "_" + ticket.getGeneratedAt().getOperator().getName());

        Optional<ParkingLot> optionalParkingLot = parkingLotRepository.getParkingLotById(parkingLotId);
        if (optionalParkingLot.isEmpty()) {
            throw new RuntimeException("Invalid parking lot id");
        }

        ParkingLot parkingLot = optionalParkingLot.get();

        SpotAssignmentStrategyType spotAssignmentStrategyType = parkingLot.getSpotAssignmentStrategyType();

        SpotAssignmentStrategy spotAssignmentStrategy = SpotAssignmentFactory
                .getSpotAssignmentStrategy(spotAssignmentStrategyType);

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(vehicleType, gate);

        ticket.setParkingSpot(parkingSpot);

        ticketRepository.save(ticket);

        return ticket;
    }
}
