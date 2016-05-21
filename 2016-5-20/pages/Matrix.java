package pages;

public class Matrix {	//矩阵类
	private int rows,columns;   //行数  ， 列数
	private int[][] element;	//矩阵对应数组
	
	public Matrix(int m,int n){//构造方法，传入行数和列数，并创建对应的数组元素。
		this.element = new int[m][n];
		this.rows = m;
		this.columns = n;
	}
	
	public Matrix(int n){//单个参数传入时的构造方法。
		this(n,n);
	}
	
	public int get(int i,int j){//返回矩阵中i行j列的值
		return this.element[i][j];
	}
	
	public void set(int i,int j,int value){//将value传入矩阵中i行j列的元素。
		this.element[i][j] = value;
	}
}

