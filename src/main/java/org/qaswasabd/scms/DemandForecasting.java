package org.qaswasabd.scms;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import java.io.IOException;

public class DemandForecasting {
    private String productID;
    private int forecastedQuantity;
    private SimpleRegression regression;
    public DemandForecasting(String productID) throws IOException {
        this.productID = productID;
        this.regression = new SimpleRegression();
        double historicalData[] = Order.getHistoricalSalesData(this.productID);
        for (int i = 0; i < historicalData.length; i++) {
            this.regression.addData(i, historicalData[i]);
        }
    }
    public int generateDemandForecast() {
        double forecast = this.regression.predict(this.regression.getN());
        this.forecastedQuantity = (int) Math.round(forecast);
        return this.forecastedQuantity;
    }
}
