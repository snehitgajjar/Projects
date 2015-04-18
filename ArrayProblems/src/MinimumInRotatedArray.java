
public class MinimumInRotatedArray {
	
	    public int findMin(int[] list) {
	        int pivotIndex=0,front=0,end=list.length-1,pivot=0;
	        
	        do
	        {
	        
	            if((end+1-front)==1)
	            {
	                return list[front];
	            }
	            else if(end+1-front==2)
	            {
	                if(list[front]>list[front+1])
	                {
	                    return list[front+1];
	                }
	                else
	                {
	                    return list[front];
	                }
	            }
	          pivotIndex=(end+front)/2;
	            pivot=list[pivotIndex];
	         
	    
	            
	            if(pivot<list[pivotIndex-1])
	            {
	                return pivot;
	            }
	            
	          
	            
	            
	            
	            if(list[front]<pivot&& pivot<list[end])
	            {
	                end=pivotIndex;
	              
	            }
	           
	            else if(list[end]<list[front]&& list[front]<pivot)
	            {
	                
	                front=pivotIndex;
	                
	            }
	            else if(pivot<list[front]&&list[front]<list[end])
	            {
	                end=pivotIndex;
	               
	            }
	            else if(pivot<list[end]&&list[end]<list[front])
	            {
	                end=pivotIndex;
	                
	              
	            }
	            
	            
	            
	            
	            
	        }while(true);
	    

	    }
	}


