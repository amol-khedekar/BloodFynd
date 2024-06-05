package com.bloodfynd.controller;

import com.bloodfynd.model.Requesting;
import com.bloodfynd.service.DonorService;
import com.bloodfynd.service.RequestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RequestingController {
    @Autowired
    private RequestingService requestingService;

    @GetMapping("/acceptstatus/{email}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<String>> updateStatus(@PathVariable String email) throws Exception {
        requestingService.updateStatus(email);
        List<String> al = new ArrayList<>();
        al.add("accepted");
        return new ResponseEntity<List<String>>(al, HttpStatus.OK);
    }

    @GetMapping("/rejectstatus/{email}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<String>> rejectStatus(@PathVariable String email) throws Exception {
        requestingService.rejectStatus(email);
        List<String> al = new ArrayList<>();
        al.add("rejected");
        return new ResponseEntity<List<String>>(al, HttpStatus.OK);
    }

    @GetMapping("/requestHistory")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Requesting>> getRequestHistory() throws Exception {
        List<Requesting> history = requestingService.getRequestHistory();
        return new ResponseEntity<List<Requesting>>(history, HttpStatus.OK);
    }

    @GetMapping("/requestHistory/{email}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Requesting>> getRequestHistoryByEmail(@PathVariable String email) throws Exception {
        System.out.print("requesting");
        List<Requesting> history = requestingService.getRequestHistoryByEmail(email);
        return new ResponseEntity<List<Requesting>>(history, HttpStatus.OK);
    }

    @GetMapping("/getTotalRequests/{email}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Integer>> getTotalRequests(@PathVariable String email) throws Exception {
        List<Requesting> history = requestingService.getRequestHistoryByEmail(email);
        List<Integer> al = new ArrayList<>();
        al.add(history.size());
        return new ResponseEntity<List<Integer>>(al, HttpStatus.OK);
    }

    @PostMapping("/requestblood")
    @CrossOrigin(origins = "http://localhost:4200")
    public Requesting addNewBloodRequest(@RequestBody Requesting request) throws Exception {
        return requestingService.saveBloodRequest(request);
    }
}
