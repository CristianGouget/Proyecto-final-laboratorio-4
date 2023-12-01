package com.yt.backend.service;

import com.yt.backend.model.Loan;
import com.yt.backend.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    // repository
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan addLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(long idLoan) {
        return loanRepository.findById(idLoan).get();
    }

    @Override
    public List<Loan> getLoans() {
        return (List<Loan>) loanRepository.findAll();
    }

    @Override
    public void deleteLoan(long idLoan) {
        loanRepository.deleteById(idLoan);
    }

    @Override
    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }
}
