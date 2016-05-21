package pages;

public class Triple implements Comparable<Triple>{ 			//ϡ��������Ԫ����Ԫ���࣬������ʾ��·����ͼ���ߣ�
	public int row,column,value;							//�к��кţ�Ԫ��ֵ���ֱ�����·��ʼ�յ��Լ����ȣ�
	
	public Triple(int row,int column,int value){			//���췽��
		this.row = row;
		this.column = column;
		this.value = value;
	}
	
	public String toString(){
		return "("+row+","+column+","+value+")";
	}
	
	public boolean equals(Triple tri){
		return this.row==tri.row&&this.column==tri.column; 
	}
	
	public int compareTo(Triple tri){
		if(this.row==tri.row&&this.column== tri.column)
		return 0;
		return (this.row<tri.row||this.row==tri.row&&this.column<tri.column)?-1:1;
	}

}

