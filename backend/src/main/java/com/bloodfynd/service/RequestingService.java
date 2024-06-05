package com.bloodfynd.service;

import com.bloodfynd.model.Requesting;
import com.bloodfynd.repository.RequestingBloodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestingService {
    @Autowired
    private RequestingBloodRepository requestingBloodRepository;

    public Requesting saveBloodRequest(Requesting request) {
        return requestingBloodRepository.save(request);
    }

    public List<Requesting> getRequestHistory() {
        return (List<Requesting>) requestingBloodRepository.findAll();
    }

    public List<Requesting> getRequestHistoryByEmail(String email) {
        return (List<Requesting>) requestingBloodRepository.findByEmail(email);

    }

    public void updateStatus(String email) {
        requestingBloodRepository.updateStatus(email);
        System.out.println("Updated");
    }

    public void rejectStatus(String email) {
        requestingBloodRepository.rejectStatus(email);
    }
}
