package ch.ptoj.monitorenergie.android;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.main_fragment,container, false);     
     return view;
  }
  
  
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Button bIndex = (Button) getActivity().findViewById(R.id.button_index);
		Button bReleve = (Button) getActivity().findViewById(R.id.button_releve);

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

		super.onActivityCreated(savedInstanceState);
	}
  
	  private void showIndex(){
		  	Intent intent=new Intent(getActivity(),IndexListActivity.class);
		  	startActivity(intent);
		  }
	  
	  private void showDetail(){
		  	Intent intent=new Intent(getActivity(),IndexDetailActivity.class);
		  	startActivity(intent);
		  }


} 
