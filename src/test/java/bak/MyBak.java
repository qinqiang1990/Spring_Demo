package bak;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class MyBak {

	@Test
	public void bak() throws Exception {
		// TODO Auto-generated method stub
		// String command = "cmd  /c  dir   c:\\ ";
		StringBuffer sb = new StringBuffer();
		sb.append("cmd  /c ");
		sb.append("C:\\mysqldump");
		sb.append(" -uroot ");
		sb.append(" -pqin1990 ");
		sb.append(" shiro ");
		String command = sb.toString();
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				process.getInputStream(), "gbk"));
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}

	@Test
	public void bakSaveFile() throws Exception {
		// TODO Auto-generated method stub
		// String command = "cmd  /c  dir   c:\\ ";
		StringBuffer sb = new StringBuffer();
		sb.append("cmd  /c ");
		sb.append("C:\\mysqldump");
		sb.append(" -uroot ");
		sb.append(" -pqin1990 ");
		sb.append(" shiro ");
		String command = sb.toString();
		Process process = Runtime.getRuntime().exec(command);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				process.getInputStream(), "gbk"));

		BufferedWriter bw = new BufferedWriter(new FileWriter(this.getClass()
				.getResource("/bakfile/a.txt").getPath()));

		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			bw.write(line);
			bw.newLine();
		}
		br.close();
		bw.close();
	}

	@Test
	public void MyBak() throws Exception {
		// TODO Auto-generated method stub
		String command = "cmd  /c  C:\\mysql -uroot -pqin1990 shiro";
		Process process = Runtime.getRuntime().exec(command);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				process.getOutputStream()));

		BufferedReader br = new BufferedReader(new FileReader(this.getClass()
				.getResource("/bakfile/a.txt").getPath()));

		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			bw.write(line);
			bw.newLine();
		}
		bw.close();
		br.close();

	}

	@Test
	public void doZip() throws Exception {
		// TODO Auto-generated method stub
		String path = "d:\\zip\\1";
		String zip = "d:\\zip\\1.zip";
		compress.common.doCompression(zip, path);

	}


	@Test
	public void doTar() throws Exception {
		// TODO Auto-generated method stub
		String path = "d:\\zip\\1";
		String tar = "d:\\zip\\1.tar";
		compress.common.doTar(tar, path);

	}	
	@Test
	public void doComZip() throws Exception {
		// TODO Auto-generated method stub
		String zip = "d:\\zip\\1.tar.gz";
		String tar = "d:\\zip\\1.tar";
		compress.common.doCompressionGZip(zip, tar);
		
	}	
	@Test
	public void doUnZip() throws Exception {
		// TODO Auto-generated method stub
		String gzip = "d:\\zip\\1.tar.gz";
		String tar = "d:\\zip\\1.tar";
		compress.common.doUnGZip(gzip, tar);
		
	}	
	
	@Test
	public void doDecomTar() throws Exception {
		// TODO Auto-generated method stub
		String path = "d:\\zip\\2";
		String tar = "d:\\zip\\1.tar";
		compress.common.doDecompressionTar(tar, path);

	}

	
}
