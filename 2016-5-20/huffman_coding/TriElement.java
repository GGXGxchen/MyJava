package huffman_coding;
//����������������
public class TriElement {	
	char data;	              //������ַ�
	double weight;			  //�ַ�ռ��Ȩ�أ���Frequnecyָ��
	int parent,left,right;	  //ָ����ĸ��㡢���Һ��ӵ��±�
	
	public TriElement(char data,double weight,int parent,int left,int right){
		this.data=data;
		this.weight = weight;
		this.parent=parent;
		this.left=left;
		this.right=right;
	}
	
	public TriElement(double weight,int parent,int left,int right){ //������
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.weight = weight;
	}
	
	public boolean isLeft(){  //�ж��Ƿ�ΪҶ�ӽ�㣨���ӽ�㣩
		return (this.left==-1)&&(this.right==-1);
	}
	
	public TriElement(char data,double weight){
		this(data,weight,-1,-1,-1);
	}
	
	public String toString(){	//���ؽڵ�������ַ���
		return "Ȩֵ��"+weight+",left:"+left+",right"+right+",parent"+parent;
	}
}
