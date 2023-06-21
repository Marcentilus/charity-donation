package pl.coderslab.charity.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.dto.DonationDto;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationDto findDonationById(long id) throws ResponseStatusException {

        return donationRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Donation not found"))
                .getDonationAsDto();

    }

    public List<Donation> findAllDonations() {
        return donationRepository.findAll();
    }

    public List<DonationDto> findDonationsByUser(long id){
        List<Donation> donations = donationRepository.getDonationsByUser(id);
        if(donations == null){
            return new ArrayList<>();
        }

        List<DonationDto> donationDtos = donations.stream()
                .map(Donation::getDonationAsDto).toList();

        for (DonationDto donationDto: donationDtos) {
            if(donationDto.getPickUpDate().isBefore(LocalDate.now())){
                donationDto.setCollected(true);
            }
        }
        return donationDtos;
    }

    public int getTotalDonationQty(long id) {

       return donationRepository.getQuantitySum(id).orElse(0);
    }

    public long getTotalDonations(long id){

        return donationRepository.countByUser(id).orElse(0L);
    }

    public void addDonation(Donation donation){

        donationRepository.save(donation);
    }

}
