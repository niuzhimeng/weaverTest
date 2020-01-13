
package hpd_incidentinterface_create_ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CreateOutputMap complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CreateOutputMap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Incident_Number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateOutputMap", propOrder = { "incidentNumber" })
public class CreateOutputMap {

	@XmlElement(name = "Incident_Number", required = true)
	protected String incidentNumber;

	/**
	 * Gets the value of the incidentNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIncidentNumber() {
		return incidentNumber;
	}

	/**
	 * Sets the value of the incidentNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIncidentNumber(String value) {
		this.incidentNumber = value;
	}

}
