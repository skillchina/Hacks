package club.iandroid.hack50.hellochart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import club.iandroid.hack50.R;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class HelloChartPileActivity extends Activity {

    PieChartView chartView;
    private PieChartData data;
    Activity activity;
    int numValues = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_chart_pile);
        activity = this;
        chartView = (PieChartView) findViewById(R.id.chartView);
        chartView.setCircleFillRatio(0.6f);

        chartView.setOnValueTouchListener(new ValueTouchListener());

        initData();
    }

    private void initData(){
        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.nextColor());

            sliceValue.setLabel("金额："+i);
            sliceValue.setOutSideDate("日期:"+i);
            sliceValue.setOutSideRemark("备注："+i);
            values.add(sliceValue);
        }

        data = new PieChartData(values);
        data.setSlicesSpacing(0);
        data.setHasLabels(true);
        data.setHasLabelsOnlyForSelected(false);
        data.setHasLabelsOutside(true);
        data.setHasCenterCircle(true);
        data.setCenterCircleScale(0.3f);
//        data.setValueLabelBackgroundEnabled(false);

        chartView.setPieChartData(data);

    }

    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            Toast.makeText(activity, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
