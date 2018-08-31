package mvvm.feature.home.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.feature.contact.view.ContactFragment_;
import com.cgalves.mystorie.feature.noticias.view.fragment.NoticiasFragment_;
import com.cgalves.mystorie.feature.novidades.view.fragment.NovidadesFragment_;

/**
 * Created by scopus on 27/07/18.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public HomeFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NovidadesFragment_();
        } else if (position == 1){
            return new NoticiasFragment_();
        } else if (position == 2){
            return new ContactFragment_();
        } else {
            return new ContactFragment_();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.novidades);
            case 1:
                return mContext.getString(R.string.noticias);
            case 2:
                return mContext.getString(R.string.contato);
            default:
                return null;
        }
    }
}
