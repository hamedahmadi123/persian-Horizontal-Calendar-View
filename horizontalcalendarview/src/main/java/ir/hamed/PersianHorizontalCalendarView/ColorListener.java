package ir.hamed.PersianHorizontalCalendarView;

import android.graphics.drawable.Drawable;

public interface ColorListener {
    Drawable getBackgroundSelect();
    Drawable getBackgroundToday();
    int getDayColor();
    int getDateColor();
    int getMonthColor();
}
