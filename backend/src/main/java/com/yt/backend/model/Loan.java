package com.yt.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan_table")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLoan;

    @Column(name = "loan_date")
    private LocalDate loanDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Loan() {
    }

    private String LoanStatus;




    public long getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(long idLoan) {
        this.idLoan = idLoan;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getLoanStatus() {
        return LoanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        LoanStatus = loanStatus;
    }
}