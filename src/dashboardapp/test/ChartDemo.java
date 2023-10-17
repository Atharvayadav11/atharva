/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChartDemo extends JFrame {
    // ... existing code ...

    // Static method for creating the chart
    public static JFreeChart createProfitChart() {
        // Create a dataset with your data
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Fetch data from your MySQL database and add it to the dataset
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb", "root", "ashokvaishali");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT date, profit FROM profitchart");

            while (resultSet.next()) {
                dataset.addValue(resultSet.getDouble("profit"), "Profit", resultSet.getString("date"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a chart based on the dataset
        JFreeChart chart = ChartFactory.createBarChart("Profit Chart", "Date", "Profit", dataset, PlotOrientation.VERTICAL, true, true, false);

        return chart;
    }
}
