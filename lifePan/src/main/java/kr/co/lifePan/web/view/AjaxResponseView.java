package kr.co.lifePan.web.view;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractView;

import kr.co.lifePan.web.domain.AjaxResponse;

public class AjaxResponseView extends AbstractView {
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxResponse ajaxReponse = (AjaxResponse) model.get("ajaxReponse");

		PrintWriter out = null;
		try {
			response.resetBuffer();
			response.reset();
			response.setStatus(ajaxReponse.getStatus().value());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			out = new PrintWriter(new BufferedWriter(response.getWriter(), 12288));
			out.print(objectMapper.writeValueAsString(ajaxReponse));
			out.flush();
		} catch (java.io.IOException ioe) {
			logger.fatal(ioe);
			throw ioe;
		} catch (Exception ex) {
			logger.fatal(ex);
			throw new ServletException(ex);
		} finally {
			try {
				if (out != null) {
					out.flush();
				}
			} catch (Exception ex) {
			} finally {
				out = null;
			}
		}
	}
}
