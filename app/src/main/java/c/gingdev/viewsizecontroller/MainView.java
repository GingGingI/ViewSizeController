package c.gingdev.viewsizecontroller;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import c.gingdev.viewsizecontroller.CallbackInterfaces.onItemClickListener;
import c.gingdev.viewsizecontroller.Fragment.MainListFragment;
import c.gingdev.viewsizecontroller.Fragment.PinchZoom;

public class MainView extends AppCompatActivity implements onItemClickListener {

  @Override
  protected void onCreate ( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main_view );

    if ( savedInstanceState == null ) {
      MainListFragment fragment = new MainListFragment();

      getSupportFragmentManager().beginTransaction()
              .add( R.id.FragmentContent, fragment )
              .commit();
    }
  }

  @Override
  public void onListItemClicked ( int position ) {
    Fragment fragment = null;
    switch ( position ) {
      case 0:
//        Pinch Zoom
        fragment = new PinchZoom();

        getSupportFragmentManager().beginTransaction()
                .replace( R.id.FragmentContent, fragment )
                .addToBackStack( null )
                .commit();
        break;
      case 1:
//        Button Zoom

        break;
    }
  }
}
