package com.kdu.smarthome.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.GeneralDTO.GeneralDTO;
import com.kdu.smarthome.dto.house.HouseListDTO;
import com.kdu.smarthome.dto.house.HouseRequestDTO;
import com.kdu.smarthome.dto.house.HouseResponseDTO;
import com.kdu.smarthome.dto.roomsanddevices.RoomDeviceDTO;
import com.kdu.smarthome.dto.userentry.UserEntryRequestDTO;
import com.kdu.smarthome.dto.userentry.UserEntryResponseDTO;
import com.kdu.smarthome.exceptions.NotAuthorizedException;
import com.kdu.smarthome.services.deviceservice.GetRoomsDevicesService;
import com.kdu.smarthome.services.houseservice.HouseService;
import com.kdu.smarthome.services.houseservice.AddUserService;
import com.kdu.smarthome.services.houseservice.UpdateAddressService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is used to hit the api endpoints for the House
 */
@RestController
@RequestMapping("/api/v1")
public class HouseController {

    private HouseService houseService;
    private AddUserService addUserService;
    private UpdateAddressService updateAddressService;
    private GetRoomsDevicesService getRoomsDevicesService;

    public HouseController(HouseService houseService, AddUserService addUserService, UpdateAddressService updateAddressService, GetRoomsDevicesService getRoomsDevicesService) {
        this.houseService = houseService;
        this.addUserService = addUserService;
        this.updateAddressService = updateAddressService;
        this.getRoomsDevicesService = getRoomsDevicesService;
    }

    /**
     * Used to get the houses
     * @return
     * @throws JsonProcessingException
     */

    @GetMapping("/house")
    public ResponseEntity<HouseListDTO> getHouses() throws JsonProcessingException {
        HouseListDTO houseListDTO = houseService.getAllHouses();
        return new ResponseEntity<>(houseListDTO, HttpStatus.OK);
    }

    /**
     * It is used to add the user
     * @param houseId
     * @param userEntryRequestDTO
     * @param request
     * @return
     */

    @PostMapping("/house/{houseId}/add-user")
    public ResponseEntity<UserEntryResponseDTO> addUser(@PathVariable String houseId , @RequestBody UserEntryRequestDTO userEntryRequestDTO , HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
       UserEntryResponseDTO response = addUserService.addUserToHouse(Long.parseLong(houseId),userEntryRequestDTO.getUsername(),token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * Adding the house
     * @param houseRequestDTO
     * @param request
     * @return
     */

    @PostMapping("/house")
    public ResponseEntity<HouseResponseDTO> addHouse(@RequestBody HouseRequestDTO houseRequestDTO, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        HouseResponseDTO houseResponseDTO = houseService.addHouse(houseRequestDTO,token);
        return new ResponseEntity<>(houseResponseDTO, HttpStatus.OK);
    }

    /**
     * Used to update the house
     * @param houseId
     * @param newAddress
     * @return
     */
    @PutMapping("/house")
    public ResponseEntity<GeneralDTO> updateAddress(@RequestParam String houseId, @RequestBody String newAddress) {
        try {
            long parsedHouseId = Long.parseLong(houseId);
            GeneralDTO userHouseDTO = updateAddressService.updateAddress(parsedHouseId, newAddress);
            return new ResponseEntity<>(userHouseDTO, HttpStatus.OK);
        } catch (NumberFormatException e) {
            String errorMessage = "Invalid houseId. Please provide a valid number.";
            throw new NotAuthorizedException(errorMessage);
        }
    }

    /**
     * Get the Rooms and the Devices
     * @param houseId
     * @return
     * @throws JsonProcessingException
     */

    @GetMapping("/house/{houseId}")
    public ResponseEntity<RoomDeviceDTO> getRoomsDevices(@PathVariable String houseId) throws JsonProcessingException {
       RoomDeviceDTO roomsDevicesDTO = getRoomsDevicesService.getRoomsAndDevices(Long.parseLong(houseId));
        return new ResponseEntity<>(roomsDevicesDTO,HttpStatus.OK);
    }
}
