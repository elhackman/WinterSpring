package com.ctq.test1;

public class requestEntry {
 
	private String Time;
	private String Method;
	private String Header;
	private String Body;
    
	public requestEntry(String Method,String Time,String Header,String Body) {
		this.Time = Time;
		this.Method = Method;
		this.Header = Header;
		this.Body = Body;		
 	}
   
	 public String getRtime() {
		 return Time;
	 }

	 public String getRmethod() {
		 return Method;
	 }
	 
	 public String getRheader() {
		 return Header;
	 }

	 public String getRbody() {
		 return Body;
	 }
   
  @Override
  public String toString() {
    return "requestEntry [time=" + Time + ", method=" + Method + ", header=" + Header + ", body=" + Body + "]";
  } 
}
