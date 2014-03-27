package ch.ptoj.monitorenergie.android;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class IndexListFragment extends ListFragment {

	/*
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container,Bundle savedInstanceState) { View view =
	 * inflater.inflate(R.layout.indexlist_fragment,container, false); return
	 * view; }
	 */

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] values = getListIndex();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
        String value=(String)l.getAdapter().getItem(position);	
    	Intent intent=new Intent(getActivity(),IndexDetailActivity.class);
    	intent.putExtra("item", value);
	 	startActivity(intent);
	}
	
	private String[] getListIndex(){
		return new String[] {"Chauffage","Electricité I","Electrcité II"};
	}
}
