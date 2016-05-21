package huffman_coding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//��������
public class HuffmanTree {
	private Map<Character,String> hcodeMap = new HashMap<Character,String>();  //�����
	private String charset;
	private Frequency fy;
	public String fileStr;
	public float  ratio = -1;
	private TriElement[] huffman;
	boolean isCreateTree =false;						//�����ж��Ƿ��д�����������
	
	public HuffmanTree(Frequency fy){					//����Ƶ�ʱ���ȡ���в����������������
		this.fileStr = fy.fileStr;
		this.fy = fy;
		this.charset =fy.charset;
		int n = fy.fz.length;
		TriElement[] huff = new TriElement[n];				//����
		for(int i=0;i<n;i++){
			huff[i] = new TriElement(fy.fz[i].ch, fy.fz[i].weight);//���������������
		}

		this.huffman = new TriElement[2*n-1];

		for(int i = 0;i<n;i++){
			this.huffman[i] = huff[i];
		}

		for(int i = n;i<2*n-1;i++){
			double min1 = (double)Integer.MAX_VALUE,min2 = min1;
			int x1=-1,x2=-1;
			for(int j=0;j<i;j++){
				if(this.huffman[j].parent==-1){
					if(this.huffman[j].weight<min1)
					{
						min2 = min1;
						x2 = x1;
						min1 = this.huffman[j].weight;
						x1 = j;
//						System.out.println(x1+";"+min2);
					}else 
						if(this.huffman[j].weight<min2){
								min2 = this.huffman[j].weight;
								x2 = j;
						}
				}
			}
				this.huffman[x1].parent = i;
				this.huffman[x2].parent = i;
				this.huffman[i] = new TriElement(min1+min2,-1,x1,x2);
				isCreateTree = true;
			}
		
	}
	
	public void hCode(){  //�����ַ�����
		if(!isCreateTree)
			new HuffmanTree(this.fy);
		
		for(int i =0;i<this.charset.length();i++){
			int n = fy.fz.length , currentN = i;
			char hufcode[] = new char[n];
			int child = i,parent = huffman[child].parent;
			for(i=n-1;parent!=-1;i--){
				hufcode[i] = (huffman[parent].left==child)?'0':'1';
				child = parent;
				parent = huffman[child].parent;
			}
			this.hcodeMap.put(this.charset.charAt(currentN),new String(hufcode,i+1,n-1-i));
			i = currentN;
		}
	}
	
	public void getRatio(){
		System.out.println("ѹ����:"+ratio);  //ѹ����
	}
	
		public void getFy(){
		if(this.fy==null)
			new Frequency().frequency(fileStr);
		System.out.println("\n�ַ�ʹ��Ƶ��:");
		for(Frequency c : fy.fz)	//�����ַ�ʹ��Ƶ�ʱ�
			System.out.println("�ַ�: "+ c.ch+"ʹ��Ƶ��: "+ c.weight);
	}
		
	public void getHcodeMap(){//�鿴�����
		if(this.hcodeMap.size()==0)
			this.hCode();
		System.out.println("Huffman���룺");
		Iterator<Character> it = hcodeMap.keySet().iterator(); //���������
		while(it.hasNext()){
			Character c = it.next();
			System.out.println("�ַ�: "+ c+"����: "+ hcodeMap.get(c));
		}
	}	
	
	public void encode(String inFilePath){  ////ʵ�������ַ����ı���
		if(this.hcodeMap.size()==0)
			this.hCode();
		StringBuilder sb = new StringBuilder();
		for(int i =0;i<this.fileStr.length();i++){
			sb.append(this.hcodeMap.get(this.fileStr.charAt(i)));
		}
		byte compressed[] = sb.toString().getBytes();
		ratio = (float)(fileStr.length()*8)/(float)(compressed.length);
		FileIO.saveFile(compressed,inFilePath);
	}
	
	public void decode(String inFilePath,String outFilePath){  ////ʵ�������ַ���������
		if(!this.isCreateTree)
			throw new RuntimeException("���ȴ���Huffman��");
		
		String compressed = FileIO.readFile(inFilePath);
		String text = "";
		int node = this.huffman.length-1;
		for(int i=0;i<compressed.length();i++){
			if(compressed.charAt(i)=='0')
				node = huffman[node].left;
			else
				node = huffman[node].right;
			if(huffman[node].isLeft()){
				text+=this.charset.charAt(node);
				node = this.huffman.length-1;
			}
		}
		FileIO.saveFile(text.getBytes(), outFilePath);
	}
	
}
