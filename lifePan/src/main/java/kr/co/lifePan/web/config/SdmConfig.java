package kr.co.lifePan.web.config;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("sdmConfig") 
public class SdmConfig implements InitializingBean {

	private static Logger logger = Logger.getLogger(SdmConfig.class);

	@Autowired(required = false)
	private ServletContext servletContext;
	@Value("${sdm.repository.localtion}")
	private String filePath;
	@Value("${sdm.authority.check}")
	private boolean authorityCheck;
	@Value("${sdm.file.upload.location}")
	private String uploadPath;
	private String contextRootPath;
	@Value("${sdm.repository.excelpath}")
	private String stationUploadExcelPath;
	
	@Value("${sdm.kml.dir}")
	private String kmlFileDir;
	@Value("${sdm.kml.marker.time}")
	private String kmlMarkerTimeFormat;
	@Value("${sdm.address.serverIp}")
	private String serverIp;
	@Value("${sdm.site.dir}")
	private String siteFileDir;

	
	public String getKmlMarkerTimeFormat() {
		return kmlMarkerTimeFormat;
	}


	public void setKmlMarkerTimeFormat(String kmlMarkerTime) {
		this.kmlMarkerTimeFormat = kmlMarkerTime;
	}


	public String getKmlFileDir() {
		return kmlFileDir;
	}


	public void setKmlFileDir(String kmlFileDir) {
		this.kmlFileDir = kmlFileDir;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		if (servletContext != null) {
			this.contextRootPath = servletContext.getRealPath("/").replaceAll("\\\\", "/");
			this.filePath = this.filePath.trim();
			this.filePath = this.filePath.replaceAll("\\\\", "/");
			this.filePath = this.filePath.replaceAll("(/)+", "/");
			this.filePath = this.filePath.replaceAll("\\{contextroot\\}", this.contextRootPath);
			if (!this.filePath.endsWith("/")) {
				this.filePath = this.filePath + "/";
			}
			this.filePath = this.filePath.trim();
			this.filePath = this.filePath.replaceAll("\\\\", "/");
			this.filePath = this.filePath.replaceAll("(/)+", "/");
			if (logger.isTraceEnabled()) {
				logger.trace("Context Root path is " + this.contextRootPath);
				logger.trace("SDM File will be saved to " + this.filePath);
			}
		}
	}

	
	public String getStationUploadExcelPath() {
		return stationUploadExcelPath;
	}


	public void setStationUploadExcelPath(String stationUploadExcelPath) {
		this.stationUploadExcelPath = stationUploadExcelPath;
	}


	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getContextRootPath() {
		return contextRootPath;
	}

	public void setContextRootPath(String contextRootPath) {
		this.contextRootPath = contextRootPath;
	}

	public boolean isAuthorityCheck() {
		return authorityCheck;
	}

	public void setAuthorityCheck(boolean authorityCheck) {
		this.authorityCheck = authorityCheck;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}


	public String getSiteFileDir() {
		return siteFileDir;
	}


	public void setSiteFileDir(String siteFileDir) {
		this.siteFileDir = siteFileDir;
	}	
	
}
