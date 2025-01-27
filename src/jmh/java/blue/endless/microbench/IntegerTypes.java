package blue.endless.microbench;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;


@Fork(value = 1, warmups = 1 )
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class IntegerTypes {
	
	@Benchmark
	public float[] floatVecs() {
		float[] vec = { 1.0f, 0.3f, 0.5f };
		float[] mat =
			{
				1.0f, 0.0f, 0.0f,
				0.0f, 1.0f, 0.0f,
				0.0f, 0.0f, 1.0f
			};
		
		float[] result = {
			mat[0] * vec[0] + mat[1] * vec[1] + mat[2] * vec[2],
			mat[3] * vec[0] + mat[4] * vec[1] + mat[5] * vec[2],
			mat[6] * vec[0] + mat[7] * vec[1] + mat[8] * vec[2]
		};
		
		return result;
	}
	
	@Benchmark
	public double[] doubleVecs() {
		double[] vec = { 1.0, 0.3, 0.5 };
		double[] mat =
			{
				1.0, 0.0, 0.0,
				0.0, 1.0, 0.0,
				0.0, 0.0, 1.0
			};
		
		double[] result = {
			mat[0] * vec[0] + mat[1] * vec[1] + mat[2] * vec[2],
			mat[3] * vec[0] + mat[4] * vec[1] + mat[5] * vec[2],
			mat[6] * vec[0] + mat[7] * vec[1] + mat[8] * vec[2]
		};
		
		return result;
	}
}
