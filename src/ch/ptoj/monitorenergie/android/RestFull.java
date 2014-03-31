package ch.ptoj.monitorenergie.android;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.DefaultHttpClient;


import android.os.AsyncTask;
import android.util.Log;

public class RestFull {
	
	private OnTaskCompleted onTaskCompleted;
	private String pathApplication;

	public RestFull(String pathApplication) {
		this.pathApplication = pathApplication;
	}

	public void setOnTaskCompleted(OnTaskCompleted onTaskCompleted) {
		this.onTaskCompleted = onTaskCompleted;
	}
	
	public void login(final Account account) {
		
		ToDoHttp todo=new ToDoHttp();
		todo.setMethod(ToDoHttp.MethodType.POST);
		todo.setUrl(pathApplication + "/j_spring_security_check");
		todo.setAccount(account);
		
		FormParam form=new FormParam();
		form.addParameter("j_username", account.getUsername());
		form.addParameter("j_password", account.getPassword());
		form.addParameter("returnStatus", "true");
		todo.setForm(form);

		new LoadHTTP().execute(todo);
	}
	
	public void logout(final Account account) {
		ToDoHttp todo=new ToDoHttp();
		todo.setMethod(ToDoHttp.MethodType.GET);
		todo.setUrl(pathApplication + "/logout");
		todo.setAccount(account);

		new LoadHTTP().execute(todo);
	}
	
	public void listFluids(Account account) {
		ToDoHttp todo=new ToDoHttp();
		todo.setMethod(ToDoHttp.MethodType.GET);
		todo.setUrl(pathApplication + "/private/rest/fluids");
		todo.setAccount(account);

		new LoadHTTP().execute(todo);
	}
	
	class LoadHTTP extends AsyncTask<ToDoHttp, Void, ResultHttp> {
	
		@Override
		protected ResultHttp doInBackground(ToDoHttp... todo) {
			
			DefaultHttpClient httpclient = new DefaultHttpClient();						
            todo[0].setCredential(httpclient);
			try {
				HttpResponse response = httpclient.execute(todo[0].getRequest());
								
				ResultHttp result=new ResultHttp();
				int code = response.getStatusLine().getStatusCode();
                result.setCode(code);				
				if(code==HttpStatus.SC_OK){
					todo[0].extractCredential(httpclient);
					HttpEntity entity = response.getEntity();
					result.setBody(ResponseTools.getResponseBody(entity));
				}
												
				return result;
				
			} catch (Exception e) {
				Log.d("RestFull", "Error " + e.getMessage(), e);
			}
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(ResultHttp result) {
			if(onTaskCompleted!=null){
				if(result.getCode()==HttpStatus.SC_OK){
				   onTaskCompleted.onTaskCompleted("OK - "+result.getBody());
				}
				else{
				   onTaskCompleted.onTaskCompleted("UNAUTHORIZED");					
				}
			}
			
			super.onPostExecute(result);
		}
	}
}
