package com.weavernorth.OA2archives.bean;
/**
 * 
 * 类名:ArchivesBean
 * 类描述:文档保存信息的实体类
 * Company:weavernorth
 * @author:刘立华
 * @date: 2017-11-7下午12:55:58
 */
public class ArchivesBean {
	//请求id
	private String requestid = "";
	//文件名（不带后缀）
	private String object_name = "";
	//ftp路径
	private String file_path = "";
	//文件类型（固定pdf）
	private String file_format = "";
	//流程--标题
	private String c_chinese_title = "";
	//英文标题
	private String c_english_title = "";


	//文件类别
	private String c_doc_type = "";
	//编制部门
	private  String c_compile_dept = "";
	//编制日期
	private String  c_compile_date = "";
	//编制人
	private String  c_compiler = "";
	//发文日期
	private String  c_issue_date = "";
	//文号
	private String c_issue_num = "";
	//发文机关
	private String c_issue_dept = "";
	//主送单位
	private String c_target_dept = "";
	//

	//流程--合同号
	private String c_doc_code = "";
	//流程--合同类别
	private String c_contract_type = "";
	//合同年度
	private String c_year = "";
	//固定值：SSTPC
	private String c_party_a = "";
	//流程--合同乙方
	private String c_party_b = "";
	//发起人部门
	private String c_resp_dept = "";
	//合并后PDF页数
	private String c_page_counts = "";
	//流程--归档日期
	private String c_archive_date = "";
	//流程--归档人
	private String c_archive_owne = "";
	//归档部门
	private  String  c_archive_org = "";
	//每个excel单独流水
	private String c_inner_sequence = "";

	public void setC_doc_type(String c_doc_type) {
		this.c_doc_type = c_doc_type;
	}

	public void setC_compile_dept(String c_compile_dept) {
		this.c_compile_dept = c_compile_dept;
	}

	public void setC_compile_date(String c_compile_date) {
		this.c_compile_date = c_compile_date;
	}

	public void setC_compiler(String c_compiler) {
		this.c_compiler = c_compiler;
	}

	public void setC_issue_date(String c_issue_date) {
		this.c_issue_date = c_issue_date;
	}

	public void setC_issue_num(String c_issue_num) {
		this.c_issue_num = c_issue_num;
	}

	public void setC_issue_dept(String c_issue_dept) {
		this.c_issue_dept = c_issue_dept;
	}

	public void setC_target_dept(String c_target_dept) {
		this.c_target_dept = c_target_dept;
	}

	public void setC_archive_org(String c_archive_org) {
		this.c_archive_org = c_archive_org;
	}

	public String getC_doc_type() {

		return c_doc_type;
	}

	public String getC_compile_dept() {
		return c_compile_dept;
	}

	public String getC_compile_date() {
		return c_compile_date;
	}

	public String getC_compiler() {
		return c_compiler;
	}

	public String getC_issue_date() {
		return c_issue_date;
	}

	public String getC_issue_num() {
		return c_issue_num;
	}

	public String getC_issue_dept() {
		return c_issue_dept;
	}

	public String getC_target_dept() {
		return c_target_dept;
	}

	public String getC_archive_org() {
		return c_archive_org;
	}

