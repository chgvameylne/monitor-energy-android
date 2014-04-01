package ch.ptoj.monitorenergie.android;


import android.app.Application;

public class MonitorEnergie extends Application {

	private static MonitorEnergie myInstance;
	
	private String pathApplication;
	private Account account;
	
	public static MonitorEnergie getInstance(){
		return myInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		myInstance=this;
		initAccount();	
    	pathApplication="https://www.ptojit.ch/monitorenergy";
	}
	
	private void initAccount(){
	   account=new Account("","");
	}
	
	
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void login(OnTaskCompleted onTaskCompleted){		
		RestFull rest=new RestFull(pathApplication);
		rest.setOnTaskCompleted(onTaskCompleted);
		rest.login(account);
	}

	public void logout(OnTaskCompleted onTaskCompleted){		
		RestFull rest=new RestFull(pathApplication);
		rest.setOnTaskCompleted(onTaskCompleted);
		rest.logout(account);
	}

	public void listFluids(OnTaskCompleted onTaskCompleted){		
		RestFull rest=new RestFull(pathApplication);
		rest.setOnTaskCompleted(onTaskCompleted);
		rest.listFluids(account);
	}

	
}
