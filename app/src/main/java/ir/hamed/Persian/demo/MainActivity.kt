package ir.hamed.Persian.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ir.hamed.PersianHorizontalCalendarView.DayDateMonthYearModel
import ir.hamed.PersianHorizontalCalendarView.HorizontalCalendarListener
import ir.hamed.PersianHorizontalCalendarView.PersianCalendar

class MainActivity : AppCompatActivity(), HorizontalCalendarListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val hcv = findViewById<PersianCalendar>(R.id.hcv)
        hcv.setCallBack(this@MainActivity)
    }

    override fun updateMonthOnScroll(selectedDate: DayDateMonthYearModel?) {
        Toast.makeText(this@MainActivity, selectedDate?.date + "" + selectedDate?.month + " " + selectedDate?.year, Toast.LENGTH_LONG).show()
    }

    override fun newDateSelected(selectedDate: DayDateMonthYearModel?) {
        Toast.makeText(this@MainActivity, selectedDate?.date + "" + selectedDate?.month + " " + selectedDate?.year, Toast.LENGTH_LONG).show()
    }

}
