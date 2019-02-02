package ir.hamed.PersianHorizontalCalendarView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable

import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_calender_layout.view.*


class PersianCalendarView : LinearLayout, ColorListener {

    private var backgroundSelect: Drawable? = null
    var dayColor1 = -1
    private var DateColor1 = -1
    private var MonthColor1 = -1

    override fun getbackgroundSelect(): Drawable? {
        return backgroundSelect
    }

    override fun getDayColor(): Int {
        return dayColor1
    }

    override fun getDateColor(): Int {
        return DateColor1
    }

    override fun getMonthColor(): Int {
        return MonthColor1
    }


    private var calAdapter: CalAdapter
    private var linearLayoutManager: LinearLayoutManager
    var toCallBack: HorizontalCalendarListener? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init1(context, attrs, 0)

    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init1(context, attrs, defStyleAttr)
    }

    @SuppressLint("ResourceAsColor")
    private fun init1(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context!!.obtainStyledAttributes(attrs, R.styleable.PersianCalendarView)

        if (typedArray.hasValue(R.styleable.PersianCalendarView_pcv_background)) {
            setBackgroundColor(typedArray.getColor(R.styleable.PersianCalendarView_pcv_background, R.color.white))
        }
        dayColor1 = typedArray.getColor(R.styleable.PersianCalendarView_pcv_day_color, R.color.white)
        DateColor1 = typedArray.getColor(R.styleable.PersianCalendarView_pcv_date_color, R.color.white)
        MonthColor1 = typedArray.getColor(R.styleable.PersianCalendarView_pcv_month_color, R.color.white)
        backgroundSelect = if (typedArray.hasValue(R.styleable.PersianCalendarView_pcv_background_select))
            typedArray.getDrawable(R.styleable.PersianCalendarView_pcv_background_select)
        else
            resources.getDrawable(R.drawable.background_selected_day)
        typedArray.recycle()
        recycler_view.adapter = calAdapter

    }

    init {

        View.inflate(context, R.layout.custom_calender_layout, this)
        calAdapter = CalAdapter(context, this)
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recycler_view.layoutManager = linearLayoutManager
        linearLayoutManager.scrollToPosition(499)


    }

    fun getcurrentDayModel(position: Int): DayDateMonthYearModel {
        return calAdapter.getItem(position)
    }


    override fun setBackgroundColor(color: Int) {
        main_background.setBackgroundColor(color)
    }

    fun setCallBack(toCallBack: HorizontalCalendarListener) {
        this.toCallBack = toCallBack
        calAdapter.setCallback(toCallBack)
    }


}
