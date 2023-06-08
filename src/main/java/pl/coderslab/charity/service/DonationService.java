package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    public Optional<Donation> findDonationById(long id) {

        return donationRepository.findById(id);

    }

    public List<Donation> findAllDonations() {
        return donationRepository.findAll();
    }

    public int getTotalDonationQty() {

       return donationRepository.getQuantitySum().orElse(0);
    }

    public long getTotalDonations(){

        return donationRepository.count();
    }

    public void addDonation(Donation donation){

        donationRepository.save(donation);
    }

}
