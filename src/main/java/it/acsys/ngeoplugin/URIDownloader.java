package it.acsys.ngeoplugin;


import int_.esa.eo.ngeo.downloadmanager.exception.DMPluginException;
import int_.esa.eo.ngeo.downloadmanager.exception.FileSystemWriteException;
import int_.esa.eo.ngeo.downloadmanager.exception.ProductUnavailableException;
import int_.esa.eo.ngeo.downloadmanager.exception.UnexpectedResponseException;
import int_.esa.eo.ngeo.downloadmanager.plugin.EDownloadStatus;
import int_.esa.eo.ngeo.downloadmanager.plugin.IDownloadPlugin;
import int_.esa.eo.ngeo.downloadmanager.plugin.IDownloadPluginInfo;
import int_.esa.eo.ngeo.downloadmanager.plugin.IDownloadProcess;
import int_.esa.eo.ngeo.downloadmanager.plugin.IProductDownloadListener;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.Header;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.metalinker.FileType;
import org.metalinker.FilesType;
import org.metalinker.MetalinkType;
import org.metalinker.ResourcesType.Url;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.siemens.pse.umsso.client.UmssoCLCore;
import com.siemens.pse.umsso.client.UmssoCLCoreImpl;
import com.siemens.pse.umsso.client.UmssoCLEnvironment;
import com.siemens.pse.umsso.client.UmssoCLInput;
import com.siemens.pse.umsso.client.UmssoHttpGet;
import com.siemens.pse.umsso.client.util.UmssoHttpResponse;

public class URIDownloader implements IDownloadPlugin {
//	private static XmlRpcClient client = null;
	private static String rpcURL;
	private String logLevel;
	private static Logger log = Logger.getLogger(URIDownloader.class);
	private Properties properties = new Properties();
	String allProxyOption = "";
	
	public URIDownloader() {
	}
	
	public void setRpcUrl(String rpcURL) {
		this.rpcURL = rpcURL;
	}
	
	public IDownloadProcess createDownloadProcess(URI productURI, File repositoryDir, String user, String password, IProductDownloadListener downloadListener, String proxyLocation, int proxyPort, String proxyUser, String proxyPassword) throws DMPluginException {
		return this.createDownloadProcess(productURI, repositoryDir, user, password, downloadListener, proxyLocation, proxyPort, proxyUser, proxyPassword, null);
	}
	
