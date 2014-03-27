package ch.ptoj.monitorenergie.android;


import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;

public class IndexListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index_list);

		FragmentManager fm = getFragmentManager();

		// Check to see if the Fragment back stack has been populated
		// If not, create and populate the layout.
		IndexDetailFragment detailsFragment = (IndexDetailFragment) fm
				.findFragmentById(R.id.details_container);

		if (detailsFragment == null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.list_container, new IndexListFragment());
			ft.add(R.id.details_container, new IndexDetailFragment());
			ft.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index_list, menu);
		return true;
	}

}
