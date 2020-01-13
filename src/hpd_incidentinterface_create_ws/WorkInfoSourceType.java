
package hpd_incidentinterface_create_ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Work_Info_SourceType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="Work_Info_SourceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Email"/>
 *     &lt;enumeration value="Fax"/>
 *     &lt;enumeration value="Phone"/>
 *     &lt;enumeration value="Voice Mail"/>
 *     &lt;enumeration value="Walk In"/>
 *     &lt;enumeration value="Pager"/>
 *     &lt;enumeration value="System Assignment"/>
 *     &lt;enumeration value="Web"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="BMC Impact Manager Event"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Work_Info_SourceType")
@XmlEnum
public enum WorkInfoSourceType {

	@XmlEnumValue("Email")
	EMAIL("Email"), @XmlEnumValue("Fax")
	FAX("Fax"), @XmlEnumValue("Phone")
	PHONE("Phone"), @XmlEnumValue("Voice Mail")
	VOICE_MAIL("Voice Mail"), @XmlEnumValue("Walk In")
	WALK_IN("Walk In"), @XmlEnumValue("Pager")
	PAGER("Pager"), @XmlEnumValue("System Assignment")
	SYSTEM_ASSIGNMENT("System Assignment"), @XmlEnumValue("Web")
	WEB("Web"), @XmlEnumValue("Other")
	OTHER("Other"), @XmlEnumValue("BMC Impact Manager Event")
	BMC_IMPACT_MANAGER_EVENT("BMC Impact Manager Event");
	private final String value;

	WorkInfoSourceType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static WorkInfoSourceType fromValue(String v) {
		for (WorkInfoSourceType c : WorkInfoSourceType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
