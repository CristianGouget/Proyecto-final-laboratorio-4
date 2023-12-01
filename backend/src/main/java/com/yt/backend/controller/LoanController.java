package com.yt.backend.controller;

import com.yt.backend.model.Loan;
import com.yt.backend.model.Book;
import com.yt.backend.service.LoanService;
import com.yt.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class LoanController {
    // services
    @Autowired
    private LoanService loanService;

    @Autowired
    private BookService bookService;

    // add Loan
    @PostMapping("/addloan")
    public String addLoan(@RequestBody Loan loan){
        loanService.addLoan(loan);

        return "Loan Added Successfully..";
    }

    // getLoanById
    @RequestMapping("/loan/{idLoan}")
    public Loan getLoanById(@PathVariable("idLoan") long idLoan){
        return loanService.getLoanById(idLoan);
    }

    // getLoans
    @RequestMapping("/loans")
    public List<Loan> getLoans(){
        return loanService.getLoans();
    }

    // updateLoan
    @PutMapping("/loan")
    public Loan updateLoan(@RequestBody Loan loan){
        return loanService.updateLoan(loan);
    }

    // deleteLoanById
    @DeleteMapping("/loan/{idLoan}")
    public String deleteLoan(@PathVariable("idLoan") long idLoan){
        loanService.deleteLoan(idLoan);
        return "Loan Deleted";
    }
}