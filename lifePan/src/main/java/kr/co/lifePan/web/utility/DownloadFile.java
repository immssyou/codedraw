package kr.co.lifePan.web.utility;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;

public class DownloadFile {

	private File file;
	private String fileName;
	private String fileMime;
	private boolean deleteAfterDownload;

	public boolean isDeleteAfterDownload() {
		return deleteAfterDownload;
	}

	public void setDeleteAfterDownload(boolean deleteAfterDownload) {
		this.deleteAfterDownload = deleteAfterDownload;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileMime() {
		if (fileMime == null || "".equals(fileMime.trim())) {
			fileMime = "application/octet-stream";
		}
		return fileMime;
	}

	public void setFileMime(String fileMime) {
		this.fileMime = fileMime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEncodedFileName(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		String encodedfileName = fileName;
		try {
			if (userAgent != null && !"".equals(userAgent.trim()) && userAgent.toLowerCase().indexOf("msie") != -1) {
				encodedfileName = URLEncoder.encode(fileName, "UTF-8");
				encodedfileName = encodedfileName.replaceAll("\\+", " ");
				encodedfileName = encodedfileName.replaceAll("\\%2E", ".");
			} else {
				encodedfileName = new String(fileName.getBytes("UTF-8"), "8859_1");
			}
			return encodedfileName;
		} catch (UnsupportedEncodingException ex) {
			return fileName;
		}
	}

	public long getFileSize() {
		return file.length();
	}

	public void dispose() {
		if (isDeleteAfterDownload()) {
			file.delete();
		}
	}
}
