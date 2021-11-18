package ubun.annotation.utility;

import ubun.annotation.Column;
import ubun.annotation.PrimaryKey;
@DefineTable(name = "transaction_history")
public class TransactionHistory {
	@PrimaryKey(name = "transaction_id")
	private long transactionId;
	@Column(name = "account_number")
	private int accountNumber;
	@Column
	private String name;
	@Column(name = "transaction_type")
	private String transactionType;
	@Column
	private int amount;

	public TransactionHistory(int accountNumber, String name, String transactionType, int amount) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public TransactionHistory() {
		// TODO Auto-generated constructor stub
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "TransactionHistory : " + super.toString();
	}
}
