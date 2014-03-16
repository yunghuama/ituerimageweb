package com.csms.constants;

/**
 * CSMS sql 语句
 * @author cheney
 *
 */
public class CSMSSQLConstant {

	public static final String LIMIT = " limit ?,?";
	
	/**
	 * 类别管理
	 */
	public static final String CATEGORY_SAVE_SQL = "insert into t_category(id,name,enname,imageurl,superid,remark,type,createtime,updatetime) values(?,?,?,?,?,?,?,?,?)";
	public static final String CATEGORY_SELECT_BY_PAGE_SQL = "select c.* from t_category c where c.superid = ?";
	public static final String CATEGORY_SELECT_BY_TYPE_PAGE_SQL = "select c.* from t_category c where c.type = ? and c.superid = ?";
	public static final String CATEGORY_ROWCOUNT_SQL = "select count(c.id)";
	public static final String CATEGORY_SELECT_ALL_SQL = "select * from t_category";
	public static final String CATEGORY_SELECT_BY_ID = "select c.* from t_category c where id = ?";
	public static final String CATEGORY_DELETE_BY_IDS = "delete from t_category where id in ";
	public static final String CATEGORY_UPDATE_BY_ID_SQL = "update t_category set name = ?,enname=?,imageurl=?,superid=?,remark=?,type = ?,updatetime=? where id = ?";
	
	
	/**
	 * 图片管理
	 */
	public static final String IMAGE_SAVE_SQL = "insert into t_image(id,name,title,remark,path,tag,categoryid,createtime,updatetime,imagewidth,imageheight) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String IMAGE_SELECT_BY_PAGE_SQL = "select c.* from t_image c where c.categoryid = ? order by createtime desc";
	public static final String IMAGE_SELECT_BY_PAGE_TAG_SQL = "select c.* from t_image c where c.tag like ? order by createtime desc";
	public static final String IMAGE_ROWCOUNT_SQL = "select count(c.id)";
	public static final String IMAGE_SELECT_ALL_SQL = "select * from t_image";
	public static final String IMAGE_SELECT_BY_ID = "select c.* from t_image c where id = ?";
	public static final String IMAGE_DELETE_BY_IDS = "delete from t_image where id in ";
	public static final String IMAGE_UPDATE_BY_ID_SQL = "update t_image set name = ?,title=?,remark=?,path=?,tag=?,categoryid = ?,updatetime=? where id = ?";

	
	/**
	 * 活动管理
	 */
	public static final String EVENT_SAVE_SQL = "insert into t_event(id,content,creator,createtime) values(?,?,?,?)";
	public static final String EVENT_SELECT_BY_PAGE_SQL = "select c.*,u.realname from t_event c,users u where  c.creator = u.id ";
	public static final String EVENT_ROWCOUNT_SQL = "select count(c.id)";
	public static final String EVENT_SELECT_BY_ID = "select c.* from t_event c where id = ?";
	public static final String EVENT_DELETE_BY_IDS = "delete from t_event where id in ";
	public static final String EVENT_UPDATE_BY_ID_SQL = "update t_event set content = ? where id = ?";

	/**
	 * 考核管理
	 */
	public static final String KPI_SAVE_SQL = "insert into t_kpi(id,content,creator,createtime) values(?,?,?,?)";
	public static final String KPI_SELECT_BY_PAGE_SQL = "select c.*,u.realname from t_kpi c,users u where  c.creator = u.id ";
	public static final String KPI_ROWCOUNT_SQL = "select count(c.id)";
	public static final String KPI_SELECT_BY_ID = "select c.* from t_kpi c where id = ?";
	public static final String KPI_DELETE_BY_IDS = "delete from t_kpi where id in ";
	public static final String KPI_UPDATE_BY_ID_SQL = "update t_kpi set content = ? where id = ?";

