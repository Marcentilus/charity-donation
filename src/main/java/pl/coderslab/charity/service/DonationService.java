package pl.coderslab.charity.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.dto.DonationDetailsDto;
import pl.coderslab.charity.dto.DonationDto;
import pl.coderslab.charity.dto.UserDonationDto;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    private final UserService userService;

    public DonationDetailsDto findDonationDetailsById(long id) throws ResponseStatusException {

        return donationRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Donation not found"))
                .getDonationDetails();

    }

    public Donation findDonationById(long id) throws ResponseStatusException{
        return donationRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Donation not found"));

    }

    public List<Donation> findAllDonations() {
        return donationRepository.findAll();
    }

    public List<DonationDetailsDto> findDonationsByUser(long id){
        List<Donation> donations = donationRepository.getDonationsByUser(id);
        if(donations == null){
            return new ArrayList<>();
        }

        List<DonationDetailsDto> donationDetailsDtoList = donations.stream()
                .map(Donation::getDonationDetails).toList();

        for (DonationDetailsDto donationDto: donationDetailsDtoList) {
            if(donationDto.getPickUpDate().isBefore(LocalDate.now())){
                donationDto.setCollected(true);
            }
        }
        return donationDetailsDtoList;
    }

    public int getTotalDonationQty(long id) {

       return donationRepository.getQuantitySum(id).orElse(0);
    }

    public long getTotalDonations(long id){

        return donationRepository.countByUser(id).orElse(0L);
    }

    public UserDonationDto addDonation(DonationDto donationDto, long userId) {

        Donation donation = donationRepository.save(donationDto.getDonationFromDto());


        return new UserDonationDto(userId, donation);


        }

        public void deactivateDonation(long id) throws  ResponseStatusException{


            Donation donation = findDonationById(id);
            if(donation == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Donation not found");
            }
            donation.setCollected(true);

            donationRepository.save(donation);

        }

}
