package outputManager;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import taxpayer.Taxpayer;

public class ChartCreator {
	public void createPieChart(Taxpayer taxp){
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		pieDataset.setValue("Entertainment; " + taxp.
				getCategoryReceiptAmountPaid("Entertainment"),
				taxp.getCategoryReceiptAmountPaid("Entertainment"));
		pieDataset.setValue("Basic; " + taxp.
				getCategoryReceiptAmountPaid("Basic"),
				taxp.getCategoryReceiptAmountPaid("Basic"));
		pieDataset.setValue("Travel; " + taxp.
				getCategoryReceiptAmountPaid("Travel"),
				taxp.getCategoryReceiptAmountPaid("Travel"));
		pieDataset.setValue("Health; " + taxp.
				getCategoryReceiptAmountPaid("Health"),
				taxp.getCategoryReceiptAmountPaid("Health"));
		pieDataset.setValue("Other; " + taxp.
				getCategoryReceiptAmountPaid("Other"), 
				taxp.getCategoryReceiptAmountPaid("Other"));
		JFreeChart chart = ChartFactory.createPieChart("Pie Chart",
				pieDataset, true, true, true);
		ChartFrame frame = new ChartFrame("Pie Chart", chart);
		frame.setVisible(true);
		frame.setSize(450, 500);
	}
	
	public void createBarChart(Taxpayer taxp){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(taxp.getBasicTax(), "", "Basic Tax");
		dataset.setValue(taxp.getTaxIncrease(), "", "Tax increase");
		dataset.setValue(taxp.getTotalTax(), "", "Total tax");
		JFreeChart chart = ChartFactory.createBarChart("", "",
				"Tax analysis in $", dataset);
		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.black);
		ChartFrame frame = new ChartFrame("", chart);
		frame.setVisible(true);
		frame.setSize(450,500);
	}
}
