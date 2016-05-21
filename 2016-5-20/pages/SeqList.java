package pages;

public class SeqList{ //顺序表类，用于	
	private String[] element;	//对象数组，存储顺序表的数据元素
	 int n;						//顺序表元素个数（与数组长度不同，指当前元素个数而不是容量）
	 
	public SeqList(int length){	//构造方法，创建容量为length的空表
		this.element = new String[length];
		this.n =0;
	}
	
	public int size(){			   	 //顺序表元素个数
		return this.n;
	}
	
	public String get(int x){        //返回顺序表第x个元素
		if(x>=0&&x<this.n)			 //检查元素是否符合下标约束
			return this.element[x];
		return null;
	}
	
	public int insert(int i,String t){						   //在顺序表第i位插入t
		if(t==null)throw new NullPointerException("t == null");//空值检查
		if(i<0)i= 0;										   //容错，插入到第一位
		if(i>this.n) i = n;									   //下标大于目前已有的元素数，插入到队尾
		String[] obj = element;								   //obj数组引用element赋值。
		if(n==element.length){								   //判断顺序表是否满了，若满则扩大
			element = new String[obj.length*2];
			for(int j=0;j<i;j++){							   //拷贝已有值
				element[j] = obj[j];
			}
		}
		for(int j=n-1;j>=i;j--){							   //若是插入到队列中间将i位后的元素后移
			element[j+1] = obj[j];
		}
		element[i] = t;
		n++;
		return i;											   //返回插入的位置（x的序号）
	}
	
	public int insert(String x){							   //无指定位置时默认插入顺序表最后
		return this.insert(n,x);	
	}
}

