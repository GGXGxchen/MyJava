package pages;

public class SeqList{ //˳����࣬����	
	private String[] element;	//�������飬�洢˳��������Ԫ��
	 int n;						//˳���Ԫ�ظ����������鳤�Ȳ�ͬ��ָ��ǰԪ�ظ���������������
	 
	public SeqList(int length){	//���췽������������Ϊlength�Ŀձ�
		this.element = new String[length];
		this.n =0;
	}
	
	public int size(){			   	 //˳���Ԫ�ظ���
		return this.n;
	}
	
	public String get(int x){        //����˳����x��Ԫ��
		if(x>=0&&x<this.n)			 //���Ԫ���Ƿ�����±�Լ��
			return this.element[x];
		return null;
	}
	
	public int insert(int i,String t){						   //��˳����iλ����t
		if(t==null)throw new NullPointerException("t == null");//��ֵ���
		if(i<0)i= 0;										   //�ݴ����뵽��һλ
		if(i>this.n) i = n;									   //�±����Ŀǰ���е�Ԫ���������뵽��β
		String[] obj = element;								   //obj��������element��ֵ��
		if(n==element.length){								   //�ж�˳����Ƿ����ˣ�����������
			element = new String[obj.length*2];
			for(int j=0;j<i;j++){							   //��������ֵ
				element[j] = obj[j];
			}
		}
		for(int j=n-1;j>=i;j--){							   //���ǲ��뵽�����м佫iλ���Ԫ�غ���
			element[j+1] = obj[j];
		}
		element[i] = t;
		n++;
		return i;											   //���ز����λ�ã�x����ţ�
	}
	
	public int insert(String x){							   //��ָ��λ��ʱĬ�ϲ���˳������
		return this.insert(n,x);	
	}
}

