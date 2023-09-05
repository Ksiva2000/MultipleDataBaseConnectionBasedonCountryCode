package com.tanasvi.multidb.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tanasvi.multidb.entity.UsersEntity;
import com.tanasvi.multidb.model.Users;
import com.tanasvi.multidb.repository.CommonRepo;

@Service
@Qualifier(value = "MultiDbService")
public class MultiDbService {

    private final Map<Object, CommonRepo> repoMap;

    @Autowired
    public MultiDbService(Collection<CommonRepo> commonRepos) {
    	repoMap = commonRepos.stream()
                             .collect(Collectors.toMap(repo -> repo.getCountry().toLowerCase(), Function.identity()));
    }
    
    

    public String saveRegisterDataBasedOnCountry(String country, Users u) {
        CommonRepo repo = repoMap.get(country.toLowerCase());
        if (repo != null) {
            UsersEntity userEntity = new UsersEntity();
            userEntity.setId(u.getId());
            userEntity.setName(u.getName());
            userEntity.setDescription(u.getDescription());
            repo.save(userEntity);
            return "SuccessfullyDone";
        } else {
            throw new IllegalArgumentException("Invalid country: " + country);
        }
    }

    public List<UsersEntity> getDetailsBasedOnCountry(String country) throws Exception {
        CommonRepo repo = repoMap.get(country.toLowerCase());
        if (repo != null) {
            return repo.findAll();
        } else {
            throw new Exception("Please choose the correct country: " + country);
        }
    }
    public Optional<UsersEntity> getDetailsBasedOnUserIdCountry(String country,long id) throws Exception {
        CommonRepo repo = repoMap.get(country.toLowerCase());
        if (repo != null) {
            return repo.findById(id);
        } else {
            throw new NullPointerException("Id Not Found: " + id);
        }
    }
}

