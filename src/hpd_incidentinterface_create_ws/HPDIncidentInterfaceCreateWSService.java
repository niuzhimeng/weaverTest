
package hpd_incidentinterface_create_ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * 
 * HelpDesk_Submit_Service Create
 * 
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
* HPD_IncidentInterface_Create_WSService service = new HPD_IncidentInterface_Create_WSService();
* HPDIncidentInterfaceCreateWSPortTypePortType portType = service.getHPDIncidentInterfaceCreateWSPortTypeSoap();
* portType.helpDeskSubmitService(...);
 * </pre>
 * </p>
 * 
 */
@WebServiceClient(name = "HPD_IncidentInterface_Create_WSService", targetNamespace = "urn:HPD_IncidentInterface_Create_WS", wsdlLocation = "http://remedy.ss-tpc.com:8080/arsys/WSDL/public/Remedy-AP/HPD_IncidentInterface_Create_WS")
public class HPDIncidentInterfaceCreateWSService extends Service {

	private final static URL HPDINCIDENTINTERFACECREATEWSSERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(HPDIncidentInterfaceCreateWSService.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = HPDIncidentInterfaceCreateWSService.class.getResource(".");
			url = new URL(baseUrl,
					"http://remedy.ss-tpc.com:8080/arsys/WSDL/public/Remedy-AP/HPD_IncidentInterface_Create_WS");
		} catch (MalformedURLException e) {
			logger.warning(
					"Failed to create URL for the wsdl Location: 'http://remedy.ss-tpc.com:8080/arsys/WSDL/public/Remedy-AP/HPD_IncidentInterface_Create_WS', retrying as a local file");
			logger.warning(e.getMessage());
		}
		HPDINCIDENTINTERFACECREATEWSSERVICE_WSDL_LOCATION = url;
	}

	public HPDIncidentInterfaceCreateWSService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public HPDIncidentInterfaceCreateWSService() {
		super(HPDINCIDENTINTERFACECREATEWSSERVICE_WSDL_LOCATION,
				new QName("urn:HPD_IncidentInterface_Create_WS", "HPD_IncidentInterface_Create_WSService"));
	}

	/**
	 * 
	 * @return returns HPDIncidentInterfaceCreateWSPortTypePortType
	 */
	@WebEndpoint(name = "HPD_IncidentInterface_Create_WSPortTypeSoap")
	public HPDIncidentInterfaceCreateWSPortTypePortType getHPDIncidentInterfaceCreateWSPortTypeSoap() {
		return super.getPort(
				new QName("urn:HPD_IncidentInterface_Create_WS", "HPD_IncidentInterface_Create_WSPortTypeSoap"),
				HPDIncidentInterfaceCreateWSPortTypePortType.class);
	}

}
