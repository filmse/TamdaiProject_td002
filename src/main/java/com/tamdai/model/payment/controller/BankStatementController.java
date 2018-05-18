package com.tamdai.model.payment.controller;

import com.tamdai.model.payment.entity.BankStatement;
import com.tamdai.model.payment.entity.OrderPayment;
import com.tamdai.model.payment.entity.PaymentTransaction;
import com.tamdai.model.payment.repository.OrderPaymentRepository;
import com.tamdai.model.payment.service.BankStatementService;
import com.tamdai.model.payment.service.OrderPaymentService;
import com.tamdai.model.payment.service.PaymentTransactionService;
import com.tamdai.model.security.entity.UserEntity;
import com.tamdai.model.security.repository.UserRepository;
import com.tamdai.model.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/")
public class BankStatementController {

	@Autowired
    UserService userService;

	@Autowired
	BankStatementService bankStatementService;

	@Autowired
    PaymentTransactionService paymentTransactionService;

	@Autowired
	OrderPaymentService paymentService;

	@Autowired
	OrderPaymentRepository orderPaymentRepository;

	@Autowired
    UserRepository userRepository;

	@RequestMapping(value = "create/bankStatement/{id}", method = RequestMethod.POST)
    public BankStatement bankStatementCreate(@PathVariable("id") Long idOrder,
                                             @RequestBody BankStatement bankStatement,
                                             @RequestParam("userId") Long id, BindingResult bindingResult) {
        UserEntity user = userService.getUserId(id);
        OrderPayment orderPayments = orderPaymentRepository.getOne(idOrder);
        
        orderPayments.setStatusOrder("waitPaid");
        return bankStatementService.bankStatementCreate(bankStatement, user);
    }

	@RequestMapping(value = "get/bankStatement", method = RequestMethod.GET)
	public List<BankStatement> getBankStatementAll() {
		return bankStatementService.getBankStatementAll();
	}

	@RequestMapping(value = "get/bankStatement/{id}", method = RequestMethod.GET)
	public BankStatement getBankStatementById(@PathVariable("id") Long id) {
		return bankStatementService.getBankStatementById(id);
	}

	@RequestMapping(value = "update/bankStatement/{id}", method = RequestMethod.PUT)
	public BankStatement bankStatementUpdate(@PathVariable("id") Long id, HttpServletRequest request,
                                             @RequestBody BankStatement bankStatement, @RequestParam("Email") String email,
                                             @RequestParam("UserId") Long userId, @RequestParam("orderId") Long orderId,
                                             @RequestParam("transRef") String transRef, @RequestParam("transAmount") String transAmount,
                                             @RequestParam("Balance") String balance, BindingResult bindingResult) {
		UserEntity user = userService.getUserByEmail(email);
		OrderPayment orderPayment = paymentService.getOrderById(orderId);
		BankStatement bank = bankStatementService.getBankStatementById(id);

		PaymentTransaction paymentTransaction = new PaymentTransaction();

		// CreateDate
		String signUpDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		paymentTransaction.setCreateDate(signUpDate);

		// CreateTime
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
		paymentTransaction.setCreateTime(timeStamp);

		String transA = new String(transAmount);
		String a = "300";
		String b = "500";
		int five = 50;
		int one = 100;
		int transAm = Integer.parseInt(transAmount);
		int transba = Integer.parseInt(balance);

		if (Objects.equals(transA, a)) {
			System.out.println("เงื่อนไข 300");
			System.out.println("transA: " + transA);
			paymentTransaction.setTransAmount(transA);

			System.out.println("balance: " + balance);

			int result = (transba + five);
			System.out.println("result :" + result);
			paymentTransaction.setTransAmount(String.valueOf(result));

			String transRe = String.valueOf(result);
			System.out.println("transRe :" + transRe);
			System.out.println("=========================================");
			paymentTransaction.setTransRemark(transRe);
			bankStatementService.bankStatementUpdate(bankStatement, user, transRe, bank);
		} else if (Objects.equals(transA, b)) {

			System.out.println("เงื่อนไข 500");
			System.out.println(transA);
			paymentTransaction.setTransAmount(transA);

			System.out.println("balance: " + balance);

			int result = (transba + one);
			System.out.println("result :" + result);
			paymentTransaction.setTransAmount(String.valueOf(result));

			String transRe = String.valueOf(result);
			System.out.println("transRe :" + transRe);
			System.out.println("=========================================");
			paymentTransaction.setTransRemark(transRe);
			bankStatementService.bankStatementUpdate(bankStatement, user, transRe, bank);
		} else {
			System.out.println("เงื่อนไข 100");
			System.out.println(transA);
			paymentTransaction.setTransAmount(transA);

			System.out.println("balance: " + balance);

			int result = transba;
			System.out.println("result :" + result);
			paymentTransaction.setTransAmount(String.valueOf(result));

			String transRe = String.valueOf(result);
			System.out.println("transRe :" + transRe);
			System.out.println("=========================================");
			paymentTransaction.setTransRemark(transRe);
			bankStatementService.bankStatementUpdate(bankStatement, user, transRe, bank);
		}

		String statusOrder = new String("confirm");
		orderPayment.setStatusOrder(statusOrder);
		orderPaymentRepository.save(orderPayment);

		paymentTransactionService.createPaymentTransaction(user, paymentTransaction, transRef);

		// userService.addPaymentTransaction(user, paymentTransaction);

		return bank;
	}

}
