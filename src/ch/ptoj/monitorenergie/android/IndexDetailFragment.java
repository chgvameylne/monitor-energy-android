package ch.ptoj.monitorenergie.android;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class IndexDetailFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.fragment_index_detail,container, false);
     return view;
  }
  
  
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(getActivity().getIntent()!=null&&getActivity().getIntent().getExtras()!=null){
    		String item=getActivity().getIntent().getExtras().getString("item");
	    	TextView tv=(TextView)getActivity().findViewById(R.id.textView1);
		    tv.setText(item);
		}
	}
  
	public void setText(String text){
    	TextView tv=(TextView)getActivity().findViewById(R.id.textView1);
	    tv.setText(text);
		
	}


} 
