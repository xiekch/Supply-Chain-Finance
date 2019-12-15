package com.xiekch.server.controller;

import javax.servlet.http.HttpSession;

import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.xiekch.server.domain.*;
import com.xiekch.server.service.ContractsService;

@Controller
public class ContractsController {
    private Web3j web3j;

    public ContractsController(Web3j web3j) {
        this.web3j = web3j;
    }

    @PostMapping("/contract/new")
    public String newCompany(@RequestParam("name") String companyName, @RequestParam("balance") int balance,
            @RequestParam("rate") int rate, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        String owner = user.getName();
        String message = "OK, you created a company. \n";
        if (!ContractsService.getInstance(web3j).createCompany(companyName, owner, balance, rate)) {
            message = "Error";
        }

        model.addAttribute("message", message);
        return "result";
    }

    @PostMapping("/contract/deal")
    public String deal(@RequestParam("from") String companyName, @RequestParam("to") String toCompanyName,
            @RequestParam("amount") int amount, HttpSession session, Model model) {
        String message = "OK, you made a deal. \n";
        int newReceiptId = ContractsService.getInstance(web3j).deal(companyName, toCompanyName, amount);
        if (newReceiptId == 0) {
            message = "Error";
        } else {
            message += "newReceiptId: " + newReceiptId;
        }

        model.addAttribute("message", message);
        return "result";
    }

    @PostMapping("/contract/transfer")
    public String transfer(@RequestParam("from") String companyName, @RequestParam("to") String toCompanyName,
            @RequestParam("id") int receiptId, @RequestParam("amount") int amount, HttpSession session, Model model) {
        String message = "OK, you transfer a deal. \n";
        int newReceiptId = ContractsService.getInstance(web3j).transfer(companyName, toCompanyName, receiptId, amount);
        if (newReceiptId == 0) {
            message = "Error";
        } else {
            message += "newReceiptId: " + newReceiptId;
        }

        model.addAttribute("message", message);
        return "result";
    }

    @PostMapping("/contract/finance")
    public String finance(@RequestParam("name") String companyName, @RequestParam("id") int receiptId,
            @RequestParam("amount") int amount, HttpSession session, Model model) {
        String message = "OK, you financed. \n";
        int newReceiptId = ContractsService.getInstance(web3j).financing(companyName, receiptId, amount);

        if (newReceiptId == 0) {
            message = "Error";
        } else {
            message += "newReceiptId: " + newReceiptId;
        }
        model.addAttribute("message", message);
        return "result";
    }

    @PostMapping("/contract/pay")
    public String pay(@RequestParam("name") String companyName, @RequestParam("id") int receiptId, HttpSession session,
            Model model) {
        String message = "OK, you payed. \n";
        int newReceiptId = ContractsService.getInstance(web3j).pay(companyName, receiptId);

        if (newReceiptId == 0) {
            message = "Error";
        } else {
            // message += "newReceiptId: " + newReceiptId;
        }
        model.addAttribute("message", message);
        return "result";
    }

}