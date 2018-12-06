/**  
 * Project Name:JRJGService  
 * File Name:IDC.java  
 * Package Name:com.ffjr.commons.utils  
 * Date:2018年4月13日上午10:52:52  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/

package com.zhuqifeng.commons.utils.card;

/**  
 * ClassName:IDC <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2018年4月13日 上午10:52:52 <br/>  
 * @author   QiFeng.Zhu  
 * @version    1.0
 * @since    JDK 1.8  
 * @see        
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zhuqifeng.commons.utils.base.StringUtils;

/**
 * 身份证工具类
 * 
 * @author June
 * @version 1.0, 2010-06-17
 */
public class IDCardUtil extends StringUtils {

	/** 中国公民身份证号码最小长度。 */
	public static final int CHINA_ID_MIN_LENGTH = 15;

	/** 中国公民身份证号码最大长度。 */
	public static final int CHINA_ID_MAX_LENGTH = 18;

	/** 省、直辖市代码表 */
	public static final String cityCode[] = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
			"64", "65", "71", "81", "82", "91" };

	/** 每位加权因子 */
	public static final int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	/** 第18位校检码 */
	public static final String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
	/** 最低年限 */
	public static final int MIN = 1930;
	public static Map<String, String> cityCodes = new HashMap<String, String>();
	/** 台湾身份首字母对应数字 */
	public static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();
	/** 香港身份首字母对应数字 */
	public static Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();
	static {
		cityCodes.put("11", "北京市");
		cityCodes.put("1101", "北京市市辖区");
		cityCodes.put("110101", "北京市东城区");
		cityCodes.put("110102", "北京市西城区");
		cityCodes.put("110103", "北京市崇文区");
		cityCodes.put("110104", "北京市宣武区");
		cityCodes.put("110105", "北京市朝阳区");
		cityCodes.put("110106", "北京市丰台区");
		cityCodes.put("110107", "北京市石景山区");
		cityCodes.put("110108", "北京市海淀区");
		cityCodes.put("110109", "北京市门头沟区");
		cityCodes.put("110111", "北京市房山区");
		cityCodes.put("1102", "北京市市辖县");
		cityCodes.put("110221", "北京市昌平县");
		cityCodes.put("110222", "北京市顺义县");
		cityCodes.put("110223", "北京市通县");
		cityCodes.put("110224", "北京市大兴县");
		cityCodes.put("110226", "北京市平谷县");
		cityCodes.put("110227", "北京市怀柔县");
		cityCodes.put("110228", "北京市密云县");
		cityCodes.put("110229", "北京市延庆县");
		cityCodes.put("12", "天津市");
		cityCodes.put("1201", "天津市市辖区");
		cityCodes.put("120101", "天津市和平区");
		cityCodes.put("120102", "天津市河东区");
		cityCodes.put("120103", "天津市河西区");
		cityCodes.put("120104", "天津市南开区");
		cityCodes.put("120105", "天津市河北区");
		cityCodes.put("120106", "天津市红桥区");
		cityCodes.put("120107", "天津市塘沽区");
		cityCodes.put("120108", "天津市汉沽区");
		cityCodes.put("120109", "天津市大港区");
		cityCodes.put("120110", "天津市东丽区");
		cityCodes.put("120111", "天津市西青区");
		cityCodes.put("120112", "天津市津南区");
		cityCodes.put("120113", "天津市北辰区");
		cityCodes.put("1202", "天津市市辖县");
		cityCodes.put("120221", "天津市宁河县");
		cityCodes.put("120222", "天津市武清县");
		cityCodes.put("120223", "天津市静海县");
		cityCodes.put("120224", "天津市宝坻县");
		cityCodes.put("120225", "天津市蓟县");
		cityCodes.put("13", "河北省");
		cityCodes.put("1301", "河北省石家庄市");
		cityCodes.put("130101", "河北省石家庄市市辖区");
		cityCodes.put("130102", "河北省石家庄市长安区");
		cityCodes.put("130103", "河北省石家庄市桥东区");
		cityCodes.put("130104", "河北省石家庄市桥西区");
		cityCodes.put("130105", "河北省石家庄市新华区");
		cityCodes.put("130106", "河北省石家庄市郊区");
		cityCodes.put("130107", "河北省石家庄市井陉矿区");
		cityCodes.put("130121", "河北省井陉县");
		cityCodes.put("130122", "河北省获鹿县");
		cityCodes.put("130123", "河北省正定县");
		cityCodes.put("130124", "河北省栾城县");
		cityCodes.put("130125", "河北省行唐县");
		cityCodes.put("130126", "河北省灵寿县");
		cityCodes.put("130127", "河北省高邑县");
		cityCodes.put("130128", "河北省深泽县");
		cityCodes.put("130129", "河北省赞皇县");
		cityCodes.put("130130", "河北省无极县");
		cityCodes.put("130131", "河北省平山县");
		cityCodes.put("130132", "河北省元氏县");
		cityCodes.put("130133", "河北省赵县");
		cityCodes.put("130181", "河北省辛集市");
		cityCodes.put("130182", "河北省藁城市");
		cityCodes.put("130183", "河北省晋州市");
		cityCodes.put("130184", "河北省新乐市");
		cityCodes.put("1302", "河北省唐山市");
		cityCodes.put("130201", "河北省唐山市市辖区");
		cityCodes.put("130202", "河北省唐山市路南区");
		cityCodes.put("130203", "河北省唐山市路北区");
		cityCodes.put("130204", "河北省唐山市东矿区");
		cityCodes.put("130205", "河北省唐山市开平区");
		cityCodes.put("130206", "河北省唐山市新区");
		cityCodes.put("130221", "河北省丰润县");
		cityCodes.put("130222", "河北省丰南县");
		cityCodes.put("130223", "河北省滦县");
		cityCodes.put("130224", "河北省滦南县");
		cityCodes.put("130225", "河北省乐亭县");
		cityCodes.put("130226", "河北省迁安县");
		cityCodes.put("130227", "河北省迁西县");
		cityCodes.put("130229", "河北省玉田县");
		cityCodes.put("130230", "河北省唐海县");
		cityCodes.put("130281", "河北省遵化市");
		cityCodes.put("1303", "河北省秦皇岛市");
		cityCodes.put("130301", "河北省秦皇岛市市辖区");
		cityCodes.put("130302", "河北省秦皇岛市海港区");
		cityCodes.put("130303", "河北省秦皇岛市山海关区");
		cityCodes.put("130304", "河北省秦皇岛市北戴河区");
		cityCodes.put("130321", "河北省青龙满族自治县");
		cityCodes.put("130322", "河北省昌黎县");
		cityCodes.put("130323", "河北省抚宁县");
		cityCodes.put("130324", "河北省卢龙县");
		cityCodes.put("1304", "河北省邯郸市");
		cityCodes.put("130401", "河北省邯郸市市辖区");
		cityCodes.put("130402", "河北省邯郸市邯山区");
		cityCodes.put("130403", "河北省邯郸市丛台区");
		cityCodes.put("130404", "河北省邯郸市复兴区");
		cityCodes.put("130406", "河北省邯郸市峰峰矿区");
		cityCodes.put("130421", "河北省邯郸县");
		cityCodes.put("130423", "河北省临漳县");
		cityCodes.put("130424", "河北省成安县");
		cityCodes.put("130425", "河北省大名县");
		cityCodes.put("130426", "河北省涉县");
		cityCodes.put("130427", "河北省磁县");
		cityCodes.put("130428", "河北省肥乡县");
		cityCodes.put("130429", "河北省永年县");
		cityCodes.put("130430", "河北省丘县");
		cityCodes.put("130431", "河北省鸡泽县");
		cityCodes.put("130432", "河北省广平县");
		cityCodes.put("130433", "河北省馆陶县");
		cityCodes.put("130434", "河北省魏县");
		cityCodes.put("130435", "河北省曲周县");
		cityCodes.put("130481", "河北省武安市");
		cityCodes.put("1305", "河北省邢台市");
		cityCodes.put("130501", "河北省邢台市市辖区");
		cityCodes.put("130502", "河北省邢台市桥东区");
		cityCodes.put("130503", "河北省邢台市桥西区");
		cityCodes.put("130521", "河北省邢台县");
		cityCodes.put("130522", "河北省临城县");
		cityCodes.put("130523", "河北省内丘县");
		cityCodes.put("130524", "河北省柏乡县");
		cityCodes.put("130525", "河北省隆尧县");
		cityCodes.put("130526", "河北省任县");
		cityCodes.put("130527", "河北省南和县");
		cityCodes.put("130528", "河北省宁晋县");
		cityCodes.put("130529", "河北省巨鹿县");
		cityCodes.put("130530", "河北省新河县");
		cityCodes.put("130531", "河北省广宗县");
		cityCodes.put("130532", "河北省平乡县");
		cityCodes.put("130533", "河北省威县");
		cityCodes.put("130534", "河北省清河县");
		cityCodes.put("130535", "河北省临西县");
		cityCodes.put("130581", "河北省南宫市");
		cityCodes.put("130582", "河北省沙河市");
		cityCodes.put("1306", "河北省保定市");
		cityCodes.put("130601", "河北省保定市市辖区");
		cityCodes.put("130602", "河北省保定市新市区");
		cityCodes.put("130603", "河北省保定市北市区");
		cityCodes.put("130604", "河北省保定市南市区");
		cityCodes.put("130621", "河北省满城县");
		cityCodes.put("130622", "河北省清苑县");
		cityCodes.put("1307", "河北省张家口市");
		cityCodes.put("130701", "河北省张家口市市辖区");
		cityCodes.put("130702", "河北省张家口市桥东区");
		cityCodes.put("130703", "河北省张家口市桥西区");
		cityCodes.put("130705", "河北省张家口市宣化区");
		cityCodes.put("130706", "河北省张家口市下花园区");
		cityCodes.put("130721", "河北省宣化县");
		cityCodes.put("130722", "河北省张北县");
		cityCodes.put("130723", "河北省康保县");
		cityCodes.put("130724", "河北省沽源县");
		cityCodes.put("130725", "河北省尚义县");
		cityCodes.put("130726", "河北省蔚县");
		cityCodes.put("130727", "河北省阳原县");
		cityCodes.put("130728", "河北省怀安县");
		cityCodes.put("130729", "河北省万全县");
		cityCodes.put("130730", "河北省怀来县");
		cityCodes.put("130731", "河北省涿鹿县");
		cityCodes.put("130732", "河北省赤城县");
		cityCodes.put("130733", "河北省崇礼县");
		cityCodes.put("1308", "河北省承德市");
		cityCodes.put("130801", "河北省承德市市辖区");
		cityCodes.put("130802", "河北省承德市双桥区");
		cityCodes.put("130803", "河北省承德市双滦区");
		cityCodes.put("130804", "河北省承德市鹰手营子矿区");
		cityCodes.put("130821", "河北省承德县");
		cityCodes.put("130822", "河北省兴隆县");
		cityCodes.put("130823", "河北省平泉县");
		cityCodes.put("130824", "河北省滦平县");
		cityCodes.put("130825", "河北省隆化县");
		cityCodes.put("130826", "河北省丰宁满族自治县");
		cityCodes.put("130827", "河北省宽城满族自治县");
		cityCodes.put("130828", "河北省围场满族蒙古族自治县");
		cityCodes.put("1309", "河北省沧洲市");
		cityCodes.put("130901", "河北省沧洲市市辖区");
		cityCodes.put("130902", "河北省沧洲市新华区");
		cityCodes.put("130903", "河北省沧洲市运河区");
		cityCodes.put("130904", "河北省沧洲市郊区");
		cityCodes.put("130921", "河北省沧县");
		cityCodes.put("130922", "河北省青县");
		cityCodes.put("130923", "河北省东光县");
		cityCodes.put("130924", "河北省海兴县");
		cityCodes.put("130925", "河北省盐山县");
		cityCodes.put("130926", "河北省肃宁县");
		cityCodes.put("130927", "河北省南皮县");
		cityCodes.put("130928", "河北省吴桥县");
		cityCodes.put("130929", "河北省献县");
		cityCodes.put("130930", "河北省孟村回族自治县");
		cityCodes.put("130981", "河北省泊头市");
		cityCodes.put("130982", "河北省任丘市");
		cityCodes.put("130983", "河北省黄骅市");
		cityCodes.put("130984", "河北省河间市");
		cityCodes.put("1310", "河北省廊坊市");
		cityCodes.put("131001", "河北省廊坊市市辖区");
		cityCodes.put("131002", "河北省廊坊市安次区");
		cityCodes.put("131022", "河北省固安县");
		cityCodes.put("131023", "河北省永清县");
		cityCodes.put("131024", "河北省香河县");
		cityCodes.put("131025", "河北省大城县");
		cityCodes.put("131026", "河北省文安县");
		cityCodes.put("131028", "河北省大厂回族自治县");
		cityCodes.put("131081", "河北省霸州市");
		cityCodes.put("131082", "河北省三河市");
		cityCodes.put("1324", "河北省保定地区");
		cityCodes.put("132401", "河北省定州市");
		cityCodes.put("132402", "河北省涿州市");
		cityCodes.put("132403", "河北省安国市");
		cityCodes.put("132404", "河北省高碑店市");
		cityCodes.put("132421", "河北省易县");
		cityCodes.put("132423", "河北省徐水县");
		cityCodes.put("132424", "河北省涞源县");
		cityCodes.put("132425", "河北省定兴县");
		cityCodes.put("132426", "河北省顺平县");
		cityCodes.put("132427", "河北省唐县");
		cityCodes.put("132428", "河北省望都县");
		cityCodes.put("132429", "河北省涞水县");
		cityCodes.put("132432", "河北省高阳县");
		cityCodes.put("132433", "河北省安新县");
		cityCodes.put("132434", "河北省雄县");
		cityCodes.put("132435", "河北省容城县");
		cityCodes.put("132437", "河北省曲阳县");
		cityCodes.put("132438", "河北省阜平县");
		cityCodes.put("132441", "河北省博野县");
		cityCodes.put("132442", "河北省蠡县");
		cityCodes.put("1330", "河北省衡水地区");
		cityCodes.put("133001", "河北省衡水市");
		cityCodes.put("133002", "河北省冀州县");
		cityCodes.put("133023", "河北省枣强县");
		cityCodes.put("133024", "河北省武邑县");
		cityCodes.put("133025", "河北省深县");
		cityCodes.put("133026", "河北省武强县");
		cityCodes.put("133027", "河北省饶阳县");
		cityCodes.put("133028", "河北省安平县");
		cityCodes.put("133029", "河北省故城县");
		cityCodes.put("133030", "河北省景县");
		cityCodes.put("133031", "河北省阜城县");
		cityCodes.put("14", "山西省");
		cityCodes.put("1401", "山西省太原市");
		cityCodes.put("140101", "山西省太原市市辖区");
		cityCodes.put("140102", "山西省太原市南城区");
		cityCodes.put("140103", "山西省太原市北城区");
		cityCodes.put("140104", "山西省太原市河西区");
		cityCodes.put("140112", "山西省太原市南郊区");
		cityCodes.put("140113", "山西省太原市北郊区");
		cityCodes.put("140121", "山西省清徐县");
		cityCodes.put("140122", "山西省阳曲县");
		cityCodes.put("140123", "山西省娄烦县");
		cityCodes.put("140181", "山西省古交市");
		cityCodes.put("1402", "山西省大同市");
		cityCodes.put("140201", "山西省大同市市辖区");
		cityCodes.put("140202", "山西省大同市城区");
		cityCodes.put("140203", "山西省大同市矿区");
		cityCodes.put("140211", "山西省大同市南郊区");
		cityCodes.put("140212", "山西省大同市新荣区");
		cityCodes.put("140221", "山西省阳高县");
		cityCodes.put("140222", "山西省天镇县");
		cityCodes.put("140223", "山西省广灵县");
		cityCodes.put("140224", "山西省灵丘县");
		cityCodes.put("140225", "山西省浑源县");
		cityCodes.put("140226", "山西省左云县");
		cityCodes.put("140227", "山西省大同县");
		cityCodes.put("1403", "山西省阳泉市");
		cityCodes.put("140301", "山西省阳泉市市辖区");
		cityCodes.put("140302", "山西省阳泉市城区");
		cityCodes.put("140303", "山西省阳泉市矿区");
		cityCodes.put("140311", "山西省阳泉市郊区");
		cityCodes.put("140321", "山西省平定县");
		cityCodes.put("140322", "山西省盂县");
		cityCodes.put("1404", "山西省长治市");
		cityCodes.put("140401", "山西省长治市市辖区");
		cityCodes.put("140402", "山西省长治市城区");
		cityCodes.put("140411", "山西省长治市郊区");
		cityCodes.put("140421", "山西省长治县");
		cityCodes.put("140422", "山西省潞城县");
		cityCodes.put("140423", "山西省襄垣县");
		cityCodes.put("140424", "山西省屯留县");
		cityCodes.put("140425", "山西省平顺县");
		cityCodes.put("140426", "山西省黎城县");
		cityCodes.put("140427", "山西省壶关县");
		cityCodes.put("140428", "山西省长子县");
		cityCodes.put("140429", "山西省武乡县");
		cityCodes.put("140430", "山西省沁县");
		cityCodes.put("140431", "山西省沁源县");
		cityCodes.put("1405", "山西省晋城市");
		cityCodes.put("140501", "山西省晋城市市辖区");
		cityCodes.put("140502", "山西省晋城市城区");
		cityCodes.put("140511", "山西省晋城市郊区");
		cityCodes.put("140521", "山西省沁水县");
		cityCodes.put("140522", "山西省阳城县");
		cityCodes.put("140524", "山西省陵川县");
		cityCodes.put("140581", "山西省高平市");
		cityCodes.put("1406", "山西省朔州市");
		cityCodes.put("140601", "山西省朔州市市辖区");
		cityCodes.put("140602", "山西省朔州市朔城区");
		cityCodes.put("140603", "山西省朔州市平鲁区");
		cityCodes.put("140621", "山西省山阴县");
		cityCodes.put("140622", "山西省应县");
		cityCodes.put("140623", "山西省右玉县");
		cityCodes.put("140624", "山西省怀仁县");
		cityCodes.put("1422", "山西省忻洲地区");
		cityCodes.put("142201", "山西省忻州市");
		cityCodes.put("142202", "山西省原平县");
		cityCodes.put("142222", "山西省定襄县");
		cityCodes.put("142223", "山西省五台县");
		cityCodes.put("142225", "山西省代县");
		cityCodes.put("142226", "山西省繁峙县");
		cityCodes.put("142227", "山西省宁武县");
		cityCodes.put("142228", "山西省静乐县");
		cityCodes.put("142229", "山西省神池县");
		cityCodes.put("142230", "山西省五寨县");
		cityCodes.put("142231", "山西省苛岚县");
		cityCodes.put("142232", "山西省河曲县");
		cityCodes.put("142233", "山西省保德县");
		cityCodes.put("142234", "山西省偏关县");
		cityCodes.put("1423", "山西省吕梁地区");
		cityCodes.put("142301", "山西省孝义市");
		cityCodes.put("142321", "山西省汾阳县");
		cityCodes.put("142322", "山西省文水县");
		cityCodes.put("142323", "山西省交城县");
		cityCodes.put("142325", "山西省兴县");
		cityCodes.put("142326", "山西省临县");
		cityCodes.put("142327", "山西省柳林县");
		cityCodes.put("142328", "山西省石楼县");
		cityCodes.put("142329", "山西省岚县");
		cityCodes.put("142330", "山西省方山县");
		cityCodes.put("142331", "山西省离石县");
		cityCodes.put("142332", "山西省中阳县");
		cityCodes.put("142333", "山西省交口县");
		cityCodes.put("1424", "山西省晋中地区");
		cityCodes.put("142401", "山西省榆次市");
		cityCodes.put("142402", "山西省介休市");
		cityCodes.put("142421", "山西省榆社县");
		cityCodes.put("142422", "山西省左权县");
		cityCodes.put("142423", "山西省和顺县");
		cityCodes.put("142424", "山西省昔阳县");
		cityCodes.put("142427", "山西省寿阳县");
		cityCodes.put("142429", "山西省太谷县");
		cityCodes.put("142430", "山西省祁县");
		cityCodes.put("142431", "山西省平遥县");
		cityCodes.put("142433", "山西省灵石县");
		cityCodes.put("1426", "山西省临汾地区");
		cityCodes.put("142601", "山西省临汾市");
		cityCodes.put("142602", "山西省侯马市");
		cityCodes.put("142603", "山西省霍州市");
		cityCodes.put("142621", "山西省曲沃县");
		cityCodes.put("142622", "山西省翼城县");
		cityCodes.put("142623", "山西省襄汾县");
		cityCodes.put("142625", "山西省洪洞县");
		cityCodes.put("142627", "山西省古县");
		cityCodes.put("142628", "山西省安泽县");
		cityCodes.put("142629", "山西省浮山县");
		cityCodes.put("142630", "山西省吉县");
		cityCodes.put("142631", "山西省乡宁县");
		cityCodes.put("142632", "山西省蒲县");
		cityCodes.put("142633", "山西省大宁县");
		cityCodes.put("142634", "山西省永和县");
		cityCodes.put("142635", "山西省隰县");
		cityCodes.put("142636", "山西省汾西县");
		cityCodes.put("1427", "山西省运城地区");
		cityCodes.put("142701", "山西省运城市");
		cityCodes.put("142722", "山西省永济县");
		cityCodes.put("142723", "山西省芮城县");
		cityCodes.put("142724", "山西省临猗县");
		cityCodes.put("142725", "山西省万荣县");
		cityCodes.put("142726", "山西省新绛县");
		cityCodes.put("142727", "山西省稷山县");
		cityCodes.put("142728", "山西省河津县");
		cityCodes.put("142729", "山西省闻喜县");
		cityCodes.put("142730", "山西省夏县");
		cityCodes.put("142731", "山西省绛县");
		cityCodes.put("142732", "山西省平陆县");
		cityCodes.put("142733", "山西省垣曲县");
		cityCodes.put("15", "内蒙古自治区");
		cityCodes.put("1501", "内蒙古呼和浩特市");
		cityCodes.put("150101", "内蒙古呼和浩特市市辖区");
		cityCodes.put("150102", "内蒙古呼和浩特市新城区");
		cityCodes.put("150103", "内蒙古呼和浩特市回民区");
		cityCodes.put("150104", "内蒙古呼和浩特市玉泉区");
		cityCodes.put("150105", "内蒙古呼和浩特市郊区");
		cityCodes.put("150121", "内蒙古土默特左旗");
		cityCodes.put("150122", "内蒙古托克托县");
		cityCodes.put("1502", "内蒙古包头市");
		cityCodes.put("150201", "内蒙古包头市市辖区");
		cityCodes.put("150202", "内蒙古包头市东河区");
		cityCodes.put("150203", "内蒙古包头市昆都伦区");
		cityCodes.put("150204", "内蒙古包头市青山区");
		cityCodes.put("150205", "内蒙古包头市石拐矿区");
		cityCodes.put("150206", "内蒙古包头市白云矿区");
		cityCodes.put("150207", "内蒙古包头市郊区");
		cityCodes.put("150221", "内蒙古土默特右旗");
		cityCodes.put("150222", "内蒙古固阳县");
		cityCodes.put("1503", "内蒙古乌海市");
		cityCodes.put("150301", "内蒙古乌海市市辖区");
		cityCodes.put("150302", "内蒙古乌海市海勃湾区");
		cityCodes.put("150303", "内蒙古乌海市海南区");
		cityCodes.put("150304", "内蒙古乌海市乌达区");
		cityCodes.put("1504", "内蒙古赤峰市");
		cityCodes.put("150401", "内蒙古赤峰市市辖区");
		cityCodes.put("150402", "内蒙古赤峰市红山区");
		cityCodes.put("150403", "内蒙古赤峰市元宝山区");
		cityCodes.put("150404", "内蒙古赤峰市松山区");
		cityCodes.put("150421", "内蒙古阿鲁科尔沁旗");
		cityCodes.put("150422", "内蒙古巴林左旗");
		cityCodes.put("150423", "内蒙古巴林右旗");
		cityCodes.put("150424", "内蒙古林西县");
		cityCodes.put("150425", "内蒙古克什克腾旗");
		cityCodes.put("150426", "内蒙古翁牛特旗");
		cityCodes.put("150428", "内蒙古喀喇沁旗");
		cityCodes.put("150429", "内蒙古宁城县");
		cityCodes.put("150430", "内蒙古敖汉旗");
		cityCodes.put("1521", "内蒙古呼伦贝尔盟");
		cityCodes.put("152101", "内蒙古海拉尔市");
		cityCodes.put("152102", "内蒙古满洲里市");
		cityCodes.put("152103", "内蒙古扎兰屯市");
		cityCodes.put("152104", "内蒙古牙克石市");
		cityCodes.put("152122", "内蒙古阿荣旗");
		cityCodes.put("152123", "内蒙古莫力达瓦达翰尔族自治旗");
		cityCodes.put("152125", "内蒙古额尔古纳右旗");
		cityCodes.put("152126", "内蒙古额尔古纳左旗");
		cityCodes.put("152127", "内蒙古鄂伦春自治旗");
		cityCodes.put("152128", "内蒙古鄂温克族自治旗");
		cityCodes.put("152129", "内蒙古新巴尔虎右旗");
		cityCodes.put("152130", "内蒙古新巴尔虎左旗");
		cityCodes.put("152131", "内蒙古陈巴尔虎旗");
		cityCodes.put("1522", "内蒙古兴安盟");
		cityCodes.put("152201", "内蒙古乌兰浩特市");
		cityCodes.put("152221", "内蒙古科尔沁右翼前旗");
		cityCodes.put("152222", "内蒙古科尔沁右翼中旗");
		cityCodes.put("152223", "内蒙古扎赍特旗");
		cityCodes.put("152224", "内蒙古突泉县");
		cityCodes.put("1523", "内蒙古哲里木盟");
		cityCodes.put("152301", "内蒙古通辽市");
		cityCodes.put("152302", "内蒙古霍林郭勒市");
		cityCodes.put("152322", "内蒙古科尔沁左翼中旗");
		cityCodes.put("152323", "内蒙古科尔沁左翼后旗");
		cityCodes.put("152324", "内蒙古开鲁县");
		cityCodes.put("152325", "内蒙古库伦旗");
		cityCodes.put("152326", "内蒙古奈曼旗　");
		cityCodes.put("152327", "内蒙古扎鲁特旗");
		cityCodes.put("1525", "内蒙古锡林郭勒盟");
		cityCodes.put("152501", "内蒙古二连浩特市");
		cityCodes.put("152502", "内蒙古锡林浩特市");
		cityCodes.put("152522", "内蒙古阿巴嘎旗");
		cityCodes.put("152523", "内蒙古苏尼特左旗");
		cityCodes.put("152524", "内蒙古苏尼特右旗");
		cityCodes.put("152525", "内蒙古东乌珠穆沁旗");
		cityCodes.put("152526", "内蒙古西乌珠穆沁旗");
		cityCodes.put("152527", "内蒙古太仆寺旗");
		cityCodes.put("152528", "内蒙古镶黄旗");
		cityCodes.put("152529", "内蒙古正镶白旗");
		cityCodes.put("152530", "内蒙古正蓝旗");
		cityCodes.put("152531", "内蒙古多伦县");
		cityCodes.put("1526", "内蒙古乌兰察布盟");
		cityCodes.put("152601", "内蒙古集宁市");
		cityCodes.put("152602", "内蒙古丰镇市");
		cityCodes.put("152621", "内蒙古武川县");
		cityCodes.put("152622", "内蒙古和林格尔县");
		cityCodes.put("152623", "内蒙古清水河县");
		cityCodes.put("152624", "内蒙古卓资县");
		cityCodes.put("152625", "内蒙古化德县");
		cityCodes.put("152626", "内蒙古商都县");
		cityCodes.put("152627", "内蒙古兴和县");
		cityCodes.put("152629", "内蒙古凉城县");
		cityCodes.put("152630", "内蒙古察哈尔右翼前旗");
		cityCodes.put("152631", "内蒙古察哈尔右翼中旗");
		cityCodes.put("152632", "内蒙古察哈尔右翼后旗");
		cityCodes.put("152633", "内蒙古达尔罕茂明安联合旗");
		cityCodes.put("152634", "内蒙古四子王旗");
		cityCodes.put("1527", "内蒙古伊克昭盟");
		cityCodes.put("152701", "内蒙古东胜市");
		cityCodes.put("152722", "内蒙古达拉特旗");
		cityCodes.put("152723", "内蒙古准格尔旗");
		cityCodes.put("152724", "内蒙古鄂托克前旗");
		cityCodes.put("152725", "内蒙古鄂托克旗");
		cityCodes.put("152726", "内蒙古杭锦旗　");
		cityCodes.put("152727", "内蒙古乌审旗");
		cityCodes.put("152728", "内蒙古伊金霍洛旗");
		cityCodes.put("1528", "内蒙古巴彦淖尔盟");
		cityCodes.put("152801", "内蒙古临河市");
		cityCodes.put("152822", "内蒙古五原县");
		cityCodes.put("152823", "内蒙古磴口县");
		cityCodes.put("152824", "内蒙古乌拉特前旗");
		cityCodes.put("152825", "内蒙古乌拉特中旗");
		cityCodes.put("152826", "内蒙古乌拉特后旗");
		cityCodes.put("152827", "内蒙古杭锦后旗");
		cityCodes.put("1529", "内蒙古阿拉善盟");
		cityCodes.put("152921", "内蒙古阿拉善左旗");
		cityCodes.put("152922", "内蒙古阿拉善右旗");
		cityCodes.put("152923", "内蒙古额济纳旗");
		cityCodes.put("21", "辽宁省");
		cityCodes.put("2101", "辽宁省沈阳市");
		cityCodes.put("210101", "辽宁省沈阳市市辖区");
		cityCodes.put("210102", "辽宁省沈阳市和平区");
		cityCodes.put("210103", "辽宁省沈阳市沈河区");
		cityCodes.put("210104", "辽宁省沈阳市大东区");
		cityCodes.put("210105", "辽宁省沈阳市皇姑区");
		cityCodes.put("210106", "辽宁省沈阳市铁西区");
		cityCodes.put("210111", "辽宁省沈阳市苏家屯区");
		cityCodes.put("210112", "辽宁省沈阳市东陵区");
		cityCodes.put("210113", "辽宁省沈阳市新城子区");
		cityCodes.put("210114", "辽宁省沈阳市于洪区");
		cityCodes.put("210122", "辽宁省辽中县");
		cityCodes.put("210123", "辽宁省康平县");
		cityCodes.put("210124", "辽宁省法库县");
		cityCodes.put("210181", "辽宁省新民市");
		cityCodes.put("2102", "辽宁省大连市");
		cityCodes.put("210201", "辽宁省大连市市辖区");
		cityCodes.put("210202", "辽宁省大连市中山区");
		cityCodes.put("210203", "辽宁省大连市西岗区");
		cityCodes.put("210204", "辽宁省大连市沙河口区");
		cityCodes.put("210211", "辽宁省大连市甘井子区");
		cityCodes.put("210212", "辽宁省大连市旅顺口区");
		cityCodes.put("210213", "辽宁省大连市金州区");
		cityCodes.put("210224", "辽宁省长海县");
		cityCodes.put("210281", "辽宁省瓦房店市");
		cityCodes.put("210282", "辽宁省普兰店市");
		cityCodes.put("210283", "辽宁省庄河市");
		cityCodes.put("2103", "辽宁省鞍山市");
		cityCodes.put("210301", "辽宁省鞍山市市辖区");
		cityCodes.put("210302", "辽宁省鞍山市铁东区");
		cityCodes.put("210303", "辽宁省鞍山市铁西区");
		cityCodes.put("210304", "辽宁省鞍山市立山区");
		cityCodes.put("210311", "辽宁省鞍山市旧堡区");
		cityCodes.put("210321", "辽宁省台安县");
		cityCodes.put("210323", "辽宁省岫岩满族自治县");
		cityCodes.put("210381", "辽宁省海城市");
		cityCodes.put("2104", "辽宁省抚顺市");
		cityCodes.put("210401", "辽宁省抚顺市市辖区");
		cityCodes.put("210402", "辽宁省抚顺市新抚区");
		cityCodes.put("210403", "辽宁省抚顺市露天区");
		cityCodes.put("210404", "辽宁省抚顺市望花区");
		cityCodes.put("210411", "辽宁省抚顺市顺城区");
		cityCodes.put("210421", "辽宁省抚顺县");
		cityCodes.put("210422", "辽宁省新宾满族自治县");
		cityCodes.put("210423", "辽宁省清原满族自治县");
		cityCodes.put("2105", "辽宁省本溪市");
		cityCodes.put("210501", "辽宁省本溪市市辖区");
		cityCodes.put("210502", "辽宁省本溪市平山区");
		cityCodes.put("210503", "辽宁省本溪市溪湖区");
		cityCodes.put("210504", "辽宁省本溪市明山区");
		cityCodes.put("210511", "辽宁省本溪市南芬区");
		cityCodes.put("210521", "辽宁省本溪满族自治县");
		cityCodes.put("210522", "辽宁省桓仁满族自治县");
		cityCodes.put("2106", "辽宁省丹东市");
		cityCodes.put("210601", "辽宁省丹东市市辖区");
		cityCodes.put("210602", "辽宁省丹东市元宝区");
		cityCodes.put("210603", "辽宁省丹东市振兴区");
		cityCodes.put("210604", "辽宁省丹东市振安区");
		cityCodes.put("210621", "辽宁省凤城满族自治县");
		cityCodes.put("210624", "辽宁省宽甸满族自治县");
		cityCodes.put("210681", "辽宁省东港市");
		cityCodes.put("2107", "辽宁省锦州市");
		cityCodes.put("210701", "辽宁省锦州市市辖区");
		cityCodes.put("210702", "辽宁省锦州市古塔区");
		cityCodes.put("210703", "辽宁省锦州市凌河区");
		cityCodes.put("210711", "辽宁省锦州市太和区");
		cityCodes.put("210725", "辽宁省北镇满族自治县");
		cityCodes.put("210726", "辽宁省黑山县");
		cityCodes.put("210727", "辽宁省义县");
		cityCodes.put("210781", "辽宁省凌海市");
		cityCodes.put("2108", "辽宁省营口市");
		cityCodes.put("210801", "辽宁省营口市市辖区");
		cityCodes.put("210802", "辽宁省营口市站前区");
		cityCodes.put("210803", "辽宁省营口市西市区");
		cityCodes.put("210804", "辽宁省营口市鲅鱼圈区");
		cityCodes.put("210811", "辽宁省营口市老边区");
		cityCodes.put("210881", "辽宁省盖州市");
		cityCodes.put("210882", "辽宁省大石桥市");
		cityCodes.put("2109", "辽宁省阜新市");
		cityCodes.put("210901", "辽宁省阜新市市辖区");
		cityCodes.put("210902", "辽宁省阜新市海洲区");
		cityCodes.put("210903", "辽宁省阜新市新邱区");
		cityCodes.put("210904", "辽宁省阜新市太平区");
		cityCodes.put("210905", "辽宁省阜新市清河门区");
		cityCodes.put("210911", "辽宁省阜新市细河区");
		cityCodes.put("210921", "辽宁省阜新蒙古族自治县");
		cityCodes.put("210922", "辽宁省彰武县");
		cityCodes.put("2110", "辽宁省辽阳市");
		cityCodes.put("211001", "辽宁省辽阳市市辖区");
		cityCodes.put("211002", "辽宁省辽阳市白塔区");
		cityCodes.put("211003", "辽宁省辽阳市文圣区");
		cityCodes.put("211004", "辽宁省辽阳市宏伟区");
		cityCodes.put("211005", "辽宁省辽阳市弓长岭区");
		cityCodes.put("211011", "辽宁省辽阳市太子河区");
		cityCodes.put("211021", "辽宁省辽阳县");
		cityCodes.put("211022", "辽宁省灯塔县");
		cityCodes.put("2111", "辽宁省盘锦市");
		cityCodes.put("211101", "辽宁省盘锦市市辖区");
		cityCodes.put("211102", "辽宁省盘锦市双台子区");
		cityCodes.put("211103", "辽宁省盘锦市兴隆台区");
		cityCodes.put("211121", "辽宁省大洼县");
		cityCodes.put("211122", "辽宁省盘山县");
		cityCodes.put("2112", "辽宁省铁岭市");
		cityCodes.put("211201", "辽宁省铁岭市市辖区");
		cityCodes.put("211202", "辽宁省铁岭市银州区");
		cityCodes.put("211204", "辽宁省铁岭市清河区");
		cityCodes.put("211221", "辽宁省铁岭县");
		cityCodes.put("211223", "辽宁省西丰县");
		cityCodes.put("211224", "辽宁省昌图县");
		cityCodes.put("211281", "辽宁省铁法市");
		cityCodes.put("211282", "辽宁省开原市");
		cityCodes.put("2113", "辽宁省朝阳市");
		cityCodes.put("211301", "辽宁省朝阳市市辖区");
		cityCodes.put("211302", "辽宁省朝阳市双塔区");
		cityCodes.put("211303", "辽宁省朝阳市龙城区");
		cityCodes.put("211321", "辽宁省朝阳县");
		cityCodes.put("211322", "辽宁省建平县");
		cityCodes.put("211324", "辽宁省喀喇沁左翼蒙古族自治县");
		cityCodes.put("211381", "辽宁省北票市");
		cityCodes.put("211382", "辽宁省凌源市");
		cityCodes.put("2114", "辽宁省锦西市");
		cityCodes.put("211401", "辽宁省锦西市市辖区");
		cityCodes.put("211402", "辽宁省锦西市连山区");
		cityCodes.put("211403", "辽宁省锦西市葫芦岛区");
		cityCodes.put("211404", "辽宁省锦西市南票区");
		cityCodes.put("211421", "辽宁省绥中县");
		cityCodes.put("211422", "辽宁省建昌县");
		cityCodes.put("211481", "辽宁省兴城市");
		cityCodes.put("22", "吉林省");
		cityCodes.put("2201", "吉林省长春市");
		cityCodes.put("220101", "吉林省长春市市辖区");
		cityCodes.put("220102", "吉林省长春市南关区");
		cityCodes.put("220103", "吉林省长春市宽城区");
		cityCodes.put("220104", "吉林省长春市朝阳区");
		cityCodes.put("220105", "吉林省长春市二道河子区");
		cityCodes.put("220111", "吉林省长春市郊区");
		cityCodes.put("220122", "吉林省农安县");
		cityCodes.put("220124", "吉林省德惠县");
		cityCodes.put("220125", "吉林省双阳县");
		cityCodes.put("220181", "吉林省九台市");
		cityCodes.put("220182", "吉林省榆树市");
		cityCodes.put("2202", "吉林省吉林市");
		cityCodes.put("220201", "吉林省吉林市市辖区");
		cityCodes.put("220202", "吉林省吉林市昌邑区");
		cityCodes.put("220203", "吉林省吉林市龙潭区");
		cityCodes.put("220204", "吉林省吉林市船营区");
		cityCodes.put("220211", "吉林省吉林市丰满区");
		cityCodes.put("220221", "吉林省永吉县");
		cityCodes.put("220223", "吉林省磐石县");
		cityCodes.put("220281", "吉林省蛟河市");
		cityCodes.put("220282", "吉林省桦甸市");
		cityCodes.put("220283", "吉林省舒兰市");
		cityCodes.put("2203", "吉林省四平市");
		cityCodes.put("220301", "吉林省四平市市辖区");
		cityCodes.put("220302", "吉林省四平市铁西区");
		cityCodes.put("220303", "吉林省四平市铁东区");
		cityCodes.put("220322", "吉林省梨树县");
		cityCodes.put("220323", "吉林省伊通满族自治县");
		cityCodes.put("220324", "吉林省双辽县");
		cityCodes.put("220381", "吉林省公主岭市");
		cityCodes.put("2204", "吉林省辽源市");
		cityCodes.put("220401", "吉林省辽源市市辖区");
		cityCodes.put("220402", "吉林省辽源市龙山区");
		cityCodes.put("220403", "吉林省辽源市西安区");
		cityCodes.put("220421", "吉林省东丰县");
		cityCodes.put("220422", "吉林省东辽县");
		cityCodes.put("2205", "吉林省通化市");
		cityCodes.put("220501", "吉林省通化市市辖区");
		cityCodes.put("220502", "吉林省通化市东昌区");
		cityCodes.put("220503", "吉林省通化市二道江区");
		cityCodes.put("220521", "吉林省通化县");
		cityCodes.put("220523", "吉林省辉南县");
		cityCodes.put("220524", "吉林省柳河县");
		cityCodes.put("220581", "吉林省梅河口市");
		cityCodes.put("220582", "吉林省集安市");
		cityCodes.put("2206", "吉林省浑江市");
		cityCodes.put("220601", "吉林省浑江市市辖区");
		cityCodes.put("220602", "吉林省浑江市八道江区");
		cityCodes.put("220603", "吉林省浑江市三岔子区");
		cityCodes.put("220621", "吉林省抚松县");
		cityCodes.put("220622", "吉林省靖宇县");
		cityCodes.put("220623", "吉林省长白朝鲜族自治县");
		cityCodes.put("220681", "吉林省临江市");
		cityCodes.put("2207", "吉林省松原市");
		cityCodes.put("220701", "吉林省松原市市辖区");
		cityCodes.put("220702", "吉林省松原市扶余区");
		cityCodes.put("220721", "吉林省前郭尔罗斯蒙古族自治县");
		cityCodes.put("220722", "吉林省长岭县");
		cityCodes.put("220723", "吉林省乾安县");
		cityCodes.put("2208", "吉林省白城市");
		cityCodes.put("220801", "吉林省白城市市辖区");
		cityCodes.put("220802", "吉林省白城市洮北区");
		cityCodes.put("220821", "吉林省镇赍县");
		cityCodes.put("220822", "吉林省通榆县");
		cityCodes.put("220881", "吉林省洮南市");
		cityCodes.put("220882", "吉林省大安市");
		cityCodes.put("2224", "吉林省延边朝鲜族自治州");
		cityCodes.put("222401", "吉林省延吉市");
		cityCodes.put("222402", "吉林省图们市");
		cityCodes.put("222403", "吉林省敦化市");
		cityCodes.put("222404", "吉林省珲春市");
		cityCodes.put("222405", "吉林省龙井市");
		cityCodes.put("222406", "吉林省和龙市");
		cityCodes.put("222424", "吉林省汪清县");
		cityCodes.put("222426", "吉林省安图县");
		cityCodes.put("23", "黑龙江");
		cityCodes.put("2301", "黑龙江哈尔滨市");
		cityCodes.put("230101", "黑龙江哈尔滨市市辖区");
		cityCodes.put("230102", "黑龙江哈尔滨市道里区");
		cityCodes.put("230103", "黑龙江哈尔滨市南岗区");
		cityCodes.put("230104", "黑龙江哈尔滨市道外区");
		cityCodes.put("230105", "黑龙江哈尔滨市太平区");
		cityCodes.put("230106", "黑龙江哈尔滨市香坊区");
		cityCodes.put("230107", "黑龙江哈尔滨市动力区");
		cityCodes.put("230108", "黑龙江哈尔滨市平房区");
		cityCodes.put("230121", "黑龙江呼兰县");
		cityCodes.put("230123", "黑龙江依兰县");
		cityCodes.put("230124", "黑龙江方正县");
		cityCodes.put("230125", "黑龙江宾县");
		cityCodes.put("230181", "黑龙江阿城市");
		cityCodes.put("2302", "黑龙江齐齐哈尔市");
		cityCodes.put("230201", "黑龙江齐齐哈尔市市辖区");
		cityCodes.put("230202", "黑龙江齐齐哈尔市龙沙区");
		cityCodes.put("230203", "黑龙江齐齐哈尔市建华区");
		cityCodes.put("230204", "黑龙江齐齐哈尔市铁锋区");
		cityCodes.put("230205", "黑龙江齐齐哈尔市昂昂溪区");
		cityCodes.put("230206", "黑龙江齐齐哈尔市富拉尔基区");
		cityCodes.put("230207", "黑龙江齐齐哈尔市碾子山区");
		cityCodes.put("230208", "黑龙江齐齐哈尔市梅里斯达斡尔族");
		cityCodes.put("230221", "黑龙江龙江县");
		cityCodes.put("230223", "黑龙江依安县");
		cityCodes.put("230224", "黑龙江泰来县");
		cityCodes.put("230225", "黑龙江甘南县");
		cityCodes.put("230227", "黑龙江富裕县");
		cityCodes.put("230229", "黑龙江克山县");
		cityCodes.put("230230", "黑龙江克东县");
		cityCodes.put("230231", "黑龙江拜泉县");
		cityCodes.put("230281", "黑龙江讷河市");
		cityCodes.put("2303", "黑龙江鸡西市");
		cityCodes.put("230301", "黑龙江鸡西市市辖区");
		cityCodes.put("230302", "黑龙江鸡西市鸡冠区");
		cityCodes.put("230303", "黑龙江鸡西市恒山区");
		cityCodes.put("230304", "黑龙江鸡西市滴道区");
		cityCodes.put("230305", "黑龙江鸡西市梨树区");
		cityCodes.put("230306", "黑龙江鸡西市城子河区");
		cityCodes.put("230307", "黑龙江鸡西市麻山区");
		cityCodes.put("230321", "黑龙江鸡东县");
		cityCodes.put("230322", "黑龙江虎林县");
		cityCodes.put("2304", "黑龙江鹤岗市");
		cityCodes.put("230401", "黑龙江鹤岗市市辖区");
		cityCodes.put("230402", "黑龙江鹤岗市向阳区");
		cityCodes.put("230403", "黑龙江鹤岗市工农区");
		cityCodes.put("230404", "黑龙江鹤岗市南山区");
		cityCodes.put("230405", "黑龙江鹤岗市兴安区");
		cityCodes.put("230406", "黑龙江鹤岗市东山区");
		cityCodes.put("230407", "黑龙江鹤岗市兴山区");
		cityCodes.put("230421", "黑龙江萝北县");
		cityCodes.put("230422", "黑龙江绥滨县");
		cityCodes.put("2305", "黑龙江双鸭山市");
		cityCodes.put("230501", "黑龙江双鸭山市市辖区");
		cityCodes.put("230502", "黑龙江双鸭山市尖山区");
		cityCodes.put("230503", "黑龙江双鸭山市岭东区");
		cityCodes.put("230505", "黑龙江双鸭山市四方台区");
		cityCodes.put("230506", "黑龙江双鸭山市宝山区");
		cityCodes.put("230521", "黑龙江集贤县");
		cityCodes.put("230522", "黑龙江友谊县");
		cityCodes.put("230523", "黑龙江宝清县");
		cityCodes.put("230524", "黑龙江饶河县");
		cityCodes.put("2306", "黑龙江大庆市");
		cityCodes.put("230601", "黑龙江大庆市市辖区");
		cityCodes.put("230602", "黑龙江大庆市萨尔图区");
		cityCodes.put("230603", "黑龙江大庆市龙凤区");
		cityCodes.put("230604", "黑龙江大庆市让胡路区");
		cityCodes.put("230605", "黑龙江大庆市红岗区");
		cityCodes.put("230606", "黑龙江大庆市大同区");
		cityCodes.put("230621", "黑龙江肇州县");
		cityCodes.put("230622", "黑龙江肇源县");
		cityCodes.put("230623", "黑龙江林甸县");
		cityCodes.put("230624", "黑龙江杜尔伯特蒙古族自治县");
		cityCodes.put("2307", "黑龙江伊春市");
		cityCodes.put("230701", "黑龙江伊春市市辖区");
		cityCodes.put("230702", "黑龙江伊春市伊春区");
		cityCodes.put("230703", "黑龙江伊春市南岔区");
		cityCodes.put("230704", "黑龙江伊春市友好区");
		cityCodes.put("230705", "黑龙江伊春市西林区");
		cityCodes.put("230706", "黑龙江伊春市翠峦区");
		cityCodes.put("230707", "黑龙江伊春市新青区");
		cityCodes.put("230708", "黑龙江伊春市美溪区");
		cityCodes.put("230709", "黑龙江伊春市金山屯区");
		cityCodes.put("230710", "黑龙江伊春市五营区");
		cityCodes.put("230711", "黑龙江伊春市乌马河区");
		cityCodes.put("230712", "黑龙江伊春市汤旺河区");
		cityCodes.put("230713", "黑龙江伊春市带岭区");
		cityCodes.put("230714", "黑龙江伊春市乌伊岭区");
		cityCodes.put("230715", "黑龙江伊春市红星区");
		cityCodes.put("230716", "黑龙江伊春市上甘岭区");
		cityCodes.put("230722", "黑龙江嘉荫县");
		cityCodes.put("230781", "黑龙江铁力市");
		cityCodes.put("2308", "黑龙江佳木斯市");
		cityCodes.put("230801", "黑龙江佳木斯市市辖区");
		cityCodes.put("230802", "黑龙江佳木斯市永红区");
		cityCodes.put("230803", "黑龙江佳木斯市向阳区");
		cityCodes.put("230804", "黑龙江佳木斯市前进区");
		cityCodes.put("230805", "黑龙江佳木斯市东风区");
		cityCodes.put("230811", "黑龙江佳木斯市郊区");
		cityCodes.put("230822", "黑龙江桦南县");
		cityCodes.put("230826", "黑龙江桦川县");
		cityCodes.put("230828", "黑龙江汤原县");
		cityCodes.put("230833", "黑龙江扶远县");
		cityCodes.put("230881", "黑龙江同江市");
		cityCodes.put("230882", "黑龙江富锦市");
		cityCodes.put("2309", "黑龙江七台河市");
		cityCodes.put("230901", "黑龙江七台河市市辖区");
		cityCodes.put("230902", "黑龙江七台河市新兴区");
		cityCodes.put("230903", "黑龙江七台河市桃山区");
		cityCodes.put("230904", "黑龙江七台河市茄子河区");
		cityCodes.put("230921", "黑龙江勃利县");
		cityCodes.put("2310", "黑龙江牡丹江市");
		cityCodes.put("231001", "黑龙江牡丹江市市辖区");
		cityCodes.put("231002", "黑龙江牡丹江市东安区");
		cityCodes.put("231003", "黑龙江牡丹江市阳明区");
		cityCodes.put("231004", "黑龙江牡丹江市爱民区");
		cityCodes.put("231005", "黑龙江牡丹江市西安区");
		cityCodes.put("231011", "黑龙江牡丹江市郊区");
		cityCodes.put("231023", "黑龙江穆棱县");
		cityCodes.put("231024", "黑龙江东宁县");
		cityCodes.put("231025", "黑龙江林口县");
		cityCodes.put("231081", "黑龙江绥芬河市");
		cityCodes.put("231082", "黑龙江密山市");
		cityCodes.put("231083", "黑龙江海林市");
		cityCodes.put("231084", "黑龙江宁安市");
		cityCodes.put("2311", "黑龙江黑河市");
		cityCodes.put("231101", "黑龙江黑河市市辖区");
		cityCodes.put("231102", "黑龙江黑河市爱辉区");
		cityCodes.put("231121", "黑龙江嫩江县");
		cityCodes.put("231122", "黑龙江德都县");
		cityCodes.put("231123", "黑龙江逊克县");
		cityCodes.put("231124", "黑龙江孙吴县");
		cityCodes.put("231181", "黑龙江北安市");
		cityCodes.put("231182", "黑龙江五大连池市");
		cityCodes.put("2321", "黑龙江松花江地区");
		cityCodes.put("232101", "黑龙江双城市");
		cityCodes.put("232102", "黑龙江尚志市");
		cityCodes.put("232103", "黑龙江五常市");
		cityCodes.put("232126", "黑龙江巴彦县");
		cityCodes.put("232127", "黑龙江木兰县");
		cityCodes.put("232128", "黑龙江通河县");
		cityCodes.put("232131", "黑龙江延寿县");
		cityCodes.put("2323", "黑龙江绥化地区");
		cityCodes.put("232301", "黑龙江绥化市");
		cityCodes.put("232302", "黑龙江安达市");
		cityCodes.put("232303", "黑龙江肇东市");
		cityCodes.put("232304", "黑龙江海伦市");
		cityCodes.put("232324", "黑龙江望奎县");
		cityCodes.put("232325", "黑龙江兰西县");
		cityCodes.put("232326", "黑龙江青冈县");
		cityCodes.put("232330", "黑龙江庆安县");
		cityCodes.put("232331", "黑龙江明水县");
		cityCodes.put("232332", "黑龙江绥棱县");
		cityCodes.put("2327", "黑龙江大兴安岭地区");
		cityCodes.put("232721", "黑龙江呼玛县");
		cityCodes.put("232722", "黑龙江塔河县");
		cityCodes.put("232723", "黑龙江漠河县");
		cityCodes.put("31", "上海市");
		cityCodes.put("3101", "上海市市辖区");
		cityCodes.put("310101", "上海市黄浦区");
		cityCodes.put("310102", "上海市南市区");
		cityCodes.put("310103", "上海市卢湾区");
		cityCodes.put("310104", "上海市徐汇区");
		cityCodes.put("310105", "上海市长宁区");
		cityCodes.put("310106", "上海市静安区");
		cityCodes.put("310107", "上海市普陀区");
		cityCodes.put("310108", "上海市闸北区");
		cityCodes.put("310109", "上海市虹口区");
		cityCodes.put("310110", "上海市扬浦区");
		cityCodes.put("310112", "上海市闵行区");
		cityCodes.put("310113", "上海市宝山区");
		cityCodes.put("310114", "上海市嘉定区");
		cityCodes.put("310115", "上海市浦东新区");
		cityCodes.put("3102", "上海市市辖县");
		cityCodes.put("310226", "上海市奉贤县");
		cityCodes.put("310227", "上海市松江县");
		cityCodes.put("310228", "上海市金山县");
		cityCodes.put("310229", "上海市青浦县");
		cityCodes.put("310230", "上海市崇明县");
		cityCodes.put("32", "江苏省");
		cityCodes.put("3201", "江苏省南京市");
		cityCodes.put("320101", "江苏省南京市市辖区");
		cityCodes.put("320102", "江苏省南京市玄武区");
		cityCodes.put("320103", "江苏省南京市白下区");
		cityCodes.put("320104", "江苏省南京市秦淮区");
		cityCodes.put("320105", "江苏省南京市建邺区");
		cityCodes.put("320106", "江苏省南京市鼓楼区");
		cityCodes.put("320107", "江苏省南京市下关区");
		cityCodes.put("320111", "江苏省南京市浦口区");
		cityCodes.put("320112", "江苏省南京市大厂区");
		cityCodes.put("320113", "江苏省南京市栖霞区");
		cityCodes.put("320114", "江苏省南京市雨花台区");
		cityCodes.put("320121", "江苏省江宁县");
		cityCodes.put("320122", "江苏省江浦县");
		cityCodes.put("320123", "江苏省六合县");
		cityCodes.put("320124", "江苏省溧水县");
		cityCodes.put("320125", "江苏省高淳县");
		cityCodes.put("3202", "江苏省无锡市");
		cityCodes.put("320201", "江苏省无锡市市辖区");
		cityCodes.put("320202", "江苏省无锡市崇安区");
		cityCodes.put("320203", "江苏省无锡市南长区");
		cityCodes.put("320204", "江苏省无锡市北塘区");
		cityCodes.put("320211", "江苏省无锡市郊区");
		cityCodes.put("320212", "江苏省无锡市马山区");
		cityCodes.put("320222", "江苏省无锡县");
		cityCodes.put("320281", "江苏省江阴市");
		cityCodes.put("320282", "江苏省宜兴市");
		cityCodes.put("3203", "江苏省徐州市");
		cityCodes.put("320301", "江苏省徐州市市辖区");
		cityCodes.put("320302", "江苏省徐州市鼓楼区");
		cityCodes.put("320303", "江苏省徐州市云龙区");
		cityCodes.put("320304", "江苏省徐州市矿区");
		cityCodes.put("320305", "江苏省徐州市贾汪区");
		cityCodes.put("320311", "江苏省徐州市泉山区");
		cityCodes.put("320321", "江苏省丰县");
		cityCodes.put("320322", "江苏省沛县");
		cityCodes.put("320323", "江苏省铜山县");
		cityCodes.put("320324", "江苏省睢宁县");
		cityCodes.put("320381", "江苏省新沂市");
		cityCodes.put("320382", "江苏省邳州市");
		cityCodes.put("3204", "江苏省常州市");
		cityCodes.put("320401", "江苏省常州市市辖区");
		cityCodes.put("320402", "江苏省常州市天宁区");
		cityCodes.put("320404", "江苏省常州市钟楼区");
		cityCodes.put("320405", "江苏省常州市戚墅堰区");
		cityCodes.put("320411", "江苏省常州市郊区");
		cityCodes.put("320421", "江苏省武进县");
		cityCodes.put("320481", "江苏省溧阳市");
		cityCodes.put("320482", "江苏省金坛市");
		cityCodes.put("3205", "江苏省苏州市");
		cityCodes.put("320501", "江苏省苏州市市辖区");
		cityCodes.put("320502", "江苏省苏州市沧浪区");
		cityCodes.put("320503", "江苏省苏州市平江区");
		cityCodes.put("320504", "江苏省苏州市金阊区");
		cityCodes.put("320511", "江苏省苏州市郊区");
		cityCodes.put("320524", "江苏省吴县");
		cityCodes.put("320581", "江苏省常熟市");
		cityCodes.put("320582", "江苏省张家港市");
		cityCodes.put("320583", "江苏省昆山市");
		cityCodes.put("320584", "江苏省吴江市");
		cityCodes.put("320585", "江苏省太仓市");
		cityCodes.put("3206", "江苏省南通市");
		cityCodes.put("320601", "江苏省南通市市辖区");
		cityCodes.put("320602", "江苏省南通市崇川区");
		cityCodes.put("320611", "江苏省南通市港闸区");
		cityCodes.put("320621", "江苏省海安县");
		cityCodes.put("320623", "江苏省如东县");
		cityCodes.put("320625", "江苏省海门县");
		cityCodes.put("320681", "江苏省启东市");
		cityCodes.put("320682", "江苏省如皋市");
		cityCodes.put("320683", "江苏省通州市");
		cityCodes.put("3207", "江苏省连云港市");
		cityCodes.put("320701", "江苏省连云港市市辖区");
		cityCodes.put("320703", "江苏省连云港市连云区");
		cityCodes.put("320704", "江苏省连云港市云台区");
		cityCodes.put("320705", "江苏省连云港市新浦区");
		cityCodes.put("320706", "江苏省连云港市海州区");
		cityCodes.put("320721", "江苏省赣榆县");
		cityCodes.put("320722", "江苏省东海县");
		cityCodes.put("320723", "江苏省灌云县");
		cityCodes.put("3208", "江苏省淮阴市");
		cityCodes.put("320801", "江苏省淮阴市市辖区");
		cityCodes.put("320802", "江苏省淮阴市清河区");
		cityCodes.put("320811", "江苏省淮阴市清浦区");
		cityCodes.put("320821", "江苏省淮阴县");
		cityCodes.put("320822", "江苏省灌南县");
		cityCodes.put("320823", "江苏省沭阳县");
		cityCodes.put("320825", "江苏省泗阳县");
		cityCodes.put("320826", "江苏省涟水县");
		cityCodes.put("320827", "江苏省泗洪县");
		cityCodes.put("320829", "江苏省洪泽县");
		cityCodes.put("320830", "江苏省盱眙县");
		cityCodes.put("320831", "江苏省金湖县");
		cityCodes.put("320881", "江苏省宿迁市");
		cityCodes.put("320882", "江苏省淮安市");
		cityCodes.put("3209", "江苏省盐城市");
		cityCodes.put("320901", "江苏省盐城市市辖区");
		cityCodes.put("320902", "江苏省盐城市城区");
		cityCodes.put("320911", "江苏省盐城市郊区");
		cityCodes.put("320921", "江苏省响水县");
		cityCodes.put("320922", "江苏省滨海县");
		cityCodes.put("320923", "江苏省阜宁县");
		cityCodes.put("320924", "江苏省射阳县");
		cityCodes.put("320925", "江苏省建湖县");
		cityCodes.put("320926", "江苏省大丰县");
		cityCodes.put("320981", "江苏省东台市");
		cityCodes.put("3210", "江苏省扬州市");
		cityCodes.put("321001", "江苏省扬州市市辖区");
		cityCodes.put("321002", "江苏省扬州市广陵区");
		cityCodes.put("321011", "江苏省扬州市郊区");
		cityCodes.put("321023", "江苏省宝应县");
		cityCodes.put("321026", "江苏省江都县");
		cityCodes.put("321027", "江苏省邗江县");
		cityCodes.put("321028", "江苏省泰县");
		cityCodes.put("321081", "江苏省仪征市");
		cityCodes.put("321082", "江苏省泰州市");
		cityCodes.put("321083", "江苏省兴化市");
		cityCodes.put("321084", "江苏省高邮市");
		cityCodes.put("321085", "江苏省泰兴市");
		cityCodes.put("321086", "江苏省靖江市");
		cityCodes.put("3211", "江苏省镇江市");
		cityCodes.put("321101", "江苏省镇江市市辖区");
		cityCodes.put("321102", "江苏省镇江市京口区");
		cityCodes.put("321111", "江苏省镇江市润州区");
		cityCodes.put("321121", "江苏省丹徒县");
		cityCodes.put("321123", "江苏省句容县");
		cityCodes.put("321124", "江苏省扬中县");
		cityCodes.put("321181", "江苏省丹阳市");
		cityCodes.put("33", "浙江省");
		cityCodes.put("3301", "浙江省杭州市");
		cityCodes.put("330101", "浙江省杭州市市辖区");
		cityCodes.put("330102", "浙江省杭州市上城区");
		cityCodes.put("330103", "浙江省杭州市下城区");
		cityCodes.put("330104", "浙江省杭州市江干区");
		cityCodes.put("330105", "浙江省杭州市拱墅区");
		cityCodes.put("330106", "浙江省杭州市西湖区");
		cityCodes.put("330122", "浙江省桐庐县");
		cityCodes.put("330123", "浙江省富阳县");
		cityCodes.put("330124", "浙江省临安县");
		cityCodes.put("330125", "浙江省余杭县");
		cityCodes.put("330126", "浙江省建德县");
		cityCodes.put("330127", "浙江省淳安县");
		cityCodes.put("330181", "浙江省萧山市");
		cityCodes.put("3302", "浙江省宁波市");
		cityCodes.put("330201", "浙江省宁波市市辖区");
		cityCodes.put("330203", "浙江省宁波市海曙区");
		cityCodes.put("330204", "浙江省宁波市江东区");
		cityCodes.put("330205", "浙江省宁波市江北区");
		cityCodes.put("330206", "浙江省宁波市北仓区");
		cityCodes.put("330211", "浙江省宁波市镇海区");
		cityCodes.put("330225", "浙江省象山县");
		cityCodes.put("330226", "浙江省宁海县");
		cityCodes.put("330227", "浙江省鄞县");
		cityCodes.put("330281", "浙江省余姚市");
		cityCodes.put("330282", "浙江省慈溪市");
		cityCodes.put("330283", "浙江省奉化市");
		cityCodes.put("3303", "浙江省温州市");
		cityCodes.put("330301", "浙江省温州市市辖区");
		cityCodes.put("330302", "浙江省温州市鹿城区");
		cityCodes.put("330303", "浙江省温州市龙湾区");
		cityCodes.put("330304", "浙江省温州市瓯海区");
		cityCodes.put("330322", "浙江省洞头县");
		cityCodes.put("330324", "浙江省永嘉县");
		cityCodes.put("330326", "浙江省平阳县");
		cityCodes.put("330327", "浙江省苍南县");
		cityCodes.put("330328", "浙江省文成县");
		cityCodes.put("330329", "浙江省泰顺县");
		cityCodes.put("330381", "浙江省瑞安市");
		cityCodes.put("330382", "浙江省乐清市");
		cityCodes.put("3304", "浙江省嘉兴市");
		cityCodes.put("330401", "浙江省嘉兴市市辖区");
		cityCodes.put("330402", "浙江省嘉兴市城区");
		cityCodes.put("330411", "浙江省嘉兴市郊区");
		cityCodes.put("330421", "浙江省嘉善县");
		cityCodes.put("330424", "浙江省海盐县");
		cityCodes.put("330481", "浙江省海宁市");
		cityCodes.put("330482", "浙江省平湖市");
		cityCodes.put("330483", "浙江省桐乡市");
		cityCodes.put("3305", "浙江省湖洲市");
		cityCodes.put("330501", "浙江省湖洲市市辖区");
		cityCodes.put("330521", "浙江省德清县");
		cityCodes.put("330522", "浙江省长兴县");
		cityCodes.put("330523", "浙江省安吉县");
		cityCodes.put("3306", "浙江省绍兴市");
		cityCodes.put("330601", "浙江省绍兴市市辖区");
		cityCodes.put("330602", "浙江省绍兴市越城区");
		cityCodes.put("330621", "浙江省绍兴县");
		cityCodes.put("330623", "浙江省嵊县");
		cityCodes.put("330624", "浙江省新昌县");
		cityCodes.put("330681", "浙江省诸暨市");
		cityCodes.put("330682", "浙江省上虞市");
		cityCodes.put("3307", "浙江省金华市");
		cityCodes.put("330701", "浙江省金华市市辖区");
		cityCodes.put("330702", "浙江省金华市婺城区");
		cityCodes.put("330721", "浙江省金华县");
		cityCodes.put("330723", "浙江省武义县");
		cityCodes.put("330726", "浙江省浦江县");
		cityCodes.put("330727", "浙江省磐安县");
		cityCodes.put("330728", "浙江省永康市");
		cityCodes.put("330781", "浙江省兰溪市");
		cityCodes.put("330782", "浙江省义乌市");
		cityCodes.put("330783", "浙江省东阳市");
		cityCodes.put("3308", "浙江省衢州市");
		cityCodes.put("330801", "浙江省衢州市市辖区");
		cityCodes.put("330802", "浙江省衢州市柯城区");
		cityCodes.put("330821", "浙江省衢县");
		cityCodes.put("330822", "浙江省常山县");
		cityCodes.put("330824", "浙江省开化县");
		cityCodes.put("330825", "浙江省龙游县");
		cityCodes.put("330881", "浙江省江山市");
		cityCodes.put("3309", "浙江省舟山市");
		cityCodes.put("330901", "浙江省舟山市市辖区");
		cityCodes.put("330902", "浙江省舟山市定海区");
		cityCodes.put("330903", "浙江省舟山市普陀区");
		cityCodes.put("330921", "浙江省岱山县");
		cityCodes.put("330922", "浙江省嵊泗县");
		cityCodes.put("3325", "浙江省丽水地区");
		cityCodes.put("332501", "浙江省丽水市");
		cityCodes.put("332502", "浙江省龙泉市");
		cityCodes.put("332522", "浙江省青田县");
		cityCodes.put("332523", "浙江省云和县");
		cityCodes.put("332525", "浙江省庆元县");
		cityCodes.put("332526", "浙江省缙云县");
		cityCodes.put("332527", "浙江省遂昌县");
		cityCodes.put("332528", "浙江省松阳县");
		cityCodes.put("332529", "浙江省景宁畲族自治县");
		cityCodes.put("3326", "浙江省台州地区");
		cityCodes.put("332601", "浙江省椒江市");
		cityCodes.put("332602", "浙江省临海市");
		cityCodes.put("332603", "浙江省黄岩市");
		cityCodes.put("332623", "浙江省温岭县");
		cityCodes.put("332624", "浙江省仙居县");
		cityCodes.put("332625", "浙江省天台县");
		cityCodes.put("332626", "浙江省三门县");
		cityCodes.put("332627", "浙江省玉环县");
		cityCodes.put("34", "安徽省");
		cityCodes.put("3401", "安徽省合肥市");
		cityCodes.put("340101", "安徽省合肥市市辖区");
		cityCodes.put("340102", "安徽省合肥市东市区");
		cityCodes.put("340103", "安徽省合肥市中市区");
		cityCodes.put("340104", "安徽省合肥市西市区");
		cityCodes.put("340111", "安徽省合肥市郊区");
		cityCodes.put("340121", "安徽省长丰县");
		cityCodes.put("340122", "安徽省肥东县");
		cityCodes.put("340123", "安徽省肥西县");
		cityCodes.put("3402", "安徽省芜湖市");
		cityCodes.put("340201", "安徽省芜湖市市辖区");
		cityCodes.put("340202", "安徽省芜湖市镜湖区");
		cityCodes.put("340203", "安徽省芜湖市马塘区");
		cityCodes.put("340204", "安徽省芜湖市新芜区");
		cityCodes.put("340207", "安徽省芜湖市鸠江区");
		cityCodes.put("340221", "安徽省芜湖县");
		cityCodes.put("340222", "安徽省繁昌县");
		cityCodes.put("340223", "安徽省南陵县");
		cityCodes.put("3403", "安徽省蚌埠市");
		cityCodes.put("340301", "安徽省蚌埠市市辖区");
		cityCodes.put("340302", "安徽省蚌埠市东市区");
		cityCodes.put("340303", "安徽省蚌埠市中市区");
		cityCodes.put("340304", "安徽省蚌埠市西市区");
		cityCodes.put("340311", "安徽省蚌埠市郊区");
		cityCodes.put("340321", "安徽省怀远县");
		cityCodes.put("340322", "安徽省五河县");
		cityCodes.put("340323", "安徽省固镇县");
		cityCodes.put("3404", "安徽省淮南市");
		cityCodes.put("340401", "安徽省淮南市市辖区");
		cityCodes.put("340402", "安徽省淮南市大通区");
		cityCodes.put("340403", "安徽省淮南市田家庵区");
		cityCodes.put("340404", "安徽省淮南市谢家集区");
		cityCodes.put("340405", "安徽省淮南市八公山区");
		cityCodes.put("340406", "安徽省淮南市潘集区");
		cityCodes.put("340421", "安徽省凤台县");
		cityCodes.put("3405", "安徽省马鞍山市");
		cityCodes.put("340501", "安徽省马鞍山市市辖区");
		cityCodes.put("340502", "安徽省马鞍山市金家庄区");
		cityCodes.put("340503", "安徽省马鞍山市花山区");
		cityCodes.put("340504", "安徽省马鞍山市雨山区");
		cityCodes.put("340505", "安徽省马鞍山市向山区");
		cityCodes.put("340521", "安徽省当涂县");
		cityCodes.put("3406", "安徽省淮北市");
		cityCodes.put("340601", "安徽省淮北市市辖区");
		cityCodes.put("340602", "安徽省淮北市杜集区");
		cityCodes.put("340603", "安徽省淮北市相山区");
		cityCodes.put("340604", "安徽省淮北市烈山区");
		cityCodes.put("340621", "安徽省濉溪县");
		cityCodes.put("3407", "安徽省铜陵市");
		cityCodes.put("340701", "安徽省铜陵市市辖区");
		cityCodes.put("340702", "安徽省铜陵市铜官山区");
		cityCodes.put("340703", "安徽省铜陵市狮子山区");
		cityCodes.put("340711", "安徽省铜陵市郊区");
		cityCodes.put("340721", "安徽省铜陵县");
		cityCodes.put("3408", "安徽省安庆市");
		cityCodes.put("340801", "安徽省安庆市市辖区");
		cityCodes.put("340802", "安徽省安庆市迎江区");
		cityCodes.put("340803", "安徽省安庆市大观区");
		cityCodes.put("340811", "安徽省安庆市郊区");
		cityCodes.put("340821", "安徽省桐城县");
		cityCodes.put("340822", "安徽省怀宁县");
		cityCodes.put("340823", "安徽省枞阳县");
		cityCodes.put("340824", "安徽省潜山县");
		cityCodes.put("340825", "安徽省太湖县");
		cityCodes.put("340826", "安徽省宿松县");
		cityCodes.put("340827", "安徽省望江县");
		cityCodes.put("340828", "安徽省岳西县");
		cityCodes.put("3410", "安徽省黄山市");
		cityCodes.put("341001", "安徽省黄山市市辖区");
		cityCodes.put("341002", "安徽省黄山市屯溪区");
		cityCodes.put("341003", "安徽省黄山市黄山区");
		cityCodes.put("341004", "安徽省黄山市徽州区");
		cityCodes.put("341021", "安徽省歙县");
		cityCodes.put("341022", "安徽省休宁县");
		cityCodes.put("341023", "安徽省黟县");
		cityCodes.put("341024", "安徽省祁门县");
		cityCodes.put("3411", "安徽省滁州市");
		cityCodes.put("341101", "安徽省滁州市市辖区");
		cityCodes.put("341102", "安徽省滁州市琅琊区");
		cityCodes.put("341103", "安徽省滁州市南谯区");
		cityCodes.put("341122", "安徽省来安县");
		cityCodes.put("341124", "安徽省全椒县");
		cityCodes.put("341125", "安徽省定远县");
		cityCodes.put("341126", "安徽省凤阳县");
		cityCodes.put("341127", "安徽省嘉山县");
		cityCodes.put("341181", "安徽省天长县");
		cityCodes.put("341182", "安徽省滁州市明光市");
		cityCodes.put("3412", "安徽省阜阳市");
		cityCodes.put("341201", "安徽省阜阳市市辖区");
		cityCodes.put("341202", "安徽省阜阳市颍州区");
		cityCodes.put("341203", "安徽省阜阳市颍东区");
		cityCodes.put("341204", "安徽省阜阳市颍泉区");
		cityCodes.put("341221", "安徽省阜阳市临泉县");
		cityCodes.put("341222", "安徽省阜阳市太和县");
		cityCodes.put("341223", "安徽省阜阳市涡阳县");
		cityCodes.put("341224", "安徽省阜阳市蒙城县");
		cityCodes.put("341225", "安徽省阜阳市阜南县");
		cityCodes.put("341226", "安徽省阜阳市颍上县");
		cityCodes.put("341227", "安徽省阜阳市利辛县");
		cityCodes.put("341281", "安徽省阜阳市亳州市");
		cityCodes.put("341282", "安徽省阜阳市界首市");
		cityCodes.put("3413", "安徽省宿州市");
		cityCodes.put("341301", "安徽省宿州市市辖区");
		cityCodes.put("341302", "安徽省宿州市甬桥区");
		cityCodes.put("341321", "安徽省宿州市砀山县");
		cityCodes.put("341322", "安徽省宿州市萧县");
		cityCodes.put("341323", "安徽省宿州市灵璧县");
		cityCodes.put("341324", "安徽省宿州市泗县");
		cityCodes.put("3424", "安徽省六安地区");
		cityCodes.put("342401", "安徽省六安市");
		cityCodes.put("342422", "安徽省寿县");
		cityCodes.put("342423", "安徽省霍邱县");
		cityCodes.put("342425", "安徽省舒城县");
		cityCodes.put("342426", "安徽省金寨县");
		cityCodes.put("342427", "安徽省霍山县");
		cityCodes.put("3425", "安徽省宣城地区");
		cityCodes.put("342501", "安徽省宣州市");
		cityCodes.put("342522", "安徽省郎溪县");
		cityCodes.put("342523", "安徽省广德县");
		cityCodes.put("342524", "安徽省宁国县");
		cityCodes.put("342529", "安徽省泾县");
		cityCodes.put("342530", "安徽省旌德县");
		cityCodes.put("342531", "安徽省绩溪县");
		cityCodes.put("3426", "安徽省巢湖地区");
		cityCodes.put("342601", "安徽省巢湖市");
		cityCodes.put("342622", "安徽省庐江县");
		cityCodes.put("342623", "安徽省无为县");
		cityCodes.put("342625", "安徽省含山县");
		cityCodes.put("342626", "安徽省和县");
		cityCodes.put("3429", "安徽省池州地区");
		cityCodes.put("342901", "安徽省贵池县");
		cityCodes.put("342921", "安徽省东至县");
		cityCodes.put("342922", "安徽省石台县");
		cityCodes.put("342923", "安徽省青阳县");
		cityCodes.put("35", "福建省");
		cityCodes.put("3501", "福建省福州市");
		cityCodes.put("350101", "福建省福州市市辖区");
		cityCodes.put("350102", "福建省福州市鼓楼区");
		cityCodes.put("350103", "福建省福州市台江区");
		cityCodes.put("350104", "福建省福州市仓山区");
		cityCodes.put("350105", "福建省福州市马尾区");
		cityCodes.put("350111", "福建省福州市郊区");
		cityCodes.put("350121", "福建省闽侯县");
		cityCodes.put("350122", "福建省连江县");
		cityCodes.put("350123", "福建省罗源县");
		cityCodes.put("350124", "福建省闽清县");
		cityCodes.put("350125", "福建省永泰县");
		cityCodes.put("350126", "福建省长乐县");
		cityCodes.put("350128", "福建省平潭县");
		cityCodes.put("350181", "福建省福清市");
		cityCodes.put("3502", "福建省厦门市");
		cityCodes.put("350201", "福建省厦门市市辖区");
		cityCodes.put("350202", "福建省厦门市鼓浪屿区");
		cityCodes.put("350203", "福建省厦门市思明区");
		cityCodes.put("350204", "福建省厦门市开元区");
		cityCodes.put("350205", "福建省厦门市杏林区");
		cityCodes.put("350206", "福建省厦门市湖里区");
		cityCodes.put("350211", "福建省厦门市集美区");
		cityCodes.put("350221", "福建省同安县");
		cityCodes.put("3503", "福建省莆田市");
		cityCodes.put("350301", "福建省莆田市市辖区");
		cityCodes.put("350302", "福建省莆田市城厢区");
		cityCodes.put("350303", "福建省莆田市涵江区");
		cityCodes.put("350321", "福建省莆田县");
		cityCodes.put("350322", "福建省仙游县");
		cityCodes.put("3504", "福建省三明市");
		cityCodes.put("350401", "福建省三明市市辖区");
		cityCodes.put("350402", "福建省三明市梅列区");
		cityCodes.put("350403", "福建省三明市三元区");
		cityCodes.put("350421", "福建省明溪县");
		cityCodes.put("350423", "福建省清流县");
		cityCodes.put("350424", "福建省宁化县");
		cityCodes.put("350425", "福建省大田县");
		cityCodes.put("350426", "福建省尤溪县");
		cityCodes.put("350427", "福建省沙县");
		cityCodes.put("350428", "福建省将乐县");
		cityCodes.put("350429", "福建省泰宁县");
		cityCodes.put("350430", "福建省建宁县");
		cityCodes.put("350481", "福建省永安市");
		cityCodes.put("3505", "福建省泉州市");
		cityCodes.put("350501", "福建省泉州市市辖区");
		cityCodes.put("350502", "福建省泉州市鲤城区");
		cityCodes.put("350521", "福建省惠安县");
		cityCodes.put("350524", "福建省安溪县");
		cityCodes.put("350525", "福建省永春县");
		cityCodes.put("350526", "福建省德化县");
		cityCodes.put("350527", "福建省金门县");
		cityCodes.put("350581", "福建省石狮市");
		cityCodes.put("350582", "福建省晋江市");
		cityCodes.put("350583", "福建省南安市");
		cityCodes.put("3506", "福建省漳州市");
		cityCodes.put("350601", "福建省漳州市市辖区");
		cityCodes.put("350602", "福建省漳州市芗城区");
		cityCodes.put("350622", "福建省云霄县");
		cityCodes.put("350623", "福建省漳浦县");
		cityCodes.put("350624", "福建省诏安县");
		cityCodes.put("350625", "福建省长泰县");
		cityCodes.put("350626", "福建省东山县");
		cityCodes.put("350627", "福建省南靖县");
		cityCodes.put("350628", "福建省平和县");
		cityCodes.put("350629", "福建省华安县");
		cityCodes.put("350681", "福建省龙海市");
		cityCodes.put("3521", "福建省南平地区");
		cityCodes.put("352101", "福建省南平市");
		cityCodes.put("352102", "福建省邵武市");
		cityCodes.put("352103", "福建省武夷山市");
		cityCodes.put("352104", "福建省建瓯市");
		cityCodes.put("352121", "福建省顺昌县");
		cityCodes.put("352122", "福建省建阳县");
		cityCodes.put("352124", "福建省浦城县");
		cityCodes.put("352127", "福建省光泽县");
		cityCodes.put("352128", "福建省松溪县");
		cityCodes.put("352129", "福建省政和县");
		cityCodes.put("3522", "福建省宁德地区");
		cityCodes.put("352201", "福建省宁德市");
		cityCodes.put("352202", "福建省福安市");
		cityCodes.put("352224", "福建省福鼎县");
		cityCodes.put("352225", "福建省霞浦县");
		cityCodes.put("352227", "福建省古田县");
		cityCodes.put("352228", "福建省屏南县");
		cityCodes.put("352229", "福建省寿宁县");
		cityCodes.put("352230", "福建省周宁县");
		cityCodes.put("352231", "福建省柘荣县");
		cityCodes.put("3526", "福建省龙岩地区");
		cityCodes.put("352601", "福建省龙岩市");
		cityCodes.put("352602", "福建省漳平市");
		cityCodes.put("352622", "福建省长汀县");
		cityCodes.put("352623", "福建省永定县");
		cityCodes.put("352624", "福建省上杭县");
		cityCodes.put("352625", "福建省武平县");
		cityCodes.put("352627", "福建省连城县");
		cityCodes.put("36", "江西省");
		cityCodes.put("3601", "江西省南昌市");
		cityCodes.put("360101", "江西省南昌市市辖区");
		cityCodes.put("360102", "江西省南昌市东湖区");
		cityCodes.put("360103", "江西省南昌市西湖区");
		cityCodes.put("360104", "江西省南昌市青云谱区");
		cityCodes.put("360105", "江西省南昌市湾里区");
		cityCodes.put("360111", "江西省南昌市郊区");
		cityCodes.put("360121", "江西省南昌县");
		cityCodes.put("360122", "江西省新建县");
		cityCodes.put("360123", "江西省安义县");
		cityCodes.put("360124", "江西省进贤县");
		cityCodes.put("3602", "江西省景德镇市");
		cityCodes.put("360201", "江西省景德镇市市辖区");
		cityCodes.put("360202", "江西省景德镇市昌江区");
		cityCodes.put("360203", "江西省景德镇市珠山区");
		cityCodes.put("360222", "江西省浮梁县");
		cityCodes.put("360281", "江西省乐平市");
		cityCodes.put("3603", "江西省萍乡市");
		cityCodes.put("360301", "江西省萍乡市市辖区");
		cityCodes.put("360302", "江西省萍乡市安源区");
		cityCodes.put("360311", "江西省萍乡市上栗区");
		cityCodes.put("360312", "江西省萍乡市芦溪区");
		cityCodes.put("360313", "江西省萍乡市湘东区");
		cityCodes.put("360321", "江西省莲花县");
		cityCodes.put("3604", "江西省九江市");
		cityCodes.put("360401", "江西省九江市市辖区");
		cityCodes.put("360402", "江西省九江市庐山区");
		cityCodes.put("360403", "江西省浔阳县");
		cityCodes.put("360421", "江西省九江县");
		cityCodes.put("360423", "江西省武宁县");
		cityCodes.put("360424", "江西省修水县");
		cityCodes.put("360425", "江西省永修县");
		cityCodes.put("360426", "江西省德安县");
		cityCodes.put("360427", "江西省星子县");
		cityCodes.put("360428", "江西省都昌县");
		cityCodes.put("360429", "江西省湖口县");
		cityCodes.put("360430", "江西省彭泽县");
		cityCodes.put("360481", "江西省瑞昌市");
		cityCodes.put("3605", "江西省新余市");
		cityCodes.put("360501", "江西省新余市市辖区");
		cityCodes.put("360502", "江西省新余市渝水区");
		cityCodes.put("360521", "江西省分宜县");
		cityCodes.put("3606", "江西省鹰潭市");
		cityCodes.put("360601", "江西省鹰潭市市辖区");
		cityCodes.put("360602", "江西省鹰潭市月湖区");
		cityCodes.put("360621", "江西省贵溪县");
		cityCodes.put("360622", "江西省余江县");
		cityCodes.put("3621", "江西省赣州地区");
		cityCodes.put("362101", "江西省赣州市");
		cityCodes.put("362121", "江西省赣县");
		cityCodes.put("362122", "江西省南康县");
		cityCodes.put("362123", "江西省信丰县");
		cityCodes.put("362124", "江西省大余县");
		cityCodes.put("362125", "江西省上犹县");
		cityCodes.put("362126", "江西省崇义县");
		cityCodes.put("362127", "江西省安远县");
		cityCodes.put("362128", "江西省龙南县");
		cityCodes.put("362129", "江西省定南县");
		cityCodes.put("362130", "江西省全南县");
		cityCodes.put("362131", "江西省宁都县");
		cityCodes.put("362132", "江西省于都县");
		cityCodes.put("362133", "江西省兴国县");
		cityCodes.put("362134", "江西省瑞金县");
		cityCodes.put("362135", "江西省会昌县");
		cityCodes.put("362136", "江西省寻乌县");
		cityCodes.put("362137", "江西省石城县");
		cityCodes.put("3622", "江西省宜春地区");
		cityCodes.put("362201", "江西省宜春市");
		cityCodes.put("362202", "江西省丰城市");
		cityCodes.put("362203", "江西省樟树市");
		cityCodes.put("362204", "江西省高安市");
		cityCodes.put("362226", "江西省奉新县");
		cityCodes.put("362227", "江西省万载县");
		cityCodes.put("362228", "江西省上高县");
		cityCodes.put("362229", "江西省宜丰县");
		cityCodes.put("362232", "江西省靖安县");
		cityCodes.put("362233", "江西省铜鼓县");
		cityCodes.put("3623", "江西省上饶地区");
		cityCodes.put("362301", "江西省上饶市");
		cityCodes.put("362302", "江西省德兴市");
		cityCodes.put("362321", "江西省上饶县");
		cityCodes.put("362322", "江西省广丰县");
		cityCodes.put("362323", "江西省玉山县");
		cityCodes.put("362324", "江西省铅山县");
		cityCodes.put("362325", "江西省横峰县");
		cityCodes.put("362326", "江西省弋阳县");
		cityCodes.put("362329", "江西省余干县");
		cityCodes.put("362330", "江西省波阳县");
		cityCodes.put("362331", "江西省万年县");
		cityCodes.put("362334", "江西省婺源县");
		cityCodes.put("3624", "江西省吉安地区");
		cityCodes.put("362401", "江西省吉安市");
		cityCodes.put("362402", "江西省井岗山市");
		cityCodes.put("362421", "江西省吉安县");
		cityCodes.put("362422", "江西省吉水县");
		cityCodes.put("362423", "江西省峡江县");
		cityCodes.put("362424", "江西省新干县");
		cityCodes.put("362425", "江西省永丰县");
		cityCodes.put("362426", "江西省泰和县");
		cityCodes.put("362427", "江西省遂川县");
		cityCodes.put("362428", "江西省万安县");
		cityCodes.put("362429", "江西省安福县");
		cityCodes.put("362430", "江西省永新县");
		cityCodes.put("362432", "江西省宁冈县");
		cityCodes.put("3625", "江西省抚州地区");
		cityCodes.put("362502", "江西省临川市");
		cityCodes.put("362522", "江西省南城县");
		cityCodes.put("362523", "江西省黎川县");
		cityCodes.put("362524", "江西省南丰县");
		cityCodes.put("362525", "江西省崇仁县");
		cityCodes.put("362526", "江西省乐安县");
		cityCodes.put("362527", "江西省宜黄县");
		cityCodes.put("362528", "江西省金溪县");
		cityCodes.put("362529", "江西省资溪县");
		cityCodes.put("362531", "江西省东乡县");
		cityCodes.put("362532", "江西省广昌县");
		cityCodes.put("37", "山东省");
		cityCodes.put("3701", "山东省济南市");
		cityCodes.put("370101", "山东省济南市市辖区");
		cityCodes.put("370102", "山东省济南市历下区");
		cityCodes.put("370103", "山东省济南市市中区");
		cityCodes.put("370104", "山东省济南市槐荫区");
		cityCodes.put("370105", "山东省济南市天桥区");
		cityCodes.put("370112", "山东省济南市历城区");
		cityCodes.put("370123", "山东省长清县");
		cityCodes.put("370124", "山东省平阴县");
		cityCodes.put("370125", "山东省商河县");
		cityCodes.put("370126", "山东省济阳县");
		cityCodes.put("370181", "山东省章丘市");
		cityCodes.put("3702", "山东省青岛市");
		cityCodes.put("370201", "山东省青岛市市辖区");
		cityCodes.put("370202", "山东省青岛市市南区");
		cityCodes.put("370203", "山东省青岛市市北区");
		cityCodes.put("370204", "山东省青岛市台东区");
		cityCodes.put("370205", "山东省青岛市四方区");
		cityCodes.put("370206", "山东省青岛市沧口区");
		cityCodes.put("370211", "山东省青岛市黄岛区");
		cityCodes.put("370212", "山东省青岛市崂山区");
		cityCodes.put("370281", "山东省胶州市");
		cityCodes.put("370282", "山东省即墨市");
		cityCodes.put("370283", "山东省平度市");
		cityCodes.put("370284", "山东省胶南市");
		cityCodes.put("370285", "山东省菜西市");
		cityCodes.put("3703", "山东省淄博市");
		cityCodes.put("370301", "山东省淄博市市辖区");
		cityCodes.put("370302", "山东省淄博市淄川区");
		cityCodes.put("370303", "山东省淄博市张店区");
		cityCodes.put("370304", "山东省淄博市博山区");
		cityCodes.put("370305", "山东省淄博市临淄区");
		cityCodes.put("370306", "山东省淄博市周村区");
		cityCodes.put("370321", "山东省桓台县");
		cityCodes.put("370322", "山东省高青县");
		cityCodes.put("370323", "山东省沂源县");
		cityCodes.put("3704", "山东省枣庄市");
		cityCodes.put("370401", "山东省枣庄市市辖区");
		cityCodes.put("370402", "山东省枣庄市市中区");
		cityCodes.put("370403", "山东省枣庄市薛城区");
		cityCodes.put("370404", "山东省枣庄市峄城区");
		cityCodes.put("370405", "山东省枣庄市台儿庄区");
		cityCodes.put("370406", "山东省枣庄市山亭区");
		cityCodes.put("370481", "山东省滕州市");
		cityCodes.put("3705", "山东省东营市");
		cityCodes.put("370501", "山东省东营市市辖区");
		cityCodes.put("370502", "山东省东营市东营区");
		cityCodes.put("370503", "山东省东营市河口区");
		cityCodes.put("370521", "山东省垦利县");
		cityCodes.put("370522", "山东省利津县");
		cityCodes.put("370523", "山东省广饶县");
		cityCodes.put("3706", "山东省烟台市");
		cityCodes.put("370601", "山东省烟台市市辖区");
		cityCodes.put("370602", "山东省烟台市芝罘区");
		cityCodes.put("370611", "山东省烟台市福山区");
		cityCodes.put("370628", "山东省栖霞县");
		cityCodes.put("370629", "山东省海阳县");
		cityCodes.put("370631", "山东省牟平县");
		cityCodes.put("370634", "山东省长岛县");
		cityCodes.put("370681", "山东省龙口市");
		cityCodes.put("370682", "山东省莱阳市");
		cityCodes.put("370683", "山东省莱州市");
		cityCodes.put("370684", "山东省蓬莱市");
		cityCodes.put("370685", "山东省招远市");
		cityCodes.put("3707", "山东省潍坊市");
		cityCodes.put("370701", "山东省潍坊市市辖区");
		cityCodes.put("370702", "山东省潍坊市潍城区");
		cityCodes.put("370703", "山东省潍坊市寒亭区");
		cityCodes.put("370704", "山东省潍坊市坊子区");
		cityCodes.put("370722", "山东省安丘县");
		cityCodes.put("370724", "山东省临朐县");
		cityCodes.put("370725", "山东省昌乐县");
		cityCodes.put("370726", "山东省昌邑县");
		cityCodes.put("370727", "山东省高密县");
		cityCodes.put("370781", "山东省青州市");
		cityCodes.put("370782", "山东省诸城市");
		cityCodes.put("370783", "山东省寿光市");
		cityCodes.put("3708", "山东省济宁市");
		cityCodes.put("370801", "山东省济宁市市辖区");
		cityCodes.put("370802", "山东省济宁市市中区");
		cityCodes.put("370811", "山东省济宁市任城区");
		cityCodes.put("370826", "山东省微山县");
		cityCodes.put("370827", "山东省鱼台县");
		cityCodes.put("370828", "山东省金乡县");
		cityCodes.put("370829", "山东省嘉祥县");
		cityCodes.put("370830", "山东省汶上县");
		cityCodes.put("370831", "山东省泗水县");
		cityCodes.put("370832", "山东省梁山县");
		cityCodes.put("370881", "山东省曲阜市");
		cityCodes.put("370882", "山东省兖州市");
		cityCodes.put("370883", "山东省邹城市");
		cityCodes.put("3709", "山东省泰安市");
		cityCodes.put("370901", "山东省泰安市市辖区");
		cityCodes.put("370902", "山东省泰安市泰山区");
		cityCodes.put("370911", "山东省泰安市郊区");
		cityCodes.put("370921", "山东省宁阳县");
		cityCodes.put("370923", "山东省东平县");
		cityCodes.put("370982", "山东省新泰市");
		cityCodes.put("370983", "山东省肥城市");
		cityCodes.put("3710", "山东省威海市");
		cityCodes.put("371001", "山东省威海市市辖区");
		cityCodes.put("371002", "山东省威海市环翠区");
		cityCodes.put("371081", "山东省文登市");
		cityCodes.put("371082", "山东省荣城市");
		cityCodes.put("371083", "山东省乳山市");
		cityCodes.put("3711", "山东省日照市");
		cityCodes.put("371101", "山东省日照市市辖区");
		cityCodes.put("371102", "山东省日照市东港区");
		cityCodes.put("371121", "山东省五莲县");
		cityCodes.put("371122", "山东省莒县");
		cityCodes.put("3712", "山东省莱芜市");
		cityCodes.put("371201", "山东省莱芜市市辖区");
		cityCodes.put("371202", "山东省莱芜市莱城区");
		cityCodes.put("371203", "山东省莱芜市钢城区");
		cityCodes.put("3723", "山东省滨州地区");
		cityCodes.put("372301", "山东省滨州市");
		cityCodes.put("372321", "山东省惠民县");
		cityCodes.put("372323", "山东省阳信县");
		cityCodes.put("372324", "山东省无棣县");
		cityCodes.put("372325", "山东省沾化县");
		cityCodes.put("372328", "山东省博兴县");
		cityCodes.put("372330", "山东省邹平县");
		cityCodes.put("3724", "山东省德州地区");
		cityCodes.put("372401", "山东省德州市");
		cityCodes.put("372402", "山东省乐陵市");
		cityCodes.put("372403", "山东省禹城市");
		cityCodes.put("372421", "山东省陵县");
		cityCodes.put("372422", "山东省平原县");
		cityCodes.put("372423", "山东省夏津县");
		cityCodes.put("372424", "山东省武城县");
		cityCodes.put("372425", "山东省齐河县");
		cityCodes.put("372428", "山东省临邑县");
		cityCodes.put("372431", "山东省宁津县");
		cityCodes.put("372432", "山东省庆云县");
		cityCodes.put("3725", "山东省聊城地区");
		cityCodes.put("372501", "山东省聊城市");
		cityCodes.put("372502", "山东省临清市");
		cityCodes.put("372522", "山东省阳谷县");
		cityCodes.put("372523", "山东省莘县");
		cityCodes.put("372524", "山东省茌平县");
		cityCodes.put("372525", "山东省东阿县");
		cityCodes.put("372526", "山东省冠县");
		cityCodes.put("372527", "山东省高唐县");
		cityCodes.put("3728", "山东省临沂地区");
		cityCodes.put("372801", "山东省临沂市");
		cityCodes.put("372822", "山东省郯城县");
		cityCodes.put("372823", "山东省苍山县");
		cityCodes.put("372824", "山东省莒南县");
		cityCodes.put("372827", "山东省沂水县");
		cityCodes.put("372829", "山东省蒙阴县");
		cityCodes.put("372830", "山东省平邑县");
		cityCodes.put("372831", "山东省费县");
		cityCodes.put("372832", "山东省沂南县");
		cityCodes.put("372833", "山东省临沭县");
		cityCodes.put("3729", "山东省菏泽地区");
		cityCodes.put("372901", "山东省菏泽市");
		cityCodes.put("372922", "山东省曹县");
		cityCodes.put("372923", "山东省定陶县");
		cityCodes.put("372924", "山东省成武县");
		cityCodes.put("372925", "山东省单县");
		cityCodes.put("372926", "山东省巨野县");
		cityCodes.put("372928", "山东省郓城县");
		cityCodes.put("372929", "山东省鄄城县");
		cityCodes.put("372930", "山东省东明县");
		cityCodes.put("41", "河南省");
		cityCodes.put("4101", "河南省郑州市");
		cityCodes.put("410101", "河南省郑州市市辖区");
		cityCodes.put("410102", "河南省郑州市中原区");
		cityCodes.put("410103", "河南省郑州市二七区");
		cityCodes.put("410104", "河南省郑州市管城回族区");
		cityCodes.put("410105", "河南省郑州市金水区");
		cityCodes.put("410106", "河南省郑州市上街区");
		cityCodes.put("410108", "河南省郑州市邙山区");
		cityCodes.put("410121", "河南省荥阳县");
		cityCodes.put("410122", "河南省中牟县");
		cityCodes.put("410123", "河南省新郑县");
		cityCodes.put("410125", "河南省登封县");
		cityCodes.put("410126", "河南省密县");
		cityCodes.put("410181", "河南省巩义市");
		cityCodes.put("4102", "河南省开封市");
		cityCodes.put("410201", "河南省开封市市辖区");
		cityCodes.put("410202", "河南省开封市龙亭区");
		cityCodes.put("410203", "河南省开封市顺河回族区");
		cityCodes.put("410204", "河南省开封市鼓楼区");
		cityCodes.put("410205", "河南省开封市南关区");
		cityCodes.put("410211", "河南省开封市郊区");
		cityCodes.put("410221", "河南省杞县");
		cityCodes.put("410222", "河南省通许县");
		cityCodes.put("410223", "河南省尉氏县");
		cityCodes.put("410224", "河南省开封县");
		cityCodes.put("410225", "河南省兰考县");
		cityCodes.put("4103", "河南省洛阳市");
		cityCodes.put("410301", "河南省洛阳市市辖区");
		cityCodes.put("410302", "河南省洛阳市老城区");
		cityCodes.put("410303", "河南省洛阳市西工区");
		cityCodes.put("410304", "河南省洛阳市廛河回族区");
		cityCodes.put("410305", "河南省洛阳市涧西区");
		cityCodes.put("410306", "河南省洛阳市吉利区");
		cityCodes.put("410311", "河南省洛阳市郊区");
		cityCodes.put("410322", "河南省孟津县");
		cityCodes.put("410323", "河南省新安县");
		cityCodes.put("410324", "河南省栾川县");
		cityCodes.put("410325", "河南省嵩县");
		cityCodes.put("410326", "河南省汝阳县");
		cityCodes.put("410327", "河南省宜阳县");
		cityCodes.put("410328", "河南省洛宁县");
		cityCodes.put("410329", "河南省伊川县");
		cityCodes.put("410381", "河南省偃师市");
		cityCodes.put("4104", "河南省平顶山市");
		cityCodes.put("410401", "河南省平顶山市市辖区");
		cityCodes.put("410402", "河南省平顶山市新华区");
		cityCodes.put("410403", "河南省平顶山市卫东区");
		cityCodes.put("410411", "河南省平顶山市郊区");
		cityCodes.put("410421", "河南省宝丰县");
		cityCodes.put("410422", "河南省叶县");
		cityCodes.put("410423", "河南省鲁山县");
		cityCodes.put("410425", "河南省郏县");
		cityCodes.put("410426", "河南省襄城县");
		cityCodes.put("410481", "河南省舞钢市");
		cityCodes.put("410482", "河南省汝州市");
		cityCodes.put("4105", "河南省安阳市");
		cityCodes.put("410501", "河南省安阳市市辖区");
		cityCodes.put("410502", "河南省安阳市文峰区");
		cityCodes.put("410503", "河南省安阳市北关区");
		cityCodes.put("410504", "河南省安阳市铁西区");
		cityCodes.put("410511", "河南省安阳市郊区");
		cityCodes.put("410521", "河南省林县");
		cityCodes.put("410522", "河南省安阳县");
		cityCodes.put("410523", "河南省汤阴县");
		cityCodes.put("410526", "河南省滑县");
		cityCodes.put("410527", "河南省内黄县");
		cityCodes.put("4106", "河南省鹤壁市");
		cityCodes.put("410601", "河南省鹤壁市市辖区");
		cityCodes.put("410602", "河南省鹤壁市鹤山区");
		cityCodes.put("410603", "河南省鹤壁市山城区");
		cityCodes.put("410611", "河南省鹤壁市郊区");
		cityCodes.put("410621", "河南省浚县");
		cityCodes.put("410622", "河南省淇县");
		cityCodes.put("4107", "河南省新乡市　　　　　　");
		cityCodes.put("410701", "河南省新乡市市辖区");
		cityCodes.put("410702", "河南省新乡市红旗区");
		cityCodes.put("410703", "河南省新乡市新华区");
		cityCodes.put("410704", "河南省新乡市北站区");
		cityCodes.put("410711", "河南省新乡市郊区");
		cityCodes.put("410721", "河南省新乡县");
		cityCodes.put("410724", "河南省获嘉县");
		cityCodes.put("410725", "河南省原阳县");
		cityCodes.put("410726", "河南省延津县");
		cityCodes.put("410727", "河南省封丘县");
		cityCodes.put("410728", "河南省长恒县");
		cityCodes.put("410781", "河南省卫辉市");
		cityCodes.put("410782", "河南省辉县市");
		cityCodes.put("4108", "河南省焦作市");
		cityCodes.put("410801", "河南省焦作市市辖区");
		cityCodes.put("410802", "河南省焦作市解放区");
		cityCodes.put("410803", "河南省焦作市中站区");
		cityCodes.put("410804", "河南省焦作市马村区");
		cityCodes.put("410811", "河南省焦作市山阳区");
		cityCodes.put("410821", "河南省修武县");
		cityCodes.put("410822", "河南省博爱县");
		cityCodes.put("410823", "河南省武陟县");
		cityCodes.put("410825", "河南省温县");
		cityCodes.put("410826", "河南省孟县");
		cityCodes.put("410881", "河南省济源市");
		cityCodes.put("410882", "河南省沁阳市");
		cityCodes.put("4109", "河南省濮阳市");
		cityCodes.put("410901", "河南省濮阳市市辖区");
		cityCodes.put("410902", "河南省濮阳市市区");
		cityCodes.put("410922", "河南省清丰县");
		cityCodes.put("410923", "河南省南乐县");
		cityCodes.put("410926", "河南省范县");
		cityCodes.put("410927", "河南省台前县");
		cityCodes.put("410928", "河南省濮阳县");
		cityCodes.put("4110", "河南省许昌市");
		cityCodes.put("411001", "河南省许昌市市辖区");
		cityCodes.put("411002", "河南省许昌市魏都区");
		cityCodes.put("411023", "河南省许昌县");
		cityCodes.put("411024", "河南省鄢陵县");
		cityCodes.put("411081", "河南省禹州市");
		cityCodes.put("411082", "河南省长葛市");
		cityCodes.put("4111", "河南省漯河市");
		cityCodes.put("411101", "河南省漯河市市辖区");
		cityCodes.put("411102", "河南省漯河市源仁区");
		cityCodes.put("411121", "河南省舞阳县");
		cityCodes.put("411122", "河南省临颖县");
		cityCodes.put("411123", "河南省郾城县");
		cityCodes.put("4112", "河南省三门峡市");
		cityCodes.put("411201", "河南省三门峡市市辖区");
		cityCodes.put("411202", "河南省三门峡市湖滨区");
		cityCodes.put("411221", "河南省渑池县");
		cityCodes.put("411222", "河南省陕县");
		cityCodes.put("411224", "河南省卢氏县");
		cityCodes.put("411281", "河南省义马市");
		cityCodes.put("411282", "河南省灵宝市");
		cityCodes.put("4123", "河南省商丘地区");
		cityCodes.put("412301", "河南省商丘市");
		cityCodes.put("412321", "河南省虞城县");
		cityCodes.put("412322", "河南省商丘县");
		cityCodes.put("412323", "河南省民权县");
		cityCodes.put("412324", "河南省宁陵县");
		cityCodes.put("412325", "河南省睢县");
		cityCodes.put("412326", "河南省夏邑县");
		cityCodes.put("412327", "河南省柘城县");
		cityCodes.put("412328", "河南省永城县");
		cityCodes.put("4127", "河南省周口地区");
		cityCodes.put("412701", "河南省周口市");
		cityCodes.put("412702", "河南省项城市");
		cityCodes.put("412721", "河南省扶沟县");
		cityCodes.put("412722", "河南省西华县");
		cityCodes.put("412723", "河南省商水县");
		cityCodes.put("412724", "河南省太康县");
		cityCodes.put("412725", "河南省鹿邑县");
		cityCodes.put("412726", "河南省郸城县");
		cityCodes.put("412727", "河南省淮阳县");
		cityCodes.put("412728", "河南省沈丘县");
		cityCodes.put("4128", "河南省驻马店地区");
		cityCodes.put("412801", "河南省驻马店市");
		cityCodes.put("412821", "河南省确山县");
		cityCodes.put("412822", "河南省泌阳县");
		cityCodes.put("412823", "河南省遂平县");
		cityCodes.put("412824", "河南省西平县");
		cityCodes.put("412825", "河南省上蔡县");
		cityCodes.put("412826", "河南省汝南县");
		cityCodes.put("412827", "河南省平舆县");
		cityCodes.put("412828", "河南省新蔡县");
		cityCodes.put("412829", "河南省正阳县");
		cityCodes.put("4129", "河南省南阳地区");
		cityCodes.put("412901", "河南省南阳市");
		cityCodes.put("412902", "河南省邓州市");
		cityCodes.put("412921", "河南省南召县");
		cityCodes.put("412922", "河南省方城县");
		cityCodes.put("412923", "河南省西峡县");
		cityCodes.put("412924", "河南省南阳县");
		cityCodes.put("412925", "河南省镇平县");
		cityCodes.put("412926", "河南省内乡县");
		cityCodes.put("412927", "河南省淅川县");
		cityCodes.put("412928", "河南省社旗县");
		cityCodes.put("412929", "河南省唐河县");
		cityCodes.put("412931", "河南省新野县");
		cityCodes.put("412932", "河南省桐柏县");
		cityCodes.put("4130", "河南省信阳地区");
		cityCodes.put("413001", "河南省信阳市");
		cityCodes.put("413021", "河南省息县");
		cityCodes.put("413022", "河南省淮滨县");
		cityCodes.put("413023", "河南省信阳县");
		cityCodes.put("413024", "河南省横川县");
		cityCodes.put("413025", "河南省光山县");
		cityCodes.put("413026", "河南省固始县");
		cityCodes.put("413027", "河南省商城县");
		cityCodes.put("413028", "河南省罗山县");
		cityCodes.put("413029", "河南省新县");
		cityCodes.put("42", "湖北省");
		cityCodes.put("4201", "湖北省武汉市");
		cityCodes.put("420101", "湖北省武汉市市辖区");
		cityCodes.put("420102", "湖北省武汉市江岸区");
		cityCodes.put("420103", "湖北省武汉市江汉区");
		cityCodes.put("420104", "湖北省武汉市乔口区");
		cityCodes.put("420105", "湖北省武汉市汉阳区");
		cityCodes.put("420106", "湖北省武汉市武昌区");
		cityCodes.put("420107", "湖北省武汉市青山区");
		cityCodes.put("420111", "湖北省武汉市洪山区");
		cityCodes.put("420112", "湖北省武汉市东西湖区");
		cityCodes.put("420113", "湖北省武汉市汉南区");
		cityCodes.put("420114", "湖北省蔡甸区");
		cityCodes.put("420122", "湖北省武昌县");
		cityCodes.put("420123", "湖北省黄陂县");
		cityCodes.put("420124", "湖北省新洲县");
		cityCodes.put("4202", "湖北省黄石市");
		cityCodes.put("420201", "湖北省黄石市市辖区");
		cityCodes.put("420202", "湖北省黄石市黄石港区");
		cityCodes.put("420203", "湖北省黄石市石灰窑区");
		cityCodes.put("420204", "湖北省黄石市下陆区");
		cityCodes.put("420205", "湖北省黄石市铁山区");
		cityCodes.put("420221", "湖北省大冶县");
		cityCodes.put("4203", "湖北省十堰市");
		cityCodes.put("420301", "湖北省十堰市市辖区");
		cityCodes.put("420302", "湖北省十堰市茅箭区");
		cityCodes.put("420303", "湖北省十堰市张湾区");
		cityCodes.put("4204", "湖北省沙市市");
		cityCodes.put("420400", "湖北省沙市市");
		cityCodes.put("4205", "湖北省宜昌市");
		cityCodes.put("420501", "湖北省宜昌市市辖区");
		cityCodes.put("420502", "湖北省宜昌市西陵区");
		cityCodes.put("420503", "湖北省宜昌市伍家岗区");
		cityCodes.put("420504", "湖北省宜昌市点军区");
		cityCodes.put("420521", "湖北省宜昌县");
		cityCodes.put("420523", "湖北省枝江县");
		cityCodes.put("420525", "湖北省远安县");
		cityCodes.put("420526", "湖北省兴山县");
		cityCodes.put("420527", "湖北省秭归县");
		cityCodes.put("420528", "湖北省长阳土家族自治县");
		cityCodes.put("420529", "湖北省五峰土家族自治县");
		cityCodes.put("420581", "湖北省枝城市");
		cityCodes.put("420582", "湖北省当阳市");
		cityCodes.put("4206", "湖北省襄樊市");
		cityCodes.put("420601", "湖北省襄樊市市辖区");
		cityCodes.put("420602", "湖北省襄樊市襄城区");
		cityCodes.put("420603", "湖北省襄樊市樊东区");
		cityCodes.put("420604", "湖北省襄樊市樊西区");
		cityCodes.put("420605", "湖北省襄樊市郊区");
		cityCodes.put("420621", "湖北省襄阳县");
		cityCodes.put("420623", "湖北省宜城县");
		cityCodes.put("420624", "湖北省南漳县");
		cityCodes.put("420625", "湖北省谷城县");
		cityCodes.put("420626", "湖北省保康县");
		cityCodes.put("420681", "湖北省随州市");
		cityCodes.put("420682", "湖北省老河口市");
		cityCodes.put("420683", "湖北省枣阳市");
		cityCodes.put("4207", "湖北省鄂州市");
		cityCodes.put("420701", "湖北省鄂州市市辖区");
		cityCodes.put("420702", "湖北省鄂州市梁子湖区");
		cityCodes.put("420703", "湖北省鄂州市谷容区");
		cityCodes.put("420704", "湖北省鄂州市鄂城区");
		cityCodes.put("4208", "湖北省荆门市");
		cityCodes.put("420801", "湖北省荆门市市辖区");
		cityCodes.put("420802", "湖北省荆门市东宝区");
		cityCodes.put("420803", "湖北省荆门市沙洋区");
		cityCodes.put("4209", "湖北省孝感市");
		cityCodes.put("420901", "湖北省孝感市市辖区");
		cityCodes.put("420902", "湖北省孝感市孝南区");
		cityCodes.put("420903", "湖北省孝感市孝昌区");
		cityCodes.put("420922", "湖北省大悟县");
		cityCodes.put("420923", "湖北省云梦县");
		cityCodes.put("420924", "湖北省汉川县");
		cityCodes.put("420981", "湖北省应城市");
		cityCodes.put("420982", "湖北省安陆市");
		cityCodes.put("420983", "湖北省广水市");
		cityCodes.put("4221", "湖北省黄冈地区");
		cityCodes.put("422101", "湖北省麻城市");
		cityCodes.put("422102", "湖北省武穴市　　　　　　");
		cityCodes.put("422103", "湖北省黄州市");
		cityCodes.put("422123", "湖北省红安县");
		cityCodes.put("422125", "湖北省罗田县");
		cityCodes.put("422126", "湖北省英山县");
		cityCodes.put("422127", "湖北省浠水县");
		cityCodes.put("422128", "湖北省蕲春县");
		cityCodes.put("422130", "湖北省黄梅县");
		cityCodes.put("4223", "湖北省咸宁地区");
		cityCodes.put("422301", "湖北省咸宁市");
		cityCodes.put("422302", "湖北省蒲圻市");
		cityCodes.put("422322", "湖北省嘉鱼县");
		cityCodes.put("422324", "湖北省通城县");
		cityCodes.put("422325", "湖北省崇阳县");
		cityCodes.put("422326", "湖北省通山县");
		cityCodes.put("422327", "湖北省阳新县");
		cityCodes.put("4224", "湖北省荆州地区");
		cityCodes.put("422401", "湖北省仙桃市");
		cityCodes.put("422402", "湖北省石首市");
		cityCodes.put("422403", "湖北省洪湖市");
		cityCodes.put("422404", "湖北省天门市");
		cityCodes.put("422405", "湖北省潜江市");
		cityCodes.put("422406", "湖北省钟祥市");
		cityCodes.put("422421", "湖北省江陵县");
		cityCodes.put("422422", "湖北省松滋县");
		cityCodes.put("422423", "湖北省**县");
		cityCodes.put("422425", "湖北省监利县");
		cityCodes.put("422432", "湖北省京山县");
		cityCodes.put("4226", "湖北省郧阳地区");
		cityCodes.put("422601", "湖北省丹江口市");
		cityCodes.put("422622", "湖北省郧县");
		cityCodes.put("422623", "湖北省郧西县");
		cityCodes.put("422624", "湖北省竹山县");
		cityCodes.put("422625", "湖北省竹溪县");
		cityCodes.put("422626", "湖北省房县");
		cityCodes.put("4228", "湖北省恩施土家族苗族自治州");
		cityCodes.put("422801", "湖北省恩施市");
		cityCodes.put("422802", "湖北省利川市");
		cityCodes.put("422822", "湖北省建始县");
		cityCodes.put("422823", "湖北省巴东县");
		cityCodes.put("422825", "湖北省宣恩县");
		cityCodes.put("422826", "湖北省咸丰县");
		cityCodes.put("422827", "湖北省来凤县");
		cityCodes.put("422828", "湖北省鹤峰县");
		cityCodes.put("4229", "湖北省省直辖行政单位");
		cityCodes.put("422921", "湖北省神农架林区");
		cityCodes.put("43", "湖南省");
		cityCodes.put("4301", "湖南省长沙市");
		cityCodes.put("430101", "湖南省长沙市市辖区");
		cityCodes.put("430102", "湖南省长沙市东区");
		cityCodes.put("430103", "湖南省长沙市南区");
		cityCodes.put("430104", "湖南省长沙市西区");
		cityCodes.put("430105", "湖南省长沙市北区");
		cityCodes.put("430111", "湖南省长沙市郊区");
		cityCodes.put("430121", "湖南省长沙县　　");
		cityCodes.put("430122", "湖南省望城县");
		cityCodes.put("430124", "湖南省宁乡县");
		cityCodes.put("430181", "湖南省浏阳市");
		cityCodes.put("4302", "湖南省株洲市");
		cityCodes.put("430201", "湖南省株洲市市辖区");
		cityCodes.put("430202", "湖南省株洲市东区");
		cityCodes.put("430203", "湖南省株洲市北区");
		cityCodes.put("430204", "湖南省株洲市南区");
		cityCodes.put("430211", "湖南省株洲市郊区");
		cityCodes.put("430221", "湖南省株洲县");
		cityCodes.put("430223", "湖南省攸县");
		cityCodes.put("430224", "湖南省茶陵县");
		cityCodes.put("430225", "湖南省酃县");
		cityCodes.put("430281", "湖南省醴陵市");
		cityCodes.put("4303", "湖南省湘潭市");
		cityCodes.put("430301", "湖南省湘潭市市辖区");
		cityCodes.put("430302", "湖南省湘潭市雨湖区");
		cityCodes.put("430304", "湖南省湘潭市岳塘区");
		cityCodes.put("430321", "湖南省湘潭县");
		cityCodes.put("430381", "湖南省湘乡市");
		cityCodes.put("430382", "湖南省韶山市");
		cityCodes.put("4304", "湖南省衡阳市");
		cityCodes.put("430401", "湖南省衡阳市市辖区");
		cityCodes.put("430402", "湖南省衡阳市江东区");
		cityCodes.put("430403", "湖南省衡阳市城南区");
		cityCodes.put("430404", "湖南省衡阳市城北区");
		cityCodes.put("430411", "湖南省衡阳市郊区");
		cityCodes.put("430412", "湖南省衡阳市南岳区");
		cityCodes.put("430421", "湖南省衡阳县");
		cityCodes.put("430422", "湖南省衡南县");
		cityCodes.put("430423", "湖南省衡山县");
		cityCodes.put("430424", "湖南省衡东县");
		cityCodes.put("430425", "湖南省常宁县");
		cityCodes.put("430426", "湖南省祁东县");
		cityCodes.put("430481", "湖南省耒阳市");
		cityCodes.put("4305", "湖南省邵阳市");
		cityCodes.put("430501", "湖南省邵阳市市辖区");
		cityCodes.put("430502", "湖南省邵阳市东区");
		cityCodes.put("430503", "湖南省邵阳市西区");
		cityCodes.put("430511", "湖南省邵阳市郊区");
		cityCodes.put("430521", "湖南省邵东县");
		cityCodes.put("430522", "湖南省新邵县");
		cityCodes.put("430523", "湖南省邵阳县");
		cityCodes.put("430524", "湖南省隆回县");
		cityCodes.put("430525", "湖南省洞口县");
		cityCodes.put("430526", "湖南省武冈县");
		cityCodes.put("430527", "湖南省绥宁县");
		cityCodes.put("430528", "湖南省新宁县");
		cityCodes.put("430529", "湖南省城步苗族自治县");
		cityCodes.put("4306", "湖南省岳阳市");
		cityCodes.put("430601", "湖南省岳阳市市辖区");
		cityCodes.put("430602", "湖南省岳阳市南区");
		cityCodes.put("430603", "湖南省岳阳市北区");
		cityCodes.put("430611", "湖南省岳阳市郊区");
		cityCodes.put("430621", "湖南省岳阳县");
		cityCodes.put("430623", "湖南省华容县");
		cityCodes.put("430624", "湖南省湘阴县");
		cityCodes.put("430626", "湖南省平江县");
		cityCodes.put("430681", "湖南省汨罗市");
		cityCodes.put("430682", "湖南省临湘市");
		cityCodes.put("4307", "湖南省常德市");
		cityCodes.put("430701", "湖南省常德市市辖区");
		cityCodes.put("430702", "湖南省常德市武陵区");
		cityCodes.put("430703", "湖南省常德市鼎城区");
		cityCodes.put("430721", "湖南省安乡县");
		cityCodes.put("430722", "湖南省汉寿县");
		cityCodes.put("430723", "湖南省澧县");
		cityCodes.put("430724", "湖南省临澧县");
		cityCodes.put("430725", "湖南省桃源县");
		cityCodes.put("430726", "湖南省石门县");
		cityCodes.put("430781", "湖南省津市市");
		cityCodes.put("4308", "湖南省大庸市");
		cityCodes.put("430801", "湖南省大庸市市辖区");
		cityCodes.put("430802", "湖南省大庸市永定区");
		cityCodes.put("430811", "湖南省大庸市武陵源区");
		cityCodes.put("430821", "湖南省慈利县");
		cityCodes.put("430822", "湖南省桑植县");
		cityCodes.put("4323", "湖南省益阳地区");
		cityCodes.put("432301", "湖南省益阳市");
		cityCodes.put("432302", "湖南省沅江市");
		cityCodes.put("432321", "湖南省益阳县");
		cityCodes.put("432322", "湖南省南县");
		cityCodes.put("432325", "湖南省桃江县");
		cityCodes.put("432326", "湖南省安化县");
		cityCodes.put("4325", "湖南省娄底地区");
		cityCodes.put("432501", "湖南省娄底市");
		cityCodes.put("432502", "湖南省冷水江市");
		cityCodes.put("432503", "湖南省涟源市");
		cityCodes.put("432522", "湖南省双峰县");
		cityCodes.put("432524", "湖南省新化县");
		cityCodes.put("4328", "湖南省郴州地区");
		cityCodes.put("432801", "湖南省郴州市");
		cityCodes.put("432802", "湖南省资兴市");
		cityCodes.put("432821", "湖南省郴县");
		cityCodes.put("432822", "湖南省桂阳县");
		cityCodes.put("432823", "湖南省永兴县");
		cityCodes.put("432824", "湖南省宜章县");
		cityCodes.put("432826", "湖南省嘉禾县");
		cityCodes.put("432827", "湖南省临武县");
		cityCodes.put("432828", "湖南省汝城县");
		cityCodes.put("432829", "湖南省桂东县");
		cityCodes.put("432831", "湖南省安仁县");
		cityCodes.put("4329", "湖南省零陵地区");
		cityCodes.put("432901", "湖南省永州市");
		cityCodes.put("432902", "湖南省冷水滩市");
		cityCodes.put("432922", "湖南省东安县");
		cityCodes.put("432923", "湖南省道县");
		cityCodes.put("432924", "湖南省宁远县");
		cityCodes.put("432925", "湖南省江永县");
		cityCodes.put("432926", "湖南省江华瑶族自治县");
		cityCodes.put("432927", "湖南省蓝山县");
		cityCodes.put("432928", "湖南省新田县　");
		cityCodes.put("432929", "湖南省双牌县");
		cityCodes.put("432930", "湖南省祁阳县");
		cityCodes.put("4330", "湖南省怀化地区");
		cityCodes.put("433001", "湖南省怀化市");
		cityCodes.put("433002", "湖南省洪江市");
		cityCodes.put("433021", "湖南省黔阳县");
		cityCodes.put("433022", "湖南省沅陵县");
		cityCodes.put("433023", "湖南省辰溪县");
		cityCodes.put("433024", "湖南省溆浦县");
		cityCodes.put("433025", "湖南省麻阳苗族自治县");
		cityCodes.put("433026", "湖南省新晃侗族自治县");
		cityCodes.put("433027", "湖南省芷江侗族自治县");
		cityCodes.put("433029", "湖南省会同县");
		cityCodes.put("433030", "湖南省靖州苗族侗族自治县");
		cityCodes.put("433031", "湖南省通道侗族自治县");
		cityCodes.put("4331", "湖南省湘西土家族苗族自治州");
		cityCodes.put("433101", "湖南省吉首市");
		cityCodes.put("433122", "湖南省泸溪县");
		cityCodes.put("433123", "湖南省风凰县");
		cityCodes.put("433124", "湖南省花垣县");
		cityCodes.put("433125", "湖南省保靖县");
		cityCodes.put("433126", "湖南省古丈县");
		cityCodes.put("433127", "湖南省永顺县");
		cityCodes.put("433130", "湖南省龙山县");
		cityCodes.put("44", "广东省");
		cityCodes.put("4401", "广东省广州市");
		cityCodes.put("440101", "广东省广州市市辖区");
		cityCodes.put("440102", "广东省广州市东山区");
		cityCodes.put("440103", "广东省广州市荔湾区");
		cityCodes.put("440104", "广东省广州市越秀区");
		cityCodes.put("440105", "广东省广州市海珠区");
		cityCodes.put("440106", "广东省广州市天河区");
		cityCodes.put("440107", "广东省广州市芳村区");
		cityCodes.put("440111", "广东省广州市白云区");
		cityCodes.put("440112", "广东省广州市黄埔区");
		cityCodes.put("440122", "广东省从花县");
		cityCodes.put("440181", "广东省番禺市");
		cityCodes.put("440182", "广东省花都市");
		cityCodes.put("440183", "广东省增城市");
		cityCodes.put("4402", "广东省韶关市");
		cityCodes.put("440201", "广东省韶关市市辖区");
		cityCodes.put("440202", "广东省韶关市北江区");
		cityCodes.put("440203", "广东省韶关市武江区");
		cityCodes.put("440204", "广东省韶关市浈江区");
		cityCodes.put("440221", "广东省曲江县");
		cityCodes.put("440222", "广东省始兴县");
		cityCodes.put("440223", "广东省南雄县");
		cityCodes.put("440224", "广东省仁化县");
		cityCodes.put("440225", "广东省乐昌县");
		cityCodes.put("440229", "广东省翁源县");
		cityCodes.put("440232", "广东省乳源瑶族自治县");
		cityCodes.put("440233", "广东省新丰县");
		cityCodes.put("4403", "广东省深圳市");
		cityCodes.put("440301", "广东省深圳市市辖区");
		cityCodes.put("440303", "广东省深圳市罗湖区");
		cityCodes.put("440304", "广东省深圳市福田区");
		cityCodes.put("440305", "广东省深圳市南山区");
		cityCodes.put("440306", "广东省深圳市宝安区");
		cityCodes.put("440307", "广东省深圳市龙岗区");
		cityCodes.put("4404", "广东省珠海市");
		cityCodes.put("440401", "广东省珠海市市辖区");
		cityCodes.put("440402", "广东省珠海市香州区");
		cityCodes.put("440407", "广东省汕头市龙湖区");
		cityCodes.put("440421", "广东省斗门县");
		cityCodes.put("4405", "广东省汕头市");
		cityCodes.put("440501", "广东省汕头市市辖区");
		cityCodes.put("440506", "广东省汕头市达濠区");
		cityCodes.put("440508", "广东省汕头市金园区");
		cityCodes.put("440509", "广东省汕头市升平区");
		cityCodes.put("440521", "广东省澄海县");
		cityCodes.put("440523", "广东省南懊县");
		cityCodes.put("440582", "广东省潮阳市");
		cityCodes.put("4406", "广东省佛山市");
		cityCodes.put("440601", "广东省佛山市市辖区");
		cityCodes.put("440602", "广东省佛山市城区");
		cityCodes.put("440603", "广东省佛山市石湾区");
		cityCodes.put("440624", "广东省高明县");
		cityCodes.put("440681", "广东省顺德市");
		cityCodes.put("440682", "广东省南海市");
		cityCodes.put("440683", "广东省三水市");
		cityCodes.put("4407", "广东省江门市");
		cityCodes.put("440701", "广东省江门市市辖区");
		cityCodes.put("440702", "广东省江门市城区");
		cityCodes.put("440711", "广东省江门市郊区");
		cityCodes.put("440723", "广东省恩平县");
		cityCodes.put("440781", "广东省台山市");
		cityCodes.put("440782", "广东省新会市");
		cityCodes.put("440783", "广东省开平市");
		cityCodes.put("440784", "广东省鹤山市");
		cityCodes.put("4408", "广东省湛江市");
		cityCodes.put("440801", "广东省湛江市市辖区");
		cityCodes.put("440802", "广东省湛江市赤坎区");
		cityCodes.put("440803", "广东省湛江市霞山区");
		cityCodes.put("440804", "广东省湛江市坡头区");
		cityCodes.put("440811", "广东省湛江市郊区");
		cityCodes.put("440821", "广东省吴川县");
		cityCodes.put("440823", "广东省遂溪县");
		cityCodes.put("440824", "广东省海康县");
		cityCodes.put("440825", "广东省徐闻县");
		cityCodes.put("440881", "广东省廉江市");
		cityCodes.put("4409", "广东省茂名市");
		cityCodes.put("440901", "广东省茂名市市辖区");
		cityCodes.put("440902", "广东省茂名市茂南区");
		cityCodes.put("440921", "广东省信宜县");
		cityCodes.put("440923", "广东省电白县");
		cityCodes.put("440924", "广东省化州县");
		cityCodes.put("440981", "广东省高州市");
		cityCodes.put("4412", "广东省肇庆市");
		cityCodes.put("441201", "广东省肇庆市市辖区");
		cityCodes.put("441202", "广东省肇庆市端州区");
		cityCodes.put("441203", "广东省肇庆市鼎湖区");
		cityCodes.put("441223", "广东省广宁县");
		cityCodes.put("441224", "广东省怀集县");
		cityCodes.put("441225", "广东省封开县");
		cityCodes.put("441226", "广东省德庆县");
		cityCodes.put("441228", "广东省新兴县");
		cityCodes.put("441229", "广东省郁南县");
		cityCodes.put("441281", "广东省云浮市");
		cityCodes.put("441282", "广东省罗定市");
		cityCodes.put("441283", "广东省高要市");
		cityCodes.put("441284", "广东省四会市");
		cityCodes.put("4413", "广东省惠州市");
		cityCodes.put("441301", "广东省惠州市市辖区");
		cityCodes.put("441302", "广东省惠州市惠城区");
		cityCodes.put("441321", "广东省惠阳县");
		cityCodes.put("441322", "广东省博罗县");
		cityCodes.put("441323", "广东省惠东县");
		cityCodes.put("441324", "广东省龙门县");
		cityCodes.put("4414", "广东省梅州市");
		cityCodes.put("441401", "广东省梅州市市辖区");
		cityCodes.put("441402", "广东省梅州市梅江区");
		cityCodes.put("441421", "广东省梅县");
		cityCodes.put("441422", "广东省大埔县");
		cityCodes.put("441423", "广东省丰顺县");
		cityCodes.put("441424", "广东省五华县");
		cityCodes.put("441425", "广东省兴宁县");
		cityCodes.put("441426", "广东省平远县");
		cityCodes.put("441427", "广东省蕉岭县");
		cityCodes.put("4415", "广东省汕尾市");
		cityCodes.put("441501", "广东省汕尾市市辖区");
		cityCodes.put("441502", "广东省汕尾市城区");
		cityCodes.put("441521", "广东省海丰县");
		cityCodes.put("441522", "广东省陆丰县");
		cityCodes.put("441523", "广东省陆河县");
		cityCodes.put("4416", "广东省河源市");
		cityCodes.put("441601", "广东省河源市市辖区");
		cityCodes.put("441602", "广东省河源市源城区");
		cityCodes.put("441621", "广东省紫金县");
		cityCodes.put("441622", "广东省龙川县");
		cityCodes.put("441623", "广东省连平县");
		cityCodes.put("441624", "广东省和平县");
		cityCodes.put("441625", "广东省东源县");
		cityCodes.put("4417", "广东省阳江市");
		cityCodes.put("441701", "广东省阳江市市辖区");
		cityCodes.put("441702", "广东省阳江市江城区");
		cityCodes.put("441721", "广东省阳西县");
		cityCodes.put("441722", "广东省阳春县");
		cityCodes.put("441723", "广东省阳东县");
		cityCodes.put("4418", "广东省清远市");
		cityCodes.put("441801", "广东省清远市市辖区");
		cityCodes.put("441802", "广东省清远市清城区");
		cityCodes.put("441821", "广东省佛冈县");
		cityCodes.put("441822", "广东省英德县");
		cityCodes.put("441823", "广东省阳山县");
		cityCodes.put("441824", "广东省连县");
		cityCodes.put("441825", "广东省连山壮族瑶族自治县");
		cityCodes.put("441826", "广东省连南瑶族自治县");
		cityCodes.put("441827", "广东省清新县");
		cityCodes.put("4419", "广东省东莞市");
		cityCodes.put("441900", "广东省东莞市");
		cityCodes.put("4420", "广东省中山市");
		cityCodes.put("442000", "广东省中山市");
		cityCodes.put("4451", "广东省潮州市");
		cityCodes.put("445101", "广东省潮州市市辖区");
		cityCodes.put("445102", "广东省潮州市湘桥区");
		cityCodes.put("445121", "广东省潮州市潮安县");
		cityCodes.put("445122", "广东省饶平县");
		cityCodes.put("4452", "广东省揭阳市");
		cityCodes.put("445201", "广东省揭阳市市辖区");
		cityCodes.put("445202", "广东省揭阳市榕城区");
		cityCodes.put("445221", "广东省揭东县");
		cityCodes.put("445222", "广东省揭西县");
		cityCodes.put("445224", "广东省惠来县");
		cityCodes.put("445281", "广东省普宁县");
		cityCodes.put("45", "广西");
		cityCodes.put("4501", "广西南宁市");
		cityCodes.put("450101", "广西南宁市市辖区");
		cityCodes.put("450102", "广西南宁市兴宁区");
		cityCodes.put("450103", "广西南宁市新城区");
		cityCodes.put("450104", "广西南宁市城北区");
		cityCodes.put("450105", "广西南宁市江南区");
		cityCodes.put("450106", "广西南宁市永新区");
		cityCodes.put("450111", "广西南宁市市郊区");
		cityCodes.put("450121", "广西邕宁县");
		cityCodes.put("450122", "广西武鸣县");
		cityCodes.put("4502", "广西柳州市");
		cityCodes.put("450201", "广西柳州市市辖区");
		cityCodes.put("450202", "广西柳州市城中区");
		cityCodes.put("450203", "广西柳州市鱼峰区");
		cityCodes.put("450204", "广西柳州市柳南区");
		cityCodes.put("450205", "广西柳州市柳北区");
		cityCodes.put("450211", "广西柳州市市郊区");
		cityCodes.put("450221", "广西柳江县");
		cityCodes.put("450222", "广西柳城县");
		cityCodes.put("4503", "广西桂林市");
		cityCodes.put("450301", "广西桂林市市辖区");
		cityCodes.put("450302", "广西桂林市秀峰区");
		cityCodes.put("450303", "广西桂林市叠彩区");
		cityCodes.put("450304", "广西桂林市象山区");
		cityCodes.put("450305", "广西桂林市七星区");
		cityCodes.put("450311", "广西桂林市市郊区");
		cityCodes.put("450321", "广西阳朔县");
		cityCodes.put("450322", "广西临桂县");
		cityCodes.put("4504", "广西梧州市");
		cityCodes.put("450401", "广西梧州市市辖区");
		cityCodes.put("450403", "广西梧州市万秀区");
		cityCodes.put("450404", "广西梧州市蝶山区");
		cityCodes.put("450411", "广西梧州市市郊区");
		cityCodes.put("450421", "广西苍梧县");
		cityCodes.put("4505", "广西北海市　");
		cityCodes.put("450501", "广西北海市市辖区");
		cityCodes.put("450502", "广西北海市海城区");
		cityCodes.put("450511", "广西北海市市郊区");
		cityCodes.put("450521", "广西合浦县");
		cityCodes.put("4506", "广西防城港市");
		cityCodes.put("450601", "广西防城港市市辖区");
		cityCodes.put("450602", "广西防城港市港口区");
		cityCodes.put("450603", "广西防城港市防城区");
		cityCodes.put("450621", "广西上思县");
		cityCodes.put("4521", "广西南宁地区");
		cityCodes.put("452101", "广西凭祥市");
		cityCodes.put("452122", "广西横县");
		cityCodes.put("452123", "广西宾阳县");
		cityCodes.put("452124", "广西上林县");
		cityCodes.put("452126", "广西隆安县");
		cityCodes.put("452127", "广西马山县");
		cityCodes.put("452128", "广西扶绥县");
		cityCodes.put("452129", "广西崇左县");
		cityCodes.put("452130", "广西大新县");
		cityCodes.put("452131", "广西天等县");
		cityCodes.put("452132", "广西宁明县");
		cityCodes.put("452133", "广西龙州县");
		cityCodes.put("4522", "广西柳州地区");
		cityCodes.put("452201", "广西合山市");
		cityCodes.put("452223", "广西鹿寨县");
		cityCodes.put("452224", "广西象州县");
		cityCodes.put("452225", "广西武宣县");
		cityCodes.put("452226", "广西来宾县");
		cityCodes.put("452227", "广西融安县");
		cityCodes.put("452228", "广西三江侗族自治县");
		cityCodes.put("452229", "广西融水苗族自治县");
		cityCodes.put("452230", "广西金秀瑶族自治县");
		cityCodes.put("452231", "广西忻城县");
		cityCodes.put("4523", "广西桂林地区");
		cityCodes.put("452322", "广西灵川县");
		cityCodes.put("452323", "广西全州县");
		cityCodes.put("452324", "广西兴安县");
		cityCodes.put("452325", "广西永福县");
		cityCodes.put("452327", "广西灌阳县");
		cityCodes.put("452328", "广西龙胜各族自治县");
		cityCodes.put("452329", "广西资源县");
		cityCodes.put("452330", "广西平乐县");
		cityCodes.put("452331", "广西荔浦县");
		cityCodes.put("452332", "广西恭城瑶族自治县");
		cityCodes.put("4524", "广西梧州地区");
		cityCodes.put("452421", "广西岑溪县");
		cityCodes.put("452423", "广西藤县");
		cityCodes.put("452424", "广西昭平县");
		cityCodes.put("452425", "广西蒙山县");
		cityCodes.put("452426", "广西贺县");
		cityCodes.put("452427", "广西钟山县");
		cityCodes.put("452428", "广西富川瑶族自治县");
		cityCodes.put("4525", "广西玉林地区");
		cityCodes.put("452501", "广西玉林市");
		cityCodes.put("452502", "广西贵港市");
		cityCodes.put("452523", "广西桂平县");
		cityCodes.put("452524", "广西平南县");
		cityCodes.put("452525", "广西容县");
		cityCodes.put("51", "四川省");
		cityCodes.put("510502", "四川省泸州市市中区");
		cityCodes.put("510521", "四川省泸县");
		cityCodes.put("510522", "四川省合江县");
		cityCodes.put("510523", "四川省纳溪县");
		cityCodes.put("510524", "四川省叙永县");
		cityCodes.put("510525", "四川省古蔺县");
		cityCodes.put("5106", "四川省德阳市");
		cityCodes.put("510601", "四川省德阳市市辖区");
		cityCodes.put("510602", "四川省德阳市市中区");
		cityCodes.put("510622", "四川省绵竹县");
		cityCodes.put("510623", "四川省中江县");
		cityCodes.put("510625", "四川省什邡县");
		cityCodes.put("510681", "四川省广汉市");
		cityCodes.put("5107", "四川省绵阳市");
		cityCodes.put("510701", "四川省绵阳市市辖区");
		cityCodes.put("510703", "四川省绵阳市涪城区");
		cityCodes.put("510704", "四川省绵阳市游仙区");
		cityCodes.put("510722", "四川省三台县");
		cityCodes.put("510723", "四川省盐亭县");
		cityCodes.put("510724", "四川省安县");
		cityCodes.put("510725", "四川省梓潼县");
		cityCodes.put("510726", "四川省北川县");
		cityCodes.put("510727", "四川省平武县");
		cityCodes.put("510781", "四川省江油市");
		cityCodes.put("5108", "四川省广元市");
		cityCodes.put("510801", "四川省广元市市辖区");
		cityCodes.put("510802", "四川省广元市市中区");
		cityCodes.put("510811", "四川省广元市元坝区");
		cityCodes.put("510812", "四川省广元市朝天区");
		cityCodes.put("510821", "四川省旺苍县");
		cityCodes.put("510822", "四川省青川县");
		cityCodes.put("510823", "四川省剑阁县");
		cityCodes.put("510824", "四川省苍溪县");
		cityCodes.put("5109", "四川省遂宁市");
		cityCodes.put("510901", "四川省遂宁市市辖区");
		cityCodes.put("510902", "四川省遂宁市市中区");
		cityCodes.put("510921", "四川省蓬溪县");
		cityCodes.put("510922", "四川省射洪县");
		cityCodes.put("5110", "四川省内江市");
		cityCodes.put("511001", "四川省内江市市辖区");
		cityCodes.put("511002", "四川省内江市市中区");
		cityCodes.put("511011", "四川省内江市东兴区");
		cityCodes.put("511022", "四川省乐至县");
		cityCodes.put("511023", "四川省安岳县");
		cityCodes.put("511024", "四川省威远县");
		cityCodes.put("511025", "四川省资中县");
		cityCodes.put("511027", "四川省简阳县");
		cityCodes.put("511028", "四川省隆昌县");
		cityCodes.put("511081", "四川省资阳市");
		cityCodes.put("5111", "四川省乐山市");
		cityCodes.put("511101", "四川省乐山市市辖区");
		cityCodes.put("511102", "四川省乐山市市中区");
		cityCodes.put("511111", "四川省乐山市沙湾区");
		cityCodes.put("511112", "四川省乐山市五通桥区");
		cityCodes.put("511113", "四川省乐山市金口河区");
		cityCodes.put("511121", "四川省仁寿县");
		cityCodes.put("511122", "四川省眉山县");
		cityCodes.put("511123", "四川省犍为县");
		cityCodes.put("511124", "四川省井研县");
		cityCodes.put("511126", "四川省夹江县");
		cityCodes.put("511127", "四川省洪雅县");
		cityCodes.put("511128", "四川省彭山县");
		cityCodes.put("511129", "四川省沐川县");
		cityCodes.put("511130", "四川省青神县");
		cityCodes.put("511131", "四川省丹棱县");
		cityCodes.put("511132", "四川省峨边彝族自治县");
		cityCodes.put("511133", "四川省马边彝族自治县");
		cityCodes.put("511181", "四川省峨眉山市");
		cityCodes.put("5112", "四川省万县市");
		cityCodes.put("511201", "四川省万县市市辖区");
		cityCodes.put("511202", "四川省万县市龙宝区");
		cityCodes.put("511203", "四川省万县市天城区");
		cityCodes.put("511204", "四川省万县市五桥区");
		cityCodes.put("511221", "四川省开县");
		cityCodes.put("511222", "四川省忠县");
		cityCodes.put("511223", "四川省梁平县");
		cityCodes.put("511224", "四川省云阳县");
		cityCodes.put("511225", "四川省奉节县");
		cityCodes.put("511226", "四川省巫山县");
		cityCodes.put("511227", "四川省巫溪县");
		cityCodes.put("511228", "四川省城口县");
		cityCodes.put("5113", "四川省南充市");
		cityCodes.put("511301", "四川省南充市市辖区");
		cityCodes.put("511302", "四川省南充市顺庆区");
		cityCodes.put("511303", "四川省南充市高坪区");
		cityCodes.put("511304", "四川省南充市嘉陵区");
		cityCodes.put("511321", "四川省南部县");
		cityCodes.put("511322", "四川省营山县");
		cityCodes.put("511323", "四川省蓬安县");
		cityCodes.put("511324", "四川省仪陇县");
		cityCodes.put("511325", "四川省西充县");
		cityCodes.put("511381", "四川省阆中市");
		cityCodes.put("5123", "四川省涪陵地区");
		cityCodes.put("512301", "四川省涪陵市");
		cityCodes.put("512322", "四川省垫江县");
		cityCodes.put("512323", "四川省南川县");
		cityCodes.put("512324", "四川省丰都县");
		cityCodes.put("512326", "四川省武隆县");
		cityCodes.put("5125", "四川省宜宾地区");
		cityCodes.put("512501", "四川省宜宾市");
		cityCodes.put("512527", "四川省宜宾县");
		cityCodes.put("512528", "四川省南溪县");
		cityCodes.put("512529", "四川省江安县");
		cityCodes.put("512530", "四川省长宁县");
		cityCodes.put("512531", "四川省高县");
		cityCodes.put("512532", "四川省筠连县");
		cityCodes.put("512533", "四川省珙县");
		cityCodes.put("512534", "四川省兴文县");
		cityCodes.put("512535", "四川省屏山县");
		cityCodes.put("5130", "四川省达川地区");
		cityCodes.put("513001", "四川省达川市");
		cityCodes.put("513002", "四川省万源县");
		cityCodes.put("513021", "四川省达县");
		cityCodes.put("513022", "四川省宣汉县");
		cityCodes.put("513023", "四川省开江县");
		cityCodes.put("513029", "四川省大竹县");
		cityCodes.put("513030", "四川省渠县");
		cityCodes.put("5131", "四川省雅安地区");
		cityCodes.put("513101", "四川省雅安市");
		cityCodes.put("513122", "四川省名山县");
		cityCodes.put("513123", "四川省荥经县");
		cityCodes.put("513124", "四川省汉源县");
		cityCodes.put("513125", "四川省石棉县");
		cityCodes.put("513126", "四川省天全县");
		cityCodes.put("513127", "四川省芦山县");
		cityCodes.put("513128", "四川省宝兴县");
		cityCodes.put("5132", "四川省阿坝藏族羌族自治州");
		cityCodes.put("513221", "四川省汶川县");
		cityCodes.put("513222", "四川省理县");
		cityCodes.put("513223", "四川省茂县");
		cityCodes.put("513224", "四川省松潘县");
		cityCodes.put("513225", "四川省南坪县");
		cityCodes.put("513226", "四川省金川县");
		cityCodes.put("513227", "四川省小金县");
		cityCodes.put("513228", "四川省黑水县");
		cityCodes.put("513229", "四川省马尔康县");
		cityCodes.put("513230", "四川省壤塘县");
		cityCodes.put("513231", "四川省阿坝县");
		cityCodes.put("513232", "四川省若尔盖县");
		cityCodes.put("513233", "四川省红原县");
		cityCodes.put("5133", "四川省甘孜藏族自治州");
		cityCodes.put("513321", "四川省康定县");
		cityCodes.put("513322", "四川省泸定县");
		cityCodes.put("513323", "四川省丹巴县");
		cityCodes.put("513324", "四川省九龙县");
		cityCodes.put("513325", "四川省雅江县");
		cityCodes.put("513326", "四川省道孚县");
		cityCodes.put("513327", "四川省炉霍县");
		cityCodes.put("513328", "四川省甘孜县");
		cityCodes.put("513329", "四川省新龙县");
		cityCodes.put("513330", "四川省德格县");
		cityCodes.put("513331", "四川省白玉县");
		cityCodes.put("513332", "四川省石渠县");
		cityCodes.put("513333", "四川省色达县");
		cityCodes.put("513334", "四川省理塘县");
		cityCodes.put("513335", "四川省巴塘县");
		cityCodes.put("513336", "四川省乡城县");
		cityCodes.put("513337", "四川省稻城县");
		cityCodes.put("513338", "四川省得荣县");
		cityCodes.put("5134", "四川省凉山彝族自治州");
		cityCodes.put("513401", "四川省西昌市");
		cityCodes.put("513422", "四川省木里藏族自治县");
		cityCodes.put("513423", "四川省盐源县");
		cityCodes.put("513424", "四川省德昌县");
		cityCodes.put("513425", "四川省会理县");
		cityCodes.put("513426", "四川省会东县");
		cityCodes.put("513427", "四川省宁南县");
		cityCodes.put("513428", "四川省普格县");
		cityCodes.put("513429", "四川省布拖县");
		cityCodes.put("513430", "四川省金阳县");
		cityCodes.put("513431", "四川省昭觉县");
		cityCodes.put("513432", "四川省喜德县");
		cityCodes.put("513433", "四川省冕宁县");
		cityCodes.put("513434", "四川省越西县");
		cityCodes.put("513435", "四川省甘洛县");
		cityCodes.put("513436", "四川省美姑县");
		cityCodes.put("513437", "四川省雷波县");
		cityCodes.put("5135", "四川省黔江地区");
		cityCodes.put("513521", "四川省石柱土家族自治县");
		cityCodes.put("513522", "四川省秀山土家族苗族自治县");
		cityCodes.put("513523", "四川省黔江土家族苗族自治县");
		cityCodes.put("513524", "四川省酉阳土家族苗族自治县");
		cityCodes.put("513525", "四川省彭水苗族土家族自治县");
		cityCodes.put("5136", "四川省广安地区");
		cityCodes.put("513601", "四川省华蓥市");
		cityCodes.put("513621", "四川省岳池县");
		cityCodes.put("513622", "四川省广安县");
		cityCodes.put("513623", "四川省武胜县");
		cityCodes.put("513624", "四川省邻水县");
		cityCodes.put("5137", "四川省巴中地区");
		cityCodes.put("513701", "四川省巴中市");
		cityCodes.put("513721", "四川省通江县");
		cityCodes.put("513722", "四");
		cityCodes.put("52", "贵州省");
		cityCodes.put("5201", "贵州省贵阳市");
		cityCodes.put("520101", "贵州省贵阳市市辖区");
		cityCodes.put("520102", "贵州省贵阳市南明区");
		cityCodes.put("520103", "贵州省贵阳市云岩区");
		cityCodes.put("520111", "贵州省贵阳市花溪区");
		cityCodes.put("520112", "贵州省贵阳市乌当区");
		cityCodes.put("520113", "贵州省贵阳市白云区");
		cityCodes.put("5202", "贵州省六盘水市");
		cityCodes.put("520201", "贵州省六盘水市钟山区");
		cityCodes.put("520202", "贵州省六盘水市盘县特区");
		cityCodes.put("520203", "贵州省六盘水市六枝特区");
		cityCodes.put("520221", "贵州省水城县");
		cityCodes.put("5221", "贵州省遵义地区");
		cityCodes.put("522101", "贵州省遵义市");
		cityCodes.put("522102", "贵州省赤水市");
		cityCodes.put("522121", "贵州省遵义县");
		cityCodes.put("522122", "贵州省桐梓县");
		cityCodes.put("522123", "贵州省绥阳县");
		cityCodes.put("522124", "贵州省正安县");
		cityCodes.put("522125", "贵州省道真仡佬苗族自治县");
		cityCodes.put("522126", "贵州省务川仡佬苗族自治县");
		cityCodes.put("522127", "贵州省凤冈县");
		cityCodes.put("522128", "贵州省湄潭县");
		cityCodes.put("522129", "贵州省余庆县");
		cityCodes.put("522130", "贵州省仁怀县");
		cityCodes.put("522132", "贵州省习水县");
		cityCodes.put("5222", "贵州省铜仁地区");
		cityCodes.put("522201", "贵州省铜仁市");
		cityCodes.put("522222", "贵州省江口县");
		cityCodes.put("522223", "贵州省玉屏侗族自治县");
		cityCodes.put("522224", "贵州省石阡县");
		cityCodes.put("522225", "贵州省思南县");
		cityCodes.put("522226", "贵州省印江土家族苗族自治县");
		cityCodes.put("522227", "贵州省德江县");
		cityCodes.put("522228", "贵州省沿河土家族自治县");
		cityCodes.put("522229", "贵州省松桃苗族自治县");
		cityCodes.put("522230", "贵州省万山特区");
		cityCodes.put("5223", "贵州省黔西南布依族苗族自治州");
		cityCodes.put("522301", "贵州省兴义市");
		cityCodes.put("522322", "贵州省兴仁县");
		cityCodes.put("522323", "贵州省普安县");
		cityCodes.put("522324", "贵州省晴隆县");
		cityCodes.put("522325", "贵州省贞丰县");
		cityCodes.put("522326", "贵州省望谟县");
		cityCodes.put("522327", "贵州省册亨县");
		cityCodes.put("522328", "贵州省安龙县");
		cityCodes.put("5224", "贵州省毕节地区");
		cityCodes.put("522401", "贵州省毕节市");
		cityCodes.put("522422", "贵州省大方县");
		cityCodes.put("522423", "贵州省黔西县");
		cityCodes.put("522424", "贵州省金沙县");
		cityCodes.put("522425", "贵州省织金县");
		cityCodes.put("522426", "贵州省纳雍县");
		cityCodes.put("522427", "贵州省威宁彝族回族苗族自治县");
		cityCodes.put("522428", "贵州省赫章县");
		cityCodes.put("5225", "贵州省安顺地区");
		cityCodes.put("522501", "贵州省安顺市");
		cityCodes.put("522502", "贵州省清镇市");
		cityCodes.put("522522", "贵州省开阳县");
		cityCodes.put("522523", "贵州省息烽县");
		cityCodes.put("522524", "贵州省修文县");
		cityCodes.put("522526", "贵州省平坝县");
		cityCodes.put("522527", "贵州省普定县");
		cityCodes.put("522528", "贵州省关岭布依族苗族自治县");
		cityCodes.put("53", "云南省");
		cityCodes.put("532129", "云南省彝良县");
		cityCodes.put("532130", "云南省威信县");
		cityCodes.put("532131", "云南省水富县");
		cityCodes.put("5322", "云南省曲靖地区");
		cityCodes.put("532201", "云南省曲靖市");
		cityCodes.put("532223", "云南省马龙县");
		cityCodes.put("532224", "云南省宜威县");
		cityCodes.put("532225", "云南省富源县");
		cityCodes.put("532226", "云南省罗平县");
		cityCodes.put("532227", "云南省师宗县");
		cityCodes.put("532228", "云南省陆良县");
		cityCodes.put("532231", "云南省寻甸回族彝族自治县");
		cityCodes.put("532233", "云南省会泽县");
		cityCodes.put("5323", "云南省楚雄彝族自治州");
		cityCodes.put("532301", "云南省楚雄市");
		cityCodes.put("532322", "云南省双柏县");
		cityCodes.put("532323", "云南省牟定县");
		cityCodes.put("532324", "云南省南华县");
		cityCodes.put("532325", "云南省姚安县");
		cityCodes.put("532326", "云南省大姚县");
		cityCodes.put("532327", "云南省永仁县");
		cityCodes.put("532328", "云南省元谋县");
		cityCodes.put("532329", "云南省武定县");
		cityCodes.put("532331", "云南省禄丰县");
		cityCodes.put("5324", "云南省玉溪地区");
		cityCodes.put("532401", "云南省玉溪市");
		cityCodes.put("532422", "云南省江川县");
		cityCodes.put("532423", "云南省澄江县");
		cityCodes.put("532424", "云南省通海县");
		cityCodes.put("532425", "云南省华宁县");
		cityCodes.put("532426", "云南省易门县");
		cityCodes.put("532427", "云南省峨山彝族自治县");
		cityCodes.put("532428", "云南省新平彝族傣族自治县");
		cityCodes.put("532429", "云南省元江哈尼族彝族自治县");
		cityCodes.put("5325", "云南省红河哈尼族彝族自治州");
		cityCodes.put("532501", "云南省个旧市");
		cityCodes.put("532502", "云南省开远市");
		cityCodes.put("532522", "云南省蒙自县");
		cityCodes.put("532523", "云南省屏边苗族自治县");
		cityCodes.put("532524", "云南省建水县");
		cityCodes.put("532525", "云南省石屏县");
		cityCodes.put("532526", "云南省弥勒县");
		cityCodes.put("532527", "云南省泸西县");
		cityCodes.put("532528", "云南省元阳县");
		cityCodes.put("532529", "云南省红河县");
		cityCodes.put("532530", "云南省金平苗族瑶族傣族自治县");
		cityCodes.put("532531", "云南省绿春县");
		cityCodes.put("532532", "云南省河口瑶族自治县");
		cityCodes.put("5326", "云南省文山壮族苗族自治州");
		cityCodes.put("532621", "云南省文山县");
		cityCodes.put("532622", "云南省砚山县");
		cityCodes.put("532623", "云南省西畴县");
		cityCodes.put("532624", "云南省麻栗坡县");
		cityCodes.put("532625", "云南省马关县");
		cityCodes.put("532626", "云南省丘北县");
		cityCodes.put("532627", "云南省广南县");
		cityCodes.put("532628", "云南省富宁县");
		cityCodes.put("5327", "云南省思茅地区");
		cityCodes.put("532701", "云南省思茅市");
		cityCodes.put("532722", "云南省普洱哈尼族继族自治区");
		cityCodes.put("532723", "云南省墨江哈尼族自治县");
		cityCodes.put("532724", "云南省景东彝族自治区");
		cityCodes.put("532725", "云南省景谷傣族彝族自治区");
		cityCodes.put("532726", "云南省镇沅彝族哈尼族拉祜族自治");
		cityCodes.put("532727", "云南省江城哈尼族彝族自治县");
		cityCodes.put("532728", "云南省孟连傣族拉祜族佤族自治县");
		cityCodes.put("532729", "云南省澜沧拉祜族自治县");
		cityCodes.put("532730", "云南省西盟佤族自治县");
		cityCodes.put("5328", "云南省西双版纳傣族自治州");
		cityCodes.put("532801", "云南省景洪市");
		cityCodes.put("532822", "云南省勐海县");
		cityCodes.put("532823", "云南省勐腊县");
		cityCodes.put("5329", "云南省大理白族自治州");
		cityCodes.put("532901", "云南省大理市");
		cityCodes.put("532922", "云南省漾濞彝族自治县");
		cityCodes.put("532923", "云南省祥云县");
		cityCodes.put("532924", "云南省宾川县");
		cityCodes.put("532925", "云南省弥渡县");
		cityCodes.put("532926", "云南省南涧彝族自治县");
		cityCodes.put("532927", "云南省巍山彝族回族自治县");
		cityCodes.put("532928", "云南省永平县");
		cityCodes.put("532929", "云南省云龙县");
		cityCodes.put("532930", "云南省洱源县");
		cityCodes.put("532931", "云南省剑川县");
		cityCodes.put("532932", "云南省鹤庆县");
		cityCodes.put("5330", "云南省保山地区");
		cityCodes.put("533001", "云南省保山市");
		cityCodes.put("533022", "云南省施甸县");
		cityCodes.put("533023", "云南省腾冲县");
		cityCodes.put("533024", "云南省龙陵县");
		cityCodes.put("533025", "云南省昌宁县");
		cityCodes.put("5331", "云南省德宏傣族景颇族自治州");
		cityCodes.put("533101", "云南省畹町市");
		cityCodes.put("533102", "云南省瑞丽市");
		cityCodes.put("533121", "云南省潞西县");
		cityCodes.put("533122", "云南省梁河县");
		cityCodes.put("533123", "云南省盈江县");
		cityCodes.put("533124", "云南省陇川县");
		cityCodes.put("5332", "云南省丽江地区");
		cityCodes.put("533221", "云南省丽江纳西族自治县");
		cityCodes.put("533222", "云南省永胜县");
		cityCodes.put("533223", "云南省华坪县");
		cityCodes.put("533224", "云南省宁蒗彝族自治县");
		cityCodes.put("5333", "云南省怒江傈僳族自治州");
		cityCodes.put("533321", "云南省泸水县");
		cityCodes.put("533323", "云南省福贡县");
		cityCodes.put("533324", "云南省贡山独龙族怒族自治县");
		cityCodes.put("533325", "云南省兰坪白族普米族自治县");
		cityCodes.put("5334", "云南省迪庆藏族自治州");
		cityCodes.put("533421", "云南省中甸县");
		cityCodes.put("533422", "云南省德钦县");
		cityCodes.put("533423", "云南省维西僳僳族自治县");
		cityCodes.put("5335", "云南省临沧地区");
		cityCodes.put("533521", "云南省临沧县");
		cityCodes.put("533522", "云南省凤庆县");
		cityCodes.put("533523", "云南省云县");
		cityCodes.put("533524", "云南省永德县");
		cityCodes.put("533525", "云南省镇康县");
		cityCodes.put("533526", "云南省双江拉祜族佤族布朗族傣族");
		cityCodes.put("533527", "云南省耿马傣族佤族自治县");
		cityCodes.put("533528", "云南省沧源佤族自治县");
		cityCodes.put("54", "西藏自治区");
		cityCodes.put("5401", "西藏拉萨市");
		cityCodes.put("540101", "西藏拉萨市市辖区");
		cityCodes.put("540102", "西藏拉萨市城关区");
		cityCodes.put("540121", "西藏林周县");
		cityCodes.put("540122", "西藏当雄县");
		cityCodes.put("540123", "西藏尼木县");
		cityCodes.put("540124", "西藏曲水县");
		cityCodes.put("540125", "西藏堆龙德庆县");
		cityCodes.put("540126", "西藏达孜县");
		cityCodes.put("540127", "西藏墨竹工卡县");
		cityCodes.put("5421", "西藏昌都地区");
		cityCodes.put("542121", "西藏昌都县");
		cityCodes.put("542122", "西藏江达县");
		cityCodes.put("542123", "西藏贡觉县");
		cityCodes.put("542124", "西藏类乌齐县");
		cityCodes.put("542125", "西藏丁青县");
		cityCodes.put("542126", "西藏察雅县");
		cityCodes.put("542127", "西藏八宿县");
		cityCodes.put("542128", "西藏左贡县");
		cityCodes.put("542129", "西藏芒康县");
		cityCodes.put("542132", "西藏洛隆县");
		cityCodes.put("542133", "西藏边坝县");
		cityCodes.put("542134", "西藏盐井县");
		cityCodes.put("542135", "西藏碧土县");
		cityCodes.put("542136", "西藏妥坝县");
		cityCodes.put("542137", "西藏生达县");
		cityCodes.put("5422", "西藏山南地区");
		cityCodes.put("542221", "西藏乃东县");
		cityCodes.put("542222", "西藏扎襄县");
		cityCodes.put("542223", "西藏贡嘎县");
		cityCodes.put("542224", "西藏桑日县");
		cityCodes.put("542225", "西藏琼结县");
		cityCodes.put("542226", "西藏曲松县");
		cityCodes.put("542227", "西藏措美县");
		cityCodes.put("542228", "西藏洛扎县");
		cityCodes.put("542229", "西藏加查县");
		cityCodes.put("542231", "西藏隆子县");
		cityCodes.put("542232", "西藏错那县");
		cityCodes.put("542233", "西藏浪卡子县");
		cityCodes.put("5423", "西藏日喀则地区");
		cityCodes.put("542301", "西藏日喀则市");
		cityCodes.put("542322", "西藏南木林县");
		cityCodes.put("542323", "西藏江孜县");
		cityCodes.put("542324", "西藏定日县");
		cityCodes.put("542325", "西藏萨迦县");
		cityCodes.put("542326", "西藏拉孜县");
		cityCodes.put("542327", "西藏昂仁县");
		cityCodes.put("542328", "西藏谢通门县");
		cityCodes.put("542329", "西藏白朗县");
		cityCodes.put("542330", "西藏仁布县");
		cityCodes.put("542331", "西藏康马县");
		cityCodes.put("542332", "西藏定结县");
		cityCodes.put("542333", "西藏仲巴县");
		cityCodes.put("542334", "西藏亚东县");
		cityCodes.put("542335", "西藏吉隆县");
		cityCodes.put("542336", "西藏聂拉木县");
		cityCodes.put("542337", "西藏萨嘎县");
		cityCodes.put("542338", "西藏岗巴县");
		cityCodes.put("5424", "西藏那曲地区");
		cityCodes.put("542421", "西藏那曲县");
		cityCodes.put("542422", "西藏嘉黎县");
		cityCodes.put("542423", "西藏比如县");
		cityCodes.put("542424", "西藏聂荣县");
		cityCodes.put("542425", "西藏安多县");
		cityCodes.put("542426", "西藏申扎县");
		cityCodes.put("542427", "西藏索县");
		cityCodes.put("542428", "西藏班戈县");
		cityCodes.put("542429", "西藏巴青县");
		cityCodes.put("542430", "西藏尼玛县");
		cityCodes.put("5425", "西藏阿里地区");
		cityCodes.put("542521", "西藏普兰县");
		cityCodes.put("542522", "西藏札达县");
		cityCodes.put("542523", "西藏噶尔县");
		cityCodes.put("542524", "西藏日土县");
		cityCodes.put("542525", "西藏革吉县");
		cityCodes.put("542526", "西藏改则县");
		cityCodes.put("542527", "西藏措勤县");
		cityCodes.put("542528", "西藏隆格尔县");
		cityCodes.put("5426", "西藏林芝地区");
		cityCodes.put("542621", "西藏林芝县");
		cityCodes.put("542622", "西藏工布江达县");
		cityCodes.put("542623", "西藏米林县");
		cityCodes.put("542624", "西藏墨脱县");
		cityCodes.put("542625", "西藏波密县");
		cityCodes.put("542626", "西藏察隅县");
		cityCodes.put("542627", "西藏朗县");
		cityCodes.put("61", "陕西省");
		cityCodes.put("6101", "陕西省西安市");
		cityCodes.put("610101", "陕西省西安市市辖区");
		cityCodes.put("610102", "陕西省西安市新城区");
		cityCodes.put("610103", "陕西省西安市碑林区");
		cityCodes.put("610104", "陕西省西安市莲湖区");
		cityCodes.put("610111", "陕西省西安市灞桥区");
		cityCodes.put("610112", "陕西省西安市未央区");
		cityCodes.put("610113", "陕西省西安市雁塔区");
		cityCodes.put("610114", "陕西省西安市阎良区");
		cityCodes.put("610121", "陕西省长安县");
		cityCodes.put("610122", "陕西省蓝田县");
		cityCodes.put("610123", "陕西省临潼县");
		cityCodes.put("610124", "陕西省周至县");
		cityCodes.put("610125", "陕西省户县");
		cityCodes.put("610126", "陕西省高陵县");
		cityCodes.put("6102", "陕西省铜川市");
		cityCodes.put("610201", "陕西省铜川市市辖区");
		cityCodes.put("610202", "陕西省铜川市城区");
		cityCodes.put("610203", "陕西省铜川市郊区");
		cityCodes.put("610221", "陕西省耀县");
		cityCodes.put("610222", "陕西省宜君县");
		cityCodes.put("6103", "陕西省宝鸡市");
		cityCodes.put("610301", "陕西省宝鸡市市辖区");
		cityCodes.put("610302", "陕西省宝鸡市渭滨区");
		cityCodes.put("610303", "陕西省宝鸡市金台区");
		cityCodes.put("610321", "陕西省宝鸡县");
		cityCodes.put("610322", "陕西省凤翔县");
		cityCodes.put("610323", "陕西省岐山县");
		cityCodes.put("610324", "陕西省扶风县");
		cityCodes.put("610326", "陕西省眉县");
		cityCodes.put("610327", "陕西省陇县");
		cityCodes.put("610328", "陕西省千阳县");
		cityCodes.put("610329", "陕西省麟游县");
		cityCodes.put("610330", "陕西省凤县");
		cityCodes.put("610331", "陕西省太白县");
		cityCodes.put("6104", "陕西省咸阳市");
		cityCodes.put("610401", "陕西省咸阳市市辖区");
		cityCodes.put("610402", "陕西省咸阳市秦都区");
		cityCodes.put("610403", "陕西省咸阳市杨陵区");
		cityCodes.put("610404", "陕西省咸阳市渭城区");
		cityCodes.put("610422", "陕西省三原县");
		cityCodes.put("610423", "陕西省泾阳县");
		cityCodes.put("610424", "陕西省乾县");
		cityCodes.put("610425", "陕西省礼泉县");
		cityCodes.put("610426", "陕西省永寿县");
		cityCodes.put("610427", "陕西省彬县");
		cityCodes.put("610428", "陕西省长武县");
		cityCodes.put("610429", "陕西省旬邑县");
		cityCodes.put("610430", "陕西省淳化县");
		cityCodes.put("610431", "陕西省武功县");
		cityCodes.put("610481", "陕西省兴平市");
		cityCodes.put("6121", "陕西省渭南地区");
		cityCodes.put("612101", "陕西省渭南市");
		cityCodes.put("612102", "陕西省韩城市");
		cityCodes.put("612103", "陕西省华阴市");
		cityCodes.put("612124", "陕西省华县");
		cityCodes.put("612126", "陕西省潼关县");
		cityCodes.put("612127", "陕西省大荔县");
		cityCodes.put("612128", "陕西省蒲城县");
		cityCodes.put("612129", "陕西省澄城县");
		cityCodes.put("612130", "陕西省白水县");
		cityCodes.put("612132", "陕西省合阳县");
		cityCodes.put("612133", "陕西省富平县");
		cityCodes.put("6123", "陕西省汉中地区");
		cityCodes.put("612301", "陕西省汉中市");
		cityCodes.put("612321", "陕西省南郑县");
		cityCodes.put("612322", "陕西省城固县");
		cityCodes.put("612323", "陕西省洋县");
		cityCodes.put("612324", "陕西省西乡县");
		cityCodes.put("612325", "陕西省勉县");
		cityCodes.put("612326", "陕西省宁强县");
		cityCodes.put("612327", "陕西省略阳县");
		cityCodes.put("612328", "陕西省镇巴县");
		cityCodes.put("612329", "陕西省留坝县");
		cityCodes.put("612330", "陕西省佛坪县");
		cityCodes.put("6124", "陕西省安康地区");
		cityCodes.put("612401", "陕西省安康市");
		cityCodes.put("612422", "陕西省汉阳县");
		cityCodes.put("612423", "陕西省石泉县");
		cityCodes.put("612424", "陕西省宁陕县");
		cityCodes.put("612425", "陕西省紫阳县");
		cityCodes.put("612426", "陕西省岚皋县");
		cityCodes.put("612427", "陕西省平利县");
		cityCodes.put("612428", "陕西省镇坪县");
		cityCodes.put("612429", "陕西省旬阳县");
		cityCodes.put("612430", "陕西省白河县");
		cityCodes.put("6125", "陕西省商洛地区");
		cityCodes.put("612501", "陕西省商州市");
		cityCodes.put("612522", "陕西省洛南县");
		cityCodes.put("612523", "陕西省丹风县");
		cityCodes.put("612524", "陕西省商南县");
		cityCodes.put("612525", "陕西省山阳县");
		cityCodes.put("612526", "陕西省镇安县");
		cityCodes.put("612527", "陕西省柞水县");
		cityCodes.put("6126", "陕西省延安地区");
		cityCodes.put("612601", "陕西省延安市");
		cityCodes.put("612621", "陕西省延长县");
		cityCodes.put("612622", "陕西省延川县");
		cityCodes.put("612623", "陕西省子长县");
		cityCodes.put("612624", "陕西省安塞县");
		cityCodes.put("612625", "陕西省志丹县");
		cityCodes.put("612626", "陕西省吴旗县");
		cityCodes.put("612627", "陕西省甘泉县");
		cityCodes.put("612628", "陕西省富县");
		cityCodes.put("612629", "陕西省洛川县");
		cityCodes.put("612630", "陕西省宜川县");
		cityCodes.put("612631", "陕西省黄龙县");
		cityCodes.put("612632", "陕西省黄陵县");
		cityCodes.put("6127", "陕西省榆林地区");
		cityCodes.put("612701", "陕西省榆林市");
		cityCodes.put("612722", "陕西省神木县");
		cityCodes.put("612723", "陕西省府谷县");
		cityCodes.put("612724", "陕西省横山县");
		cityCodes.put("612725", "陕西省靖边县");
		cityCodes.put("612726", "陕西省定边县");
		cityCodes.put("612727", "陕西省绥德县");
		cityCodes.put("612728", "陕西省米脂县");
		cityCodes.put("612729", "陕西省佳县");
		cityCodes.put("612730", "陕西省吴堡县");
		cityCodes.put("612731", "陕西省清涧县");
		cityCodes.put("612732", "陕西省子洲县");
		cityCodes.put("62", "甘肃省");
		cityCodes.put("6201", "甘肃省兰州市");
		cityCodes.put("620101", "甘肃省兰州市市辖区");
		cityCodes.put("620102", "甘肃省兰州市城关区");
		cityCodes.put("620103", "甘肃省兰州市七里河区");
		cityCodes.put("620104", "甘肃省兰州市西固区");
		cityCodes.put("620105", "甘肃省兰州市安宁区");
		cityCodes.put("620111", "甘肃省兰州市红古区");
		cityCodes.put("620121", "甘肃省永登县");
		cityCodes.put("620122", "甘肃省皋兰县");
		cityCodes.put("620123", "甘肃省榆中县");
		cityCodes.put("6202", "甘肃省嘉峪关市");
		cityCodes.put("620201", "甘肃省嘉峪关市市辖区");
		cityCodes.put("6203", "甘肃省金昌市");
		cityCodes.put("620301", "甘肃省金昌市市辖区");
		cityCodes.put("620302", "甘肃省金昌市金川区");
		cityCodes.put("620321", "甘肃省永昌县");
		cityCodes.put("6204", "甘肃省白银市");
		cityCodes.put("620401", "甘肃省白银市市辖区");
		cityCodes.put("620402", "甘肃省白银市白银区");
		cityCodes.put("620403", "甘肃省白银市平川区");
		cityCodes.put("620421", "甘肃省清远县");
		cityCodes.put("620422", "甘肃省会宁县");
		cityCodes.put("620423", "甘肃省景泰县");
		cityCodes.put("6205", "甘肃省天水市");
		cityCodes.put("620501", "甘肃省天水市市辖区");
		cityCodes.put("620502", "甘肃省天水市秦城区");
		cityCodes.put("620503", "甘肃省天水市北道区");
		cityCodes.put("620521", "甘肃省清水县");
		cityCodes.put("620522", "甘肃省秦安县");
		cityCodes.put("620523", "甘肃省甘谷县");
		cityCodes.put("620524", "甘肃省武山县");
		cityCodes.put("620525", "甘肃省张家川回族自治县");
		cityCodes.put("6221", "甘肃省酒泉地区");
		cityCodes.put("622101", "甘肃省玉门市");
		cityCodes.put("622102", "甘肃省酒泉市");
		cityCodes.put("622103", "甘肃省敦煌市");
		cityCodes.put("622123", "甘肃省金塔县");
		cityCodes.put("622124", "甘肃省肃北蒙古族自治县");
		cityCodes.put("622125", "甘肃省阿克塞哈萨克族自治县");
		cityCodes.put("622126", "甘肃省安西县");
		cityCodes.put("6222", "甘肃省张掖地区");
		cityCodes.put("622201", "甘肃省张掖市");
		cityCodes.put("622222", "甘肃省肃南裕固族自治县");
		cityCodes.put("622223", "甘肃省民乐县");
		cityCodes.put("622224", "甘肃省临泽县");
		cityCodes.put("622225", "甘肃省高台县");
		cityCodes.put("622226", "甘肃省山丹县");
		cityCodes.put("6223", "甘肃省武威地区");
		cityCodes.put("622301", "甘肃省武威市");
		cityCodes.put("622322", "甘肃省民勤县　");
		cityCodes.put("622323", "甘肃省古浪县");
		cityCodes.put("622326", "甘肃省天祝藏族自治县");
		cityCodes.put("6224", "甘肃省定西地区");
		cityCodes.put("622421", "甘肃省定西县");
		cityCodes.put("622424", "甘肃省通渭县");
		cityCodes.put("622425", "甘肃省陇西县");
		cityCodes.put("622426", "甘肃省渭源县");
		cityCodes.put("622427", "甘肃省临洮县");
		cityCodes.put("622428", "甘肃省漳县");
		cityCodes.put("622429", "甘肃省岷县");
		cityCodes.put("6226", "甘肃省陇南地区");
		cityCodes.put("622621", "甘肃省武都县");
		cityCodes.put("622623", "甘肃省宕昌县");
		cityCodes.put("622624", "甘肃省成县");
		cityCodes.put("622625", "甘肃省康县");
		cityCodes.put("622626", "甘肃省文县");
		cityCodes.put("622627", "甘肃省西和县");
		cityCodes.put("622628", "甘肃省礼县");
		cityCodes.put("622629", "甘肃省两当县");
		cityCodes.put("622630", "甘肃省徽县");
		cityCodes.put("6227", "甘肃省平凉地区");
		cityCodes.put("622701", "甘肃省平凉市");
		cityCodes.put("622722", "甘肃省泾川县");
		cityCodes.put("622723", "甘肃省灵台县");
		cityCodes.put("622724", "甘肃省崇信县");
		cityCodes.put("622725", "甘肃省华亭县");
		cityCodes.put("622726", "甘肃省庄浪县");
		cityCodes.put("622727", "甘肃省静宁县");
		cityCodes.put("6228", "甘肃省庆阳地区");
		cityCodes.put("622801", "甘肃省西峰市");
		cityCodes.put("622821", "甘肃省庆阳县");
		cityCodes.put("622822", "甘肃省环县");
		cityCodes.put("622823", "甘肃省华池县");
		cityCodes.put("622824", "甘肃省合水县");
		cityCodes.put("622825", "甘肃省正宁县");
		cityCodes.put("622826", "甘肃省宁县");
		cityCodes.put("622827", "甘肃省镇原县");
		cityCodes.put("6229", "甘肃省临夏回族自治州");
		cityCodes.put("622901", "甘肃省临夏市");
		cityCodes.put("622921", "甘肃省临夏县");
		cityCodes.put("622922", "甘肃省康乐县");
		cityCodes.put("622923", "甘肃省永靖县");
		cityCodes.put("622924", "甘肃省广河县");
		cityCodes.put("622925", "甘肃省和政县");
		cityCodes.put("622926", "甘肃省东乡族自治县");
		cityCodes.put("622927", "甘肃省积石山保安族东乡族撒拉族");
		cityCodes.put("6230", "甘肃省甘南藏族自治州");
		cityCodes.put("623021", "甘肃省临潭县");
		cityCodes.put("623022", "甘肃省卓尼县");
		cityCodes.put("623023", "甘肃省舟曲县");
		cityCodes.put("623024", "甘肃省迭部县");
		cityCodes.put("623025", "甘肃省玛曲县");
		cityCodes.put("623026", "甘肃省碌曲县");
		cityCodes.put("623027", "甘肃省夏河县");
		cityCodes.put("63", "青海省");
		cityCodes.put("6301", "青海省西宁市");
		cityCodes.put("630101", "青海省西宁市市辖区");
		cityCodes.put("630102", "青海省西宁市城东区");
		cityCodes.put("630103", "青海省西宁市城中区");
		cityCodes.put("630104", "青海省西宁市城西区");
		cityCodes.put("630105", "青海省西宁市城北区");
		cityCodes.put("630121", "青海省大通回族土族自治县");
		cityCodes.put("6321", "青海省海东地区");
		cityCodes.put("632121", "青海省平安县");
		cityCodes.put("632122", "青海省民和回族土族自治县");
		cityCodes.put("632123", "青海省乐都县");
		cityCodes.put("632124", "青海省湟中县");
		cityCodes.put("632125", "青海省湟源县");
		cityCodes.put("632126", "青海省互助土族自治县");
		cityCodes.put("632127", "青海省化隆回族自治县");
		cityCodes.put("632128", "青海省循化撒拉族自治县");
		cityCodes.put("6322", "青海省海北藏族自治州");
		cityCodes.put("632221", "青海省门源回族自治县");
		cityCodes.put("632222", "青海省祁连县");
		cityCodes.put("632223", "青海省海晏县");
		cityCodes.put("632224", "青海省刚察县");
		cityCodes.put("6323", "青海省黄南藏族自治州");
		cityCodes.put("632321", "青海省同仁县");
		cityCodes.put("632322", "青海省尖扎县");
		cityCodes.put("632323", "青海省泽库县");
		cityCodes.put("632324", "青海省河南蒙古族自治县");
		cityCodes.put("6325", "青海省海南藏族自治州");
		cityCodes.put("632521", "青海省共和县");
		cityCodes.put("632522", "青海省同德县");
		cityCodes.put("632523", "青海省贵德县");
		cityCodes.put("632524", "青海省兴海县");
		cityCodes.put("632525", "青海省贵南县");
		cityCodes.put("6326", "青海省果洛藏族自治州");
		cityCodes.put("632621", "青海省玛沁县");
		cityCodes.put("632622", "青海省班玛县");
		cityCodes.put("632623", "青海省甘德县");
		cityCodes.put("632624", "青海省达日县");
		cityCodes.put("632625", "青海省久治县");
		cityCodes.put("632626", "青海省玛多县");
		cityCodes.put("6327", "青海省玉树藏族自治州");
		cityCodes.put("632721", "青海省玉树县");
		cityCodes.put("632722", "青海省杂多县");
		cityCodes.put("632723", "青海省称多县");
		cityCodes.put("632724", "青海省治多县");
		cityCodes.put("632725", "青海省囊谦县");
		cityCodes.put("632726", "青海省曲麻莱县");
		cityCodes.put("6328", "青海省海西蒙古族藏族自治州");
		cityCodes.put("632801", "青海省格尔木市");
		cityCodes.put("632802", "青海省德令哈市");
		cityCodes.put("632821", "青海省乌兰县");
		cityCodes.put("632822", "青海省都兰县");
		cityCodes.put("632823", "青海省天峻县");
		cityCodes.put("64", "宁夏回族自治区");
		cityCodes.put("6401", "宁夏银川市");
		cityCodes.put("640101", "宁夏银川市市辖区");
		cityCodes.put("640102", "宁夏银川市城区");
		cityCodes.put("640103", "宁夏银川市新城区");
		cityCodes.put("640111", "宁夏银川市郊区");
		cityCodes.put("640121", "宁夏永宁县");
		cityCodes.put("640122", "宁夏贺兰县");
		cityCodes.put("6402", "宁夏石嘴山市");
		cityCodes.put("640201", "宁夏石嘴山市市辖区");
		cityCodes.put("640202", "宁夏石嘴山市大武口区");
		cityCodes.put("640203", "宁夏石嘴山市石嘴山区");
		cityCodes.put("640204", "宁夏石嘴山市石炭井区");
		cityCodes.put("640221", "宁夏平罗县");
		cityCodes.put("640222", "宁夏陶乐县");
		cityCodes.put("640223", "宁夏惠农县");
		cityCodes.put("6421", "宁夏银南地区");
		cityCodes.put("642101", "宁夏吴忠市");
		cityCodes.put("642102", "宁夏青铜峡市");
		cityCodes.put("642123", "宁夏中卫县");
		cityCodes.put("642124", "宁夏中宁县");
		cityCodes.put("642125", "宁夏灵武县");
		cityCodes.put("642126", "宁夏盐池县");
		cityCodes.put("642127", "宁夏同心县");
		cityCodes.put("6422", "宁夏固原地区");
		cityCodes.put("642221", "宁夏固原县");
		cityCodes.put("642222", "宁夏海原县");
		cityCodes.put("642223", "宁夏西吉县");
		cityCodes.put("642224", "宁夏隆德县");
		cityCodes.put("642225", "宁夏泾源县");
		cityCodes.put("642226", "宁夏彭阳县");
		cityCodes.put("65", "新疆维吾尔族自治区");
		cityCodes.put("6501", "新疆乌鲁木齐市");
		cityCodes.put("650101", "新疆乌鲁木齐市市辖区");
		cityCodes.put("650102", "新疆乌鲁木齐市天山区");
		cityCodes.put("650103", "新疆乌鲁木齐市沙衣巴克区");
		cityCodes.put("650104", "新疆乌鲁木齐市新市区");
		cityCodes.put("650105", "新疆乌鲁木齐市水磨沟区");
		cityCodes.put("650106", "新疆乌鲁木齐市头屯河区");
		cityCodes.put("650107", "新疆乌鲁木齐市南山矿区");
		cityCodes.put("650108", "新疆乌鲁木齐市东山区");
		cityCodes.put("650121", "新疆乌鲁木齐县");
		cityCodes.put("6502", "新疆克拉玛依市");
		cityCodes.put("650201", "新疆克拉玛依市市辖区");
		cityCodes.put("650202", "新疆克拉玛依市独山子区");
		cityCodes.put("650203", "新疆克拉玛依市克拉玛依区");
		cityCodes.put("650204", "新疆克拉玛依市白碱滩区");
		cityCodes.put("650205", "新疆克拉玛依市乌尔禾区");
		cityCodes.put("6521", "新疆吐鲁番地区");
		cityCodes.put("652101", "新疆吐鲁番市");
		cityCodes.put("652122", "新疆鄯善县");
		cityCodes.put("652123", "新疆托克逊县");
		cityCodes.put("6522", "新疆哈密地区");
		cityCodes.put("652201", "新疆哈密市");
		cityCodes.put("652222", "新疆巴里坤哈萨克自治县");
		cityCodes.put("652223", "新疆伊吾县");
		cityCodes.put("6523", "新疆昌吉回族自治州");
		cityCodes.put("652301", "新疆昌吉市");
		cityCodes.put("652302", "新疆阜康市");
		cityCodes.put("652322", "新疆米泉县");
		cityCodes.put("652323", "新疆呼图壁县");
		cityCodes.put("652324", "新疆玛纳斯县");
		cityCodes.put("652325", "新疆奇台县");
		cityCodes.put("652327", "新疆吉木萨尔县");
		cityCodes.put("652328", "新疆木垒哈萨克自治县");
		cityCodes.put("6527", "新疆博尔塔拉蒙古自治州");
		cityCodes.put("652701", "新疆博乐市");
		cityCodes.put("652722", "新疆精河县");
		cityCodes.put("652723", "新疆温泉县");
		cityCodes.put("6528", "新疆巴音郭楞蒙古自治州");
		cityCodes.put("652801", "新疆库尔勒市");
		cityCodes.put("652822", "新疆轮台县");
		cityCodes.put("652823", "新疆尉梨县");
		cityCodes.put("652824", "新疆若羌县");
		cityCodes.put("652825", "新疆且未县");
		cityCodes.put("652826", "新疆焉耆回族自治县");
		cityCodes.put("652827", "新疆和静县");
		cityCodes.put("652828", "新疆和硕县");
		cityCodes.put("652829", "新疆博湖县");
		cityCodes.put("6529", "新疆阿克苏地区");
		cityCodes.put("652901", "新疆阿克苏市");
		cityCodes.put("652922", "新疆温宿县");
		cityCodes.put("652923", "新疆库车县");
		cityCodes.put("652924", "新疆沙雅县");
		cityCodes.put("652925", "新疆新和县");
		cityCodes.put("652926", "新疆拜城县");
		cityCodes.put("652927", "新疆乌什县");
		cityCodes.put("652928", "新疆阿瓦提县");
		cityCodes.put("652929", "新疆柯坪县");
		cityCodes.put("6530", "新疆克孜勒苏柯尔克孜自治州");
		cityCodes.put("653001", "新疆阿图什市");
		cityCodes.put("653022", "新疆阿克陶县");
		cityCodes.put("653023", "新疆阿合奇县");
		cityCodes.put("653024", "新疆乌恰县");
		cityCodes.put("6531", "新疆喀什地区");
		cityCodes.put("653101", "新疆喀什市");
		cityCodes.put("653121", "新疆疏附县");
		cityCodes.put("653122", "新疆疏勒县");
		cityCodes.put("653123", "新疆英吉沙县");
		cityCodes.put("653124", "新疆泽普县");
		cityCodes.put("653125", "新疆莎车县");
		cityCodes.put("653126", "新疆叶城县");
		cityCodes.put("653127", "新疆麦盖提县");
		cityCodes.put("653128", "新疆岳普湖县");
		cityCodes.put("653129", "新疆伽师县");
		cityCodes.put("653130", "新疆巴楚县");
		cityCodes.put("653131", "新疆塔什库尔干塔吉克自治县");
		cityCodes.put("6532", "新疆和田地区");
		cityCodes.put("653201", "新疆和田市");
		cityCodes.put("653221", "新疆和田县");
		cityCodes.put("653222", "新疆墨玉县");
		cityCodes.put("653223", "新疆皮山县");
		cityCodes.put("653224", "新疆洛浦县");
		cityCodes.put("653225", "新疆策勒县");
		cityCodes.put("653226", "新疆于田县");
		cityCodes.put("653227", "新疆民丰县");
		cityCodes.put("6540", "新疆伊犁哈萨克自治州");
		cityCodes.put("654001", "新疆奎屯市");
		cityCodes.put("6541", "新疆伊犁地区");
		cityCodes.put("654101", "新疆伊宁市");
		twFirstCode.put("A", 10);
		twFirstCode.put("B", 11);
		twFirstCode.put("C", 12);
		twFirstCode.put("D", 13);
		twFirstCode.put("E", 14);
		twFirstCode.put("F", 15);
		twFirstCode.put("G", 16);
		twFirstCode.put("H", 17);
		twFirstCode.put("J", 18);
		twFirstCode.put("K", 19);
		twFirstCode.put("L", 20);
		twFirstCode.put("M", 21);
		twFirstCode.put("N", 22);
		twFirstCode.put("P", 23);
		twFirstCode.put("Q", 24);
		twFirstCode.put("R", 25);
		twFirstCode.put("S", 26);
		twFirstCode.put("T", 27);
		twFirstCode.put("U", 28);
		twFirstCode.put("V", 29);
		twFirstCode.put("X", 30);
		twFirstCode.put("Y", 31);
		twFirstCode.put("W", 32);
		twFirstCode.put("Z", 33);
		twFirstCode.put("I", 34);
		twFirstCode.put("O", 35);
		hkFirstCode.put("A", 1);
		hkFirstCode.put("B", 2);
		hkFirstCode.put("C", 3);
		hkFirstCode.put("R", 18);
		hkFirstCode.put("U", 21);
		hkFirstCode.put("Z", 26);
		hkFirstCode.put("X", 24);
		hkFirstCode.put("W", 23);
		hkFirstCode.put("O", 15);
		hkFirstCode.put("N", 14);
	}

	/**
	 * 将15位身份证号码转换为18位
	 * 
	 * @param idCard
	 *            15位身份编码
	 * @return 18位身份编码
	 */
	public static String conver15CardTo18(String idCard) {
		String idCard18 = "";
		if (idCard.length() != CHINA_ID_MIN_LENGTH) {
			return null;
		}
		if (isNum(idCard)) {
			// 获取出生年月日
			String birthday = idCard.substring(6, 12);
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			if (birthDate != null)
				cal.setTime(birthDate);
			// 获取出生年(完全表现形式,如：2010)
			String sYear = String.valueOf(cal.get(Calendar.YEAR));
			idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
			// 转换字符数组
			char[] cArr = idCard18.toCharArray();
			if (cArr != null) {
				int[] iCard = converCharToInt(cArr);
				int iSum17 = getPowerSum(iCard);
				// 获取校验位
				String sVal = getCheckCode18(iSum17);
				if (sVal.length() > 0) {
					idCard18 += sVal;
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
		return idCard18;
	}

	/**
	 * 验证身份证是否合法
	 */
	public static boolean validateCard(String idCard) {
		String card = idCard.trim();
		if (validateIdCard18(card)) {
			return true;
		}
		if (validateIdCard15(card)) {
			return true;
		}
		String[] cardval = validateIdCard10(card);
		if (cardval != null) {
			if (cardval[2].equals("true")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证18位身份编码是否合法
	 * 
	 * @param idCard
	 *            身份编码
	 * @return 是否合法
	 */
	public static boolean validateIdCard18(String idCard) {
		boolean bTrue = false;
		if (idCard.length() == CHINA_ID_MAX_LENGTH) {
			// 前17位
			String code17 = idCard.substring(0, 17);
			// 第18位
			String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
			if (isNum(code17)) {
				char[] cArr = code17.toCharArray();
				if (cArr != null) {
					int[] iCard = converCharToInt(cArr);
					int iSum17 = getPowerSum(iCard);
					// 获取校验位
					String val = getCheckCode18(iSum17);
					if (val.length() > 0) {
						if (val.equalsIgnoreCase(code18)) {
							bTrue = true;
						}
					}
				}
			}
		}
		return bTrue;
	}

	/**
	 * 验证15位身份编码是否合法
	 * 
	 * @param idCard
	 *            身份编码
	 * @return 是否合法
	 */
	public static boolean validateIdCard15(String idCard) {
		if (idCard.length() != CHINA_ID_MIN_LENGTH) {
			return false;
		}
		if (isNum(idCard)) {
			String proCode = idCard.substring(0, 2);
			if (cityCodes.get(proCode) == null) {
				return false;
			}
			String birthCode = idCard.substring(6, 12);
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("yy").parse(birthCode.substring(0, 2));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			if (birthDate != null)
				cal.setTime(birthDate);
			if (!valiDate(cal.get(Calendar.YEAR), Integer.valueOf(birthCode.substring(2, 4)), Integer.valueOf(birthCode.substring(4, 6)))) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 验证10位身份编码是否合法
	 * 
	 * @param idCard
	 *            身份编码
	 * @return 身份证信息数组
	 *         <p>
	 *         [0] - 台湾、澳门、香港 [1] - 性别(男M,女F,未知N) [2] - 是否合法(合法true,不合法false)
	 *         若不是身份证件号码则返回null
	 *         </p>
	 */
	public static String[] validateIdCard10(String idCard) {
		String[] info = new String[3];
		String card = idCard.replaceAll("[\\(|\\)]", "");
		if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
			return null;
		}
		if (idCard.matches("^[a-zA-Z][0-9]{9}{1}")) { // 台湾
			info[0] = "台湾";
			System.out.println("11111");
			String char2 = idCard.substring(1, 2);
			if (char2.equals("1")) {
				info[1] = "M";
				System.out.println("MMMMMMM");
			} else if (char2.equals("2")) {
				info[1] = "F";
				System.out.println("FFFFFFF");
			} else {
				info[1] = "N";
				info[2] = "false";
				System.out.println("NNNN");
				return info;
			}
			info[2] = validateTWCard(idCard) ? "true" : "false";
		} else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?{1}")) { // 澳门
			info[0] = "澳门";
			info[1] = "N";
			// TODO
		} else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?{1}")) { // 香港
			info[0] = "香港";
			info[1] = "N";
			info[2] = validateHKCard(idCard) ? "true" : "false";
		} else {
			return null;
		}
		return info;
	}

	/**
	 * 验证台湾身份证号码
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 验证码是否符合
	 */
	public static boolean validateTWCard(String idCard) {
		String start = idCard.substring(0, 1);
		String mid = idCard.substring(1, 9);
		String end = idCard.substring(9, 10);
		Integer iStart = twFirstCode.get(start);
		Integer sum = iStart / 10 + (iStart % 10) * 9;
		char[] chars = mid.toCharArray();
		Integer iflag = 8;
		for (char c : chars) {
			sum = sum + Integer.valueOf(c + "") * iflag;
			iflag--;
		}
		return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
	}

	/**
	 * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查)
	 * <p>
	 * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35
	 * 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
	 * </p>
	 * <p>
	 * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效
	 * </p>
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 验证码是否符合
	 */
	public static boolean validateHKCard(String idCard) {
		String card = idCard.replaceAll("[\\(|\\)]", "");
		Integer sum = 0;
		if (card.length() == 9) {
			sum = (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 9 + (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]) - 55) * 8;
			card = card.substring(1, 9);
		} else {
			sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 8;
		}
		String mid = card.substring(1, 7);
		String end = card.substring(7, 8);
		char[] chars = mid.toCharArray();
		Integer iflag = 7;
		for (char c : chars) {
			sum = sum + Integer.valueOf(c + "") * iflag;
			iflag--;
		}
		if (end.toUpperCase().equals("A")) {
			sum = sum + 10;
		} else {
			sum = sum + Integer.valueOf(end);
		}
		return (sum % 11 == 0) ? true : false;
	}

	/**
	 * 将字符数组转换成数字数组
	 * 
	 * @param ca
	 *            字符数组
	 * @return 数字数组
	 */
	public static int[] converCharToInt(char[] ca) {
		int len = ca.length;
		int[] iArr = new int[len];
		try {
			for (int i = 0; i < len; i++) {
				iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return iArr;
	}

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 * 
	 * @param iArr
	 * @return 身份证编码。
	 */
	public static int getPowerSum(int[] iArr) {
		int iSum = 0;
		if (power.length == iArr.length) {
			for (int i = 0; i < iArr.length; i++) {
				for (int j = 0; j < power.length; j++) {
					if (i == j) {
						iSum = iSum + iArr[i] * power[j];
					}
				}
			}
		}
		return iSum;
	}

	/**
	 * 将power和值与11取模获得余数进行校验码判断
	 * 
	 * @param iSum
	 * @return 校验位
	 */
	public static String getCheckCode18(int iSum) {
		String sCode = "";
		switch (iSum % 11) {
		case 10:
			sCode = "2";
			break;
		case 9:
			sCode = "3";
			break;
		case 8:
			sCode = "4";
			break;
		case 7:
			sCode = "5";
			break;
		case 6:
			sCode = "6";
			break;
		case 5:
			sCode = "7";
			break;
		case 4:
			sCode = "8";
			break;
		case 3:
			sCode = "9";
			break;
		case 2:
			sCode = "x";
			break;
		case 1:
			sCode = "0";
			break;
		case 0:
			sCode = "1";
			break;
		}
		return sCode;
	}

	/**
	 * 根据身份编号获取年龄
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 年龄
	 */
	public static int getAgeByIdCard(String idCard) {
		int iAge = 0;
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String year = idCard.substring(6, 10);
		Calendar cal = Calendar.getInstance();
		int iCurrYear = cal.get(Calendar.YEAR);
		iAge = iCurrYear - Integer.valueOf(year);
		return iAge;
	}

	/**
	 * 根据身份编号获取生日
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 生日(yyyyMMdd)
	 */
	public static String getBirthByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return idCard.substring(6, 14);
	}

	/**
	 * 根据身份编号获取生日年
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 生日(yyyy)
	 */
	public static Short getYearByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return Short.valueOf(idCard.substring(6, 10));
	}

	/**
	 * 根据身份编号获取生日月
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 生日(MM)
	 */
	public static Short getMonthByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return Short.valueOf(idCard.substring(10, 12));
	}

	/**
	 * 根据身份编号获取生日天
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 生日(dd)
	 */
	public static Short getDateByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return Short.valueOf(idCard.substring(12, 14));
	}

	/**
	 * 根据身份编号获取性别
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 性别(M-男，F-女，N-未知)
	 */
	public static String getGenderByIdCard(String idCard) {
		String sGender = "N";
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String sCardNum = idCard.substring(16, 17);
		if (Integer.parseInt(sCardNum) % 2 != 0) {
			sGender = "M";
		} else {
			sGender = "F";
		}
		return sGender;
	}

	/**
	 * 根据身份编号获取户籍省份
	 * 
	 * @param idCard
	 *            身份编码
	 * @return 省级编码。
	 */
	public static String getProvinceByIdCard(String idCard) {
		int len = idCard.length();
		String sProvince = null;
		String sProvinNum = "";
		if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
			sProvinNum = idCard.substring(0, 2);
		}
		sProvince = cityCodes.get(sProvinNum + "0000");
		return sProvince;
	}

	public static String getCityByIdCard(String idCard) {
		int len = idCard.length();
		String sProvince = null;
		String sProvinNum = "";
		if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
			sProvinNum = idCard.substring(0, 4);
		}
		sProvince = cityCodes.get(sProvinNum + "00");
		return sProvince;
	}

	/**
	 * 数字验证
	 * 
	 * @param val
	 * @return 提取的数字。
	 */
	public static boolean isNum(String val) {
		return val == null || "".equals(val) ? false : val.matches("^[0-9]*{1}");
	}

	/**
	 * 验证小于当前日期 是否有效
	 * 
	 * @param iYear
	 *            待验证日期(年)
	 * @param iMonth
	 *            待验证日期(月 1-12)
	 * @param iDate
	 *            待验证日期(日)
	 * @return 是否有效
	 */
	public static boolean valiDate(int iYear, int iMonth, int iDate) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int datePerMonth;
		if (iYear < MIN || iYear >= year) {
			return false;
		}
		if (iMonth < 1 || iMonth > 12) {
			return false;
		}
		switch (iMonth) {
		case 4:
		case 6:
		case 9:
		case 11:
			datePerMonth = 30;
			break;
		case 2:
			boolean dm = ((iYear % 4 == 0 && iYear % 100 != 0) || (iYear % 400 == 0)) && (iYear > MIN && iYear < year);
			datePerMonth = dm ? 29 : 28;
			break;
		default:
			datePerMonth = 31;
		}
		return (iDate >= 1) && (iDate <= datePerMonth);
	}

	/**
	 * 根据身份证号，自动获取对应的星座
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 星座
	 */
	public static String getConstellationById(String idCard) {
		if (!validateCard(idCard))
			return "";
		int month = IDCardUtil.getMonthByIdCard(idCard);
		int day = IDCardUtil.getDateByIdCard(idCard);
		String strValue = "";

		if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
			strValue = "水瓶座";
		} else if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) {
			strValue = "双鱼座";
		} else if ((month == 3 && day > 20) || (month == 4 && day <= 19)) {
			strValue = "白羊座";
		} else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
			strValue = "金牛座";
		} else if ((month == 5 && day >= 21) || (month == 6 && day <= 21)) {
			strValue = "双子座";
		} else if ((month == 6 && day > 21) || (month == 7 && day <= 22)) {
			strValue = "巨蟹座";
		} else if ((month == 7 && day > 22) || (month == 8 && day <= 22)) {
			strValue = "狮子座";
		} else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
			strValue = "处女座";
		} else if ((month == 9 && day >= 23) || (month == 10 && day <= 23)) {
			strValue = "天秤座";
		} else if ((month == 10 && day > 23) || (month == 11 && day <= 22)) {
			strValue = "天蝎座";
		} else if ((month == 11 && day > 22) || (month == 12 && day <= 21)) {
			strValue = "射手座";
		} else if ((month == 12 && day > 21) || (month == 1 && day <= 19)) {
			strValue = "魔羯座";
		}

		return strValue;
	}

	/**
	 * 根据身份证号，自动获取对应的生肖
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 生肖
	 */
	public static String getZodiacById(String idCard) { // 根据身份证号，自动返回对应的生肖
		if (!validateCard(idCard))
			return "";

		String sSX[] = { "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗" };
		int year = IDCardUtil.getYearByIdCard(idCard);
		int end = 3;
		int x = (year - end) % 12;

		String retValue = "";
		retValue = sSX[x];

		return retValue;
	}

	/**
	 * 根据身份证号，自动获取对应的天干地支
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 天干地支
	 */
	public static String getChineseEraById(String idCard) { // 根据身份证号，自动返回对应的生肖
		if (!validateCard(idCard))
			return "";

		String sTG[] = { "癸", "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "任" };
		String sDZ[] = { "亥", "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌" };

		int year = IDCardUtil.getYearByIdCard(idCard);
		int i = (year - 3) % 10;
		int j = (year - 3) % 12;

		String retValue = "";
		retValue = sTG[i] + sDZ[j];

		return retValue;
	}

	public static Map<String, Object> getInfo(String CardCode) {
		try {
			if (StringUtils.isBlank(CardCode)) {
				return null;
			} else if (CardCode.length() == 18) {
				return getCarInfo(CardCode);
			} else if (CardCode.length() == 15) {
				return getCarInfo15W(CardCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据身份证的号码算出当前身份证持有者的性别和年龄 18位身份证
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Map<String, Object> getCarInfo(String CardCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String year = CardCode.substring(6).substring(0, 4);// 得到年份
		String yue = CardCode.substring(10).substring(0, 2);// 得到月份
		// String day=CardCode.substring(12).substring(0,2);//得到日
		Integer sex;
		if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
			// sex = "女";
			sex = 1;
		} else {
			// sex = "男";
			sex = 0;
		}
		Date date = new Date();// 得到当前的系统时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String fyear = format.format(date).substring(0, 4);// 当前年份
		String fyue = format.format(date).substring(5, 7);// 月份
		// String fday=format.format(date).substring(8,10);
		int age = 0;
		if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
			age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
		} else {// 当前用户还没过生
			age = Integer.parseInt(fyear) - Integer.parseInt(year);
		}
		map.put("sex", sex);
		map.put("age", age);
		return map;
	}

	/**
	 * 15位身份证的验证
	 * 
	 * @param
	 * @throws Exception
	 */
	private static Map<String, Object> getCarInfo15W(String card) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String uyear = "19" + card.substring(6, 8);// 年份
		String uyue = card.substring(8, 10);// 月份
		// String uday=card.substring(10, 12);//日
		String usex = card.substring(14, 15);// 用户的性别
		Integer sex;
		if (Integer.parseInt(usex) % 2 == 0) {
			// sex = "女";
			sex = 1;
		} else {
			// sex = "男";
			sex = 0;
		}
		Date date = new Date();// 得到当前的系统时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String fyear = format.format(date).substring(0, 4);// 当前年份
		String fyue = format.format(date).substring(5, 7);// 月份
		// String fday=format.format(date).substring(8,10);
		int age = 0;
		if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
			age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;
		} else {// 当前用户还没过生
			age = Integer.parseInt(fyear) - Integer.parseInt(uyear);
		}
		map.put("sex", sex);
		map.put("age", age);
		return map;
	}

}
