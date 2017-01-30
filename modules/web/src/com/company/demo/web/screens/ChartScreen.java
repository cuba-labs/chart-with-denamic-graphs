package com.company.demo.web.screens;

import com.haulmont.charts.gui.amcharts.model.BulletType;
import com.haulmont.charts.gui.amcharts.model.Graph;
import com.haulmont.charts.gui.amcharts.model.charts.XYChart;
import com.haulmont.charts.gui.amcharts.model.data.DataItem;
import com.haulmont.charts.gui.amcharts.model.data.DataProvider;
import com.haulmont.charts.gui.amcharts.model.data.ListDataProvider;
import com.haulmont.charts.gui.amcharts.model.data.MapDataItem;
import com.haulmont.charts.gui.components.charts.Chart;
import com.haulmont.cuba.gui.components.AbstractWindow;
import org.apache.commons.lang.math.RandomUtils;

import javax.inject.Inject;
import java.util.Map;

public class ChartScreen extends AbstractWindow {
    @Inject
    private Chart xyChart;

    @Override
    public void init(Map<String, Object> params) {
        ListDataProvider dataProvider = new ListDataProvider();
        xyChart.getConfiguration().setDataProvider(dataProvider);
    }

    private int graphId = 0;

    public void addGraph() {
        Graph graph = new Graph()
                .setId("graph" + graphId++)
                .setBalloonText("x:[[x]] y:[[y]]")
                .setBullet(BulletType.ROUND)
                .setFillAlphas(0.0)
                .setLineAlpha(0.8)
                .setXField(graphId + "x")
                .setYField(graphId + "y");

        XYChart configuration = (XYChart) xyChart.getConfiguration();
        configuration.addGraphs(graph);

        DataProvider dataProvider = configuration.getDataProvider();

        dataProvider.addItem(getPointPair(1, RandomUtils.nextDouble(), graphId));
        dataProvider.addItem(getPointPair(2, RandomUtils.nextDouble(), graphId));
        dataProvider.addItem(getPointPair(3, RandomUtils.nextDouble(), graphId));
        dataProvider.addItem(getPointPair(4, RandomUtils.nextDouble(), graphId));
        dataProvider.addItem(getPointPair(5, RandomUtils.nextDouble(), graphId));

        xyChart.repaint();
    }

    private DataItem getPointPair(double x, double y, int graphId) {
        MapDataItem dataItem = new MapDataItem();
        dataItem.add(graphId + "x", x);
        dataItem.add(graphId + "y", y);

        return dataItem;
    }
}