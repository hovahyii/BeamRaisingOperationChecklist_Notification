package com.hovah_inc.beamraisingoperationchecklist.document;


public class Book {
    private final int name;
    private final int author;
    private final int imageResource;
    private boolean isFavorite = false;
    private final String imageUrl;

    public Book(int name, int author, int imageResource, String imageUrl) {
        this.name = name;
        this.author = author;
        this.imageResource = imageResource;
        this.imageUrl = imageUrl;
    }

    public int getName() {
        return name;
    }

    public int getAuthor() {
        return author;
    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }
    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
