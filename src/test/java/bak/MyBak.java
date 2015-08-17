package bak;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class MyBak {

	@Test
	public void bak() throws Exception {
		// TODO Auto-generated method stub
		//cmd  /c  mysqldump -uroot -p111111 shiro > D:\\e.sql
		//String command = "cmd  /c  dir   c:\\ ";
		String command = "cmd  /c  mysqldump ";
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
		String command = "cmd  /c  dir   c:\\ ";
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
		String command = "cmd  /c  dir   c:\\ ";
		Process process = Runtime.getRuntime().exec(command);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				process.getInputStream(), "gbk"));
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}
}
