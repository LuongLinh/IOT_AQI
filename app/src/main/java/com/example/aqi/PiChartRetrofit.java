package com.example.aqi;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aqi.Api.ApiClient;
import com.example.aqi.Api.ApiInterface;
import com.example.aqi.Api.ApiNo2;
import com.example.aqi.pojo.Example;
import com.example.aqi.pojo.PiChart;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PiChartRetrofit extends AppCompatActivity {

    public ApiNo2 apiNo2;
    List<Example> exampleList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        apiNo2 = ApiClient.getClient().create(ApiNo2.class);
        getData();
    }

    private void getData() {
//        Call<PiChart> piChartCall = apiInterface.init();
//        piChartCall.enqueue(new Callback<PiChart>() {
//            @Override
//            public void onResponse(Call<PiChart> call, Response<PiChart> response) {
//                Log.d("CHART_RESPONSE", "" + response.body().getBarMonths().toString());
//                setData(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<PiChart> call, Throwable t) {
//
//            }
//        });

        Call<List<Example>> exampleCall = apiNo2.getChart();
        exampleCall.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                Log.d("CHART_RESPONSE", "" + response.body());
//                List<Example> examples = response.body();
                setData(response.body());
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Log.d("fail", "fail to connect" );
            }
        });
//        Call<JsonArray> loadAqi = apiInterface.getChart();
//        loadAqi.enqueue(new Callback<JsonArray>() {
//            @Override
//            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
//                Log.d("CHART_RESPONSE", "" + response.body().toString());
////                List<Example> examples = response.body();
//                setData(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<JsonArray> call, Throwable t) {
//
//            }
//        });
    }

    private void setData(List<Example> body) {
        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> aqi = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            BarEntry value = new BarEntry(body.get(i).getQuantity(), i); // Jan
            aqi.add(value);
            Log.d("quantity", "aqi: " + body.get(i).getQuantity());
        }

        BarDataSet aqiData = new BarDataSet(aqi, "Tốt");
        aqiData.setColor(Color.rgb(0, 228, 0));

        dataSets = new ArrayList<>();
        dataSets.add(aqiData);


        ArrayList<String> xAxis = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            String x = new String(body.get(i).getTime());
            xAxis.add(x);
            Log.d("time", "time: " + body.get(i).getTime());
        }

        BarChart chart = (BarChart) findViewById(R.id.barchart);
        BarData data = new BarData(xAxis, dataSets);

        chart.setData(data);
        chart.setDescription("My Chart");
        chart.animateXY(2000, 2000);
        chart.invalidate();
//
//
//            BarChart chart = (BarChart) findViewById(R.id.barchart);
//            BarData data = new BarData(xAxis, dataSets);
//
//            chart.setData(data);
//            chart.setDescription("My Chart");
//            chart.animateXY(2000, 2000);
//            chart.invalidate();
//        }

    }


//    private void setData( examples) {
//        ArrayList<BarDataSet> dataSets = null;
//
//        ArrayList<BarEntry> completed = new ArrayList<>();
//
//        for (int i = 0; i < examples.length(); i++) {
//            BarEntry value = new BarEntry(examples.g, i); // Jan
//            completed.add(value);
//        }
//
//        BarDataSet completeData = new BarDataSet(completed, "Tốt");
//        completeData.setColor(Color.rgb(0, 228, 0));
//
//        dataSets = new ArrayList<>();
//        dataSets.add(completeData);
//
//        ArrayList<String> xAxis = new ArrayList<>();
//
//        for (Example aqi: examples) {
//            Log.d("CHART_RESPONSE", "time: " + aqi.getTime());
//            xAxis.add(aqi.getTime());
//
//            BarChart chart = (BarChart) findViewById(R.id.barchart);
//            BarData data = new BarData(xAxis, dataSets);
//
//            chart.setData(data);
//            chart.setDescription("My Chart");
//            chart.animateXY(2000, 2000);
//            chart.invalidate();
//        }
//    }

//    private void setData(PiChart piChart) {
//        ArrayList<BarDataSet> dataSets = null;
//
//        ArrayList<BarEntry> completed = new ArrayList<>();
//
//
//        /**
//         *  Getting the value for Complete list
//         */
//
//        for (int i = 0; i < piChart.getBarCompleted().size(); i++) {
//            BarEntry value = new BarEntry(piChart.getBarCompleted().get(i), i); // Jan
//            completed.add(value);
//        }
//
//
//        /**
//         *  Add complete data into the bar chart
//         */
//
//        BarDataSet completeData = new BarDataSet(completed, "Tốt");
//        completeData.setColor(Color.rgb(0, 228, 0));
//
//
//        /**
//         *  Getting Pending data
//         */
//
//        ArrayList<BarEntry> pending = new ArrayList<>();
//        for (int i = 0; i < piChart.getBarCompleted().size(); i++) {
//            BarEntry value = new BarEntry(piChart.getBarPending().get(i), i); // Jan
//            pending.add(value);
//        }
//
//        /**
//         *  adding pending data into the bar chart
//         */
//
//        BarDataSet pendingdata = new BarDataSet(pending, "Bình thường");
//        pendingdata.setColor(Color.rgb(225, 155, 0));
//
//
//        /**
//         *  Getting rejected data from the api
//         */
//
//        ArrayList<BarEntry> rejected = new ArrayList<>();
//        for (int i = 0; i < piChart.getBarCompleted().size(); i++) {
//            BarEntry value = new BarEntry(piChart.getBarRejected().get(i), i); // Jan
//            rejected.add(value);
//        }
//
//
//        /**
//         *  set rejected data into
//         */
//
//        BarDataSet rejectedData = new BarDataSet(rejected, "Xấu");
//        pendingdata.setColor(Color.rgb(255, 255, 0));
//
//
//        dataSets = new ArrayList<>();
//        dataSets.add(completeData);
//        dataSets.add(pendingdata);
//        dataSets.add(rejectedData);
//
//
//        ArrayList<String> xAxis = new ArrayList<>();
//        for (String months : piChart.getBarMonths()) {
//            Log.d("CHART_RESPONSE", "month: " + months.toString());
//            xAxis.add(months);
//        }
//
//        BarChart chart = (BarChart) findViewById(R.id.barchart);
//
//        BarData data = new BarData(xAxis, dataSets);
//
//        chart.setData(data);
//        chart.setDescription("My Chart");
//        chart.animateXY(2000, 2000);
//        chart.invalidate();
//    }
}