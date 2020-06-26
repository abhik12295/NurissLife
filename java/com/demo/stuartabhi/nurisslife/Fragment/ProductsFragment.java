package com.demo.stuartabhi.nurisslife.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.stuartabhi.nurisslife.FlipView.Friend;
import com.demo.stuartabhi.nurisslife.FlipView.Utils;
import com.demo.stuartabhi.nurisslife.R;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {


    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActionBar().setTitle("Products");
        getActionBar().setSubtitle("For You");

        final ListView friends = (ListView) inflater.inflate(R.layout.activity_friends,container,false);
        //final LinearLayout ll=(LinearLayout)inflater.inflate(R.layout.fragment_products,container,false);
        //final ListView friends=(ListView)inflater.inflate(R.layout.fragment_products,container,false);
        FlipSettings settings=new FlipSettings.Builder().defaultPage(1).build();
        friends.setAdapter(new FriendsAdapter(getActivity(), Utils.friends,settings));
        friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Friend friend = (Friend) friends.getAdapter().getItem(position);
                Toast.makeText(getContext(), friend.getNickname(), Toast.LENGTH_SHORT).show();

            }
        });
         return friends;
    }

   class FriendsAdapter extends BaseFlipAdapter<Friend> {

       private final int PAGES = 3;
        private int[] IDS_INTEREST = {R.id.interest_1, R.id.interest_2, R.id.interest_3};

        public FriendsAdapter(Context context, List<Friend> items, FlipSettings settings) {
            super(context, items, settings);
       }


       @Override
       public View getPage(int position, View convertView, ViewGroup parent, Friend friend1, Friend friend2) {
           final FriendsHolder holder;

           if (convertView == null) {
               holder = new FriendsHolder();
               convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_products, parent, false);
               holder.leftAvatar = (ImageView) convertView.findViewById(R.id.first);
               holder.rightAvatar = (ImageView) convertView.findViewById(R.id.second);
               holder.infoPage = getActivity().getLayoutInflater().inflate(R.layout.friends_info, parent, false);
               holder.nickName = (TextView) holder.infoPage.findViewById(R.id.nickname);

               for (int id : IDS_INTEREST)
                   holder.interests.add((TextView) holder.infoPage.findViewById(id));

               convertView.setTag(holder);
           } else {
               holder = (FriendsHolder) convertView.getTag();
           }

           switch (position) {
               // Merged page with 2 friends
               case 1:
                   holder.leftAvatar.setImageResource(((Friend) friend1).getAvatar());
                   if (friend2 != null)
                       holder.rightAvatar.setImageResource(((Friend) friend2).getAvatar());
                   break;
               default:
                   fillHolder(holder, position == 0 ? (Friend) friend1 : (Friend) friend2);
                   holder.infoPage.setTag(holder);
                   return holder.infoPage;
           }
           return convertView;
       }

       @Override
        public int getPagesCount() {
            return PAGES;
       }

        private void fillHolder(FriendsHolder holder, Friend friend) {
            if (friend == null)
                return;
            Iterator<TextView> iViews = holder.interests.iterator();
            Iterator<String> iInterests = friend.getInterests().iterator();
            while (iViews.hasNext() && iInterests.hasNext())
                iViews.next().setText(iInterests.next());
            holder.infoPage.setBackgroundColor(getResources().getColor(friend.getBackground()));
            holder.nickName.setText(friend.getNickname());
        }

        class FriendsHolder {
            ImageView leftAvatar;
            ImageView rightAvatar;
            View infoPage;

            List<TextView> interests = new ArrayList<>();
            TextView nickName;
        }
    }


    private ActionBar getActionBar() {
        return ((AppCompatActivity)getActivity()).getSupportActionBar();
    }



}
