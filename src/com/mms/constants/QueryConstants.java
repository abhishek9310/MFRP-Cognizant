package com.mms.constants;

public class QueryConstants {
	public static final String GETADMIN="select password from admin where username=?";
	public static final String GET_COUNTRIES = "select * from country_master";
	public static final String GET_STATES = "select * from state_master";
	public static final String GET_ID_DOCUMENTS = "select * from identification_master";
	public static final String SET_BRANCH_ADMIN="insert into branch_admin_info (first_name,last_name,address,state_id,email,password,gender,marital_status,contact_no,status_id,date_of_birth,identification_id) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_STATUS_ID="select id from status_master where min_age <= ? and max_age >= ?";
	public static final String SET_MEDICINE="insert into medicine (id,medicine_name,dosage_value,dosage_unit,price_of_strip,description,requested_strips,medicines_in_strip,manufacture_date,expiry_date) values(?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_LAST_MEDICINE_ID="SELECT MAX(id) as id FROM medicine";
	public static final String GET_UNASIGNED_BRANCHES="select id,branch_name from branch where branch_admin_id is null";
	public static final String GET_LAST_BRANCH_ADMIN_ID="select max(id) as id from branch_admin_info";
	public static final String SET_BRANCH_ADMIN_IN_BRANCH="update branch set branch_admin_id = ? where id in (?)";
	public static final String SEARCH_MEDICINE="select * from medicine where id= ? or medicine_name like ? ";
	public static final String GET_ALL_MEDICINE="select * from medicine";
	public static final String GET_MEDICINE_BY_ID="select * from medicine where id = ?";
	public static final String GET_BRANCH_BY_ADMIN_ID="select id,branch_name from branch where branch_admin_id = ?";
	public static final String GET_BRANCH_ADMIN_BY_ID="select password from branch_admin_info where id =?";
	public static final String ADD_BRANCH_STOCK="insert into branch_stock_detail (id,medicine_id,branch_admin_id,branch_id,number_of_strips,description) values(?,?,?,?,?,?)";
	public static final String GET_LAST_STOCK_ID="select max(id) as id  from branch_stock_detail";
	public static final String GET_LAST_MEDICINE_REQUEST_ID="SELECT MAX(id) as id from medicine_request";
	public static final String Add_MEDICINE_REQUEST="insert into medicine_request (id,medicine_id,no_of_strips,branch_id,branch_admin_id,status) values (?,?,?,?,?,?)";
	public static final String GET_REQUEST_BY_BRANCH_ADMIN="select mr.*,b.branch_name,m.medicine_name,m.dosage_value,m.dosage_unit "
			+ "from medicine_request as mr "
			+ "join branch as b on mr.branch_id=b.id "
			+ "join medicine as m on mr.medicine_id = m.id "
			+ "where mr.branch_admin_id = ? and mr.status=?";
	public static final String VALIDATE_STATE="select * from state_master where id=? and country_id=?";
	public static final String VALIDATE_ID_DOCUMENT="select * from identification_master where id=?";
	public static final String GET_BRANCHES_BY_IDS="select * from branch where id in (?)";
	public static final String CANCEL_REQUEST="update medicine_request set status='Cancelled' where id=? and branch_admin_id=? and status='Pending'";
	public static final String GET_PENDING_REQUEST_BY_BRANCH_ADMIN_ID_AND_REQUEST_ID="select * from medicine_request where id=? and branch_admin_id=? and status='Pending'";
	public static final String GET_ALL_REQUEST_BY_BRANCH_ADMIN="select mr.*,b.branch_name,m.medicine_name,m.dosage_value,m.dosage_unit "
			+ "from medicine_request as mr "
			+ "join branch as b on mr.branch_id=b.id "
			+ "join medicine as m on mr.medicine_id = m.id "
			+ "where mr.branch_admin_id = ?";
	public static final String GET_BRANCH_BY_ADMIN_ID_AND_BRANCH_ID="select * from branch where id=? and branch_admin_id=?";
	public static final String GET_STOCK_BY_BRANCH_ADMIN="select bsd.*,b.branch_name,m.medicine_name,m.dosage_value,m.dosage_unit "
			+ "from branch_stock_detail as bsd "
			+ "join branch as b on b.id = bsd.branch_id "
			+ "join medicine as m on m.id= bsd.medicine_id where bsd.branch_admin_id = ?";
	public static final String GET_ALL_BRANCH_ADMIN="select * from branch_admin_info";
	public static final String GET_ALL_REQUESTS_BY_STATUS="select mr.*,b.branch_name,m.medicine_name,m.dosage_value,m.dosage_unit "
		+ "from medicine_request as mr "
		+ "join branch as b on mr.branch_id=b.id "
		+ "join medicine as m on mr.medicine_id = m.id "
		+ "where mr.status=?"; 
	public static final String APPROVE_REQUEST="update medicine_request set status='Approved' where id=? and status='Pending'";
	public static final String REJECT_REQUEST="update medicine_request set status='Rejected' where id=? and status='Pending'";
	public static final String GET_PENDING_REQUEST_BY_ID="select * from medicine_request where id=? and status='Pending'";
	public static final String GET_REQUEST_DETAIL_BY_ID="select * from medicine_request where id=?";
	public static final String GET_REQUESTED_MEDICINE_COUNT="select requested_strips from medicine where id=?";
	public static final String UPDATE_MEDICINE_REQUESTED_COUNT="update medicine set requested_strips=? where id=?";
}
