package com.co.tita.front.controllers;

import com.co.tita.front.core.DataSingleton;
import com.co.tita.front.dtos.PaymentDto;
import com.co.tita.front.entity.credits.Credit;
import com.co.tita.front.entity.payments.Payment;
import com.co.tita.front.reports.CreditDetailReport;
import com.co.tita.front.reports.CreditPayments;
import com.co.tita.front.reports.CreditReport;
import com.co.tita.front.reports.PaymentReport;
import com.co.tita.front.service.BanksService;
import com.co.tita.front.service.CreditsService;
import com.co.tita.front.service.HomeService;
import com.co.tita.front.service.PaymentService;
import com.co.tita.front.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class HomeController {

@Autowired
private BanksService banksService;

@Autowired
private CreditsService creditsService;
@Autowired
private HomeService homeService;

@Autowired
private PaymentService paymentService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }



    @PostMapping("/in")
    public String Ingresar(@ModelAttribute("username") String userName,@ModelAttribute("password") String pass) throws IOException {
        if(homeService.Init(userName,pass)) {
            return "redirect:/banks";
        }else{
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logOut(){
        DataSingleton dataSingleton = DataSingleton.getDataSingleton();
            dataSingleton.setTokenDto(null);
            dataSingleton.setUserDto(null);
        return "login";
    }
    @GetMapping("/banks")
    public String getBanks(Model model) {
        DataSingleton dataSingleton = DataSingleton.getDataSingleton();
        if(null == dataSingleton.getTokenDto() ){
            return "login";
        }
        model.addAttribute("banks",banksService.getBankByUserId(dataSingleton.getUserDto().getId()));
        return "Bank-List";
    }

    @GetMapping("/credits")
    public String getCredits(Model model, @RequestParam("bankid") Long bankId) {
        DataSingleton dataSingleton = DataSingleton.getDataSingleton();
        if(null == dataSingleton.getTokenDto() ){
            return "login";
        }
        model.addAttribute("credits",creditsService.getCreditsByUserId(bankId,dataSingleton.getUserDto().getId()));
        return "Credit-List";
    }

    @GetMapping("/credit.get")
    public String getCreditDetails(Model model,@RequestParam("creditid") Long creditId){
        DataSingleton dataSingleton = DataSingleton.getDataSingleton();
        if(null == dataSingleton.getTokenDto() ){
            return "login";
        }

        CreditDetailReport creditDetailReport = creditsService.getCreditDetailById(creditId);
        CreditPayments payments = buildDetails(creditDetailReport);

        model.addAttribute("creditDetail",payments);
        return "Credit-Details";
    }


    private CreditPayments buildDetails(CreditDetailReport creditDetails){
        CreditPayments creditPayments = new CreditPayments();
        creditPayments.setId(creditDetails.getId());
        creditPayments.setCreditDate(creditDetails.getCreditDate());
        creditPayments.setAmount(creditDetails.getAmount());
        creditPayments.setPaymentReportList(creditDetails.getPaymentReportList());
        creditPayments.setBankName(creditDetails.getBankId().getBankName());
        creditPayments.setBankId(creditDetails.getBankId().getId());
        creditPayments.setQuotaQuantity(creditDetails.getQuotaQuantity());

        double amountPending = 0d;
        double quantityQuotaPending = 0d;
        AtomicReference<Double> amountPayed = new AtomicReference<>(0d);
        AtomicReference<Double> quantityQuotaPayed = new AtomicReference<>(0d);

        creditDetails.getPaymentReportList().forEach(p ->{
            amountPayed.updateAndGet(v -> new Double((double) (v + p.getAmountPayment())));
            quantityQuotaPayed.updateAndGet(v ->new Double((double) (v + p.getQuantityQuotas())));
        });

        amountPending = creditDetails.getAmount() -amountPayed.get();
        creditPayments.setAmountPending(amountPending);

        quantityQuotaPending = creditDetails.getQuotaQuantity() - quantityQuotaPayed.get();
        creditPayments.setQuotaQuntityPending(quantityQuotaPending);
        creditPayments.setQuotaAmount(creditDetails.getQuotaAmount());

        return creditPayments;
    }

    @GetMapping("/payment.add")
    public String iniciarPago(Model model,@RequestParam("credit") Long credit) throws IOException {
        DataSingleton dataSingleton = DataSingleton.getDataSingleton();
        if(null == dataSingleton.getTokenDto() ){
            return "login";
        }
        CreditDetailReport creditDetailReport = creditsService.getCreditDetailById(credit);
        CreditPayments payments = buildDetails(creditDetailReport);
        model.addAttribute("creditData", payments);
        return "Payment-add";
    }

    @PostMapping("/payment/create")
    public String hacerPago(@ModelAttribute("codigo") String codigo,
                            @ModelAttribute("cantidadCuotasPayment") String quantityQuota,
                            @ModelAttribute("payment") String payment) throws JsonProcessingException, ParseException {

        DataSingleton dataSingleton = DataSingleton.getDataSingleton();
        if(null == dataSingleton.getTokenDto() ){
            return "login";
        }
        PaymentDto paymentDto = new PaymentDto();
        Date date = new Date();
        paymentDto.setPaymentDate(date);
        paymentDto.setQuantityQuotas(Double.parseDouble(quantityQuota));
        paymentDto.setAmountPayment(Double.parseDouble(payment));
        Long creditId = Long.parseLong(codigo);
        paymentDto.setCreditId(creditId);
        paymentDto.setUserId(dataSingleton.getUserDto().getId());
        paymentService.createPayment(paymentDto);
        return "redirect:/credit.get?creditid="+codigo;
    }

}
