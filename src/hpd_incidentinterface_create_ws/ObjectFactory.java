
package hpd_incidentinterface_create_ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the hpd_incidentinterface_create_ws package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _HelpDeskSubmitService_QNAME = new QName("urn:HPD_IncidentInterface_Create_WS",
			"HelpDesk_Submit_Service");
	private final static QName _AuthenticationInfo_QNAME = new QName("urn:HPD_IncidentInterface_Create_WS",
			"AuthenticationInfo");
	private final static QName _HelpDeskSubmitServiceResponse_QNAME = new QName("urn:HPD_IncidentInterface_Create_WS",
			"HelpDesk_Submit_ServiceResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: hpd_incidentinterface_create_ws
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link CreateInputMap }
	 * 
	 */
	public CreateInputMap createCreateInputMap() {
		return new CreateInputMap();
	}

	/**
	 * Create an instance of {@link AuthenticationInfo }
	 * 
	 */
	public AuthenticationInfo createAuthenticationInfo() {
		return new AuthenticationInfo();
	}

	/**
	 * Create an instance of {@link CreateOutputMap }
	 * 
	 */
	public CreateOutputMap createCreateOutputMap() {
		return new CreateOutputMap();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CreateInputMap
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "urn:HPD_IncidentInterface_Create_WS", name = "HelpDesk_Submit_Service")
	public JAXBElement<CreateInputMap> createHelpDeskSubmitService(CreateInputMap value) {
		return new JAXBElement<CreateInputMap>(_HelpDeskSubmitService_QNAME, CreateInputMap.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link AuthenticationInfo }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "urn:HPD_IncidentInterface_Create_WS", name = "AuthenticationInfo")
	public JAXBElement<AuthenticationInfo> createAuthenticationInfo(AuthenticationInfo value) {
		return new JAXBElement<AuthenticationInfo>(_AuthenticationInfo_QNAME, AuthenticationInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CreateOutputMap
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "urn:HPD_IncidentInterface_Create_WS", name = "HelpDesk_Submit_ServiceResponse")
	public JAXBElement<CreateOutputMap> createHelpDeskSubmitServiceResponse(CreateOutputMap value) {
		return new JAXBElement<CreateOutputMap>(_HelpDeskSubmitServiceResponse_QNAME, CreateOutputMap.class, null,
				value);
	}

}
