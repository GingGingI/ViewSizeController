package c.gingdev.viewsizecontroller.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import c.gingdev.viewsizecontroller.CallbackInterfaces.onItemClickListener;
import c.gingdev.viewsizecontroller.R;

public class MainListFragment extends ListFragment {
  private onItemClickListener itemClickListener;

  @Override
  public void onAttach ( Activity activity ) {
    super.onAttach( activity );
    itemClickListener = ( onItemClickListener ) activity;
  }

  @Override
  public void onActivityCreated ( @Nullable Bundle savedInstanceState ) {
    super.onActivityCreated( savedInstanceState );

    final String[] items = getResources().getStringArray( R.array.lists );
    final ArrayAdapter<String> adapter = new ArrayAdapter<>( getActivity(), android.R.layout.simple_list_item_1, items );
    setListAdapter( adapter );
  }

  @Override
  public void onListItemClick ( ListView l, View v, int position, long id ) {
    itemClickListener.onListItemClicked( position );
  }
}
