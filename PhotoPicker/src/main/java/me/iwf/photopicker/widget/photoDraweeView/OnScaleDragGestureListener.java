package me.iwf.photopicker.widget.photoDraweeView;

/**
 * Created by jwu on 16/1/5.
 */
public interface OnScaleDragGestureListener {

    void onDrag(float dx, float dy);

    void onFling(float startX, float startY, float velocityX, float velocityY);

    void onScale(float scaleFactor, float focusX, float focusY);

    void onScaleEnd();
}
