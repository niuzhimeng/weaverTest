
package hpd_incidentinterface_create_ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for CreateInputMap complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CreateInputMap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Assigned_Group" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Assigned_Group_Shift_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Assigned_Support_Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Assigned_Support_Organization" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Assignee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Categorization_Tier_1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Categorization_Tier_2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Categorization_Tier_3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CI_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Closure_Manufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Closure_Product_Category_Tier1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Closure_Product_Category_Tier2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Closure_Product_Category_Tier3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Closure_Product_Model_Version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Closure_Product_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="First_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Impact" type="{urn:HPD_IncidentInterface_Create_WS}ImpactType"/>
 *         &lt;element name="Last_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Lookup_Keyword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Manufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Product_Categorization_Tier_1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Product_Categorization_Tier_2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Product_Categorization_Tier_3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Product_Model_Version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Product_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reported_Source" type="{urn:HPD_IncidentInterface_Create_WS}Reported_SourceType"/>
 *         &lt;element name="Resolution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Resolution_Category_Tier_1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Resolution_Category_Tier_2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Resolution_Category_Tier_3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Service_Type" type="{urn:HPD_IncidentInterface_Create_WS}Service_TypeType"/>
 *         &lt;element name="Status" type="{urn:HPD_IncidentInterface_Create_WS}StatusType"/>
 *         &lt;element name="Action" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Create_Request" type="{urn:HPD_IncidentInterface_Create_WS}Create_RequestType" minOccurs="0"/>
 *         &lt;element name="Summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Urgency" type="{urn:HPD_IncidentInterface_Create_WS}UrgencyType"/>
 *         &lt;element name="Work_Info_Summary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Work_Info_Notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Work_Info_Type" type="{urn:HPD_IncidentInterface_Create_WS}Work_Info_TypeType" minOccurs="0"/>
 *         &lt;element name="Work_Info_Date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Work_Info_Source" type="{urn:HPD_IncidentInterface_Create_WS}Work_Info_SourceType" minOccurs="0"/>
 *         &lt;element name="Work_Info_Locked" type="{urn:HPD_IncidentInterface_Create_WS}Create_RequestType" minOccurs="0"/>
 *         &lt;element name="Work_Info_View_Access" type="{urn:HPD_IncidentInterface_Create_WS}Work_Info_View_AccessType" minOccurs="0"/>
 *         &lt;element name="Middle_Initial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status_Reason" type="{urn:HPD_IncidentInterface_Create_WS}Status_ReasonType" minOccurs="0"/>
 *         &lt;element name="Direct_Contact_First_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Direct_Contact_Middle_Initial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Direct_Contact_Last_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TemplateID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceCI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceCI_ReconID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HPD_CI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HPD_CI_ReconID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HPD_CI_FormName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkInfoAttachment1Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkInfoAttachment1Data" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="WorkInfoAttachment1OrigSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Login_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Customer_Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Corporate_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateInputMap", propOrder = { "assignedGroup", "assignedGroupShiftName", "assignedSupportCompany",
		"assignedSupportOrganization", "assignee", "categorizationTier1", "categorizationTier2", "categorizationTier3",
		"ciName", "closureManufacturer", "closureProductCategoryTier1", "closureProductCategoryTier2",
		"closureProductCategoryTier3", "closureProductModelVersion", "closureProductName", "department", "firstName",
		"impact", "lastName", "lookupKeyword", "manufacturer", "productCategorizationTier1",
		"productCategorizationTier2", "productCategorizationTier3", "productModelVersion", "productName",
		"reportedSource", "resolution", "resolutionCategoryTier1", "resolutionCategoryTier2", "resolutionCategoryTier3",
		"serviceType", "status", "action", "createRequest", "summary", "notes", "urgency", "workInfoSummary",
		"workInfoNotes", "workInfoType", "workInfoDate", "workInfoSource", "workInfoLocked", "workInfoViewAccess",
		"middleInitial", "statusReason", "directContactFirstName", "directContactMiddleInitial",
		"directContactLastName", "templateID", "serviceCI", "serviceCIReconID", "hpdci", "hpdciReconID",
		"hpdciFormName", "workInfoAttachment1Name", "workInfoAttachment1Data", "workInfoAttachment1OrigSize", "loginID",
		"customerCompany", "corporateID" })
public class CreateInputMap {

