package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    private final Pageable firstFourElements = PageRequest.of(0, 4);

    public Optional<Institution> findById(long id){

        return institutionRepository.findById(id);
    }

    public List<Institution> findFirstFourInstitutions(){

//        Pageable.ofSize(4)

        Page<Institution> institutions= institutionRepository.findAll(firstFourElements);

        return institutions.getContent();
    }

    public List<Institution> findAllInstitutions(){
        return institutionRepository.findAll();
    }
}
