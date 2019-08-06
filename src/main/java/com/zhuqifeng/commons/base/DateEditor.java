/**  
 * Project Name:ocepay  
 * File Name:Dat.java  
 * Package Name:com.ocepay.base  
 * Date:2019年8月6日  下午1:51:37  
 * Copyright (c) 2019, zz621@126.com All Rights Reserved.   
 */  
  
package com.zhuqifeng.commons.base;  
/**  
 * ClassName:Dat </br>
 * Function: 
 * <p>用一句话描述类的功能</p>
 * Reason:   TODO ADD REASON.  </br>
 * Date:     2019年8月6日  下午1:51:37 
 *
 * @author   zhuqifeng  
 * @version  
 * @since    JDK 1.8  
 * @see       
 */
import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zhuqifeng.commons.utils.base.StringUtils;

/**
 * 
 * (入参转化)
 *
 * <p>
 * 修改历史:                                            <br>  
 * 修改日期            修改人员       版本             修改内容<br>  
 * -------------------------------------------------<br>  
 * 2015年6月15日 下午6:16:17   user     1.0        初始化创建<br>
 * </p> 
 *
 * @version        1.0  
 * @since        JDK1.7
 */
public class DateEditor extends PropertyEditorSupport {

    private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DateFormat dateFormat;
    private boolean allowEmpty = true;

    public DateEditor() {
    }

    public DateEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    /** 
     * Parse the Date from the given text, using the specified DateFormat. 
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && StringUtils.isBlank(text)) {
            // Treat empty String as null value.
            setValue(null);
        } else {
            try {
                if (this.dateFormat != null)
                    setValue(this.dateFormat.parse(text));
                else {
                    if (text.contains(":"))
                        setValue(TIMEFORMAT.parse(text));
                    else
                        setValue(DATEFORMAT.parse(text));
                }
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    /** 
     * Format the Date as String, using the specified DateFormat. 
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        DateFormat dateFormat = this.dateFormat;
        if (dateFormat == null)
            dateFormat = TIMEFORMAT;
        return (value != null ? dateFormat.format(value) : "");
    }

}
  