	@XmlElement(name = "Assigned_Group")
	protected String assignedGroup;
	@XmlElement(name = "Assigned_Group_Shift_Name")
	protected String assignedGroupShiftName;
	@XmlElement(name = "Assigned_Support_Company")
	protected String assignedSupportCompany;
	@XmlElement(name = "Assigned_Support_Organization")
	protected String assignedSupportOrganization;
	@XmlElement(name = "Assignee")
	protected String assignee;
	@XmlElement(name = "Categorization_Tier_1")
	protected String categorizationTier1;
	@XmlElement(name = "Categorization_Tier_2")
	protected String categorizationTier2;
	@XmlElement(name = "Categorization_Tier_3")
	protected String categorizationTier3;
	@XmlElement(name = "CI_Name")
	protected String ciName;
	@XmlElement(name = "Closure_Manufacturer")
	protected String closureManufacturer;
	@XmlElement(name = "Closure_Product_Category_Tier1")
	protected String closureProductCategoryTier1;
	@XmlElement(name = "Closure_Product_Category_Tier2")
	protected String closureProductCategoryTier2;
	@XmlElement(name = "Closure_Product_Category_Tier3")
	protected String closureProductCategoryTier3;
	@XmlElement(name = "Closure_Product_Model_Version")
	protected String closureProductModelVersion;
	@XmlElement(name = "Closure_Product_Name")
	protected String closureProductName;
	@XmlElement(name = "Department")
	protected String department;
	@XmlElement(name = "First_Name", required = true)
	protected String firstName;
	@XmlElement(name = "Impact", required = true, nillable = true)
	protected String impact;
	@XmlElement(name = "Last_Name", required = true)
	protected String lastName;
	@XmlElement(name = "Lookup_Keyword")
	protected String lookupKeyword;
	@XmlElement(name = "Manufacturer")
	protected String manufacturer;
	@XmlElement(name = "Product_Categorization_Tier_1")
	protected String productCategorizationTier1;
	@XmlElement(name = "Product_Categorization_Tier_2")
	protected String productCategorizationTier2;
	@XmlElement(name = "Product_Categorization_Tier_3")
	protected String productCategorizationTier3;
	@XmlElement(name = "Product_Model_Version")
	protected String productModelVersion;
	@XmlElement(name = "Product_Name")
	protected String productName;
	@XmlElement(name = "Reported_Source", required = true, nillable = true)
	protected ReportedSourceType reportedSource;
	@XmlElement(name = "Resolution")
	protected String resolution;
	@XmlElement(name = "Resolution_Category_Tier_1")
	protected String resolutionCategoryTier1;
	@XmlElement(name = "Resolution_Category_Tier_2")
	protected String resolutionCategoryTier2;
	@XmlElement(name = "Resolution_Category_Tier_3")
	protected String resolutionCategoryTier3;
	@XmlElement(name = "Service_Type", required = true, nillable = true)
	protected ServiceTypeType serviceType;
	@XmlElement(name = "Status", required = true)
	protected StatusType status;
	@XmlElement(name = "Action", required = true)
	protected String action;
	@XmlElement(name = "Create_Request")
	protected CreateRequestType createRequest;
	@XmlElement(name = "Summary", required = true)
	protected String summary;
	@XmlElement(name = "Notes")
	protected String notes;
	@XmlElement(name = "Urgency", required = true, nillable = true)
	protected String urgency;
	@XmlElement(name = "Work_Info_Summary")
	protected String workInfoSummary;
	@XmlElement(name = "Work_Info_Notes")
	protected String workInfoNotes;
	@XmlElement(name = "Work_Info_Type")
	protected WorkInfoTypeType workInfoType;
	@XmlElement(name = "Work_Info_Date")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar workInfoDate;
	@XmlElement(name = "Work_Info_Source")
	protected WorkInfoSourceType workInfoSource;
	@XmlElement(name = "Work_Info_Locked", defaultValue = "No")
	protected CreateRequestType workInfoLocked;
	@XmlElement(name = "Work_Info_View_Access", defaultValue = "Internal")
	protected WorkInfoViewAccessType workInfoViewAccess;
	@XmlElement(name = "Middle_Initial")
	protected String middleInitial;
	@XmlElement(name = "Status_Reason")
	protected StatusReasonType statusReason;
	@XmlElement(name = "Direct_Contact_First_Name")
	protected String directContactFirstName;
	@XmlElement(name = "Direct_Contact_Middle_Initial")
	protected String directContactMiddleInitial;
	@XmlElement(name = "Direct_Contact_Last_Name")
	protected String directContactLastName;
	@XmlElement(name = "TemplateID")
	protected String templateID;
	@XmlElement(name = "ServiceCI")
	protected String serviceCI;
	@XmlElement(name = "ServiceCI_ReconID")
	protected String serviceCIReconID;
	@XmlElement(name = "HPD_CI")
	protected String hpdci;
	@XmlElement(name = "HPD_CI_ReconID")
	protected String hpdciReconID;
	@XmlElement(name = "HPD_CI_FormName")
	protected String hpdciFormName;
	@XmlElement(name = "WorkInfoAttachment1Name")
	protected String workInfoAttachment1Name;
	@XmlElement(name = "WorkInfoAttachment1Data")
	protected byte[] workInfoAttachment1Data;
	@XmlElement(name = "WorkInfoAttachment1OrigSize")
	protected Integer workInfoAttachment1OrigSize;
	@XmlElement(name = "Login_ID")
	protected String loginID;
	@XmlElement(name = "Customer_Company")
	protected String customerCompany;
	@XmlElement(name = "Corporate_ID")
	protected String corporateID;

