package lecho.lib.hellocharts.formatter;

import lecho.lib.hellocharts.model.SliceValue;

public interface PieChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SliceValue value);

    public int formatChartValueDate(char[] formattedValue, SliceValue value);

    public int formatChartValueRemark(char[] formattedValue, SliceValue value);
}
