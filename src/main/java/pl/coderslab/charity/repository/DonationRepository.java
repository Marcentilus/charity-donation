package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("select sum(d.quantity) from Donation d where d.user.id =:id ")
    Optional<Integer> getQuantitySum(long id);

    @Query("select count(d.id) from Donation d where d.user.id =:id")
    Optional<Long> countByUser(long id);

    @Query("select d from Donation d where d.user.id =:id")
    List<Donation> getDonationsByUser(long id);



}
