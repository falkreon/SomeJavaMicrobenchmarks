package blue.endless.microbench;

import java.text.NumberFormat;

public class SampledStats {
	NumberFormat fmt = NumberFormat.getCompactNumberInstance();
	
	private double currentTotal = 0.0;
	private long sampleCount = 0L;
	
	private boolean firstSample = true;
	private double min = 0.0;
	private double max = 0.0;
	
	public SampledStats() {}
	
	public void takeSample(double sampleValue) {
		// Unlikely, but let's check and do the best we can in this case.
		if (sampleCount < Long.MAX_VALUE) {
			sampleCount++;
			currentTotal += sampleValue;
		}
		
		if (firstSample) {
			min = sampleValue;
			max = sampleValue;
			firstSample = false;
		} else {
			if (sampleValue < min) min = sampleValue;
			if (sampleValue > max) max = sampleValue;
		}
	}
	
	public void reset() {
		currentTotal = 0.0;
		sampleCount = 0L;
		min = 0.0;
		max = 0.0;
		firstSample = true;
	}
	
	public double mean() {
		return currentTotal / sampleCount;
	}
	
	public double estimatedError() {
		double mean = mean();
		return Math.max(Math.abs(max - mean), Math.abs(mean - min));
	}
	
	public String toString() {
		double mean = mean();
		double percentError = Math.max(Math.abs(max - mean), Math.abs(mean - min)) / mean;
		
		return "mean avg: "+fmt.format(mean())+" +/- "+fmt.format(percentError)+"% (min: "+min+", max: "+max+")";
	}
}
