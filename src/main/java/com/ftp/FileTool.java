package com.ftp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
 public class FileTool { 
	  
	public static boolean uploadFile(String url,
			int port,
			String username, 
			String password, 
			String path, 
			String filename, 
			InputStream input 
	){
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("GBK");
		try {
			int reply;
			ftp.connect(url, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.makeDirectory(path);
			ftp.changeWorkingDirectory(path);
			ftp.storeFile(filename, input);	
			input.close();
			ftp.logout();
			success = true;
		 
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	
	public static void upLoadFromProduction(String url,
			int port,
			String username,
			String password, 
			String path, 
			String filename, 
			String orginfilename 
	   ) {
		try {
			FileInputStream in = new FileInputStream(new File(orginfilename));
			boolean flag = uploadFile(url, port, username, password, path,filename, in);
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
     
	
	 public static boolean downFile(String url, int port, String username,
	            String password, String remotePath, String fileName,
	            String localPath,String orginfilename) {
		 
			FTPClient ftp = new FTPClient();
    		ftp.setControlEncoding("GBK");
	        boolean result = false;
	        try {
	            int reply;
	         	ftp.connect(url, port);
	            ftp.login(username, password);
	            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	            reply = ftp.getReplyCode();
	            if (!FTPReply.isPositiveCompletion(reply)) {
	            	ftp.disconnect();
	                System.err.println("FTP server refused connection.");
	                return result;
	            }
	            ftp.changeWorkingDirectory(remotePath);
	            FTPFile[] fs = ftp.listFiles();
	            for (FTPFile ff : fs) {
	                if (ff.getName().equals(fileName)) {
	                    File localFile = new File(localPath + "/" + ff.getName());
	                    OutputStream is = new FileOutputStream(localFile);
	                    ftp.retrieveFile(ff.getName(), is);
	                    is.close();
	                }
	            }
	 
	            ftp.logout();
	            result = true;
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (ftp.isConnected()) {
	                try {
	                	ftp.disconnect();
	                } catch (IOException ioe) {
	                }
	            }
	        }
	        return result;
	    } 
	 
	 
	 
	 public static boolean sendFile(String url,
				int port,
				String username, 
				String password, 
				String path, 
				String filename, 
				String orginfilename 
		){
			boolean success = false;
			FTPClient ftp = new FTPClient();
			ftp.setControlEncoding("GBK");
			try {
				int reply;
				ftp.connect(url, port);
				ftp.login(username, password);
				reply = ftp.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftp.disconnect();
					return success;
				}
				ftp.setBufferSize(1024*20);
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftp.makeDirectory(path);
				ftp.changeWorkingDirectory(path);
				//ftp.storeFile(filename, input);	
				sendFile(ftp,orginfilename,0,filename);
			//	input.close();
				ftp.logout();
				success = true;
			 
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {
					}
				}
			}
			return success;
		}
	 
	 
	 
	 
		/**
		 * @param ftp
		 * @param orginfilename
		 * @param tofilename
		 * @throws IOException
		 */
	 public static void sendFile(FTPClient ftp,String orginfilename, String tofilename) throws IOException {
		 
			File file = new File(orginfilename);
			int n = -1;
			long size = file.length();
			long trans = 0;
			int bufferSize = ftp.getBufferSize();
			byte[] buffer = new byte[bufferSize];

			FileInputStream fileInputStream = new FileInputStream(file);
			OutputStream outputstream = ftp.storeFileStream(tofilename);
			
			while ((n = fileInputStream.read(buffer)) != -1) {
				outputstream.write(buffer);
				trans += n;
				TransListener listener = new TransListener();
				listener.update(trans, size);
			}
			fileInputStream.close();
			outputstream.flush();
			outputstream.close();
		}
	 
	 public static void sendFile(FTPClient ftp,String orginfilename,long seek, String tofilename) throws IOException {
		 
			File file = new File(orginfilename);
			int n = -1;
			long size = file.length();
			if(seek>=size)
			{
				System.out.println("");
				return;
			}
			long trans = 0;
			int bufferSize = ftp.getBufferSize();
			byte[] buffer = new byte[bufferSize];
			 //RandomAccessFile raf = new RandomAccessFile(file,"r");    
			FileInputStream raf = new FileInputStream(file);
			//fileInputStream.skip(seek);
			 
			OutputStream outputstream = ftp.storeFileStream(tofilename);
	 		
			while ((n = raf.read(buffer)) != -1) {
				outputstream.write(buffer,0,n);
				trans += n;
				TransListener listener = new TransListener();
				listener.update(trans, size);
				if(trans>800000&&seek==0)
				{
					raf.close();
					outputstream.flush();
					outputstream.close();
					return;
				}
			}
			raf.close();
			outputstream.flush();
			outputstream.close();
		}

	 
	 
	 public static boolean sendFile2(String url,
				int port,
				String username, 
				String password, 
				String path, 
				String filename, 
				String orginfilename, 
				long seek
		){
			boolean success = false;
			FTPClient ftp = new FTPClient();
			ftp.setControlEncoding("GBK");
			try {
				int reply;
				ftp.connect(url, port);
				ftp.login(username, password);
				reply = ftp.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftp.disconnect();
					return success;
				}
				ftp.setBufferSize(1024*20);
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftp.makeDirectory(path);
				ftp.changeWorkingDirectory(path);
				//ftp.storeFile(filename, input);	
				sendFile2(ftp,orginfilename,seek,filename);
			//	input.close();
				ftp.logout();
				success = true;
			 
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {
					}
				}
			}
			return success;
		}
	 
	 public static void sendFile2(FTPClient ftp,String orginfilename,long seek, String tofilename) throws IOException {
		 
			File file = new File(orginfilename);
			int n = -1;
			long size = file.length();
			if(seek>=size)
			{
				System.out.println("");
				return;
			}
			long trans = seek;
			int bufferSize = ftp.getBufferSize();
			byte[] buffer = new byte[bufferSize];
			// RandomAccessFile raf = new RandomAccessFile(file,"r");    
			 //raf.seek(seek);   
				
			FileInputStream raf = new FileInputStream(file);   
			
			raf.skip(seek);
			 
			OutputStream outputstream = ftp.appendFileStream(tofilename);
			ftp.setRestartOffset(seek); 
			while ((n = raf.read(buffer)) != -1) {
				outputstream.write(buffer,0,n);
				trans += n;
				TransListener listener = new TransListener();
				listener.update(trans, size);
			}
			raf.close();
			outputstream.flush();
			outputstream.close();
		}
	 
	public static void main(String[] args) {
		//downFile("127.0.0.1", 21, "one", "one", "ant", "ant-1.6.5.jar", "D:");
		//upLoadFromProduction("127.0.0.1", 21, "two", "two", "ant", "ant-1.6.5.jar", "D:/repository/ant/ant/1.6.5/ant-1.6.5.jar");
		
		
	//boolean flag =sendFile("127.0.0.1", 21, "two", "two", "ant", "ant-1.6.5.jar", "D:/repository/ant/ant/1.6.5/ant-1.6.5.jar");
 	//System.out.println(flag);

		
 //  boolean flag =sendFile2("127.0.0.1", 21, "two", "two", "ant", "ant-1.6.5.jar", "D:/repository/ant/ant/1.6.5/ant-1.6.5.jar",819200);
  // System.out.println(flag);
		
		File file = new File("D:/Serv_U/one/ant/ant-1.6.5.jar");
		System.out.println(file.length());
		file = new File("D:/Serv_U/two/ant/ant-1.6.5.jar");
		System.out.println(file.length());
		
	}
}