package compress;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.*;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.*;
import org.apache.commons.compress.utils.IOUtils;

public class common {

	public static void doCompression(String zip, String path) throws Exception {
		ZipArchiveOutputStream zos = new ZipArchiveOutputStream(
				new FileOutputStream(zip));
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

	public static void doTar(String tar, String path)
			throws Exception {
		TarArchiveOutputStream tos =new TarArchiveOutputStream(new FileOutputStream(tar),"GBK");
		File of = new File(path);
		for (File off : of.listFiles()) {
			FileInputStream fis = new FileInputStream(off);
			TarArchiveEntry tae = new TarArchiveEntry(off.getName());
			tae.setSize(off.length());
			tos.putArchiveEntry(tae);
			IOUtils.copy(fis, tos);
			fis.close();
			tos.closeArchiveEntry();
		}
		tos.finish();
		tos.close();
	}

	public static void doDecompressionTar(String tar, String path)
			throws Exception {
		TarArchiveInputStream tais =new TarArchiveInputStream(new FileInputStream(tar));
		TarArchiveEntry tae=null;
		while ((tae=tais.getNextTarEntry())!=null) {
			FileOutputStream fos = new FileOutputStream(path+File.separator+tae.getName());
			IOUtils.copy(tais, fos);
			fos.close();
		}
		tais.close();
	}
	public static void doCompressionGZip(String zip, String path)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(zip);
		GZIPOutputStream gzos = new GZIPOutputStream(fos);
		FileInputStream fis = new FileInputStream(path);
		IOUtils.copy(fis, gzos);
		fis.close();
		gzos.close();
	}
	public static void doUnGZip(String gzip, String tar)
			throws IOException {
		FileInputStream fis = new FileInputStream(gzip);
		GZIPInputStream gzis = new GZIPInputStream(fis);
		FileOutputStream fos = new FileOutputStream(tar);
		IOUtils.copy(gzis,fos);
		fos.close();
		gzis.close();
	}

}