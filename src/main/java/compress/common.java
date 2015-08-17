package compress;

import java.io.*;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.*;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.*;
import org.apache.commons.compress.utils.IOUtils;

public class common {

	public static int BUF = 1024;

	public static void doCompression(String zip, String path) throws Exception {
		ZipArchiveOutputStream zos = (ZipArchiveOutputStream) new ArchiveStreamFactory()
				.createArchiveOutputStream("zip", new FileOutputStream(zip));
		// or new ZipArchiveOutputStream(new FileOutputStream(path))
		// or new TarArchiveOutputStream(new FileOutputStream(path))
		zos.setEncoding("GBK");
		File of = new File(path);
		if (of.isFile())
			return;
		for (File off : of.listFiles()) {
			ZipArchiveEntry ze = new ZipArchiveEntry(off.getName());
			zos.putArchiveEntry(ze);
			// folder
			if (ze.isDirectory()) {
				zos.closeArchiveEntry();
				continue;
			}

			// file
			zos.putArchiveEntry(ze);
			FileInputStream fis = new FileInputStream(off);
			IOUtils.copy(fis, zos);
			fis.close();
			zos.closeArchiveEntry();
		}
		zos.finish();
		zos.close();
	}

	public static void doDecompression(String zip, String path)
			throws Exception {
		ZipFile file = new ZipFile(zip, "GBK");
		Enumeration<ZipArchiveEntry> en = file.getEntries();
		ZipArchiveEntry ze;
		while (en.hasMoreElements()) {
			ze = en.nextElement();
			File f = new File(path, ze.getName());
			// 创建完整路径
			if (ze.isDirectory()) {
				f.mkdirs();
				continue;
			}
			f.getParentFile().mkdirs();
			InputStream is = file.getInputStream(ze);
			OutputStream os = new FileOutputStream(f);
			IOUtils.copy(is, os);
			is.close();
			os.close();
		}
		file.close();
	}

	// 这是更一般的解压处理
	public static void doTar(String tar, String path) throws IOException {
		TarArchiveInputStream tis = new TarArchiveInputStream(
				new FileInputStream(tar));
		TarArchiveEntry te = null;
		while ((te = tis.getNextTarEntry()) != null) {
			File target = new File(path, te.getName());
			if (te.isDirectory()) {
				target.mkdirs();
				continue;
			} else
				target.getParentFile().mkdirs();

			FileOutputStream fos = new FileOutputStream(target); // 将当前entry写入文件
			byte[] buf = new byte[BUF];
			int len;
			while ((len = tis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.close();
		}
		tis.close();
	}
}