	public IDownloadProcess createDownloadProcess(URI productURI, File repositoryDir, String user, String password, IProductDownloadListener downloadListener, String proxyLocation, int proxyPort, String proxyUser, String proxyPassword, String prevCookieString ) 
				throws DMPluginException {
		log.setLevel(Level.toLevel(logLevel));
		
		allProxyOption = new String("http://{proxyUser}:{proxyPassword}@{proxyLocation}:{proxyPort}");
		if(!proxyLocation.equals("")) {
			allProxyOption = allProxyOption.replace("{proxyLocation}", proxyLocation);
			if(!proxyUser.equals("")) {
				allProxyOption = allProxyOption.replace("{proxyUser}", proxyUser);
				if(!proxyPassword.equals("")) {
					allProxyOption = allProxyOption.replace("{proxyPassword}", proxyPassword);
				} else {
					allProxyOption = allProxyOption.replace(":{proxyPassword}", "");
				}
			} else {
				allProxyOption = allProxyOption.replace("{proxyUser}:{proxyPassword}@", "");
			}
			if(proxyPort > 0) {
				allProxyOption = allProxyOption.replace("{proxyPort}", String.valueOf(proxyPort));
			} else {
				allProxyOption = allProxyOption.replace(":{proxyPort}", "");
			}
			
		} else {
			allProxyOption = "";
		}
		
		
//		RETRIEVE HEADER TO PUT INTO ARIA REQUEST
		UmssoCLCore clCore = UmssoCLCoreImpl.getInstance();
		
		
		if(proxyLocation != null && !proxyLocation.equals("")) {
			clCore.init(new UmssoCLEnvironment(proxyLocation, proxyPort, proxyUser, proxyPassword));
		}
		
			
		UmssoCLInput input = new UmssoCLInput();
		input.setVisualizerCallback(new CommandLineCallback(user, password.toCharArray()));
		UmssoHttpGet reqMethod = new UmssoHttpGet(productURI.toString());
		
		if(prevCookieString!= null && !prevCookieString.equals("")) {
			log.debug("Setting cookie " + prevCookieString);
			reqMethod.setHeader(new BasicHeader("Cookie", prevCookieString));
		}
		
		log.debug("PLUGIN PROCESSING " + productURI);
		input.setAppHttpMethod(reqMethod);
		IDownloadProcessImpl process = null;
		
		try {
	  	  	KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);

	        SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
	        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

	        clCore.getUmssoHttpClient().getConnectionManager().getSchemeRegistry().register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	    	clCore.getUmssoHttpClient().getConnectionManager().getSchemeRegistry().register(new Scheme("https", sf, 443));
			//long initialTime = System.currentTimeMillis();
	    	clCore.processHttpRequest(input);
	    	
	    	UmssoHttpResponse response = reqMethod.getHttpResponseStore().getHttpResponse();
	    	log.debug("PLUGIN PROCESSED " + productURI);
			int code = response.getStatusLine().getStatusCode();
			log.debug("SSO RESPONSE CODE " + code);
			String outName = "";
			switch(code) {
	    		
	    		case 302:
	    		case 301:
	    		case 303:
	    			Header[] headers = response.getHeaders();
	    			for(int n=0; n< headers.length; n++) {
	    				
	    				if(headers[n].getName().equals("Location")) {
	    					log.debug("GOT 302 following new location " +  headers[n].getValue());
	    					List<Cookie> cookies  = clCore.getUmssoHttpClient().getCookieStore().getCookies();	    			  	    
	    			  	    String cookieString = "";
	    			  	  	
	    					for(Cookie cookie : cookies) {
	    						cookieString += cookie.getName() + "="+ cookie.getValue() + "; ";
	    						
	    					}
	    					URL url = new URL(headers[n].getValue());
	    					URIBuilder builder = new URIBuilder();
                            builder.setHost(url.getHost())

                                    .setPort(url.getPort())

                                    .setPath(url.getPath())

                                    .setQuery(url.getQuery())

                                    .setScheme(url.getProtocol());
	    					
	    					URI uri = builder.build();
	    					response.getBodyAsStream().close();
	    					return createDownloadProcess(uri, repositoryDir, user, password, downloadListener,  proxyLocation,  proxyPort,  proxyUser,  proxyPassword, cookieString);
	    				}
	    			}
	    			break;
	    		case 200:
	    			log.debug("GOT 200 for " +  productURI.toString());
	    			String contentLength = null;
	    			headers = response.getHeaders();	    			
	    			boolean isMetalink = false;
	    			for(int n=0; n< headers.length; n++) {
	    				if(headers[n].getName().equals("Content-Length")) {
	    					contentLength = headers[n].getValue();
	    				}
	    				if(headers[n].getValue().contains("metalink+xml")) {
	    					isMetalink = true;
	    				}
	    			}
	    			Long fileSize = 0L;
	    			if(contentLength != null) {
	    				fileSize = Long.valueOf(contentLength);
	    			}
	    			
	    			long freeSpace = repositoryDir.getUsableSpace(); 
	    			log.debug("REPOSITORY DIR " + repositoryDir) ;
	    			log.debug("REPOSITORY SIZE " + freeSpace + " file size " + fileSize);
	    			if(freeSpace < fileSize) {
	    				log.debug("No enough space on disk to download " + productURI.toString());
	    				downloadListener.progress(0,0l, EDownloadStatus.IN_ERROR, "no enough space on disk to download " + productURI.toString(), new FileSystemWriteException("no enough space on disk to download " + productURI.toString()));
	    				break;
	    			}
	    			
	    			URL url = new URL(productURI.toString());
//    				String query = url.getQuery();
//    				String options = "";
//    				if(query != null) {
//	    				String[] params = query.split("%26");
//	    			    for (String param : params)  
//	    			    {  
//	    			        if(param.startsWith("ngEO_DO")) {
//	    			        	String downloadsOptions = param.split("=")[1];
//	    			        	options = downloadsOptions.replace("%7B", "(");
//	    			        	options = options.replace("%7D", ")");
//	    			        	options = options.replace("%3A", "=");
//	    			        }
//	    			    }
//    				}
	    			   
	    			
	    			if(isMetalink) {
	    				log.debug("IS A METALINK ");
	//    				SAVE METALINK ON LOCAL DISK
	    				Random rn = new Random();
	    				String metalinkName = "metalink_" + Math.abs(rn.nextInt()); 	    				
	//    				RENAME DOWNLOAD DIRECTORY WITH DOWNLOADS OPTIONS
	    				
	    			    
	    			    try {
	    					
	    					JAXBContext jaxbContext = JAXBContext.newInstance(MetalinkType.class);
	    				   	 
	    					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    					MetalinkType metalink = (MetalinkType) jaxbUnmarshaller.unmarshal(response.getBodyAsStream());
	    					FilesType filesType = metalink.getFiles();
	    					List<FileType> files = filesType.getFile();
	    					for(FileType file : files) {
//	    						if(!options.equals("")) {
//		    						String fileName = file.getName();
//		    						StringBuilder builder = new StringBuilder(fileName);	    						
//		    						builder.insert(builder.indexOf("/"), options);
//		    						
//		    						file.setName(builder.toString());
//	    						}
	    						
	    						List<Url> urls = file.getResources().getUrl();
	    						if(urls.size() > 1) {
	    							for(Url currVal : urls) {
	    								URI currUrl = null;
										try {
											currUrl = new URI(currVal.getValue());
										} catch (URISyntaxException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}	    								
	    							}
	    							normalizePreference(urls);
	    						}
	    						
	    					}
	    					
	    					Marshaller marshaller = jaxbContext.createMarshaller();
	    					marshaller.marshal(metalink, new File("webapps/METALINK/" + metalinkName + ".metalink"));
	    					
	    			    } catch(JAXBException jaxbEx) {
	    		    		jaxbEx.printStackTrace();
	    		    		log.error("Can not perform http request to " + productURI);
	    		    		downloadListener.progress(0,0l, EDownloadStatus.IN_ERROR, 
	    		    				"Can not perform http request to " + productURI, null);
	    		    	}
	    			    
	    			    
	//    				USE AS URI THE LOCAL METALINK
	    			    productURI =  new URI((String) properties.getProperty("protocol") +"://localhost:" + (String) properties.getProperty("jettyPort") + "/METALINK/" + metalinkName + ".metalink");
	    			    log.debug("new product uri " + productURI);
					} 	    			
	    			return startDownload(clCore, productURI, downloadListener, repositoryDir);
	    		case 202:
	    			downloadListener.progress(0,0l, EDownloadStatus.IDLE, "Retry later", null);
		    		Long retryAfter = Long.valueOf(properties.getProperty("RetryDownloadAfter"));
		    		try {
		    		  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		  	          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		  	          byte[] body = response.getBody(); 
		  	          Document doc = dBuilder.parse(new ByteArrayInputStream(body));		  	      
		  		      log.debug("PF RESPONSE " + new String(body));
			    	  XPathFactory xpf = XPathFactory.newInstance();
					  XPath xpath = xpf.newXPath();
					  
					  Node node = (Node) xpath.evaluate("/ProductDownloadResponse/RetryAfter", doc, XPathConstants.NODE);
					  retryAfter = Long.valueOf(node.getTextContent()) * 1000;
					  log.debug("Retry download after " + retryAfter + " milliseconds.");
					  
					  reqMethod.releaseConnection();
		    		} catch (Exception e) {
		    			e.printStackTrace();
		    			log.debug("Can not read retry value from response. The default value will be used.");
		    		}
		    		
		    		
		    		int headCode = -1;
		    		do {
	//	    			IT SHOULD BE HEAD REQUEST BY ICD BUT JCL 2.2.3 GIVES A NULL POINTER FOR HEAD REQUESTS
		    			UmssoHttpGet getMethod = new UmssoHttpGet(productURI);
			    		input.setAppHttpMethod(getMethod);
		    			clCore.processHttpRequest(input);
		    			headCode = getMethod.getHttpResponseStore().getHttpResponse().getStatusLine().getStatusCode();
		    			getMethod.abort();
		    			getMethod.releaseConnection();
		    			if (headCode == 202){
			    			try {
				    			Thread.sleep(retryAfter);
				    		} catch(InterruptedException inEx) {
				    			inEx.printStackTrace();
				        		log.error("Can not sleep");
				        	}
			    			log.debug("RETRYING... TO DOWNLOAD " + productURI.toString());
		    			} else if (headCode == 200){
		    				return createDownloadProcess(productURI, repositoryDir, user, password, downloadListener, proxyLocation, proxyPort, proxyUser, proxyPassword);
		    			} else if (headCode == 404){
		    				downloadListener.progress(0,0l, EDownloadStatus.IN_ERROR, "Product not found", null);
		    			}
		    		} while(headCode == 202);
		    		break;
		    	case 503:		    		
		    		log.debug("STATUS CODE 503 for " + productURI.toString());
		    		Long sleepTime = Long.valueOf(properties.getProperty("RetryDownloadAfter"));
		    		long firstFailure = System.currentTimeMillis();
		    		long maxRetryValue = Long.valueOf(properties.getProperty("maxRetryTime"));
		    		
		    		do {
		    			UmssoHttpGet getMethod = new UmssoHttpGet(productURI);
			    		input.setAppHttpMethod(getMethod);
		    			clCore.processHttpRequest(input);
		    			headCode = getMethod.getHttpResponseStore().getHttpResponse().getStatusLine().getStatusCode();
		    			getMethod.abort();
		    			getMethod.releaseConnection();
		    			if (headCode == 503){
			    			try {
				    			Thread.sleep(sleepTime);
				    		} catch(InterruptedException inEx) {
				    			inEx.printStackTrace();
				        		log.error("Can not sleep");
				        	}
			    			log.debug("RETRYING... TO DOWNLOAD " + productURI.toString());
		    			} else if (headCode == 200){
		    				return startDownload(clCore, productURI, downloadListener, repositoryDir);
		    			} else if (headCode == 404){
		    				downloadListener.progress(0,0l, EDownloadStatus.IN_ERROR, "Product not found: 404", null);
		    				break;
		    			}
		    		} while((System.currentTimeMillis()-firstFailure)<maxRetryValue);
		    		
		    		String body = new String(response.getBody());
		    		String errorMessage = extractErrorMessage(body, "/BadRequestResponse");
		    		if(errorMessage.equals("")) {
	    				errorMessage = "Internal server error: 503";
	    			}
		    		downloadListener.progress(0,0l, EDownloadStatus.IN_ERROR, errorMessage, 
		    					new ProductUnavailableException(errorMessage));
		    		break;
		    	case 400:
		    		//check if response is an xml compliant with ngEOBadRequestResponse.xsd
		    		//ifxml
		    		body = new String(response.getBody());
		    		errorMessage = extractErrorMessage(body, "/BadRequestResponse");
		    		
	    			if(errorMessage.equals("")) {
	    				errorMessage = "Invalid options: 400";
	    			}
	    			
	    			downloadListener.progress(0,0l, EDownloadStatus.IN_ERROR, errorMessage, 
				  	        		new UnexpectedResponseException(errorMessage));
		    		break;
		    	case 404:
		    		body = new String(response.getBody());
		    		errorMessage = extractErrorMessage(body, "/MissingProductResponse");
		    		
	    			if(errorMessage.equals("")) {
	    				errorMessage = "Product not found: 404";
	    			}
	    			
	    			downloadListener.progress(0,0l, EDownloadStatus.IN_ERROR, errorMessage, 
				  	        		new UnexpectedResponseException(errorMessage));
		    		break;
		    	case 401:
		    		body = new String(response.getBody());
		    		errorMessage = extractErrorMessage(body, "/MissingProductResponse");
		    		
	    			if(errorMessage.equals("")) {
	    				errorMessage = "Authorization required: 401";
	    			}
	    			
	    			downloadListener.progress(0,0l, EDownloadStatus.IN_ERROR, errorMessage, 
				  	        		new UnexpectedResponseException(errorMessage));
		    		break;
		    	case 403:
		    		body = new String(response.getBody());
		    		errorMessage = extractErrorMessage(body, "/MissingProductResponse");
		    		
	    			if(errorMessage.equals("")) {
	    				errorMessage = "Access forbidden: 403";
	    			}
	    			
	    			downloadListener.progress(0,0l, EDownloadStatus.IN_ERROR, errorMessage, 
				  	        		new UnexpectedResponseException(errorMessage));
		    		break;
		    	default:
		    		body = new String(response.getBody());
		    		errorMessage = extractErrorMessage(body, "/BadRequestResponse");
		    		if(errorMessage.equals("")) {
		    			errorMessage = body;
		    		}
		  	        downloadListener.progress(0,0l, EDownloadStatus.IN_ERROR, errorMessage, 
		  	        		new UnexpectedResponseException(errorMessage));
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			downloadListener.progress(0,0l, EDownloadStatus.IN_ERROR, e.getMessage(), null);
		} finally {
			if(reqMethod != null) {
				reqMethod.abort();
				reqMethod.releaseConnection();
			}
	    }
			
		return process;
	}
	
