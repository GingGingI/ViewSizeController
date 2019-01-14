package c.gingdev.viewsizecontroller.Utils;

import android.view.MotionEvent;

public class OnRotationGestureDetector {
  private static final int INVALID_PONTER_ID = -1;
  private float fx, fy, sx, sy;
  private int ptrID1, ptrID2;
  public float angle;

  private RotationGestureListener listener;

  public OnRotationGestureDetector(RotationGestureListener listener) {
    this.listener = listener;
    this.ptrID1 = INVALID_PONTER_ID;
    this.ptrID2 = INVALID_PONTER_ID;
  }

  public boolean onTouchEvent( MotionEvent event ) {
    switch ( event.getActionMasked() ) {
      case MotionEvent.ACTION_DOWN:
        ptrID1 = event.getPointerId( event.getActionIndex() );
        break;

      case MotionEvent.ACTION_POINTER_DOWN:
        ptrID2 = event.getPointerId( event.getActionIndex() );
        sx = event.getX(event.findPointerIndex( ptrID1 ));
        sy = event.getY(event.findPointerIndex( ptrID1 ));
        fx = event.getX(event.findPointerIndex( ptrID2 ));
        fy = event.getY(event.findPointerIndex( ptrID2 ));
        break;

      case MotionEvent.ACTION_MOVE:
        if ( ptrID1 != INVALID_PONTER_ID && ptrID2 != INVALID_PONTER_ID ) {
          float nfx, nfy, nsx, nsy;
          nsx = event.getX(event.findPointerIndex( ptrID1 ));
          nsy = event.getY(event.findPointerIndex( ptrID1 ));
          nfx = event.getX(event.findPointerIndex( ptrID2 ));
          nfy = event.getY(event.findPointerIndex( ptrID2 ));

          angle = angleBetweenLines( fx, fy, sx, sy, nfx, nfy, nsx, nsy );

          if ( listener != null ) {
            listener.onRotation( this );
          }
        }
        break;

      case MotionEvent.ACTION_UP:
        ptrID1 = INVALID_PONTER_ID;
        break;

      case MotionEvent.ACTION_POINTER_UP:
        ptrID2 = INVALID_PONTER_ID;
        break;

      case MotionEvent.ACTION_CANCEL:
        ptrID1 = INVALID_PONTER_ID;
        ptrID2 = INVALID_PONTER_ID;
        break;
    }
    return true;
  }

  private float angleBetweenLines (float fx, float fy, float sx, float sy, float nfx, float nfy, float nsx, float nsy) {
    float angle1 = ( float ) Math.atan2( (fy - sy), (fx - sx) );
    float angle2 = ( float ) Math.atan2( (nfy - nsy), (nfx - nsx) );

    float angle = (( float ) Math.toDegrees(  angle2 - angle1 )) % 360;
    if ( angle < -180.f ) angle += 360.f;
    if ( angle > 180.f ) angle -= 360.f;
    return angle;
  }

  public interface RotationGestureListener {
    void onRotation(OnRotationGestureDetector RotationDetector);
  }
}