	/**
	 * Gets the value of the assignedGroup property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAssignedGroup() {
		return assignedGroup;
	}

	/**
	 * Sets the value of the assignedGroup property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAssignedGroup(String value) {
		this.assignedGroup = value;
	}

	/**
	 * Gets the value of the assignedGroupShiftName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAssignedGroupShiftName() {
		return assignedGroupShiftName;
	}

	/**
	 * Sets the value of the assignedGroupShiftName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAssignedGroupShiftName(String value) {
		this.assignedGroupShiftName = value;
	}

	/**
	 * Gets the value of the assignedSupportCompany property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAssignedSupportCompany() {
		return assignedSupportCompany;
	}

	/**
	 * Sets the value of the assignedSupportCompany property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAssignedSupportCompany(String value) {
		this.assignedSupportCompany = value;
	}

	/**
	 * Gets the value of the assignedSupportOrganization property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAssignedSupportOrganization() {
		return assignedSupportOrganization;
	}

	/**
	 * Sets the value of the assignedSupportOrganization property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAssignedSupportOrganization(String value) {
		this.assignedSupportOrganization = value;
	}

	/**
	 * Gets the value of the assignee property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAssignee() {
		return assignee;
	}

	/**
	 * Sets the value of the assignee property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAssignee(String value) {
		this.assignee = value;
	}

	/**
	 * Gets the value of the categorizationTier1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCategorizationTier1() {
		return categorizationTier1;
	}

	/**
	 * Sets the value of the categorizationTier1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCategorizationTier1(String value) {
		this.categorizationTier1 = value;
	}

	/**
	 * Gets the value of the categorizationTier2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCategorizationTier2() {
		return categorizationTier2;
	}

	/**
	 * Sets the value of the categorizationTier2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCategorizationTier2(String value) {
		this.categorizationTier2 = value;
	}

	/**
	 * Gets the value of the categorizationTier3 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCategorizationTier3() {
		return categorizationTier3;
	}

	/**
	 * Sets the value of the categorizationTier3 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCategorizationTier3(String value) {
		this.categorizationTier3 = value;
	}

	/**
	 * Gets the value of the ciName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCIName() {
		return ciName;
	}

	/**
	 * Sets the value of the ciName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCIName(String value) {
		this.ciName = value;
	}

	/**
	 * Gets the value of the closureManufacturer property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClosureManufacturer() {
		return closureManufacturer;
	}

	/**
	 * Sets the value of the closureManufacturer property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClosureManufacturer(String value) {
		this.closureManufacturer = value;
	}

	/**
	 * Gets the value of the closureProductCategoryTier1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClosureProductCategoryTier1() {
		return closureProductCategoryTier1;
	}

	/**
	 * Sets the value of the closureProductCategoryTier1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClosureProductCategoryTier1(String value) {
		this.closureProductCategoryTier1 = value;
	}

	/**
	 * Gets the value of the closureProductCategoryTier2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClosureProductCategoryTier2() {
		return closureProductCategoryTier2;
	}

	/**
	 * Sets the value of the closureProductCategoryTier2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClosureProductCategoryTier2(String value) {
		this.closureProductCategoryTier2 = value;
	}

	/**
	 * Gets the value of the closureProductCategoryTier3 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClosureProductCategoryTier3() {
		return closureProductCategoryTier3;
	}

	/**
	 * Sets the value of the closureProductCategoryTier3 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClosureProductCategoryTier3(String value) {
		this.closureProductCategoryTier3 = value;
	}

	/**
	 * Gets the value of the closureProductModelVersion property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClosureProductModelVersion() {
		return closureProductModelVersion;
	}

	/**
	 * Sets the value of the closureProductModelVersion property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClosureProductModelVersion(String value) {
		this.closureProductModelVersion = value;
	}

	/**
	 * Gets the value of the closureProductName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClosureProductName() {
		return closureProductName;
	}

	/**
	 * Sets the value of the closureProductName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClosureProductName(String value) {
		this.closureProductName = value;
	}

	/**
	 * Gets the value of the department property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Sets the value of the department property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDepartment(String value) {
		this.department = value;
	}

	/**
	 * Gets the value of the firstName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the value of the firstName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFirstName(String value) {
		this.firstName = value;
	}

	/**
	 * Gets the value of the impact property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImpact() {
		return impact;
	}

	/**
	 * Sets the value of the impact property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setImpact(String value) {
		this.impact = value;
	}

	/**
	 * Gets the value of the lastName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the value of the lastName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLastName(String value) {
		this.lastName = value;
	}

	/**
	 * Gets the value of the lookupKeyword property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLookupKeyword() {
		return lookupKeyword;
	}

	/**
	 * Sets the value of the lookupKeyword property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLookupKeyword(String value) {
		this.lookupKeyword = value;
	}

	/**
	 * Gets the value of the manufacturer property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets the value of the manufacturer property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setManufacturer(String value) {
		this.manufacturer = value;
	}

	/**
	 * Gets the value of the productCategorizationTier1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductCategorizationTier1() {
		return productCategorizationTier1;
	}

	/**
	 * Sets the value of the productCategorizationTier1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductCategorizationTier1(String value) {
		this.productCategorizationTier1 = value;
	}

	/**
	 * Gets the value of the productCategorizationTier2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductCategorizationTier2() {
		return productCategorizationTier2;
	}

	/**
	 * Sets the value of the productCategorizationTier2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductCategorizationTier2(String value) {
		this.productCategorizationTier2 = value;
	}

	/**
	 * Gets the value of the productCategorizationTier3 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductCategorizationTier3() {
		return productCategorizationTier3;
	}

	/**
	 * Sets the value of the productCategorizationTier3 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductCategorizationTier3(String value) {
		this.productCategorizationTier3 = value;
	}

	/**
	 * Gets the value of the productModelVersion property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductModelVersion() {
		return productModelVersion;
	}

	/**
	 * Sets the value of the productModelVersion property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductModelVersion(String value) {
		this.productModelVersion = value;
	}

	/**
	 * Gets the value of the productName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the value of the productName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductName(String value) {
		this.productName = value;
	}

	/**
	 * Gets the value of the reportedSource property.
	 * 
	 * @return possible object is {@link ReportedSourceType }
	 * 
	 */
	public ReportedSourceType getReportedSource() {
		return reportedSource;
	}

