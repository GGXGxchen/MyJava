package huffman_coding;

import java.util.*;

public class Frequency {
	public String charset;
	public char ch;				//ͳ�Ƶ��ַ�
	public double weight;		//ͳ���ַ���Ƶ�ʣ�������Ȩ��
	public String fileStr;
	Frequency[] fz = null; 
	
	public String toString(){	//����ĳ��Frequency���ַ�ֵ��Ȩ�ء�
		return "�ַ�" + ch + "\tƵ��" + weight;
	}
	

	public void frequency(String fileStr){//�����ַ�����ͳ�������ַ�����Ƶ��
		this.fileStr=fileStr;
		StringBuilder sb = new StringBuilder();//����Ը����ַ����������Ƶ����ʹ��StringBuider�����Ч��
		List<Frequency> list = new ArrayList<Frequency>();//����list���ϣ�����ΪFrequency
		here:
			for(int i=0;i<fileStr.length();i++){
				char ch = fileStr.charAt(i);
				for(Frequency f :list){
					if(f.ch==ch){
						f.weight++;
						continue here;//��������here���ֵ�ѭ��
					}
				}
				Frequency co = new Frequency();
				co.ch = ch;
				co.weight = 1;
				list.add(co);
				sb.append(ch);
			}
		
		int sum = fileStr.length();	//���ַ���
		for(Frequency c : list)
			c.weight = c.weight/sum;//Ȩ��=�ַ���/���ַ���
		fz=list.toArray(new Frequency[list.size()]);
		this.charset = sb.toString();
	}

//	public static void main(String[] args){ //����Ƶ�ʱ����Ƿ�������
//		Frequency fy=new Frequency();
//		fy.frequency("abcdef");
//		for(Frequency f :fy.fz){
//		System.out.print(f.weight);
//		}
//			
//	}

}
