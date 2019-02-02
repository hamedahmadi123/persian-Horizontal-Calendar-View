package ir.hamed.PersianHorizontalCalendarView;

public interface HorizontalCalendarListener {
    void updateMonthOnScroll(DayDateMonthYearModel selectedDate);
    void newDateSelected(DayDateMonthYearModel selectedDate);
}