	/**
	 * Sets the value of the reportedSource property.
	 * 
	 * @param value
	 *            allowed object is {@link ReportedSourceType }
	 * 
	 */
	public void setReportedSource(ReportedSourceType value) {
		this.reportedSource = value;
	}

	/**
	 * Gets the value of the resolution property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResolution() {
		return resolution;
	}

	/**
	 * Sets the value of the resolution property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResolution(String value) {
		this.resolution = value;
	}

	/**
	 * Gets the value of the resolutionCategoryTier1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResolutionCategoryTier1() {
		return resolutionCategoryTier1;
	}

	/**
	 * Sets the value of the resolutionCategoryTier1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResolutionCategoryTier1(String value) {
		this.resolutionCategoryTier1 = value;
	}

	/**
	 * Gets the value of the resolutionCategoryTier2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResolutionCategoryTier2() {
		return resolutionCategoryTier2;
	}

	/**
	 * Sets the value of the resolutionCategoryTier2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResolutionCategoryTier2(String value) {
		this.resolutionCategoryTier2 = value;
	}

	/**
	 * Gets the value of the resolutionCategoryTier3 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResolutionCategoryTier3() {
		return resolutionCategoryTier3;
	}

	/**
	 * Sets the value of the resolutionCategoryTier3 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResolutionCategoryTier3(String value) {
		this.resolutionCategoryTier3 = value;
	}

	/**
	 * Gets the value of the serviceType property.
	 * 
	 * @return possible object is {@link ServiceTypeType }
	 * 
	 */
	public ServiceTypeType getServiceType() {
		return serviceType;
	}

