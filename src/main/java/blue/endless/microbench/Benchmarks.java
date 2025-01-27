package blue.endless.microbench;

import java.util.function.DoubleSupplier;

public class Benchmarks {
	
	public static double test() {
		return Math.random() * 100;
	}
	
	public static double test2() {
		return Math.sqrt(Math.sin(Math.random() * 100) + Math.cos(Math.random() * 100));
	}
	
	
	public static void main(String... args) {
		long warmups = 1000;
		long tests = 10_000;
		
		SampledStats stats = run(Benchmarks::test, warmups, tests);
		SampledStats stats2 = run(Benchmarks::test2, warmups, tests);
		
		System.out.println("test : "+stats);
		System.out.println("test2: "+stats2);
	}
	
	
	public static SampledStats run(DoubleSupplier r, long warmupCycles, long testCycles) {
		SampledStats stats = new SampledStats();
		
		long start = System.nanoTime();
		
		double total = 0.0;
		//Warmup
		for(long i=0; i<warmupCycles; i++) {
			total += test();
			
			long now = System.nanoTime();
			stats.takeSample(now - start);
			start = now;
		}
		
		stats.reset();
		start = System.nanoTime();
		
		//Test
		for(long i=0; i<testCycles; i++) {
			total += test();
			
			long now = System.nanoTime();
			stats.takeSample(now - start);
			start = now;
		}
		
		return stats;
	}
}
