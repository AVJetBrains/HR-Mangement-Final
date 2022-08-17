package com.example.hrmanagementfinal.services;

import com.example.hrmanagementfinal.accessor.CandidateAccessor;
import com.example.hrmanagementfinal.models.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandidateServices {

    @Autowired
    private CandidateAccessor candidateAccessor;

    public CandidateDTO getCandidatesInfo(String Name){
        return candidateAccessor.findByName(Name);
    }
}