	public void terminate() throws DMPluginException {
//		Object[] params = new Object[]{};
//		try {
//			getRPCClient().execute("aria2.shutdown",params);
//		} catch(XmlRpcException ex)  	{
//			throw new DMPluginException(ex.getMessage());
//		}
		
	}
	
	private IDownloadProcessImpl startDownload(UmssoCLCore clCore, URI productURI,IProductDownloadListener downloadListener, File repositoryDir) {
		List<Cookie> cookies  = clCore.getUmssoHttpClient().getCookieStore().getCookies();
//		String cookie_saml = "_saml_idp=";
//  	  	String cookie_shib = "_shibsession_=";
//  	  	String umsso20session = "umsso20session";
//  	    String JSESSIONID = "JSESSIONID";
  	    
  	    String cookieString = "";
  	  	
		for(Cookie cookie : cookies) {
//			if(cookie.getName().equals("_saml_idp")) {
//				cookie_saml = cookie.getName() + "=" + cookie.getValue();
//			}
//			if(cookie.getName().contains("_shibsession_")) {
//				cookie_shib = cookie.getName() + "="+ cookie.getValue();
//			}
//			if(cookie.getName().equals("umsso20session")) {
//				umsso20session = cookie.getName() + "="+ cookie.getValue();
//			}
//			if(cookie.getName().equals("JSESSIONID")) {
//				JSESSIONID = cookie.getName() + "="+ cookie.getValue();
//			}
			
			cookieString += cookie.getName() + "="+ cookie.getValue() + "; ";
			
		}
	
		//String cookie = cookie_saml + "; " + cookie_shib + "; " + umsso20session + "; " + JSESSIONID;
		//String header = "Cookie: " + cookie;
		
		String header = "Cookie: " + cookieString;
		log.debug("CREATING ARIA PROCESS WITH URL " + productURI.toString());
		log.debug("CREATING ARIA PROCESS WITH COOKIE " + header);
		
		IDownloadProcessImpl process =  new IDownloadProcessImpl(rpcURL,null, downloadListener,productURI.toString(),header, repositoryDir, this.logLevel, allProxyOption);
		
		try {
			process.startDownload();
		} catch (DMPluginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("PLUG IN RETURNING PROCESS " + process);
		
		return process;
	}
	
	public IDownloadPluginInfo initialize(File tmpRootDir, File pluginCfgRootDir) throws DMPluginException {
		try {
			java.io.InputStream stream  = new java.io.FileInputStream(pluginCfgRootDir);
	    	properties.load(stream);
	    	stream.close();
			this.setRpcUrl("http://localhost:" + properties.getProperty("ariaPort") +"/rpc");
			this.setLogLevel(properties.getProperty("loglevel"));
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		return new IDownloadPluginInfoImpl();
		
	}
	
	private void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	private void normalizePreference(List<Url> urls) {
		Collections.sort(urls, new Comparator<Url>() {
	
			@Override
			public int compare(Url o1, Url o2) {
				if(o1.getPreference() != null && o2.getPreference() != null) {
					return o1.getPreference().compareTo(o2.getPreference());
				} else {
					if(o1.getPreference() != null) {
						return -1;
					}
					if(o2.getPreference() != null) {
						return 1;
					}
					return 0;
				}
			}
		});
		
		BigInteger preference = BigInteger.valueOf(100);
		for(Url currUrl : urls) {
			currUrl.setPreference(preference);
			preference = BigInteger.valueOf(preference.intValue()-1);
		}
	
	}
	
	private String extractErrorMessage(String body, String responseType) {
		String errorMessage = "";
		if(body.startsWith("<?xml version")) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    			Document doc = dBuilder.parse(new ByteArrayInputStream(body.getBytes()));
    			XPathFactory xpf = XPathFactory.newInstance();
    			XPath xpath = xpf.newXPath();
    			Node node = (Node) xpath.evaluate(responseType + "/ResponseMessage", doc, XPathConstants.NODE);
    			if(node != null) {
    				errorMessage = node.getTextContent();
    			}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return errorMessage;
	}
	
}
