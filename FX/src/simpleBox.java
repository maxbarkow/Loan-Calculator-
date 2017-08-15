import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class simpleBox extends Application {

	private TextField tfAnnualInterestRate = new TextField();
	private TextField tfNumberofYears = new TextField();
	private TextField tfLoanAmount = new TextField();
	private TextField tfMonthlyPayment = new TextField();
	private TextField tfTotalPayment = new TextField();
	private Button btCalculate = new Button("Calculate");

	@Override
	public void start(Stage primaryStage){
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("Annual Interest rates:"), 0, 0);
		gridPane.add(tfAnnualInterestRate, 1, 0);
		gridPane.add(new Label("Number Of Years:"), 0, 1);
		gridPane.add(tfNumberofYears, 1, 1);
		gridPane.add(new Label("Loan Amount:"), 0, 2);
		gridPane.add(tfLoanAmount, 1, 2);
		gridPane.add(new Label ("Monthly Payment:"), 0, 3);
		gridPane.add(tfMonthlyPayment, 1, 3);
		gridPane.add(new Label ("Total Payment:"), 0, 4);
		gridPane.add(tfTotalPayment, 1, 4);
		gridPane.add(btCalculate, 1, 5);

		gridPane.setAlignment(Pos.CENTER);
		tfAnnualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
		tfNumberofYears.setAlignment(Pos.BOTTOM_RIGHT);
		tfLoanAmount.setAlignment(Pos.BOTTOM_RIGHT);
		tfMonthlyPayment.setAlignment(Pos.BOTTOM_RIGHT);
		tfTotalPayment.setAlignment(Pos.BOTTOM_RIGHT);
		tfMonthlyPayment.setEditable(false);
		tfTotalPayment.setEditable(false);
		GridPane.setHalignment(btCalculate, HPos.RIGHT);

		btCalculate.setOnAction(e -> calculateLoanPayment());

		Scene scene = new Scene(gridPane, 400, 250);
		primaryStage.setTitle("Loan Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

public class Loan {
	private double annualInterestRate;
	private double numberOfYears;
	private double loanAmount;
	private java.util.Date loanDate;

	public Loan(double annualInterestRate, double numberOfYears, double loanAmount){
		this.annualInterestRate = annualInterestRate;
		this.numberOfYears = numberOfYears;
		this.loanAmount = loanAmount;
		loanDate = new java.util.Date();	

	}
	public double getAnnualInterestRate(){
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double annualInterestRate){
		this.annualInterestRate = annualInterestRate;
	}
	public double getNumberOfYears(){
		return numberOfYears;
	}
	public void setNumberOfYears(double numberOfYears){
		this.numberOfYears = numberOfYears;
	}
	public double getLoanAmount(){
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount){
		this.loanAmount = loanAmount;
	}
	public double getMonthlyPayment(){
		double monthlyInterestRate = annualInterestRate/1200;
		double monthlyPayment = loanAmount * monthlyInterestRate /(1-(1/Math.pow(1 + monthlyInterestRate,  numberOfYears * 12)));
		return monthlyPayment;
	}
	public double getTotalPayment(){
		double totalPayment = getMonthlyPayment() * numberOfYears * 12;
		return totalPayment;
	}
	public java.util.Date getloanDate(){
		return loanDate;
	}
}

	private void calculateLoanPayment(){
		double interest = Double.parseDouble(tfAnnualInterestRate.getText());
		double year = Double.parseDouble(tfNumberofYears.getText());
		double loanAmount = Double.parseDouble(tfLoanAmount.getText());
		
		Loan loan = new Loan(interest, year, loanAmount);
		
		tfMonthlyPayment.setText(String.format("$%.2f", loan.getMonthlyPayment()));
		tfTotalPayment.setText(String.format("$%.2f", loan.getTotalPayment()));
		
		
		
		
		
	}
	
	
	
	
	public static void main(String[] args){
		Application.launch(args);
	}

}