	/**
	 * 意见反馈
	 */
	public static final String OPINION_SAVE_SQL = "insert into t_opinion(id,content,number,creator,createtime) values(?,?,?,?,?)";
	public static final String OPINION_SELECT_BY_PAGE_SQL = "select c.*,u.realname from t_opinion c,users u where  c.creator = u.id ";
	public static final String OPINION_ROWCOUNT_SQL = "select count(c.id)";
	public static final String OPINION_SELECT_BY_ID = "select c.* from t_opinion c where id = ?";
	public static final String OPINION_DELETE_BY_IDS = "delete from t_opinion where id in ";
	public static final String OPINION_UPDATE_BY_ID_SQL = "update t_opinion set content = ?,number = ? where id = ?";
	
	
	
	
	/**
	 * 策略管理
	 */
	public static final String RULE_SELECT_BY_PAGE_SQL = "select r.*, g.id as groupid, g.name as groupname,c.content as contentname from sms_group g left join sms_rule r on g.id = r.smsgroup left join t_message c on r.content = c.id where g.department = ?  order by g.type";
	public static final String RULE_SELECT_ALL_SQL = "select r.* from sms_rule r,users u where r.department = ? and r.creator = u.id ";
	public static final String RULE_SELECT_BY_ID = "select * from sms_rule where id = ?";
	public static final String RULE_SELECT_BY_GROUP_ID = "select * from sms_rule where smsgroup = ?";
	public static final String RULE_SAVE_SQL = "insert into sms_rule(id,name,ruleday,rulestarttime,ruleendtime,content,department,state,creator,createtime,timetype,type,smsgroup) value(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String RULE_UPDATE_SQL = "update sms_rule set name =? ,ruleday = ?, rulestarttime = ?,ruleendtime = ?,content = ?,state = ?,timetype=?,type=? where smsgroup = ?";
	public static final String RULE_DELETE_BY_IDS_SQL = "delete from sms_rule where id in ";
	public static final String RULE_DELETE_BY_GROUP_SQL = "delete from sms_rule where smsgroup = ?";
	public static final String RULE_UPDATE_STATE_SQL = "update sms_rule set state = ? where smsgroup = ?";
	public static final String RULE_ROWCOUNT_SQL = "select count(r.id)";
	public static final String PRERULE_SELECT_BY_RULE_SQL = "select pr.*,sc.content as contentname from sms_previousrule pr,t_message sc where pr.rule = ? and pr.content = sc.id ";
	public static final String PRERULE_SAVE_SQL = "insert into sms_previousrule(id,name,executedate,rule,content,creator,createtime) value(?,?,?,?,?,?,?)";
	public static final String PRERULE_DELETE_SQL = "delete from sms_previousrule where id in ";
	public static final String GLORULE_SELECT_SQL = "select gr.*,u.realname,c.content as contentname from sms_globalrule gr,users u,t_message c  where gr.department = ? and gr.creator = u.id and gr.content = c.id";
	public static final String GLORULE_SELECT_BY_ID = "select * from sms_globalrule where id = ?";
	public static final String GLORULE_COUNT_SQL = "select count(id) from sms_globalrule where department = ?";
	public static final String GLORULE_SAVE_SQL = "insert into sms_globalrule(id,name,content,department,state,creator,createtime) values(?,?,?,?,?,?,?)";
	public static final String GLORULE_UPDATE_SQL = "update sms_globalrule set name = ?,content = ? ,state = ? where id = ?";
	public static final String GLORULE_UPDATE_STATE = "update sms_globalrule set state = ? where id = ?";
	public static final String GLORULE_DELETE_BY_IDS_SQL = "delete from sms_globalrule where id in ";
	
	/**
	 * 组群管理
	 */
	public static final String GROUP_SELECT_BY_PAGE_SQL = "select g.*,u.realname,r.name as rulename from users u, sms_group g left join sms_rule r on g.rule = r.id where g.department = ? and  g.creator = u.id ";
	public static final String GROUP_SAVE_SQL = "insert into sms_group(id,name,rule,department,remark,type,creator,createtime) values(?,?,?,?,?,?,?,?)";
	public static final String GROUP_UPDATE_SQL = "update sms_group set name=?,rule=?,remark=? where id = ?";
	public static final String GROUP_DELETE_BY_IDS_SQL = "delete from sms_group where type='1' and id in ";
	public static final String GROUP_DELETE_BY_ID_SQL = "delete from sms_group where type='1' and id = ?";
	public static final String GROUP_SELECT_BY_ID = "select * from sms_group where id = ?";
	public static final String GROUP_SELECT_ALL_SQL = "select g.* from sms_group g";
	public static final String GROUP_ROWCOUNT_SQL = "select count(g.id) ";
	public static final String GROUP_DEFAULT_SELECT = "select * from sms_group where type = '0'";
	public static final String GROUP_SELECT_NAME_DEPART = "select * from sms_group where name=? and department = ?";
	
	
	/**
	 * 号码管理
	 */
	public static final String NUMBER_SELECT_BY_PAGE_DEP_SQL = "select n.*,g.name as groupname from sms_number n  left join sms_group as g on n.smsgroup = g.id ";
	public static final String NUMBER_SELECT_BY_PAGE_GROUP_SQL = "select n.*,g.name as groupname from sms_number n  , sms_group as g where n.smsgroup = g.id and n.smsgroup = ? ";
	public static final String NUMBER_SAVE_BY_GROUP_SQL = "insert into sms_number(id,number,smsgroup,department,remark,name,creator,createtime) values(?,?,?,?,?,?,?,?)";
	public static final String NUMBER_SELECT_BY_ID_SQL = "select n.* from sms_number n where id = ?";
	public static final String NUMBER_UPDATE_ALL_SQL = "update sms_number set number = ?,smsgroup = ?,remark = ?,name=? where id = ?";
	public static final String NUMBER_UPDATE_GROUP_SQL = "update sms_number set smsgroup = ? where id = ?";
	public static final String NUMBER_UPDATE_GROUP_NAME_SQL = "update sms_number set smsgroup = ?,remark = ?,name=? where id = ?";
	public static final String NUMBER_ROWCOUNT_SQL = "select count(n.id)";
	public static final String NUMBER_DELETE_BY_IDS_SQL = "delete from sms_number where id in ";
	public static final String NUMBER_SELECT_ALL_SQL = "select n.* from sms_number n where n.department = ?";
	public static final String NUMBER_SELECT_COUNT_SQL = "select count(id) from sms_number where number = ?";
	public static final String NUMBER_UPDATE_TODEFAULT_GROUP = "update sms_number set smsgroup = ? where smsgroup = ?";
	public static final String NUMBER_UPDATE_GROUP_NAME_REMARK = "update sms_number set smsgroup =? ,name = ? ,remark = ? where number = ? and department = ?";