	/**
	 * Sets the value of the serviceType property.
	 * 
	 * @param value
	 *            allowed object is {@link ServiceTypeType }
	 * 
	 */
	public void setServiceType(ServiceTypeType value) {
		this.serviceType = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link StatusType }
	 * 
	 */
	public StatusType getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link StatusType }
	 * 
	 */
	public void setStatus(StatusType value) {
		this.status = value;
	}

	/**
	 * Gets the value of the action property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the value of the action property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAction(String value) {
		this.action = value;
	}

	/**
	 * Gets the value of the createRequest property.
	 * 
	 * @return possible object is {@link CreateRequestType }
	 * 
	 */
	public CreateRequestType getCreateRequest() {
		return createRequest;
	}

	/**
	 * Sets the value of the createRequest property.
	 * 
	 * @param value
	 *            allowed object is {@link CreateRequestType }
	 * 
	 */
	public void setCreateRequest(CreateRequestType value) {
		this.createRequest = value;
	}

	/**
	 * Gets the value of the summary property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Sets the value of the summary property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSummary(String value) {
		this.summary = value;
	}

	/**
	 * Gets the value of the notes property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the value of the notes property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNotes(String value) {
		this.notes = value;
	}

	/**
	 * Gets the value of the urgency property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUrgency() {
		return urgency;
	}

	/**
	 * Sets the value of the urgency property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUrgency(String value) {
		this.urgency = value;
	}

	/**
	 * Gets the value of the workInfoSummary property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWorkInfoSummary() {
		return workInfoSummary;
	}

	/**
	 * Sets the value of the workInfoSummary property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWorkInfoSummary(String value) {
		this.workInfoSummary = value;
	}

	/**
	 * Gets the value of the workInfoNotes property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWorkInfoNotes() {
		return workInfoNotes;
	}

	/**
	 * Sets the value of the workInfoNotes property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWorkInfoNotes(String value) {
		this.workInfoNotes = value;
	}

	/**
	 * Gets the value of the workInfoType property.
	 * 
	 * @return possible object is {@link WorkInfoTypeType }
	 * 
	 */
	public WorkInfoTypeType getWorkInfoType() {
		return workInfoType;
	}

	/**
	 * Sets the value of the workInfoType property.
	 * 
	 * @param value
	 *            allowed object is {@link WorkInfoTypeType }
	 * 
	 */
	public void setWorkInfoType(WorkInfoTypeType value) {
		this.workInfoType = value;
	}

	/**
	 * Gets the value of the workInfoDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getWorkInfoDate() {
		return workInfoDate;
	}

	/**
	 * Sets the value of the workInfoDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setWorkInfoDate(XMLGregorianCalendar value) {
		this.workInfoDate = value;
	}

	/**
	 * Gets the value of the workInfoSource property.
	 * 
	 * @return possible object is {@link WorkInfoSourceType }
	 * 
	 */
	public WorkInfoSourceType getWorkInfoSource() {
		return workInfoSource;
	}

	/**
	 * Sets the value of the workInfoSource property.
	 * 
	 * @param value
	 *            allowed object is {@link WorkInfoSourceType }
	 * 
	 */
	public void setWorkInfoSource(WorkInfoSourceType value) {
		this.workInfoSource = value;
	}

	/**
	 * Gets the value of the workInfoLocked property.
	 * 
	 * @return possible object is {@link CreateRequestType }
	 * 
	 */
	public CreateRequestType getWorkInfoLocked() {
		return workInfoLocked;
	}

	/**
	 * Sets the value of the workInfoLocked property.
	 * 
	 * @param value
	 *            allowed object is {@link CreateRequestType }
	 * 
	 */
	public void setWorkInfoLocked(CreateRequestType value) {
		this.workInfoLocked = value;
	}

	/**
	 * Gets the value of the workInfoViewAccess property.
	 * 
	 * @return possible object is {@link WorkInfoViewAccessType }
	 * 
	 */
	public WorkInfoViewAccessType getWorkInfoViewAccess() {
		return workInfoViewAccess;
	}

	/**
	 * Sets the value of the workInfoViewAccess property.
	 * 
	 * @param value
	 *            allowed object is {@link WorkInfoViewAccessType }
	 * 
	 */
	public void setWorkInfoViewAccess(WorkInfoViewAccessType value) {
		this.workInfoViewAccess = value;
	}

	/**
	 * Gets the value of the middleInitial property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMiddleInitial() {
		return middleInitial;
	}

	/**
	 * Sets the value of the middleInitial property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMiddleInitial(String value) {
		this.middleInitial = value;
	}

	/**
	 * Gets the value of the statusReason property.
	 * 
	 * @return possible object is {@link StatusReasonType }
	 * 
	 */
	public StatusReasonType getStatusReason() {
		return statusReason;
	}

	/**
	 * Sets the value of the statusReason property.
	 * 
	 * @param value
	 *            allowed object is {@link StatusReasonType }
	 * 
	 */
	public void setStatusReason(StatusReasonType value) {
		this.statusReason = value;
	}

	/**
	 * Gets the value of the directContactFirstName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDirectContactFirstName() {
		return directContactFirstName;
	}

	/**
	 * Sets the value of the directContactFirstName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDirectContactFirstName(String value) {
		this.directContactFirstName = value;
	}

	/**
	 * Gets the value of the directContactMiddleInitial property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDirectContactMiddleInitial() {
		return directContactMiddleInitial;
	}

	/**
	 * Sets the value of the directContactMiddleInitial property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDirectContactMiddleInitial(String value) {
		this.directContactMiddleInitial = value;
	}

	/**
	 * Gets the value of the directContactLastName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDirectContactLastName() {
		return directContactLastName;
	}

	/**
	 * Sets the value of the directContactLastName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDirectContactLastName(String value) {
		this.directContactLastName = value;
	}

	/**
	 * Gets the value of the templateID property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTemplateID() {
		return templateID;
	}

	/**
	 * Sets the value of the templateID property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTemplateID(String value) {
		this.templateID = value;
	}

	/**
	 * Gets the value of the serviceCI property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getServiceCI() {
		return serviceCI;
	}

	/**
	 * Sets the value of the serviceCI property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setServiceCI(String value) {
		this.serviceCI = value;
	}

	/**
	 * Gets the value of the serviceCIReconID property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getServiceCIReconID() {
		return serviceCIReconID;
	}

	/**
	 * Sets the value of the serviceCIReconID property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setServiceCIReconID(String value) {
		this.serviceCIReconID = value;
	}

	/**
	 * Gets the value of the hpdci property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHPDCI() {
		return hpdci;
	}

	/**
	 * Sets the value of the hpdci property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHPDCI(String value) {
		this.hpdci = value;
	}

	/**
	 * Gets the value of the hpdciReconID property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHPDCIReconID() {
		return hpdciReconID;
	}

	/**
	 * Sets the value of the hpdciReconID property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHPDCIReconID(String value) {
		this.hpdciReconID = value;
	}

	/**
	 * Gets the value of the hpdciFormName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHPDCIFormName() {
		return hpdciFormName;
	}

	/**
	 * Sets the value of the hpdciFormName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHPDCIFormName(String value) {
		this.hpdciFormName = value;
	}

	/**
	 * Gets the value of the workInfoAttachment1Name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWorkInfoAttachment1Name() {
		return workInfoAttachment1Name;
	}

	/**
	 * Sets the value of the workInfoAttachment1Name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWorkInfoAttachment1Name(String value) {
		this.workInfoAttachment1Name = value;
	}

	/**
	 * Gets the value of the workInfoAttachment1Data property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getWorkInfoAttachment1Data() {
		return workInfoAttachment1Data;
	}

	/**
	 * Sets the value of the workInfoAttachment1Data property.
	 * 
	 * @param value
	 *            allowed object is byte[]
	 */
	public void setWorkInfoAttachment1Data(byte[] value) {
		this.workInfoAttachment1Data = ((byte[]) value);
	}

	/**
	 * Gets the value of the workInfoAttachment1OrigSize property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getWorkInfoAttachment1OrigSize() {
		return workInfoAttachment1OrigSize;
	}

	/**
	 * Sets the value of the workInfoAttachment1OrigSize property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setWorkInfoAttachment1OrigSize(Integer value) {
		this.workInfoAttachment1OrigSize = value;
	}

	/**
	 * Gets the value of the loginID property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoginID() {
		return loginID;
	}

	/**
	 * Sets the value of the loginID property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoginID(String value) {
		this.loginID = value;
	}

	/**
	 * Gets the value of the customerCompany property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCustomerCompany() {
		return customerCompany;
	}

	/**
	 * Sets the value of the customerCompany property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCustomerCompany(String value) {
		this.customerCompany = value;
	}

	/**
	 * Gets the value of the corporateID property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCorporateID() {
		return corporateID;
	}

	/**
	 * Sets the value of the corporateID property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCorporateID(String value) {
		this.corporateID = value;
	}

}
