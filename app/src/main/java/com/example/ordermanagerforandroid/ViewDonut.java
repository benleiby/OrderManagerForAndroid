package com.example.ordermanagerforandroid;

/**
 * Container to associate images with donut MenuItems.
 * @author Benjamin Leiby
 */
public class ViewDonut extends Donut {

    private int image;

    public ViewDonut(DonutVariety variety, int quantity, int image) {
        super(variety, quantity);
        this.image = image;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
