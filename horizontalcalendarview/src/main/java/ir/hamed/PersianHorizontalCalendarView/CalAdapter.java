package ir.hamed.PersianHorizontalCalendarView;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by UManzoor on 11/28/2017.
 */

public class CalAdapter extends RecyclerView.Adapter<CalAdapter.MyViewHolder> {

    Context context;
    ColorListener colorListener;

    HorizontalCalendarListener toCallBack;

    DayDateMonthYearModel lastDaySelected;


    LinearLayout clickedTextView = null;
    int moth = 0;
    @SuppressLint("UseSparseArrays")
    HashMap<Integer, DayDateMonthYearModel> dayDateMonthYearModelHashMap = new HashMap<>();

    public CalAdapter(Context context, ColorListener colorListener) {
        this.context = context;
        this.colorListener = colorListener;
    }

    public void setCallback(HorizontalCalendarListener toCallBack) {
        this.toCallBack = toCallBack;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView day, date, month;

        public MyViewHolder(View view) {
            super(view);
            day = view.findViewById(R.id.day);
            day.setTextColor(colorListener.getDayColor());
            date = view.findViewById(R.id.date);
            date.setTextColor(colorListener.getDateColor());
            month = view.findViewById(R.id.month);
            month.setTextColor(colorListener.getMonthColor());
        }
    }

    @Override
    public CalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_day_layout, parent, false);
        return new CalAdapter.MyViewHolder(itemView);
    }


    public DayDateMonthYearModel getItem(int position) {
        DayDateMonthYearModel dayDateMonthYearModel = dayDateMonthYearModelHashMap.get(position);

        if (dayDateMonthYearModel == null) {
            long day = position - 500;
            day *= -86400000;
            Date date = new Date(new Date().getTime() - day);
            PersianDate pdate = new PersianDate(date);
            PersianDateFormat dayFormater = new PersianDateFormat("d");
            PersianDateFormat monthFormater = new PersianDateFormat("n");
            dayDateMonthYearModel = new DayDateMonthYearModel();
            dayDateMonthYearModel.date = dayFormater.format(pdate);
            dayDateMonthYearModel.day = pdate.dayName();
            dayDateMonthYearModel.GregorianDate = date;
            dayDateMonthYearModel.persianDate = pdate;
            dayDateMonthYearModel.isToday = day == 0;
            dayDateMonthYearModel.month = pdate.monthName();
            dayDateMonthYearModel.monthOfYear = Integer.parseInt(monthFormater.format(pdate));
            dayDateMonthYearModel.year = pdate.getShYear() + "";
            dayDateMonthYearModel.monthNumeric = pdate.getShMonth() + "";
            dayDateMonthYearModelHashMap.put(position, dayDateMonthYearModel);
        }
        return dayDateMonthYearModel;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        DayDateMonthYearModel dayDateMonthYearModel = getItem(position);


        String t = dayDateMonthYearModel.day/*.charAt(0)*/;


        if (dayDateMonthYearModel.monthOfYear != moth) {
            if (toCallBack != null) {
                toCallBack.updateMonthOnScroll(dayDateMonthYearModel);
            }
            moth = dayDateMonthYearModel.monthOfYear;
        }
        if (position == 500)
            holder.itemView.setBackground(colorListener.getBackgroundToday());
        else {
            if (lastDaySelected != null && lastDaySelected.pos == position)
                holder.itemView.setBackground(colorListener.getBackgroundSelect());
            else if (clickedTextView != null)
                clickedTextView.setBackground(null);

        }

        holder.day.setText(t);
        holder.month.setText(dayDateMonthYearModel.month);
        holder.date.setText(dayDateMonthYearModel.date);
        holder.date.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedTextView != null)
                    if (lastDaySelected.pos == 500)
                        clickedTextView.setBackground(colorListener.getBackgroundToday());
                    else
                        clickedTextView.setBackground(null);

                clickedTextView = (LinearLayout) v;
                clickedTextView.setBackground(colorListener.getBackgroundSelect());

                if (toCallBack != null) {
                    toCallBack.newDateSelected(getItem(position));
                }
                lastDaySelected = getItem(position);
                lastDaySelected.pos = position;

            }
        });
    }

    @Override
    public int getItemCount() {
        return 1000;
    }

    @Override
    public void onViewAttachedToWindow(MyViewHolder holder) {
        holder.setIsRecyclable(false);
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        holder.setIsRecyclable(false);
        super.onViewDetachedFromWindow(holder);
    }

}
