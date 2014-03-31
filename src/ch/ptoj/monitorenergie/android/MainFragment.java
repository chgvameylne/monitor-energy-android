package ch.ptoj.monitorenergie.android;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment implements OnTaskCompleted {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_fragment, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Button bIndex = (Button) getActivity().findViewById(R.id.button_index);
		Button bReleve = (Button) getActivity()
				.findViewById(R.id.button_releve);
		Button bLogin = (Button) getActivity().findViewById(R.id.button_login);
		Button bLogout = (Button) getActivity().findViewById(R.id.button_logout);
		Button bFluids = (Button) getActivity()
				.findViewById(R.id.button_fluids);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
		
		TextView tv_user=(TextView)getActivity().findViewById(R.id.editText_user);
		tv_user.setText(preferences.getString("login.user", ""));
		TextView tv_pass=(TextView)getActivity().findViewById(R.id.editText_pass);
		tv_pass.setText(preferences.getString("login.pass", ""));
		
		bIndex.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showIndex();
			}
		});

		bReleve.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDetail();
			}
		});

		bLogin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				login();
			}
		});

		bLogout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				logout();
			}
		});

		bFluids.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				fluids();
			}
		});

		super.onActivityCreated(savedInstanceState);
	}

	private void savePreference(){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
		SharedPreferences.Editor editor=preferences.edit();
		TextView tv_user=(TextView)getActivity().findViewById(R.id.editText_user);
		editor.putString("login.user", tv_user.getText().toString());
		TextView tv_pass=(TextView)getActivity().findViewById(R.id.editText_pass);
		editor.putString("login.pass", tv_pass.getText().toString());
		editor.commit();
		
	}
	
	@Override
	public void onPause() {
		savePreference();
		super.onPause();
	}

	private void showIndex() {
		Intent intent = new Intent(getActivity(), IndexListActivity.class);
		startActivity(intent);
	}

	private void showDetail() {
		Intent intent = new Intent(getActivity(), IndexDetailActivity.class);
		startActivity(intent);
	}

	private void login() {
		
		TextView tv_user=(TextView)getActivity().findViewById(R.id.editText_user);
		TextView tv_pass=(TextView)getActivity().findViewById(R.id.editText_pass);
		Account account=new Account(tv_user.getText().toString(),tv_pass.getText().toString());
		MonitorEnergie.getInstance().setAccount(account);
		
		
		MonitorEnergie.getInstance().login(this);
	}

	private void logout() {
		MonitorEnergie.getInstance().logout(this);
	}

	private void fluids() {
		MonitorEnergie.getInstance().listFluids(this);
	}

	@Override
	public void onTaskCompleted(String message) {
		if(message!=null){
		   Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
		}
	}

}
