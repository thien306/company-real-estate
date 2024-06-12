package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Transaction;
import com.codegym.model.Type;
import com.codegym.service.impl.CustomerServiceImpl;
import com.codegym.service.impl.TransactionService;
import com.codegym.service.impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    TypeServiceImpl typeService;
    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping
    public String showList(Model model) {
        List<Transaction> transactions = transactionService.findAll();
        model.addAttribute("transactions", transactions);
        return "showlist";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Transaction transaction = new Transaction();
        List<Customer> customerList = customerService.findAll();
        List<Type> typeList = typeService.findAll();
        model.addAttribute("transaction", transaction);
        model.addAttribute("typeList", typeList);
        model.addAttribute("customers", customerList);
        return "createForm";
    }

    @PostMapping("/create")
    public String saveTransaction(@ModelAttribute("transaction") Transaction transaction, Model model) {
        transactionService.save(transaction);
        model.addAttribute("message", "Congratulations !!! Transaction has been created");
        return "redirect:/transactions";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable String id) {
        Optional<Transaction> transaction = transactionService.findById(id);
        if (transaction.isEmpty()) {
            return "error";
        }
        transactionService.removeById(id);
        return "redirect:/transactions";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        Optional<Transaction> transaction = transactionService.findById(id);
        if (!transaction.isPresent()) {
            return "error";
        }

        Transaction transaction1 = transaction.get();
        model.addAttribute("transaction", transaction1);
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        List<Type> typeList = typeService.findAll();
        model.addAttribute("typeList", typeList);
        return "updateform";
    }
    @PostMapping("/update")
    public String updateTransaction(@ModelAttribute Transaction transaction){
        transactionService.save(transaction);
        return "redirect:/transactions";
    }
}

