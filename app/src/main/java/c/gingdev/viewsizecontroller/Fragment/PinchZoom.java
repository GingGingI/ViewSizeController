package c.gingdev.viewsizecontroller.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import c.gingdev.viewsizecontroller.R;
import c.gingdev.viewsizecontroller.Utils.OnRotationGestureDetector;

public class PinchZoom extends Fragment implements View.OnTouchListener, OnRotationGestureDetector.RotationGestureListener {

  public final static String TAG = "PinchZoom";

  private float ScaleFactor = 1.0f;

  private OnRotationGestureDetector RGD;
  private ScaleGestureDetector SGD;
  private ImageView imgView;
  private RelativeLayout layout;
  private TextView ScaleTxt, RotateTxt;

  @Nullable
  @Override
  public View onCreateView ( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
    if ( inflater != null ) {
      return inflater.inflate( R.layout.activity_pinch_zoom, container, false );
    } else {
      return super.onCreateView( inflater, container, savedInstanceState );
    }
  }

  @Override
  public void onViewCreated ( @NonNull View view, @Nullable Bundle savedInstanceState ) {
    super.onViewCreated( view, savedInstanceState );
    imgView = ( ImageView ) view.findViewById( R.id.ImgView );
    ScaleTxt = ( TextView ) view.findViewById( R.id.SizeTxt );
    RotateTxt = ( TextView ) view.findViewById( R.id.RotateTxt );
    layout = (RelativeLayout) view.findViewById( R.id.View );

    SGD = new ScaleGestureDetector( this.getContext(), new PinchZoomListener() );
    RGD = new OnRotationGestureDetector( this );

    view.findViewById( R.id.ContentView ).setOnTouchListener( this );
  }

  @Override
  public boolean onTouch ( View view, MotionEvent motionEvent ) {
    SGD.onTouchEvent( motionEvent );
    RGD.onTouchEvent( motionEvent );
    switch ( motionEvent.getAction() ) {
      case MotionEvent.ACTION_UP:
          layout.setRotation( layout.getRotation() + imgView.getRotation() );
          imgView.setRotation( 0.0f );
        break;
    }
    return true;
  }

  @Override
  public void onRotation ( OnRotationGestureDetector RotationDetector ) {
    imgView.setRotation( RotationDetector.angle );
    RotateTxt.setText( String.format( "Rotation : %.5f", imgView.getRotation() ) );
  }

  public class PinchZoomListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    @Override
    public boolean onScale ( ScaleGestureDetector detector ) {
      ScaleFactor *= detector.getScaleFactor();
      ScaleFactor = Math.max( 0.1f, Math.min( ScaleFactor, 10.0f ) );

      ScaleTxt.setText( String.format( "Scale : %s", ScaleFactor ) );

      layout.setScaleX( ScaleFactor );
      layout.setScaleY( ScaleFactor );
      return true;
    }
  }
}
