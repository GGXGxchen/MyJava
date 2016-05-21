package huffman_coding;
//二叉树的三叉链表
public class TriElement {	
	char data;	              //输入的字符
	double weight;			  //字符占的权重，由Frequnecy指定
	int parent,left,right;	  //指定父母结点、左右孩子的下标
	
	public TriElement(char data,double weight,int parent,int left,int right){
		this.data=data;
		this.weight = weight;
		this.parent=parent;
		this.left=left;
		this.right=right;
	}
	
	public TriElement(double weight,int parent,int left,int right){ //构造结点
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.weight = weight;
	}
	
	public boolean isLeft(){  //判断是否为叶子结点（无子结点）
		return (this.left==-1)&&(this.right==-1);
	}
	
	public TriElement(char data,double weight){
		this(data,weight,-1,-1,-1);
	}
	
	public String toString(){	//返回节点的描述字符串
		return "权值："+weight+",left:"+left+",right"+right+",parent"+parent;
	}
}
