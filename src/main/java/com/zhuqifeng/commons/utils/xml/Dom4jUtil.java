package com.zhuqifeng.commons.utils.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 使用dom4j解析xml文档
 * 
 * @author Administrator
 * 
 */
public class Dom4jUtil {
	
	private String formatXml(String str) throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		StringReader in = new StringReader(str);
		org.dom4j.Document doc = reader.read(in);
		OutputFormat formater = OutputFormat.createPrettyPrint();
		formater.setEncoding("UTF-8");
		StringWriter out = new StringWriter();
		XMLWriter writer = new XMLWriter(out, formater);
		writer.write(doc);
		writer.close();
		return out.toString();
	}

	public void parseXml01() {
		try {
			// 将src下面的xml转换为输入流
			InputStream inputStream = new FileInputStream(new File(
					"D:/project/dynamicWeb/src/resource/module01.xml"));
			// InputStream inputStream =
			// this.getClass().getResourceAsStream("/module01.xml");//也可以根据类的编译文件相对路径去找xml
			// 创建SAXReader读取器，专门用于读取xml
			SAXReader saxReader = new SAXReader();
			// 根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
			// Document document = saxReader.read(inputStream);
			Document document = saxReader.read(new File(
					"D:/project/dynamicWeb/src/resource/module01.xml"));// 必须指定文件的绝对路径
			// 另外还可以使用DocumentHelper提供的xml转换器也是可以的。
			// Document document =
			// DocumentHelper.parseText("<?xml version=\"1.0\" encoding=\"UTF-8\"?><modules id=\"123\"><module> 这个是module标签的文本信息</module></modules>");

			// 获取根节点对象
			Element rootElement = document.getRootElement();
			System.out.println("根节点名称：" + rootElement.getName());// 获取节点的名称
			System.out.println("根节点有多少属性：" + rootElement.attributeCount());// 获取节点属性数目
			System.out.println("根节点id属性的值：" + rootElement.attributeValue("id"));// 获取节点的属性id的值
			System.out.println("根节点内文本：" + rootElement.getText());// 如果元素有子节点则返回空字符串，否则返回节点内的文本
			// rootElement.getText() 之所以会换行是因为
			// 标签与标签之间使用了tab键和换行符布局，这个也算是文本所以显示出来换行的效果。
			System.out.println("根节点内文本(1)：" + rootElement.getTextTrim());// 去掉的是标签与标签之间的tab键和换行符等等，不是内容前后的空格
			System.out.println("根节点子节点文本内容：" + rootElement.getStringValue()); // 返回当前节点递归所有子节点的文本信息。

			// 获取子节点
			Element element = rootElement.element("module");
			if (element != null) {
				System.out.println("子节点的文本：" + element.getText());// 因为子节点和根节点都是Element对象所以它们的操作方式都是相同的
			}
			// 但是有些情况xml比较复杂，规范不统一，某个节点不存在直接java.lang.NullPointerException，所以获取到element对象之后要先判断一下是否为空

			rootElement.setName("root");// 支持修改节点名称
			System.out.println("根节点修改之后的名称：" + rootElement.getName());
			rootElement.setText("text"); // 同样修改标签内的文本也一样
			System.out.println("根节点修改之后的文本：" + rootElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parseXml02() {
		try {
			// 将src下面的xml转换为输入流
			InputStream inputStream = this.getClass().getResourceAsStream("/module02.xml");
			// 创建SAXReader读取器，专门用于读取xml
			SAXReader saxReader = new SAXReader();
			// 根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
			Document document = saxReader.read(inputStream);

			Element rootElement = document.getRootElement();
			Iterator<Element> modulesIterator = rootElement.elements("module").iterator();
			// rootElement.element("name");获取某一个子元素
			// rootElement.elements("name");获取根节点下子元素moudule节点的集合，返回List集合类型
			// rootElement.elements("module").iterator();把返回的list集合里面每一个元素迭代子节点，全部返回到一个Iterator集合中
			while (modulesIterator.hasNext()) {
				Element moduleElement = modulesIterator.next();
				Element nameElement = moduleElement.element("name");
				System.out.println(nameElement.getName() + ":" + nameElement.getText());
				Element valueElement = moduleElement.element("value");
				System.out.println(valueElement.getName() + ":" + valueElement.getText());
				Element descriptElement = moduleElement.element("descript");
				System.out.println(descriptElement.getName() + ":" + descriptElement.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parseXml03() {
		try {
			// 将src下面的xml转换为输入流
			InputStream inputStream = this.getClass().getResourceAsStream("/module03.xml");
			// 创建SAXReader读取器，专门用于读取xml
			SAXReader saxReader = new SAXReader();
			// 根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
			Document document = saxReader.read(inputStream);

			Element rootElement = document.getRootElement();
			if (rootElement.elements("module") != null) {
				// 因为第一个module标签只有内容没有子节点，直接.iterator()就java.lang.NullPointerException了,
				// 所以需要分开实现
				List<Element> elementList = rootElement.elements("module");
				for (Element element : elementList) {
					if (!element.getTextTrim().equals("")) {
						System.out.println("【1】" + element.getTextTrim());
					} else {
						Element nameElement = element.element("name");
						System.out.println("   【2】" + nameElement.getName() + ":"
								+ nameElement.getText());
						Element valueElement = element.element("value");
						System.out.println("   【2】" + valueElement.getName() + ":"
								+ valueElement.getText());
						Element descriptElement = element.element("descript");
						System.out.println("   【2】" + descriptElement.getName() + ":"
								+ descriptElement.getText());

						List<Element> subElementList = element.elements("module");
						for (Element subElement : subElementList) {
							if (!subElement.getTextTrim().equals("")) {
								System.out.println("      【3】" + subElement.getTextTrim());
							} else {
								Element subnameElement = subElement.element("name");
								System.out.println("      【3】" + subnameElement.getName() + ":"
										+ subnameElement.getText());
								Element subvalueElement = subElement.element("value");
								System.out.println("      【3】" + subvalueElement.getName() + ":"
										+ subvalueElement.getText());
								Element subdescriptElement = subElement.element("descript");
								System.out.println("      【3】" + subdescriptElement.getName() + ":"
										+ subdescriptElement.getText());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void build01() {
		try {
			// DocumentHelper提供了创建Document对象的方法
			Document document = DocumentHelper.createDocument();
			// 添加节点信息
			Element rootElement = document.addElement("modules");
			// 这里可以继续添加子节点，也可以指定内容
			rootElement.setText("这个是module标签的文本信息");
			Element element = rootElement.addElement("module");

			Element nameElement = element.addElement("name");
			Element valueElement = element.addElement("value");
			Element descriptionElement = element.addElement("description");
			nameElement.setText("名称");
			nameElement.addAttribute("language", "java");// 为节点添加属性值
			valueElement.setText("值");
			valueElement.addAttribute("language", "c#");
			descriptionElement.setText("描述");
			descriptionElement.addAttribute("language", "sql server");
			System.out.println(document.asXML()); // 将document文档对象直接转换成字符串输出
			Writer fileWriter = new FileWriter("c:\\module.xml");
			// dom4j提供了专门写入文件的对象XMLWriter
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(document);
			xmlWriter.flush();
			xmlWriter.close();
			System.out.println("xml文档添加成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map readStringXmlOut(String xml) {
		Map map = new HashMap();
		Document doc = null;
		try {
			// 将字符串转为XML
			doc = DocumentHelper.parseText(xml);
			// 获取根节点
			Element rootElt = doc.getRootElement();
			// 拿到根节点的名称
			System.out.println("根节点：" + rootElt.getName());

			// 获取根节点下的子节点head
			Iterator iter = rootElt.elementIterator("head");
			// 遍历head节点
			while (iter.hasNext()) {

				Element recordEle = (Element) iter.next();
				// 拿到head节点下的子节点title值
				String title = recordEle.elementTextTrim("title");
				System.out.println("title:" + title);
				map.put("title", title);
				// 获取子节点head下的子节点script
				Iterator iters = recordEle.elementIterator("script");
				// 遍历Header节点下的Response节点
				while (iters.hasNext()) {
					Element itemEle = (Element) iters.next();
					// 拿到head下的子节点script下的字节点username的值
					String username = itemEle.elementTextTrim("username");
					String password = itemEle.elementTextTrim("password");

					System.out.println("username:" + username);
					System.out.println("password:" + password);
					map.put("username", username);
					map.put("password", password);
				}
			}

			// 获取根节点下的子节点body
			Iterator iterss = rootElt.elementIterator("body");
			// 遍历body节点
			while (iterss.hasNext()) {
				Element recordEless = (Element) iterss.next();
				// 拿到body节点下的子节点result值
				String result = recordEless.elementTextTrim("result");
				System.out.println("result:" + result);
				// 获取子节点body下的子节点form
				Iterator itersElIterator = recordEless.elementIterator("form");
				// 遍历Header节点下的Response节点
				while (itersElIterator.hasNext()) {
					Element itemEle = (Element) itersElIterator.next();
					// 拿到body下的子节点form下的字节点banlce的值
					String banlce = itemEle.elementTextTrim("banlce");
					String subID = itemEle.elementTextTrim("subID");

					System.out.println("banlce:" + banlce);
					System.out.println("subID:" + subID);
					map.put("result", result);
					map.put("banlce", banlce);
					map.put("subID", subID);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
