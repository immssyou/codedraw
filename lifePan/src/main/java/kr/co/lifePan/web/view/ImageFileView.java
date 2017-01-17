package kr.co.lifePan.web.view;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class ImageFileView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try (ByteArrayOutputStream bas = (ByteArrayOutputStream) model.get("stream"); OutputStream os = response.getOutputStream()) {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Length", "" + bas.size());

			os.write(bas.toByteArray());
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
