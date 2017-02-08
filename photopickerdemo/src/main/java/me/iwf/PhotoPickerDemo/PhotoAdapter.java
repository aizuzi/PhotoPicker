package me.iwf.PhotoPickerDemo;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.util.ArrayList;

import me.iwf.photopicker.R;
import me.iwf.photopicker.utils.AndroidLifecycleUtils;

/**
 * Created by donglua on 15/5/31.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

  private ArrayList<String> photoPaths = new ArrayList<String>();
  private LayoutInflater inflater;

  private Context mContext;

  final static int TYPE_ADD = 1;
  final static int TYPE_PHOTO = 2;

  final static int MAX = 9;

  public PhotoAdapter(Context mContext, ArrayList<String> photoPaths) {
    this.photoPaths = photoPaths;
    this.mContext = mContext;
    inflater = LayoutInflater.from(mContext);

  }


  @Override public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = null;
    switch (viewType) {
      case TYPE_ADD:
        itemView = inflater.inflate(me.iwf.PhotoPickerDemo.R.layout.item_add, parent, false);
        break;
      case TYPE_PHOTO:
        itemView = inflater.inflate(R.layout.__picker_item_photo, parent, false);
        break;
    }
    return new PhotoViewHolder(itemView);
  }


  @Override
  public void onBindViewHolder(final PhotoViewHolder holder, final int position) {

    boolean canLoadImage = holder.ivPhoto != null && AndroidLifecycleUtils.canLoadImage(holder.ivPhoto.getContext());

    if(canLoadImage) {
      Uri uri = Uri.fromFile(new File(photoPaths.get(position)));

      ImageRequest request = ImageRequestBuilder
              .newBuilderWithSource(uri)
              .setAutoRotateEnabled(true)
              .build();
      PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
              .setOldController(holder.ivPhoto.getController())
              .setImageRequest(request)
              .setAutoPlayAnimations(true)
              .build();
      holder.ivPhoto.setController(controller);
    }
  }


  @Override public int getItemCount() {
    int count = photoPaths.size() + 1;
    if (count > MAX) {
      count = MAX;
    }
    return count;
  }

  @Override
  public int getItemViewType(int position) {
    return (position == photoPaths.size() && position != MAX) ? TYPE_ADD : TYPE_PHOTO;
  }

  public static class PhotoViewHolder extends RecyclerView.ViewHolder {
    private SimpleDraweeView ivPhoto;
    private View vSelected;
    public PhotoViewHolder(View itemView) {
      super(itemView);
      ivPhoto   = (SimpleDraweeView) itemView.findViewById(R.id.iv_photo);
      vSelected = itemView.findViewById(R.id.v_selected);
      if (vSelected != null) vSelected.setVisibility(View.GONE);
    }
  }

}
