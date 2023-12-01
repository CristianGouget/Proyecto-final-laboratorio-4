package com.yt.backend.service;

import com.yt.backend.model.Loan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {
    public Loan addLoan(Loan loan);
    public Loan getLoanById(long idLoan);
    public List<Loan> getLoans();
    public void deleteLoan(long idLoan);
    public Loan updateLoan(Loan loan);
}
