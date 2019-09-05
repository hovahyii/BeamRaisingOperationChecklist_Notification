package com.hovah_inc.beamraisingoperationchecklist.document;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.hovah_inc.beamraisingoperationchecklist.R;

import java.util.ArrayList;

public class Document extends AppCompatActivity {

    private static final String favoritedBookNamesKey = "favoritedBookNamesKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);

        GridView gridView = findViewById(R.id.gridview);
        final BooksAdapter booksAdapter = new BooksAdapter(this, books);
        gridView.setAdapter(booksAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Book book = books[position];
                book.toggleFavorite();

                booksAdapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);

        //Construct a list of books you've favorited
        final ArrayList<Integer> favoritedBookNames = new ArrayList<>();
        for (Book book : books) {
            if (book.getIsFavorite()) {
                favoritedBookNames.add(book.getName());
            }
        }

        //Save that list to outState for later
        outstate.putIntegerArrayList(favoritedBookNamesKey, favoritedBookNames);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //Get our previously saved list of favorited books
        final ArrayList<Integer> favoritedBookNames =
                savedInstanceState.getIntegerArrayList(favoritedBookNamesKey);

        //Look at all your books and figure out which ones are the favorites
        for (int bookName : favoritedBookNames) {
            for (Book book : books) {
                if (book.getName() == bookName) {
                    book.setIsFavorite(true);
                    break;
                }
            }
        }
    }



    private Book[] books = {
            new Book(R.string.abc_an_amazing_alphabet_book, R.string.dr_seuss, R.drawable.splash,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/abc.jpg"),
            new Book(R.string.are_you_my_mother, R.string.dr_seuss, R.drawable.splash,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/areyoumymother.jpg"),
            new Book(R.string.where_is_babys_belly_button, R.string.karen_katz, R.drawable.splash,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/whereisbabysbellybutton.jpg"),
            new Book(R.string.on_the_night_you_were_born, R.string.nancy_tillman, R.drawable.splash,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/onthenightyouwereborn.jpg"),
            new Book(R.string.hand_hand_fingers_thumb, R.string.dr_seuss, R.drawable.splash,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/handhandfingersthumb.jpg"),
            new Book(R.string.the_very_hungry_caterpillar, R.string.eric_carle, R.drawable.splash,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/theveryhungrycaterpillar.jpg"),
            new Book(R.string.the_going_to_bed_book, R.string.sandra_boynton, R.drawable.splash,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/thegoingtobedbook.jpg"),
            new Book(R.string.oh_baby_go_baby, R.string.dr_seuss, R.drawable.splash,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/ohbabygobaby.jpg"),
            new Book(R.string.the_tooth_book, R.string.dr_seuss, R.drawable.splash,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/thetoothbook.jpg"),
            new Book(R.string.one_fish_two_fish_red_fish_blue_fish, R.string.dr_seuss, R.drawable.splash,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/onefish.jpg")
    };

}
