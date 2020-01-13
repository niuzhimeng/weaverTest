
package hpd_incidentinterface_create_ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Status_ReasonType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="Status_ReasonType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Infrastructure Change Created"/>
 *     &lt;enumeration value="Local Site Action Required"/>
 *     &lt;enumeration value="Purchase Order Approval"/>
 *     &lt;enumeration value="Registration Approval"/>
 *     &lt;enumeration value="Supplier Delivery"/>
 *     &lt;enumeration value="Support Contact Hold"/>
 *     &lt;enumeration value="Third Party Vendor Action Reqd"/>
 *     &lt;enumeration value="Client Action Required"/>
 *     &lt;enumeration value="Infrastructure Change"/>
 *     &lt;enumeration value="Request"/>
 *     &lt;enumeration value="Future Enhancement"/>
 *     &lt;enumeration value="Pending Original Incident"/>
 *     &lt;enumeration value="Client Hold"/>
 *     &lt;enumeration value="Monitoring Incident"/>
 *     &lt;enumeration value="Customer Follow-Up Required"/>
 *     &lt;enumeration value="Temporary Corrective Action"/>
 *     &lt;enumeration value="No Further Action Required"/>
 *     &lt;enumeration value="Resolved by Original Incident"/>
 *     &lt;enumeration value="Automated Resolution Reported"/>
 *     &lt;enumeration value="No longer a Causal CI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Status_ReasonType")
@XmlEnum
public enum StatusReasonType {

	@XmlEnumValue("Infrastructure Change Created")
	INFRASTRUCTURE_CHANGE_CREATED("Infrastructure Change Created"), @XmlEnumValue("Local Site Action Required")
	LOCAL_SITE_ACTION_REQUIRED("Local Site Action Required"), @XmlEnumValue("Purchase Order Approval")
	PURCHASE_ORDER_APPROVAL("Purchase Order Approval"), @XmlEnumValue("Registration Approval")
	REGISTRATION_APPROVAL("Registration Approval"), @XmlEnumValue("Supplier Delivery")
	SUPPLIER_DELIVERY("Supplier Delivery"), @XmlEnumValue("Support Contact Hold")
	SUPPORT_CONTACT_HOLD("Support Contact Hold"), @XmlEnumValue("Third Party Vendor Action Reqd")
	THIRD_PARTY_VENDOR_ACTION_REQD("Third Party Vendor Action Reqd"), @XmlEnumValue("Client Action Required")
	CLIENT_ACTION_REQUIRED("Client Action Required"), @XmlEnumValue("Infrastructure Change")
	INFRASTRUCTURE_CHANGE("Infrastructure Change"), @XmlEnumValue("Request")
	REQUEST("Request"), @XmlEnumValue("Future Enhancement")
	FUTURE_ENHANCEMENT("Future Enhancement"), @XmlEnumValue("Pending Original Incident")
	PENDING_ORIGINAL_INCIDENT("Pending Original Incident"), @XmlEnumValue("Client Hold")
	CLIENT_HOLD("Client Hold"), @XmlEnumValue("Monitoring Incident")
	MONITORING_INCIDENT("Monitoring Incident"), @XmlEnumValue("Customer Follow-Up Required")
	CUSTOMER_FOLLOW_UP_REQUIRED("Customer Follow-Up Required"), @XmlEnumValue("Temporary Corrective Action")
	TEMPORARY_CORRECTIVE_ACTION("Temporary Corrective Action"), @XmlEnumValue("No Further Action Required")
	NO_FURTHER_ACTION_REQUIRED("No Further Action Required"), @XmlEnumValue("Resolved by Original Incident")
	RESOLVED_BY_ORIGINAL_INCIDENT("Resolved by Original Incident"), @XmlEnumValue("Automated Resolution Reported")
	AUTOMATED_RESOLUTION_REPORTED("Automated Resolution Reported"), @XmlEnumValue("No longer a Causal CI")
	NO_LONGER_A_CAUSAL_CI("No longer a Causal CI");
	private final String value;

	StatusReasonType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static StatusReasonType fromValue(String v) {
		for (StatusReasonType c : StatusReasonType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
