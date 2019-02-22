import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;

public class PostFile {
    
	public static void postfiles(File file) throws HttpException, IOException
	{
		
		File input = new File(file.toString());
        
		String strURL = "http://localhost:2222/senddata";

        PostMethod post = new PostMethod(strURL);

        post.setRequestEntity(new InputStreamRequestEntity(
                new FileInputStream(input), input.length()));
        

         post.setRequestHeader(
                 "Content-type", "text/plain; charset=UTF-8");        
        HttpClient httpclient = new HttpClient();
        
        // Execute request
        try {            
            int result = httpclient.executeMethod(post);            
            // Display status code
            Date date=new Date();
            
            System.out.println("Response status code: " + result+","+date);            
            // Display response
            //System.out.println("Response body: ");
            //System.out.println(post.getResponseBodyAsString());
            
        } finally {
         
            post.releaseConnection();
        }
	}
    public static void main(String[] args) throws Exception {
        
        // Get file to be posted
        String dirpath="localpath";
        
        File[] files=new File(dirpath).listFiles();
        for(File file:files)
        {
        	if(file.isFile())
        	{
        		Thread.sleep(30000);
        		System.out.println(file.toString());
        		postfiles(file);
        	}
        }
        
    }
}