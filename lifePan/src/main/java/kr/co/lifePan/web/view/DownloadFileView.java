package kr.co.lifePan.web.view;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import kr.co.lifePan.web.utility.DownloadFile;

public class DownloadFileView extends AbstractView {
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DownloadFile downloadFile = (DownloadFile) model.get("downloadFile");

		FileInputStream fin = null;
		OutputStream fout = null;
		try {
			String fileName = downloadFile.getEncodedFileName(request);
			response.reset();
			response.setContentType(downloadFile.getFileMime()
					+ "; charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Length",
					"" + downloadFile.getFileSize());
			// response.setContentLength((int) downloadFile.getFileSize());
			// response.setHeader("Connection", "close");
			fin = new FileInputStream(downloadFile.getFile());
			byte[] readBuffer = new byte[8192]; // 8K 버퍼
			fout = response.getOutputStream();
			int numOfBytes = -1;
			while ((numOfBytes = fin.read(readBuffer)) != -1) {
				fout.write(readBuffer, 0, numOfBytes);
				fout.flush();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (fin != null) {
					fin.close();
				}
			} catch (Exception ex) {
			} finally {
				fin = null;
			}
			try {
				if (fout != null) {
					fout.close();
				}
			} catch (Exception ex) {
			} finally {
				fout = null;
			}
			downloadFile.dispose();
		}
	}
}
