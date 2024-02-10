package com.company.diamonds.logic;

import com.company.diamonds.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */
    public void process(int size) {
        int h_count = 0;
        int size_count = size+1;
        for(int h = 0; h < size*2+1; h++) {
            for (int w = 0; w < size * 2 + 2; w++) {
                if ((h == 0 || h == size * 2) && (w == 0 || w == size * 2 + 1)) { // Углы рамки
                    mOut.print('+');
                } else if ((h != 0 || h != size * 2) && (w == 0 || w == size * 2 + 1)) { // Вертикальные стороны рамки
                    mOut.print('|');
                } else if ((h == 0 || h == size * 2) && (w != 0 || w != size * 2 + 1)) { // Горизонтальные стороны рамки
                    mOut.print('-');
                } else if ((h != 0 || h != size * 2) && (w != 0 || w != size * 2 + 1)) { // Содержимое рамки
                    if (h == Math.ceil((size * 2 + 1) / 2)) { // Середина ромба
                        if (w == 1) {
                            mOut.print('<');
                        } else if (w == size * 2) {
                            mOut.print('>');
                        } else {
                            if (h % 2 == 0) {
                                mOut.print('-');
                            } else {
                                mOut.print('=');
                            }
                        }
                    } else if (h < Math.ceil((size * 2 + 1) / 2)) { // Ромб выше середине
                        if (w <= size) { // Ромб до центра
                            if (w == size_count) { // Грани
                                mOut.print('/');
                            } else if (w > size_count) {
                                if (h % 2 == 0) {
                                    mOut.print('-');
                                } else {
                                    mOut.print('=');
                                }
                            } else {
                                mOut.print(' ');
                            }
                        } else if (w > size) { // Ромб после центра
                            if (w == size + h_count) { // Грани
                                mOut.print('\\');
                            } else if (w < size + h_count) {
                                if (h % 2 == 0) {
                                    mOut.print('-');
                                } else {
                                    mOut.print('=');
                                }
                            } else {
                                mOut.print(' ');
                            }
                        }
                    } else if (h > Math.ceil((size * 2 + 1) / 2)) { // Ромб ниже середины
                        if (w <= size) { // Ромб до центра
                            if (w == size_count) { // Грани
                                mOut.print('\\');
                            } else if (w > size_count) {
                                if (h % 2 == 0) {
                                    mOut.print('-');
                                } else {
                                    mOut.print('=');
                                }
                            } else {
                                mOut.print(' ');
                            }
                        } else if (w > size) { // Ромб после центра
                            if (w == size + h_count) { // Грани
                                mOut.print('/');
                            } else if (w < size + h_count) {
                                if (h % 2 == 0) {
                                    mOut.print('-');
                                } else {
                                    mOut.print('=');
                                }
                            } else {
                                mOut.print(' ');
                            }
                        }
                    }
                }
            }
            mOut.print('\n');
            if (h < size) { // До середины, грань движется к краю, а после к центру
                ++h_count; // Для правой половины ромба
                --size_count;
            } else {
                --h_count;
                ++size_count;
            }
        }
    }

}
