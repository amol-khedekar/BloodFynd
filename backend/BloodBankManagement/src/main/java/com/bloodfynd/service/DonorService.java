package com.bloodfynd.service;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bloodfynd.model.Donor;
import com.bloodfynd.model.Requesting;
import com.bloodfynd.repository.DonorRepository;
import com.bloodfynd.repository.RequestingBloodRepository;

@Service
public class DonorService {
    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private RequestingBloodRepository requestingBloodRepository;

    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public Donor saveUserAsDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public Donor fetchDonorByBloodGroup(String bloodGroup) {
        return donorRepository.findByBloodGroup(bloodGroup);
    }

    public List<Donor> getAllDonors() {
        return (List<Donor>) donorRepository.findAll();
    }

    public List<Donor> getBloodDetails() {
        return (List<Donor>) donorRepository.findBloodDetails();
    }

    public Donor fetchDonorByGender(String gender) {
        return donorRepository.findByGender(gender);
    }


    public void checkforOldBloodSamples(List<Donor> donors) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String todayDate = formatter.format(date);
        for (Donor donorlist : donors) {
            String donationDate = donorlist.getDate();
            long days = findDifference(donationDate, todayDate);
            if (days > 90) {
                String userName = donorlist.getName();
                donorRepository.deleteByUsername(userName);
            }
        }
    }

    static long findDifference(String donationDate, String todayDate) {
        long daysDifference = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(donationDate);
            Date date2 = sdf.parse(todayDate);
            long timeDifference = date2.getTime() - date1.getTime();
            daysDifference = (timeDifference / (1000 * 60 * 60 * 24)) % 365;
            System.out.println("The Blood sample is " + daysDifference + " days older.");
        } catch (Exception e) {
            System.out.print(e);
        }
        return daysDifference;
    }
}