	//类型id
	private String typeid = "";
	//pdf保存是否成功 0是  1否
	private String saveStatus = "";
	//pdf上传是否 成功  0 是  1否  2 未上传
	private String uploadStatus = "";
	private String excelmode_path = "";
	public String getExcelmode_path() {
		return excelmode_path;
	}
	public void setExcelmode_path(String excelmode_path) {
		this.excelmode_path = excelmode_path;
	}
	public String getNewexcel_path() {
		return newexcel_path;
	}
	public void setNewexcel_path(String newexcel_path) {
		this.newexcel_path = newexcel_path;
	}
	//写入数据后的excel保存的服务器路径
	private String newexcel_path = "";
	public String getSaveStatus() {
		return saveStatus;
	}
	public void setSaveStatus(String saveStatus) {
		this.saveStatus = saveStatus;
	}
	public String getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}
	public String getObject_name() {
		return object_name;
	}
	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_format() {
		return file_format;
	}
	public void setFile_format(String file_format) {
		this.file_format = file_format;
	}
	public String getC_chinese_title() {
		return c_chinese_title;
	}
	public void setC_chinese_title(String c_chinese_title) {
		this.c_chinese_title = c_chinese_title;
	}
	public String getC_english_title() {
		return c_english_title;
	}
	public void setC_english_title(String c_english_title) {
		this.c_english_title = c_english_title;
	}
	public String getC_doc_code() {
		return c_doc_code;
	}
	public void setC_doc_code(String c_doc_code) {
		this.c_doc_code = c_doc_code;
	}
	public String getC_contract_type() {
		return c_contract_type;
	}
	public void setC_contract_type(String c_contract_type) {
		this.c_contract_type = c_contract_type;
	}
	public String getC_year() {
		return c_year;
	}
	public void setC_year(String c_year) {
		this.c_year = c_year;
	}
	public String getC_party_a() {
		return c_party_a;
	}
	public void setC_party_a(String c_party_a) {
		this.c_party_a = c_party_a;
	}
	public String getC_party_b() {
		return c_party_b;
	}
	public void setC_party_b(String c_party_b) {
		this.c_party_b = c_party_b;
	}
	public String getC_resp_dept() {
		return c_resp_dept;
	}
	public void setC_resp_dept(String c_resp_dept) {
		this.c_resp_dept = c_resp_dept;
	}
	public String getC_page_counts() {
		return c_page_counts;
	}
	public void setC_page_counts(String c_page_counts) {
		this.c_page_counts = c_page_counts;
	}
	public String getC_archive_date() {
		return c_archive_date;
	}
	public void setC_archive_date(String c_archive_date) {
		this.c_archive_date = c_archive_date;
	}
	public String getC_archive_owne() {
		return c_archive_owne;
	}
	public void setC_archive_owne(String c_archive_owne) {
		this.c_archive_owne = c_archive_owne;
	}
	public String getC_inner_sequence() {
		return c_inner_sequence;
	}
	public void setC_inner_sequence(String c_inner_sequence) {
		this.c_inner_sequence = c_inner_sequence;
	}
	@Override
	public String toString() {
		return "ArchivesBean [requestid=" + requestid + ", object_name="
				+ object_name + ", file_path=" + file_path + ", file_format="
				+ file_format + ", c_chinese_title=" + c_chinese_title
				+ ", c_doc_code=" + c_doc_code + ", c_contract_type="
				+ c_contract_type + ", c_year=" + c_year + ", c_party_a="
				+ c_party_a + ", c_party_b=" + c_party_b + ", c_resp_dept="
				+ c_resp_dept + ", c_page_counts=" + c_page_counts
				+ ", c_archive_date=" + c_archive_date + ", c_archive_owne="
				+ c_archive_owne + ", c_inner_sequence=" + c_inner_sequence
				+ ", typeid=" + typeid + ", getTypeid()=" + getTypeid()
				+ ", getRequestid()=" + getRequestid() + ", getObject_name()="
				+ getObject_name() + ", getFile_path()=" + getFile_path()
				+ ", getFile_format()=" + getFile_format()
				+ ", getC_chinese_title()=" + getC_chinese_title()
				+ ", getC_doc_code()=" + getC_doc_code()
				+ ", getC_contract_type()=" + getC_contract_type()
				+ ", getC_year()=" + getC_year() + ", getC_party_a()="
				+ getC_party_a() + ", getC_party_b()=" + getC_party_b()
				+ ", getC_resp_dept()=" + getC_resp_dept()
				+ ", getC_page_counts()=" + getC_page_counts()
				+ ", getC_archive_date()=" + getC_archive_date()
				+ ", getC_archive_owne()=" + getC_archive_owne()
				+ ", getC_inner_sequence()=" + getC_inner_sequence()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
