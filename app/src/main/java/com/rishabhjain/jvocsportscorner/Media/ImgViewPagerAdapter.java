package com.rishabhjain.jvocsportscorner.Media;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rishabhjain.jvocsportscorner.R;

public class ImgViewPagerAdapter extends PagerAdapter {
    Context context;
    int[] image;
    LayoutInflater inflater;

    public ImgViewPagerAdapter(Context context, int[] image) {
        this.context = context;
        this.image = image;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imgimage;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_image, container, false);

        imgimage = (ImageView) itemView.findViewById(R.id.detail_image);

//        imgimage.setImageResource(image[position]);

        Glide.with(context)
                .load(image[position])
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgimage);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        container.removeView((View) object);

    }
}