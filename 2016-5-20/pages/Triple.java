package pages;

public class Triple implements Comparable<Triple>{ 			//稀疏矩阵非零元素三元组类，用来表示道路（即图各边）
	public int row,column,value;							//行号列号，元素值（分别代表道路各始终点以及长度）
	
	public Triple(int row,int column,int value){			//构造方法
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

