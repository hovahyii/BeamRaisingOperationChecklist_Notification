package com.hovah_inc.beamraisingoperationchecklist.document;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hovah_inc.beamraisingoperationchecklist.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class BooksAdapter extends BaseAdapter {

    private final Context mContext;
    private final Book[] books;

    public BooksAdapter (Context context, Book[] books) {
        this.mContext = context;
        this.books = books;
    }


    @Override
    public int getCount() {
        return books.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Book book = books[position];

        //view holder pattern
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_book, null);

            final ImageView imageViewCoverArt = convertView.findViewById(R.id.imageview_cover_art);
            final TextView nameTextView = convertView.findViewById(R.id.textview_book_name);
            final TextView authorTextView = convertView.findViewById(R.id.textview_book_author);
            final ImageView imageViewFavorite = convertView.findViewById(R.id.imageview_favorite);

            final ViewHolder viewHolder = new ViewHolder(nameTextView, authorTextView, imageViewCoverArt, imageViewFavorite);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
//        viewHolder.imageViewCoverArt.setImageResource(book.getImageResource());
        Picasso.with(mContext).load(book.getImageUrl()).into(viewHolder.imageViewCoverArt);



        viewHolder.nameTextView.setText(mContext.getString(book.getName()));
        viewHolder.authorTextView.setText(mContext.getString(book.getAuthor()));
        viewHolder.imageViewFavorite.setImageResource(book.getIsFavorite() ? R.drawable.splash : R.drawable.splash);

        return convertView;
    }

    private class ViewHolder {
        private final TextView nameTextView;
        private final TextView authorTextView;
        private final ImageView imageViewCoverArt;
        private final ImageView imageViewFavorite;

        public ViewHolder (TextView nameTextView, TextView authorTextView, ImageView imageViewCoverArt, ImageView imageViewFavorite) {
            this.nameTextView = nameTextView;
            this.authorTextView = authorTextView;
            this.imageViewCoverArt = imageViewCoverArt;
            this.imageViewFavorite = imageViewFavorite;
        }
    }

}
