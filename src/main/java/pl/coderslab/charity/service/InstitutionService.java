package pl.coderslab.charity.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    private final Pageable firstFourElements = PageRequest.of(0, 4);

    public Institution findById(long id){



        return institutionRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "institution with id: " + id + " not found"));
    }

    public List<Institution> findFirstFourInstitutions(){

//        Pageable.ofSize(4)

        Page<Institution> institutions= institutionRepository.findAll(firstFourElements);

        return institutions.getContent();
    }

    public List<Institution> findAllInstitutions(){
        return institutionRepository.findAll();
    }

    public Institution addInstitution(Institution institution){

        return institutionRepository.save(institution);
    }

    public void deleteInstitution(long id){

        Optional<Institution> institutionOptional = institutionRepository.findById(id);
        Institution institution = institutionOptional.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "institution with id: " + id + " not found"));

        institutionRepository.delete(institution);

    }
}
