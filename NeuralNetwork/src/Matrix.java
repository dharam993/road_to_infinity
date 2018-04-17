import java.lang.Math;

class Matrix {
	// initialize variables
	int rows = 0;
	int cols = 0;
	double[][] data;

	// return empty matrix
	public Matrix(int rows, int cols) {

		this.rows = rows;
		this.cols = cols;
		this.data = new double[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.data[i][j] = 0;
			}
		}
	}

	// subtract obj
	public void subtract(Object obj) {

		if (obj instanceof Matrix) {
			Matrix b = (Matrix) obj;
			if (this.rows != b.rows || this.cols != b.cols) {
				throw new RuntimeException("sorry cannot subtract matrix");
			}
			for (int i = 0; i < this.rows; i++) {
				for (int j = 0; j < this.cols; j++) {
					this.data[i][j] -= b.data[i][j];
				}
			}
		} else {
			for (int i = 0; i < this.rows; i++) {
				for (int j = 0; j < this.cols; j++) {
					this.data[i][j] -= (double) obj;
				}
			}
		}
	}

	// subtract obj
	public static Matrix subtract(Matrix a, Matrix b) {
		Matrix result = new Matrix(a.rows, a.cols);
		if (a.rows != b.rows || a.cols != b.cols) {
			throw new RuntimeException("sorry cannot subtract matrix");
		}
		for (int i = 0; i < a.rows; i++) {
			for (int j = 0; j < a.cols; j++) {
				result.data[i][j] -= a.data[i][j] - b.data[i][j];
			}
		}

		return result;
	}

	// add obj
	public void add(Object obj) {

		if (obj instanceof Matrix) {
			Matrix b = (Matrix) obj;
			if (this.rows != b.rows || this.cols != b.cols) {
				throw new RuntimeException("sorry cannot add matrix");
			}
			for (int i = 0; i < this.rows; i++) {
				for (int j = 0; j < this.cols; j++) {
					this.data[i][j] += b.data[i][j];
				}
			}
		} else {
			for (int i = 0; i < this.rows; i++) {
				for (int j = 0; j < this.cols; j++) {
					this.data[i][j] += (double) obj;
				}
			}
		}
	}

	// multiply obj
	public Matrix mul(Object obj) {

		if (obj instanceof Matrix) {
			Matrix b = (Matrix) obj;
			for (int i = 0; i < this.rows; i++) {
				for (int j = 0; j < this.cols; j++) {
					this.data[i][j] += this.data[i][j] * b.data[i][j];
				}
			}
		} else {
			for (int i = 0; i < this.rows; i++) {
				for (int j = 0; j < this.cols; j++) {
					this.data[i][j] *= (double) obj;
				}
			}
		}

		return this;
	}

	// multiply obj
	public static Matrix mul(Matrix a, Matrix b) {

		Matrix result = new Matrix(a.rows, b.cols);

		for (int i = 0; i < result.rows; i++) {
			for (int j = 0; j < result.cols; j++) {
				double sum = 0;
				for (int k = 0; k < a.cols; k++) {
					sum += a.data[i][k] * b.data[k][j];
				}
				result.data[i][j] = sum;
			}
		}

		return result;
	}

	// transpose matrix
	public Matrix transpose() {
		Matrix transpose = new Matrix(this.cols, this.rows);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				transpose.data[j][i] = this.data[i][j];
			}
		}

		return transpose;
	}

	// randomize matrix
	public void randomize() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				this.data[i][j] = (Math.random() * 2 - 1);
			}
		}
	}

	public static Matrix fromArray(double[] arr) {
		Matrix matrix = new Matrix(arr.length, 1);
		for (int i = 0; i < arr.length; i++) {
			matrix.data[i][0] = arr[i];
		}
		return matrix;
	}

	// change matrix to array
	public static double[] toArray(Matrix matrix) {
		int arr_index = 0;
		double[] arr = new double[matrix.rows * matrix.cols];
		for (int i = 0; i < matrix.rows; i++) {
			for (int j = 0; j < matrix.cols; j++) {
				arr[arr_index] = matrix.data[i][j];
			}
		}
		return arr;
	}
}