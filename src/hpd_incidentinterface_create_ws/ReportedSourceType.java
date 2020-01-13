
package hpd_incidentinterface_create_ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Reported_SourceType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="Reported_SourceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Direct Input"/>
 *     &lt;enumeration value="Email"/>
 *     &lt;enumeration value="External Escalation"/>
 *     &lt;enumeration value="Fax"/>
 *     &lt;enumeration value="Self Service"/>
 *     &lt;enumeration value="Systems Management"/>
 *     &lt;enumeration value="Phone"/>
 *     &lt;enumeration value="Voice Mail"/>
 *     &lt;enumeration value="Walk In"/>
 *     &lt;enumeration value="Web"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="BMC Impact Manager Event"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Reported_SourceType")
@XmlEnum
public enum ReportedSourceType {

	@XmlEnumValue("Direct Input")
	DIRECT_INPUT("Direct Input"), @XmlEnumValue("Email")
	EMAIL("Email"), @XmlEnumValue("External Escalation")
	EXTERNAL_ESCALATION("External Escalation"), @XmlEnumValue("Fax")
	FAX("Fax"), @XmlEnumValue("Self Service")
	SELF_SERVICE("Self Service"), @XmlEnumValue("Systems Management")
	SYSTEMS_MANAGEMENT("Systems Management"), @XmlEnumValue("Phone")
	PHONE("Phone"), @XmlEnumValue("Voice Mail")
	VOICE_MAIL("Voice Mail"), @XmlEnumValue("Walk In")
	WALK_IN("Walk In"), @XmlEnumValue("Web")
	WEB("Web"), @XmlEnumValue("Other")
	OTHER("Other"), @XmlEnumValue("BMC Impact Manager Event")
	BMC_IMPACT_MANAGER_EVENT("BMC Impact Manager Event");
	private final String value;

	ReportedSourceType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static ReportedSourceType fromValue(String v) {
		for (ReportedSourceType c : ReportedSourceType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
