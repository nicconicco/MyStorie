package mvvm.feature.home.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.model.Constants;
import com.cgalves.mystorie.feature.home.model.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scopus on 17/07/18.
 */

public class PhotosAdapter extends PagerAdapter {

    private final Context context;
    private List<Image> list = new ArrayList<>();

    public PhotosAdapter(Context context, List<Image> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.custom_viewpager_home, container, false);
        ImageView imageView = layout.findViewById(R.id.img);

        try {
            imageView.setImageDrawable(list.get(position).getImage());
        } catch (Exception e) {
            Log.e(Constants.ERROR_EXCEPTION, e.getMessage());
        }

        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
