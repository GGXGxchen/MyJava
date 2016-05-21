package pages;

public class Matrix {	//������
	private int rows,columns;   //����  �� ����
	private int[][] element;	//�����Ӧ����
	
	public Matrix(int m,int n){//���췽����������������������������Ӧ������Ԫ�ء�
		this.element = new int[m][n];
		this.rows = m;
		this.columns = n;
	}
	
	public Matrix(int n){//������������ʱ�Ĺ��췽����
		this(n,n);
	}
	
	public int get(int i,int j){//���ؾ�����i��j�е�ֵ
		return this.element[i][j];
	}
	
	public void set(int i,int j,int value){//��value���������i��j�е�Ԫ�ء�
		this.element[i][j] = value;
	}
}

