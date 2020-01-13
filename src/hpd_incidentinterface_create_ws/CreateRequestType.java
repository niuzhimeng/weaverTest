
package hpd_incidentinterface_create_ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Create_RequestType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="Create_RequestType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Yes"/>
 *     &lt;enumeration value="No"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Create_RequestType")
@XmlEnum
public enum CreateRequestType {

	@XmlEnumValue("Yes")
	YES("Yes"), @XmlEnumValue("No")
	NO("No");
	private final String value;

	CreateRequestType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static CreateRequestType fromValue(String v) {
		for (CreateRequestType c : CreateRequestType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