    /**
     * 报警管理
     */
    public static final String WARN_SAVE_SQL = "insert into sms_warn(id,cpu,memusage,storage,memtotal,createtime) values(?,?,?,?,?,?)";
    public static final String WARN_SELECT_SQL = "select * from sms_warn order by createtime desc limit ?,?";


    /**
     * 集团管理
     */
    public static final String ENTERPRISE_SELECT_BY_PAGE_SQL = "select e.* from sms_enterprise e ";
    public static final String ENTERPRISE_SELECT_BY_PAGE_DISTRICT_SQL = "select e.* from sms_enterprise e where district = ?";
    public static final String ENTERPRISE_ROWCOUNT_SQL = "select count(e.id)";
    public static final String ENTERPRISE_SAVE_SQL = "insert into sms_enterprise(id,name,district,number,code,createtime) values(?,?,?,?,?,?)";
    public static final String ENTERPRISE_DELETE_BY_IDS_SQL = "delete from sms_enterprise where id in ";
    public static final String ENTERPRISE_UPDATE_SQL = "update sms_enterprise set name = ?,district = ?,number = ?,code = ? where id = ?";
    public static final String ENTERPRISE_SELECT_BY_ID = "select * from sms_enterprise where id = ?";
    
    
    /**
     * 区域管理
     */
    public static final String DISTRICT_SELECT_ALL = "select d.* from sms_district d";
    public static final String DISTRICT_SELECT_ALL_BY_PARENT = "select d.* from sms_district d where parentId = ?";
    public static final String DISTRICT_ROWCOUNT_SQL = "select count(d.id) ";
    public static final String DISTRICT_SAVE_SQL = "insert into sms_district(id,name,parentid,createtime) values(?,?,?,?)";
    public static final String DISTRICT_UPDATE_SQL = "update sms_district set name = ?, parentid = ? where id = ?";
    public static final String DISTRICT_DELETE_BY_IDS = "delete from sms_district where id in ";
    public static final String DISTRICT_SELECT_BY_ID = "select * from sms_district where id = ?";
    public static final String DISTRICT_SELECT_BY_PARENT_ID = "select * from sms_district where parentid = ?";
    public static final String DISTRICT_SELECT_BY_TYPE = "select * from sms_district where type = ?";
    
    
    /**
     * 短信处理sql
     */
    public static final String SSI_SELECT_BY_STATE_PAGE = "select * from tb_ssi where state = ? limit ?,?";
    public static final String SSI_SELECT_BY_INCREASE_ID_PAGE = "select * from tb_ssi where sid > ? limit ?,?";
    public static final String SSI_SELECT_BY_STAT = "select * from tb_ssi where state = ?";
    public static final String SSI_SELECT_BY_INCREASE_ID = "select * from tb_ssi where sid > ?";
    public static final String SSI_DELETE_BY_STATE = "delete from tb_ssi where state = ?";
    public static final String SSI_RSP_SAVE = "insert into tb_ssi_rsp(active_service_flag,Divert_ServiceV_ton,Divert_ServiceV_npi,Divert_ServiceV_address,Divert_ServiceV_operatorID,Divert_ServiceV_nettype,New_SM_ContentV,new_data_codingV,STATE,CREATEDATE) values(?,?,?,?,?,?,?,?,?,?)";
    
    
    /**
     * 查询短信处理规则
     */
    public static final String SMS_RULE_QUERY = "SELECT snumber.number,srule.ruleDay,srule.timeType,scontent.content FROM sms_number snumber,t_message scontent,sms_rule srule where snumber.smsgroup = srule.smsgroup and srule.content = scontent.id";
  
}
